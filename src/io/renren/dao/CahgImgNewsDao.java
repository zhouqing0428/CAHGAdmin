package io.renren.dao;

import io.renren.entity.CahgImgNewsEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
public interface CahgImgNewsDao extends BaseDao<CahgImgNewsEntity> {
	/*
	 * 置顶
	 */
	void stick(Integer imgNewId);

	void unStick();
	
}
