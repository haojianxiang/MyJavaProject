package pipeline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


public class DBPlatformContactPipeline extends DBHelper implements Pipeline {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static final String  INSERT_SQL="INSERT INTO platform_contact4(taskId, websiteid, person, telephone, mobilephone, email, category, product, fax, address, zip, country_region, province_state, city, company_name, operational_address, website, website_url) "
			+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

	@Override
	public void process(ResultItems resultItems, Task task) {
		Map map = resultItems.get(YwClawConstants.PROCESS_DOMAIN_KEY);
		if (map != null) {
//			logger.info("taskId:" + map.get("taskId") + "person:" + map.get("person"));
			insertShopCategory(map);

		} else {
			logger.warn("没抓取到数据");
		}
	}

	/**
	 * 保存详情页面
	 * 
	 * @param map
	 */
	private void insertShopCategory(Map map) {
//		map.put("telephone", "");
		try {
			Connection con = this.getConn();
			if (con == null) {
				throw new NullPointerException("单条插入数据，获取的数据库连接为null！");
			}
			PreparedStatement ps = con.prepareStatement(INSERT_SQL);
			setProperty(ps, map);
			ps.executeUpdate();
			this.close(con, ps, null);// 释放资源
			logger.info("存储一条数据成功" );
		} catch (Exception e) {
			logger.error("存储单条数据异常!", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 设置属性
	 * */
	private void setProperty(PreparedStatement ps,Map map)throws Exception{
		

		ps.setString(1,map.get("taskId")+"");
		ps.setString(2,map.get("websiteid")+"");
		ps.setString(3,map.get("person")+"");
		ps.setString(4,map.get("telephone")+"");
		ps.setString(5,map.get("mobilephone")+"");
		ps.setString(6,map.get("email")+"");
		ps.setString(7,map.get("category")+"");
		ps.setString(8,map.get("product")+"");
		ps.setString(9,map.get("fax")+"");
		ps.setString(10,map.get("address")+"");
		ps.setString(11,map.get("zip")+"");
		ps.setString(12,map.get("country_region")+"");
		ps.setString(13,map.get("province_state")+"");
		ps.setString(14,map.get("city")+"");
		ps.setString(15,map.get("company_name")+"");
		ps.setString(16,map.get("operational_address")+"");
		ps.setString(17,map.get("website")+"");
		ps.setString(18,map.get("website_url")+"");
		
	}
	

}
