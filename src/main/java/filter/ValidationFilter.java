package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import util.Validation;

import java.io.IOException;
import java.io.PrintWriter;

public class ValidationFilter implements Filter  {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String firstname = request.getParameter("fname");
		String lastname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Validation validation = new Validation();
		String isEmailValidate = validation.checkEmailValidation(email);
		String isFirstNameValide = validation.checkNameValidation(firstname);
		String isLastNameValide = validation.checkNameValidation(firstname);
		String isPasswordValide = validation.checkPassowrdValidation(password);
		
		if(!isFirstNameValide.equals(" "))
		{
			out.print("<span style='color:red'>"+isFirstNameValide+"</span>"+"<br>");
			RequestDispatcher rd = request.getRequestDispatcher("SignUp.html");
			rd.include(request, response);
		}
		else if(!isLastNameValide.equals(" "))
		{
			out.print("<span style='color:red'>"+isFirstNameValide+"</span>"+"<br>");
			RequestDispatcher rd = request.getRequestDispatcher("/SignUp.html");
			rd.include(request, response);
		}
		else if(!isEmailValidate.equals(" "))
		{
			out.print("<span style='color:red'>"+isEmailValidate+"</span>"+"<br>");
			RequestDispatcher rd = request.getRequestDispatcher("/SignUp.html");
			rd.include(request, response);
		}
		else if(!isPasswordValide.equals(" "))
		{
			out.print("<span style='color:red'>"+isPasswordValide+"</span>"+"<br>");
			RequestDispatcher rd = request.getRequestDispatcher("/SignUp.html");
			rd.include(request, response);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

}
