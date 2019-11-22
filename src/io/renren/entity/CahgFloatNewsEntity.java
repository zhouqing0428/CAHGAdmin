package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
public class CahgFloatNewsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//弹窗新闻ID
	private Integer floatNewId;
	//弹窗新闻标题
	private String floatNewTitle;
	//弹窗新闻内容
	private String floatNewContent;
	//发布时间
	private Date createDate;
	//最后修改时间
	private Date lastUpdateDate;
	//创建人
	private Long createUserId;
	//最后修改人
	private Long lastUpdateUserId;
	//弹窗新闻弹窗链接
	private String floatUrl;
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
	//作者
	private String author;
	//科室ID
	private Integer deptId;
	//状态：0:显示 1:禁用
	private Integer floatNewStatus;
	//置顶状态0:表示置顶
	private Integer floatNewsStick;
	//排序
	private String floatNewsRank;
	//部门名称
	private String deptName;

	/**
	 * 设置：弹窗新闻ID
	 */
	public void setFloatNewId(Integer floatNewId) {
		this.floatNewId = floatNewId;
	}
	/**
	 * 获取：弹窗新闻ID
	 */
	public Integer getFloatNewId() {
		return floatNewId;
	}
	/**
	 * 设置：弹窗新闻标题
	 */
	public void setFloatNewTitle(String floatNewTitle) {
		this.floatNewTitle = floatNewTitle;
	}
	/**
	 * 获取：弹窗新闻标题
	 */
	public String getFloatNewTitle() {
		return floatNewTitle;
	}
	/**
	 * 设置：图标新闻内容
	 */
	public void setFloatNewContent(String floatNewContent) {
		this.floatNewContent = floatNewContent;
	}
	/**
	 * 获取：图标新闻内容
	 */
	public String getFloatNewContent() {
		return floatNewContent;
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
	 * 设置：弹窗新闻图片链接
	 */
	public void setFloatUrl(String floatUrl) {
		this.floatUrl = floatUrl;
	}
	/**
	 * 获取：弹窗新闻图片链接
	 */
	public String getFloatUrl() {
		return floatUrl;
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
	 * 设置：状态：0:显示 1:禁用
	 */
	public void setFloatNewStatus(Integer floatNewStatus) {
		this.floatNewStatus = floatNewStatus;
	}
	/**
	 * 获取：状态：0:显示 1:禁用
	 */
	public Integer getFloatNewStatus() {
		return floatNewStatus;
	}
	/**
	 * 设置：置顶状态0:表示置顶
	 */
	public void setFloatNewsStick(Integer floatNewsStick) {
		this.floatNewsStick = floatNewsStick;
	}
	/**
	 * 获取：置顶状态0:表示置顶
	 */
	public Integer getFloatNewsStick() {
		return floatNewsStick;
	}
	/**
	 * 设置：排序
	 */
	public void setFloatNewsRank(String floatNewsRank) {
		this.floatNewsRank = floatNewsRank;
	}
	/**
	 * 获取：排序
	 */
	public String getFloatNewsRank() {
		return floatNewsRank;
	}
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
