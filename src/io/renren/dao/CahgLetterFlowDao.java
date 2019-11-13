package io.renren.dao;

import java.util.Map;

import io.renren.entity.CahgLetterFlowEntity;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-18 13:41:20
 */
public interface CahgLetterFlowDao extends BaseDao<CahgLetterFlowEntity> {

	void hadDeal(Integer[] letterFlowIds);

	//int queryUnReadTotal(Map<String, Object> map);

	int queryUnDealTotal(Map<String, Object> map);
	
}
