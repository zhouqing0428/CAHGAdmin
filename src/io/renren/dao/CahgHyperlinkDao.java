package io.renren.dao;

import java.util.List;
import java.util.Map;

import io.renren.entity.CahgHyperlinkEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-25 11:37:11
 */
public interface CahgHyperlinkDao extends BaseDao<CahgHyperlinkEntity> {

	List<CahgHyperlinkEntity> headList(Map<String, Object> map);

	List<CahgHyperlinkEntity> rootList(Map<String, Object> map);
	
}
