package services;

import entities.Grade;

public class GradeOperations {
	
	public void addGradeToStudent(String studentId, String courseId, double grade, String year){
		Grade newGrade = new Grade(studentId, courseId, grade, year);
			
	}
}
