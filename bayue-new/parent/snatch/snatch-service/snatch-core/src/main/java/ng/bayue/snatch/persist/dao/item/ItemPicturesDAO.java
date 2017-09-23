package ng.bayue.snatch.persist.dao.item;

import java.util.List;

import ng.bayue.snatch.domain.item.ItemPicturesDO;
import ng.bayue.snatch.exception.DAOException;

/**
 * 商品图片信息 DAO
 * 
 * @author haisheng.Long 2016-07-13 13:17:51
 */
public interface ItemPicturesDAO {

	/**
	 * 插入 商品图片信息
	 * 
	 * @param itemPicturesDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Long insert(ItemPicturesDO itemPicturesDO) throws DAOException;

	/**
	 * 根据ID更新 商品图片信息全部属性
	 * 
	 * @param itemPicturesDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Integer update(ItemPicturesDO itemPicturesDO) throws DAOException;

	/**
	 * 根据ID删除 商品图片信息
	 * 
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 商品图片信息部分属性，包括全部
	 * 
	 * @param itemPicturesDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Integer updateDynamic(ItemPicturesDO itemPicturesDO) throws DAOException;

	/**
	 * 根据ID查询 一个 商品图片信息
	 * 
	 * @param id
	 * @return ItemPicturesDO
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	ItemPicturesDO selectById(Long id) throws DAOException;

	/**
	 * 根据 商品图片信息 动态返回记录数
	 * 
	 * @param itemPicturesDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Long selectCountDynamic(ItemPicturesDO itemPicturesDO) throws DAOException;

	/**
	 * 根据 商品图片信息 动态返回 商品图片信息 列表
	 * 
	 * @param itemPicturesDO
	 * @return List<ItemPicturesDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	List<ItemPicturesDO> selectDynamic(ItemPicturesDO itemPicturesDO) throws DAOException;

	/**
	 * 根据 商品图片信息 动态返回 商品图片信息 Limit 列表
	 * 
	 * @param itemPicturesDO
	 *            start,pageSize属性必须指定
	 * @return List<ItemPicturesDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	List<ItemPicturesDO> selectDynamicPageQuery(ItemPicturesDO itemPicturesDO) throws DAOException;

	/**
	 * <pre>
	 * 批量插入图片信息
	 * </pre>
	 *
	 * @param listPics
	 * @return
	 */
	int insertBatch(List<ItemPicturesDO> listPics);

	/**
	 * <pre>
	 * 根据detailId列表获取商品图片信息
	 * </pre>
	 *
	 * @param detailIds
	 * @return
	 */
	List<ItemPicturesDO> selectByDetailIds(List<Long> detailIds);
	
}
