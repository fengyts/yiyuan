package ng.bayue.item.constant;


/**
 * 商品详情 常量
 * 
 * @author haisheng.Long 2016-07-13 13:17:52
 */
public interface ItemDetailConstant {
	
	public static final class ItemStatus{
		public static final int OFF_SALES = 0; // "未上架";
		public static final int ON_SALES = 1; // "已上架";
		public static final int CANCELLATION = 2; // "作废";
	}
	
	public interface ItemType{
		final int NOMAL_ITEM = 1;//正常商品
		final int SERVER_ITEM = 2;//服务商品
		final int SECOND_HAND_ITEM = 3;//二手商品
	}

}
