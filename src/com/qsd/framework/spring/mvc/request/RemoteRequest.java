package com.qsd.framework.spring.mvc.request;

/**
 * 远程参数接口
 * 
 * @author suntongwei
 */
public class RemoteRequest implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1217281159273131537L;
	
	/**
	 * 手机imei号
	 */
	private String imei;
	/**
	 * 手机类型
	 */
	private Integer phoneType;
	/**
	 * APP版本号
	 */
	private String appVer;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Integer getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(Integer phoneType) {
		this.phoneType = phoneType;
	}
	public String getAppVer() {
		return appVer;
	}
	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

}
