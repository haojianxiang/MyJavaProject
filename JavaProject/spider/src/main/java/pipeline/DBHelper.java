package pipeline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

//import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 获取数据库连接
 * 
 * @author qindongliang
 * 
 * **/
public class DBHelper {

	private static Logger log = LoggerFactory.getLogger(DBHelper.class);
	private static Properties p = new Properties();
 	private static BasicDataSource dataSource;     
	static {
		try {
			p.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("mysql.properties"));
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(p.getProperty("driver"));
			dataSource.setUrl(p.getProperty("url"));
			dataSource.setUsername(p.getProperty("username"));
			dataSource.setPassword(p.getProperty("password"));
			dataSource.setInitialSize(Integer.parseInt(p.getProperty("initialSize").trim()));
//			dataSource.setMaxActive(Integer.parseInt(p.getProperty("maxActive").trim()));
			Runtime.getRuntime().addShutdownHook(new Thread(){
				@Override
				public void run() {
					if(dataSource != null) {
						try {
							dataSource.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			log.error("读取连接配置文件发生异常!");
		}

	}

	public Connection getConn() {
	 
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			log.error("获取数据库连接异常", e);
		}
	
		return null;
	}

	/**
	 * 关闭数据库资源
	 * 
	 * **/
	public void close(Connection con,PreparedStatement ps,ResultSet rs)throws Exception{
		if(rs!=null){
			rs.close();
		}
		if(ps!=null){
			ps.close();
		}
		if(con!=null){
			con.close();
		}
	}
	
	public void destory(){
		if(dataSource != null) {
			try {
				dataSource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
