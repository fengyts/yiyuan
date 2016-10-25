package ng.bayue.backend.shiro_old;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestShiro {
	
	public static void main(String[] args) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		UsernamePasswordToken token = new UsernamePasswordToken("test", "123");
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		currentUser.logout();
	}
	
	public void test(){
		TestShiro ts = new TestShiro();
		A a = new A();
		a.setName("wangwu");
		a.setAge(22);
		JSONObject o = JSONObject.fromObject(a);
		String str = o.toString();
		System.out.println(str);
	}

}

class A{
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
