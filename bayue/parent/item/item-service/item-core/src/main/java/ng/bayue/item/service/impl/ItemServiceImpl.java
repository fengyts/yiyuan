package ng.bayue.item.service.impl;

import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.item.service.ItemInfoService;
import ng.bayue.item.service.ItemService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="itemService")
public class ItemServiceImpl implements ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemInfoService infoService;
	@Autowired
	private ItemDetailService detailService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveInfoAndDetail(ItemInfoDO infoDO, ItemDetailDO detailDO) throws ServiceException {
		if (null == detailDO) {
			logger.info("插入itemDetail异常,ItemDetailDO 不能为空");
			return -1;
		}
		String spuDetail = detailDO.getSpu();
		if (StringUtils.isEmpty(spuDetail)) {
			logger.info("插入iteminfo和itemdetail异常,spu不能为空");
			return -1;
		}
		Long itemId = detailDO.getItemId();
		if (null == itemId) {
			logger.info("插入itemDetail异常,itemId不能为空");
			return -1;
		}
		String mainTitle = detailDO.getMainTitle();
		String subTitle = detailDO.getSubTitle();
		if (StringUtils.isEmpty(mainTitle) || StringUtils.isEmpty(subTitle)) {
			logger.info("插入itemDetail异常,subTitle和mainTitle不能为空");
			return -1;
		}
		Long res1 = null, res2 = null;

		if (null == infoDO) {
			res1 = detailService.insert(detailDO);
			return 0 < res1 ? 1 : -1;
		} else {
			String spu = infoDO.getSpu();
			Long largeId = infoDO.getLargeId();
			Long smallId = infoDO.getSmallId();
			Long unitId = infoDO.getUnitId();
			String mainTitleSpu = infoDO.getMainTitle();
			if (StringUtils.isEmpty(spu) || null == largeId || null == smallId || null == unitId
					|| StringUtils.isEmpty(mainTitleSpu)) {
				logger.info("插入itemInfo异常，spu,大类id,小类id,单位id不能为空");
				return -1;
			}
			res1 = infoService.insert(infoDO);
			res2 = detailService.insert(detailDO);
			return res1 > 0 && res2 > 0 ? 1 : -1;
		}

	}
}
