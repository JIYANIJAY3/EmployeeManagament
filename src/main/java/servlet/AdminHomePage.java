package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.awt.geom.QuadCurve2D;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession adminsession = request.getSession(false);

		if (adminsession != null) {
			String adminName = (String) request.getAttribute("Name");
			out.print("<h1> WELCOM " + "Admin" + "<h1><br><br>");
			out.println("<br><a href='AddProject.html'>Add Project</a>");
			out.println("<br><a href=\'ShowProject\'>Show Project</a>");
			out.println("<a href=\'AdminLogOut\'>LogOut</a><br><br>");
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
