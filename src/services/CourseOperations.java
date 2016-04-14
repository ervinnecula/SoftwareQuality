package services;

import io.DatabaseFileReader;
import io.DatabaseFileWriter;

import java.util.List;
import java.util.Map;

import entities.Course;


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
}
