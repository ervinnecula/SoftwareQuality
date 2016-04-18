package entities;

import java.util.Comparator;

public class MainDisplayObject implements Comparator<MainDisplayObject>, Comparable<MainDisplayObject> {
	
	private Student student;
	private String credits;
	private String points;
	private String averageGrade;
	private States state;
	
	public MainDisplayObject(Student student, String credits, String points, String averageGrade, States state){
		this.student = student;
		this.credits = credits;
		this.points = points;
		this.averageGrade = averageGrade;
		this.state = state;
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

	public String getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(String averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	public States getState(){
		return this.state;
	}
	
	public void setState(States state){
		this.state = state;
	}
	
	public int compare(MainDisplayObject mdo, MainDisplayObject otherMdo){
		return (Integer.parseInt(otherMdo.getPoints()) - Integer.parseInt(mdo.getPoints()));
	}

	@Override
	public int compareTo(MainDisplayObject o) {
		Integer points1 = Integer.parseInt(this.getPoints());
		Integer points2 = Integer.parseInt(o.getPoints());
		return points2.compareTo(points1);
	}
		
}
