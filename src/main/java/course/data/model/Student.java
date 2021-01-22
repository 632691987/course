package course.data.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="student_no")
	private String studentNo;
	
	@Column(name="student_name")
	private String studentName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="college_id")
	private College belongCollege;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( 
			name = "student_course",
			joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")}
	)
	private Collection<Student> studentList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public College getBelongCollege() {
		return belongCollege;
	}

	public void setBelongCollege(College belongCollege) {
		this.belongCollege = belongCollege;
	}

	public Collection<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(Collection<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(studentNo, studentName, belongCollege);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;

		return Objects.equal(studentNo, other.studentNo) 
				&& Objects.equal(studentName, other.studentName)
				&& Objects.equal(belongCollege, other.belongCollege);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("studentNo", studentNo)
				.add("studentName", studentName)
				.add("belongCollege", belongCollege)
				.toString();
	}
}
