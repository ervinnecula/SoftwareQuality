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
		double o1 = Double.parseDouble(mdo.getAverageGrade());
		double o2 = Double.parseDouble(otherMdo.getAverageGrade());
		
		if(o1 < o2) return -1;
		else if(o1 == o2) return 0;
		else return 1;
	}

	@Override
	public int compareTo(MainDisplayObject o) {
		Double avg1 = Double.parseDouble(this.getAverageGrade());
		Double avg2 = Double.parseDouble(o.getAverageGrade());
		return avg2.compareTo(avg1);
	}
		
}
