package ng.bayue.snatch.persist.dao.item;

import java.util.List;

import ng.bayue.snatch.domain.item.CarouselDO;
import ng.bayue.snatch.exception.DAOException;


 /**
 * 首页幻灯片轮播 DAO
 * @author haisheng.Long 2017-03-07 11:29:32
 */
public interface CarouselDAO {

	/**
	 * 插入  首页幻灯片轮播
	 * @param carouselDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Long insert(CarouselDO carouselDO) throws DAOException;

	/**
	 * 根据ID更新 首页幻灯片轮播全部属性
	 * @param carouselDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Integer update(CarouselDO carouselDO) throws DAOException;

	/**
	 * 根据ID删除 首页幻灯片轮播
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 首页幻灯片轮播部分属性，包括全部
	 * @param carouselDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Integer updateDynamic(CarouselDO carouselDO) throws DAOException;

	/**
	 * 根据ID查询 一个 首页幻灯片轮播
	 * @param id
	 * @return CarouselDO
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	CarouselDO selectById(Long id) throws DAOException;

	/**
	 * 根据  首页幻灯片轮播 动态返回记录数
	 * @param carouselDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Long selectCountDynamic(CarouselDO carouselDO) throws DAOException;

	/**
	 * 根据  首页幻灯片轮播 动态返回 首页幻灯片轮播 列表
	 * @param carouselDO
	 * @return List<CarouselDO>
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	List<CarouselDO> selectDynamic(CarouselDO carouselDO) throws DAOException;

	/**
	 * 根据  首页幻灯片轮播 动态返回 首页幻灯片轮播 Limit 列表
	 * @param carouselDO start,pageSize属性必须指定
	 * @return List<CarouselDO>
	 * @throws DAOException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	List<CarouselDO> selectDynamicPageQuery(CarouselDO carouselDO) throws DAOException;
}
