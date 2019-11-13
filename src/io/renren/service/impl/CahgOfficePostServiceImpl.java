package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgOfficePostDao;
import io.renren.entity.CahgOfficePostEntity;
import io.renren.service.CahgOfficePostService;



@Service("cahgOfficePostService")
public class CahgOfficePostServiceImpl implements CahgOfficePostService {
	@Autowired
	private CahgOfficePostDao cahgOfficePostDao;
	
	@Override
	public CahgOfficePostEntity queryObject(Integer officePostId){
		return cahgOfficePostDao.queryObject(officePostId);
	}
	
	@Override
	public List<CahgOfficePostEntity> queryList(Map<String, Object> map){
		return cahgOfficePostDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgOfficePostDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgOfficePostEntity cahgOfficePost){
		cahgOfficePostDao.save(cahgOfficePost);
	}
	
	@Override
	public void update(CahgOfficePostEntity cahgOfficePost){
		cahgOfficePostDao.update(cahgOfficePost);
	}
	
	@Override
	public void delete(Integer officePostId){
		cahgOfficePostDao.delete(officePostId);
	}
	
	@Override
	public void deleteBatch(Integer[] officePostIds){
		cahgOfficePostDao.deleteBatch(officePostIds);
	}
	
}
