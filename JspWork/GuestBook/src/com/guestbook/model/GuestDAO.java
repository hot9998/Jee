package com.guestbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GuestDAO {
	private static GuestDAO instance = new GuestDAO();

	// db세팅
	public static GuestDAO getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/guest");
		return ds.getConnection();
	}

	// 데이터 입력
	public void guestInsert(GuestDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,sysdate,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getGrade());
			ps.setString(4, dto.getIpaddr());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

	// 리스트
	public ArrayList<GuestDTO> guestList(String field, String word,int startRow,int endRow) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<GuestDTO> arr = new ArrayList<GuestDTO>();
		try {
			con = getConnection();
			String sql = "";
			if (word.equals("")) {
				sql = "select * from "
						+ "(select rownum rn,aa.* from"
						+ "(select * from guestbook order by num desc)aa )"
						+ " where rn >="+startRow+" and rn<="+endRow;
			} else {
				sql = "select * from "
						+ "(select rownum rn,aa.* from"
						+ "(select * from guestbook"
						+ " where "+field+" like '%"+word+"%' order by num desc)aa )"
						+ " where rn >="+startRow+" and rn<="+endRow;
			}

			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				GuestDTO dto = new GuestDTO();
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setGrade(rs.getString("grade"));
				dto.setIpaddr(rs.getString("ipaddr"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

	// 게시물 수
	public int guestCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			String sql="";
			if(word.equals("")) {
				sql="select count(*) from guestbook";
			}else {
				sql="select count(*) from guestbook where "+field+" like '%"+word+"%'";				
			}			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}

	// 상세 보기
	public GuestDTO guestView(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		GuestDTO dto = new GuestDTO();
		try {
			con = getConnection();
			String sql = "select * from guestbook where num=" + num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setGrade(rs.getString("grade"));
				dto.setIpaddr(rs.getString("ipaddr"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return dto;

	}

	// 로그인
	public boolean loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "select * from member where userid='" + userid + "' and pwd='" + pwd + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return flag;
	}

	// 게시물 삭제
	public void guestDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String sql = "delete from guestbook where num=" + num;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}

	}

	private void closeConnection(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
