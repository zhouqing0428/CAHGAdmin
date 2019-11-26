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
public class CahgStyleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//风采图片ID
	private Integer styleId;
	//风采图片标题
	private String styleTitle;
	//图风采图片分类
	private String styleCategory;
	//发布时间
	private Date createDate;
	//最后修改时间
	private Date lastUpdateDate;
	//创建人
	private Long createUserId;
	//最后修改人
	private Long lastUpdateUserId;
	//风采图片链接
	private String styleUrl;
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
	//发布作者
	private String author;
	//发布科室ID
	private Integer deptId;
	//状态：0:显示 1:禁用
	private Integer styleStatus;
	//总关采用：0:否 1：是
	private Integer styleStick;
	//排序
	private String styleRank;
	//部门名称
	private String deptName;
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	public String getStyleTitle() {
		return styleTitle;
	}
	public void setStyleTitle(String styleTitle) {
		this.styleTitle = styleTitle;
	}
	public String getStyleCategory() {
		return styleCategory;
	}
	public void setStyleCategory(String styleCategory) {
		this.styleCategory = styleCategory;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public String getStyleUrl() {
		return styleUrl;
	}
	public void setStyleUrl(String styleUrl) {
		this.styleUrl = styleUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getStyleStatus() {
		return styleStatus;
	}
	public void setStyleStatus(Integer styleStatus) {
		this.styleStatus = styleStatus;
	}
	public Integer getStyleStick() {
		return styleStick;
	}
	public void setStyleStick(Integer styleStick) {
		this.styleStick = styleStick;
	}
	public String getStyleRank() {
		return styleRank;
	}
	public void setStyleRank(String styleRank) {
		this.styleRank = styleRank;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
