package dao;

import java.sql.Connection;
import java.util.List;

import bean.EmployeeBean;

public interface EmployeeDaoInterface {
	public int addEmployee(Connection con, EmployeeBean employeeBean);
	public boolean loginEmployee(Connection con, EmployeeBean employeeBean);
	public EmployeeBean getEmployeeByEmail(Connection con, String email);
	public int updateEmployeeDetails(Connection con, EmployeeBean bean);
	public List<EmployeeBean> showEmployee(Connection con);
}
