package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Course;
import entities.Grade;
import services.CourseOperations;
import services.GradeOperations;
import services.StudentOperations;

@WebServlet("/AddGrade")
public class AddGrade extends HttpServlet{

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CourseOperations courseOp = new CourseOperations();
		request.setAttribute("courses", courseOp.getAllCoursesAsMap());
		request.getRequestDispatcher("WEB-INF/addGrade.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			int studId = Integer.parseInt(request.getParameter("studId"));
			int courseId = Integer.parseInt(request.getParameter("courseId"));
			double grade = Double.parseDouble(request.getParameter("grade"));
			int year = Integer.parseInt(request.getParameter("year"));

			GradeOperations gOp = new GradeOperations();
			gOp.saveGrade(new Grade(studId, courseId, grade, year));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("WEB-INF/addGrade.jsp").forward(request, response);
	}
}
