package io.renren.service;

import io.renren.entity.CahgDutyInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-26 14:48:27
 */
public interface CahgDutyInfoService {
	
	CahgDutyInfoEntity queryObject(Integer dutyInfoId);
	
	List<CahgDutyInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgDutyInfoEntity cahgDutyInfo);
	
	void update(CahgDutyInfoEntity cahgDutyInfo);
	
	void delete(Integer dutyInfoId);
	
	void deleteBatch(Integer[] dutyInfoIds);
}
