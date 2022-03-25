package dao;

import java.sql.Connection;
import java.util.List;

import bean.AssignProjectBean;
import bean.EmployeeBean;

interface AssignProjectDaoInterface {
	public int assignProject(Connection con, String[] checkboxValues, String projectId);
	List<AssignProjectBean> getProjectData(Connection con, int i);
	List<EmployeeBean> getEmployeeProjectStatus(Connection con,String i);
	public int changeAssignProjectStatus(Connection con, String[] checkboxValues, String Id);
	public int ChangeEmployeeProjectStatusToEnd(Connection con, String[] checkboxValues, String Id);
	public int removeAssignEmployee(Connection con,String[] checkboxValues,String Id);
}
