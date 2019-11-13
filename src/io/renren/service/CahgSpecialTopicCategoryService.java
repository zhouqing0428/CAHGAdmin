package io.renren.service;

import io.renren.entity.CahgSpecialTopicCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-19 17:27:39
 */
public interface CahgSpecialTopicCategoryService {
	
	CahgSpecialTopicCategoryEntity queryObject(Integer specialTopicCategoryId);
	
	List<CahgSpecialTopicCategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgSpecialTopicCategoryEntity cahgSpecialTopicCategory);
	
	void update(CahgSpecialTopicCategoryEntity cahgSpecialTopicCategory);
	
	void delete(Integer specialTopicCategoryId);
	
	void deleteBatch(Integer[] specialTopicCategoryIds);

}
