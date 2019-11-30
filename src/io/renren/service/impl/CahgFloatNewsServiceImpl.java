package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgFloatNewsDao;
import io.renren.entity.CahgFloatNewsEntity;
import io.renren.service.CahgFloatNewsService;
import io.renren.utils.ShiroUtils;



@Service("cahgFloatNewsService")
public class CahgFloatNewsServiceImpl implements CahgFloatNewsService {
	@Autowired
	private CahgFloatNewsDao cahgFloatNewsDao;
	
	@Override
	public CahgFloatNewsEntity queryObject(Integer floatNewId){
		return cahgFloatNewsDao.queryObject(floatNewId);
	}
	
	@Override
	public List<CahgFloatNewsEntity> queryList(Map<String, Object> map){
		return cahgFloatNewsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgFloatNewsDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgFloatNewsEntity cahgFloatNews){
		cahgFloatNews.setCreateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgFloatNewsDao.save(cahgFloatNews);
	}
	
	@Override
	public void update(CahgFloatNewsEntity cahgFloatNews){
		cahgFloatNewsDao.update(cahgFloatNews);
	}
	
	@Override
	public void delete(Integer floatNewId){
		cahgFloatNewsDao.delete(floatNewId);
	}
	
	@Override
	public void deleteBatch(Integer[] floatNewIds){
		cahgFloatNewsDao.deleteBatch(floatNewIds);
	}

	@Override
	public void stick(Integer floatNewId) {
		// TODO Auto-generated method stub
		cahgFloatNewsDao.stick(floatNewId);
	}

	@Override
	public void unStick() {
		// TODO Auto-generated method stub
		cahgFloatNewsDao.unStick();
	}
	
	@Override
	public void updateStatusShow(Integer[] floatNewIds) {
		// TODO Auto-generated method stub
		cahgFloatNewsDao.updateStatusShow(floatNewIds);
	}
	
	@Override
	public void updateStatusUnShow(Integer[] floatNewIds) {
		// TODO Auto-generated method stub
		cahgFloatNewsDao.updateStatusUnShow(floatNewIds);
	}
	
}
