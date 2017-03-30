//package com.meitun.scheduler.salesorder;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.meitun.redis.util.JedisCacheUtil;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:spring.xml" })
//public class CancelNoSendTrialOrderJobTest {
//
//    @Autowired
//    private CancelNoSendTrialOrderJob job;
//
//    @Autowired
//    private JedisCacheUtil jedisCacheUtil;
//
//    private static final String TRIAL_ORDER_JOB_KEY = "TASK_TRIAL_ORDER_JOB_KEY_";
//
//    @Test
//    public void testExecute() {
//        jedisCacheUtil.unLock(TRIAL_ORDER_JOB_KEY);
//
//        job.execute();
//    }
//
//}
