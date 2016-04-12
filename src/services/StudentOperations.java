package services;

import java.util.List;

import entities.Course;
import entities.Grade;
import entities.Student;
import io.FileReader;

public class StudentOperations {

	private Course course;
	private Grade grade;
	private Student student;
	
	public Student getStudentById(String id){
		FileReader.init();
		return FileReader.getStudentById(id);
	}
	
	public List<Student> getAllStudents(){
		FileReader.init();
		return FileReader.loadAllStudentsFromDB();
	}
}
