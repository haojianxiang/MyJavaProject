package util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

/**
 * 签名工具类
 * @author dengrenfu
 *
 */
public class SignUtils {

    private static final Logger logger = Logger.getLogger(SignUtils.class);
	private static ReentrantLock simpleFlushLock=new ReentrantLock();
    /**
     * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
     * uppercase(hex(sha1(secretkey1value1key2value2...secret))
     *
     * @param paramNames  需要签名的参数名
     * @param paramValues 参数列表
     * @param secret
     * @return
     * @throws Exception 
     */
    public static String sign(Map<String, String> paramValues, String secret) throws Exception {
        return sign(paramValues,null,secret);
    }

    /**
     * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
     * @throws Exception 
     */
    public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames,String secret) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if(ignoreParamNames != null && ignoreParamNames.size() > 0){
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] digest = getMD5Digest(sb.toString());
            return byte2hex(digest);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }    

    public static String utf8Encoding(String value, String sourceCharsetName) {
        try {
            return new String(value.getBytes(sourceCharsetName), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }

    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static String getUUID() {
    	simpleFlushLock.lock();
    	String key=null;
    	try{
        UUID uuid = UUID.randomUUID();
         key=uuid.toString().toUpperCase();
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("获取uuid异常.",e);
    	}finally{
    		simpleFlushLock.unlock();	
    	}
		return key;
        
    }

}

