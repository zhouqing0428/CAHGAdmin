package io.renren.dao;

import io.renren.entity.CahgStyleEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
public interface CahgStyleDao extends BaseDao<CahgStyleEntity> {
	/*
	 * 置顶
	 */
	void stick(Integer styleId);

	void unStick();
	
	void updateStatusShow(Integer[] styleIds);
	
	void updateStatusUnShow(Integer[] styleIds);
	
}
