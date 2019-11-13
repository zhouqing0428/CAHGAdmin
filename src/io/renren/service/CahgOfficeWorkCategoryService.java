package io.renren.service;

import io.renren.entity.CahgOfficeWorkCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-08 16:43:42
 */
public interface CahgOfficeWorkCategoryService {
	
	CahgOfficeWorkCategoryEntity queryObject(Integer officeWorkCategoryId);
	
	List<CahgOfficeWorkCategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgOfficeWorkCategoryEntity cahgOfficeWorkCategory);
	
	void update(CahgOfficeWorkCategoryEntity cahgOfficeWorkCategory);
	
	void delete(Integer officeWorkCategoryId);
	
	void deleteBatch(Integer[] officeWorkCategoryIds);
}
