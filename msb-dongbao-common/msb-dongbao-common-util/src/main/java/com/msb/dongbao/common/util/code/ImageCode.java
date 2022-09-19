package com.msb.dongbao.common.util.code;


import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * @author xcy
 * @date 2022/9/15 - 16:45
 */
@Data
public class ImageCode {
	/**
	 * 图片中的内容
	 */
	private String code;
	/**
	 * 图片
	 */
	private ByteArrayInputStream image;


	private int width = 400;

	private int height = 100;

	public static ImageCode getInstance() {
		return new ImageCode();
	}

	private ImageCode() {
		//定义一个黑板，使用RGB
		//BufferedImage子类描述了一个 {@link java.awt.Image Image} 具有可访问的图像数据缓冲区
		//TYPE_INT_RGB表示包含 8 位 RGB 颜色分量的图像，这些分量被打包到整数像素中
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		//给定一个画笔
		Graphics graphics = image.getGraphics();

		//画笔的颜色
		graphics.setColor(new Color(17, 234, 37));

		//画出矩形
		graphics.fillRect(0, 0, width, height);

		//字体，选择字体以及字体类型和字体大小
		graphics.setFont(new Font("宋体", Font.BOLD, 30));

		Random random = new Random();

		this.code = "";

		//生成6位数字验证码
		/*for (int i = 0; i < 6; i++) {
			String s = String.valueOf(random.nextInt(10));
			code += s;

			//图形中字体的颜色
			graphics.setColor(new Color(0, 0, 0));
			//将字体画出来
			graphics.drawString(s, (width / 6) * i + 5, 60);


			//画线
			graphics.setColor(new Color(100, 100, 100));
			//将线画出来
			graphics.drawLine((width / 6) * i, 40, (width / 6) * i + 25, 60);
		}*/

		//生成表达式
		int num1 = random.nextInt(20);
		int num2 = random.nextInt(20);
		String symbol = "";
		double ran = Math.random();
		if (ran > 0.25) {
			symbol = "+";
		} else if (ran > 0.5) {
			symbol = "-";
		} else if (ran > 0.75) {
			symbol = "*";
		} else {
			symbol = "/";
		}
		graphics.setColor(new Color(100, 0, 200));

		graphics.drawString(num1 + "", 2, 60);
		graphics.drawString(symbol, (width / 6) + 2, 60);
		graphics.drawString(num2 + "", (width / 6) * 2 + 2, 60);
		graphics.drawString("=", (width / 6) * 3 + 2, 60);
		graphics.drawString("?", (width / 6) * 4 + 2, 60);

		int result = 0;
		switch (symbol) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			case "*":
				result = num1 * num2;
				break;
			case "/":
				result = num1 / num2;
				break;
		}
		this.code = String.valueOf(result);

		//画出100条干扰线条
		graphics.setColor(new Color(100, 100, 100));
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			int x1 = random.nextInt(20);
			int y1 = random.nextInt(20);

			graphics.drawLine(x, y, x + x1, y + y1);
		}

		//画笔收起来，处理此图形上下文并释放它正在使用的任何系统资源
		graphics.dispose();

		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			//赋值给 ByteArrayInputStream image
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
			ImageIO.write(image, "jpeg", imageOutputStream);

			inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		this.image = inputStream;
	}
}
