package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgCommonFormsDao;
import io.renren.entity.CahgCommonFormsEntity;
import io.renren.service.CahgCommonFormsService;



@Service("cahgCommonFormsService")
public class CahgCommonFormsServiceImpl implements CahgCommonFormsService {
	@Autowired
	private CahgCommonFormsDao cahgCommonFormsDao;
	
	@Override
	public CahgCommonFormsEntity queryObject(Integer commonFormsId){
		return cahgCommonFormsDao.queryObject(commonFormsId);
	}
	
	@Override
	public List<CahgCommonFormsEntity> queryList(Map<String, Object> map){
		return cahgCommonFormsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgCommonFormsDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgCommonFormsEntity cahgCommonForms){
		cahgCommonFormsDao.save(cahgCommonForms);
	}
	
	@Override
	public void update(CahgCommonFormsEntity cahgCommonForms){
		cahgCommonFormsDao.update(cahgCommonForms);
	}
	
	@Override
	public void delete(Integer commonFormsId){
		cahgCommonFormsDao.delete(commonFormsId);
	}
	
	@Override
	public void deleteBatch(Integer[] commonFormsIds){
		cahgCommonFormsDao.deleteBatch(commonFormsIds);
	}
	
}
