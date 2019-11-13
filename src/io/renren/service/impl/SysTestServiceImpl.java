package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.SysTestDao;
import io.renren.entity.SysTestEntity;
import io.renren.service.SysTestService;



@Service("sysTestService")
public class SysTestServiceImpl implements SysTestService {
	@Autowired
	private SysTestDao sysTestDao;
	
	@Override
	public SysTestEntity queryObject(Long testId){
		return sysTestDao.queryObject(testId);
	}
	
	@Override
	public List<SysTestEntity> queryList(Map<String, Object> map){
		return sysTestDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysTestDao.queryTotal(map);
	}
	
	@Override
	public void save(SysTestEntity sysTest){
		sysTestDao.save(sysTest);
	}
	
	@Override
	public void update(SysTestEntity sysTest){
		sysTestDao.update(sysTest);
	}
	
	@Override
	public void delete(Long testId){
		sysTestDao.delete(testId);
	}
	
	@Override
	public void deleteBatch(Long[] testIds){
		sysTestDao.deleteBatch(testIds);
	}
	
}
