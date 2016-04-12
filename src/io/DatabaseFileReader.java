package io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import entities.Student;

public class DatabaseFileReader {
	
	private static List<Student> students;
	
	public static void init(){
		 students = new ArrayList<>();
	}
	
	public List<Student> loadAllStudentsFromDB(){
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("student.tsv").getFile());
		
		try {
			List<String> fileLines = Files.readAllLines(file.toPath(), StandardCharsets.US_ASCII);
			
			for(String fileLine: fileLines){
				String[] splitResult = fileLine.split("\t");
				
				Student student = new Student(splitResult[0],splitResult[1],splitResult[2],splitResult[3]);
				students.add(student);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return students;
	}
	
	public Student getStudentById(String id){
		
		loadAllStudentsFromDB();
		
		for(Student student: students){
			if(student.getId().equals(id)){
				return student;
			}
		}
		return null;
	}
}
