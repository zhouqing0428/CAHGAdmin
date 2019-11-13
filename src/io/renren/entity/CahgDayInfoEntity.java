package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-27 15:44:14
 */
public class CahgDayInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//每日信息ID
	private Integer dayId;
	//每日信息标题
	private String dayTitle;
	//每日信息内容
	private String dayContent;
	//状态0：显示 1：隐藏
	private Integer dayStatus;
	//置顶状态 0：置顶
	private Integer dayStick;
	//排序
	private String dayRank;
	//科室ID
	private Integer deptId;
	//作者
	private String author;
	//发布时间
	private Date createDate;
	//最后修改时间
	private Date lastUpdateDate;
	//创建人
	private Long createUserId;
	//最后修改人
	private Long lastUpdateUserId;
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
	//发布科室名称
	private String deptName;
	//上传文件的时间加后缀
	private String dayName;
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getDayFileName() {
		return dayFileName;
	}
	public void setDayFileName(String dayFileName) {
		this.dayFileName = dayFileName;
	}
	//上传文件名称
	private String dayFileName;
	
	/**
	 * 设置：每日信息ID
	 */
	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}
	/**
	 * 获取：每日信息ID
	 */
	public Integer getDayId() {
		return dayId;
	}
	/**
	 * 设置：每日信息标题
	 */
	public void setDayTitle(String dayTitle) {
		this.dayTitle = dayTitle;
	}
	/**
	 * 获取：每日信息标题
	 */
	public String getDayTitle() {
		return dayTitle;
	}
	/**
	 * 设置：每日信息内容
	 */
	public void setDayContent(String dayContent) {
		this.dayContent = dayContent;
	}
	/**
	 * 获取：每日信息内容
	 */
	public String getDayContent() {
		return dayContent;
	}
	/**
	 * 设置：状态0：显示 1：隐藏
	 */
	public void setDayStatus(Integer dayStatus) {
		this.dayStatus = dayStatus;
	}
	/**
	 * 获取：状态0：显示 1：隐藏
	 */
	public Integer getDayStatus() {
		return dayStatus;
	}
	/**
	 * 设置：置顶状态 0：置顶
	 */
	public void setDayStick(Integer dayStick) {
		this.dayStick = dayStick;
	}
	/**
	 * 获取：置顶状态 0：置顶
	 */
	public Integer getDayStick() {
		return dayStick;
	}
	/**
	 * 设置：排序
	 */
	public void setDayRank(String dayRank) {
		this.dayRank = dayRank;
	}
	/**
	 * 获取：排序
	 */
	public String getDayRank() {
		return dayRank;
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
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
