package io.renren.service;

import io.renren.entity.CahgLetterAttentiveEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-11-24 10:35:30
 */
public interface CahgLetterAttentiveService {
	
	CahgLetterAttentiveEntity queryObject(Integer letterAttentiveId);
	
	List<CahgLetterAttentiveEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgLetterAttentiveEntity cahgLetterAttentive);
	
	void update(CahgLetterAttentiveEntity cahgLetterAttentive);
	
	void delete(Integer letterAttentiveId);
	
	void deleteBatch(Integer[] letterAttentiveIds);
}
