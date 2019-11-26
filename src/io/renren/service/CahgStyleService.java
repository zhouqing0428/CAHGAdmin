package io.renren.service;

import io.renren.entity.CahgStyleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
public interface CahgStyleService {
	
	CahgStyleEntity queryObject(Integer styleId);
	
	List<CahgStyleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgStyleEntity cahgStyle);
	
	void update(CahgStyleEntity cahgStyle);
	
	void delete(Integer styleId);
	
	void deleteBatch(Integer[] styleIds);

	void stick(Integer styleId);

	void unStick();
	
	void updateStatusShow(Integer[] styleIds);
	void updateStatusUnShow(Integer[] styleIds);
}
