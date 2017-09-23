package ng.bayue.other.learn;

public class InnerClassTest {
	
	private Integer id;
	
	public void method(){
		InnerClassTest.Inner1 inner = new Inner1();
		InnerClassTest.Inner2 inner2 = this.new Inner2();
		this.id = inner.idInner;
		InnerClassTest.Inner3 inner3 = new Inner3();
	}
	
	public static void method1(){}
	
	public class Inner1{
		private int idInner;
		public String name;
	}
	
	class Inner2{
		private int idInner;
		public String name;
		
		InnerClassTest.Inner1 i1 = new Inner1();
	}
	
	static class Inner3{
		private static String name;
		private int id3;
	}

}

class A{
	private int id;
	public static String name;
	public final int age=1;
}
