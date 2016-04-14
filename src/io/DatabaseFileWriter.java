package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import entities.Student;

public class DatabaseFileWriter {

	private static final String PATH_TO_STUDENTS_FILE = "Y:\\git\\SoftwareQuality\\students.tsv";
	
	public static void saveStudentToFile(Student student) {

		try {
		    Files.write(Paths.get(PATH_TO_STUDENTS_FILE),
		    			("\n" + student.getId() + "\t" + student.getName() + "\t" + student.getStartingYear() + "\t" + student.getStartingYear() + "\t" + student.getAdmissionGrade()).getBytes(),
		    			 StandardOpenOption.APPEND);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
