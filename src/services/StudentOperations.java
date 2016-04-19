package services;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseDetails;
import entities.Grade;
import entities.MainDisplayObject;
import entities.States;
import entities.Student;
import io.DatabaseFileReader;
import io.DatabaseFileWriter;

public class StudentOperations {

	public Student getStudentById(String id){
		return DatabaseFileReader.getStudentById(id);
	}
	
	public List<Student> getAllStudents(){
		return DatabaseFileReader.loadAllStudentsFromDB();
	}
		
	public void saveStudent(Student student){
		DatabaseFileWriter.saveStudentToFile(student);
	}
	
	public String calculatePointsForStudent(String studentId) {

		int points = 0;

		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);

		CourseOperations co = new CourseOperations();

		for (Grade grade : grades) {
			if (grade.getGrade() > 5.0 && co.checkIfPassedAtFirstTime(studentId, grade.getCourseId())) {
				points = points + (int) grade.getGrade() * Integer.parseInt(co.getCreditsOfCourse(grade.getCourseId()));
			}
		}

		return points+"";
	}
	
	public String calculateCreditsForStudent(String studentId){
		int credits = 0;
		
		List<Grade> grades = DatabaseFileReader.loadGradesForStudent(studentId);	
		
		CourseOperations co = new CourseOperations();
		
		for(Grade grade:grades){
			if(grade.getGrade() >= 5.0 && co.checkIfPassedAtFirstTime(studentId, grade.getCourseId())){
				credits = credits + Integer.parseInt(co.getCreditsOfCourse(grade.getCourseId()));
			}
		}

		return credits+"";
	}
	
	public List<String> calculateCreditsForStudents(){
		List<Student> students = DatabaseFileReader.loadAllStudentsFromDB();
		List<String> creditList = new ArrayList<String>();
		for(Student student:students){
			String credits = calculateCreditsForStudent(student.getId());
			creditList.add(credits);
		}
		return creditList;
	}
	
	public List<String> calculatePointsForStudents(){
		List<Student> students = DatabaseFileReader.loadAllStudentsFromDB();
		List<String> pointsList = new ArrayList<String>();
		for(Student student:students){
			String points = calculatePointsForStudent(student.getId());
			pointsList.add(points);
		}
		return pointsList;
	}
	
	public String calculateAverageGradeForStudent(String studentId){
		
		List<Grade> gradesOfStudent = DatabaseFileReader.loadGradesForStudent(studentId);
		
		double totalOfGrades = 0.0;
		int counter = 0;
		
		for(Grade grade:gradesOfStudent){
			totalOfGrades += grade.getGrade();
			counter++;
		}
		
		double average = totalOfGrades / counter;
		
		return average+"";
	}
	
	public List<String> calculateAverageGradeForStudents(){
		
		List<String> averageGrades = new ArrayList<String>();
		
		List<Student> studentList = DatabaseFileReader.loadAllStudentsFromDB();
		for(Student student: studentList){
			String average = calculateAverageGradeForStudent(student.getId());
			averageGrades.add(average);
		}
		
		return averageGrades;
	}
	
	public States caculateStateForStudent(String studentId){
		
		String credits = calculateCreditsForStudent(studentId);
		String points = calculatePointsForStudent(studentId);
		
		if (credits.equals(DatabaseDetails.MAXIMUM_CREDITS)) {
			return States.SCOLARSHIP;
		} 
		else if (Integer.parseInt(credits) >= Integer.parseInt(DatabaseDetails.MINIMUM_CREDITS)
				&& Integer.parseInt(points) >= Integer.parseInt(DatabaseDetails.MINIMUM_POINTS_FOR_BUDGET)) {
			return States.BUDGET;
		} 
		else if (Integer.parseInt(credits) >= Integer.parseInt(DatabaseDetails.MINIMUM_CREDITS)
				&& Integer.parseInt(points) < Integer.parseInt(DatabaseDetails.MINIMUM_POINTS_FOR_BUDGET)) {
			return States.TAX;
		} 
		else
			return States.REPEATYEAR;
	}
	
	public List<States> calculateStateForStudents(){
		
		List<Student> students = DatabaseFileReader.loadAllStudentsFromDB();
		List<States> states = new ArrayList<States>();
		
		for(Student student:students){
			States state = caculateStateForStudent(student.getId());
			
			states.add(state);
		}
		return states;
	}
	
	public void exportSituationToFile(List<MainDisplayObject> mdos) {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\Users\\Ervin\\Desktop\\situation export.tsv"));

			pw.println("ID" + "\t" + "NAME" + "\t" + "AVG.GRADE" + "\t" + "CREDITS" + "\t" + "POINTS" + "\t");
			for (MainDisplayObject mdo : mdos) {
				pw.println(mdo.getStudent().getId() + "\t" + mdo.getStudent().getName() + "\t" + mdo.getAverageGrade() + "\t" + mdo.getCredits() + "\t"
						+ mdo.getPoints() + "\t" + mdo.getState());
			}
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportStudentToFile(String studentId, List<MainDisplayObject> mdos){
		
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("situation student " + studentId + ".tsv"));
		
			for (MainDisplayObject mdo : mdos) {
				if (mdo.getStudent().getId().equals(studentId)) {
					pw.println(mdo.getStudent().getId()  + "\t" + mdo.getStudent().getName() +  "\t" + mdo.getAverageGrade() + "\t" + mdo.getCredits() + "\t"
							+ mdo.getPoints() + "\t" + mdo.getState());
				}
			}
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
