package io.renren.service;

import io.renren.entity.CahgMeetingNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-06 13:51:19
 */
public interface CahgMeetingNoticeService {
	
	CahgMeetingNoticeEntity queryObject(Integer meetingNoticeId);
	
	List<CahgMeetingNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgMeetingNoticeEntity cahgMeetingNotice);
	
	void update(CahgMeetingNoticeEntity cahgMeetingNotice);
	
	void delete(Integer meetingNoticeId);
	
	void deleteBatch(Integer[] meetingNoticeIds);
}
