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
		
		try {

			String name = (String) request.getParameter("name");
			int id = Integer.parseInt(request.getParameter("id"));
			int year = Integer.parseInt(request.getParameter("year"));
			int semester = Integer.parseInt(request.getParameter("semester"));
			int credit = Integer.parseInt(request.getParameter("credit"));

			CourseOperations co = new CourseOperations();
			co.saveCourse(new Course(id, name, semester, year, credit));
			
		} catch (NumberFormatException e) {
			e.getCause();
		}
		request.getRequestDispatcher("WEB-INF/addCourse.jsp").forward(request, response);
	}

}
