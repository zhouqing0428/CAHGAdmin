package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgSpecialTopicDao;
import io.renren.entity.CahgSpecialTopicEntity;
import io.renren.service.CahgSpecialTopicService;



@Service("cahgSpecialTopicService")
public class CahgSpecialTopicServiceImpl implements CahgSpecialTopicService {
	@Autowired
	private CahgSpecialTopicDao cahgSpecialTopicDao;
	
	@Override
	public CahgSpecialTopicEntity queryObject(Integer specialTopicId){
		return cahgSpecialTopicDao.queryObject(specialTopicId);
	}
	
	@Override
	public List<CahgSpecialTopicEntity> queryList(Map<String, Object> map){
		return cahgSpecialTopicDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgSpecialTopicDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgSpecialTopicEntity cahgSpecialTopic){
		cahgSpecialTopicDao.save(cahgSpecialTopic);
	}
	
	@Override
	public void update(CahgSpecialTopicEntity cahgSpecialTopic){
		cahgSpecialTopicDao.update(cahgSpecialTopic);
	}
	
	@Override
	public void delete(Integer specialTopicId){
		cahgSpecialTopicDao.delete(specialTopicId);
	}
	
	@Override
	public void deleteBatch(Integer[] specialTopicIds){
		cahgSpecialTopicDao.deleteBatch(specialTopicIds);
	}

	@Override
	public void updateFileNull(Integer[] specialTopicIds) {
		// TODO Auto-generated method stub
		cahgSpecialTopicDao.updateFileNull(specialTopicIds);
	}
	
}
