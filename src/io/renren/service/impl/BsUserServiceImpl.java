package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.dao.BsUserDao;
import io.renren.entity.BsUserEntity;
import io.renren.service.BsUserService;



@Service("bsUserService")
public class BsUserServiceImpl implements BsUserService {
	@Autowired
	private BsUserDao bsUserDao;
	
	@Override
	public BsUserEntity queryObject(String bsUserId){
		return bsUserDao.queryObject(bsUserId);
	}
	
	@Override
	public List<BsUserEntity> queryList(Map<String, Object> map){
		return bsUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bsUserDao.queryTotal(map);
	}
	
	@Override
	public void save(BsUserEntity bsUser){
		bsUserDao.save(bsUser);
	}
	
	@Override
	public void update(BsUserEntity bsUser){
		bsUserDao.update(bsUser);
	}
	
	@Override
	public void delete(String bsUserId){
		bsUserDao.delete(bsUserId);
	}
	
	@Override
	public void deleteBatch(String[] bsUserIds){
		bsUserDao.deleteBatch(bsUserIds);
	}
	

	@Override
	public void approved(String[] bsUserIds) {
		// TODO Auto-generated method stub
		bsUserDao.approved(bsUserIds);
	}
	

	@Override
	public void auditFailure(String[] bsUserIds) {
		// TODO Auto-generated method stub
		bsUserDao.auditFailure(bsUserIds);
	}

	@Override
	public List<BsUserEntity> queryAllList(HashMap map) {
		// TODO Auto-generated method stub
		return bsUserDao.queryAllList(map);
	}

	@Override
	public void removeAudit(String[] bsUserIds) {
		// TODO Auto-generated method stub
		bsUserDao.removeAudit(bsUserIds);
	}

	@Override
	public List<BsUserEntity> queryMobileList(String[] bsUserIds) {
		// TODO Auto-generated method stub
		return bsUserDao.queryMobileList(bsUserIds);
	}

	@Override
	public BsUserEntity queryUserById(String bs_user_id) {
		// TODO Auto-generated method stub
		return bsUserDao.queryUserById(bs_user_id);
	}

	
}
