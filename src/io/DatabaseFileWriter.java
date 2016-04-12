package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import entities.Student;

public class DatabaseFileWriter {

	public void saveStudentToFile(Student student) {
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("student.tsv").getFile());
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			bw.write(student.getId()+"\t"+student.getName()+"\t"+student.getStartingYear()+"\t"+student.getAdmissionGrade());
			bw.newLine();
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
