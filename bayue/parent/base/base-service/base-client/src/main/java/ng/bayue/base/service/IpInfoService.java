package ng.bayue.base.service;

import ng.bayue.base.domain.IpInfoDO;


/**
 * Service
 * 
 * @author fengyts 2016-07-15 11:56:24
 */
public interface IpInfoService {

	IpInfoDO queryDistrictInfoByIp(String ip);

}
