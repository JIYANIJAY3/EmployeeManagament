package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import bean.ProjectBean;
import dao.DataBaseConnection;
import dao.ProjectDao;

/**
 * Servlet implementation class SearchProjectData
 */
public class SearchProjectData extends HttpServlet {
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

		ProjectDao projectDao = new ProjectDao();
		
		out.print("<h1>Project Table</h1>");
		out.print("<table border='1' cellpadding='4' width='60%'>");
		out.print("<tr><th>ProjectId</th><th>ProjectName</th><th>ProjectDesc</th>");

		List<ProjectBean> list = projectDao.showProject(con);
		for (ProjectBean pbBean : list) {
			String pageIndex = pbBean.getProjectId();
			out.print("<tr><td>" + "<a href='ProjectDetails?no=" + pageIndex + "'>" + pbBean.getProjectId() + "</a>"
					+ "</td><td>" + pbBean.getProjectName() + "</td><td>" + pbBean.getProjectDesc() + "</td></tr>");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
