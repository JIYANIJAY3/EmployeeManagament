package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.AddProjectDetailsValidation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import bean.ProjectBean;
import dao.DataBaseConnection;
import dao.ProjectDao;

public class AddProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;

	public void init(ServletConfig config) throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession adminsession = request.getSession(false);

		if (adminsession != null) {
			String pid = request.getParameter("pid");
			String pname = request.getParameter("pname");
			String pdesc = request.getParameter("pdesc");

			AddProjectDetailsValidation addProjectDetailsValidation = new AddProjectDetailsValidation();

			String isPidValide = addProjectDetailsValidation.checkProjectIdValidation(pid);
			String isPNameValide = addProjectDetailsValidation.checkProjectProjectNameValidation(pname);
			String isPdescValide = addProjectDetailsValidation.checkProjectProjectDescValidation(pdesc);

			if (!isPidValide.equals(" ")) {
				out.print("<p style=color:red>" + isPidValide);
				RequestDispatcher rd = request.getRequestDispatcher("AddProject.html");
				rd.include(request, response);
			} else if (!isPNameValide.equals(" ")) {
				out.print("<p style=color:red>" + isPNameValide);
				RequestDispatcher rd = request.getRequestDispatcher("AddProject.html");
				rd.include(request, response);
			} else if (!isPdescValide.equals(" ")) {
				out.print("<p style=color:red>" + isPdescValide);
				RequestDispatcher rd = request.getRequestDispatcher("AddProject.html");
				rd.include(request, response);
			} else

			{
				ProjectBean pbean = new ProjectBean();
				pbean.setProjectId(pid);
				pbean.setProjectName(pname);
				pbean.setProjectDesc(pdesc);

				int status = new ProjectDao().addProject(con, pbean);
				if (status > 0) {
					out.print("successfully added :)");
				} else {
					out.print("somthing went erong not added :(");
				}
			}
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
