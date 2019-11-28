package io.renren.dao;

import io.renren.entity.CahgViolationEntity;

public interface CahgViolationDao extends BaseDao<CahgViolationEntity> {

	void updateFileNull(Integer[] ids);
}
