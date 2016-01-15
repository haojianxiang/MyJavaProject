///**
// *  WordCount.java
// *  描述：  
// *
// *  版本:    v1.0
// *  作者:    rockyzheng
// *  日期:    2014-6-26  
// *  版权所有 2005-2014 传神(中国)网络科技有限公司
//*/
//package com.dhgate.htranslate.util;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.TreeSet;
//import java.util.regex.Pattern;
//
//import net.common.utils.ConfigUtil;
//import net.common.utils.ExportExcelPOI;
//import net.common.utils.FileUtils;
//import net.sf.json.JSONObject;
//import net.transn.order.vo.WordCountVo;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//public class WordCount{
//
//    //当前支持的语种列表,英法俄西葡
//    private static String[] supportedLangArray= {"en","fr","ru","es","pt"};
//
//    /**
//     * 
//     * getSupportedLang:当前支持的语种列表,639-1 的形式
//     *
//     * @return    参数说明
//     * String[]    返回值说明
//     * @throws 
//     * @since  　ver 1.0
//     */
//    public static String[] getSupportedLang(){
//        return supportedLangArray;
//    }
//
//    /**
//     * 
//     * getWordCount:获取当前支持语种的单词个数
//     *
//     * @param word  输入字符串
//     * @param lang  字符串对应语种,如果是未知，则自动调用微软接口进行判断语种
//     * @return  int    返回值说明：在支持语种范围内返回对应单词个数，否则则为 -1
//     * @throws 
//     * @since  　ver 1.0
//     */
//    public static int getWordCount(String word,SupportLang lang){
//        int count= 0;
//        String regex= LanguageRegex.getRegexByLang(lang);
//
//        //如果不支持,那么返回-1
//        if(regex.isEmpty()){ return -1; }
//
//        String[] wordArray= word.split(regex);
//        for(String singleWord:wordArray){
//            if(singleWord.matches("\\S+")){
//                count++;
//            }
//        }
//
//        return count;
//    }
//
//    /**
//     * 
//     * getWordCount:获取语种的单词个数
//     *
//     * @param word  :字符串
//     * @param lang  ：639-1 格式的语种,例如法语"fr"
//     * @return   返回值说明 ：在支持语种范围内返回对应单词个数，否则则为 -1
//     * @throws 
//     * @since  　ver 1.0
//     */
//    public static int getWordCount(String word,String lang){
//        int count= 0;
//        String regex= LanguageRegex.getRegexByLang(lang);
//      //如果不支持,那么返回-1
//    	if(regex.isEmpty()){ 
//    		
//    		StatWordCount wordCount = new StatWordCount();
//    		count = wordCount.getWordCount(word,false,false);
//    		return count;
//    	}
//    	word = getWordByRegex(word);
//        if(StringUtils.equalsIgnoreCase(lang, "zh")){
//        	 for(int i=0;i<word.length();i++){
//                 String b = Character.toString(word.charAt(i));
//                 if(b.matches(regex))
//                	 count++;
//                 else if(StringUtils.isNotBlank(b))
//                	 count++;;
//                }
//        }else{
////        	String[] wordArray= word.split(regex);
////        	for(String singleWord:wordArray){
////        		if(singleWord.matches("\\S+")){
////        			count++;
////        		}
////        	}
//        	count = WordCount.getCountWordsFromClassA(word);
//        }
//        return count;
//    }
//    
//    /**
//     * 
//     * 根据正则式规则过滤文本内容
//     * 
//     * @param word
//     * @return
//     */
//    public static String getWordByRegex(String word) {
//      java.util.regex.Pattern p_script;
//      java.util.regex.Matcher m_script;
//      java.util.regex.Pattern p_style;
//      java.util.regex.Matcher m_style;
//      java.util.regex.Pattern p_html;
//      java.util.regex.Matcher m_html;
////      定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
//      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
//      //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
//      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
//      String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
//      p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
//      m_script = p_script.matcher(word);
//      word = m_script.replaceAll(""); // 过滤script标签
//      p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
//      m_style = p_style.matcher(word);
//      word = m_style.replaceAll(""); // 过滤style标签
//      p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
//      m_html = p_html.matcher(word);
//      word = m_html.replaceAll(""); // 过滤html标签
//      word = word.replaceAll("&nbsp", "");
//      return word;
//    }
//    
//    /**
//     * 
//     * 函数功能说明 类A的计算规则 
//     * Daniel   2014-10-23
//     * 
//     * @param @param value
//     * @param @return 
//     * @return int 
//     * @throws
//     */
//    public static int getCountWordsFromClassA(String value) {
//		int valueLength = 0;
//		String[] words = value.replaceAll("[^a-zA-Z\\-]+", " ").trim().split(" ");
//		if (words.length > 0) {
//			TreeSet<Integer> lengths = new TreeSet<Integer>();
//			for (String word : words) {
//				lengths.add(word.length());
//				if (StringUtils.isNotBlank(word)) {
//					valueLength++;
//				}
//			}
//		}
//		return valueLength;
//	}
//
//    public static int getCountWordsFromWord(String str, String lang){
//    	int count = 0;
//    	if(StringUtils.isBlank(str))
//    		return count;
//    	WordCountVo wcv = new WordCountVo();
//    	String realName = FileUtils.diskNameProductor("txt.txt");
//		FileUtils.filePathMaker();
//		String filePath = FileUtils.filePathProductor(realName);
//		File file = ExportExcelPOI.writeTxtLocalForWord(str, filePath);
//		Object result = null;
////    	Date d = new java.util.Date(System.currentTimeMillis());
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        HttpEntity reqEntity = null;
//        HttpPost httppost = new HttpPost(ConfigUtil.getValue("countword"));
//        StringBody language = new StringBody("", ContentType.TEXT_PLAIN);
//        StringBody count_type = new StringBody("all", ContentType.TEXT_PLAIN);
//        try {
//        	FileBody bin = new FileBody(file);
//
//             reqEntity = MultipartEntityBuilder.create()
//                    .addPart("uploadfile", bin)
//                    .addPart("language", language)
//                    .addPart("count_type", count_type)
//                    .build();
//
//            httppost.setEntity(reqEntity);
//             response = httpclient.execute(httppost);
//                HttpEntity resEntity = response.getEntity();
//                if (resEntity != null) {
//                	result = "{" + EntityUtils.toString(resEntity) + "}";
//                	System.out.println(str + ":" + result);
//                	JSONObject jo = JSONObject.fromObject(result);
//                	wcv = (WordCountVo) jo.toBean(jo, WordCountVo.class);
//                }
//                EntityUtils.consume(resEntity);
////                System.out.println("\r\n" + d + "\r\n" + new java.util.Date(System.currentTimeMillis()));
//                if(StringUtils.equalsIgnoreCase(lang, "zh")){
//                	count = wcv.getCOUNT_CHAR();
//                }else{
//                	count = wcv.getCOUNT_NOTEAST_CHAR();
//                }
//                file.delete();
//        } catch (Exception e) {
//        	e.printStackTrace();
//		}finally {
//        	try {
//        		if(response!=null){
//        			response.close();
//        		}
//        		if(httpclient!=null){
//        			httpclient.close();
//        		}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//        }
//		return count;
//    }
//    
//}
