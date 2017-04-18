package ng.bayue.promotion.service;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.promotion.dto.TopicDTO;
import ng.bayue.promotion.dto.TopicItemDTO;

/**
 * <pre>
 * 对外模块提供接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: TopicExportService.java, v 0.1 2017年3月8日 下午1:54:42 lenovopc Exp
 *          $
 */
public interface TopicExportService {

	/**
	 * <pre>
	 * 为app首页获取所有专题接口,显示有效专题
	 * </pre>
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<TopicDTO> queryTopicListByPage(Integer pageNo, Integer pageSize);

	/**
	 * <pre>
	 * 分页获取某一个专题下面的所有商品
	 * </pre>
	 *
	 * @param topicId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<TopicItemDTO> queryTopicItemByTopicId(Long topicId, Integer pageNo, Integer pageSize);

	/**
	 * <pre>
	 * 获取热销商品列表
	 * </pre>
	 *
	 * @param pageSize
	 * @return
	 */
	List<TopicItemDTO> listItemHot(Integer pageNo, Integer pageSize);

	/**
	 * <pre>
	 * 分页获取热销商品
	 * </pre>
	 *
	 * @param pageSize
	 * @return
	 */
	Page<TopicItemDTO> queryPageListItemHot(Integer pageNo, Integer pageSize);

}
