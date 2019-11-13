package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgImgNewsDao;
import io.renren.entity.CahgImgNewsEntity;
import io.renren.service.CahgImgNewsService;
import io.renren.utils.ShiroUtils;



@Service("cahgImgNewsService")
public class CahgImgNewsServiceImpl implements CahgImgNewsService {
	@Autowired
	private CahgImgNewsDao cahgImgNewsDao;
	
	@Override
	public CahgImgNewsEntity queryObject(Integer imgNewId){
		return cahgImgNewsDao.queryObject(imgNewId);
	}
	
	@Override
	public List<CahgImgNewsEntity> queryList(Map<String, Object> map){
		return cahgImgNewsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgImgNewsDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgImgNewsEntity cahgImgNews){
		cahgImgNews.setCreateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgImgNewsDao.save(cahgImgNews);
	}
	
	@Override
	public void update(CahgImgNewsEntity cahgImgNews){
		cahgImgNewsDao.update(cahgImgNews);
	}
	
	@Override
	public void delete(Integer imgNewId){
		cahgImgNewsDao.delete(imgNewId);
	}
	
	@Override
	public void deleteBatch(Integer[] imgNewIds){
		cahgImgNewsDao.deleteBatch(imgNewIds);
	}

	@Override
	public void stick(Integer imgNewId) {
		// TODO Auto-generated method stub
		cahgImgNewsDao.stick(imgNewId);
	}

	@Override
	public void unStick() {
		// TODO Auto-generated method stub
		cahgImgNewsDao.unStick();
	}
	
}
