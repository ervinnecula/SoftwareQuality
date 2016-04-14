package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.CourseOperations;
import services.StudentOperations;
import entities.Course;
import entities.Student;

/**
 * Servlet implementation class AddCourse
 */
@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/addCourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = (String) request.getParameter("name");
		String id = (String) request.getParameter("id");
		String year = (String) request.getParameter("year");
		String semester = (String) request.getParameter("semester");
		String credit = (String) request.getParameter("credit");
		
		CourseOperations co = new CourseOperations();
		co.saveCourse(new Course(id, name, semester, year, credit));
		
		request.getRequestDispatcher("WEB-INF/addCourse.jsp").forward(request, response);
	}

}
