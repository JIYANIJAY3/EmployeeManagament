package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Validation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import bean.EmployeeBean;
import dao.DataBaseConnection;
import dao.EmployeeSignUpDao;

public class UpdateEmplyoeeProfile extends HttpServlet {

	Connection con = null;

	@Override
	public void init() throws ServletException {
		con = DataBaseConnection.getConnection();
	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		Validation validation = new Validation();
		String isFirstNameValide = validation.checkNameValidation(firstname);
		String isLastNameValide = validation.checkNameValidation(lastname);

		if (!isFirstNameValide.equals(" ")) {
			RequestDispatcher rd = request.getRequestDispatcher("EditProfile");
			rd.include(request, response);
			out.print(isFirstNameValide);
		} else if (!isLastNameValide.equals(" ")) {
			out.print(isLastNameValide);
			RequestDispatcher rd = request.getRequestDispatcher("EditProfile");
			rd.include(request, response);
		} else {
			EmployeeBean bean = new EmployeeBean();
			bean.setId(Integer.parseInt(id));
			bean.setFirstName(firstname);
			bean.setLastName(lastname);
			bean.setPassword(password);
			bean.setEmail(email);

			EmployeeSignUpDao employeeSignUpDao = new EmployeeSignUpDao();
			int status = employeeSignUpDao.updateEmployeeDetails(con, bean);
			if (status > 0) {
				HttpSession session = request.getSession(false);
				session.setAttribute("Employee", bean);
				response.sendRedirect("ProFile");
				out.print("Update Succsefull :)");
			} else {
				out.print("Somthing Went Wrong Here! :(");
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

}
