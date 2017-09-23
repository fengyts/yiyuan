package ng.bayue.base.constant;

public final class BaseRedisCacheConstant {

	/**
	 * <pre>
	 * 违禁词缓存常量
	 * </pre>
	 *
	 * @author fengyts
	 * @version $Id: BaseRedisCacheConstant.java, v 0.1 2016年7月7日 上午11:57:15 fengyts Exp $
	 */
	public interface ForbiddenWords{
		
		/**  */
		String FORBIDDENWORDS = "Base_ForbiddenWords_";
		
		/** 所有有效的违禁词 */
		String ALL_EFFECTIVE_FORBIDDEN_WORDS = "ALL_EFFECTIVE_FORBIDDEN_WORDS";
		
	}
	
	/**
	 * <pre>
	 * 地区ip地址缓存key
	 * </pre>
	 *
	 * @author fengyts
	 * @version $Id: BaseRedisCacheConstant.java, v 0.1 2016年7月15日 下午12:43:13 fengyts Exp $
	 */
	public interface DistrictInfoIP{
		
		/** 地区ip信息缓存 */
		String IP_INFO = "IP_INFO_";
		
		
	}
	
	
}
