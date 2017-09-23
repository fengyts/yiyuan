package ng.bayue.snatch.creeper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.base.domain.FrontCategoryLinkDO;
import ng.bayue.base.dto.FrontCategoryViewDTO;
import ng.bayue.base.service.FrontCategoryLinkService;
import ng.bayue.base.service.FrontCategoryService;
import ng.bayue.util.net.RequestUtils;

/**
 * <pre>
 * mt前端分类爬虫,开发测试用
 * </pre>
 *
 * @author lenovopc
 * @version $Id: MTFrontCategory.java, v 0.1 2017年4月11日 下午3:23:32 lenovopc Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-beans.xml", "classpath:spring/spring-web.xml"})
public class MTFrontCategory {
	
	@Autowired
	private FrontCategoryService frontCategoryService;
	@Autowired
	private FrontCategoryLinkService frontCategoryLinkService;

	@Test
	public void test() {
		String url = "http://www.meitun.com/loadfc?callback=";
		String dataStr = RequestUtils.doRequestReturnStr(url, null, "UTF-8");
		dataStr = dataStr.substring(1, dataStr.length() - 1);
		// System.out.println(dataStr);
		JSONObject object = JSONObject.parseObject(dataStr);
		String arrData = object.getString("data");
		List<FrontCategory1> list = JSONArray.parseArray(arrData, FrontCategory1.class);
		System.out.println(list.size());

		List<FrontCategoryDO> listData = new ArrayList<FrontCategoryDO>();
		List<FrontCategoryLinkDO> linkData = new ArrayList<FrontCategoryLinkDO>();
		
		Date date = new Date();
		int countfc = 0;
		for (FrontCategory1 fc1 : list) {
			FrontCategoryDO fc = new FrontCategoryDO();
			fc.setCreateTime(date);
			fc.setModifyTime(date);
			fc.setCreateUserId(1L);
			fc.setModifyUserId(1L);
			try {
				BeanUtils.copyProperties(fc, fc1);
				Long id = fc1.getId();
				fc.setSort(fc1.getSeq());
				fc.setCode(id.toString());
				fc.setParentId(0L);
				fc.setStatus(true);
				fc.setIsPublish(true);
				listData.add(fc);
				countfc++;
				List<FrontCategoryViewDTO> childs = fc1.getChilds();
				for (FrontCategoryViewDTO fcdto : childs) {
					FrontCategoryDO fcc = new FrontCategoryDO();
					BeanUtils.copyProperties(fcc, fcdto);
					fcc.setParentId(id);
					fcc.setSort(fcdto.getSeq());
					fcc.setCode(id.toString());
					fcc.setStatus(true);
					fcc.setIsPublish(true);
					fcc.setCreateTime(date);
					fcc.setModifyTime(date);
					fcc.setCreateUserId(1L);
					fcc.setModifyUserId(1L);
					countfc++;
					listData.add(fcc);
					//跳转链接
					FrontCategoryLinkDO fcl = new FrontCategoryLinkDO();
//					BeanUtils.copyProperties(fcl, fcdto);
					fcl.setFrontCategoryId(fcdto.getId());
					String appUrlLink = fcdto.getAppUrlLink();
					fcl.setLinkUrlApp(appUrlLink == null ? "" : appUrlLink);
					String pcUrlLink = fcdto.getPcUrlLink();
					fcl.setLinkUrlPc(pcUrlLink == null ? "" : pcUrlLink);
					String wapUrlLink = fcdto.getWapUrlLink();
					fcl.setLinkUrlWap(wapUrlLink == null ? "" : wapUrlLink);
					
					fcl.setLargeCategoryIds(id.toString());
					fcl.setSmallCategoryIds(fcdto.getId().toString());
					
					fcl.setLinkContent("test");
					fcl.setLinkType(1);
					fcl.setStatus(true);
					fcl.setCreateTime(date);
					fcl.setModifyTime(date);
					fcl.setCreateUserId(1L);
					fcl.setModifyUserId(1L);
					linkData.add(fcl);
				}

			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		frontCategoryService.insertBatch(listData);
		frontCategoryLinkService.insertBatch(linkData);

	}

}

class FrontCategory1 extends FrontCategoryViewDTO {

	private static final long serialVersionUID = 5875715204156889060L;

	/** 排序 */
	Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = this.getSeq();
	}

}
