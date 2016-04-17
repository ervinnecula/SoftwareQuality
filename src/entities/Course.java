package entities;

public class Course {

	private String id;
	private String name;
	private String semester;
	private String year;
	private String credit; 
		
	public Course(String id, String name, String semester, String year, String credit) {
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.credit = credit;
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCredit() {
		return credit;
	}
	public void setPoints(String credit) {
		this.credit = credit;
	}
	
}
