package entities;

public class MainDisplayObject {
	
	private Student student;
	private String credits;
	private String points;
	
	public MainDisplayObject(Student student, String credits, String points){
		this.student = student;
		this.credits = credits;
		this.points = points;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
	
}
