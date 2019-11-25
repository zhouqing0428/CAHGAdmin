package io.renren.service;

import io.renren.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-23 16:05:45
 */
public interface SysDeptService {
	
	SysDeptEntity queryObject(Integer deptId);
	
	List<SysDeptEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysDeptEntity sysDept);
	
	void update(SysDeptEntity sysDept);
	
	void delete(Integer deptId);
	
	void deleteBatch(Integer[] deptIds);

	List<SysDeptEntity> queryDeptList(Map<String, Object> map);
	
	SysDeptEntity queryObjectByName(String name);

	void deleteFile(Integer[] deptIds);
	
	SysDeptEntity queryListContent(Integer deptId);

	void updateContent(SysDeptEntity sysDept);

	void saveFile(SysDeptEntity deptFileId);

	void updateFileNull(Integer[] deptIds);
	
	String queryMaxNumber();
	
	//根据科室编码批量查科室集合
	List<SysDeptEntity> queryListByNumbers(List<String> numberList);
}
