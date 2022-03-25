package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AssignProjectBean;
import bean.EmployeeBean;

public class AssignProjectDao implements AssignProjectDaoInterface {

	@Override
	public int assignProject(Connection con, String[] checkboxValues, String projectId) {

		PreparedStatement ps = null;
		int status = 0;
		try {
			String set = "INSERT INTO assignproject(EmployeeId,ProjectId,ProjectStatus) VALUES(?,?,?)";
			ps = con.prepareStatement(set);
			for (int i = 0; i < checkboxValues.length; i++) {
				ps.setString(1, checkboxValues[i]);
				ps.setString(2, projectId);
				ps.setString(3, "UpComming");
				ps.addBatch();
			}
			ps.executeBatch();
			return status = 1;

		} catch (Exception e) {

		}

		return status;
	}

	@Override
	public List<AssignProjectBean> getProjectData(Connection con, int i) {
		List<AssignProjectBean> list = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String set = "SELECT ProjectId,ProjectStatus from assignproject where EmployeeId=?";
			// String set = "SELECT * FROM assignproject";

			ps = con.prepareStatement(set);
			ps.setInt(1, i);

			rs = ps.executeQuery();
			while (rs.next()) {
				AssignProjectBean assignProjectBean = new AssignProjectBean();
				assignProjectBean.setProjectId(rs.getString(1));
				assignProjectBean.setProjectStatus(rs.getString(2));
				list.add(assignProjectBean);
			}

		} catch (Exception e) {

		}
		return list;
	}

	@Override
	public List<EmployeeBean> getEmployeeProjectStatus(Connection con, String i) {

		List<EmployeeBean> list = new ArrayList<EmployeeBean>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String get = "select employee.Id, employee.FirstName,employee.LastName,"
					+ " assignproject.ProjectId,assignproject.ProjectStatus "
					+ " from employee RIGHT JOIN assignproject ON employee.Id=assignproject.EmployeeId where ProjectId=?";

			ps = con.prepareStatement(get);
			ps.setString(1, i);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmployeeBean employeeBean = new EmployeeBean();
				employeeBean.setId(rs.getInt("Id"));
				employeeBean.setFirstName(rs.getString("FirstName"));
				employeeBean.setLastName(rs.getString("LastName"));
				employeeBean.setProjectId(rs.getString("ProjectId"));
				employeeBean.setProjectStatus(rs.getString("ProjectStatus"));
				list.add(employeeBean);
			}

		} catch (Exception e) {
		}
		return list;
	}

	@Override
	public int changeAssignProjectStatus(Connection con, String[] checkboxValues, String Id) {
		PreparedStatement ps = null;
		int status = 0;
		try {
			// String set = "INSERT INTO assignproject(EmployeeId,ProjectId,ProjectStatus)
			// VALUES(?,?,?)";
			//String set = "update assignproject SET ProjectStatus=? where EmployeeId=? and ProjectId=?";
			String set = "update assignproject SET ProjectStatus=? where ProjectId=?";
			ps = con.prepareStatement(set);
			ps.setString(1, "start");
			ps.setString(2, Id);
			ps.executeUpdate();
			return status = 1;

		} catch (Exception e) {

		}
		return status;
	}

	public int ChangeEmployeeProjectStatusToEnd(Connection con, String[] checkboxValues, String Id) {
		PreparedStatement ps = null;
		int status = 0;
		try {
			// String set = "INSERT INTO assignproject(EmployeeId,ProjectId,ProjectStatus)
			// VALUES(?,?,?)";
			String set = "update assignproject SET ProjectStatus=? where ProjectId=?";

			ps = con.prepareStatement(set);
			ps.setString(1, "Complite");
			ps.setString(2, Id);
			ps.executeUpdate();
			return status = 1;

		} catch (Exception e) {

		}
		return status;
	}
	
	public int removeAssignEmployee(Connection con,String[] checkboxValues,String Id)
	{
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM assignproject where EmployeeId=? and ProjectId=?";
			ps = con.prepareStatement(sql);
			
			for(int i=0;i<checkboxValues.length;i++)
			{
				System.out.println(checkboxValues[i]);
				System.out.println(Id);
				ps.setString(1, checkboxValues[i]);
				ps.setString(2, Id);
				ps.addBatch();
			}
			ps.executeBatch();
			System.out.println("ok");
			return 1;
			
		} catch (Exception e) {
			
		}
		
		return 0;
		
	}
}
