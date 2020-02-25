package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.net.aso.s;

public class Pension_MemberDAO {
	private static Pension_MemberDAO instance;

	public static Pension_MemberDAO getInstance() {
		if (instance == null) {
			instance = new Pension_MemberDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

//	회원가입
	public void memberInsert(Pension_MemberDTO pm) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into pension_member values(?,?,?,?,?,?,?,sysdate,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, pm.getUserid());
			ps.setString(2, pm.getPwd());
			ps.setString(3, pm.getName());
			ps.setString(4, pm.getTel());
			ps.setString(5, pm.getEmail());
			ps.setString(6, pm.getZipcode());
			ps.setString(7, pm.getAddr());
			ps.setInt(8, pm.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

//	로그인
	public Pension_MemberDTO memberLogin(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Pension_MemberDTO pm = null;
		try {
			con = getConnection();
			String sql = "select * from pension_member where m_userid ='" + userid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				pm = new Pension_MemberDTO();
				if (rs.getString("m_pwd").equals(pwd)) {
					pm.setAdmin(rs.getInt("m_admin"));
					pm.setUserid(rs.getString("m_userid"));
					pm.setName(rs.getString("m_name"));
				} else {
					pm.setAdmin(2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return pm;
	}

//	회원정보
	public Pension_MemberDTO memberInfo(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Pension_MemberDTO pm = new Pension_MemberDTO();
		try {
			con = getConnection();
			String sql = "select * from pension_member where m_userid ='" + userid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				pm.setAddr(rs.getString("m_addr"));
				pm.setAdmin(rs.getInt("m_admin"));
				pm.setEmail(rs.getString("m_email"));
				pm.setJoined(rs.getString("m_joined"));
				pm.setName(rs.getString("m_name"));
				pm.setPwd(rs.getString("m_pwd"));
				pm.setTel(rs.getString("m_tel"));
				pm.setUserid(rs.getString("m_userid"));
				pm.setZipcode(rs.getString("m_zipcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return pm;
	}

//	회원정보 수정
	public void memberUpdate(Pension_MemberDTO pm) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "update pension_member set m_pwd=?,m_name=?,m_tel=?,m_email=?,m_zipcode=?,m_addr=?,m_admin=? where m_userid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pm.getPwd());
			ps.setString(2, pm.getName());
			ps.setString(3, pm.getTel());
			ps.setString(4, pm.getEmail());
			ps.setString(5, pm.getZipcode());
			ps.setString(6, pm.getAddr());
			ps.setInt(7, pm.getAdmin());
			ps.setString(8, pm.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}

	}

//	회원 탈퇴
	public void memberDelete(String userid) {
		Connection con = null;
		Statement st = null;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql = "delete from pension_comment where m_userid='"+userid+"'";			
			st.executeUpdate(sql);
			sql = "delete from pension_board where m_userid='"+userid+"'";
			st.executeUpdate(sql);
			sql = "delete from pension_member where m_userid='"+userid+"'";
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}

	}

//	아이디 중복체크
	public String memberIdCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String str = "YES";
		try {
			con = getConnection();
			String sql = "select * from pension_member where m_userid ='" + userid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				str = "NO";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return str;
	}

//	주소 체크
	public ArrayList<ZipCodeDTO> zipCheck(String dong) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ZipCodeDTO> arr = new ArrayList<ZipCodeDTO>();
		try {
			con = getConnection();
			String sql = "select * from zipcode where dong like'%" + dong + "%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ZipCodeDTO z = new ZipCodeDTO();
				z.setBunji(rs.getString("bunji"));
				z.setDong(rs.getString("dong"));
				z.setGugun(rs.getString("gugun"));
				z.setSeq(rs.getInt("seq"));
				z.setSido(rs.getString("sido"));
				z.setZipcode(rs.getString("zipcode"));
				arr.add(z);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return arr;

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
