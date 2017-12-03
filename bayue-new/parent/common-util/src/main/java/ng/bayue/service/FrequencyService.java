package ng.bayue.service;

import ng.bayue.common.model.FrequencyModel;


/**
 * <pre>
 * 接口频率访问控制器
 * </pre>
 *
 * @author lenovopc
 * @version $Id: FrequencyService.java, v 0.1 2017年11月27日 下午2:15:14 lenovopc Exp $
 */
public interface FrequencyService {
	
	/**
	 *
	 * @param model
	 * @return true-频率未达上限,false-频率已经达到上限
	 */
	Boolean overLoad(FrequencyModel model);
	
}
