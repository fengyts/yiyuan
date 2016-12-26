package ng.bayue.promotion.persist.mybatis;


import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import ng.bayue.promotion.persist.BaseDAO;

public class MybatisBaseDAO extends SqlSessionDaoSupport implements BaseDAO{
	
	@Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
      		super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	
}