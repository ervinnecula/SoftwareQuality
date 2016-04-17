package services;

import java.util.ArrayList;
import java.util.List;

import entities.Grade;
import entities.Student;
import io.DatabaseFileReader;
import io.DatabaseFileWriter;

public class StudentOperations {

	public Student getStudentById(String id){
		return DatabaseFileReader.getStudentById(id);
	}
	
	public List<Student> getAllStudents(){
		return DatabaseFileReader.loadAllStudentsFromDB();
	}
		
	public void saveStudent(Student student){
		DatabaseFileWriter.saveStudentToFile(student);
	}
	
	public String calculatePointsForStudent(String studentId) {

		int points = 0;

		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);

		CourseOperations co = new CourseOperations();

		for (Grade grade : grades) {
			if (grade.getGrade() > 5.0 && co.checkIfPassedAtFirstTime(studentId, grade.getCourseId())) {
				points = points + (int) grade.getGrade() * Integer.parseInt(co.getCreditsOfCourse(grade.getCourseId()));
			}
		}

		return points+"";
	}
	
	public String calculateCreditsForStudent(String studentId){
		int credits = 0;
		
		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);	
		
		CourseOperations co = new CourseOperations();
		
		for(Grade grade:grades){
			if(grade.getGrade() > 5.0 && co.checkIfPassedAtFirstTime(studentId, grade.getCourseId())){
				credits = credits + Integer.parseInt(co.getCreditsOfCourse(grade.getCourseId()));
			}
		}

		return credits+"";
	}
	
	public List<String> calculateCreditsForStudents(){
		List<Student> students = DatabaseFileReader.loadAllStudentsFromDB();
		List<String> credits = new ArrayList<String>();
		for(Student student:students){
			String credit = calculateCreditsForStudent(student.getId());
			credits.add(credit);
		}
		return credits;
	}
	
}
