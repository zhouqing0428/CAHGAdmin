package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgSpecialTopicCategoryDao;
import io.renren.entity.CahgSpecialTopicCategoryEntity;
import io.renren.service.CahgSpecialTopicCategoryService;



@Service("cahgSpecialTopicCategoryService")
public class CahgSpecialTopicCategoryServiceImpl implements CahgSpecialTopicCategoryService {
	@Autowired
	private CahgSpecialTopicCategoryDao cahgSpecialTopicCategoryDao;
	
	@Override
	public CahgSpecialTopicCategoryEntity queryObject(Integer specialTopicCategoryId){
		return cahgSpecialTopicCategoryDao.queryObject(specialTopicCategoryId);
	}
	
	@Override
	public List<CahgSpecialTopicCategoryEntity> queryList(Map<String, Object> map){
		return cahgSpecialTopicCategoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgSpecialTopicCategoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgSpecialTopicCategoryEntity cahgSpecialTopicCategory){
		cahgSpecialTopicCategoryDao.save(cahgSpecialTopicCategory);
	}
	
	@Override
	public void update(CahgSpecialTopicCategoryEntity cahgSpecialTopicCategory){
		cahgSpecialTopicCategoryDao.update(cahgSpecialTopicCategory);
	}
	
	@Override
	public void delete(Integer specialTopicCategoryId){
		cahgSpecialTopicCategoryDao.delete(specialTopicCategoryId);
	}
	
	@Override
	public void deleteBatch(Integer[] specialTopicCategoryIds){
		cahgSpecialTopicCategoryDao.deleteBatch(specialTopicCategoryIds);
	}

	
}
