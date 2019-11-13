package io.renren.service;

import io.renren.entity.CahgSpecialTopicEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-20 10:13:31
 */
public interface CahgSpecialTopicService {
	
	CahgSpecialTopicEntity queryObject(Integer specialTopicId);
	
	List<CahgSpecialTopicEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgSpecialTopicEntity cahgSpecialTopic);
	
	void update(CahgSpecialTopicEntity cahgSpecialTopic);
	
	void delete(Integer specialTopicId);
	
	void deleteBatch(Integer[] specialTopicIds);

	void updateFileNull(Integer[] specialTopicIds);
}
