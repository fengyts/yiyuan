package ng.bayue.item.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import ng.bayue.item.constant.CodeConstant;
import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.dto.ItemDetailDTO;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.service.CodeService;
import ng.bayue.item.service.ItemDescService;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.item.service.ItemManagerService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * item公用接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ItemManagerServiceImpl.java, v 0.1 2016年12月6日 上午10:21:31
 *          lenovopc Exp $
 */
@Service(value = "itemManagerService")
public class ItemManagerServiceImpl implements ItemManagerService {

	private static final Logger logger = LoggerFactory.getLogger(ItemManagerServiceImpl.class);
	
	@Autowired
	private CodeService codeService;
	@Autowired
	private ItemDetailService itemDetailService;
	@Autowired
	private ItemDescService itemDescService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long saveItemDetail(ItemDetailDTO detailDto) throws ServiceException {
		ItemDetailDO detailDO = new ItemDetailDO();
		try {
			String spu = detailDto.getSpu();
			if(StringUtils.isBlank(spu)){
				throw new ServiceException("save item detail error: the parameter spu is null");
			}
			String description = detailDto.getDescription();
			if(StringUtils.isBlank(description)){
				throw new ServiceException("save item detail error: the parameter description is null");
			}
			String prdid = codeService.getUniqueCode(spu, CodeConstant.CodeType.PRDID_CODE);
			BeanUtils.copyProperties(detailDO, detailDto);
			detailDO.setPrdid(prdid);
			Long id = itemDetailService.insert(detailDO);
			if(id < 1){
				throw new ServiceException("保存商品详情出错");
			}
			
			Date date = detailDto.getCreateTime();
			Long userId = detailDto.getCreateUserId();
			//保存详情描述信息
			ItemDescDO descDO = new ItemDescDO();
			descDO.setDetailId(id);
			descDO.setDescription(description);
			descDO.setItemId(detailDto.getItemId());
			descDO.setCreateTime(date);
			descDO.setModifyTime(date);
			descDO.setCreateUserId(userId);
			descDO.setModifyUserId(userId);
			Long descId = itemDescService.insert(descDO);
			if(descId < 1){
				throw new ServiceException("save item detail error: save item description error");
			}
			//保存规格组信息
			
			return id;
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
		}
		return null;
	}


	

}
