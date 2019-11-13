package io.renren.dao;

import io.renren.entity.CahgCustomNewsEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-04 10:15:43
 */
public interface CahgCustomNewsDao extends BaseDao<CahgCustomNewsEntity> {

	void updateFileNull(Integer[] customNewsIds);
	}
