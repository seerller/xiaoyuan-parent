package com.xiaoyuan.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class PoiWord {
	public String f_hard_path = null;
	public HWPFDocument document = null;
	public WordExtractor word = null;
	public int f_type = -1;

	public PoiWord() {
		this(2003);
	}
	
	public PoiWord(int f_type) {
		this.setF_type(f_type);
	}

	/**
	 * 根据路径打开WORD文档
	 * @param f_hard_path 文件路径
	 * @throws InvalidFormatException
	 */
	public PoiWord(String f_hard_path) throws InvalidFormatException {
		this.setF_hard_path(f_hard_path);
		this.openWord();
	}
	
	public void openWord() throws InvalidFormatException {
		openWord(this.getF_hard_path());
	}
	
	public void openWord(String f_hard_path) {
		try {
			this.setF_hard_path(f_hard_path);
			InputStream in1 = new FileInputStream(this.getF_hard_path());
			InputStream in2 = new FileInputStream(this.getF_hard_path());
			this.setDocument(new HWPFDocument(in1));
			this.setWord(new WordExtractor(in2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭
	 */
	public void close(){
	}
	
	/**
	 * 获取word文档中的内容（不带样式）
	 * @return
	 */
	public String getText(){
		return this.getWord().getText();
	}
	
	/**
	 * 获取word各个段落的内容
	 * @return
	 */
	public List<String> getParagraphText(){
		return Arrays.asList(this.getWord().getParagraphText());
	}
	
	/**
	 * 获取word文档中的全部图片
	 * @return
	 */
	public List<Picture> getAllPictures(){
		return this.getDocument().getPicturesTable().getAllPictures();
	}
	
	/**
	 * 保存图片到磁盘上
	 * @param pic 图片对象
	 * @param savePath 保存路径
	 */
	public boolean writeImageContent(Picture pic,String savePath){
		boolean isSuccess = true;
		OutputStream os = null;
		try {
			os = new FileOutputStream(savePath);
			pic.writeImageContent(os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		} 
		finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				os = null;
			}
		}
		return isSuccess;
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
	 * @return the f_hard_path
	 */
	public String getF_hard_path() {
		return f_hard_path;
	}

	/**
	 * @param f_hard_path the f_hard_path to set
	 */
	public void setF_hard_path(String f_hard_path) {
		this.f_hard_path = f_hard_path;
	}

	/**
	 * @return the document
	 */
	public HWPFDocument getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(HWPFDocument document) {
		this.document = document;
	}

	/**
	 * @return the word
	 */
	public WordExtractor getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(WordExtractor word) {
		this.word = word;
	}
}
