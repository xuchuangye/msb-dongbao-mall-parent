package com.msb.dongbao.portal.web.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.exception.TokenException;
import com.msb.dongbao.common.base.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理类
 * <p>
 * 注意事项：
 * 1.404错误：把JSON当做页面去返回就会出现404错误
 * 2.不能返回页面，所以需要标注@RestController或者@ResponseBody，@RestController包含@ResponseBody
 * 3.@RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * 4.@RestControllerAdvice的优先级比@ControllerAdvice的优先级高，
 * 所以在标注@RestControllerAdvice的类中的方法标注@ExceptionHandler时，尽量声明具体的异常类
 *
 * @author xcy
 * @date 2022/9/14 - 9:09
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 算术异常的处理
	 *
	 * @return 统一异常处理
	 */
	@ExceptionHandler(ArithmeticException.class)
	public ResponseResult arithmeticException() {
		return ResponseResult.fail(StateCodeEnum.ARITHMETIC_EXCEPTION.getCode(), StateCodeEnum.ARITHMETIC_EXCEPTION.getMessage());
	}

	/**
	 * 自定义的登录异常处理
	 *
	 * @return 统一异常处理
	 */
	@ExceptionHandler(TokenException.class)
	public ResponseResult tokenException() {
		return ResponseResult.fail(StateCodeEnum.TOKEN_EXCEPTION.getCode(), StateCodeEnum.TOKEN_EXCEPTION.getMessage());
	}

	/**
	 * KaptchaException
	 *
	 * @return
	 */
	@ExceptionHandler(KaptchaException.class)
	public ResponseResult kaptchaException(KaptchaException e) {
		//未找到kaptcha异常
		if (e instanceof KaptchaNotFoundException) {
			return ResponseResult.fail(StateCodeEnum.KAPTCHA_NOTFOUND_EXCEPTION.getCode(), StateCodeEnum.KAPTCHA_NOTFOUND_EXCEPTION.getMessage());
		}
		//kaptcha不正确异常
		else if (e instanceof KaptchaIncorrectException) {
			return ResponseResult.fail(StateCodeEnum.KAPTCHA_INCORRECT_EXCEPTION.getCode(), StateCodeEnum.KAPTCHA_INCORRECT_EXCEPTION.getMessage());
		}
		//kaptcha超时异常
		else if (e instanceof KaptchaTimeoutException) {
			return ResponseResult.fail(StateCodeEnum.KAPTCHA_TIMEOUT_EXCEPTION.getCode(), StateCodeEnum.KAPTCHA_INCORRECT_EXCEPTION.getMessage());
		}
		//kaptcha渲染异常
		else {
			return ResponseResult.fail(StateCodeEnum.KAPTCHA_RENDER_EXCEPTION.getCode(), StateCodeEnum.KAPTCHA_INCORRECT_EXCEPTION.getMessage());
		}
	}
}
