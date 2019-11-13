package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgWorkTableDao;
import io.renren.entity.CahgWorkTableEntity;
import io.renren.service.CahgWorkTableService;



@Service("cahgWorkTableService")
public class CahgWorkTableServiceImpl implements CahgWorkTableService {
	@Autowired
	private CahgWorkTableDao cahgWorkTableDao;
	
	@Override
	public CahgWorkTableEntity queryObject(Integer workTableId){
		return cahgWorkTableDao.queryObject(workTableId);
	}
	
	@Override
	public List<CahgWorkTableEntity> queryList(Map<String, Object> map){
		return cahgWorkTableDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cahgWorkTableDao.queryTotal(map);
	}
	
	@Override
	public void save(CahgWorkTableEntity cahgWorkTable){
		cahgWorkTableDao.save(cahgWorkTable);
	}
	
	@Override
	public void update(CahgWorkTableEntity cahgWorkTable){
		cahgWorkTableDao.update(cahgWorkTable);
	}
	
	@Override
	public void delete(Integer workTableId){
		cahgWorkTableDao.delete(workTableId);
	}
	
	@Override
	public void deleteBatch(Integer[] workTableIds){
		cahgWorkTableDao.deleteBatch(workTableIds);
	}
	
}
