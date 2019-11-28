package io.renren.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.dao.CahgActivityNoticeDao;
import io.renren.entity.CahgActivityNoticeEntity;
import io.renren.service.CahgActivityNoticeService;

@Service("activityNoticeService")
public class CahgActivityNoticeServiceImpl implements CahgActivityNoticeService {
	
	@Autowired
	private CahgActivityNoticeDao dao;

	@Override
	public CahgActivityNoticeEntity queryObject(Integer id) {
		return dao.queryObject(id);
	}

	@Override
	public List<CahgActivityNoticeEntity> queryList(Map<String, Object> map) {
		return dao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return dao.queryTotal(map);
	}

	@Override
	public void save(CahgActivityNoticeEntity info) {
		dao.save(info);
	}

	@Override
	public void update(CahgActivityNoticeEntity info) {
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
