package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgWorkTableCategoryDao;
import io.renren.entity.CahgWorkTableCategoryEntity;
import io.renren.service.CahgWorkTableCategoryService;



@Service("cahgWorkTableCategoryService")
public class CahgWorkTableCategoryServiceImpl implements CahgWorkTableCategoryService {
	@Autowired
	private CahgWorkTableCategoryDao cahgWorkTableCategoryDao;
	
	@Override
	public CahgWorkTableCategoryEntity queryObject(Integer workTableCategoryId){
		return cahgWorkTableCategoryDao.queryObject(workTableCategoryId);
	}
	
	@Override
	public List<CahgWorkTableCategoryEntity> queryList(Map<String, Object> map){
		return cahgWorkTableCategoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgWorkTableCategoryDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgWorkTableCategoryEntity cahgWorkTableCategory){
		cahgWorkTableCategoryDao.save(cahgWorkTableCategory);
	}
	
	@Override
	public void update(CahgWorkTableCategoryEntity cahgWorkTableCategory){
		cahgWorkTableCategoryDao.update(cahgWorkTableCategory);
	}
	
	@Override
	public void delete(Integer workTableCategoryId){
		cahgWorkTableCategoryDao.delete(workTableCategoryId);
	}
	
	@Override
	public void deleteBatch(Integer[] workTableCategoryIds){
		cahgWorkTableCategoryDao.deleteBatch(workTableCategoryIds);
	}

	@Override
	public List<CahgWorkTableCategoryEntity> queryAllList() {
		// TODO Auto-generated method stub
		return cahgWorkTableCategoryDao.queryAllList();
	}
	
}
