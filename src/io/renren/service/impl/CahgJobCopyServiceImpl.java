package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgJobCopyDao;
import io.renren.entity.CahgJobCopyEntity;
import io.renren.service.CahgJobCopyService;



@Service("cahgJobCopyService")
public class CahgJobCopyServiceImpl implements CahgJobCopyService {
	@Autowired
	private CahgJobCopyDao cahgJobCopyDao;
	
	@Override
	public CahgJobCopyEntity queryObject(Integer jobCopyId){
		return cahgJobCopyDao.queryObject(jobCopyId);
	}
	
	@Override
	public List<CahgJobCopyEntity> queryList(Map<String, Object> map){
		return cahgJobCopyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgJobCopyDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgJobCopyEntity cahgJobCopy){
		cahgJobCopyDao.save(cahgJobCopy);
	}
	
	@Override
	public void update(CahgJobCopyEntity cahgJobCopy){
		cahgJobCopyDao.update(cahgJobCopy);
	}
	
	@Override
	public void delete(Integer jobCopyId){
		cahgJobCopyDao.delete(jobCopyId);
	}
	
	@Override
	public void deleteBatch(Integer[] jobCopyIds){
		cahgJobCopyDao.deleteBatch(jobCopyIds);
	}
	
}
