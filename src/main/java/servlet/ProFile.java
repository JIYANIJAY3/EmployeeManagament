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

public class ProFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);

		if (session != null) {
			EmployeeBean user = (EmployeeBean) session.getAttribute("Employee");
			out.println("Id: " + user.getId() + "<br>");
			out.println("FirstName: " + user.getFirstName() + "<br>");
			out.println("LastName: " + user.getLastName() + "<br>");
			out.println("Email: " + user.getEmail() + "<br>");
			out.println("<br><br><a href=\'EditProfile\'>Edit Profile</a>");
			out.print("<a href=HomePage>Home</a>");
		} else {
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
