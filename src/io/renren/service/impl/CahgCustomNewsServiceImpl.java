package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgCustomNewsDao;
import io.renren.entity.CahgCustomNewsEntity;
import io.renren.service.CahgCustomNewsService;



@Service("cahgCustomNewsService")
public class CahgCustomNewsServiceImpl implements CahgCustomNewsService {
	@Autowired
	private CahgCustomNewsDao cahgCustomNewsDao;
	
	@Override
	public CahgCustomNewsEntity queryObject(Integer customNewsId){
		return cahgCustomNewsDao.queryObject(customNewsId);
	}
	
	@Override
	public List<CahgCustomNewsEntity> queryList(Map<String, Object> map){
		return cahgCustomNewsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgCustomNewsDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgCustomNewsEntity cahgCustomNews){
		cahgCustomNewsDao.save(cahgCustomNews);
	}
	
	@Override
	public void update(CahgCustomNewsEntity cahgCustomNews){
		cahgCustomNewsDao.update(cahgCustomNews);
	}
	
	@Override
	public void delete(Integer customNewsId){
		cahgCustomNewsDao.delete(customNewsId);
	}
	
	@Override
	public void deleteBatch(Integer[] customNewsIds){
		cahgCustomNewsDao.deleteBatch(customNewsIds);
	}

	@Override
	public void updateFileNull(Integer[] customNewsIds) {
		cahgCustomNewsDao.updateFileNull(customNewsIds);
	}
	
}
