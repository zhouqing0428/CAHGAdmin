package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-04 10:15:43
 */
public class CahgCustomNewsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer customNewsId;
	//标题
	private String title;
	//发布内容
	private String content;
	//作者
	private String author;
	//添加人
	private Long createUserId;
	//添加时间
	private Date createDate;
	//最后修改人
	private Long lastUpdateUserId;
	//最后修改时间
	private Date lastUpdateDate;
	//状态： 0 显示,1 隐藏
	private Integer status;
	//排序号
	private String rank;
	//置顶 ：0 置顶
	private Integer stick;
	//部门id
	private Integer deptId;
	//部门名称
	private String deptName;
	//上传文件时间加后缀
	private String customFilePath;
	//上传文件原名
	private String customFileName;
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
	public String getCustomFilePath() {
		return customFilePath;
	}
	public void setCustomFilePath(String customFilePath) {
		this.customFilePath = customFilePath;
	}
	public String getCustomFileName() {
		return customFileName;
	}
	public void setCustomFileName(String customFileName) {
		this.customFileName = customFileName;
	}
	/**
	 * 设置：
	 */
	public void setCustomNewsId(Integer customNewsId) {
		this.customNewsId = customNewsId;
	}
	/**
	 * 获取：
	 */
	public Integer getCustomNewsId() {
		return customNewsId;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：发布内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：发布内容
	 */
	public String getContent() {
		return content;
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
	 * 设置：添加人
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：添加人
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：添加时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getCreateDate() {
		return createDate;
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
	 * 设置：状态： 0 显示,1 隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态： 0 显示,1 隐藏
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：排序号
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * 获取：排序号
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * 设置：置顶 ：0 置顶
	 */
	public void setStick(Integer stick) {
		this.stick = stick;
	}
	/**
	 * 获取：置顶 ：0 置顶
	 */
	public Integer getStick() {
		return stick;
	}
	/**
	 * 设置：部门id
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门id
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
