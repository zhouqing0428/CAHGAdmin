package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-07 16:09:14
 */
public class CahgSurveyQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//回答类型：0 单选，1 多选
	private Integer surveyQuestionId;
	//问题
	private String question;
	//类型 ：0 单选，1 多选
	private Integer answerType;
	//
	private Long createUserId;
	//
	private Date createDate;
	//
	private Long lastUpdateUserId;
	//
	private Date lastUpdateDate;
	//是否现在首页 ：0 显示在首页
	private Integer indexShow;
	// 0 显示 ，1 隐藏
	private Integer status;
	//排序号
	private String orderId;
	//卷名id
	private Long surveyId;
	private String createUserName;
	private String lastUpdateName;

	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getLastUpdateName() {
		return lastUpdateName;
	}
	public void setLastUpdateName(String lastUpdateName) {
		this.lastUpdateName = lastUpdateName;
	}
	/**
	 * 设置：回答类型：0 单选，1 多选
	 */
	public void setSurveyQuestionId(Integer surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	/**
	 * 获取：回答类型：0 单选，1 多选
	 */
	public Integer getSurveyQuestionId() {
		return surveyQuestionId;
	}
	/**
	 * 设置：问题
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * 获取：问题
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * 设置：类型 ：0 单选，1 多选
	 */
	public void setAnswerType(Integer answerType) {
		this.answerType = answerType;
	}
	/**
	 * 获取：类型 ：0 单选，1 多选
	 */
	public Integer getAnswerType() {
		return answerType;
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
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
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
	 * 设置：是否现在首页 ：0 显示在首页
	 */
	public void setIndexShow(Integer indexShow) {
		this.indexShow = indexShow;
	}
	/**
	 * 获取：是否现在首页 ：0 显示在首页
	 */
	public Integer getIndexShow() {
		return indexShow;
	}
	/**
	 * 设置：卷名id
	 */
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	/**
	 * 获取：卷名id
	 */
	public Long getSurveyId() {
		return surveyId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
