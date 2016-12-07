package test;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestJava {

	@Test
	public void test() {
		String str = "123.22";
		BigDecimal decimal = new BigDecimal(str);
		System.out.println(decimal);
		
	}

}
