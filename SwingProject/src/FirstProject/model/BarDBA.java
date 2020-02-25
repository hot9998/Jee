package FirstProject.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BarDBA {
	private String url, user, password;

	public BarDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "hr";
			password = "1234";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 테이블 보기
	public ArrayList<Bar> barView(String tname) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Bar> arr = new ArrayList<Bar>();
		try {
			String sql = "select * from " + tname + " order by num";
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Bar bar = new Bar();
				bar.setNum(rs.getInt("num"));
				bar.setName(rs.getString("name"));
				bar.setPrice(rs.getInt("price"));
				bar.setCount(rs.getInt("count"));
				bar.setMoney(rs.getInt("money"));
				arr.add(bar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}

		return arr;

	}

	// 상품 취소
	public void barDelete(String tname, Bar bar) {
		Connection con = null;
		Statement st = null;
		try {
			String sql = "delete from " + tname + " where num =" + bar.getNum();
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			String sql2 = "update snack set stock = stock +" + bar.getCount() + " where name = '" + bar.getName() + "'";
			st.executeUpdate(sql);
			st.executeUpdate(sql2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st);
		}

	}

	// 안주 판매
	public void barOrder(String tname, Snack sn, int count) {
		Connection con = null;
		Statement st = null;
		try {
			String sql = "insert into " + tname + " values (bar_seq.nextval ,'" + sn.getName() + "'," + sn.getPrice()
					+ "," + count + "," + sn.getPrice() * count + ")";
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 계산
	public void barAccount(String tname, Bar bar) {
		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			String sql1 = "update manager set money = money +" + bar.getMoney();
			String sql2 = "insert into table0 values (bar_seq.nextval,'" + bar.getName() + "'," + bar.getPrice() + ","
					+ bar.getCount() + "," + bar.getMoney() + ")";
			String sql3 = "delete from " + tname;

			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st);
		}

	}

	// 초기화
	public void barReset() {
		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();			
			String sql = "delete from table0";			
			st.executeUpdate(sql);
			sql = "delete from table1";			
			st.executeUpdate(sql);
			sql = "delete from table2";			
			st.executeUpdate(sql);
			sql = "delete from table3";			
			st.executeUpdate(sql);
			sql = "delete from table4";			
			st.executeUpdate(sql);
			sql = "delete from table5";			
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st);
		}
	}

	private void closeConnection(Connection con, Statement st) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}