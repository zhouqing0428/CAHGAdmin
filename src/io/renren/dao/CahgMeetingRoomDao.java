package io.renren.dao;

import io.renren.entity.CahgMeetingRoomEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-10-12 16:03:57
 */
public interface CahgMeetingRoomDao extends BaseDao<CahgMeetingRoomEntity> {

	void updateUsable(Integer[] meetingRoomIds);

	void updateUnusable(Integer[] meetingRoomIds);

	int queryRoomUsable();

	void updateRoomUsable();

}
