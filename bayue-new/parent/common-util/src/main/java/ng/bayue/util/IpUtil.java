package ng.bayue.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * IP地址获取
 * @author fengyts 2014年4月22日12:49:53
 *
 */
public class IpUtil {
	
	
	private final static Logger logger = LoggerFactory.getLogger(IpUtil.class);

//	/**
//	 * 获取登录用户IP地址
//	 * 
//	 * @param request
//	 * @return
//	 */
//	public static String getIpAddr(String desc,HttpServletRequest request) {
//		String ip = request.getHeader("X-Forwarded-For");
//		logger.info("{}，X-Forwarded-For：{}",desc,ip);
//		if (StringUtil.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//			logger.info("{}，Proxy-Client-IP：{}",desc,ip);
//		}
//		if (StringUtil.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//			logger.info("{}，WL-Proxy-Client-IP：{}",desc,ip);
//		}
//		if (StringUtil.isNullOrEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//			logger.info("{}，getRemoteAddr：{}",desc,ip);
//		}
//		if (ip.equals("0:0:0:0:0:0:0:1")) {
//			ip = "127.0.0.1";
//		}
//		if(ip.contains(","))ip = ip.split(",")[0];
//		logger.info("{}，最后获取到IP地址为：{}",desc,ip);
//		return ip;
//	}
	
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */  
    public static String getIpAddr(HttpServletRequest request){  
    		String ipAddress = request.getHeader("X-Forwarded-For"); 
        
    		
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP"); 
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP"); 
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                       return "127.0.0.1";
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
    		if(ipAddress.contains(",")) {
    			ipAddress = ipAddress.split(",")[0];
    		}
    		
    		logger.info("最后获取到用户的IP地址为：{}",ipAddress);
    		
            return ipAddress;   
    }  
	
    /** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */  
    public static String getIpAddr(String desc,HttpServletRequest request){  
    		String ipAddress = request.getHeader("X-Forwarded-For"); 
        
    		logger.info("{}，X-Forwarded-For：{}",desc,ipAddress);
    		
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP"); 
                logger.info("{}，Proxy-Client-IP：{}",desc,ipAddress);
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP"); 
                logger.info("{}，WL-Proxy-Client-IP：{}",desc,ipAddress);
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                logger.info("{}，getRemoteAddr：{}",desc,ipAddress);
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                       return "127.0.0.1";
                    }  
                    ipAddress= inet.getHostAddress();  
                    logger.info("{}，getHostAddress：{}",desc,ipAddress);
                }  
            }  
    		if(ipAddress.contains(","))ipAddress = ipAddress.split(",")[0];
    		
    		logger.info("{}，最后获取到用户的IP地址为：{}",desc,ipAddress);
    		
            return ipAddress;   
    }  

}
