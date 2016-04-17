package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.DatabaseDetails;
import entities.Course;
import entities.Grade;
import entities.Student;

public class DatabaseFileReader {
	
	public static List<Student> loadAllStudentsFromDB(){

		List<Student> students = new ArrayList<Student>();
		
		try {

			BufferedReader br = new BufferedReader(new FileReader(
					DatabaseDetails.PATH_TO_STUDENTS_FILE));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					String[] splitResult = fileLine.split("\t");

					Student student = new Student(splitResult[0],
							splitResult[1], splitResult[2], splitResult[3]);

					students.add(student);
				}
				fileLine = br.readLine();

			    }
			    
		    br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return students;
	}
	
	public static List<Course> loadAllCoursesFromDB(){

		List<Course> courses = new ArrayList<Course>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					DatabaseDetails.PATH_TO_COURSES_FILE));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					String[] splitResult = fileLine.split("\t");

					Course course = new Course(splitResult[0], splitResult[1],
							splitResult[2], splitResult[3], splitResult[4]);

					courses.add(course);
				}
				fileLine = br.readLine();

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}
	
	public static Map<String,String> loadCoursesAsMap(){

		Map<String,String> courses = new HashMap<String, String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					DatabaseDetails.PATH_TO_COURSES_FILE));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					String[] splitResult = fileLine.split("\t");

					courses.put(splitResult[0], splitResult[1]);
				}
				fileLine = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
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

	public static List<Grade> loadGradesForStudent(String studentId){
		
		List<Grade> grades = new ArrayList<Grade>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					DatabaseDetails.PATH_TO_GRADES_FILE));
			String fileLine = br.readLine();

			while (fileLine != null) {
				if (!fileLine.isEmpty()) {
					String[] splitResult = fileLine.split("\t");
					
					if(splitResult[0].equals(studentId)){
						Grade grade = new Grade(splitResult[0], splitResult[1],
							Double.parseDouble(splitResult[2]), splitResult[3]);
						grades.add(grade);
					}
					
				}
				fileLine = br.readLine();

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return grades;
	}
}
