package course.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="enable", nullable=false)
	private boolean enable;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	
	@JoinColumn(name="disable_by")
	@ManyToOne(fetch=FetchType.LAZY)
	private Admin disable_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="disable_time", nullable=true)
	private Date disableTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Admin getDisable_by() {
		return disable_by;
	}

	public void setDisable_by(Admin disable_by) {
		this.disable_by = disable_by;
	}

	public Date getDisableTime() {
		return disableTime;
	}

	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, password, enable, createTime, disable_by, disableTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		
		return Objects.equal(id, other.id)
				&& Objects.equal(name, other.name) 
				&& Objects.equal(password, other.password)
				&& Objects.equal(enable, other.enable)
				&& Objects.equal(createTime, other.createTime)
				&& Objects.equal(disable_by, other.disable_by)
				&& Objects.equal(disableTime, other.disableTime);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id",id)
				.add("name",name)
				.add("password", password)
				.add("enable", enable)
				.add("createTime", createTime)
				.add("disable_by", disable_by)
				.add("disableTime", disableTime)
				.toString();
	}
}