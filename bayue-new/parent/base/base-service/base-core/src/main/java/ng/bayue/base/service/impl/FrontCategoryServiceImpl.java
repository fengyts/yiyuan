package ng.bayue.base.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ng.bayue.base.constant.BaseRedisKeyConstant;
import ng.bayue.base.constant.CategoryConstant;
import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.base.domain.FrontCategoryLinkDO;
import ng.bayue.base.dto.FrontCategoryDTO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.FrontCategoryDAO;
import ng.bayue.base.persist.dao.FrontCategoryLinkDAO;
import ng.bayue.base.service.FrontCategoryService;
import ng.bayue.common.Page;
import ng.bayue.constant.RedisCacheTimeConstant;
import ng.bayue.exception.DAOException;
import ng.bayue.service.RedisCacheService;

@Service(value = "frontCategoryService")
public class FrontCategoryServiceImpl implements FrontCategoryService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "redisCacheService")
	private RedisCacheService redisCacheService;
	@Autowired
	private FrontCategoryDAO frontCategoryDAO;
	@Autowired
	private FrontCategoryLinkDAO frontCategoryLinkDAO;

	@Override
	public Long insert(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.insert(frontCategoryDO);
		} catch (DAOException e) {
			logger.info("", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int update(FrontCategoryDO frontCategoryDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) frontCategoryDAO.update(frontCategoryDO);
			} else {
				return (Integer) frontCategoryDAO.updateDynamic(frontCategoryDO);
			}
		} catch (DAOException e) {
			logger.info("", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) frontCategoryDAO.deleteById(id);
		} catch (DAOException e) {
			logger.info("", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public FrontCategoryDO selectById(Long id) throws ServiceException {
		try {
			return frontCategoryDAO.selectById(id);
		} catch (DAOException e) {
			logger.info("", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.selectCountDynamic(frontCategoryDO);
		} catch (DAOException e) {
			logger.info("", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<FrontCategoryDO> selectDynamic(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.selectDynamic(frontCategoryDO);
		} catch (DAOException e) {
			logger.info("", e);
			throw new ServiceException(e);
		}
	}

	private List<FrontCategoryDO> selectDynamicPageQuery(FrontCategoryDO frontCategoryDO) throws ServiceException {
		try {
			return frontCategoryDAO.selectDynamicPageQuery(frontCategoryDO);
		} catch (DAOException e) {
			logger.info("", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Page<FrontCategoryDO> queryPageListDynamic(FrontCategoryDO frontCategoryDO) throws ServiceException {
		if (frontCategoryDO != null) {
			Long totalCount = this.selectCountDynamic(frontCategoryDO);

			Page<FrontCategoryDO> page = new Page<FrontCategoryDO>();
			page.setPageNo(frontCategoryDO.getStartPage());
			page.setPageSize(frontCategoryDO.getPageSize());
			page.setTotalCount(totalCount.intValue());

			if (null != totalCount && totalCount.longValue() > 0) {
				List<FrontCategoryDO> resultList = this.selectDynamicPageQuery(frontCategoryDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<FrontCategoryDO>();
	}

	@Override
	public Page<FrontCategoryDO> queryPageListDynamicAndStartPageSize(FrontCategoryDO frontCategoryDO,
			Integer startPage, Integer pageSize) throws ServiceException {
		if (frontCategoryDO != null && startPage > 0 && pageSize > 0) {
			frontCategoryDO.setStartPage(startPage);
			frontCategoryDO.setPageSize(pageSize);
			return this.queryPageListDynamic(frontCategoryDO);
		}
		return new Page<FrontCategoryDO>();
	}

	@Override
	public int insertBatch(List<FrontCategoryDO> list) {
		if (CollectionUtils.isEmpty(list)) {
			return -1;
		}
		return frontCategoryDAO.insertBatch(list);
	}

	@Override
	@Transactional
	public int insertFrontCategoryAndLinked(FrontCategoryDTO fcDto, Long userId) {
		try {
			Date date = new Date();

			FrontCategoryDO fcDO = new FrontCategoryDO();
			BeanUtils.copyProperties(fcDO, fcDto);
			fcDO.setCreateUserId(userId);
			fcDO.setCreateTime(date);
			fcDO.setModifyUserId(userId);
			fcDO.setModifyTime(date);
			String code = selectMaxCodeDynamic(fcDO);
			fcDO.setCode(code);
			if(fcDto.getLevel() == 1){
				fcDO.setParentId(0L);
			}
			Long frontCateId = insert(fcDO);
			
			if(fcDO.getLevel() != 1){
				FrontCategoryLinkDO fcLinkDO = new FrontCategoryLinkDO();
				BeanUtils.copyProperties(fcLinkDO, fcDto);
				fcLinkDO.setFrontCategoryId(frontCateId);
				fcLinkDO.setCreateUserId(userId);
				fcLinkDO.setCreateTime(date);
				fcLinkDO.setModifyUserId(userId);
				fcLinkDO.setModifyTime(date);
				frontCategoryLinkDAO.insert(fcLinkDO);
			}

			redisCacheService.deleteRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL);
			redisCacheService.deleteRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL_F);

			return 1;
		} catch (Exception e) {
			logger.info("插入前台类目失败: {}", e);
		}
		return -1;
	}

	@Override
	@Transactional
	public int updateFrontCategoryAndLinked(FrontCategoryDTO dto, Long userId) throws ServiceException {
		try {
			Date date = new Date();
			FrontCategoryDO fcate = new FrontCategoryDO();
			BeanUtils.copyProperties(fcate, dto);
			fcate.setModifyTime(date);
			fcate.setModifyUserId(userId);
			update(fcate, false);

			FrontCategoryLinkDO fcLink = new FrontCategoryLinkDO();
			BeanUtils.copyProperties(fcLink, dto);
			fcLink.setId(null);
			fcLink.setModifyTime(date);
			fcLink.setModifyUserId(userId);
			frontCategoryLinkDAO.updateByFrontCateId(fcLink);
			
			redisCacheService.deleteRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL);
			redisCacheService.deleteRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL_F);
			
			return 1;
		} catch (Exception e) {
			logger.info("更新前台类目失败: {}", e);
		}
		return -1;
	}

	@Override
	public List<FrontCategoryDTO> getAllFrontCategoryAndLink() {
		@SuppressWarnings("unchecked")
		List<FrontCategoryDTO> resultList = (List<FrontCategoryDTO>) redisCacheService
				.getRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL);
		if (CollectionUtils.isNotEmpty(resultList)) {
			return resultList;
		}
		resultList = new ArrayList<FrontCategoryDTO>();
		try {
			FrontCategoryDO frontCategoryDO = new FrontCategoryDO();
			List<FrontCategoryDO> listFC = selectDynamic(frontCategoryDO);
			if (CollectionUtils.isEmpty(listFC)) {
				return resultList;
			}
			List<FrontCategoryLinkDO> listLinkFC = frontCategoryLinkDAO.selectDynamic(new FrontCategoryLinkDO());
			for (FrontCategoryDO cate : listFC) {
				long id = cate.getId();
				FrontCategoryDTO dto = new FrontCategoryDTO();
				BeanUtils.copyProperties(dto, cate);
				for (FrontCategoryLinkDO linkDO : listLinkFC) {
					long cateId = linkDO.getFrontCategoryId();
					if (id == cateId) {
						BeanUtils.copyProperties(dto, linkDO);
						dto.setId(id);
						continue;
					}
				}
				resultList.add(dto);
			}
			redisCacheService.setRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL, resultList,
					RedisCacheTimeConstant.WEEK);
			return resultList;
		} catch (DAOException | IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
		}

		return resultList;
	}

	@Override
	public FrontCategoryDTO selectFCAndLinkByFCID(Long fcId) throws ServiceException {
		if (null == fcId || fcId.longValue() < 0) {
			return null;
		}
		FrontCategoryDTO dto = new FrontCategoryDTO();
		try {
			FrontCategoryDO fcate = selectById(fcId);
			if (null == fcate) {
				return dto;
			}
			FrontCategoryLinkDO linkDO = new FrontCategoryLinkDO();
			linkDO.setFrontCategoryId(fcId);
			List<FrontCategoryLinkDO> fcLink = frontCategoryLinkDAO.selectDynamic(linkDO);
			if (fcLink.size() > 1) {
				logger.info(
						"select frontCategoryLink exception: there find two records here, the frontCategory id is : {}",
						fcId);
				throw new ServiceException("select frontCategoryLink exception: there find two records here");
			}
			if(fcate.getLevel() == 2){
				BeanUtils.copyProperties(dto, fcLink.get(0));
			}
			BeanUtils.copyProperties(dto, fcate);
			return dto;
		} catch (DAOException | IllegalAccessException | InvocationTargetException e) {
			logger.info("select frontCategoryLink exception: {}", e);
		}
		return dto;
	}

	@Override
	public String selectMaxCodeDynamic(FrontCategoryDO fcdo) {
		if (null == fcdo) {
			return null;
		}
		Integer level = fcdo.getLevel();
		if (null == level) {
			return null;
		}
		try {
			String code = frontCategoryDAO.selectMaxCodeDynamic(fcdo);
			if (StringUtils.isEmpty(code)) { // 初始化
				Long parentId = fcdo.getParentId();
				String zerone = "01";
				if (CategoryConstant.LEVEL.LARGE == level.intValue()) { // 第一级别
					return zerone;
				} else {
					String parentCode = frontCategoryDAO.selectById(parentId).getCode();
					return parentCode + zerone;
				}
			} else {
				if (CategoryConstant.LEVEL.LARGE == level) { // 第一级别
					int codeInt = Integer.parseInt(code);
					codeInt += 1;
					return codeInt < 10 ? "0" + codeInt : "" + codeInt;
				} else {
					StringBuffer buffer = new StringBuffer(code.substring(0, code.length() - 2));
					int codeInt = Integer.parseInt(code.substring(code.length() - 2));
					codeInt += 1;
					return codeInt < 10 ? buffer.append("0" + codeInt).toString() : buffer.append(codeInt).toString();
				}
			}
		} catch (DAOException e) {
			logger.info("获取前台分类code异常：{}", e);
		}
		return null;
	}

}
