package dao;

import java.sql.Connection;
import java.util.List;

import bean.ProjectBean;

public interface ProjectDaoInterface {
	public int addProject(Connection con, ProjectBean pbean);
	public List<ProjectBean> showProject(Connection con);
}
