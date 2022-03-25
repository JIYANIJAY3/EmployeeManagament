package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Validation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import bean.EmployeeBean;
import dao.EmployeeSignUpDao;

public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if (session != null) {
			EmployeeBean user = (EmployeeBean) session.getAttribute("Employee");
			out.println("<h2>Edit Profile Details</h2>");
			out.print("<form action='UpdateEmplyoeeProfile' method='post'>");
			out.print("<table>");
			out.print("<tr><td></td><td><input type='hidden' name='id' value='" + user.getId() + "'/></td></tr>");
			out.print("<tr><td></td><td><input type='hidden' name='email' value='" + user.getEmail() + "'/></td></tr>");
			out.print("<tr><td>Firstname:</td><td><input type='text' name='fname' value='" + user.getFirstName()
					+ "'/></td></tr>");
			out.print("<tr><td>Lastname:</td><td><input type='text' name='lname' value='" + user.getLastName()
					+ "'/></td></tr>");
			out.print("<tr><td>PassWord:</td><td><input type='password' name='password' value='" + user.getPassword()
					+ "'/></td></tr>");
			out.print("<tr><td colspan='2'><input type='submit' value='Update'/></td></tr>");
			out.print("</table>");
			out.print("</form>");
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
