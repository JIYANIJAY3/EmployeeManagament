package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bean.EmployeeBean;

public class EmployeeSignUpDao implements EmployeeDaoInterface{

	static Logger log = Logger.getLogger(EmployeeSignUpDao.class.getName());

	public int addEmployee(Connection con, EmployeeBean employeeBean) {
		String firstName = employeeBean.getFirstName();
		String lastName = employeeBean.getLastName();
		String Email = employeeBean.getEmail();
		String Password = employeeBean.getPassword();

		PreparedStatement ps = null;
		int status = 0;
		if (con != null) {
			try {
				String addQuery = "INSERT INTO employee(Id,FirstName,LastName,Email,Password) VALUES (NULL,?,?,?,?)";
				ps = con.prepareStatement(addQuery);
				ps.setString(1, firstName);
				ps.setString(2, lastName);
				ps.setString(3, Email);
				ps.setString(4, Password);
				status = ps.executeUpdate();
			} catch (Exception e) {
				log.info(e);
			}
		} else {
			System.out.println("Connection is null");
		}
		return status;
	}

	public boolean loginEmployee(Connection con, EmployeeBean employeeBean) {

		PreparedStatement get = null;
		ResultSet rs = null;
		boolean status = false;
		try {
			String getData = "SELECT * FROM employee WHERE Email=? and Password=?";
			get = con.prepareStatement(getData);
			get.setString(1, employeeBean.getEmail());
			get.setString(2, employeeBean.getPassword());
			rs = get.executeQuery();
			status = rs.next();
			rs.close();
		} catch (Exception e) {
			log.error(e);
		}
		return status;
	}

	public EmployeeBean getEmployeeByEmail(Connection con, String email) {
		EmployeeBean employeeBean = new EmployeeBean();
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (con != null) {
			try {
				String getData = "SELECT * FROM employee WHERE Email=?";
//				String getData = "SELECT employee.Id,employee.FirstName,employee.LastName,"
//						+ "employee.Email,assignproject.ProjectId,assignproject.ProjectStatus"
//						+ " from employee left JOIN assignproject ON employee.Id=assignproject.EmployeeId WHERE Email=?";
				ps = con.prepareStatement(getData);
				ps.setString(1, email);
				rs = ps.executeQuery();
				if (rs.next()) {
					employeeBean.setId(rs.getInt(1));
					employeeBean.setFirstName(rs.getString(2));
					employeeBean.setLastName(rs.getString(3));
					employeeBean.setEmail(rs.getString(4));
					employeeBean.setPassword(rs.getString(5));
					//employeeBean.setProjectId(rs.getString("ProjectId"));
				}
			} catch (Exception e) {
				System.err.println("not ok");
			}
		} else {
			log.error("Connection is Null");
		}

		return employeeBean;
	}

	public int updateEmployeeDetails(Connection con, EmployeeBean bean) {

		PreparedStatement update = null;
		int status = 0;
		if (con != null) {
			try {
				String sql = "update employee set FirstName=?,LastName=?,Password=? where Email=?";
				update = con.prepareStatement(sql);
				update.setString(1, bean.getFirstName());
				update.setString(2, bean.getLastName());
				update.setString(3, bean.getPassword());
				update.setString(4, bean.getEmail());
				status = update.executeUpdate();
			} catch (Exception e) {
				log.error(e);
			}
		} else {
			return 0;
		}
		return status;
	}

	public List<EmployeeBean> showEmployee(Connection con) {
		List<EmployeeBean> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String get = "SELECT * FROM employee";
			ps = con.prepareStatement(get);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeBean eBean = new EmployeeBean();
				eBean.setId(rs.getInt(1));
				eBean.setFirstName(rs.getString(2));
				eBean.setLastName(rs.getString(3));
				eBean.setEmail(rs.getString(4));
				list.add(eBean);
			}
		} catch (Exception e) {

		}
		return list;
	}
}
