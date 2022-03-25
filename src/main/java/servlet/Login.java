package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import bean.AdminBean;
import bean.EmployeeBean;
import dao.AdminDao;
import dao.DataBaseConnection;
import dao.EmployeeSignUpDao;

public class Login extends HttpServlet {
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

		String Email = request.getParameter("email");
		String Password = request.getParameter("password");
		String role = request.getParameter("role");
		
		EmployeeSignUpDao employeeSignUpDao = new EmployeeSignUpDao();
		
		if (role.equals("Employee")) {
			EmployeeSignUpDao emDao = new EmployeeSignUpDao();
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmail(Email);
			employeeBean.setPassword(Password);
			boolean status = emDao.loginEmployee(con, employeeBean);

			if (status) {
				HttpSession session = request.getSession();
				session.setAttribute("email", Email);
				EmployeeBean bean = employeeSignUpDao.getEmployeeByEmail(con, Email);
				session.setAttribute("Employee", bean);
				response.sendRedirect("HomePage");
			} else {
				out.print("Somthing Went Wrong!");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		}
		else if(role.equals("Admin"))
		{
			
			AdminBean adminBean = new AdminBean();
			adminBean.setEmail(Email);
			adminBean.setPassword(Password);
			boolean status = new  AdminDao().adminLogin(con,adminBean);
			if(status)
			{
				HttpSession adminsession = request.getSession();
				adminsession.setAttribute("Name","Admin");
				response.sendRedirect("AdminHomePage");
			}
			else
			{
				out.print("Somthing Went Wrong! :(");
				RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		}
		else
		{
			out.print("please selct your role :)");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
	}

	@Override
	public void destroy() {
		con = DataBaseConnection.closeConnection();
	}
}
