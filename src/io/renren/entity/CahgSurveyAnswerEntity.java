package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 18:09:15
 */
public class CahgSurveyAnswerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer surveyAnswerId;
	//选项的字符串
	private String choiceText;
	//选项的得票数量
	private Integer poll;
	//问题id
	private Integer surveyQuestionId;
	//显示百分比
	private String percent;

	/**
	 * 设置：
	 */
	public void setSurveyAnswerId(Integer surveyAnswerId) {
		this.surveyAnswerId = surveyAnswerId;
	}
	/**
	 * 获取：
	 */
	public Integer getSurveyAnswerId() {
		return surveyAnswerId;
	}
	/**
	 * 设置：选项的字符串
	 */
	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}
	/**
	 * 获取：选项的字符串
	 */
	public String getChoiceText() {
		return choiceText;
	}
	/**
	 * 设置：选项的得票数量
	 */
	public void setPoll(Integer poll) {
		this.poll = poll;
	}
	/**
	 * 获取：选项的得票数量
	 */
	public Integer getPoll() {
		return poll;
	}
	public Integer getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(Integer surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}

	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
}
