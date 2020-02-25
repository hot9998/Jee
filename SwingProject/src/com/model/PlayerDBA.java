package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDBA {
	String url, user, pwd;

	// 생성자
	public PlayerDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "hr";
			pwd = "1234";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Player playerDetail(String num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Player p = new Player();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from player where num =" + num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				p.setNum(rs.getInt("num"));
				p.setName(rs.getString("name"));
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setWeight(rs.getDouble("weight"));
				p.setKind(rs.getString("kind"));
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				if (con != null)
					con.close();
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		return p;

	}

	// 선수추가
	public void playerInsert(Player p) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "insert into player values(player_seq.nextval," + "?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getBirth());
			ps.setDouble(3, p.getHeight());
			ps.setDouble(4, p.getWeight());
			ps.setString(5, p.getKind());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}

		}
	}

	// 전체보기
	public ArrayList<Player> playerView() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Player> arr = new ArrayList<Player>();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from player order by num";

			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Player p = new Player();
				p.setNum(rs.getInt("num"));
				p.setName(rs.getString("name"));
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setWeight(rs.getDouble("weight"));
				p.setKind(rs.getString("kind"));
				arr.add(p);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				if (con != null)
					con.close();
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
			}

		}
		return arr;
	}

	// 수정
	public void playerUpdate(Player p) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "update player set name=?,birth=?,height=?,weight=?,kind=? where num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getBirth());
			ps.setDouble(3, p.getHeight());
			ps.setDouble(4, p.getWeight());
			ps.setString(5, p.getKind());
			ps.setInt(6, p.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (con != null)
					con.close();
				if (ps != null)
					ps.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	// 삭제
	public void playerDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "delete from player where num =" + num;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (con != null)
					con.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	public ArrayList<Player> playerSearch(String column, String word) {
		ArrayList<Player> arr = new ArrayList<Player>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from player where " + column + " like '%" + word + "%' order by num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Player p = new Player();
				p.setNum(rs.getInt("num"));
				p.setName(rs.getString("name"));
				p.setBirth(rs.getString("birth"));
				p.setWeight(rs.getDouble("weight"));
				p.setHeight(rs.getDouble("height"));
				p.setKind(rs.getString("kind"));
				arr.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (con != null)
					con.close();
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return arr;
	}

}
