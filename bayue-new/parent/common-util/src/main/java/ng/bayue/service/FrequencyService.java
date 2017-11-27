package ng.bayue.service;

import ng.bayue.common.FrequencyModel;

/**
 * <pre>
 * 接口频率访问控制器
 * </pre>
 *
 * @author lenovopc
 * @version $Id: FrequencyService.java, v 0.1 2017年11月27日 下午2:15:14 lenovopc Exp $
 */
public interface FrequencyService {
	
	Boolean overLoad(FrequencyModel model);
	
}
