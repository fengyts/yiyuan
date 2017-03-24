package ng.bayue.item.service;

import java.util.List;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.common.Page;
import ng.bayue.item.domain.CarouselDO;
 /**
 * 首页幻灯片轮播 Service
 * @author haisheng.long 2017-03-07 11:29:32
 */
public interface CarouselService {

	/**
	 * 插入  首页幻灯片轮播
	 * @param carouselDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Long insert(CarouselDO carouselDO) throws ServiceException;

	/**
	 * 根据CarouselDO对象更新 首页幻灯片轮播
	 * @param carouselDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	int update(CarouselDO carouselDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 首页幻灯片轮播全部字段
//	 * @param carouselDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2017-03-07 11:29:32
//	 */
//	int updateById(CarouselDO carouselDO) throws ServiceException;

	/**
	 * 根据ID删除 首页幻灯片轮播
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 首页幻灯片轮播部分字段
//	 * @param carouselDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2017-03-07 11:29:32
//	 */
//	int updateDynamic(CarouselDO carouselDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 首页幻灯片轮播
	 * @param id
	 * @return CarouselDO
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	CarouselDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  首页幻灯片轮播 动态返回记录数
	 * @param carouselDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Long selectCountDynamic(CarouselDO carouselDO) throws ServiceException;

	/**
	 * 动态返回 首页幻灯片轮播 列表
	 * @param carouselDO
	 * @return List<CarouselDO>
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	List<CarouselDO> selectDynamic(CarouselDO carouselDO) throws ServiceException;

	/**
	 * 动态返回 首页幻灯片轮播 分页列表
	 * @param carouselDO
	 * @return Page<CarouselDO>
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Page<CarouselDO> queryPageListByCarouselDO(CarouselDO carouselDO);

	/**
	 * 动态返回 首页幻灯片轮播 分页列表
	 * @param carouselDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<CarouselDO>
	 * @throws ServiceException
	 * @author longhaisheng 2017-03-07 11:29:32
	 */
	Page<CarouselDO> queryPageListByCarouselDOAndStartPageSize(CarouselDO carouselDO,int startPage,int pageSize);
	
	/**
	 * <pre>
	 * 获取所有轮播图信息
	 * </pre>
	 *
	 * @return
	 */
	List<CarouselDO> getAllCarousel();

}
