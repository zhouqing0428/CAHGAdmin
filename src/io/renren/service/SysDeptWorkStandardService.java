package io.renren.service;

import io.renren.entity.SysDeptWorkStandardEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-14 14:58:24
 */
public interface SysDeptWorkStandardService {
	
	SysDeptWorkStandardEntity queryObject(Integer workStandardId);
	
	List<SysDeptWorkStandardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysDeptWorkStandardEntity sysDeptWorkStandard);
	
	void update(SysDeptWorkStandardEntity sysDeptWorkStandard);
	
	void delete(Integer workStandardId);
	
	void deleteBatch(Integer[] workStandardIds);
}
