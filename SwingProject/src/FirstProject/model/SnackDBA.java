package FirstProject.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SnackDBA {
	private String url, user, password;

	public SnackDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "hr";
			password = "1234";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 안주 추가
	public void snackInsert(Snack sn) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into snack values (bar_seq.nextval,?,?,?)";
			con = DriverManager.getConnection(url, user, password);
			ps = con.prepareStatement(sql);
			ps.setString(1, sn.getName());
			ps.setInt(2, sn.getPrice());
			ps.setInt(3, sn.getStock());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}

	}

	// 재고 보기
	public ArrayList<Snack> snackView() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Snack> arr = new ArrayList<Snack>();
		try {
			String sql = "select * from snack order by num";
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Snack sn = new Snack();
				sn.setNum(rs.getInt("num"));
				sn.setName(rs.getString("name"));
				sn.setPrice(rs.getInt("price"));
				sn.setStock(rs.getInt("stock"));
				arr.add(sn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}

		return arr;

	}

	// 재고 변경
	public void snackAdd(int stock, int num) {
		Connection con = null;
		Statement st = null;
		try {
			String sql = "update snack set stock = " + stock + " where num = " + num;
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st);
		}
	}

	// 재고 삭제
	public void snackDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			String sql = "delete from snack where num =" + num;
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
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

	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
