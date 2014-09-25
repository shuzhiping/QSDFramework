package com.qsd.framework.spring.mvc.response;


/**
 * 消息返回
 * 
 * @author suntongwei
 */
public class BaseResponse implements java.io.Serializable {

	private static final long serialVersionUID = 8697094203949287497L;
	
	private Integer code;
	private String codeMsg;
	private Object result;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCodeMsg() {
		return codeMsg;
	}
	public void setCodeMsg(String codeMsg) {
		this.codeMsg = codeMsg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
