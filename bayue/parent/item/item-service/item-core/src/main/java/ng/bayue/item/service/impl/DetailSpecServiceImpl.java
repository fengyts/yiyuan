package ng.bayue.item.service.impl;

import java.util.List;

import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.DetailSpecDAO;
import ng.bayue.item.service.DetailSpecService;
import ng.bayue.util.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="detailSpecService")
public class DetailSpecServiceImpl  implements DetailSpecService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DetailSpecDAO detailSpecDAO;

	@Override
	public Long insert(DetailSpecDO detailSpecDO) throws ServiceException {
		try {
			return detailSpecDAO.insert(detailSpecDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(DetailSpecDO detailSpecDO) throws ServiceException {
//		try {
//			return (Integer) detailSpecDAO.updateById(detailSpecDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(DetailSpecDO detailSpecDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) detailSpecDAO.update(detailSpecDO);
			}else{
				return (Integer) detailSpecDAO.updateDynamic(detailSpecDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) detailSpecDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(DetailSpecDO detailSpecDO) throws ServiceException {
//		try {
//			return (Integer) detailSpecDAO.updateDynamic(detailSpecDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public DetailSpecDO selectById(Long id) throws ServiceException {
		try {
			return detailSpecDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DetailSpecDO detailSpecDO) throws ServiceException {
		try {
			return detailSpecDAO.selectCountDynamic(detailSpecDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<DetailSpecDO> selectDynamic(DetailSpecDO detailSpecDO) throws ServiceException {
		try {
			return detailSpecDAO.selectDynamic(detailSpecDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<DetailSpecDO> selectDynamicPageQuery(DetailSpecDO detailSpecDO) throws ServiceException {
		try {
			return detailSpecDAO.selectDynamicPageQuery(detailSpecDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<DetailSpecDO> queryPageListByDetailSpecDO(DetailSpecDO detailSpecDO) {
		if (detailSpecDO != null) {
			Long totalCount = this.selectCountDynamic(detailSpecDO);
			List<DetailSpecDO> resultList = this.selectDynamicPageQuery(detailSpecDO);

			Page<DetailSpecDO> page = new Page<DetailSpecDO>();
			page.setPageNo(detailSpecDO.getStartPage());
			page.setPageSize(detailSpecDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<DetailSpecDO>();
	}

	public Page<DetailSpecDO> queryPageListByDetailSpecDOAndStartPageSize(DetailSpecDO detailSpecDO,int startPage,int pageSize){
		if (detailSpecDO != null && startPage>0 && pageSize>0) {
			detailSpecDO.setStartPage(startPage);
			detailSpecDO.setPageSize(pageSize);
			return this.queryPageListByDetailSpecDO(detailSpecDO);
		}
		return new Page<DetailSpecDO>();
	}

	@Override
	public int insertBatch(List<DetailSpecDO> list) throws ServiceException {
		if(CollectionUtils.isEmpty(list)){
			return -1;
		}
		try {
			int res = detailSpecDAO.insertBatch(list);
			return res < 1 ? -1 : res;
		} catch (DAOException e) {
			logger.error("批量插入商品关联规格组信息异常", e);
		}
		return -1;
	}

	@Override
	public int deleteByDetailId(Long detailId) throws ServiceException {
		if(null == detailId){
			return -1;
		}
		try {
			return detailSpecDAO.deleteByDetailId(detailId);
		} catch (DAOException e) {
			logger.error("删除商品关联规格组信息异常", e);
		}
		return -1;
	}

}
