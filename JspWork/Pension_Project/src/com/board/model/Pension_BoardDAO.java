package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Pension_BoardDAO {
	private static Pension_BoardDAO instance;

	public static Pension_BoardDAO getInstance() {
		if (instance == null) {
			instance = new Pension_BoardDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

//	글쓰기
	public void boardWrite(Pension_BoardDTO pb) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into pension_board values(pension_board_seq.nextval,?,?,?,sysdate,?,0)";
			ps = con.prepareStatement(sql);
			ps.setString(1, pb.getUserid());
			ps.setString(2, pb.getTitle());
			ps.setString(3, pb.getContent());
			ps.setInt(4, pb.getType());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

//	글목록
	public ArrayList<Pension_BoardDTO> boardList(int btype, int startRow, int endRow) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Pension_BoardDTO> arr = new ArrayList<Pension_BoardDTO>();
		try {
			con = getConnection();
			String sql = "select * from "
					+ "(select rownum rn,aa.* from"
					+ "(select * from pension_board b join pension_member m"
					+ " on b.m_userid=m.m_userid where b_type="+btype+" order by b_num desc)aa )"
					+ " where rn >="+startRow+" and rn<="+endRow;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Pension_BoardDTO pb = new Pension_BoardDTO();
				pb.setContent(rs.getString("b_content"));
				pb.setCount(Integer.parseInt(rs.getString("b_count")));
				pb.setDate(rs.getString("b_date"));
				pb.setNum(rs.getInt("b_num"));
				pb.setTitle(rs.getString("b_title"));
				pb.setUserid(rs.getString("m_userid"));
				pb.setType(rs.getInt("b_type"));
				pb.setName(rs.getString("m_name"));
				arr.add(pb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

//	글보기
	public Pension_BoardDTO boardView(int bnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Pension_BoardDTO pb = new Pension_BoardDTO();
		try {
			con = getConnection();
			String sql = "select * from pension_board b join pension_member m"
					+ " on b.m_userid=m.m_userid where b.b_num=" + bnum;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				pb.setName(rs.getString("m_name"));
				pb.setContent(rs.getString("b_content"));
				pb.setCount(rs.getInt("b_count"));
				pb.setDate(rs.getString("b_date"));
				pb.setNum(rs.getInt("b_num"));
				pb.setTitle(rs.getString("b_title"));
				pb.setType(rs.getInt("b_type"));
				pb.setUserid(rs.getString("m_userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return pb;

	}

//	글 삭제
	public void boardDelete(int bnum) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String sql = "delete from pension_comment where b_num=" + bnum;
			st = con.createStatement();
			st.executeUpdate(sql);
			sql = "delete from pension_board where b_num=" + bnum;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
	}

//	글 수정
	public void boardUpdate(Pension_BoardDTO pb) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "update pension_board set b_title=?,b_content=?,b_date=sysdate where b_num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pb.getTitle());
			ps.setString(2, pb.getContent());
			ps.setInt(3, pb.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

// 조회수
	public void boardCount(int bnum) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String sql = "update pension_board set b_count = b_count+1 where b_num=" + bnum;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
	}

// 글 번호
	public int boardNum(int btype) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int boardNum = 0;
		try {
			con = getConnection();
			String sql = "select count(*) from pension_board where b_type=" + btype;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				boardNum = rs.getInt(1);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return boardNum;
	}

//	댓글 쓰기
	public void commentWrite(Pension_commentDTO pc) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into pension_comment values(pension_comment_seq.nextval,?,?,?,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, pc.getUserid());
			ps.setInt(2, pc.getBnum());
			ps.setString(3, pc.getContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

//	댓글 삭제
	public void commentDelete(int cnum) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String sql = "delete from pension_comment where c_num=" + cnum;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, null);
		}
	}

//	댓글 목록
	public ArrayList<Pension_commentDTO> commentView(int bnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Pension_commentDTO> arr = new ArrayList<Pension_commentDTO>();
		try {
			con = getConnection();
			String sql = "select * from pension_comment c join pension_member m"
					+ " on c.m_userid=m.m_userid where c.b_num=" + bnum + " order by c_date ,c_num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Pension_commentDTO pc = new Pension_commentDTO();
				pc.setName(rs.getString("m_name"));
				pc.setContent(rs.getString("c_content"));
				pc.setDate(rs.getString("c_date"));
				pc.setUserid(rs.getString("m_userid"));
				pc.setNum(rs.getInt("c_num"));
				arr.add(pc);
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
