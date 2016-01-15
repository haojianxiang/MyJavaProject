package util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PropertiesConfig {
	private static final Log log = LogFactory.getLog(PropertiesConfig.class);
	private static Properties prop = new Properties();
	private static String FILENAME = "config.properties";
	
	static{
		try{
			InputStream in = PropertiesConfig.class.getClassLoader().getResourceAsStream(FILENAME);
			prop.load(in);
			if(in != null){
				in.close();
			}
			log.info("config file "+ FILENAME + " is load success!");
		}catch(Exception e){
			throw new RuntimeException("config file " + FILENAME + " is not found!",e);
		}
	}
	
	public static String getValue(String key){
		return prop.getProperty(key);
	}
}
