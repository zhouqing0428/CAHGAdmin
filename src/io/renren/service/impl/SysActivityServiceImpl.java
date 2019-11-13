package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import io.renren.dao.SysActivityDao;
import io.renren.entity.SysActivityEntity;
import io.renren.service.SysActivityService;



@Service("sysActivityService")
public class SysActivityServiceImpl implements SysActivityService {
	@Autowired
	private SysActivityDao sysActivityDao;
	
	@Override
	public SysActivityEntity queryObject(Long activityId){
		return sysActivityDao.queryObject(activityId);
	}
	
	@Override
	public List<SysActivityEntity> queryList(Map<String, Object> map){
		return sysActivityDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysActivityDao.queryTotal(map);
	}
	
	@Override
	public void save(SysActivityEntity sysActivity){
		sysActivityDao.save(sysActivity);
	}
	
	@Override
	public void update(SysActivityEntity sysActivity){
		sysActivityDao.update(sysActivity);
	}
	
	@Override
	public void delete(Long activityId){
		sysActivityDao.delete(activityId);
	}
	
	@Override
	public void deleteBatch(Long[] activityIds){
		sysActivityDao.deleteBatch(activityIds);
	}

	@Override
	public LinkedHashSet<SysActivityEntity> activityList() {
		// TODO Auto-generated method stub
		return sysActivityDao.activityList();
	}
	
}
