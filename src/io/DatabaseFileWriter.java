package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import database.DatabaseDetails;
import entities.Course;
import entities.Grade;
import entities.Student;

public class DatabaseFileWriter {

	public static void saveStudentToFile(Student student) {

		try {
			Files.write(Paths.get(DatabaseDetails.PATH_TO_STUDENTS_FILE),
		    			("\n" +student.getId() + "\t" + student.getName() + "\t" + student.getStartingYear() + "\t" + student.getAdmissionGrade()).getBytes(),
		    			 StandardOpenOption.APPEND);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveCourseToFile(Course course) {

		try {
			Files.write(Paths.get(DatabaseDetails.PATH_TO_COURSES_FILE),
					("\n" + course.getId() + "\t" + course.getName() + "\t"
							+ course.getSemester() + "\t" + course.getYear()
							+ "\t" + course.getCredit()).getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveGradeToFile(Grade grade) {

		try {
			Files.write(Paths.get(DatabaseDetails.PATH_TO_GRADES_FILE),
					("\n" + grade.getStudentId() + "\t" + grade.getCourseId() + "\t"
							+ String.valueOf(grade.getGrade()) + "\t" + grade.getYear()).getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void removeStudentFromFile(Student student){
//		
//		
//		BufferedReader reader = new BufferedReader(new FileReader(DatabaseDetails.PATH_TO_STUDENTS_FILE));
//		BufferedWriter writer = new BufferedWriter(new FileWriter("temporaryFile.tsv"));
//		
//		String line;
//		String[] splits;
//		try{
//			while((line = reader.readLine()) != null){
//				String trimmedLine = line.trim();
//				splits = trimmedLine.split("\t");
//				if(splits[0].equals(anObject))
//			}
//		}
//	}
}
