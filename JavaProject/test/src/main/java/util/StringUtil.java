package util;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



public class StringUtil {
    private final static String regEx="[^\\w%\\(\\)\\^\\|\\+-]+"; //去非数字字母
    private final static String DELIMITER = "-"; 
    private final static int KEYWORD_LENGTH_IN_URL = 40;  //url总长度控制在80个字符左右
    
    private static final Logger log = Logger.getLogger(StringUtil.class);
    
    /**
     * 替换类目名称的特殊字符  并且转成小写 
     * @param cname
     * @return
     */
    public static String replaceCname(String cname){
        String returnCname=cname;
        if(returnCname==null){
            returnCname="";
        }
        returnCname = returnCname.trim();
        returnCname=returnCname.replace("\"", "-");
        //090324日，解决类目名称mp3/mp4 ... 链接地址不对问题
        returnCname=returnCname.replace("/", "-");
        
        returnCname=returnCname.replace("&", "-");
        returnCname=returnCname.replace("'", "-");
        returnCname=returnCname.replace(" ", "-");
        returnCname=returnCname.replace(",", "-");
        returnCname=returnCname.replace("'", "-");
        returnCname=returnCname.replaceAll("-+", "-").trim();
        return returnCname.toLowerCase();
    }

    
    /**
     * 根据图片的路径，取得该图片的小样的路径
     * @param simagepath    //图片的路径
     * @return  String  //返回图片小样的路径,".thumb.gif"
     * @author tzc 2005-5-25
     */
    public static String getThumbnailPath(String simagepath){
        String sthumbnailpath= null;
        String stemp = null;
        int itemp = 0;
        stemp = simagepath;
        if(stemp!=null){
            itemp = stemp.lastIndexOf(".");
            //将最后的点“.” 文件的扩展名
            if( itemp > -1 ){
                stemp = stemp.substring( 0,itemp );
                sthumbnailpath = stemp + ".thumb.gif";
            }   
        }
        //如果没有找到用nophoto代替
        if( sthumbnailpath == null ){
            sthumbnailpath = "images/no_photo.gif";
        }
        return sthumbnailpath;
    }
    
    /**
     * 将字符串转换为长整型
     * @param src
     * @return
     */
    public static long convertStrToLong(String src){
        long longVal = 0;
        try {
            longVal = Long.parseLong(src);
        } catch (NumberFormatException e) {
            longVal=0;
        }
        return longVal;
    }
    
    public static double formatDouble(double doubleVal){
        DecimalFormat df=new DecimalFormat("#.00"); 
        return new Double(df.format(doubleVal));
        
    }
    
    /**
     * 替换关键词中的敏感字符，如：空格 百分号 斜杠,形成一个查询的地址
     * @param appid TODO
     * @param keyword
     * @return
     */
    public static String getUrlByKeywordsAndCatalogid(String keywords, String categoryid, String searchurl){
        if(keywords==null || categoryid == null)
            return "";
        String url = null ;
        String getkeywords = keywords.replaceAll("( )+","+");
        getkeywords = getkeywords.replaceAll("%","%2525");
        getkeywords = getkeywords.replaceAll("\"","%2522");
        getkeywords = getkeywords.toLowerCase() ;
        url = "http://"  + searchurl + getkeywords + "_c" + categoryid
                + ".html";
        return url;
    }
    
    /***
     * 验证非中文和非中文标点
     * @author leidengyan
     */
    public static boolean checknoChinese(String str) {
        Pattern pattern = Pattern.compile("[^\u4e00-\u9fa5]+$");
        Pattern pattern1 = Pattern.compile("[\\x00-\\x80]+$");
        Matcher match = pattern.matcher(str);
        Matcher match1 = pattern1.matcher(str);
        if(match.matches() && match1.matches()){
            return true ;
        } else {
            return false ;
        }
    }
    
    /***
     * 验证英文、数字、下划线的组合
     * @author leidengyan
     */
    public static boolean checkENU(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]+$");  //^\w+$
        Matcher match = pattern.matcher(str);
        return match.matches();
    }
    
    public final static String formatKeyword(String keyword){
        if(keyword==null)
            return "new-style";
        try{
            String s = keyword.replaceAll(regEx, " ");
            s = s.toLowerCase().trim();
            String regex = "free[\\s-_]*shipping[^\\w]*";
            s = s.replaceAll(regex, "");
            s = s.replaceAll("[^\\w]+", DELIMITER);
            s = s.replaceAll("-+", DELIMITER);
            s = URLEncoder.encode(s);
            
            if( s.length()>KEYWORD_LENGTH_IN_URL ){
                int i = s.indexOf(DELIMITER,KEYWORD_LENGTH_IN_URL);
                if(i>-1){
                	s = s.substring(0,i);
                }
            }
 
            return s.toLowerCase();
        }catch(Exception e){
            log.error("正则表达式处理错误: "+keyword);
            return "new-style";
        }
    }
    
    
	public static String getCanonicalUrl(String productName,String itemcode){
		String filterProName = formatKeyword(productName.replaceAll("&quot;", ""));
		String  canonicalUrl= "product/" + filterProName + "/" + itemcode + ".html";
		return canonicalUrl;
	}
	
	public static String formatSpecWords(String str){
	    if(null == str){
	        return "";
	    }
		str = str.replaceAll("\"", " ");
		str = str.replaceAll("'", " ");
		str = str.replaceAll("\\\\", " ");
		
		if(str.length() > 100){
			str = str.substring(0, 100);
			str += "...";
		}
		return str;
	}
	
	public static void main(String[] a){
		System.out.println(formatKeyword("grenda d8 bag ( china NO1 brand)"));
	}
}
