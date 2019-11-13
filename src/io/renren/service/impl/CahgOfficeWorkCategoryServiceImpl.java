package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgOfficeWorkCategoryDao;
import io.renren.entity.CahgOfficeWorkCategoryEntity;
import io.renren.service.CahgOfficeWorkCategoryService;



@Service("cahgOfficeWorkCategoryService")
public class CahgOfficeWorkCategoryServiceImpl implements CahgOfficeWorkCategoryService {
	@Autowired
	private CahgOfficeWorkCategoryDao cahgOfficeWorkCategoryDao;
	
	@Override
	public CahgOfficeWorkCategoryEntity queryObject(Integer officeWorkCategoryId){
		return cahgOfficeWorkCategoryDao.queryObject(officeWorkCategoryId);
	}
	
	@Override
	public List<CahgOfficeWorkCategoryEntity> queryList(Map<String, Object> map){
		return cahgOfficeWorkCategoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgOfficeWorkCategoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgOfficeWorkCategoryEntity cahgOfficeWorkCategory){
		cahgOfficeWorkCategoryDao.save(cahgOfficeWorkCategory);
	}
	
	@Override
	public void update(CahgOfficeWorkCategoryEntity cahgOfficeWorkCategory){
		cahgOfficeWorkCategoryDao.update(cahgOfficeWorkCategory);
	}
	
	@Override
	public void delete(Integer officeWorkCategoryId){
		cahgOfficeWorkCategoryDao.delete(officeWorkCategoryId);
	}
	
	@Override
	public void deleteBatch(Integer[] officeWorkCategoryIds){
		cahgOfficeWorkCategoryDao.deleteBatch(officeWorkCategoryIds);
	}
	
}
