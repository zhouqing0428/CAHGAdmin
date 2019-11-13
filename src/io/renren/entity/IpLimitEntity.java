package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 
 */
public class IpLimitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer ipLimitId;
	//状态
	private Integer status;
	
	private String ipStart;
	
	private String ipEnd;
	
	 
		public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getIpEnd() {
		return ipEnd;
	}

	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}

		public Integer getIpLimitId() {
			return ipLimitId;
		}

		public void setIpLimitId(Integer ipLimitId) {
			this.ipLimitId = ipLimitId;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
}
