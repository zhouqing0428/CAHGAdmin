package io.renren.service;

import io.renren.entity.SysSmsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 
 */
public interface SysSmsService {
	
	SysSmsEntity queryObject(Long smsId);
	
	List<SysSmsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysSmsEntity sysSms);
	
	void update(SysSmsEntity sysSms);
	
	void delete(Long smsId);
	
	void deleteBatch(Long[] smsIds);
}
