package com.qsd.framework.html.tags;

/**
 * HTML IMAGE
 * 
 * @author suntongwei
 */
public class ImageTag extends Tag {

	public static final String TAG = "Image";
	
	/**
	 * 图片路径源
	 */
	private String src;
	/**
	 * 宽度
	 */
	private String width;
	/**
	 * 高度
	 */
	private String height;
	/**
	 * 目标
	 */
	private String target;

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String getTagName() {
		return TAG;
	}
}
