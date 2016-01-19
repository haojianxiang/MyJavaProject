package produce$consumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Promo_53_RefreshPromoRedisMain_bak implements IData<String, Object> {
	
	private int pagenum = 1;
	private final int pagesize = 1000;
	@Override
	public List<String> getData() {
		List<String> promoDtoList = new ArrayList<String>();
		return promoDtoList;
	}

	@Override
	public Object handleData(String t) {
		try {
			System.out.println("sys-put-");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error-put-");
		}
		return null;
	}

	@Override
	public void sendRunLog(String string) {
		System.out.println(string);
		
	}

	@Override
	public void sendRunInfo(String string) {
		System.out.println(string);
		
	}
	
	public static void main(String[] args) {
		ProduceConsumerFactory factory = new ProduceConsumerFactory(new Promo_53_RefreshPromoRedisMain_bak());
		Map<String, String> jobParam = new HashMap();
		jobParam.put("thread_size", "10");
		Result result = factory.start(jobParam);
		System.out.println(result);
		System.exit(0);
	}


}
