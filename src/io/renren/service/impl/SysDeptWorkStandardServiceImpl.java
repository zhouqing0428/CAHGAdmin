package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.SysDeptWorkStandardDao;
import io.renren.entity.SysDeptWorkStandardEntity;
import io.renren.service.SysDeptWorkStandardService;



@Service("sysDeptWorkStandardService")
public class SysDeptWorkStandardServiceImpl implements SysDeptWorkStandardService {
	@Autowired
	private SysDeptWorkStandardDao sysDeptWorkStandardDao;
	
	@Override
	public SysDeptWorkStandardEntity queryObject(Integer workStandardId){
		return sysDeptWorkStandardDao.queryObject(workStandardId);
	}
	
	@Override
	public List<SysDeptWorkStandardEntity> queryList(Map<String, Object> map){
		return sysDeptWorkStandardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysDeptWorkStandardDao.queryTotal(map);
	}
	
	@Override
	public void save(SysDeptWorkStandardEntity sysDeptWorkStandard){
		sysDeptWorkStandardDao.save(sysDeptWorkStandard);
	}
	
	@Override
	public void update(SysDeptWorkStandardEntity sysDeptWorkStandard){
		sysDeptWorkStandardDao.update(sysDeptWorkStandard);
	}
	
	@Override
	public void delete(Integer workStandardId){
		sysDeptWorkStandardDao.delete(workStandardId);
	}
	
	@Override
	public void deleteBatch(Integer[] workStandardIds){
		sysDeptWorkStandardDao.deleteBatch(workStandardIds);
	}
	
}
