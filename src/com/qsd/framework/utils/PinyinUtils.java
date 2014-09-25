package com.qsd.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;

/**
 * 中文和拼音的转换
 * 
 * @author suntongwei
 */
public final class PinyinUtils {

	// 输出设置
	private static final HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
	/**
	 * 为一些固定词组，多音字，进行固定拼音转换
	 */
	private static final Properties StaticWordDic = new Properties();
	
	static {

		/**
		 * 方法参数HanyuPinyinToneType有以下常量对象： HanyuPinyinToneType.WITH_TONE_NUMBER
		 * 用数字表示声调，例如：liu2 HanyuPinyinToneType.WITHOUT_TONE 无声调表示，例如：liu
		 * HanyuPinyinToneType.WITH_TONE_MARK 用声调符号表示，例如：liú
		 */
		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		/**
		 * 方法参数HanyuPinyinVCharType有以下常量对象：
		 * HanyuPinyinVCharType.WITH_U_AND_COLON 以U和一个冒号表示该拼音，例如：lu:
		 * HanyuPinyinVCharType.WITH_V 以V表示该字符，例如：lv
		 * HanyuPinyinVCharType.WITH_U_UNICODE 以ü表示
		 * 
		 */
		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

		/**
		 * HanyuPinyinCaseType.LOWERCASE 转换后以全小写方式输出
		 * HanyuPinyinCaseType.UPPERCASE 转换后以全大写方式输出
		 * 
		 */
		outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		
		/**
		 * 读取固定拼音转换词库配置文件
		 */
		InputStream is = null;
		try {
			is = PinyinUtils.class.getResourceAsStream("worddic.properties");
			StaticWordDic.load(is);
		} catch (IOException e) {
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 针对需要转换成拼音的字符串
	 * 首先进行分词，然后针对分词后的结果，进行拼音转换
	 * 转换后的结果，作为关键词，提供搜索
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String toPinyin(String str) {
		StringReader sReader = null;
		Set<String> resultSet = new HashSet<String>();
		try {
			// 读入所需分词的字符串
			sReader = new StringReader(str);
			Configuration cfg = new Configuration() {
				private Configuration dConfig = DefaultConfig.getInstance();
				public List<String> getExtDictionarys() {
					// 增加个人词库
					List<String> ext = dConfig.getExtDictionarys();
					ext.add("com/qsd/framework/utils/ext.dic");
					return ext;
				}
				public List<String> getExtStopWordDictionarys() {
					return dConfig.getExtStopWordDictionarys();
				}
				public String getMainDictionary() {
					return dConfig.getMainDictionary();
				}
				public String getQuantifierDicionary() {
					return dConfig.getQuantifierDicionary();
				}
				public void setUseSmart(boolean arg0) {
				}
				public boolean useSmart() {
					return true;
				}
			};
			Dictionary.initial(cfg);
			// 初始化IKSegmenter
			IKSegmenter ik = new IKSegmenter(sReader, cfg);
			Lexeme lex = null;
			while ((lex = ik.next()) != null) {
				// 得到分词结果，进行转换
				String ikStr = lex.getLexemeText();
				char[] chars = ikStr.toCharArray();
				// 进行预过滤
				// 针对有些固定词语，转换固定拼音
				if(StaticWordDic.containsKey(ikStr)) {
					resultSet.add(StaticWordDic.get(ikStr).toString());
				} else {
					int curCount = 0, totalCount = 0;
					do {
						StringBuffer sBuffer = new StringBuffer();
						for(char c : chars) {
							String[] pinyinStr = PinyinHelper.toHanyuPinyinStringArray(c, outputFormat);
							if(null == pinyinStr) {
								sBuffer.append(c);
							} else {
								if(pinyinStr.length == 1) {
									sBuffer.append(pinyinStr[0]);
								} else {
									sBuffer.append(pinyinStr[curCount >= pinyinStr.length ? 0 : curCount]);
									if(pinyinStr.length > totalCount) {
										totalCount = pinyinStr.length;
									}
								}
							}
						}
						resultSet.add(sBuffer.toString());
					} while(curCount++ < totalCount - 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		} finally {
			if(sReader != null) {
				sReader.close();
			}
		}
		
		// 进行字符串转化，以逗号分隔
		if(resultSet.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for(String s : resultSet) {
				sb.append(s + ",");
			}
			sb.delete(sb.length() - 1, sb.length());
			return sb.toString();
		}
		return "";
	}

}
