package processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pipeline.DBPlatformContactPipeline;
import pipeline.YwClawConstants;
import redis.clients.jedis.Jedis;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * http://shop.pcpop.com/ 网站商家名，联系人名，电话及邮箱的抓取
 * @author haojianxiang
 *
 */

public class PaopaowangProcessor implements PageProcessor{
	
	private Jedis jedis = new Jedis("127.0.0.1",6379);
	private Logger logger = LoggerFactory.getLogger(getClass());
	private AtomicInteger pageNum = new AtomicInteger(0);
	private Site site = Site.me().setRetryTimes(2).setTimeOut(5000);
	private static final String channel = "泡泡网";
	
	private static final String URL_START = "http://shop.pcpop.com/";
	private static final String URL_LIST = "http://shop\\.pcpop\\.com\\/list-.*\\.html";
	private static final String URL_TARGET = "http://\\d+\\.shop\\.pcpop\\.com/.*";
	
	public static void main(String[] args) throws Exception{
		String[] urls2 = {  "http://shop.pcpop.com/" };
//		String[] urls2 = {  "http://28397.shop.pcpop.com/" };
		for (int i = 0; i < urls2.length; i++) {
			startSpider(channel, urls2[i]);
		}
	}
	
	/**
	 * 开始抓取
	 * @param channel
	 * @param startUrls
	 */
	private static void startSpider(String channel, String startUrls) {
		
//		JedisPool pool= new JedisPool("127.0.0.1");
		Spider.create(new PaopaowangProcessor())
		.addPipeline(new DBPlatformContactPipeline()).setUUID(channel)
//		.setScheduler(new RedisScheduler(pool))
		.addUrl(startUrls)
		.thread(16)
		.run();
	}
	
	@Override
	public void process(Page page) {
		if (page.getUrl().toString().equals(URL_START)) {
			//开始
			startFromURL_START(page);
        } else if (page.getUrl().regex(URL_LIST).match()) {
			//分页获取
			addTargetPageList(page);
		} else if (page.getUrl().regex(URL_TARGET).match()) {
			//详情抓取
			getTargetPageContent(page);
		} else{
        	logger.error("不能匹配的url:" + page.getUrl());
        	System.out.println("******************error不能匹配的url******************");
        }
	}
	
	/**
	 * 获取主页面上所有分类的列表
	 * @param page
	 */
	private void startFromURL_START(Page page){
		List<String> allList = page.getHtml().xpath("//div[@class='pe5']//a/@href").all();
		page.addTargetRequests(allList);
	}
	
	/**
	 * 获取所有列表的分页
	 * @param page
	 */
	private void addTargetPageList(Page page){
//		page.addTargetRequests(page.getHtml().links().regex(URL_TARGET).all());
		List<String> urls = page.getHtml().xpath("//div[@class='pf811']//a/@href").all();
		if (jedis!=null) {
			for (String url:urls) {
				try {
					if (jedis.get(url)==null) {
						jedis.set(url, "1");
						page.addTargetRequest(url);
					}else {
//						logger.info("重复的URL>>>>>---"+url);
					}
				} catch (Exception e) {
//					e.printStackTrace();
					page.addTargetRequest(url);
				}
			}
		}else {
			page.addTargetRequests(page.getHtml().xpath("//div[@class='pf811']//a/@href").all());
		}
//		page.addTargetRequests(page.getHtml().xpath("//div[@class='pf811']//a/@href").all());
		List<String> footHrefs = page.getHtml().xpath("//div[@class='pf5']//a/@href").all();
		page.addTargetRequests(footHrefs);
		
	}
	
	/**
	 * 获取所需信息
	 * @param page
	 */
	private void getTargetPageContent(Page page){
		int currentNum=pageNum.incrementAndGet();
		logger.info("当前第"+currentNum+" 个 url:"+page.getUrl().all().get(0));
		
		Map<String ,Object> data=new HashMap<String, Object>();
		String mobilephone = page.getHtml().xpath("//div[@class='shq6']/div[@class='shq62']/text()").toString();
		int i = mobilephone.length();
		List<String> a = page.getHtml().xpath("//div[@class='channel-page-nav-bottom']/text()").all();
		String a1 = a.get(1);
		String[] aa = a1.split("\\ ");
		data.put("person", aa[6].substring(4, aa[6].length()));
		mobilephone = mobilephone.substring(i-11, i);
		mobilephone = mobilephone.replaceAll(" ", ""); 
		
		/**
		 * 二次需求
		 */
		
		if (mobilephone.matches("^[1][3,5,8]\\d{9}")) {
			data.put("mobilephone", mobilephone);
		}else {
			data.put("mobilephone", "");
		}
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime  = formatter.format(date);
		data.put("channel", "paopaowang");
		String email = aa[2].substring(5, aa[2].length());
		email =email.replaceAll(" ", ",");
		if (email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
			data.put("email", email);
		}else {
			data.put("email", "");
		}
		data.put("website", page.getUrl().toString());
		data.put("is_synch", 0);
		data.put("created", currentTime);
		if (data.get("email").equals("")&data.get("mobilephone").equals("")) {
			logger.warn("没数据");
		}else {
			page.putField(YwClawConstants.PROCESS_DOMAIN_KEY,data);
		}
	}
	
	@Override
	public Site getSite() {
		site.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.27 Safari/537.36");
		site.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		site.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		site.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		site.addHeader("Connection", "keep-alive");
		return site;
	}
	
}
