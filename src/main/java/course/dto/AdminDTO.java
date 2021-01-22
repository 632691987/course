package course.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
		name = "XMLType_AdminDTO", 
		propOrder = { 
				"name", 
				"enable", 
				"createTime", 
				"disable_by", 
				"disableTime" 
		}
)
@XmlRootElement(name="XMLRoot_AdminDTO")
public class AdminDTO {
	
	@NotBlank
	@Size(min = 2, max = 255)
	private String name;
	
	@NotBlank
	@Size(min = 2, max = 255)
	private String password;
	
	@Pattern(regexp="true|false")
	private String enable;
	
	private String createTime;
	private String disable_by;
	private String disableTime;
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDisable_by() {
		return disable_by;
	}
	public void setDisable_by(String disable_by) {
		this.disable_by = disable_by;
	}
	public String getDisableTime() {
		return disableTime;
	}
	public void setDisableTime(String disableTime) {
		this.disableTime = disableTime;
	}
}