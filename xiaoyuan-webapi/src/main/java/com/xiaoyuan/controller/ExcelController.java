package com.xiaoyuan.controller;


import com.xiaoyuan.tools.ExcelUtil2;
import com.xiaoyuan.tools.MessageBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Excel")
public class ExcelController {

    @RequestMapping("/export")
    public MessageBean export(
            HttpServletResponse response,
            @RequestParam(defaultValue = ",") String split,
            String header,
            String data,
            @RequestParam(defaultValue = "文件名.xls") String fileName,
            @RequestParam(defaultValue = "表格名") String sheetName
    ) throws IOException {
        String[] header_arr = header.split(",");
        String[] data_arr = data.split(split);
        //行数
        int rows = data_arr.length / header_arr.length;
        //列数
        int cols = header_arr.length;
        //下标
        int k = 0;
        //前端传字符串，后端分隔符，然后根据header的长度使用data数组赋值到每行遍历
        List<List<String>> excelData = new ArrayList<>();
        List<String> head = new ArrayList<>();
        //用一个容器完成数据内容。。head就不要了。。
        List<String> data_bottle = new ArrayList<>();
        for (int i = 0; i < header_arr.length; i++) {
            head.add(header_arr[i]);
        }
        //表头先放。。。
        excelData.add(head);
        //每次遍历放入。。就不会到最后一次放的问题。。
        //多少行
//todo =========================================
        //每行
        for (int i = 0; i < rows; i++) {
            data_bottle = new ArrayList<>();
            //每列
            for (int j = 0; j < cols; j++) {
//                data_bottle.add(data_arr[i*j+j]);
                data_bottle.add(data_arr[k]);
                k++;
            }
            excelData.add(data_bottle);
            //清理放下一行
//            data_bottle.clear();
        }

        ExcelUtil2.exportExcel(response, excelData, sheetName, fileName, 15);
        return new MessageBean(10001, "", header);
    }


    /**
     * Excel表格导出接口
     * http://localhost:8080/ExcelDownload
     *
     * @param response response对象
     * @throws IOException 抛IO异常
     */
//    @RequestMapping("/export")
//    public MessageBean export(
//            HttpServletResponse response,
//            ArrayList<String> header,
//            ArrayList<ArrayList<String>> data,
//            @RequestParam(defaultValue = "文件名.xls") String fileName,
//            @RequestParam(defaultValue = "表格名") String sheetName
//    ) throws IOException {
//        List<List<String>> excelData = new ArrayList<>();
//        List<String> head = new ArrayList<>();
//        //用一个容器完成数据内容。。head就不要了。。
//        List<String> data_bottle = new ArrayList<>();
//        for (int i = 0; i < header.size(); i++) {
//            head.add(header.get(i));
//        }
//        //表头先放。。。
//        excelData.add(head);
//        //每次遍历放入。。就不会到最后一次放的问题。。
//        //多少行
//        for (int i = 0; i < data.size(); i++) {
//            //遍历每行的列。。
//            for (int j = 0; j < data.get(i).size(); j++) {
//                data_bottle.add(data.get(i).get(j));
//            }
//            //每行放入后清空，再装。。
//            excelData.add(data_bottle);
//            data_bottle.clear();//xixi
//        }
//        ExcelUtil2.exportExcel(response, excelData, sheetName, fileName, 15);
//        return new MessageBean(10001, "", header);
//    }

//    @RequestMapping("/export2")
//    public MessageBean export2(
//            HttpServletResponse response,
//            String[] header,
//            String[][] data,
//            @RequestParam(defaultValue = "文件名.xls") String fileName,
//            @RequestParam(defaultValue = "表格名") String sheetName
//    ) throws IOException {
//        List<List<String>> excelData = new ArrayList<>();
//        List<String> head = new ArrayList<>();
//        //用一个容器完成数据内容。。head就不要了。。
//        List<String> data_bottle = new ArrayList<>();
//        for (int i = 0; i < header.length; i++) {
//            head.add(header[i]);
//        }
//        //表头先放。。。
//        excelData.add(head);
//        //每次遍历放入。。就不会到最后一次放的问题。。
//        //多少行
//        for (int i = 0; i < data.length; i++) {
//            //遍历每行的列。。
//            for (int j = 0; j < data[i].length; j++) {
//                data_bottle.add(data[i][j]);
//            }
//            //每行放入后清空，再装。。
//            excelData.add(data_bottle);
//            data_bottle.clear();//xixi
//        }
//        ExcelUtil2.exportExcel(response, excelData, sheetName, fileName, 15);
//        return new MessageBean(10001, "", header);
//    }


    //TODO=============================================


    /**
     * Excel表格导出接口
     * http://localhost:8080/ExcelDownload
     *
     * @param response response对象
     * @throws IOException 抛IO异常
     */
//    @RequestMapping("/export1")
//    public MessageBean excelDownload(
//            HttpServletResponse response,
//            String[] header,
//            String[][] data,
//            String fileName
//    ) throws IOException {
//        if (header == null) {
//            return new MessageBean(10000, "表头为空", header);
//        }
//        //表头数据
////        header = {"ID", "姓名", "性别", "年龄", "地址", "分数"};
//
//        //数据内容
//        String[] student1 = {"1", "小红", "女", "23", "成都青羊区", "96"};
//        String[] student2 = {"2", "小强", "男", "26", "成都金牛区", "91"};
//        String[] student3 = {"3", "小明", "男", "28", "成都武侯区", "90"};
//
////        data = {{"1", "小红", "女", "23", "成都青羊区", "96"}
////                , {"2", "小强", "男", "26", "成都金牛区", "91"}
////                , {"3", "小明", "男", "28", "成都武侯区", "90"}};
//        //声明一个工作簿
//        HSSFWorkbook workbook = new HSSFWorkbook();
//
//        //生成一个表格，设置表格名称为"学生表"
//        HSSFSheet sheet = workbook.createSheet("学生表");
//
//        //设置表格列宽度为10个字节
//        sheet.setDefaultColumnWidth(10);
//
//        //创建第一行表头
//        HSSFRow headrow = sheet.createRow(0);
//
//        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
//        for (int i = 0; i < header.length; i++) {
//            //创建一个单元格
//            HSSFCell cell = headrow.createCell(i);
//
//            //创建一个内容对象
//            HSSFRichTextString text = new HSSFRichTextString(header[i]);
//
//            //将内容对象的文字内容写入到单元格中
//            cell.setCellValue(text);
//        }
//
//        //模拟遍历结果集，把内容加入表格
//        //模拟遍历第一个学生
//        HSSFRow row1 = sheet.createRow(1);
//        for (int i = 0; i < student1.length; i++) {
//            HSSFCell cell = row1.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student1[i]);
//            cell.setCellValue(text);
//        }
//
//        //模拟遍历第二个学生
//        HSSFRow row2 = sheet.createRow(2);
//        for (int i = 0; i < student2.length; i++) {
//            HSSFCell cell = row2.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student2[i]);
//            cell.setCellValue(text);
//        }
//
//        //模拟遍历第三个学生
//        HSSFRow row3 = sheet.createRow(3);
//        for (int i = 0; i < student3.length; i++) {
//            HSSFCell cell = row3.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student3[i]);
//            cell.setCellValue(text);
//        }
//
//        //准备将Excel的输出流通过response输出到页面下载
//        //八进制输出流
//        response.setContentType("application/octet-stream");
//
//        //这后面可以设置导出Excel的名称，此例中名为student.xls
//        response.setHeader("Content-disposition", "attachment;filename=student.xls");
//
//        //刷新缓冲
//        response.flushBuffer();
//
//        //workbook将Excel写入到response的输出流中，供页面下载
//        workbook.write(response.getOutputStream());
//        return new MessageBean(10001, "", header);
//    }
//
//
//    /**
//     * Excel表格导出接口
//     * http://localhost:8080/ExcelDownload
//     *
//     * @param response response对象
//     * @throws IOException 抛IO异常
//     */
//    @RequestMapping("/export2")
//    public void excelDownload2(
//            HttpServletResponse response,
//            String[] header,
//            String[][] data,
//            String fileName,
//            String sheetName
//    ) throws IOException {
//
//        List<List<String>> excelData = new ArrayList<>();
//        List<String> head = new ArrayList<>();
//        List<String> data_bottle = new ArrayList<>();//用一个容器完成数据内容。。head就不要了。。
//        for (int i = 0; i < header.length; i++) {
//            head.add(header[i]);
//        }
//        excelData.add(head);//表头先放。。。
//        //每次遍历放入。。就不会到最后一次放的问题。。
//        for (int i = 0; i < data.length; i++) {//多少行
//            for (int j = 0; j < data[i].length; j++) {//遍历每行的列。。
//                data_bottle.add(data[i][j]);
//            }
//            //每行放入后清空，再装。。
//            excelData.add(data_bottle);
//            data_bottle.clear();//xixi
//        }
////        head.add("第一列");
////        head.add("第二列");
////        head.add("第三列");
////
////        List<String> data1 = new ArrayList<>();
////        data1.add("123");
////        data1.add("234");
////        data1.add("345");
////
////        List<String> data2 = new ArrayList<>();
////        data2.add("abc");
////        data2.add("bcd");
////        data2.add("cde");
////
////        excelData.add(head);
////        excelData.add(data1);
////        excelData.add(data2);
//
////        String sheetName = "测试";
////        String fileName = "ExcelTest.xls";
//
//        ExcelUtil2.exportExcel(response, excelData, sheetName, fileName, 15);
//    }
}




