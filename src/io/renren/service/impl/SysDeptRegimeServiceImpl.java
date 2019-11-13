package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.SysDeptRegimeDao;
import io.renren.entity.SysDeptRegimeEntity;
import io.renren.service.SysDeptRegimeService;



@Service("sysDeptRegimeService")
public class SysDeptRegimeServiceImpl implements SysDeptRegimeService {
	@Autowired
	private SysDeptRegimeDao sysDeptRegimeDao;
	
	@Override
	public SysDeptRegimeEntity queryObject(Integer regimeId){
		return sysDeptRegimeDao.queryObject(regimeId);
	}
	
	@Override
	public List<SysDeptRegimeEntity> queryList(Map<String, Object> map){
		return sysDeptRegimeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysDeptRegimeDao.queryTotal(map);
	}
	
	@Override
	public void save(SysDeptRegimeEntity sysDeptRegime){
		sysDeptRegimeDao.save(sysDeptRegime);
	}
	
	@Override
	public void update(SysDeptRegimeEntity sysDeptRegime){
		sysDeptRegimeDao.update(sysDeptRegime);
	}
	
	@Override
	public void delete(Integer regimeId){
		sysDeptRegimeDao.delete(regimeId);
	}
	
	@Override
	public void deleteBatch(Integer[] regimeIds){
		sysDeptRegimeDao.deleteBatch(regimeIds);
	}
	
}
