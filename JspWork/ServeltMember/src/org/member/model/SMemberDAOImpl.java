package org.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SMemberDAOImpl implements SMeberDAO {
	private static SMemberDAOImpl instance;

	public static SMemberDAOImpl getInstance() {
		if (instance == null) {
			instance = new SMemberDAOImpl();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	@Override
	public void memInsert(MemberDTO dto) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getUserid());
			ps.setString(3, dto.getPwd());
			ps.setString(4, dto.getPhone());
			ps.setString(5, dto.getEmail());
			ps.setInt(6, dto.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnetion(con, ps, null, null);
		}
	}

	@Override
	public ArrayList<MemberDTO> memList() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
		try {
			con = getConnection();
			String sql = "select * from member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setAdmin(rs.getInt("admin"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnetion(con, null, st, rs);
		}
		return arr;
	}

	@Override
	public void memUpdate(MemberDTO dto) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "update member set name=?,pwd=?,email=?,phone=?,admin=? where userid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getEmail());
			ps.setString(4, dto.getPhone());
			ps.setInt(5, dto.getAdmin());
			ps.setString(6, dto.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnetion(con, ps, null, null);
		}

	}

	@Override
	public void memDelete(String userid) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String sql = "delete from member where userid = '" + userid + "'";
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnetion(con, null, st, null);
		}

	}

	@Override
	public MemberDTO memDetail(String userid) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO dto = new MemberDTO();
		try {
			con = getConnection();
			String sql = "select * from member where userid = '" + userid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				dto.setAdmin(rs.getInt("admin"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnetion(con, null, st, rs);
		}
		return dto;

	}

	public MemberDTO loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO dto = null;
		try {
			con = getConnection();
			String sql = "select * from member where userid = '" + userid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) { // 회원인데 비밀번호가 맞는지는 모름
				dto = new MemberDTO();
				if (rs.getString("pwd").equals(pwd)) { // 비밀번호가 맞음
					dto.setAdmin(rs.getInt("admin")); // 1, 0
					dto.setUserid(rs.getString("userid"));
					dto.setName(rs.getString("name"));
				} else { // 비밀번호가 틀림
					dto.setAdmin(2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnetion(con, null, st, rs);
		}
		return dto;
	}

	public String idCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String flag = "YES";
		try {
			con = getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				flag = "NO";
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnetion(con, null, st, rs);
		}
		return flag;

	}

	// 종료 메소드
	private void closeConnetion(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
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
