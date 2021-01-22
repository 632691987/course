package course.data.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name="college")
public class College {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="college_id")
	private Long collegeId;
	
	@Column(name="college_name", nullable=false)
	private String collegeName;
	
	@OneToMany(mappedBy="belongCollege")
	private Collection<Teacher> teachers;
	
	@OneToMany(mappedBy="belongCollege")
	private Collection<Student> students;
	
	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	public Collection<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(collegeId, collegeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		College other = (College) obj;
		
		return Objects.equal(collegeId, other.collegeId)
				&& Objects.equal(collegeName, other.collegeName);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id",collegeId)
				.add("name",collegeName)
				.toString();
	}
	
}
