package servlet;

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

public class ChangeEmployeeProjectStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;

	@Override
	public void init() throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String[] checkboxValues = request.getParameterValues("startcheckbox");
		String s1 = request.getParameter("projectId");
		String s2 = request.getParameter("hello");

		AssignProjectDao assignProjectDao = new AssignProjectDao();
		if (s2.equals("Start")) {
			int status = assignProjectDao.changeAssignProjectStatus(con, checkboxValues, s1);
			if (status > 0) {
				out.print("assign successfully");
				response.sendRedirect("ProjectDetails?no=" + s1);
			}
		} else if (s2.equals("End")) {
			int status = assignProjectDao.ChangeEmployeeProjectStatusToEnd(con, checkboxValues, s1);
			if (status > 0) {
				out.print("assign successfully");
				response.sendRedirect("ProjectDetails?no=" + s1);
			}
		} else if (s2.equals("Remove")) {
			int status = assignProjectDao.removeAssignEmployee(con, checkboxValues, s1);
			if (status > 0) {
				out.print("assign successfully");
				response.sendRedirect("ProjectDetails?no=" + s1);
			}
		} else {
			response.sendRedirect("ProjectDetails?no=" + s1);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
