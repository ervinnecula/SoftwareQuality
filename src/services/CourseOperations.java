package services;

import java.util.List;
import java.util.Map;

import entities.Course;
import entities.Grade;
import io.DatabaseFileReader;
import io.DatabaseFileWriter;


public class CourseOperations {

	public List<Course> getAllCourses(){
		return DatabaseFileReader.loadAllCoursesFromDB();
	}
	
	public Map<String,String> getAllCoursesAsMap(){
		return DatabaseFileReader.loadCoursesAsMap();
	}
	
	public void saveCourse(Course course){
		DatabaseFileWriter.saveCourseToFile(course);
	}
	
	public boolean checkIfPassedAtFirstTime(String studentId, String courseId){
		List<Course> courses = DatabaseFileReader.loadAllCoursesFromDB();
		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);
		
		for(Grade grade: grades){
			for(Course course:courses){
				if(grade.getCourseId().equals(course.getId()) && grade.getYear().equals(course.getYear())){
					return true;
				}
				
			}
		}	
		return false;
	}
	
	public String getCreditsOfCourse(String courseId){
		
		List<Course> courses = DatabaseFileReader.loadAllCoursesFromDB();
		
		for(Course course:courses){
			if(course.getId().equals(courseId)){
				return course.getCredit();
			}
		}
		
		return null;
	}
	 
}
