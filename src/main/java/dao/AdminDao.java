package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import bean.AdminBean;

public class AdminDao implements AdminDaoInterface{

	static Logger log = Logger.getLogger(AdminDao.class.getName());

	@Override
	public boolean adminLogin(Connection con, AdminBean adminBean) {

		if (con != null) {
			PreparedStatement get = null;
			ResultSet rs = null;
			boolean status = false;
			try {
				String getData = "SELECT * FROM admin WHERE Email=? and Password=?";
				get = con.prepareStatement(getData);
				get.setString(1, adminBean.getEmail());
				get.setString(2, adminBean.getPassword());
				rs = get.executeQuery();
				status = rs.next();
				rs.close();
			} catch (Exception e) {
				log.error(e);
			}
			return status;
		} else {

		}
		return false;
	}
}
