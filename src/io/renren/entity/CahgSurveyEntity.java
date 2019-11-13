package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 11:38:38
 */
public class CahgSurveyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer surveyId;
	//问卷名称
	private String title;
	//问卷简单描述
	private String description;
	//状态： 0 显示，1 隐藏
	private Integer status;
	//
	private Date createDate;
	//
	private Long createUserId;
	//
	private Date lastUpdateDate;
	//
	private Long lastUpdateUserId;

	/**
	 * 设置：
	 */
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	/**
	 * 获取：
	 */
	public Integer getSurveyId() {
		return surveyId;
	}
	/**
	 * 设置：问卷名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：问卷名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：问卷简单描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：问卷简单描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：状态： 0 显示，1 隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态： 0 显示，1 隐藏
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
}
