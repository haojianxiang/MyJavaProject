package haojianxiang.util;
///**
// *  LanguageRegex.java
// *  描述：  不同语种的计算单词个数的正则表达式
// *
// *  版本:    v1.0
// *  作者:    rockyzheng
// *  日期:    2014-6-26  
// *  版权所有 2005-2014 传神(中国)网络科技有限公司
//*/
//package com.dhgate.htranslate.util;
//
//
//public class LanguageRegex {
//	
//	//英语
//	private static String EnglishRegex = "(?i)[^a-z’\\|\\[\\]\\;\"\\{\\}\\'\\`\\/\\,\\:\\\\\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=0-9]";
//	//俄语
//	private static String RussianRegex="(?iu)[^№|А|Б|В|Г|Д|Е|Ё|Ж|З|И|Й|К|Л|М|Н|О|П|Р|С|Т|У|Ф|Х|Ц|Ч|Ш|Щ|Ъ|Ы|Ь|Э|Ю|Я|»|«|A-Z’»«\\|\\[\\]\\;\"\\{\\}\\'\\`\\/\\,\\:\\\\\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=0-9]";
//	//西语
//	private static String SpanishRegex="(?iu)[^CH|LL|Ñ|á|é|í|ó|ú|º|A-Z’\\|\\[\\]\\;\"\\{\\}\\'\\`\\/\\,\\:\\\\\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=0-9]";
//	//法语
//	private static String FrenchRegex="(?iu)[^à|â|è|é|ê|ë|î|ï|ô|ö|ù|û|ü|ç|œ|æ|«|»|A-Z’\\|\\[\\]\\;\"\\{\\}\\'\\`\\/\\,\\:\\\\\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=0-9]";
//	//葡萄牙语
//	private static String PortugueseRegex="(?iu)[^ç|á|é|í|ó|ú|â|ê|ô|ã|õ|à|A-Z’\\|\\[\\]\\;\"\\{\\}\\'\\`\\/\\,\\:\\\\\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\+\\=0-9]";
//	//中文
//	private static String ChineseRegex = "^[\u4e00-\u9fa5]$";
//	
//	/**
//	 * 
//	 * getRegexByLang:根据语种,获取对应的统计单词个数的正则表达式
//	 *
//	 * @param lang
//	 * @return    参数说明
//	 * String    返回值说明
//	 * @throws 
//	 * @since  　ver 1.0
//	 */
//	public static String getRegexByLang(SupportLang lang){
//		String regex  = "";
//		
//		switch (lang) {
//		case English:
//			regex = EnglishRegex;
//			break;
//		case French:
//			regex = FrenchRegex;
//			break;
//		case Russian:
//			regex = RussianRegex;
//			break;
//		case Spanish:
//			regex = SpanishRegex;
//			break;
//		case Portuguese:
//			regex = PortugueseRegex;
//			break;			
//		default:
//			break;
//		}
//		
//		return regex;
//	}
//	
//	/**
//	 * 
//	 * getRegexByLang:根据语种,获取对应的统计单词个数的正则表达式
//	 *
//	 * @param lang
//	 * @return    参数说明
//	 * String    返回值说明
//	 * @throws 
//	 * @since  　ver 1.0
//	 */
//	public static String getRegexByLang(String lang){
//		//"en","fr","ru","es","pt"
//		String regex  = "";
//		
//		if (lang == null || lang.isEmpty()){
//			return regex;
//		}
//		lang = lang.toLowerCase().trim();
//		if (lang.equals("en")){
//			regex = EnglishRegex;
//		}else if (lang.equals("fr")){
//			regex = FrenchRegex;
//		}else if (lang.equals("ru")){
//			regex = RussianRegex;
//		}else if (lang.equals("es")){
//			regex = SpanishRegex;
//		}else if (lang.equals("pt")){
//			regex = PortugueseRegex;
//		}else if(lang.equals("zh")){
//			regex = ChineseRegex;
//		}else{
//			regex = "";
//		}
//		
//		return regex;
//	}
//	
//	
//}
