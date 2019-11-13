package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.SysDeptDao;
import io.renren.entity.SysDeptEntity;
import io.renren.service.SysDeptService;



@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	public SysDeptEntity queryObject(Integer deptId){
		return sysDeptDao.queryObject(deptId);
	}
	
	@Override
	public List<SysDeptEntity> queryList(Map<String, Object> map){
		return sysDeptDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysDeptDao.queryTotal(map);
	}
	
	@Override
	public void save(SysDeptEntity sysDept){
		sysDeptDao.save(sysDept);
	}
	
	@Override
	public void update(SysDeptEntity sysDept){
		sysDeptDao.update(sysDept);
	}
	
	@Override
	public void delete(Integer deptId){
		sysDeptDao.delete(deptId);
	}
	
	@Override
	public void deleteBatch(Integer[] deptIds){
		sysDeptDao.deleteBatch(deptIds);
	}

	@Override
	public List<SysDeptEntity> queryDeptList() {
		// TODO Auto-generated method stub
		return sysDeptDao.queryDeptList();
	}

	@Override
	public SysDeptEntity queryObjectByName(String name) {
		// TODO Auto-generated method stub
		return sysDeptDao.queryObjectByName(name);
	}

	@Override
	public void deleteFile(Integer[] deptIds) {
		// TODO Auto-generated method stub
		sysDeptDao.deleteFile(deptIds);
	}

	@Override
	public void updateContent(SysDeptEntity sysDept) {
		sysDeptDao.updateContent(sysDept);
	}

	@Override
	public SysDeptEntity queryListContent(Integer deptId) {
		return sysDeptDao.queryListContent(deptId);
	}

	@Override
	public void saveFile(SysDeptEntity deptFileId) {
		sysDeptDao.saveFile(deptFileId);
	}

	@Override
	public void updateFileNull(Integer[] deptIds) {
		sysDeptDao.updateFileNull(deptIds);
	}
	
}
