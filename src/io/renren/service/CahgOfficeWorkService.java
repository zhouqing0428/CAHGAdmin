package io.renren.service;

import io.renren.entity.CahgOfficeWorkEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-08 17:26:39
 */
public interface CahgOfficeWorkService {
	
	CahgOfficeWorkEntity queryObject(Integer officeWorkId);
	
	List<CahgOfficeWorkEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgOfficeWorkEntity cahgOfficeWork);
	
	void update(CahgOfficeWorkEntity cahgOfficeWork);
	
	void delete(Integer officeWorkId);
	
	void deleteBatch(Integer[] officeWorkIds);
}
