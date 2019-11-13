package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgOfficeWorkDao;
import io.renren.entity.CahgOfficeWorkEntity;
import io.renren.service.CahgOfficeWorkService;



@Service("cahgOfficeWorkService")
public class CahgOfficeWorkServiceImpl implements CahgOfficeWorkService {
	@Autowired
	private CahgOfficeWorkDao cahgOfficeWorkDao;
	
	@Override
	public CahgOfficeWorkEntity queryObject(Integer officeWorkId){
		return cahgOfficeWorkDao.queryObject(officeWorkId);
	}
	
	@Override
	public List<CahgOfficeWorkEntity> queryList(Map<String, Object> map){
		return cahgOfficeWorkDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgOfficeWorkDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgOfficeWorkEntity cahgOfficeWork){
		cahgOfficeWorkDao.save(cahgOfficeWork);
	}
	
	@Override
	public void update(CahgOfficeWorkEntity cahgOfficeWork){
		cahgOfficeWorkDao.update(cahgOfficeWork);
	}
	
	@Override
	public void delete(Integer officeWorkId){
		cahgOfficeWorkDao.delete(officeWorkId);
	}
	
	@Override
	public void deleteBatch(Integer[] officeWorkIds){
		cahgOfficeWorkDao.deleteBatch(officeWorkIds);
	}
	
}
