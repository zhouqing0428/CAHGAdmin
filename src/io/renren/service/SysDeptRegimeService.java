package io.renren.service;

import io.renren.entity.SysDeptRegimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-13 17:49:11
 */
public interface SysDeptRegimeService {
	
	SysDeptRegimeEntity queryObject(Integer regimeId);
	
	List<SysDeptRegimeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysDeptRegimeEntity sysDeptRegime);
	
	void update(SysDeptRegimeEntity sysDeptRegime);
	
	void delete(Integer regimeId);
	
	void deleteBatch(Integer[] regimeIds);
}
