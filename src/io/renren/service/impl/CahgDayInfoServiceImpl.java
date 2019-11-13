package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgDayInfoDao;
import io.renren.entity.CahgDayInfoEntity;
import io.renren.service.CahgDayInfoService;



@Service("cahgDayInfoService")
public class CahgDayInfoServiceImpl implements CahgDayInfoService {
	@Autowired
	private CahgDayInfoDao cahgDayInfoDao;
	
	@Override
	public CahgDayInfoEntity queryObject(Integer dayId){
		return cahgDayInfoDao.queryObject(dayId);
	}
	
	@Override
	public List<CahgDayInfoEntity> queryList(Map<String, Object> map){
		return cahgDayInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgDayInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgDayInfoEntity cahgDayInfo){
		cahgDayInfoDao.save(cahgDayInfo);
	}
	
	@Override
	public void update(CahgDayInfoEntity cahgDayInfo){
		cahgDayInfoDao.update(cahgDayInfo);
	}
	
	@Override
	public void delete(Integer dayId){
		cahgDayInfoDao.delete(dayId);
	}
	
	@Override
	public void deleteBatch(Integer[] dayIds){
		cahgDayInfoDao.deleteBatch(dayIds);
	}

	@Override
	public void updateFileNull(Integer[] dayId) {
		cahgDayInfoDao.updateFileNull(dayId);
	}
	
}
