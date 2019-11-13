package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-06 17:50:57
 */
public class CahgAfficheEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer afficheId;
	//
	private String title;
	//状态
	private Integer status;
	//排序号
	private Integer rank;
	//会议内容
	private String content;
	//置顶: 0 置顶
	private Integer stick;
	//
	private Long createUserId;
	//添加时间
	private Date createDate;
	//最后更改人
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
	//
	private Date lastUpdateDate;
	//作者
	private String author;
	//科室ID
	private Integer deptId;
	//部门名称
	private String deptName;
	//上傳文件時間加後綴名
	private String afficheFilePath;
	//上傳文件原名
	private String afficheFileName;
	public String getAfficheFilePath() {
		return afficheFilePath;
	}
	public void setAfficheFilePath(String afficheFilePath) {
		this.afficheFilePath = afficheFilePath;
	}
	public String getAfficheFileName() {
		return afficheFileName;
	}
	public void setAfficheFileName(String afficheFileName) {
		this.afficheFileName = afficheFileName;
	}
	/**
	 * 设置：
	 */
	public void setAfficheId(Integer afficheId) {
		this.afficheId = afficheId;
	}
	/**
	 * 获取：
	 */
	public Integer getAfficheId() {
		return afficheId;
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
	 * 设置：状态：0 未开始，1 进行中，2 已结束
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：0 未开始，1 进行中，2 已结束
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：排序号
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**
	 * 获取：排序号
	 */
	public Integer getRank() {
		return rank;
	}
	/**
	 * 设置：会议内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：会议内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：置顶: 0 置顶
	 */
	public void setStick(Integer stick) {
		this.stick = stick;
	}
	/**
	 * 获取：置顶: 0 置顶
	 */
	public Integer getStick() {
		return stick;
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
	 * 设置：最后更改人
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：最后更改人
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
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
