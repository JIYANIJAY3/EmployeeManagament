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
import java.util.List;

import bean.EmployeeBean;
import dao.AssignProjectDao;
import dao.DataBaseConnection;
import dao.EmployeeSignUpDao;

public class ShowAllEmployeeToAssignProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if(session!=null)
		{
			String s1 = request.getParameter("no");
			AssignProjectDao assignProjectDao = new AssignProjectDao();
			List<EmployeeBean> listStatus = assignProjectDao.getEmployeeProjectStatus(con, s1);

			out.print("<h2>Project Details</h2>");
			out.print("<form action='ChangeEmployeeProjectStatus?projectId=" + s1 + "' method='post'>");
			out.print("<input type='hidden' name='projectid' value=" + s1 + ">");
			out.print("<table border='1' cellpadding='4' width='70%'>");
			out.print("<tr><th>Id</th><th>FirstName</th><th>LastName</th><th>ProjectId</th><th>ProjectStatus</th></tr>");
			for (EmployeeBean emp : listStatus) {
				out.print("<tr><td>" + emp.getId() + "</td><td>" + emp.getFirstName() + "</td><td>" + emp.getLastName()
						+ "</td><td>" + emp.getProjectId() + "</td><td>" + emp.getProjectStatus() + "</td></tr>");
			}
			out.print("</table>" + "<br>");
			out.print("</form>");

			out.print("<a href=HomePage>Home</a>");
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
	public void init() throws ServletException {
		con = DataBaseConnection.getConnection();
	}

}
