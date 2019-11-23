package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.SysDeptEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-23 16:05:45
 */
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

	List<SysDeptEntity> queryDeptList(Map<String, Object> map);

	SysDeptEntity queryObjectByName(String name);

	void deleteFile(Integer[] deptIds);
	
	SysDeptEntity queryListContent(Integer deptId);
	
	void updateContent(SysDeptEntity sysDept);
	
	void saveFile(SysDeptEntity deptFileId);
	
	void updateFileNull(Integer[] deptIds);
	
	String queryMaxNumber();
	
	List<SysDeptEntity> queryListByNumbers(List<String> numberList);
}
