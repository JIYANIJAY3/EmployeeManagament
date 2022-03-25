package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import bean.ProjectBean;
import dao.DataBaseConnection;
import dao.ProjectDao;

public class ShowProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;

	public void init(ServletConfig config) throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ProjectDao projectDao = new ProjectDao();
	
		HttpSession adminsession = request.getSession(false);
		if(adminsession!=null)
		{
			out.print("<h1>Project Table</h1>");
			out.print("<table border='1' cellpadding='4' width='60%'>");
			out.print("<tr><th>ProjectId</th><th>ProjectName</th><th>ProjectDesc</th>");
			
			List<ProjectBean> list = projectDao.showProject(con);
			for (ProjectBean pbBean : list) {
				String pageIndex = pbBean.getProjectId();
				out.print("<tr><td>" + "<a href='ProjectDetails?no="+pageIndex+"'>"+pbBean.getProjectId()+"</a>" + "</td><td>" + pbBean.getProjectName() + "</td><td>"
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
	public void destroy() {
		
	} 
}
