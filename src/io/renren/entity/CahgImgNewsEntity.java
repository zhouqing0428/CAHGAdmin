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
public class CahgImgNewsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//图片新闻ID
	private Integer imgNewId;
	//图片新闻标题
	private String imgNewTitle;
	//图标新闻内容
	private String imgNewContent;
	//发布时间
	private Date createDate;
	//最后修改时间
	private Date lastUpdateDate;
	//创建人
	private Long createUserId;
	//最后修改人
	private Long lastUpdateUserId;
	//图片新闻图片链接
	private String imgUrl;
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
	private Integer imgNewStatus;
	//总关采用：0:否 1：是
	private Integer imgNewsStick;
	//排序
	private String imgNewsRank;
	//部门名称
	private String deptName;

	/**
	 * 设置：图片新闻ID
	 */
	public void setImgNewId(Integer imgNewId) {
		this.imgNewId = imgNewId;
	}
	/**
	 * 获取：图片新闻ID
	 */
	public Integer getImgNewId() {
		return imgNewId;
	}
	/**
	 * 设置：图片新闻标题
	 */
	public void setImgNewTitle(String imgNewTitle) {
		this.imgNewTitle = imgNewTitle;
	}
	/**
	 * 获取：图片新闻标题
	 */
	public String getImgNewTitle() {
		return imgNewTitle;
	}
	/**
	 * 设置：图标新闻内容
	 */
	public void setImgNewContent(String imgNewContent) {
		this.imgNewContent = imgNewContent;
	}
	/**
	 * 获取：图标新闻内容
	 */
	public String getImgNewContent() {
		return imgNewContent;
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
	 * 设置：图片新闻图片链接
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：图片新闻图片链接
	 */
	public String getImgUrl() {
		return imgUrl;
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
	public void setImgNewStatus(Integer imgNewStatus) {
		this.imgNewStatus = imgNewStatus;
	}
	/**
	 * 获取：状态：0:显示 1:禁用
	 */
	public Integer getImgNewStatus() {
		return imgNewStatus;
	}
	/**
	 * 设置：置顶状态0:表示置顶
	 */
	public void setImgNewsStick(Integer imgNewsStick) {
		this.imgNewsStick = imgNewsStick;
	}
	/**
	 * 获取：置顶状态0:表示置顶
	 */
	public Integer getImgNewsStick() {
		return imgNewsStick;
	}
	/**
	 * 设置：排序
	 */
	public void setImgNewsRank(String imgNewsRank) {
		this.imgNewsRank = imgNewsRank;
	}
	/**
	 * 获取：排序
	 */
	public String getImgNewsRank() {
		return imgNewsRank;
	}
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
