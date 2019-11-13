package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgMeetingNoticeDao;
import io.renren.entity.CahgMeetingNoticeEntity;
import io.renren.service.CahgMeetingNoticeService;



@Service("cahgMeetingNoticeService")
public class CahgMeetingNoticeServiceImpl implements CahgMeetingNoticeService {
	@Autowired
	private CahgMeetingNoticeDao cahgMeetingNoticeDao;
	
	@Override
	public CahgMeetingNoticeEntity queryObject(Integer meetingNoticeId){
		return cahgMeetingNoticeDao.queryObject(meetingNoticeId);
	}
	
	@Override
	public List<CahgMeetingNoticeEntity> queryList(Map<String, Object> map){
		return cahgMeetingNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgMeetingNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgMeetingNoticeEntity cahgMeetingNotice){
		cahgMeetingNoticeDao.save(cahgMeetingNotice);
	}
	
	@Override
	public void update(CahgMeetingNoticeEntity cahgMeetingNotice){
		cahgMeetingNoticeDao.update(cahgMeetingNotice);
	}
	
	@Override
	public void delete(Integer meetingNoticeId){
		cahgMeetingNoticeDao.delete(meetingNoticeId);
	}
	
	@Override
	public void deleteBatch(Integer[] meetingNoticeIds){
		cahgMeetingNoticeDao.deleteBatch(meetingNoticeIds);
	}
	
}
