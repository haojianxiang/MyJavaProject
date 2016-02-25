package base;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.ArrayUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class UsedCodes {
	
	
	public static void main(String[] args) {
		stringToDate();
	}
	
	//1. 字符串有整型的相互转换
	public static void transformStringInt() {
		Integer i = Integer.parseInt("1"); //numeric string to an int
		String	string = String.valueOf(i);   //integer to numeric string  
	}
	
	//2. 向文件末尾添加内容
	public static void addTofile() throws IOException{
		BufferedWriter out = null;  
		try {  
		    out = new BufferedWriter(new FileWriter("filename", true));  
		    out.write("aString");  
		} catch (IOException e) {  
		    // error processing code  
		} finally {  
		    if (out != null) {  
		        out.close();  
		    }  
		}
	}
	
	//3.得到当前方法的名字
	public static void getMethodName(){
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(methodName);
	}
	
	//4. 转字符串到日期
	public static void stringToDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String dstr="2008-4-24";  
		Date date = new Date();
		try {
			date=sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
	}
	
	
	//6.把 Java util.Date 转成 sql.Date
	public static void sqlDate(){
		java.util.Date utilDate = new java.util.Date();  
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	}
	
	//7. 使用NIO进行快速的文件拷贝
	public static void fileCopy( File in, File out )  
            throws IOException  
    {  
        FileChannel inChannel = new FileInputStream( in ).getChannel();  
        FileChannel outChannel = new FileOutputStream( out ).getChannel();  
        try
        {  
//          inChannel.transferTo(0, inChannel.size(), outChannel);      // original -- apparently has trouble copying large files on Windows  
 
            // magic number for Windows, 64Mb - 32Kb)  
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);  
            long size = inChannel.size();  
            long position = 0;  
            while ( position < size )  
            {  
               position += inChannel.transferTo( position, maxCount, outChannel );  
            }  
        }  
        finally
        {  
            if ( inChannel != null )  
            {  
               inChannel.close();  
            }  
            if ( outChannel != null )  
            {  
                outChannel.close();  
            }  
        }  
    }
	
	//8.创建图片的缩略图
	private void createThumbnail(String filename, int thumbWidth, int thumbHeight, int quality, String outFilename)  
	        throws InterruptedException, FileNotFoundException, IOException  
	    {  
	        // load image from filename  
	        Image image = Toolkit.getDefaultToolkit().getImage(filename);  
	        MediaTracker mediaTracker = new MediaTracker(new Container());  
	        mediaTracker.addImage(image, 0);  
	        mediaTracker.waitForID(0);  
	        // use this to test for errors at this point: System.out.println(mediaTracker.isErrorAny());  
	 
	        // determine thumbnail size from WIDTH and HEIGHT  
	        double thumbRatio = (double)thumbWidth / (double)thumbHeight;  
	        int imageWidth = image.getWidth(null);  
	        int imageHeight = image.getHeight(null);  
	        double imageRatio = (double)imageWidth / (double)imageHeight;  
	        if (thumbRatio < imageRatio) {  
	            thumbHeight = (int)(thumbWidth / imageRatio);  
	        } else {  
	            thumbWidth = (int)(thumbHeight * imageRatio);  
	        }  
	 
	        // draw original image to thumbnail image object and  
	        // scale it to the new size on-the-fly  
	        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);  
	        Graphics2D graphics2D = thumbImage.createGraphics();  
	        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);  
	 
	        // save thumbnail image to outFilename  
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename));  
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);  
	        quality = Math.max(0, Math.min(quality, 100));  
	        param.setQuality((float)quality / 100.0f, false);  
	        encoder.setJPEGEncodeParam(param);  
	        encoder.encode(thumbImage);  
	        out.close();  
	    }
	
	
	//17. 把 Array 转换成 Map
	public static void arrayToMap(String[] args) {  
	    String[][] countries = { { "United States", "New York" }, { "United Kingdom", "London" },  
	        { "Netherland", "Amsterdam" }, { "Japan", "Tokyo" }, { "France", "Paris" } };  
	    Map countryCapitals = ArrayUtils.toMap(countries);  
	    System.out.println("Capital of Japan is " + countryCapitals.get("Japan"));  
	    System.out.println("Capital of France is " + countryCapitals.get("France"));  
	  }  

	//18. 发送邮件
	public void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException {  
	    boolean debug = false;  
	 
	     //Set the host smtp address  
	     Properties props = new Properties();  
	     props.put("mail.smtp.host", "smtp.example.com");  
	 
	    // create some properties and get the default Session  
	    Session session = Session.getDefaultInstance(props, null);  
	    session.setDebug(debug);  
	 
	    // create a message  
	    Message msg = new MimeMessage(session);  
	 
	    // set the from and to address  
	    InternetAddress addressFrom = new InternetAddress(from);  
	    msg.setFrom(addressFrom);  
	 
	    InternetAddress[] addressTo = new InternetAddress[recipients.length];  
	    for (int i = 0; i < recipients.length; i++)  
	    {  
	        addressTo[i] = new InternetAddress(recipients[i]);  
	    }  
	    msg.setRecipients(Message.RecipientType.TO, addressTo);  
	 
	    // Optional : You can also set your custom headers in the Email if you Want  
	    msg.addHeader("MyHeaderName", "myHeaderValue");  
	 
	    // Setting the Subject and Content Type  
	    msg.setSubject(subject);  
	    msg.setContent(message, "text/plain");  
	    Transport.send(msg);  
	}
	
	//19. 发送代数据的HTTP 请求
    public static void postHttpRequest(String[] args)  {  
        try {  
            URL my_url = new URL("http://coolshell.cn/");  
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));  
            String strTemp = "";  
            while(null != (strTemp = br.readLine())){  
            System.out.println(strTemp);  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }
    }
	
	//20. 改变数组的大小
    private static Object resizeArray (Object oldArray, int newSize) {  
    	   int oldSize = java.lang.reflect.Array.getLength(oldArray);  
    	   Class elementType = oldArray.getClass().getComponentType();  
    	   Object newArray = java.lang.reflect.Array.newInstance(  
    	         elementType,newSize);  
    	   int preserveLength = Math.min(oldSize,newSize);  
    	   if (preserveLength > 0)  
    	      System.arraycopy (oldArray,0,newArray,0,preserveLength);  
    	   return newArray;  
    	}  
}
