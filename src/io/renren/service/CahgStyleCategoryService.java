package io.renren.service;

import io.renren.entity.CahgStyleCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
public interface CahgStyleCategoryService {
	
	CahgStyleCategoryEntity queryObject(Integer id);
	
	List<CahgStyleCategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgStyleCategoryEntity cahgStyleCategory);
	
	void update(CahgStyleCategoryEntity cahgStyleCategory);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

}
