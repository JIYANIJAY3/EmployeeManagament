package dao;

import java.sql.Connection;

import bean.AdminBean;

public interface AdminDaoInterface {
	public boolean adminLogin(Connection con, AdminBean adminBean);
}
