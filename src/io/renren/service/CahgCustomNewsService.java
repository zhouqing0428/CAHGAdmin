package io.renren.service;

import io.renren.entity.CahgCustomNewsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-04 10:15:43
 */
public interface CahgCustomNewsService {
	
	CahgCustomNewsEntity queryObject(Integer customNewsId);
	
	List<CahgCustomNewsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgCustomNewsEntity cahgCustomNews);
	
	void update(CahgCustomNewsEntity cahgCustomNews);
	
	void delete(Integer customNewsId);
	
	void deleteBatch(Integer[] customNewsIds);

	void updateFileNull(Integer[] customNewsIds);
}
