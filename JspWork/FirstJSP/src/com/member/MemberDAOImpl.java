package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAOImpl implements MemberDAO {
	private static MemberDAOImpl instance = new MemberDAOImpl();

	public static MemberDAOImpl getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	@Override
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub\		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into member values(?,?,?,?,?,?)";
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getUserid());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getPhone());
			ps.setString(5, vo.getEmail());
			ps.setInt(6, vo.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}

	@Override
	public ArrayList<MemberVO> memberList() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberVO> arr = new ArrayList<MemberVO>();
		try {
			String sql = "select * from member";
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setName(rs.getString("name"));
				vo.setUserid(rs.getString("userid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
				vo.setAdmin(rs.getInt("admin"));
				arr.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		int flag=0;
		try {
			String sql = "update member set name=?,pwd=?,email=?,phone=?,admin=? where userid=?";
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getEmail());
			ps.setString(4, vo.getPhone());
			ps.setInt(5, vo.getAdmin());
			ps.setString(6, vo.getUserid());
			flag = ps.executeUpdate();
			// excuteUpdate가 정상적으로 수행되면 1이 리턴			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}

	@Override
	public MemberVO memberView(String userid) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberVO vo = new MemberVO();
		try {
			String sql = "select * from member where userid ='" + userid + "'";
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				vo.setAdmin(rs.getInt("admin"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setUserid(rs.getString("userid"));
				vo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return vo;
	}

	@Override
	public void memberDel(String userid) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		try {
			String sql = "delete from member where userid = '" + userid + "'";
			con = getConnection();
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}

	@Override
	public String idCheck(String userid) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String flag = "YES";
		try {
			String sql = "select * from member where userid='" + userid + "'";
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				flag = "NO";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}

	public int loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag = -1;
		try {
			con = getConnection();
			String sql = "select pwd,admin from member where userid='" + userid + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				if (pwd.equals(rs.getString("pwd"))) {
					flag = rs.getInt("admin"); //회원
					// admin값을 리턴해줘서 0이면 일반사용자, 1이면 관리자로 구분
				} else {
					flag = 2; //비밀번호 오류
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}

	private void closeConnection(Connection con, PreparedStatement ps) {
		// TODO Auto-generated method stub
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
