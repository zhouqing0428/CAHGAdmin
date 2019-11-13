package io.renren.service;

import io.renren.entity.CahgLetterFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-18 13:41:20
 */
public interface CahgLetterFlowService {
	
	CahgLetterFlowEntity queryObject(Integer letterFlowId);
	
	List<CahgLetterFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CahgLetterFlowEntity cahgLetterFlow);
	
	void update(CahgLetterFlowEntity cahgLetterFlow);
	
	void delete(Integer letterFlowId);
	
	void deleteBatch(Integer[] letterFlowIds);

	void hadDeal(Integer[] letterFlowIds);

	//int queryUnReadTotal(Map<String, Object> map);

	int queryUnDealTotal(Map<String, Object> map);
}
