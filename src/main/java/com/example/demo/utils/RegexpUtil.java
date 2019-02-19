package com.example.demo.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则过滤
 * 
 * @author LCC
 * @date 2014年11月5日
 */
public class RegexpUtil {   
	// 过滤HTML标签
    final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
    final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签
    final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性
    // 过滤特殊字符
    final static String regxpForBlankCharacters = "[\r\n\t]";
    final static String regxpForSpecialCharacters = "[`\\-~!@#$%^&*()+=|{}':;'\\[\\]<>/~！@#￥%……&*（）——+|{}【】‘；：”“’、]";
    final static String regxpForAllSpecialCharacters = "[`\\-~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    
    private RegexpUtil() { }
    
    /**  
     * 基本功能：替换标记以正常显示   <p>  
     *   
     * @param input  
     * @return String  
     */  
    public static String replaceTag(String input) {
        if (!hasSpecialChars(input)) {   
            return input;   
        }   
        StringBuffer filtered = new StringBuffer(input.length());
        char c;   
        for (int i = 0; i <= input.length() - 1; i++) {   
            c = input.charAt(i);   
            switch (c) {   
            case '<':   
                filtered.append("&lt;");   
                break;   
            case '>':   
                filtered.append("&gt;");   
                break;   
            case '"':   
                filtered.append("&quot;");   
                break;   
            case '&':   
                filtered.append("&amp;");   
                break;   
            default:   
                filtered.append(c);   
            }   
  
        }   
        return (filtered.toString());   
    }   
  
    /**  
     * 基本功能：判断标记是否存在   <p>  
     *   
     * @param input  
     * @return boolean  
     */  
    public static boolean hasSpecialChars(String input) {
        boolean flag = false;   
        if ((input != null) && (input.length() > 0)) {   
            char c;   
            for (int i = 0; i <= input.length() - 1; i++) {   
                c = input.charAt(i);   
                switch (c) {   
                case '>':   
                    flag = true;   
                    break;   
                case '<':   
                    flag = true;   
                    break;   
                case '"':   
                    flag = true;   
                    break;   
                case '&':   
                    flag = true;   
                    break;   
                }   
            }   
        }   
        return flag;   
    }   
  
    /**  
     * 基本功能：过滤所有以"<"开头以">"结尾的标签   <p>  
     *   
     * @param str  
     * @return String  
     */  
    public static String filterHtml(String str) {
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();   
        while (result1) {   
            matcher.appendReplacement(sb, "");   
            result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    }   
  
    /**  
     *   
     * 基本功能：过滤指定标签   <p>  
     *   
     * @param str  
     * @param tag 指定标签  
     * @return String  
     */  
    public static String fiterHtmlTag(String str, String tag) {
        String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
        Pattern pattern = Pattern.compile(regxp);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();   
        while (result1) {   
            matcher.appendReplacement(sb, "");   
            result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    }   
  
    /**  
     *   
     * 基本功能：替换指定的标签   <p>  
     *   
     * @param str  
     * @param beforeTag 要替换的标签  
     * @param tagAttrib 要替换的标签属性值  
     * @param startTag 新标签开始标记  
     * @param endTag  新标签结束标记  
     * @return String  
     * @如：替换img标签的src属性值为[img]属性值[/img]  
     */  
    public static String replaceHtmlTag(String str, String beforeTag,
                                        String tagAttrib, String startTag, String endTag) {
        String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
        String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
        Pattern patternForTag = Pattern.compile(regxpForTag);
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();   
        while (result) {   
            StringBuffer sbreplace = new StringBuffer();
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag
                    .group(1));   
            if (matcherForAttrib.find()) {   
                matcherForAttrib.appendReplacement(sbreplace, startTag   
                        + matcherForAttrib.group(1) + endTag);   
            }   
            matcherForTag.appendReplacement(sb, sbreplace.toString());   
            result = matcherForTag.find();   
        }   
        matcherForTag.appendTail(sb);   
        return sb.toString();   
    }  
    
    /**
     * 清除HTML标签, 文本除了逗号, 句号, 问号以及内容中的空格不被清除外, 其他的全部清除. 
     * 
     * @author LCC
     * @date 2014年11月6日
     * @param v
     * @return
     */
    public static String filterAll(String v){
    	if(StringUtils.isEmpty(v)){return v;}
    	
    	v = RegexpUtil.filterHtml(v);
    	v = RegexpUtil.filterBlank(v);
    	v = RegexpUtil.filterSpecial(v);
    	
    	return v;
    }
    
    /**
     * 清除所有的空白符  \r\n\t
     * 
     * @author LCC
     * @date 2014年11月6日
     * @param v
     * @return
     */
    public static String filterBlank(String v){
    	if(StringUtils.isEmpty(v)){return v;}
    	return v.replaceAll(RegexpUtil.regxpForBlankCharacters, "");
    }
    
    /**
     * 清除特殊字符除了 逗号, 句号, 问号以及内容中的空格
     * 
     * @author LCC
     * @date 2014年11月6日
     * @param v
     * @return
     */
    public static String filterSpecial(String v){
    	if(StringUtils.isEmpty(v)){return v;}
    	return v.replaceAll(RegexpUtil.regxpForSpecialCharacters, "");
    }
    
    /**
	 * 过滤文本内容
	 * 
	 * @author LCC
	 * @date 2014年11月6日
	 * @param v
	 * @param filter
	 * @return
	 */
    public static Object filter(String v, String filter){
		if(StringUtils.isNoneEmpty(filter)){
			String[] arr = filter.split(",");
			for (String s : arr) {
				switch (s.trim()) {
					case "all":
						v = RegexpUtil.filterAll(v);
						break;
					case "html":
						v = RegexpUtil.filterHtml(v);
						break;
					case "blank":
						v = RegexpUtil.filterBlank(v);
						break;
					case "special":
						v = RegexpUtil.filterSpecial(v);
						break;
					default:
						break;
				}
			}
		}
		return v;
	}
	/**
	 * 过滤
	 * 
	 * @author LCC
	 * @date 2014年11月6日
	 * @param vs
	 * @param filter
	 * @return
	 */
    public static Object[] filter(Object[] vs, String filter){
		if(StringUtils.isEmpty(filter)){ return vs; }
		
		Object[] nvs = new Object[vs.length];
		for (int i=0; i< vs.length; i++) {
			nvs[i] = filter(vs[i].toString(), filter);
		}
		return nvs;
	}
}
