package com.msb.dongbao.portal.web.verifycode;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.Objects;

/**
 * @author xcy
 * @date 2022/9/16 - 8:59
 */
public class TesseractTest {

	public static void main(String[] args) {
		ITesseract iTesseract = new Tesseract();

		//语言包，必须是非中文路径
		iTesseract.setDatapath("G:\\tesseract\\tesseract");

		//设置中文读取
		//iTesseract.setLanguage("chi_sim");
		//设置英文读取
		iTesseract.setLanguage("eng");

		File fileDir = new File("G:\\tesseract\\data");
		try {
			for (File file : Objects.requireNonNull(fileDir.listFiles())) {
				String verifyCode = iTesseract.doOCR(file);
				System.out.println("识别后的验证码：" + verifyCode);
			}
		} catch (TesseractException e) {
			e.printStackTrace();
		}
	}
}
