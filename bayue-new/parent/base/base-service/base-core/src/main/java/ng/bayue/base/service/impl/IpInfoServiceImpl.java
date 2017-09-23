package ng.bayue.base.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import ng.bayue.base.constant.BaseRedisCacheConstant;
import ng.bayue.base.domain.DistrictInfoDO;
import ng.bayue.base.domain.IpInfoDO;
import ng.bayue.base.persist.dao.IpInfoDAO;
import ng.bayue.base.service.IpInfoService;
import ng.bayue.constant.RedisCacheTimeConstant;
import ng.bayue.redis.RedisCacheUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ipInfoService")
public class IpInfoServiceImpl implements IpInfoService {

	private static final Logger logger = LoggerFactory
			.getLogger(IpInfoService.class);

	@Autowired
	private IpInfoDAO ipInfoDAO;

	@Override
	public IpInfoDO queryDistrictInfoByIp(String ip) {
		if (StringUtils.isEmpty(ip)) {
			return null;
		}
		ip = ip.trim();

		IpInfoDO cache = (IpInfoDO) RedisCacheUtil
				.getRedisCache(BaseRedisCacheConstant.DistrictInfoIP.IP_INFO + ip);
		if (null != cache) {
			return cache;
		}

		long ipInt = parseIpToInt(ip);
		cache = ipInfoDAO.selectIpInfoByIpInt(ipInt);
		
		RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.DistrictInfoIP.IP_INFO, cache, RedisCacheTimeConstant.MONTH);
		
		return cache;
	}

	/**
	 * <pre>
	 * ip字符串转ip的int形式
	 * </pre>
	 *
	 * @param ip
	 * @return
	 */
	private static long parseIpToInt(String ip) {
		try {
			InetAddress.getAllByName(ip);
		} catch (UnknownHostException e) {
			logger.error("未知主机地址，请检查ip参数", e);
		}

		String[] ips = ip.split("\\.");

		return Long.parseLong(ips[0]) * (2 << 23) 
			   + Long.parseLong(ips[1]) * (2 << 15) 
			   + Long.parseLong(ips[2]) * (2 << 7)
			   + Long.parseLong(ips[3]);

	}

	/**
	 * <pre>
	 * int型ip转string型ip
	 * </pre>
	 *
	 * @param ipInt
	 * @return
	 */
	private static String parseIntToIp(long ipInt) {
		String str0, str1, str2, str3;

		long s, s0;
		s = ipInt / (2 << 23);
		s0 = ipInt % (2 << 23);
		str0 = Long.toString(s);

		long s1, s11;
		s1 = s0 / (2 << 15);
		s11 = s0 % (2 << 15);
		str1 = Long.toString(s1);

		long s2, s22;
		s2 = s11 / (2 << 7);
		s22 = s11 % (2 << 7);
		str2 = Long.toString(s2);

		str3 = Long.toString(s22);

		return str0 + "." + str1 + "." + str2 + "." + str3;
	}

//	public static void main(String[] args) {
//		String host = "192.168.235.128";
//		String host1 = "222.247.49.138";
//		System.out.println(2 << 7);
//		System.out.println(2 << 15);
//		System.out.println(2 << 23);
//		long i = parseIpToInt(host1);
//		System.out.println(i);
//		System.out.println(parseIntToIp(i));
//	}

}
