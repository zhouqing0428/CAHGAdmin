package io.renren.service;

import io.renren.entity.CahgWorkTableCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-15 11:46:04
 */
public interface CahgWorkTableCategoryService {
	
	CahgWorkTableCategoryEntity queryObject(Integer workTableCategoryId);
	
	List<CahgWorkTableCategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgWorkTableCategoryEntity cahgWorkTableCategory);
	
	void update(CahgWorkTableCategoryEntity cahgWorkTableCategory);
	
	void delete(Integer workTableCategoryId);
	
	void deleteBatch(Integer[] workTableCategoryIds);

	List<CahgWorkTableCategoryEntity> queryAllList();
}
