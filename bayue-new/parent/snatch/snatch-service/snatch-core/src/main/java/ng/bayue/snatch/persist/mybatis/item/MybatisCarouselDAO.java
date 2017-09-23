package ng.bayue.snatch.persist.mybatis.item;
import java.util.List;

import ng.bayue.snatch.domain.item.CarouselDO;
import ng.bayue.snatch.exception.DAOException;
import ng.bayue.snatch.persist.dao.item.CarouselDAO;
import ng.bayue.snatch.persist.mybatis.MybatisBaseDAO;

import org.springframework.stereotype.Component;

@Component(value="carouselDAO")
public class MybatisCarouselDAO extends MybatisBaseDAO implements CarouselDAO {
	
	private static final String NAMESPACE = "ng.bayue.item.domain.CarouselMapper.MybatisCarouselDAO_";
	
	public Long insert(CarouselDO carouselDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", carouselDO);
		if (i > 0) {
			return Long.valueOf(carouselDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(CarouselDO carouselDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", carouselDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(CarouselDO carouselDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", carouselDO);
	}

	@Override
	public CarouselDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(CarouselDO carouselDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", carouselDO);
	}

	@Override
	public List<CarouselDO> selectDynamic(CarouselDO carouselDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", carouselDO);
	}

	@Override
	public List<CarouselDO> selectDynamicPageQuery(CarouselDO carouselDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", carouselDO);
	}

}
