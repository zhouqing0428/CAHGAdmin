package io.renren.service;

import io.renren.entity.CahgOfficePostEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-17 16:56:20
 */
public interface CahgOfficePostService {
	
	CahgOfficePostEntity queryObject(Integer officePostId);
	
	List<CahgOfficePostEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgOfficePostEntity cahgOfficePost);
	
	void update(CahgOfficePostEntity cahgOfficePost);
	
	void delete(Integer officePostId);
	
	void deleteBatch(Integer[] officePostIds);
}
