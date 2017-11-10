package ng.bayue.other.learn.designmodel.singleton;

/**
 * <pre>
 * 实现单利模式的原则和过程： 
    1.单例模式：确保一个类只有一个实例，自行实例化并向系统提供这个实例 
    2.单例模式分类：饿单例模式（类加载时实例化一个对象给自己的引用），懒单例模式（调用取得实例的方法如getInstance时才会实例化对象）（java中饿单例模式性能优于懒单例模式，c++中一般使用懒单例模式） 
    3.单例模式要素： 
        a.私有构造方法 
        b.私有静态引用指向自己实例 
        c.以自己实例为返回值的公有静态方法 

	1.饿汉式：单例实例在类装载时就构建，急切初始化。（预先加载法）
 * </pre>
 *
 * @author lenovopc
 * @version $Id: HungrySingleton.java, v 0.1 2017年11月1日 下午4:27:53 lenovopc Exp $
 */
public class HungrySingleton {

	private HungrySingleton() {
	}

	public static HungrySingleton singleton = new HungrySingleton();

	public static HungrySingleton getInstance() {
		return singleton;
	}

}
