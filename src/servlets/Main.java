package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.MainDisplayObject;
import entities.Student;
import services.StudentOperations;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentOperations studOp = new StudentOperations();
		
		List<String> credits = studOp.calculateCreditsForStudents();
		List<Student> students = studOp.getAllStudents();
		List<String> points = studOp.calculatePointsForStudents();
		
		List<MainDisplayObject> mdos = new ArrayList<MainDisplayObject>();
		
		for(int i=0;i<students.size();i++){
			MainDisplayObject mdo = new MainDisplayObject(students.get(i),credits.get(i),points.get(i));
			mdos.add(mdo);
		}
			
		request.setAttribute("mdos", mdos);
		
		request.getRequestDispatcher("WEB-INF/main.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*String yourname = (String) request.getParameter("yourname");
		request.setAttribute("yourname", yourname);*/
		request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
	
	}

}
