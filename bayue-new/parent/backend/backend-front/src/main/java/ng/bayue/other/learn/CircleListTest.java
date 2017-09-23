package ng.bayue.other.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CircleListTest {

	public static void test() {

		String[] star = { "*", "＊", "✲", "❈", "❉", "✿", "❀", "❃", "❁", "☸", "↕", "↔", "➷", "➹", "♠", "♥", "♦", "♣", "☜",
				"☝", "☞", "☟", "↖", "↑", "↗", "→", "↘", "㊣", "↓", "↙", "←", "★", "☆" };
		CircleList<String> list = new CircleList<String>();
		// for (String str : star) {
		// list.add(str);
		// }

		// int len = star.length;
		// String[] starDesc = new String[len];
		// for (int i = len; i > 0; i--) {
		// starDesc[len-i] = star[i-1];
		// }
		// for (String str : starDesc) {
		// list.add(str);
		// }
		// for (int i = 0; i < 500000; i++) {
		// list.circle(1);
		// System.out.println(list);
		// }
	}

	public static void testInverse() {
		CircleList<String> list = new CircleList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");

		System.out.println(list);
		list.inverse();
		System.out.println(list);
	}


	public static void main(String[] args) {
		// test();
		// testInverse();
	}

}
