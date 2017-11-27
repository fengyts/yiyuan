package ng.bayue.enums;

/**
 * <pre>
 * redis缓存校验状态类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: RedisModelStatusEnum.java, v 0.1 2017年11月27日 下午1:38:36 lenovopc
 *          Exp $
 */
public enum RedisModelStatusEnum {

	/** 正确 */
	CORRECT,
	/** 错误 */
	ERROR,
	/** 过期 */
	OVERDUE,
	/** 频率过高  */
	HIGH_FREQUENCY;

}
