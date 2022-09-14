package com.msb.dongbao.common.base.response;

import com.msb.dongbao.common.base.enums.StateCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xcy
 * @date 2022/9/13 - 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseResult<T> {

	/**
	 * 状态码
	 */
	private int code;
	/**
	 * 提示信息
	 */
	private String message;
	/**
	 * 响应数据
	 */
	private T data;

	public static <T> ResponseResult success() {
		//@Accessors(chain = ture)
		//return new ResponseResult().setCode(StateCodeEnum.SUCCESS.getCode()).setMessage(StateCodeEnum.SUCCESS.getMessage());

		//@Builder
		return ResponseResult.builder()
				.code(StateCodeEnum.SUCCESS.getCode())
				.message(StateCodeEnum.SUCCESS.getMessage())
				.build();
	}

	/**
	 * 成功响应的方法
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseResult success(T data) {
		//@Accessors(chain = ture)
		/*return new ResponseResult().setCode(StateCodeEnum.SUCCESS.getCode())
				.setMessage(StateCodeEnum.SUCCESS.getMessage())
				.setData(data);*/
		//@Builder
		return ResponseResult.builder().code(StateCodeEnum.SUCCESS.getCode())
				.message(StateCodeEnum.SUCCESS.getMessage())
				.data(data)
				.build();
	}

	/**
	 * 失败，统一的失败处理
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseResult fail(T data) {
		//@Accessors(chain = ture)
		//return new ResponseResult().setData(data);
		//@Builder
		return ResponseResult.builder()
				.data(data)
				.build();
	}
	/**
	 * 失败，自定义失败，错误码和提示信息
	 * @param code
	 * @param message
	 * @return
	 */
	public static ResponseResult fail(int code, String message) {
		//@Accessors(chain = ture)
		//return new ResponseResult().setCode(code).setMessage(message);
		//@Builder
		return ResponseResult.builder()
				.code(code)
				.message(message)
				.build();
	}
	/**
	 * 失败，自定义失败，错误码和提示信息，以及错误数据
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public static ResponseResult fail(int code, String message, String data) {
		//@Accessors(chain = ture)
		//return new ResponseResult().setCode(code).setMessage(message).setData(data);

		//@Builder
		return ResponseResult.builder()
				.code(code)
				.message(message)
				.data(data)
				.build();
	}

}
