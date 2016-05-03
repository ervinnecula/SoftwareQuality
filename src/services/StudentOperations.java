package services;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
	\
	public List<States> calculateStateForStudents(){
		
		List<Student> allStudents = getAllStudents();
		Collections.sort(allStudents);
		int countSlotsScolarship = 0,countSlotsBudget = 0,countSlotstTax = 0;
		List<States> states = new ArrayList<States>();
		
		for(Student student:allStudents){
			if(Integer.parseInt(calculateCreditsForStudent(student.getId())) >= 30 && countSlotsScolarship < Integer.parseInt(DatabaseDetails.SLOTS_SCOLARSHIP)){
				states.add(States.SCOLARSHIP);
				countSlotsScolarship++;
			}
			else if(Integer.parseInt(calculateCreditsForStudent(student.getId())) >= 30 && countSlotsBudget < Integer.parseInt(DatabaseDetails.SLOTS_BUDGET)){
				states.add(States.BUDGET);
				countSlotsBudget++;
			}
			else if(Integer.parseInt(calculateCreditsForStudent(student.getId())) >= 30 && countSlotstTax < Integer.parseInt(DatabaseDetails.SLOTS_PAID)){
				states.add(States.TAX);
				countSlotstTax++;
			}
			else{
				states.add(States.REPEATYEAR);			
			}
		}
		
		return states;
	}
	
	public List<Student> orderStudentsByAverage(){
		
		List<Student> students = getAllStudents();
		Collections.sort(students);
		return students;
	}
		
	public void exportSituationToFile(List<MainDisplayObject> mdos) {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\Users\\Ervin\\Desktop\\situation export.tsv"));

			pw.println("ID" + "\t" + "NAME" + "\t" + "AVG.GRADE" + "\t" + "CREDITS" + "\t" + "POINTS" + "\t");
			for (MainDisplayObject mdo : mdos) {
				pw.println(mdo.getStudent().getId() + "\t" + mdo.getStudent().getName() + "\t" + mdo.getAverageGrade() + "\t" + mdo.getCredits() + "\t"
						+ mdo.getPoints() + "\t" + mdo.getState());
			pw.flush();
			}
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
