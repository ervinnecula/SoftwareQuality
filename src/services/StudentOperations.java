package services;

import java.util.List;

import entities.Student;
import io.DatabaseFileReader;
import io.DatabaseFileWriter;

public class StudentOperations {

	private List<Student> students;
	
	public Student getStudentById(String id){
		return DatabaseFileReader.getStudentById(id);
	}
	
	public List<Student> getAllStudents(){
		students = DatabaseFileReader.loadAllStudentsFromDB();
		return students;
	}
		
	public void saveStudent(Student student){
		DatabaseFileWriter.saveStudentToFile(student);
	}
}
