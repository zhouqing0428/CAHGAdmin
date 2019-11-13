package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgIntroDao;
import io.renren.entity.CahgIntroEntity;
import io.renren.service.CahgIntroService;



@Service("cahgIntroService")
public class CahgIntroServiceImpl implements CahgIntroService {
	@Autowired
	private CahgIntroDao cahgIntroDao;
	
	@Override
	public CahgIntroEntity queryObject(Integer introId){
		return cahgIntroDao.queryObject(introId);
	}
	
	@Override
	public List<CahgIntroEntity> queryList(Map<String, Object> map){
		return cahgIntroDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgIntroDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgIntroEntity cahgIntro){
		cahgIntroDao.save(cahgIntro);
	}
	
	@Override
	public void update(CahgIntroEntity cahgIntro){
		cahgIntroDao.update(cahgIntro);
	}
	
	@Override
	public void delete(Integer introId){
		cahgIntroDao.delete(introId);
	}
	
	@Override
	public void deleteBatch(Integer[] introIds){
		cahgIntroDao.deleteBatch(introIds);
	}
	
}
