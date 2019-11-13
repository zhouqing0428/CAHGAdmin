package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgLetterAttentiveDao;
import io.renren.entity.CahgLetterAttentiveEntity;
import io.renren.service.CahgLetterAttentiveService;



@Service("cahgLetterAttentiveService")
public class CahgLetterAttentiveServiceImpl implements CahgLetterAttentiveService {
	@Autowired
	private CahgLetterAttentiveDao cahgLetterAttentiveDao;
	
	@Override
	public CahgLetterAttentiveEntity queryObject(Integer letterAttentiveId){
		return cahgLetterAttentiveDao.queryObject(letterAttentiveId);
	}
	
	@Override
	public List<CahgLetterAttentiveEntity> queryList(Map<String, Object> map){
		return cahgLetterAttentiveDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgLetterAttentiveDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgLetterAttentiveEntity cahgLetterAttentive){
		cahgLetterAttentiveDao.save(cahgLetterAttentive);
	}
	
	@Override
	public void update(CahgLetterAttentiveEntity cahgLetterAttentive){
		cahgLetterAttentiveDao.update(cahgLetterAttentive);
	}
	
	@Override
	public void delete(Integer letterAttentiveId){
		cahgLetterAttentiveDao.delete(letterAttentiveId);
	}
	
	@Override
	public void deleteBatch(Integer[] letterAttentiveIds){
		cahgLetterAttentiveDao.deleteBatch(letterAttentiveIds);
	}
	
}
