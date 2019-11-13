package io.renren.dao;

import io.renren.entity.CahgDayInfoEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-27 15:44:14
 */
public interface CahgDayInfoDao extends BaseDao<CahgDayInfoEntity> {
	
	void updateFileNull(Integer[] dayId);
	
}
