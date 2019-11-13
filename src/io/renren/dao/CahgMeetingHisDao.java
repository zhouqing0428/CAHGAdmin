package io.renren.dao;

import io.renren.entity.CahgMeetingHisEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-23 15:13:55
 */
public interface CahgMeetingHisDao extends BaseDao<CahgMeetingHisEntity> {

	int queryMeetingApplied(CahgMeetingHisEntity cahgMeetingHis);
	
}
