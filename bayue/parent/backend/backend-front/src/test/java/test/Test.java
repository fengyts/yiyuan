package test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;

import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.dto.ItemDTO;


public class Test {
	
	public static void testArrPrintString(){
		List<String> list = new ArrayList<String>();
		list.add("ab");
		list.add("123");
		list.add("ef");
		String[] arr = list.toArray(new String[0]);
		System.out.println(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void testBeanUtils(){
		ItemDTO dto = new ItemDTO();
		dto.setSpu("010101");
		dto.setBarcode("barcode");
		dto.setBasicPrice(12.3);
		dto.setDetailId(1L);
		dto.setItemId(2L);
		dto.setLargeId(12L);
		dto.setSmallId(32L);
		dto.setPrdid("01010101prd");
		dto.setItemTitle("itemTitle");
		dto.setMainTitle("mainTitle");
		dto.setReturnDays(5);
		dto.setMainTitle("mainTitle");
		dto.setProductType(true);
		dto.setItemType(1);
		dto.setSubTitle("subTitle");
		dto.setUnitId(23L);
		
		ItemInfoDO infoDO = new ItemInfoDO();
		ItemDetailDO detailDO = new ItemDetailDO();
		
		try {
			BeanUtils.copyProperties(infoDO, dto);
			BeanUtils.copyProperties(detailDO, dto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		testBeanUtils();
	}

}
