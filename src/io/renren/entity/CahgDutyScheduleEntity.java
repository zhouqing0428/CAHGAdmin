package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-25 16:58:27
 */
public class CahgDutyScheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer dutyScheduleId;
	//姓名
	private String name;
	// 手机号码
	private String mobile;
	//办公内线
	private String interior;
	//办公外线
	private String external;
	//值班时间
	private Date workTime;
	//备注
	private String remark;
	//状态：0 显示，1 隐藏
	private Integer status;
	//
	private Long createUserId;
	//
	private Date createDate;
	//
	private Long lastUpdateUserId;
	//
	private Date lastUpdateDate;
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
	//类别： 0 一般干部，1 领导
	private Integer category;

	/**
	 * 设置：
	 */
	public void setDutyScheduleId(Integer dutyScheduleId) {
		this.dutyScheduleId = dutyScheduleId;
	}
	/**
	 * 获取：
	 */
	public Integer getDutyScheduleId() {
		return dutyScheduleId;
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
	 * 设置：办公内线
	 */
	public void setInterior(String interior) {
		this.interior = interior;
	}
	/**
	 * 获取：办公内线
	 */
	public String getInterior() {
		return interior;
	}
	/**
	 * 设置：办公外线
	 */
	public void setExternal(String external) {
		this.external = external;
	}
	/**
	 * 获取：办公外线
	 */
	public String getExternal() {
		return external;
	}
	/**
	 * 设置：值班时间
	 */
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}
	/**
	 * 获取：值班时间
	 */
	public Date getWorkTime() {
		return workTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：状态：0 显示，1 隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：0 显示，1 隐藏
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：类别： 0 一般干部，1 领导
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}
	/**
	 * 获取：类别： 0 一般干部，1 领导
	 */
	public Integer getCategory() {
		return category;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
