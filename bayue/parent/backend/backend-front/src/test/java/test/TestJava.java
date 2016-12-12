package test;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestJava {

	@Test
	public void test() {
		int n = 31;
		long res = 1;
		for(int i = 0;i<n;i++){
			res *= 2;
		}
		System.out.println(res);
		System.out.println(res*2);
		//4294967296
	}

}
