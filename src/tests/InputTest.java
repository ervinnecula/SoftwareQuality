package tests;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import entities.Course;
import entities.Student;
import services.CourseOperations;
import services.StudentOperations;

public class InputTest {

	StudentOperations so = new StudentOperations();
	CourseOperations co = new CourseOperations();
	Course course;
	Student student;
	@Before
	public void initialize() {
//		course = new Course("asd", "Fizica Informatica", "5", "5", "30");
		course = new Course("1", "Geografie", "1", "3", "40");
//		Student student = new Student("asd", "123", "-1000", "aa12");
		student = new Student("1","Vasile1","1960","5.99");
	}

	@Test
	public void studentValidationId() {

		boolean idCorrect = true;
	
		// id contains letters
		if (Pattern.matches(student.getId(),"[a-zA-Z]+")) {
			idCorrect = false;
			
		} 
		assertEquals(true, idCorrect);
	}
	
	@Test
	public void studentValidationName(){
		boolean nameCorrect = true;
		
		if (Pattern.matches(student.getName(),"[1-9]+")) {
			nameCorrect = false;
			
		}
		assertEquals(true, nameCorrect);
	}
	
	@Test
	public void studentValidationYear(){
		
		boolean yearCorrect = true;
		
		if (Pattern.matches(student.getStartingYear(),"[a-zA-Z]") || student.getStartingYear().length() != 4
				|| Integer.parseInt(student.getStartingYear()) < 1950) {
			yearCorrect = false;	
		}
		assertEquals(true, yearCorrect);
	}
	
	@Test(expected=NumberFormatException.class)
	public void studentValidationGrade(){
		  boolean gradeCorrect = true;
		  String grade = student.getAdmissionGrade();
		  if (Pattern.matches(grade,"[a-zA-Z]") || Integer.parseInt(grade) < 0.0 || Integer.parseInt(grade) > 10.0){
			  gradeCorrect = false;
		  }
		  assertEquals(true, gradeCorrect);
	}

	@Test
	public void courseValidationId() {

		boolean idCorrect = true;

		// id contains letters
		if (Pattern.matches("[a-zA-Z]*", course.getId()) && course.getId().length() == 3) {
			idCorrect = false;
		}

		assertEquals(true, idCorrect);
	}

	@Test
	public void courseValidationName(){
		
		boolean nameCorrect = true;
		// name contains numbers
		if(Pattern.matches("[0-9]*",course.getName())){
			nameCorrect = false;
		}
		
		assertEquals(true, nameCorrect);
	}
	
	@Test
	public void courseValidationSemester(){
		
		boolean semesterCorrect = true;
		
		if (Integer.parseInt(course.getSemester()) != 1 && Integer.parseInt(course.getSemester()) != 2){
			semesterCorrect = false;
		}
		assertEquals(true, semesterCorrect);
	}
	
	@Test
	public void courseValidationYear(){
		
		boolean yearCorrect = true;
		
		if (Pattern.matches("[a-zA-Z]", course.getYear()) || Integer.parseInt(course.getYear()) < 1
				|| Integer.parseInt(course.getYear()) > 3) {
			yearCorrect = false;
		}
		
		assertEquals(true, yearCorrect);
	}
	
	@Test
	public void courseValidationCredit(){
		
		boolean creditCorrect = true;
		
		if (Pattern.matches("[a-zA-Z]",course.getCredit()) && Integer.parseInt(course.getCredit()) < 0.0) {
			creditCorrect = false;
		}
		
		assertEquals(true, creditCorrect);
	}
	
}
