package io.renren.service;

import io.renren.entity.CahgCommonFormsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-24 17:08:54
 */
public interface CahgCommonFormsService {
	
	CahgCommonFormsEntity queryObject(Integer commonFormsId);
	
	List<CahgCommonFormsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgCommonFormsEntity cahgCommonForms);
	
	void update(CahgCommonFormsEntity cahgCommonForms);
	
	void delete(Integer commonFormsId);
	
	void deleteBatch(Integer[] commonFormsIds);
}
