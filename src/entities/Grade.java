package entities;

public class Grade {
	
	private String studentId;
	private String courseId;
	private double grade;
	private String year;
	
	public Grade(String studentId, String courseId, double grade, String year) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
		this.year = year;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

}
