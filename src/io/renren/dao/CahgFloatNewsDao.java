package io.renren.dao;

import io.renren.entity.CahgFloatNewsEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2019-11-21 16:54:27
 */
public interface CahgFloatNewsDao extends BaseDao<CahgFloatNewsEntity> {
	/*
	 * 置顶
	 */
	void stick(Integer floatNewId);
	
	void unStick();
	
	void updateStatusShow(Integer[] floatNewIds);
	
	void updateStatusUnShow(Integer[] floatNewIds);
	
}
