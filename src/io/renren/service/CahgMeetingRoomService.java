package io.renren.service;

import io.renren.entity.CahgMeetingRoomEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-10-12 16:03:57
 */
public interface CahgMeetingRoomService {
	
	CahgMeetingRoomEntity queryObject(Integer meetingRoomId);
	
	List<CahgMeetingRoomEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgMeetingRoomEntity cahgMeetingRoom);
	
	void update(CahgMeetingRoomEntity cahgMeetingRoom);
	
	void delete(Integer meetingRoomId);
	
	void deleteBatch(Integer[] meetingRoomIds);

	void updateUsable(Integer[] meetingRoomIds);

	void updateUnusable(Integer[] meetingRoomIds);

	int queryRoomUsable();

	void updateRoomUsable();

}
