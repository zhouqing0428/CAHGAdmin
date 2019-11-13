package io.renren.dao;

import java.util.LinkedHashSet;
import java.util.List;

import io.renren.entity.SysActivityEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-04-17 17:34:32
 */
public interface SysActivityDao extends BaseDao<SysActivityEntity> {

	LinkedHashSet<SysActivityEntity> activityList();
	
}
