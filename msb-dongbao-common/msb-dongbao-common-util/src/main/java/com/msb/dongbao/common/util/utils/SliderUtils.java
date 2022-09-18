package com.msb.dongbao.common.util.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.msb.dongbao.common.base.entity.VerificationVO;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author xcy
 * @date 2022/9/18 - 15:47
 */
public class SliderUtils {
	public static VerificationVO cut() {
		// 读取图库目录
		File imgCatalog = new File("D:\\图片\\Saved Pictures");
		ArrayList<File> files = CollectionUtil.newArrayList(imgCatalog.listFiles());
		if (CollectionUtil.isNotEmpty(files)) {
			// 随机选择需要切的图
			int randNum = new Random().nextInt(files.size());
			File targetFile = files.get(randNum);

			// 随机选择剪切模版
			File tempImgFile = new File("D:\\图片\\Camera Roll\\" + (new Random().nextInt(12) + 1) + "-w.jpg");
			try {
				VerificationVO verificationVO = VerifyImageUtils.pictureTemplatesCut(tempImgFile, targetFile);
				System.out.println("宽：" + verificationVO.getXWidth());
//				redisUtils.set(AuthConstant.PREFIX_AUTH_VC_CODE_CACHE + account, verificationVO.getXWidth(), 1800);

//				// 移除横坐标送前端
//				verificationVO.setXWidth(null);

				return verificationVO;
			} catch (Exception e) {
				System.out.println("切图失败");
			}
		}

		return null;
	}
}
