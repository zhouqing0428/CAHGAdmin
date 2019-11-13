package io.renren.service;

import io.renren.entity.SysActivityEntity;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author
 * @email 
 * @date
 */
public interface SysActivityService {
	
	SysActivityEntity queryObject(Long activityId);
	
	List<SysActivityEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysActivityEntity sysActivity);
	
	void update(SysActivityEntity sysActivity);
	
	void delete(Long activityId);
	
	void deleteBatch(Long[] activityIds);

	LinkedHashSet<SysActivityEntity> activityList();
}
