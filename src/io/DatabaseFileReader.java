package io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import entities.Student;

public class DatabaseFileReader {
	
	private static List<Student> students;
	private static File studentsDataFile;
	
	public  static List<Student> getStudents(){
		return students;
	}
	
	public static void init(){
		 students = new ArrayList<>();
		 studentsDataFile = new File("students.tsv");
	}
	
	public static void loadAllStudentsFromDB(){

		if (students == null && studentsDataFile == null) {
			init();
		}
		try {
			List<String> fileLines = Files.readAllLines(
					studentsDataFile.toPath(), StandardCharsets.US_ASCII);

			for (String fileLine : fileLines) {
				String[] splitResult = fileLine.split("\t");

				Student student = new Student(splitResult[0], splitResult[1],
						splitResult[2], splitResult[3]);
				students.add(student);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Student getStudentById(String id){
		
		if (students==null || students.size() == 0) {
			loadAllStudentsFromDB();
		}
		for(Student student: students){
			if(student.getId().equals(id)){
				return student;
			}
		}
		return null;
	}
}
