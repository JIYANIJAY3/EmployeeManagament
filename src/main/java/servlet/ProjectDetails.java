package servlet;

import jakarta.servlet.ServletConfig;
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
import bean.ProjectBean;
import dao.AssignProjectDao;
import dao.DataBaseConnection;
import dao.EmployeeSignUpDao;
import dao.ProjectDao;

public class ProjectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;

	public void init(ServletConfig config) throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String s1 = request.getParameter("no");

		HttpSession adminsession = request.getSession(false);

		if (adminsession != null) {
			EmployeeSignUpDao employeeSignUpDao = new EmployeeSignUpDao();

			List<EmployeeBean> list = employeeSignUpDao.showEmployee(con);

			out.print("<h1>Assign Employee  to " + s1 + "</h1>");

			out.print("<form action='AssignProjectToEmployee' method='post'>");
			out.print("<input type='hidden' name='projectid' value=" + s1 + ">");
			out.print("<table border='1' cellpadding='4' width='70%'>");
			out.print("<tr><th>Id</th><th>FirstName</th><th>LastName</th><th>Email</th><th>Select</th></tr>");
			for (EmployeeBean eBean : list) {
				out.print("<tr><td>" + eBean.getId() + "</td><td>" + eBean.getFirstName() + "</td><td>"
						+ eBean.getLastName() + "</td><td>" + eBean.getEmail() + "</td><td>"
						+ "<input type='checkbox' name='checkbox' value= " + eBean.getId() + ">" + "</td></tr>");
			}
			out.print("</table>" + "<br>");
			out.print("<input type='submit' value='submit'>");
			out.print("</form>");

			AssignProjectDao assignProjectDao = new AssignProjectDao();
			List<EmployeeBean> listStatus = assignProjectDao.getEmployeeProjectStatus(con, s1);

			out.print("<h2>Start Project</h2>");
			out.print("<form action='ChangeEmployeeProjectStatus?projectId=" + s1 + "' method='post'>");
			out.print("<input type='hidden' name='projectid' value=" + s1 + ">");
			out.print("<table border='1' cellpadding='4' width='70%'>");
			out.print(
					"<tr><th>Id</th><th>FirstName</th><th>LastName</th><th>ProjectId</th><th>ProjectStatus</th><th>Select</th></tr>");
			for (EmployeeBean emp : listStatus) {
				out.print("<tr><td>" + emp.getId() + "</td><td>" + emp.getFirstName() + "</td><td>" + emp.getLastName()
						+ "</td><td>" + emp.getProjectId() + "</td><td>" + emp.getProjectStatus() + "</td><td>"
						+ "<input type='checkbox' name='startcheckbox' value= " + emp.getId() + ">" + "</td></tr>");
			}
			out.print("</table>" + "<br>");
			out.print("<input type='submit' name='hello' value='Start'>");
			out.print("  " + "<input type='submit' name='hello' value='End'>" + "  ");
			out.print("  " + "<input type='submit' name='hello' value='Remove'>" + "  ");
			out.print("</form>");

			out.print("<a href=AdminHomePage>Home</a>");
		} else {
			response.sendRedirect("Login.html");
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
	}

	public void destroy() {

	}
}
