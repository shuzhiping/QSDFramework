package com.qsd.framework.spring.mvc.response;

/**
 * 
 * 
 * @author suntongwei
 */
public class ExtJsRespone extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6791604576800058177L;

	private Boolean success;
	
	private String msg;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
