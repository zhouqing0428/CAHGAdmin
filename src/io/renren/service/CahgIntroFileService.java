package io.renren.service;

import io.renren.entity.CahgIntroFileEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-20 13:45:52
 */
public interface CahgIntroFileService {
	
	CahgIntroFileEntity queryObject(Integer introFileId);
	
	List<CahgIntroFileEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgIntroFileEntity cahgIntroFile);
	
	void update(CahgIntroFileEntity cahgIntroFile);
	
	void delete(Integer introFileId);
	
	void deleteBatch(Integer[] introFileIds);
}
