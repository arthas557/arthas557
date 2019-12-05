package com.arthas557.common.exception;

/**
 * 业务异常对象。
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5151669876695986575L;

	public static final String SYS_ERROR_MSG = "系统异常";


    /**
     * 简单处理系统异常与业务异常的异常信息
     */
	public static String convertExceptionToMsg(Exception e){
		if(e instanceof BusinessException){
			return e.getMessage();
		}else {
			return SYS_ERROR_MSG;
		}
	}
	/**
	 * 简单处理Throwable与业务异常的异常信息
	 */
	public static String convertExceptionToMsg(Throwable throwable){
		if(throwable instanceof Exception){
			return convertExceptionToMsg((Exception)throwable);
		}else {
			return SYS_ERROR_MSG;
		}
	}


	public BusinessException() {
		super();
	}
    public BusinessException(String format, Object... args) {
        super(String.format(format, args));
    }
	public static BusinessException getInstance( String format, Object... args){
		return new BusinessException(String.format(format, args));
	}
	public static BusinessException getInstance( String errorMsg){
		return new BusinessException(errorMsg);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}



}
