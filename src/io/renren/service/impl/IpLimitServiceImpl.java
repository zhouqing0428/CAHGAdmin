package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.CahgCommonFormsDao;
import io.renren.dao.IpLimitDao;
import io.renren.entity.CahgCommonFormsEntity;
import io.renren.entity.IpLimitEntity;
import io.renren.entity.PictureEntity;
import io.renren.service.CahgCommonFormsService;
import io.renren.service.IpLimitService;



@Service("ipLimitService")
public class IpLimitServiceImpl implements IpLimitService {
	@Autowired
	private IpLimitDao ipLimitDao;

	@Override
	public List<IpLimitEntity> queryList(Map<String, Object> map) {
		return ipLimitDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return ipLimitDao.queryTotal(map);
	}

	@Override
	public IpLimitEntity queryObject(Integer ipLimitId) {
		return ipLimitDao.queryObject(ipLimitId);
	}

	@Override
	public void updateIP(IpLimitEntity ipLimitEntity) {
		// TODO Auto-generated method stub
		ipLimitDao.updateIP(ipLimitEntity);
	}


	
}
