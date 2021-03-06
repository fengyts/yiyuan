package ng.bayue.snatch.service.impl.item;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.service.CategoryService;
import ng.bayue.base.service.DictionaryService;
import ng.bayue.common.Page;
import ng.bayue.snatch.constant.item.CodeConstant;
import ng.bayue.snatch.domain.item.ItemInfoDO;
import ng.bayue.snatch.dto.item.ItemInfoDTO;
import ng.bayue.snatch.exception.DAOException;
import ng.bayue.snatch.exception.ServiceException;
import ng.bayue.snatch.persist.dao.item.ItemInfoDAO;
import ng.bayue.snatch.service.item.CodeService;
import ng.bayue.snatch.service.item.ItemInfoService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "itemInfoService")
public class ItemInfoServiceImpl implements ItemInfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemInfoDAO itemInfoDAO;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private CodeService codeService;

	@Override
	public Long insert(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			Long smallId = itemInfoDO.getSmallId();
			if(null == smallId){
				throw new ServiceException("保存spu时出错,小类id不能为空");
			}
			CategoryDO cateSmall = categoryService.selectById(smallId);
			String cateSmallCode = cateSmall.getCode();
			if(StringUtils.isBlank(cateSmallCode)){
				logger.info("保存spu时出错,根据小类id-{}获取小类编码出错",smallId);
				throw new ServiceException("保存spu时出错,根据小类id-" + smallId + "获取小类编码出错");
			}
			String spu = codeService.getUniqueCode(cateSmallCode, CodeConstant.CodeType.SPU_CODE);
			itemInfoDO.setSpu(spu);
			return itemInfoDAO.insert(itemInfoDO);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public int update(ItemInfoDO itemInfoDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) itemInfoDAO.update(itemInfoDO);
			} else {
				return (Integer) itemInfoDAO.updateDynamic(itemInfoDO);
			}
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) itemInfoDAO.deleteById(id);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public ItemInfoDO selectById(Long id) throws ServiceException {
		try {
			return itemInfoDAO.selectById(id);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.selectCountDynamic(itemInfoDO);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ItemInfoDO> selectDynamic(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.selectDynamic(itemInfoDO);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	private List<ItemInfoDO> selectDynamicPageQuery(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.selectDynamicPageQuery(itemInfoDO);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	public Page<ItemInfoDTO> queryPageListByItemInfoDO(ItemInfoDO itemInfoDO) {
		if (itemInfoDO != null) {
			Long totalCount = this.selectCountDynamic(itemInfoDO);
			List<ItemInfoDO> resultList = this.selectDynamicPageQuery(itemInfoDO);
			List<ItemInfoDTO> result = handleAssociationData(resultList);

			Page<ItemInfoDTO> page = new Page<ItemInfoDTO>();
			page.setPageNo(itemInfoDO.getStartPage());
			page.setPageSize(itemInfoDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(result);
			return page;
		}
		return new Page<ItemInfoDTO>();
	}

	public Page<ItemInfoDTO> queryPageListByItemInfoDOAndStartPageSize(ItemInfoDO itemInfoDO, int startPage,
			int pageSize) {
		if (itemInfoDO != null && startPage > 0 && pageSize > 0) {
			itemInfoDO.setStartPage(startPage);
			itemInfoDO.setPageSize(pageSize);
			Page<ItemInfoDTO> page = this.queryPageListByItemInfoDO(itemInfoDO);

			return page;
		}
		return new Page<ItemInfoDTO>();
	}

	private List<ItemInfoDTO> handleAssociationData(List<ItemInfoDO> list) {
		List<ItemInfoDTO> result = new ArrayList<ItemInfoDTO>();
		if (CollectionUtils.isEmpty(list)) {
			return result;
		}
		List<Long> listLarge = new ArrayList<Long>();
		List<Long> listSmall = new ArrayList<Long>();
		List<Long> listDic = new ArrayList<Long>();
		for (ItemInfoDO info : list) {
			listLarge.add(info.getLargeId());
			listSmall.add(info.getSmallId());
			listDic.add(info.getUnitId());
		}
		List<CategoryDO> listLargeDO = categoryService.selectByIds(listLarge);
		List<CategoryDO> listSmallDO = categoryService.selectByIds(listSmall);
		List<DictionaryDO> listDicDO = dictionaryService.selectByIds(listDic);
		for (ItemInfoDO info : list) {
			ItemInfoDTO infoDto = new ItemInfoDTO();
			try {
				long largeId = info.getLargeId();
				for (CategoryDO cate : listLargeDO) {
					if (largeId == cate.getId().longValue()) {
						infoDto.setLargeCateName(cate.getName());
						break;
					}
				}
				long smallId = info.getSmallId();
				for (CategoryDO cateSmall : listSmallDO) {
					if (smallId == cateSmall.getId().longValue()) {
						infoDto.setSmallCateName(cateSmall.getName());
						break;
					}
				}
				long dicId = info.getUnitId();
				for (DictionaryDO dic : listDicDO) {
					if (dicId == dic.getId().longValue()) {
						infoDto.setUnitName(dic.getName());
						break;
					}
				}
				BeanUtils.copyProperties(infoDto, info);
				result.add(infoDto);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("", e);
			}
		}
		return result;
	}

	@Override
	public ItemInfoDO selectBySPU(String spu) throws ServiceException {
		if (StringUtils.isEmpty(spu)) {
			return null;
		}
		try {
			List<ItemInfoDO> list = itemInfoDAO.selectBySPU(spu);
			if (CollectionUtils.isEmpty(list)) {
				logger.info("spu 不存在");
				return null;
			}
			if (1 < list.size()) {
				logger.info("数据库中存在2个相同的spu");
				return null;
			}
			return list.get(0);
		} catch (DAOException e) {
			logger.error("根据spu获取iteminfo异常：{}", e);
		}
		return null;
	}

	@Override
	public List<ItemInfoDO> selectBySpus(List<String> spus) throws ServiceException {
		return null;
	}

	@Override
	public int updateRebuildSpu(ItemInfoDO infoDO, boolean isRebuildSpu) throws ServiceException {
		if(null == infoDO){
			return -1;
		}
		if(isRebuildSpu){
			long smallId = infoDO.getSmallId();
			CategoryDO cateDO = categoryService.selectById(smallId);
			if(null == cateDO){
				logger.info("更新spu信息异常，根据小类id获取类别异常,id:{}",smallId);
				throw new ServiceException("更新spu信息异常，根据小类id获取类别异常");
			}
			String code = cateDO.getCode();
			String spu = codeService.getUniqueCode(code, CodeConstant.CodeType.SPU_CODE);
			infoDO.setSpu(spu);
		}
		return update(infoDO, false);
	}


}
