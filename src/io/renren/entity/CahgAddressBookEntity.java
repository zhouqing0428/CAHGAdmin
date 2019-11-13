package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 通讯录管理
 * @author 
 * @email 
 * @date 2017-07-14 17:51:32
 */
public class CahgAddressBookEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer addressLookId;
	//名称
	private String name;
	//手机号码
	private String mobile;
	//办公内线
	private String interior;
	//办公外线
	private String external;
	//发布时间
	private Date createDate;
	//最后修改时间
	private Date lastUpdateDate;
	//创建人
	private Long createUserId;
	//最后修改人
	private Long lastUpdateUserId;
	//工号
	private String jobNumber;
	//职务
	private String duty;
	//科室ID
	private Integer deptId;
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
	//
	private String deptName;

	/**
	 * 设置：ID
	 */
	public void setAddressLookId(Integer addressLookId) {
		this.addressLookId = addressLookId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getAddressLookId() {
		return addressLookId;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：通讯录名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号码
	 */
	public String getMobile() {
		return mobile;
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
	 * 设置：发布时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getCreateDate() {
		return createDate;
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
	 * 设置：创建人
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
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
	 * 设置：工号
	 */
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	/**
	 * 获取：工号
	 */
	public String getJobNumber() {
		return jobNumber;
	}
	/**
	 * 设置：职务
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * 获取：职务
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * 设置：科室ID
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：科室ID
	 */
	public Integer getDeptId() {
		return deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
