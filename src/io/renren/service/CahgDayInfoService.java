package io.renren.service;

import io.renren.entity.CahgDayInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-27 15:44:14
 */
public interface CahgDayInfoService {
	
	CahgDayInfoEntity queryObject(Integer dayId);
	
	List<CahgDayInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgDayInfoEntity cahgDayInfo);
	
	void update(CahgDayInfoEntity cahgDayInfo);
	
	void delete(Integer dayId);
	
	void deleteBatch(Integer[] dayIds);
	
	void updateFileNull(Integer[] dayId);
}
