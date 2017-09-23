package ng.bayue.learn.thread;

public class MyThread {

	private static String usernameRef;
	private static String passwordRef;

	synchronized public static void test(String username, String password) {
		try {
			usernameRef = username;
			if ("a".equals(username)) {
				Thread.sleep(3000);
			}
			passwordRef = password;
			System.out.println("username=" + usernameRef + " password=" + passwordRef);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
