package io.renren.service;

import io.renren.entity.CahgWorkTableEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-15 11:45:48
 */
public interface CahgWorkTableService {
	
	CahgWorkTableEntity queryObject(Integer workTableId);
	
	List<CahgWorkTableEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgWorkTableEntity cahgWorkTable);
	
	void update(CahgWorkTableEntity cahgWorkTable);
	
	void delete(Integer workTableId);
	
	void deleteBatch(Integer[] workTableIds);
}
