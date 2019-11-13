package io.renren.service;

import io.renren.entity.CahgHyperlinkEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-25 11:37:11
 */
public interface CahgHyperlinkService {
	
	CahgHyperlinkEntity queryObject(Integer linkId);
	
	List<CahgHyperlinkEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgHyperlinkEntity cahgHyperlink);
	
	void update(CahgHyperlinkEntity cahgHyperlink);
	
	void delete(Integer linkId);
	
	void deleteBatch(Integer[] linkIds);

	List<CahgHyperlinkEntity> headList(Map<String, Object> map);

	List<CahgHyperlinkEntity> rootList(Map<String, Object> map);
}
