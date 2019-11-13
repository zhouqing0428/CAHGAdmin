package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgDutyScheduleDao;
import io.renren.entity.CahgDutyScheduleEntity;
import io.renren.service.CahgDutyScheduleService;



@Service("cahgDutyScheduleService")
public class CahgDutyScheduleServiceImpl implements CahgDutyScheduleService {
	@Autowired
	private CahgDutyScheduleDao cahgDutyScheduleDao;
	
	@Override
	public CahgDutyScheduleEntity queryObject(Integer dutyScheduleId){
		return cahgDutyScheduleDao.queryObject(dutyScheduleId);
	}
	
	@Override
	public List<CahgDutyScheduleEntity> queryList(Map<String, Object> map){
		return cahgDutyScheduleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgDutyScheduleDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgDutyScheduleEntity cahgDutySchedule){
		cahgDutyScheduleDao.save(cahgDutySchedule);
	}
	
	@Override
	public void update(CahgDutyScheduleEntity cahgDutySchedule){
		cahgDutyScheduleDao.update(cahgDutySchedule);
	}
	
	@Override
	public void delete(Integer dutyScheduleId){
		cahgDutyScheduleDao.delete(dutyScheduleId);
	}
	
	@Override
	public void deleteBatch(Integer[] dutyScheduleIds){
		cahgDutyScheduleDao.deleteBatch(dutyScheduleIds);
	}
	
}
