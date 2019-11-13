package io.renren.service;

import io.renren.entity.CahgDutyScheduleEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-25 16:47:18
 */
public interface CahgDutyScheduleService {
	
	CahgDutyScheduleEntity queryObject(Integer dutyScheduleId);
	
	List<CahgDutyScheduleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgDutyScheduleEntity cahgDutySchedule);
	
	void update(CahgDutyScheduleEntity cahgDutySchedule);
	
	void delete(Integer dutyScheduleId);
	
	void deleteBatch(Integer[] dutyScheduleIds);
}
