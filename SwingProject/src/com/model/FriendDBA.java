package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendDBA {
	private String url, user, pwd;

	// db 세팅
	public FriendDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "hr";
			pwd = "1234";
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 친구 추가
	public void insertFriend(Friend f) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into friend values(friend_seq.nextval,?,?,?,?)";
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getBirth());
			ps.setString(3, f.getPhone());
			ps.setString(4, f.getAddr());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, ps);
		}

	}

	// 친구 보기
	public ArrayList<Friend> viewFriend() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Friend> arr = new ArrayList<Friend>();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from friend order by num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Friend f = new Friend();
				f.setNum(rs.getInt("num"));
				f.setName(rs.getString("name"));
				f.setBirth(rs.getString("birth"));
				f.setPhone(rs.getString("phone"));
				f.setAddr(rs.getString("addr"));
				arr.add(f);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;

	}

	// 번호 검색
	public Friend numFriend(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Friend f = new Friend();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from friend where num = " + num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				f.setName(rs.getString("name"));
				f.setBirth(rs.getString("birth"));
				f.setPhone(rs.getString("phone"));
				f.setAddr(rs.getString("addr"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, st, rs);
		}
		return f;
	}

	// 친구 수정
	public void updateFriend(Friend f) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "update friend set name=?,birth=?,phone=?,addr=? where num = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getBirth());
			ps.setString(3, f.getPhone());
			ps.setString(4, f.getAddr());
			ps.setInt(5, f.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, ps);
		}

	}

	// 친구 삭제
	public void deleteFriend(int num) {
		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "delete from friend where num = " + num;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, st);
		}

	}

	// 친구 검색
	public ArrayList<Friend> searchFriend(String column, String input) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Friend> arr = new ArrayList<Friend>();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from friend where " + column + " like '%" + input + "%' order by num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Friend f = new Friend();
				f.setNum(rs.getInt("num"));
				f.setName(rs.getString("name"));
				f.setBirth(rs.getString("birth"));
				f.setPhone(rs.getString("phone"));
				f.setAddr(rs.getString("addr"));
				arr.add(f);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}

	public void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void closeConnection(Connection con, Statement st) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
