package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.CahgJobEntity;
import io.renren.entity.JobDetailEntity;
import io.renren.entity.JobFlow;
import io.renren.entity.SysUserEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-11 21:19:36
 */
public interface CahgJobDao extends BaseDao<CahgJobEntity> {
	
	int saveAndGetKey(CahgJobEntity job);
	
	CahgJobEntity saveAndEntity(CahgJobEntity job);
	
	void saveJobFlow(JobFlow jobFlow);

	List<JobDetailEntity> quryJobDetailList(Map map);

	SysUserEntity queryFirstFlowUser(int cahgJobId);

	Map queryUserJobStatus(Map<String, Object> map);

	void saveJobDetail(JobDetailEntity jobDetail);

	void updateJobFlowStatus(Map<String, Object> map);

	void updateJobStatus(Map<String, Object> map);

	void updateNextJobFlowStatus(Map<String, Object> map);

	void updateAllJobFlowStatu(Map<String, Object> map);

	int queryjobCount(Map<String, Object> map);

	int queryPersonTotal(Map<String, Object> map);

	void finish(CahgJobEntity cahgJob);
	
	void updateFileNull(Integer[] jobIds);
}
