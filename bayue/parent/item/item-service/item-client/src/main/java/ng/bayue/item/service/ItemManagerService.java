package ng.bayue.item.service;

public interface ItemManagerService {

	/**
	 * <pre>
	 * 获取spu,prdid编码
	 * </pre>
	 *
	 * @param code
	 *            获取spu编码,code为小类的编码;获取prdid编码,code为spu
	 * @param type 编码类型，1-spu编码，2-prdid编码，具体参考{@link ng.bayue.item.constant.CodeConstant}类
	 * @return
	 */
	String getUniqueCode(String code, int type);

}
