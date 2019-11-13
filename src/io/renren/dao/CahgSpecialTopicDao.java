package io.renren.dao;

import io.renren.entity.CahgSpecialTopicEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-20 10:13:31
 */
public interface CahgSpecialTopicDao extends BaseDao<CahgSpecialTopicEntity> {

	void updateFileNull(Integer[] specialTopicIds);
	
}
