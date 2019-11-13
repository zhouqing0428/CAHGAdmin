package io.renren.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//import sun.java2d.pipe.SpanShapeRenderer.Simple;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 
 */
public class SysActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long activityId;
	//
	private String title;
	//活动人数
	private Integer number;
	//费用
	private String expense;
	//
	private Date strartDate;
	//
	private Date endDate;
	//
	private String content;
	//
	private String actiImg;
	//活动效果作用
	private String effect;
	//
	private String note;
	//
	private Long actiCateId;

	/**
	 * 设置：
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：
	 */
	public Long getActivityId() {
		return activityId;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：活动人数
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：活动人数
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * 设置：费用
	 */
	public void setExpense(String expense) {
		this.expense = expense;
	}
	/**
	 * 获取：费用
	 */
	public String getExpense() {
		return expense;
	}
	/**
	 * 设置：
	 */
	public void setStrartDate(Date strartDate) {
		this.strartDate = strartDate;
	}
	/**
	 * 获取：
	 */
	public String getStrartDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(null!=strartDate&&!"".equals(strartDate)){
			return sdf.format(strartDate);
		}
		return "";
		
	}
	/**
	 * 设置：
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：
	 */
	public String getEndDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(null!=endDate&&!"".equals(endDate)){
			return sdf.format(endDate);
		}
		return "";
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：活动效果作用
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}
	/**
	 * 获取：活动效果作用
	 */
	public String getEffect() {
		return effect;
	}
	/**
	 * 设置：
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 设置：
	 */
	public void setActiCateId(Long actiCateId) {
		this.actiCateId = actiCateId;
	}
	/**
	 * 获取：
	 */
	public Long getActiCateId() {
		return actiCateId;
	}
	public String getActiImg() {
		return actiImg;
	}
	public void setActiImg(String actiImg) {
		this.actiImg = actiImg;
	}
}
