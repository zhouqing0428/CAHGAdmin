package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.IpLimitEntity;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-06 17:50:57
 */
public interface IpLimitDao extends BaseDao<IpLimitEntity> {
	int queryjobCount(Map<String, Object> map);
	IpLimitEntity queryObject(Integer id);	
	void updateIP(IpLimitEntity ipLimitEntity);
}
