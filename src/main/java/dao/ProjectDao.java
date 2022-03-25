package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bean.ProjectBean;

public class ProjectDao implements ProjectDaoInterface{
	
	static Logger log = Logger.getLogger(ProjectDao.class.getName());
	public int addProject(Connection con, ProjectBean pbean) {
		if (con != null) {
			PreparedStatement ps = null;
			int status = 0;
			try {
				String set = "INSERT INTO project(ProjectId,ProjectName,ProjectDesc) VALUES(?,?,?)";
				ps = con.prepareStatement(set);
				ps.setString(1, pbean.getProjectId());
				ps.setString(2, pbean.getProjectName());
				ps.setString(3, pbean.getProjectDesc());
				status = ps.executeUpdate();
				return status;
			} catch (Exception e) {
				log.error(e);
			}
			
		} else {
			log.error("Not Connected to Database :(");
		}
		return 0;
	}
	
	public List<ProjectBean> showProject(Connection con)
	{
		List<ProjectBean> list = new ArrayList<>();

	
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String get = "SELECT * FROM project";
			ps = con.prepareStatement(get);
			rs = ps.executeQuery();
			while(rs.next())
			{
				ProjectBean pbBean = new ProjectBean();
				pbBean.setProjectId(rs.getString(1));
				pbBean.setProjectName(rs.getString(2));
				pbBean.setProjectDesc(rs.getString(3));
				
				list.add(pbBean);
			}
			rs.close();
		} catch (Exception e) {
			
		}
		return list;
	}
}
