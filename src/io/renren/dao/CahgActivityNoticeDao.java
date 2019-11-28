package io.renren.dao;

import io.renren.entity.CahgActivityNoticeEntity;

public interface CahgActivityNoticeDao extends BaseDao<CahgActivityNoticeEntity> {

	void updateFileNull(Integer[] ids);
}
