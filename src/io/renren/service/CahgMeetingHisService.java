package io.renren.service;

import io.renren.entity.CahgMeetingHisEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-23 15:13:55
 */
public interface CahgMeetingHisService {
	
	CahgMeetingHisEntity queryObject(Integer meetingHisId);
	
	List<CahgMeetingHisEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgMeetingHisEntity cahgMeetingHis);
	
	void update(CahgMeetingHisEntity cahgMeetingHis);
	
	void delete(Integer meetingHisId);
	
	void deleteBatch(Integer[] meetingHisIds);

	int queryMeetingApplied(CahgMeetingHisEntity cahgMeetingHis);
}
