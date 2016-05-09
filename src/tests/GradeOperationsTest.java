package tests;

import static org.junit.Assert.*;
import io.DatabaseFileReader;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import services.CourseOperations;
import services.GradeOperations;
import services.StudentOperations;
import entities.Course;
import entities.Grade;
import entities.Student;

public class GradeOperationsTest {

	@Test
	public void test() {
		CourseOperations co = new CourseOperations();
		StudentOperations so = new StudentOperations();
		GradeOperations go = new GradeOperations();
		Course courseForTest = new Course("courseIdForTest5", "courseNameForTest5", "1", "1", "5");
		Student studForTest = new Student("studIdForTest5", "studNameForTest5", "2013", "8.9");
		Grade gradeForTest = new Grade(studForTest.getId(), courseForTest.getId(), 8.0, "2014");
		co.saveCourse(courseForTest);
		so.saveStudent(studForTest);
		go.saveGrade(gradeForTest);
		DatabaseFileReader dbr = new DatabaseFileReader();
		List<Grade> grades=dbr.loadGradesForStudent(studForTest.getId());
		Grade foundGr=null;
		for(Grade gr : grades ) {
			if(gr.getCourseId().equals(courseForTest.getId()) && gr.getYear().equals("2014")) {
				foundGr=gr;
			}
		}
		assertEquals(gradeForTest.getGrade(), foundGr.getGrade(),2);
	}
	

}
