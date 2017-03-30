//package com.meitun.scheduler.salesorder;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:spring.xml" })
//public class JobTest {
//
//    private static final Logger LOG = LoggerFactory.getLogger(JobTest.class);
//
//    @Autowired
//    private SalesOrderRemoteService salesOrderRemoteService;
//
//    @Test
//    public void testFindSubOrder4BackendPage() {
//        while (true) {
//            try {
//                int result = salesOrderRemoteService.finishOrderInBatch(2, 15, 45);
//
//                if (result < 2) {
//                    break;
//                }
//                LOG.info("完成--------");
//            } catch (Exception e) {
//                LOG.error("订单自动完成异常", e);
//                break;
//            }
//        }
//    }
//
//}
