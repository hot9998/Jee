package db.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public ArrayList getUserInfo() throws Exception{
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
		while(rs.next()) {
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
		}
		System.out.println(sb.toString());
		return res;	
	}

}
