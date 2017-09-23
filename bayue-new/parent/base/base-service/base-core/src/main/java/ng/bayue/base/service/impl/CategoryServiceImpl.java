package ng.bayue.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.base.constant.CategoryConstant;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.CategoryDAO;
import ng.bayue.base.service.CategoryService;
import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

	private final static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	@Transactional
	public Long insert(CategoryDO categoryDO) throws ServiceException {
		try {
			return categoryDAO.insert(categoryDO);
		} catch (DAOException e) {
			logger.info("category insert exception:{}", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int update(CategoryDO categoryDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) categoryDAO.update(categoryDO);
			} else {
				return (Integer) categoryDAO.updateDynamic(categoryDO);
			}
		} catch (DAOException e) {
			logger.info("category update exception:{}", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) categoryDAO.deleteById(id);
		} catch (DAOException e) {
			logger.info("category deleteById exception:{}", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public CategoryDO selectById(Long id) throws ServiceException {
		try {
			return categoryDAO.selectById(id);
		} catch (DAOException e) {
			logger.info("category selectById exception:{}", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CategoryDO categoryDO) throws ServiceException {
		try {
			return categoryDAO.selectCountDynamic(categoryDO);
		} catch (DAOException e) {
			logger.info("category selectCountDynamic exception:{}", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<CategoryDO> selectDynamic(CategoryDO categoryDO) throws ServiceException {
		try {
			return categoryDAO.selectDynamic(categoryDO);
		} catch (DAOException e) {
			logger.info("category selectDynamic exception:{}", e);
			throw new ServiceException(e);
		}
	}

	private List<CategoryDO> selectDynamicPageQuery(CategoryDO categoryDO) throws ServiceException {
		try {
			return categoryDAO.selectDynamicPageQuery(categoryDO);
		} catch (DAOException e) {
			logger.info("category selectDynamicPageQuery exception:{}", e);
			throw new ServiceException(e);
		}
	}

	public Page<CategoryDO> queryPageListByCategoryDO(CategoryDO categoryDO) {
		if (categoryDO != null) {
			Long totalCount = this.selectCountDynamic(categoryDO);
			List<CategoryDO> resultList = this.selectDynamicPageQuery(categoryDO);

			Page<CategoryDO> page = new Page<CategoryDO>();
			page.setPageNo(categoryDO.getStartPage());
			page.setPageSize(categoryDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<CategoryDO>();
	}

	public Page<CategoryDO> queryPageListByCategoryDOAndStartPageSize(CategoryDO categoryDO, int startPage,
			int pageSize) {
		if (categoryDO != null && startPage > 0 && pageSize > 0) {
			categoryDO.setStartPage(startPage);
			categoryDO.setPageSize(pageSize);
			return this.queryPageListByCategoryDO(categoryDO);
		}
		return new Page<CategoryDO>();
	}

	@Override
	public String selectMaxCodeDynamic(CategoryDO categoryDO) {
		if (null == categoryDO) {
			return null;
		}
		Integer level = categoryDO.getLevel();
		if (null == level) {
			return null;
		}
		try {
			String code = categoryDAO.selectMaxCodeDynamic(categoryDO);
			if (StringUtils.isEmpty(code)) {// 初始化
				Long parentId = categoryDO.getParentId();
				String zerone = "01";
				if (CategoryConstant.LEVEL.LARGE == level.intValue()) {// 第一级别
					return zerone;
				} else {
					String parentCode = categoryDAO.selectById(parentId).getCode();
					return parentCode + zerone;
				}
			} else {
				if (CategoryConstant.LEVEL.LARGE == level) {// 第一级别
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
			logger.error("", e);
		}
		return null;
	}

	@Override
	@Transactional
	public void updateBatch(List<CategoryDO> list) throws ServiceException {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		try {
			categoryDAO.updateBatch(list);
		} catch (DAOException e) {
			logger.error("", e);
		}
	}

	@Override
	public List<CategoryDO> selectByIds(List<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return null;
		}
		try {
			return categoryDAO.selectByIds(ids);
		} catch (DAOException e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public List<CategoryDO> selectAncestors(Long cateId) {
		if (null == cateId) {
			return null;
		}
		CategoryDO cateDO = selectById(cateId);
		if (null == cateDO) {
			return null;
		}
		List<CategoryDO> result = new ArrayList<CategoryDO>();
		int level = cateDO.getLevel();
		if (level == CategoryConstant.LEVEL.MIDDLE) {
			CategoryDO parentCate = selectById(cateDO.getParentId());
			if (null != parentCate) {
				result.add(parentCate);
				result.add(cateDO);
			}
		}

		return result;
	}

}
