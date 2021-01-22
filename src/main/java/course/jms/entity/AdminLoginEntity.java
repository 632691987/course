package course.jms.entity;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

public class AdminLoginEntity implements Serializable{
	
	private static final long serialVersionUID = 2016_03_16l;
	
	private String messageCode;
	
	private String adminName;
	
	private String maintainField01;
	
	private String maintainField02;

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getMaintainField01() {
		return maintainField01;
	}

	public void setMaintainField01(String maintainField01) {
		this.maintainField01 = maintainField01;
	}

	public String getMaintainField02() {
		return maintainField02;
	}

	public void setMaintainField02(String maintainField02) {
		this.maintainField02 = maintainField02;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
		.add("messageCode", messageCode)
		.add("adminName", adminName)
		.add("maintainField01", maintainField01)
		.add("maintainField02", maintainField02)
		.toString();
	}
	
	
}
