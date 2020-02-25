package db.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.net.aso.p;

public class QueryBean {
	Connection con;
	Statement st;
	ResultSet rs;

	public QueryBean() {
		con = null;
		st = null;
		rs = null;
	}

	public void getConnection() {
		try {
			con = DBConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList getUserInfo() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append("		u_id,u_name,u_phone,u_grade,write_time ");
		sb.append(" from ");
		sb.append(" 	user_info_sample ");
		sb.append(" order by ");
		sb.append(" 	write_time ");

//		System.out.println(sb.toString());						
		rs = st.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
		}
		System.out.println(sb.toString());
		return res;
	}

	public int setUserInfo(String id, String name, String phone, String grade) {

		int result = 0;

		StringBuffer sb = new StringBuffer();
		PreparedStatement ps = null;

		sb.append("insert into user_info_sample values");
		// µð¹ö±ë ¿ë
		// sb.append("('" + id + "','" + name + "','" + phone + "'," + grade + ",
		// sysdate)");
		// System.out.print(sb.toString());
		sb.append("(?,?,?,?,sysdate)");
		try {
			ps = con.prepareStatement(sb.toString());
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, phone);
			ps.setString(4, grade);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteUserInfo(String id) throws Exception {
		int result = 0;

		PreparedStatement ps = null;

		StringBuffer sb = new StringBuffer();

		sb.append("delete from user_info_sample where u_id = ?");

		try {
			ps = con.prepareStatement(sb.toString());
			ps.clearParameters();
			ps.setString(1, id);

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
