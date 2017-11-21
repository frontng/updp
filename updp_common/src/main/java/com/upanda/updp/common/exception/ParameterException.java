package com.upanda.updp.common.exception;

/**
 * 入参异常（非法参数）
 * @author Front Ng
 * @date 2017年2月22日
 */
public class ParameterException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParameterException() {
		super();
	}

	public ParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}
}
