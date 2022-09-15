package com.msb.dongbao.portal.web.advice;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import com.msb.dongbao.common.base.exception.TokenException;
import com.msb.dongbao.common.base.response.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

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
		return ResponseResult.fail(301, "算术异常处理");
	}

	/**
	 * 登录异常的处理
	 *
	 * @param e 异常
	 * @return 统一异常处理
	 */
	@ExceptionHandler(TokenException.class)
	public ResponseResult tokenException(Exception e) {
		return ResponseResult.fail(StateCodeEnum.FAIL.getCode(), e.getMessage());
	}
}
