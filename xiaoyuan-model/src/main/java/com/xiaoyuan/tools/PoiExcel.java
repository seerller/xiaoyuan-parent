package com.xiaoyuan.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PoiExcel {
	private String FileName = null;
	private Workbook Workbook = null;
	private int f_type = -1;

	public PoiExcel() {
		this(2003);
	}
	
	public PoiExcel(int f_type) {
		this.setF_type(f_type);
		Workbook Workbook = null;
		if(f_type==2003){
			Workbook = new HSSFWorkbook();
		}
		else if(f_type==2007){
			Workbook = new XSSFWorkbook();
		}
		this.setWorkbook(Workbook);
	}

	public PoiExcel(String fileName) throws InvalidFormatException {
		this.setFileName(fileName);
		this.openExcel();
	}

	public void setFileName(String fileName) {
		this.FileName = fileName;
	}

	public String getFileName() {
		return this.FileName;
	}

	public void openExcel() throws InvalidFormatException {
		openExcel(this.getFileName());
	}

	public void openExcel(String fileName) {
		try {
			this.setFileName(fileName);
			this.setWorkbook(this.getWookBookByFile(this.getFileName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Workbook getWookBookByFile(String fileName) throws InvalidFormatException, IOException{
		Workbook wb = null;
		InputStream in = new java.io.FileInputStream(fileName);
		if(!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if(POIFSFileSystem.hasPOIFSHeader(in)){
			wb = new HSSFWorkbook(in);//2003版本
			this.setF_type(2003);
		}
		else{
			/*if(POIXMLDocument.hasOOXMLHeader(in)){
				wb = new XSSFWorkbook(OPCPackage.open(in));//2007版本
				this.setF_type(2007);
			}*/
		}
		if(in!=null){
			in.close();
		}
		return wb;
	}
	
	public void setWorkbook(Workbook Workbook) {
		this.Workbook = Workbook;
	}

	public Workbook getWorkbook() {
		return this.Workbook;
	}
	
	public int getRowCount(int sheetIndex){
		Sheet sheet = this.getSheet(sheetIndex);
		return sheet.getLastRowNum()+1;
	}
	
	public int getSheetCount(){
		return this.Workbook.getNumberOfSheets();
	}
	
	public String getSheetName(int sheetIndex){
		return this.getSheet(sheetIndex).getSheetName();
	}
	
	public Sheet getSheet(int sheetIndex){
		return this.getWorkbook().getSheetAt(sheetIndex);
	}

	public Cell getCell(int sheetIndex, int rowNum, int cellNum) {
		Cell cell = null;
		Sheet sheet = this.getSheet(sheetIndex);
		if(sheet!=null){
			Row row = sheet.getRow(rowNum);
			if(row!=null){
				cell = row.getCell(cellNum);
			}
		}
		return cell;
	}

	
	public String getCellStringValue(int sheetIndex, int rowNum, int cellNum) {
		Sheet sheet = this.getSheet(sheetIndex);
		String str = "";
		if (sheet != null) {
			str = this.getCellStringValue(sheet, rowNum, cellNum);
		}
		return str.trim();
	}

	public String getCellStringValue(Sheet sheet, int rowNum, int cellNum) {
		Row row = sheet.getRow(rowNum);
		String str = "";
		if (row != null) {
			str = this.getCellStringValue(row, cellNum);
		}
		return str.trim();
	}

	public String getCellStringValue(Row row, int cellNum) {
		Cell cell = row.getCell(cellNum);
		String str = "";
		if (cell != null) {
			str = this.getCellStringValue(cell);
		}
		return str.trim();
	}

	public String getCellStringValue(Cell cell) {
		String strCell = "";
		Object inputValue = null;// 单元格值
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:

				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date d = cell.getDateCellValue();
					//strCell = DateUtil.dateToStr(d, "yyyy-MM-dd HH:mm:ss");
				}else{
					// POI默认数字类型都是double,整数后都有.0,这里是处理方法、
					Long longVal = Math.round(cell.getNumericCellValue());
					Double doubleVal = cell.getNumericCellValue();
					if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
						inputValue = longVal;
					}
					else{
						inputValue = doubleVal;
					}
					strCell = String.valueOf(inputValue);
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				try {
					strCell = cell.getStringCellValue();
				} catch (Exception ex) {
					try {
						strCell = String.valueOf(cell.getNumericCellValue());
						int index1 = strCell.indexOf(".0");
						if(index1==strCell.length()-2){
							strCell = strCell.substring(0,index1);
						}
					} catch (Exception ex1) {
						// strCell = cell.getCellFormula();
						strCell = "";
					}
				}
				break;
			case Cell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				strCell = "";
				break;
			}
		}
		return strCell.trim();
	}

	public String getCellDateValue(int sheetIndex, int rowNum, int cellNum) {
		Sheet sheet = this.getSheet(sheetIndex);
		String str = "";
		if (sheet != null) {
			str = this.getCellDateValue(sheet, rowNum, cellNum);
		}
		return str.trim();
	}

	public String getCellDateValue(Sheet sheet, int rowNum, int cellNum) {
		Row row = sheet.getRow(rowNum);
		String str = "";
		if (row != null) {
			str = this.getCellDateValue(row, cellNum);
		}
		return str.trim();
	}

	public String getCellDateValue(Row row, int cellNum) {
		Cell cell = row.getCell(cellNum);
		String str = "";
		if (cell != null) {
			str = this.getCellDateValue(cell);
		}
		return str.trim();
	}

	public String getCellDateValue(Cell cell) {
		return this.getCellDateFormatValue(cell, "yyyy-MM-dd HH:mm:ss");
	}

	public String getCellDateFormatValue(int sheetIndex, int rowNum, int cellNum,
			String formatStr) {
		Sheet sheet = this.getSheet(sheetIndex);
		String str = "";
		if (sheet != null) {
			str = this.getCellDateFormatValue(sheet, rowNum, cellNum, formatStr);
		}
		return str.trim();
	}

	public String getCellDateFormatValue(Sheet sheet, int rowNum,
			int cellNum, String formatStr) {
		Row row = sheet.getRow(rowNum);
		String str = "";
		if (row != null) {
			str = this.getCellDateFormatValue(row, cellNum, formatStr);
		}
		return str.trim();
	}

	public String getCellDateFormatValue(Row row, int cellNum,
			String formatStr) {
		Cell cell = row.getCell(cellNum);
		String str = "";
		if (cell != null) {
			str = this.getCellDateFormatValue(cell, formatStr);
		}
		return str.trim();
	}

	@SuppressWarnings("unused")
	public String getCellDateFormatValue(Cell cell, String formatStr) {
		String strCell = "";
		if (cell != null) {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					formatStr);
			int i = cell.getCellType();
			if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				try {
					// if(HSSFDateUtil.isCellDateFormatted(cell)){}
					strCell = sdf.format(cell.getDateCellValue());
				} catch (Exception ex1) {
					strCell = "";
				}
			} else if ( cell.getCellType() == Cell.CELL_TYPE_STRING) {
				String d = cell.getStringCellValue();
				strCell = d.replaceAll("[年月.]", "-").replace("日", "").trim();
				try {
					strCell = sdf.format(sdf.parse(strCell));
				} catch (Exception ex1) {
					strCell = "";
				}
			}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
				//黄虎修改，2016.10.21数字类型时间提取有错如果提取到的是2016.10这样的字符串时间装换格式错误，所有默认为当月1号
				short format = cell.getCellStyle().getDataFormat();
				
				double value = cell.getNumericCellValue();
				if(format == 194){
					String x = ""+value;
					if(x.indexOf(".",5) < 0 ){
						x+=".1";
					}
					strCell = x.replaceAll("[年月.]", "-").replace("日", "").trim();
					try {
						strCell = sdf.format(sdf.parse(strCell));
					} catch (Exception ex1) {
						strCell = "";
					}
				}else{
					Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
					try {
						strCell = sdf.format(date);
					} catch (Exception ex1) {
						strCell = "";
					}
				}
				//System.out.println(strCell);
			}
		}
		return strCell.trim();
	}

	public void setCellStringValue(int sheetIndex, int rowNum, int cellNum,
			String value) {
		this.setCellStringValue(this.getSheet(sheetIndex),
				rowNum, cellNum, value);
	}

	public void setCellStringValue(Sheet sheet, int rowNum, int cellNum,
			String value) {
		Row row = sheet.getRow(rowNum);
		if(row==null){
			row = sheet.createRow(rowNum);
		}
		this.setCellStringValue(row, cellNum, value);
	}

	public void setCellStringValue(Row row, int cellNum, String value) {
		Cell cell = row.getCell(cellNum);
		if(cell==null){
			cell = row.createCell(cellNum);
		}
		this.setCellStringValue(cell, value);
	}

	public void setCellStringValue(Cell cell, String value) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			//cell.setCellValue(value);
			if(value.indexOf("${<br>}")>-1){
				value = value.replace("${<br>}", "\r\n");
				RichTextString rt = null;
				if(f_type==2003){
					rt = new HSSFRichTextString(value);
				}
				else if(f_type==2007){
					rt = new XSSFRichTextString(value);
				}
				CellStyle cellStyle=Workbook.createCellStyle();
				cellStyle.setWrapText(true);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(rt);
			}
			else{
				cell.setCellValue(value);
			}
		}
	}

	public void setCellIntValue(int sheetIndex, int rowNum, int cellNum, int value) {
		this.setCellIntValue(this.getSheet(sheetIndex), rowNum,
				cellNum, value);
	}

	public void setCellIntValue(Sheet sheet, int rowNum, int cellNum,
			int value) {
		Row row = sheet.getRow(rowNum);
		if(row==null){
			row = sheet.createRow(rowNum);
		}
		this.setCellIntValue(row, cellNum, value);
	}

	public void setCellIntValue(Row row, int cellNum, int value) {
		Cell cell = row.getCell(cellNum);
		if(cell==null){
			cell = row.createCell(cellNum);
		}
		this.setCellIntValue(cell, value);
	}

	public void setCellIntValue(Cell cell, int value) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value);
		}
	}

	public void setCellDoubleValue(int sheetIndex, int rowNum, int cellNum,
			double value) {
		this.setCellDoubleValue(this.getSheet(sheetIndex),
				rowNum, cellNum, value);
	}

	public void setCellDoubleValue(Sheet sheet, int rowNum, int cellNum,
			double value) {
		Row row = sheet.getRow(rowNum);
		if(row==null){
			row = sheet.createRow(rowNum);
		}
		this.setCellDoubleValue(row, cellNum, value);
	}

	public void setCellDoubleValue(Row row, int cellNum, double value) {
		Cell cell = row.getCell(cellNum);
		if(cell==null){
			cell = row.createCell(cellNum);
		}
		this.setCellDoubleValue(cell, value);
	}

	public void setCellDoubleValue(Cell cell, double value) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(value);
		}
	}

	public void save() {
		this.save(this.getFileName());
	}

	public void save(String fileName) {
		
		try {
			File file = new File(fileName);
			File fileParent = file.getParentFile();//父文件夹，文件夹不存在则先创建文件夹
			if(!fileParent.exists()){  
			    fileParent.mkdirs();  
			}  
			if(!file.exists())
				file.createNewFile();
			//关闭编辑
			FileOutputStream fout = new FileOutputStream(fileName);
			this.getWorkbook().write(fout);
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setRegionStyle(Sheet sheet, CellRangeAddress region, CellStyle cs) {
		for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
			Row row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				Cell cell = row.getCell(j);
				if(cell!=null){
					cell.setCellStyle(cs);
				}
			}
		}
	}
	
	public String getCellPosition(int rowNum,int colNum)  {    
          String cposition="";  
          if(colNum>26)  
          {  
              int multiple=(colNum)/26;  
              int remainder=(colNum)%26;  
              char mchar=(char)(multiple+64);  
              char rchar=(char)(remainder+64);  
              cposition=mchar+""+rchar;  
          }  
          else  
          {  
              cposition=(char)(colNum+64)+"";
          }  
          cposition+=rowNum;  
          return cposition;  
    }
	
	/**
	 * @return the f_type
	 */
	public int getF_type() {
		return f_type;
	}

	/**
	 * @param f_type the f_type to set
	 */
	public void setF_type(int f_type) {
		this.f_type = f_type;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:/tomcat_8/apache-tomcat-8.0.33/webapps/pwkyps/public/upload/前期项目计划表.xlsx";
		PoiExcel excel = null;
		try {
			excel = new PoiExcel(path);
			for(int sheetIndex=0;sheetIndex<excel.getSheetCount();sheetIndex++){
				String sheetName = excel.getSheetName(sheetIndex);
				System.out.println(sheetIndex+":"+sheetName+":"+excel.getWorkbook().isSheetHidden(sheetIndex));
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取合并单元格的值
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public String getMergedRegionValue(Sheet sheet ,int row , int column){
		int sheetMergeCount = sheet.getNumMergedRegions();
		boolean flag = true;
		for(int i = 0 ; i < sheetMergeCount ; i++){
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if(row >= firstRow && row <= lastRow){
				if(column >= firstColumn && column <= lastColumn){
					flag = false;
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}
		Row fRow = sheet.getRow(row);
		Cell fCell = fRow.getCell(column);
		return getCellValue(fCell);
	}

	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell){
		if(cell == null) return "";
		if(cell.getCellTypeEnum() == CellType.STRING){
			return cell.getStringCellValue();
		}else if(cell.getCellTypeEnum() == CellType.BOOLEAN){
			return String.valueOf(cell.getBooleanCellValue());
		}else if(cell.getCellTypeEnum() == CellType.FORMULA){
			return cell.getCellFormula() ;
		}else if(cell.getCellTypeEnum() == CellType.NUMERIC){
			return String.valueOf(cell.getNumericCellValue());
		}
		return "";
	}
	
	/**  
	* 判断指定的单元格是否是合并单元格  
	* @param sheet   
	* @param row 行下标  
	* @param column 列下标  
	* @return  
	*/  
	private boolean isMergedRegion(Sheet sheet,int row ,int column) {  
		int sheetMergeCount = sheet.getNumMergedRegions();  
		for (int i = 0; i < sheetMergeCount; i++) {  
			CellRangeAddress range = sheet.getMergedRegion(i);  
			int firstColumn = range.getFirstColumn();  
			int lastColumn = range.getLastColumn();  
			int firstRow = range.getFirstRow();  
			int lastRow = range.getLastRow();  
			if(row >= firstRow && row <= lastRow){  
				if(column >= firstColumn && column <= lastColumn){  
					return true;  
				}  
			}  
	  }  
	  return false;  
	} 
	
	
	/**
	 * 获取指定单元格的值  新（有合并单元格判断）
	 * @param sheetIndex
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @author lqs
	 * 2018-07-03
	 */
	public String getCellStringValueNew(int sheetIndex, int rowNum, int cellNum) {
		Sheet sheet = this.getSheet(sheetIndex);
		String str = "";
		if (sheet != null) {
			boolean isMerge = isMergedRegion(sheet, rowNum, cellNum);  
			//判断是否具有合并单元格  
			if(isMerge) {  
				str = getMergedRegionValue(sheet, rowNum,cellNum);  
			}else {
				str = this.getCellStringValue(sheet, rowNum, cellNum);
			}
			
		}
		return str.trim();
	}
}
