package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import bean.EmployeeBean;
import dao.DataBaseConnection;
import dao.EmployeeSignUpDao;


public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
	
		if (session != null) {
			EmployeeBean user = (EmployeeBean) session.getAttribute("Employee");
			out.print("<h1> WELCOM "+user.getFirstName()+"<h1><br><br>");
			out.println("<br><a href=\'ProFile\'>ProFile</a>");
			out.println("<a href=\'EmployeeShowProject\'>Show Project</a><br><br>");
			out.println("<a href=\'LogOut\'>LogOut</a><br><br>");
		}
		else
		{
			response.sendRedirect("Login.html");
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
