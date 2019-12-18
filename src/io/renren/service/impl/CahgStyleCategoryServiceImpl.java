package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgStyleCategoryDao;
import io.renren.entity.CahgStyleCategoryEntity;
import io.renren.service.CahgStyleCategoryService;



@Service("cahgStyleCategoryService")
public class CahgStyleCategoryServiceImpl implements CahgStyleCategoryService {
	@Autowired
	private CahgStyleCategoryDao cahgStyleCategoryDao;
	
	@Override
	public CahgStyleCategoryEntity queryObject(Integer id){
		return cahgStyleCategoryDao.queryObject(id);
	}
	
	@Override
	public List<CahgStyleCategoryEntity> queryList(Map<String, Object> map){
		return cahgStyleCategoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgStyleCategoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgStyleCategoryEntity cahgStyleCategory){
		cahgStyleCategoryDao.save(cahgStyleCategory);
	}
	
	@Override
	public void update(CahgStyleCategoryEntity cahgStyleCategory){
		cahgStyleCategoryDao.update(cahgStyleCategory);
	}
	
	@Override
	public void delete(Integer id){
		cahgStyleCategoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		cahgStyleCategoryDao.deleteBatch(ids);
	}

}
