package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import bean.EmployeeBean;
import dao.DataBaseConnection;
import dao.EmployeeSignUpDao;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	@Override
	public void init() throws ServletException {
		con = DataBaseConnection.getConnection();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String Email = request.getParameter("email");
		String Password = request.getParameter("password");
		
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setFirstName(firstName);
		employeeBean.setLastName(lastName);
		employeeBean.setEmail(Email);
		employeeBean.setPassword(Password);
		
		EmployeeSignUpDao signUpDao = new EmployeeSignUpDao();
		int status = signUpDao.addEmployee(con, employeeBean);
		if(status>0)
		{
			out.print("Succsefully Added");
		}
		else
		{
			out.print("Not Added Somthing Went Worng!");
		}
	}	
}
