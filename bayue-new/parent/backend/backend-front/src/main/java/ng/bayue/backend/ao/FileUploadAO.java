package ng.bayue.backend.ao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.constant.CategoryConstant.LEVEL;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.service.CategoryService;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploadAO {

	private final Logger logger = LoggerFactory.getLogger(FileUploadAO.class);

	@Autowired
	private CategoryService categoryService;

	public void insertCategoryBatch(List<CategoryDO> list) {
		
	}

	public ResultMessage uploadFile(InputStream is) {
		ResultMessage msg = new ResultMessage();
		try {
			Workbook book = WorkbookFactory.create(is);
			Sheet sheet = book.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
			for (int i = 1; i < rows; i++) {
				/*if(i == 0){//忽略excel第一行
					continue;
				}*/
				
				Row row = sheet.getRow(i);
				String big = row.getCell(0).getStringCellValue().trim();
				String mid = row.getCell(1).getStringCellValue().trim();
				String smal = row.getCell(2).getStringCellValue().trim();
				
				if(!map.containsKey(big)){//不包含大类，加入大类,中类，和小类
					Map<String,List<String>> mapMid = new HashMap<String,List<String>>();
					List<String> listSmal = new ArrayList<String>();
					listSmal.add(smal);
					mapMid.put(mid, listSmal);
					map.put(big, mapMid);
				}else{
					Map<String,List<String>> mapMid = map.get(big);
					if(!mapMid.containsKey(mid)){//中类中不包含中类，加入中类，小类
						List<String> listSmal = new ArrayList<String>();
						listSmal.add(smal);
						mapMid.put(mid, listSmal);
					}else{
						List<String> listSmal = mapMid.get(mid);
						listSmal.add(smal);
					}
				}
				
			}
			
//			int i=1;
			Date date = new Date();
			List<CategoryDO> listSmalCate = new ArrayList<CategoryDO>();
			for(Map.Entry<String, Map<String,List<String>>> entry : map.entrySet()){
				String big = entry.getKey();
				
				//插入大类
				CategoryDO bigCate = new CategoryDO();
				bigCate.setLevel(LEVEL.LARGE);
				bigCate.setName(big);
				bigCate.setParentId(0L);
				bigCate.setStatus(true);
				bigCate.setCreateTime(date);
				bigCate.setModifyTime(date);
				
				long bigId = categoryService.insert(bigCate);
				
				Map<String,List<String>> mapMid = entry.getValue();
				for(Map.Entry<String, List<String>> entry1 : mapMid.entrySet()){
					String mid = entry1.getKey();
					List<String> listSmal = entry1.getValue();
					
					//插入中类
					CategoryDO midCate = new CategoryDO();
					midCate.setLevel(LEVEL.MIDDLE);
					midCate.setName(mid);
					midCate.setParentId(bigId);
					midCate.setStatus(true);
					midCate.setCreateTime(date);
					midCate.setModifyTime(date);
					
					long midId = categoryService.insert(midCate); 
					
					for(String smal : listSmal){
//						System.out.println(i + ":" + big + " - " + mid + " - " + smal);
//						i++;
						
						//插入中类
						CategoryDO smalCate = new CategoryDO();
						smalCate.setLevel(LEVEL.SMALL);
						smalCate.setName(smal);
						smalCate.setParentId(midId);
						smalCate.setStatus(true);
						smalCate.setCreateTime(date);
						smalCate.setModifyTime(date);
						
						listSmalCate.add(smalCate);
					}
				}
			}
//			categoryService.insertBatch(listSmalCate);
			
			msg.setMessage("haha");
			return msg;

		} catch (InvalidFormatException | IOException e) {
			logger.error("", e);
		}
		return msg;
	}

}
