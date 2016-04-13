package services;

import java.util.List;

import entities.Course;
import entities.Grade;
import entities.Student;
import io.DatabaseFileReader;
import io.DatabaseFileWriter;

public class StudentOperations {

	private Course course;
	private Grade grade;
	private Student student;
	
	public Student getStudentById(String id){
		DatabaseFileReader dfr = new DatabaseFileReader();
		
		return dfr.getStudentById(id);
	}
	
	public List<Student> getAllStudents(){
		DatabaseFileReader.init();
		DatabaseFileReader.loadAllStudentsFromDB();
		return DatabaseFileReader.getStudents();
	}
		
	public void saveStudent(Student student){
		DatabaseFileWriter dfw = new DatabaseFileWriter();
		
		dfw.saveStudentToFile(student);
	}
}
