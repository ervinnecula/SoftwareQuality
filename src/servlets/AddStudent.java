package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Student;
import services.StudentOperations;

/**
 * Servlet implementation class AddStudent
 */
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/addStudent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = (String) request.getParameter("name");
		String id = (String) request.getParameter("id");
		String startingYear = (String) request.getParameter("startingYear");
		String admissionGrade = (String) request.getParameter("admissionGrade");
		
		StudentOperations so = new StudentOperations();
		so.saveStudent(new Student(id,name,startingYear,admissionGrade));
		
		request.getRequestDispatcher("WEB-INF/addStudent.jsp").forward(request, response);
	}

}
