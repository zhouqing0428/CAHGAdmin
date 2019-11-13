package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgMeetingRoomDao;
import io.renren.entity.CahgMeetingRoomEntity;
import io.renren.service.CahgMeetingRoomService;



@Service("cahgMeetingRoomService")
public class CahgMeetingRoomServiceImpl implements CahgMeetingRoomService {
	@Autowired
	private CahgMeetingRoomDao cahgMeetingRoomDao;
	
	@Override
	public CahgMeetingRoomEntity queryObject(Integer meetingRoomId){
		return cahgMeetingRoomDao.queryObject(meetingRoomId);
	}
	
	@Override
	public List<CahgMeetingRoomEntity> queryList(Map<String, Object> map){
		return cahgMeetingRoomDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgMeetingRoomDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgMeetingRoomEntity cahgMeetingRoom){
		cahgMeetingRoomDao.save(cahgMeetingRoom);
	}
	
	@Override
	public void update(CahgMeetingRoomEntity cahgMeetingRoom){
		cahgMeetingRoomDao.update(cahgMeetingRoom);
	}
	
	@Override
	public void delete(Integer meetingRoomId){
		cahgMeetingRoomDao.delete(meetingRoomId);
	}
	
	@Override
	public void deleteBatch(Integer[] meetingRoomIds){
		cahgMeetingRoomDao.deleteBatch(meetingRoomIds);
	}

	@Override
	public void updateUsable(Integer[] meetingRoomIds) {
		// TODO Auto-generated method stub
		cahgMeetingRoomDao.updateUsable(meetingRoomIds);
	}

	@Override
	public void updateUnusable(Integer[] meetingRoomIds) {
		// TODO Auto-generated method stub
		cahgMeetingRoomDao.updateUnusable(meetingRoomIds);
	}

	@Override
	public int queryRoomUsable() {
		// TODO Auto-generated method stub
		return 	cahgMeetingRoomDao.queryRoomUsable();
	}

	@Override
	public void updateRoomUsable() {
		// TODO Auto-generated method stub
		cahgMeetingRoomDao.updateRoomUsable();
	}
	
}
