package course.data.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue
	private Long teacherNO;

	@Column(name = "teacher_name", nullable = false)
	private String teacherName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", nullable = false)
	private Date startTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "college_id", nullable = false)
	private College belongCollege;
	
	@OneToMany(mappedBy="courseTeacher")
	private Collection<Course> teachCourses;
	
	public Collection<Course> getTeachCourses() {
		return teachCourses;
	}

	public void setTeachCourses(Collection<Course> teachCourses) {
		this.teachCourses = teachCourses;
	}

	public Long getTeacherNO() {
		return teacherNO;
	}

	public void setTeacherNO(Long teacherNO) {
		this.teacherNO = teacherNO;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public College getBelongCollege() {
		return belongCollege;
	}

	public void setBelongCollege(College belongCollege) {
		this.belongCollege = belongCollege;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(teacherNO, teacherName, startTime, belongCollege);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;

		return Objects.equal(teacherNO, other.teacherNO) && Objects.equal(teacherName, other.teacherName)
				&& Objects.equal(startTime, other.startTime) && Objects.equal(belongCollege, other.belongCollege);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", teacherNO).add("name", teacherName).add("password", startTime)
				.add("enable", belongCollege).toString();
	}

}
