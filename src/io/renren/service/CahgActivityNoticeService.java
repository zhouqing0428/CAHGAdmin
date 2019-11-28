package io.renren.service;

import java.util.List;
import java.util.Map;

import io.renren.entity.CahgActivityNoticeEntity;

public interface CahgActivityNoticeService {

	CahgActivityNoticeEntity queryObject(Integer id);
	
	List<CahgActivityNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgActivityNoticeEntity info);
	
	void update(CahgActivityNoticeEntity info);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	void updateFileNull(Integer[] ids);
}
