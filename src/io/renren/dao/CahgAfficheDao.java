package io.renren.dao;

import io.renren.entity.CahgAfficheEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-06 17:50:57
 */
public interface CahgAfficheDao extends BaseDao<CahgAfficheEntity> {
	void updateFileNull(Integer[] afficheIds);
}
