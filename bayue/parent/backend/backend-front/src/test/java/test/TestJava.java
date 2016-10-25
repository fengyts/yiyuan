package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class TestJava {

	@Test
	public void test() {
		File file = new File("E:/test/Koala.jpg");
		System.out.println(file.length());
	}

}
