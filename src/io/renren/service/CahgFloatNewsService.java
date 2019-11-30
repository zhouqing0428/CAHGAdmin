package io.renren.service;

import io.renren.entity.CahgFloatNewsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2019-11-21 16:54:27
 */
public interface CahgFloatNewsService {
	
	CahgFloatNewsEntity queryObject(Integer floatNewId);
	
	List<CahgFloatNewsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgFloatNewsEntity cahgFloatNews);
	
	void update(CahgFloatNewsEntity cahgFloatNews);
	
	void delete(Integer floatNewId);
	
	void deleteBatch(Integer[] floatNewIds);

	void stick(Integer floatNewId);

	void unStick();
	
	void updateStatusShow(Integer[] floatNewIds);
	void updateStatusUnShow(Integer[] floatNewIds);
	
}
