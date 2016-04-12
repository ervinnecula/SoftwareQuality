package entities;

public class Student {

	private String id;
	private String name;
	private String startingYear;
	private String admissionGrade;
	
	
	public Student(String id, String name, String startingYear, String admissionGrade) {
		this.id = id;
		this.name = name;
		this.startingYear = startingYear;
		this.admissionGrade = admissionGrade;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartingYear() {
		return startingYear;
	}
	public void setStartingYear(String startingYear) {
		this.startingYear = startingYear;
	}
	public String getAdmissionGrade() {
		return admissionGrade;
	}
	public void setAdmissionGrade(String admissionGrade) {
		this.admissionGrade = admissionGrade;
	}
	
}
