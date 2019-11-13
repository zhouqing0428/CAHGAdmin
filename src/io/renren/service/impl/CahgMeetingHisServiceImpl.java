package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgMeetingHisDao;
import io.renren.entity.CahgMeetingHisEntity;
import io.renren.service.CahgMeetingHisService;



@Service("cahgMeetingHisService")
public class CahgMeetingHisServiceImpl implements CahgMeetingHisService {
	@Autowired
	private CahgMeetingHisDao cahgMeetingHisDao;
	
	@Override
	public CahgMeetingHisEntity queryObject(Integer meetingHisId){
		return cahgMeetingHisDao.queryObject(meetingHisId);
	}
	
	@Override
	public List<CahgMeetingHisEntity> queryList(Map<String, Object> map){
		return cahgMeetingHisDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgMeetingHisDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgMeetingHisEntity cahgMeetingHis){
		cahgMeetingHisDao.save(cahgMeetingHis);
	}
	
	@Override
	public void update(CahgMeetingHisEntity cahgMeetingHis){
		cahgMeetingHisDao.update(cahgMeetingHis);
	}
	
	@Override
	public void delete(Integer meetingHisId){
		cahgMeetingHisDao.delete(meetingHisId);
	}
	
	@Override
	public void deleteBatch(Integer[] meetingHisIds){
		cahgMeetingHisDao.deleteBatch(meetingHisIds);
	}

	@Override
	public int queryMeetingApplied(CahgMeetingHisEntity cahgMeetingHis) {
		// TODO Auto-generated method stub
		return cahgMeetingHisDao.queryMeetingApplied(cahgMeetingHis);
	}
	
}
