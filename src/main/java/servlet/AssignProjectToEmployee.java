package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import dao.AssignProjectDao;
import dao.DataBaseConnection;

public class AssignProjectToEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;

	@Override
	public void init() throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String[] checkboxValues = request.getParameterValues("checkbox");
		String projectId = request.getParameter("projectid");



			AssignProjectDao assignProjectDao = new AssignProjectDao();
			int status = assignProjectDao.assignProject(con, checkboxValues, projectId);
			if (status > 0) {
				response.sendRedirect("ProjectDetails?no=" + projectId);
			}
			else
			{
				response.sendRedirect("ProjectDetails?no=" + projectId);
			}
		}
	
}
