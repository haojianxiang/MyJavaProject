package processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pipeline.DBPlatformContactPipeline;

import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;


/**
 * 对于一些简单网站的抓取模板（仅供参考）
 * @author haojianxiang
 *
 */


public class Model implements PageProcessor{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private AtomicInteger pageNum = new AtomicInteger(0);
	private Site site = Site.me().setRetryTimes(3).setTimeOut(100000);
	private String searchUrl;
	private String taskId;
	private String taskid;
	
	private static final String URL_START = "***************************";
	private static final String URL_LIST = "***************************";
	private static final String URL_TARGET = "***************************";
	
	
	public static void main(String[] args) throws Exception{
		String taskId = "**name_data**";
		String[] urls2 = {  "**startUrl**" };
//		String[] urls2 = {  "**textUrl**" };
		for (int i = 0; i < urls2.length; i++) {
			startSpider(taskId, urls2[i]);
		}
		
	}
	private static void startSpider(String taskId, String startUrls) {
		
		JedisPool pool= new JedisPool("127.0.0.1");
		Spider.create(new Model(taskId))
		.addPipeline(new DBPlatformContactPipeline()).setUUID(taskId)
		.setScheduler(new RedisScheduler(pool))
		.addUrl(startUrls)
		.thread(5)
		.run();
		
	}
	
	
	@Override
	public void process(Page page) {
		if (page.getUrl().toString().equals(URL_START)) {
			System.out.println("开始");
			startFromURL_START(page);
        } else if (page.getUrl().regex(URL_LIST).match()) {
			System.out.println("分页获取");
			addTargetPageList(page);
		} else if (page.getUrl().regex(URL_TARGET).match()) {
			System.out.println("详情抓取");
			getTargetPageContent(page);
		} else{
        	logger.error("不能匹配的url:" + page.getUrl());
        	System.out.print("error:不能匹配的url:");
        	System.out.println(page.getUrl());
        }
		
	}
	
	/**
	 * 获取主页面上所有分类的列表
	 * 
	 */
	
	
	private void startFromURL_START(Page page){
		
		List<String> allList = page.getHtml().xpath("//div[@class='主页面全部列表连接div']").all();
		System.out.println(allList);
		page.addTargetRequests(allList);
		
	}
	
	/**
	 * 获取所有列表的分页
	 * 
	 */
	private void addTargetPageList(Page page){
		page.addTargetRequests(page.getHtml().links().regex(URL_TARGET).all());
		
		//任选其一
		String nexthref = page.getHtml().xpath("下一页链接").all().get(0);
		System.out.println("nexthref"+nexthref);
		page.addTargetRequest(nexthref);
		
		//任选其一
		List<String> footHrefs = page.getHtml().xpath("分页区").all();
		System.out.println(footHrefs);
		page.addTargetRequests(footHrefs);
		
	}
	
	/**
	 * 获取所需信息
	 * 
	 */
	private void getTargetPageContent(Page page){
		int currentNum=pageNum.incrementAndGet();
		logger.info("当前第"+currentNum+" 个 url:"+page.getUrl().all().get(0));
		
		Map<String ,Object> data=new HashMap<String, Object>();
		
		if (StringUtils.isNotBlank(taskId)) {
			data.put("taskId", taskId);
		}
		
		data.put("company_name", page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("address", 	 page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("person", 		 page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("telephone", 	 page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("mobilephone",  page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("fax", 		 page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("email", 		 page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("product", 	 page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("category", 	 page.getHtml().xpath("//div[@class='']").all().get(0));
		data.put("websiteid", "**");
		data.put("website_url", page.getUrl().toString());

		System.out.println("data"+data);
//		page.putField(YwClawConstants.PROCESS_DOMAIN_KEY,data);
	}
	
	
	
	
	@Override
	public Site getSite() {
		site.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.27 Safari/537.36"); // 下面的可以不加
		site.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"); // 下面的可以不加
		site.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		site.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		site.addHeader("Connection", "keep-alive");
		return site;
	}
	
	
	
	public Model(){
		
	}

	public Model(String taskid) {
		super();
		this.taskid = taskid;
	}

	public Model(String taskId, String searchUrl) {
		super();
		this.searchUrl = searchUrl;
		this.taskId = taskId;
	}
	
	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}


}
