package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation implements ValidationInterface {

	@Override
	public String checkEmailValidation(String email) {

		String emailregex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(emailregex);
		Matcher matcher = pattern.matcher(email);

		if (email.isEmpty()) {
			return "Email Is Empty";
		} else if (!matcher.matches()) {
			return "Email Are Not Valide please enter proper email like - xxx@gmai.com";
		} else {
			return " ";
		}
	}

	@Override
	public String checkNameValidation(String name) {

		if (name.isEmpty()) {
			return "FirstNanme Is Empty";
		} else if (!Pattern.matches("[a-zA-Z]+", name)) {
			return "Number Are Not Allowed";
		} else {
			return " ";
		}
	}

	@Override
	public String checkPassowrdValidation(String password) {

		if(password.isEmpty())
		{
			return "Password Is Empty";
		}else if(password.length()<8)
		{
			return "Minimum 8 digit Required";
		}
		else
		{
			return " ";
		}

//		String uppercaseRegEx = "^[A-Z]+$";
//		String lowercaseRegEx = "^[a-z]+$";
//		String numberRegEx = "^[0-9]+$";
//		String specialCharRegEx = "^[#?!@$%^&*-]+$";
//
//		if (password.isEmpty()) {
//			return "Password Is Empty";
//		} else if (!Pattern.matches(uppercaseRegEx, password)) {
//			return "Add atleast one uppercase";
//		} else if (!Pattern.matches(lowercaseRegEx, password)) {
//			return "Add atleast one lowercase";
//		} else if (!Pattern.matches(numberRegEx, password)) {
//			return "Add atleast one Digit";
//		} else if (!Pattern.matches(specialCharRegEx, password)) {
//			return "Add atleast one special char";
//		} else {
//			return " ";
//		}
	}
}
