package com.fxdigital.utils;

import java.io.Serializable;

/**
 * 返回vo对象
 */
public class ResultVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String message;
	private Object result;

	private final static String CODE_OK = "200";
	private final static String CODE_ERROR = "1";
	private final static String MESSAGE_OK = "操作成功！";
	private final static String MESSAGE_ERROR = "数据请求失败，请稍后再试！";

	public ResultVo(String code, String message, Object result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	public ResultVo() {
	}

	/**
	 * 通用失败返回操作
	 * 
	 * @param result
	 *            返回数据 可为null
	 * @return
	 */
	public static ResultVo getErrorResult(Object result) {
		return new ResultVo(ResultVo.CODE_ERROR, ResultVo.MESSAGE_ERROR, result);
	}

	public static ResultVo getErrorResult(String message) {
		return new ResultVo(ResultVo.CODE_ERROR, message, null);
	}

	public static ResultVo getErrorResult() {
		return new ResultVo(ResultVo.CODE_ERROR, ResultVo.MESSAGE_ERROR, null);
	}

	public static ResultVo getErrorResult(String code, String message) {
		return new ResultVo(code, message, null);
	}

	public static ResultVo getErrorResult(String code, String message, Object result) {
		return new ResultVo(code, message, result);
	}

	/**
	 * 通用成功返回操作
	 * 
	 * @param result
	 *            返回数据 可为null
	 * @return
	 */
	public static ResultVo getSuccResult(Object result) {
		return new ResultVo(ResultVo.CODE_OK, ResultVo.MESSAGE_OK, result);
	}

	public static ResultVo getSuccResult(String message) {
		return new ResultVo(ResultVo.CODE_OK, message, null);
	}

	public static ResultVo getSuccResult(String message, Object result) {
		return new ResultVo(ResultVo.CODE_OK, message, result);
	}

	public static ResultVo getSuccResult() {
		return new ResultVo(ResultVo.CODE_OK, ResultVo.MESSAGE_OK, null);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ResultVo{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", result=" + result +
				'}';
	}
}
