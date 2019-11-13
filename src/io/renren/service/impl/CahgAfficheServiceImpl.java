package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgAfficheDao;
import io.renren.entity.CahgAfficheEntity;
import io.renren.service.CahgAfficheService;



@Service("cahgAfficheService")
public class CahgAfficheServiceImpl implements CahgAfficheService {
	@Autowired
	private CahgAfficheDao cahgAfficheDao;
	
	@Override
	public CahgAfficheEntity queryObject(Integer afficheId){
		return cahgAfficheDao.queryObject(afficheId);
	}
	
	@Override
	public List<CahgAfficheEntity> queryList(Map<String, Object> map){
		return cahgAfficheDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgAfficheDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgAfficheEntity cahgAffiche){
		cahgAfficheDao.save(cahgAffiche);
	}
	
	@Override
	public void update(CahgAfficheEntity cahgAffiche){
		cahgAfficheDao.update(cahgAffiche);
	}
	
	@Override
	public void delete(Integer afficheId){
		cahgAfficheDao.delete(afficheId);
	}
	
	@Override
	public void deleteBatch(Integer[] afficheIds){
		cahgAfficheDao.deleteBatch(afficheIds);
	}

	@Override
	public void updateFileNull(Integer[] afficheIds) {
		cahgAfficheDao.updateFileNull(afficheIds);
	}
	
}
