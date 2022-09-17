package com.msb.dongbao.common.util.utils;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

import java.awt.*;

/**
 * 单例模式之内部类的实现方式
 * @author xcy
 * @date 2022/9/16 - 17:11
 */
public class JCaptchaUtils {
	/**
	 * 内部类
	 */
	private static class InnerClass {
		private static final ImageCaptchaService SERVICE = imageCaptchaService();
	}

	public static ImageCaptchaService getService() {
		return InnerClass.SERVICE;
	}

	private static ImageCaptchaService imageCaptchaService() {
		final int width = 250;
		final int height = 100;
		//背景：单色背景生成器
		BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(width, height);
		//字
		int[] ints = {0, 255};
		//随机范围颜色生成器
		RandomRangeColorGenerator randomRangeColorGenerator = new RandomRangeColorGenerator(ints, ints, ints);
		//随机文本贴纸
		//minAcceptedWordLength：最小接受字长
		//maxAcceptedWordLength：最大接受字长
		//colorGenerator：颜色生成器
		RandomTextPaster randomTextPaster = new RandomTextPaster(4, 6, randomRangeColorGenerator);

		//字体的最小值
		final int min = 25;
		//字体的最大值
		final int max = 30;

		Font[] fonts = new Font[] {new Font("Simsun", Font.PLAIN, 20)};
		//字体
		RandomFontGenerator randomFontGenerator = new RandomFontGenerator(min, max, fonts);


		//组装图像
		//组合词到图像
		ComposedWordToImage composedWordToImage = new ComposedWordToImage(randomFontGenerator, backgroundGenerator, randomTextPaster);
		//撰写字典单词生成器
		ComposeDictionaryWordGenerator composeDictionaryWordGenerator = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));

		GimpyFactory gimpyFactory = new GimpyFactory(composeDictionaryWordGenerator, composedWordToImage);

		//通用验证引擎
		GenericCaptchaEngine genericCaptchaEngine = new GenericCaptchaEngine(new CaptchaFactory[]{gimpyFactory});

		//minGuarantedStorageDelayInSeconds：保证存储延迟（以秒为单位）
		//maxCaptchaStoreSize：最大验证码存储大小
		//captchaStoreLoadBeforeGarbageCollection：垃圾回收前的验证码存储加载
		return new GenericManageableCaptchaService(genericCaptchaEngine, 20, 2000, 2000);
	}
}
