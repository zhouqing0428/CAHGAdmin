package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgDutyInfoDao;
import io.renren.entity.CahgDutyInfoEntity;
import io.renren.service.CahgDutyInfoService;



@Service("cahgDutyInfoService")
public class CahgDutyInfoServiceImpl implements CahgDutyInfoService {
	@Autowired
	private CahgDutyInfoDao cahgDutyInfoDao;
	
	@Override
	public CahgDutyInfoEntity queryObject(Integer dutyInfoId){
		return cahgDutyInfoDao.queryObject(dutyInfoId);
	}
	
	@Override
	public List<CahgDutyInfoEntity> queryList(Map<String, Object> map){
		return cahgDutyInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgDutyInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgDutyInfoEntity cahgDutyInfo){
		cahgDutyInfoDao.save(cahgDutyInfo);
	}
	
	@Override
	public void update(CahgDutyInfoEntity cahgDutyInfo){
		cahgDutyInfoDao.update(cahgDutyInfo);
	}
	
	@Override
	public void delete(Integer dutyInfoId){
		cahgDutyInfoDao.delete(dutyInfoId);
	}
	
	@Override
	public void deleteBatch(Integer[] dutyInfoIds){
		cahgDutyInfoDao.deleteBatch(dutyInfoIds);
	}
	
}
