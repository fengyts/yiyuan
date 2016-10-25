package ng.bayue.util;

/**
 * JAVA 返回随机数，并根据概率、比率
 * 
 * @author zhanglei
 * 
 */
public class Probability {

	/**
	 * 0出现的概率为50%
	 */
	public static double rate0 = 0.50;
	/**
	 * 1出现的概率为20%
	 */
	public static double rate1 = 0.20;
	/**
	 * 2出现的概率为15%
	 */
	public static double rate2 = 0.15;
	/**
	 * 3出现的概率为10%
	 */
	public static double rate3 = 0.10;
	/**
	 * 4出现的概率为4%
	 */
	public static double rate4 = 0.04;
	/**
	 * 5出现的概率为1%
	 */
	public static double rate5 = 0.01;

	/**
	 * Math.random()产生一个double型的随机数，判断一下 例如0出现的概率为%50，则介于0到0.50中间的返回0
	 * 
	 * @return int
	 * 
	 */
	private int percentageRandom() {
		double randomNumber;
		randomNumber = Math.random();
		if (randomNumber >= 0 && randomNumber <= rate0) {
			return 0;
		} else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
			return 1;
		} else if (randomNumber >= rate0 + rate1 && randomNumber <= rate0 + rate1 + rate2) {
			return 2;
		} else if (randomNumber >= rate0 + rate1 + rate2
				&& randomNumber <= rate0 + rate1 + rate2 + rate3) {
			return 3;
		} else if (randomNumber >= rate0 + rate1 + rate2 + rate3
				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4) {
			return 4;
		} else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4
				&& randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4 + rate5) {
			return 5;
		}
		return -1;
	}

	/**
	 * 测试主程序
	 * 
	 * @param agrs
	 */
	public static void main(String[] agrs) {
		int i = 0;
		Probability pro = new Probability();
		for (i = 0; i <= 100; i++) {// 打印100个测试概率的准确性
//			System.out.println(pro.percentageRandom());
			double d = Math.random();
			if(d < 0.01){
				if(d < 0.001){
					System.out.println("炸弹："+ d);
					java.text.DecimalFormat df = new java.text.DecimalFormat("#.###################");
					String str = df.format(d);
					System.out.println(str);
				}
				System.out.println(d);
			}
		}
	}
}
