package io.renren.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.CahgViolationDao;
import io.renren.entity.CahgViolationEntity;
import io.renren.service.CahgViolationService;

@Service("violationService")
public class CahgViolationServiceImpl implements CahgViolationService {
	
	@Autowired
	private CahgViolationDao dao;

	@Override
	public CahgViolationEntity queryObject(Integer id) {
		return dao.queryObject(id);
	}

	@Override
	public List<CahgViolationEntity> queryList(Map<String, Object> map) {
		return dao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return dao.queryTotal(map);
	}

	@Override
	public void save(CahgViolationEntity info) {
		dao.save(info);
	}

	@Override
	public void update(CahgViolationEntity info) {
		dao.update(info);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids) {
		dao.deleteBatch(ids);
	}

	@Override
	public void updateFileNull(Integer[] ids) {
		dao.updateFileNull(ids);
	}

}
