package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import entities.Student;

public class DatabaseFileReader {
	
	private static final String PATH_TO_STUDENTS_FILE = "Y:\\git\\SoftwareQuality\\students.tsv";
	
	public static List<Student> loadAllStudentsFromDB(){

		List<Student> students = new ArrayList<Student>();		
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(PATH_TO_STUDENTS_FILE));
		    StringBuilder sb = new StringBuilder();
		    String fileLine = br.readLine();
		    
			    while (fileLine != null) {
	
			    
			    String[] splitResult = fileLine.split("\t");
			    
				Student student = new Student(splitResult[0], splitResult[1],
							splitResult[2], splitResult[3]);
				
				students.add(student);
				
				fileLine = br.readLine();
				
			    }
			    
		    br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return students;
	}
	
	public static Student getStudentById(String id){
		
	
		List<Student> students = new ArrayList<Student>();
		students = loadAllStudentsFromDB();
		
		for(Student student: students){
			if(student.getId().equals(id)){
				return student;
			}
		}
		return null;
	}
}
