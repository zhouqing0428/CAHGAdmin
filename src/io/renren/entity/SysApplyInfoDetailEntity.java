package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email
 * @date 
 */
public class SysApplyInfoDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//活动报名信息
	private String applyInfoId;
	//姓名
	private String name;
	//手机号码
	private String moblie;
	//付款凭证截图
	private String payImg;
	//报名时间
	private Date applyTime;
	//上传凭证时间
	private Date payTime;
	//审核状态
	private String checkState;
	//付款状态
	private String payState;
	//短信状态
	private String smsState;
	//备注
	private String note;
	//支付凭证
	private String payInfo;
	//活动id
	private Long activityId;
	//代理商id
	private String bsUserId;
	//活动标题
	private String title;
	//活动收费价格
    private String expense;
    
	
	/**
	 * 设置：活动报名信息
	 */
	public void setApplyInfoId(String applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
	/**
	 * 获取：活动报名信息
	 */
	public String getApplyInfoId() {
		return applyInfoId;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：手机号码
	 */
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	/**
	 * 获取：手机号码
	 */
	public String getMoblie() {
		return moblie;
	}
	
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	/**
	 * 设置：上传凭证时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：上传凭证时间
	 */
	public Date getPayTime() {
		return payTime;
	}
	/**
	 * 设置：审核状态
	 */
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	/**
	 * 获取：审核状态
	 */
	public String getCheckState() {
		return checkState;
	}
	/**
	 * 设置：付款状态
	 */
	public void setPayState(String payState) {
		this.payState = payState;
	}
	/**
	 * 获取：付款状态
	 */
	public String getPayState() {
		return payState;
	}
	/**
	 * 设置：短信状态
	 */
	public void setSmsState(String smsState) {
		this.smsState = smsState;
	}
	/**
	 * 获取：短信状态
	 */
	public String getSmsState() {
		return smsState;
	}
	/**
	 * 设置：备注
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：备注
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 设置：支付凭证
	 */
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	/**
	 * 获取：支付凭证
	 */
	public String getPayInfo() {
		return payInfo;
	}
	/**
	 * 设置：活动id
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public Long getActivityId() {
		return activityId;
	}
	/**
	 * 设置：代理商id
	 */
	public void setBsUserId(String bsUserId) {
		this.bsUserId = bsUserId;
	}
	/**
	 * 获取：代理商id
	 */
	public String getBsUserId() {
		return bsUserId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExpense() {
		return expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
	
	public String getPayImg() {
		return payImg;
	}
	public void setPayImg(String payImg) {
		this.payImg = payImg;
	}
}
