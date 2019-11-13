package io.renren.service;

import io.renren.entity.CahgJobCopyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-21 15:34:27
 */
public interface CahgJobCopyService {
	
	CahgJobCopyEntity queryObject(Integer jobCopyId);
	
	List<CahgJobCopyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgJobCopyEntity cahgJobCopy);
	
	void update(CahgJobCopyEntity cahgJobCopy);
	
	void delete(Integer jobCopyId);
	
	void deleteBatch(Integer[] jobCopyIds);
}
