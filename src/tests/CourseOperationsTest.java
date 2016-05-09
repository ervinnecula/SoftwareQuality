package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import services.CourseOperations;
import services.GradeOperations;
import services.StudentOperations;
import entities.Course;
import entities.Grade;
import entities.Student;

public class CourseOperationsTest {

	@Test
	public void testSaveCourse() {
		Course courseForTest1 = new Course("idForTest1", "courseNameForTest1", "1", "1", "5");
		Course courseForTest2= new Course("idForTest2", "courseNameForTest2", "2", "2", "5");
		CourseOperations co = new CourseOperations();
		co.saveCourse(courseForTest1);
		co.saveCourse(courseForTest2);
		Map<String,String> cMap = co.getAllCoursesAsMap();
		if(!cMap.keySet().contains(courseForTest1.getId()) 
				&& !cMap.keySet().contains(courseForTest1.getId())){
			fail("Courses were not saved correctly.");
		}
	}

	@Test
	public void testCheckIfPassedAtFirstTime() {
		CourseOperations co = new CourseOperations();
		StudentOperations so = new StudentOperations();
		GradeOperations go = new GradeOperations();
		Course courseForTest = new Course("courseIdForTest3", "courseNameForTest3", "1", "1", "5");
		Student studForTest = new Student("studIdForTest3", "studNameForTest3", "2013", "8.9");
		Grade grade1 = new Grade(studForTest.getId(), courseForTest.getId(), 4.0, "2013");
		Grade grade2 = new Grade(studForTest.getId(), courseForTest.getId(), 6.5, "2014");
		co.saveCourse(courseForTest);
		so.saveStudent(studForTest);
		go.saveGrade(grade1);
		go.saveGrade(grade2);
		
		assertEquals(false, co.checkIfPassedAtFirstTime(studForTest.getId(), courseForTest.getId()));
		
	}

	@Test
	public void testGetCreditsOfCourse() {
		String crazyBigCredit = "50";
		String nonExistClass = "nonExistClass";
		Course courseForTest = new Course("courseIdForTest4", "courseNameForTest4", "1", "1", crazyBigCredit);
		CourseOperations co = new CourseOperations();
		co.saveCourse(courseForTest);
		assertEquals(crazyBigCredit, co.getCreditsOfCourse(courseForTest.getId()));
		assertEquals(null, co.getCreditsOfCourse(nonExistClass));
	}

	@Test
	public void testExportCoursesToFile() {
		fail("Not yet implemented");
	}

}
