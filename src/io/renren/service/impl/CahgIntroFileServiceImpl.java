package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgIntroFileDao;
import io.renren.entity.CahgIntroFileEntity;
import io.renren.service.CahgIntroFileService;



@Service("cahgIntroFileService")
public class CahgIntroFileServiceImpl implements CahgIntroFileService {
	@Autowired
	private CahgIntroFileDao cahgIntroFileDao;
	
	@Override
	public CahgIntroFileEntity queryObject(Integer introFileId){
		return cahgIntroFileDao.queryObject(introFileId);
	}
	
	@Override
	public List<CahgIntroFileEntity> queryList(Map<String, Object> map){
		return cahgIntroFileDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgIntroFileDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgIntroFileEntity cahgIntroFile){
		cahgIntroFileDao.save(cahgIntroFile);
	}
	
	@Override
	public void update(CahgIntroFileEntity cahgIntroFile){
		cahgIntroFileDao.update(cahgIntroFile);
	}
	
	@Override
	public void delete(Integer introFileId){
		cahgIntroFileDao.delete(introFileId);
	}
	
	@Override
	public void deleteBatch(Integer[] introFileIds){
		cahgIntroFileDao.deleteBatch(introFileIds);
	}
	
}
