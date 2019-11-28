package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.entity.CahgViolationEntity;

public interface CahgViolationService {

	CahgViolationEntity queryObject(Integer id);
	
	List<CahgViolationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgViolationEntity info);
	
	void update(CahgViolationEntity info);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	void updateFileNull(Integer[] ids);
}
