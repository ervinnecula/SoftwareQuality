package services;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		double average;
		
		for(Grade grade:gradesOfStudent){
			totalOfGrades += grade.getGrade();
			counter++;
		}
		
		if(gradesOfStudent.size() != 0){
			average = totalOfGrades / counter;
		}
		else{
			average = 0;
		}
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
	
	public Map<String, States> calculateStateForStudents(List<Student> allStudents){
		
		Collections.sort(allStudents);
		int countSlotsScolarship = 0,countSlotsBudget = 0,countSlotstTax = 0;
		
		Map<String, States> studentState = new TreeMap<String, States>();
		
		for(Student student:allStudents){
			if(Integer.parseInt(calculateCreditsForStudent(student.getId())) >= 30 && countSlotsScolarship < Integer.parseInt(DatabaseDetails.SLOTS_SCOLARSHIP)){
				studentState.put(student.getId(), States.SCOLARSHIP);
				countSlotsScolarship++;
			}
			else if(Integer.parseInt(calculateCreditsForStudent(student.getId())) >= 30 && countSlotsBudget < Integer.parseInt(DatabaseDetails.SLOTS_BUDGET)){
				studentState.put(student.getId(), States.BUDGET);
				countSlotsBudget++;
			}
			else if(Integer.parseInt(calculateCreditsForStudent(student.getId())) >= 30 && countSlotstTax < Integer.parseInt(DatabaseDetails.SLOTS_PAID)){
				studentState.put(student.getId(), States.TAX);
				countSlotstTax++;
			}
			else{
				studentState.put(student.getId(), States.REPEATYEAR);			
			}
		}
		
		return studentState;
	}
	
	public List<Student> orderStudentsByAverage(){
		
		List<Student> students = getAllStudents();
		Collections.sort(students);
		return students;
	}
		
	public void exportSituationToFile(List<MainDisplayObject> mdos) {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(DatabaseDetails.EXPORT_PATH + "export situation.tsv"));

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
			PrintWriter pw = new PrintWriter(new FileOutputStream(DatabaseDetails.EXPORT_PATH + "situation student " + studentId + ".tsv"));
		
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
