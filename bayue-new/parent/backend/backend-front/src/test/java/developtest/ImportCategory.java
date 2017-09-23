package developtest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ng.bayue.backend.ao.basedata.CategoryAO;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.service.CategoryService;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-beans.xml",
		"classpath:spring/spring-web.xml"})
public class ImportCategory {
	
	private static final Logger logger = LoggerFactory.getLogger(ImportCategory.class);
	
	@Autowired
	private CategoryAO categoryAO;
	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void importCategorysExcel(){
		Map<String,List<String>> map = readerFile();
		
//		insertCategory(map);
		
	}
	
	private static Map<String,List<String>> readerFile(){
		String path="/category.xlsx";
		URL url = ImportCategory.class.getResource(path);
		String filePath = url.getFile();
		File file = new File(filePath);
		
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		try {
			Workbook book = WorkbookFactory.create(file);
			Sheet sheet = book.getSheetAt(0);
			int nums = sheet.getPhysicalNumberOfRows();
			System.out.println("physicalCount:" + nums);
			int realCount = 0;
			for(int i = 1; i < nums; i++){
				Row row = sheet.getRow(i);
				String first = row.getCell(0).getStringCellValue();
				String second = row.getCell(1).getStringCellValue();
//				System.out.println(first+" - "+second);
				
				//放入一级分类
				if(!map.containsKey(first)){
					List<String> list = new ArrayList<String>();
					list.add(second);
					realCount++;//统计一级分类个数
					map.put(first, list);
					realCount++;//统计二级分类个数
				}else{
					List<String> list = map.get(first);
					list.add(second);
					realCount++;//统计二级分类个数
				}
			}
			System.out.println("realCount:" + realCount);
			
		} catch (InvalidFormatException | IOException e) {
			logger.error("", e);
		}
		
		return map;
	}
	
	private void insertCategory(Map<String,List<String>> map){
		int fir = 1;
		for(Map.Entry<String, List<String>> entry : map.entrySet()){
//			fir++;
//			if(fir>3){break;}
			String first = entry.getKey();
			//插入一级分类，返回id，此id为二级分类的parentId
			CategoryDO categoryDO = new CategoryDO();
			categoryDO.setName(first);
			categoryDO.setLevel(1);
			categoryDO.setParentId(0L);
			categoryDO.setStatus(true);
			
			Date date = new Date();
			categoryDO.setCreateTime(date);
			categoryDO.setModifyTime(date);
			categoryDO.setCreateUserId(UserHandler.getUser().getId());
			
			String codeFir = categoryAO.selectMaxCode(categoryDO);
//			String codeFir = fir < 10 ? "0" + fir++ : "" + fir++;
			categoryDO.setCode(codeFir);
			
//			Long firstId = categoryService.insert(categoryDO);
			Long firstId = (Long) categoryAO.addCategory(categoryDO).getData();
			
			//插入二级分类
			List<String> seconds = entry.getValue();
			int sec = 1;
			for(String second : seconds){
				CategoryDO categoryDO1 = new CategoryDO();
				categoryDO1.setName(second);
				categoryDO1.setLevel(2);
				categoryDO1.setParentId(firstId);
				categoryDO1.setStatus(true);
				
				categoryDO1.setCreateTime(date);
				categoryDO1.setModifyTime(date);
				categoryDO1.setCreateUserId(UserHandler.getUser().getId());
				
				String codeSec = categoryAO.selectMaxCode(categoryDO1);
//				String codeSec = sec < 10 ? codeFir + "0" + sec++ : codeFir + sec++;
				categoryDO1.setCode(codeSec);
				categoryAO.addCategory(categoryDO1);
			}
			
		}
	}

}
