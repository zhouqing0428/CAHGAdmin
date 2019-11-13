package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgImptWorkDao;
import io.renren.entity.CahgImptWorkEntity;
import io.renren.service.CahgImptWorkService;



@Service("cahgImptWorkService")
public class CahgImptWorkServiceImpl implements CahgImptWorkService {
	@Autowired
	private CahgImptWorkDao cahgImptWorkDao;
	
	@Override
	public CahgImptWorkEntity queryObject(Integer imptWorkId){
		return cahgImptWorkDao.queryObject(imptWorkId);
	}
	
	@Override
	public List<CahgImptWorkEntity> queryList(Map<String, Object> map){
		return cahgImptWorkDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgImptWorkDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgImptWorkEntity cahgImptWork){
		cahgImptWorkDao.save(cahgImptWork);
	}
	
	@Override
	public void update(CahgImptWorkEntity cahgImptWork){
		cahgImptWorkDao.update(cahgImptWork);
	}
	
	@Override
	public void delete(Integer imptWorkId){
		cahgImptWorkDao.delete(imptWorkId);
	}
	
	@Override
	public void deleteBatch(Integer[] imptWorkIds){
		cahgImptWorkDao.deleteBatch(imptWorkIds);
	}
	
}
