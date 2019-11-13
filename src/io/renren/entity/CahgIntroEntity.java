package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-20 13:45:52
 */
public class CahgIntroEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer introId;
	//简介内容
	private String content;
	//简介备注
	private String remark;
	//最后修改人
	private Long lastUpdateUserId;
	//最后修改时间
	private Date lastUpdateDate;
	/**
	 * 设置：最后修改人
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：最后修改人
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：最后修改时间
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * 设置：
	 */
	public void setIntroId(Integer introId) {
		this.introId = introId;
	}
	/**
	 * 获取：
	 */
	public Integer getIntroId() {
		return introId;
	}
	/**
	 * 设置：简介内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：简介内容
	 */
	public String getContent() {
		return content;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
