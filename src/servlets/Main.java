package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.CourseOperations;
import services.StudentOperations;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentOperations studOp = new StudentOperations();
		request.setAttribute("students", studOp.getAllStudents());
		request.setAttribute("credits", studOp.calculateCreditsForStudents());
		CourseOperations courseOp = new CourseOperations();
		request.setAttribute("courses", courseOp.getAllCourses());
		request.getRequestDispatcher("WEB-INF/main.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*String yourname = (String) request.getParameter("yourname");
		request.setAttribute("yourname", yourname);*/
		request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
	
	}

}
