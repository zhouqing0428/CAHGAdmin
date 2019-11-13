package io.renren.service;

import io.renren.entity.CahgIntroEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-20 13:45:52
 */
public interface CahgIntroService {
	
	CahgIntroEntity queryObject(Integer introId);
	
	List<CahgIntroEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgIntroEntity cahgIntro);
	
	void update(CahgIntroEntity cahgIntro);
	
	void delete(Integer introId);
	
	void deleteBatch(Integer[] introIds);
}
