package io.renren.service;

import io.renren.entity.CahgJobEntity;
import io.renren.entity.IpLimitEntity;

import java.util.List;
import java.util.Map;



/**
 * 
 * 
 */
public interface IpLimitService {
	List<IpLimitEntity> queryList(Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	IpLimitEntity queryObject(Integer ipLimitId);
	void updateIP(IpLimitEntity ipLimitEntity);
}
