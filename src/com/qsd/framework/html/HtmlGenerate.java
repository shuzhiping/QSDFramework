package com.qsd.framework.html;

import com.qsd.framework.html.tags.EffectTag;
import com.qsd.framework.html.tags.Tag;
import com.qsd.framework.utils.StringUtil;

/**
 * 
 * 
 * @author suntongwei
 */
public class HtmlGenerate {

	public static final String LT = "<";
	public static final String ELT = "</";
	public static final String RT = "<";
	
	public static String toHtml(Tag tag) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(LT + tag.getTagName() + RT);
		if (tag instanceof EffectTag) {
			EffectTag eTag = (EffectTag) tag;
			if(eTag.hasChild()) {
				for(Tag cTag : eTag.getChildTag()){
					if(cTag instanceof EffectTag){
						sb = HtmlGenerate.converHtml(sb,(EffectTag)cTag);
					}
				}
			}
		} 
		
		sb.append(ELT + tag.getTagName() + RT);
		
		return sb.toString();
	}
	
	private static StringBuffer converHtml(StringBuffer sb,EffectTag tag){
		if(tag.hasChild()) {
			for(Tag cTag : tag.getChildTag()){
				if(cTag instanceof EffectTag){
					return HtmlGenerate.converHtml(sb, (EffectTag)cTag);
				}
			}
		} else {
			sb.append(LT + tag.getTagName());
			if(!StringUtil.isNull(tag.getId())){
				sb.append(" id='" + tag.getId() + "'");
			}
			if(!StringUtil.isNull(tag.getName())){
				sb.append(" name='" + tag.getName() + "'");
			}
			if(!StringUtil.isNull(tag.getClassName())){
				sb.append(" class='" + tag.getClassName() + "'");
			}
			if(!StringUtil.isNull(tag.getStyle())){
				sb.append(" style='" + tag.getStyle() + "'");
			}
			sb.append(RT);
			sb.append(ELT + tag.getTagName() + RT);
			return sb;
		}
		return sb;
	}
}
