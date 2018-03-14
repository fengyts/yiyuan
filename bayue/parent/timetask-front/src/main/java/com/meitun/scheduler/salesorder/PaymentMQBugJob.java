package com.meitun.scheduler.salesorder;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.local.PaymentMQBug;

/**
 * 支付MQ BUG修复
 * 
 * @author 项硕
 * @version 2015年6月16日 下午5:43:45
 */
@Component("paymentMQBugJob")
public class PaymentMQBugJob extends AbstractJobRunnable {
	private static final Logger log = LoggerFactory.getLogger(PaymentMQBugJob.class);
	private static final String CURRENT_JOB_PREFIXED = "paymentMQBug";
	
	@Autowired
	private PaymentMQBug salesOrderPaidCallback;
	
	public static void main(String[] args) throws IOException {
		List<String> codeList = IOUtils.readLines(PaymentMQBugJob.class.getResourceAsStream("code.txt"));
		System.out.println(codeList);
	}
	
	@Override
	public void execute(){
		log.info("start to run PaymentMQBugJob");
		
		try {
			List<String> codeList = IOUtils.readLines(PaymentMQBugJob.class.getClassLoader().getResourceAsStream("code.txt"));
			for (String code : codeList) {
				if (null != code) {
					log.info("start to fix order[{}]", code);
					try {
						salesOrderPaidCallback.fix(code.trim());
						log.info("end to fix order[{}]", code);
					} catch (Exception e) {
						log.error("fix order[{}] fail", code);
					}
				}
			}
		} catch (IOException e) {
			log.error("get code.txt fail", e);
		}
	}
	
	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
