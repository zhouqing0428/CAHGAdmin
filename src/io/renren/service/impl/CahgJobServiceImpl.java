package io.renren.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.CahgJobDao;
import io.renren.entity.CahgJobEntity;
import io.renren.entity.JobDetailEntity;
import io.renren.entity.JobFlow;
import io.renren.entity.SysUserEntity;
import io.renren.service.CahgJobService;
import io.renren.utils.ShiroUtils;

@Service("cahgJobService")
public class CahgJobServiceImpl implements CahgJobService {
	@Autowired
	private CahgJobDao cahgJobDao;

	@Override
	public CahgJobEntity queryObject(Integer jobId) {
		return cahgJobDao.queryObject(jobId);
	}

	@Override
	public List<CahgJobEntity> queryList(Map<String, Object> map) {
		return cahgJobDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return cahgJobDao.queryTotal(map);
	}

	@Override
	public void save(CahgJobEntity cahgJob) {
		cahgJob.setCreateUserId(ShiroUtils.getUserId());
		cahgJob.setStatus(0);
		cahgJobDao.saveAndGetKey(cahgJob);
	}

	@Override
	public void update(CahgJobEntity cahgJob) {
		cahgJobDao.update(cahgJob);
	}

	@Override
	public void delete(Integer jobId) {
		cahgJobDao.delete(jobId);
	}

	@Override
	public void deleteBatch(Integer[] jobIds) {
		cahgJobDao.deleteBatch(jobIds);
	}

	@Override
	public int saveAndGetKey(CahgJobEntity cahgJob) {
		return cahgJobDao.saveAndGetKey(cahgJob);
	}

	@Override
	public CahgJobEntity saveAndEntity(CahgJobEntity cahgJob) {
		// TODO Auto-generated method stub
		return cahgJobDao.saveAndEntity(cahgJob);
	}

	@Override
	public void saveJobFlow(JobFlow jobFlow) {
		// TODO Auto-generated method stub
		cahgJobDao.saveJobFlow(jobFlow);
	}

	@Override
	public List<JobDetailEntity> quryJobDetailList(Map map) {
		// TODO Auto-generated method stub
		return cahgJobDao.quryJobDetailList(map);
	}

	@Override
	public SysUserEntity queryFirstFlowUser(int cahgJobId) {
		// TODO Auto-generated method stub
		return cahgJobDao.queryFirstFlowUser(cahgJobId);
	}

	@Override
	public Map queryUserJobStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgJobDao.queryUserJobStatus(map);
	}

	@Override
	public void saveJobDetail(JobDetailEntity jobDetail) {
		// TODO Auto-generated method stub
		cahgJobDao.saveJobDetail(jobDetail);
	}

	@Override
	public void updateJobFlowStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cahgJobDao.updateJobFlowStatus(map);
	}

	@Override
	public void updateJobStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cahgJobDao.updateJobStatus(map);
	}

	@Override
	public void updateNextJobFlowStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cahgJobDao.updateNextJobFlowStatus(map);
	}

	@Override
	public void updateAllJobFlowStatu(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cahgJobDao.updateAllJobFlowStatu(map);
	}

	@Override
	public int queryjobCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgJobDao.queryjobCount(map);
	}

	@Override
	public int queryPersonTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cahgJobDao.queryPersonTotal(map);
	}


	@Override
	public void finish(CahgJobEntity cahgJob) {
		// TODO Auto-generated method stub
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		  //Date bt=sdf.parse(beginTime); 
		Date now =new Date();
		Date endTime = cahgJob.getEndTime();
		int result = now.compareTo(endTime);
		if (result == 1) {
			cahgJob.setStatus(3);
		} else {
			cahgJob.setStatus(2);
		}
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cahgJob.setFinishTime(new Date());
		cahgJobDao.update(cahgJob);
	}

	@Override
	public void updateFileNull(Integer[] jobIds) {
		cahgJobDao.updateFileNull(jobIds);
	}

	@Override
	public List<CahgJobEntity> queryAllList(Map<String, Object> map) {
		return cahgJobDao.queryAllList(map);
	}


/*
	@Override
	public void finish(CahgJobEntity cahgJob) {
		// TODO Auto-generated method stub
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		  //Date bt=sdf.parse(beginTime); 
		Date now =new Date();
		Date endTime = cahgJob.getEndTime();
		int result = now.compareTo(endTime);
		if (result == 1) {
			cahgJob.setStatus(2);
		} else {
			cahgJob.setStatus(1);
		}

		cahgJobDao.update(cahgJob);
	}
*/

}
