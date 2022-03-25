package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import bean.AssignProjectBean;
import bean.EmployeeBean;
import bean.ProjectBean;
import dao.AssignProjectDao;
import dao.DataBaseConnection;
import dao.ProjectDao;

public class EmployeeShowProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;

	@Override
	public void init() throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if(session!=null)
		{
//			EmployeeBean user = (EmployeeBean) session.getAttribute("Employee");
//			AssignProjectDao assignProjectDao = new AssignProjectDao();
//			List<AssignProjectBean> list = assignProjectDao.getProjectData(con, user.getId());
//
//			out.print("<h1>Project Table</h1>");
//			out.print("<table border='1' cellpadding='4' width='60%'>");
//			out.print("<tr><th>ProjectId</th><th>ProjectStatus</th>");
//			for (AssignProjectBean pbBean : list) {
//				out.print("<tr><td>" + pbBean.getProjectId() + "</a>" + "</td><td>" + pbBean.getProjectStatus()
//						+ "</td><tr> ");
//			}
//			out.print("<a href=HomePage>Home</a>");
			out.print("<h1>Project Table</h1>");
			out.print("<table border='1' cellpadding='4' width='60%'>");
			out.print("<tr><th>ProjectId</th><th>ProjectName</th><th>ProjectDesc</th>");
			
			ProjectDao projectDao = new ProjectDao();
			List<ProjectBean> list = projectDao.showProject(con);
			for (ProjectBean pbBean : list) {
				String pageIndex = pbBean.getProjectId();
				out.print("<tr><td>" + "<a href='ShowAllEmployeeToAssignProject?no="+pageIndex+"'>"+pbBean.getProjectId()+"</a>" + "</td><td>" + pbBean.getProjectName() + "</td><td>"
						+ pbBean.getProjectDesc() + "</td></tr>");
			}
			
			out.print("<a href=AdminHomePage>Home</a>");
		}
		else
		{
			response.sendRedirect("Login.html");
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
	}
}
