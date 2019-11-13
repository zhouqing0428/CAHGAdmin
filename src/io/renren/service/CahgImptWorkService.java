package io.renren.service;

import io.renren.entity.CahgImptWorkEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-19 11:17:50
 */
public interface CahgImptWorkService {
	
	CahgImptWorkEntity queryObject(Integer imptWorkId);
	
	List<CahgImptWorkEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgImptWorkEntity cahgImptWork);
	
	void update(CahgImptWorkEntity cahgImptWork);
	
	void delete(Integer imptWorkId);
	
	void deleteBatch(Integer[] imptWorkIds);
}
