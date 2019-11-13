package io.renren.service;

import io.renren.entity.SysTestEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 
 */
public interface SysTestService {
	
	SysTestEntity queryObject(Long testId);
	
	List<SysTestEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysTestEntity sysTest);
	
	void update(SysTestEntity sysTest);
	
	void delete(Long testId);
	
	void deleteBatch(Long[] testIds);
}
