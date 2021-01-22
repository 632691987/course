package course.data.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import course.data.model.types.CourseType;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "course_name", nullable = false)
	private String courseName;

	@Column(name = "course_desc")
	private String courseDesc;

	@Column(name = "course_weight", nullable = false)
	private int courseWeight;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="course_teacher", nullable=false)
	private Teacher courseTeacher;
	
	@Column(name="course_enable")
	private int courseEnable;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="create_by", nullable=false)
	private Admin createdBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="locked_by", nullable=false)
	private Admin lockedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="college_id", nullable=false)	
	private College belongCollege;
	
	@Enumerated(EnumType.STRING)
	@Column(name="course_type")
	private CourseType courseType;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( 
			name = "student_course",
			joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")}
	)
	private Collection<Student> studentList;
	
	public Collection<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(Collection<Student> studentList) {
		this.studentList = studentList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public int getCourseWeight() {
		return courseWeight;
	}

	public void setCourseWeight(int courseWeight) {
		this.courseWeight = courseWeight;
	}

	public Teacher getCourseTeacher() {
		return courseTeacher;
	}

	public void setCourseTeacher(Teacher courseTeacher) {
		this.courseTeacher = courseTeacher;
	}

	public int getCourseEnable() {
		return courseEnable;
	}

	public void setCourseEnable(int courseEnable) {
		this.courseEnable = courseEnable;
	}

	public Admin getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Admin createdBy) {
		this.createdBy = createdBy;
	}

	public Admin getLockedBy() {
		return lockedBy;
	}

	public void setLockedBy(Admin lockedBy) {
		this.lockedBy = lockedBy;
	}

	public College getBelongCollege() {
		return belongCollege;
	}

	public void setBelongCollege(College belongCollege) {
		this.belongCollege = belongCollege;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, 
				courseName, 
				courseDesc, 
				courseWeight, 
				courseTeacher, 
				courseEnable, 
				createdBy, 
				lockedBy, 
				belongCollege, 
				courseType);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		
		return Objects.equal(id, other.id)
				&& Objects.equal(courseName, other.courseName)
				&& Objects.equal(courseDesc, other.courseDesc)
				&& Objects.equal(courseWeight, other.courseWeight)
				&& Objects.equal(courseTeacher, other.courseTeacher)
				&& Objects.equal(courseEnable, other.courseEnable)
				&& Objects.equal(createdBy, other.createdBy)
				&& Objects.equal(lockedBy, other.lockedBy)
				&& Objects.equal(belongCollege, other.belongCollege)
				&& Objects.equal(courseType, other.courseType);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id",id)
				.add("courseName",courseName)
				.add("courseDesc",courseDesc)
				.add("courseWeight",courseWeight)
				.add("courseTeacher",courseTeacher)
				.add("courseEnable",courseEnable)
				.add("createdBy",createdBy)
				.add("lockedBy",lockedBy)
				.add("belongCollege",belongCollege)
				.add("courseType",courseType)
				.toString();
	}

}
