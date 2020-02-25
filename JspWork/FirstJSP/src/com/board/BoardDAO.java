package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	//추가
	public void boardInsert(BoardVO bo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql ="";
		int number=0;
		//부모글
		 int num = bo.getNum();
		 int ref = bo.getRef();
		 int re_step =bo.getRe_step();
		 int re_level = bo.getRe_level();
				
		try {
			con = getConnection();
			ps = con.prepareStatement("select max(num) from board");
			rs= ps.executeQuery();
			if(rs.next()) {
				number = rs.getInt(1)+1;
			}else {
				number=1;
			}
			
			if(num!=0) {	//답변글
				sql = "update board set re_step=re_step+1 where ref=? and re_step>?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, ref);
				ps.setInt(2, re_step);
				ps.executeUpdate();
				re_step = re_step+1;
				re_level = re_level +1;
			}else {//새글
				ref = number;
				re_step = 0;
				re_level = 0;
			}
	
			 sql ="insert into "
						+ " board(num,writer,subject,email,passwd,content,"
						+ "             readcount,ip,reg_date,ref, re_step,re_level)"
						+ "  values(board_seq.nextval,?,?,?,?,?,0,?,to_char(sysdate,'yyyy/mm/dd'),?,?,?)";
		   ps= con.prepareStatement(sql);
		   ps.setString(1, bo.getWriter());
		   ps.setString(2, bo.getSubject());
		   ps.setString(3, bo.getEmail());
		   ps.setString(4, bo.getPasswd());
		   ps.setString(5, bo.getContent());
		   ps.setString(6, bo.getIp());
		   ps.setInt(7, ref);
		   ps.setInt(8,re_step);
		   ps.setInt(9, re_level);
		   ps.executeUpdate();
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con,ps,rs);
		}
		
	}
	//전체보기
	public ArrayList<BoardVO> getBoardList(String field, String word,
			                                               int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		ArrayList<BoardVO> arr = new ArrayList<BoardVO>();
		String sql="";
		try {
			con = getConnection();
			if(word.equals("")) { //검색아님
				sql = "select *  from (" + 
	   				    " select rownum rn, aa.*  from ( "+ 
	   				   	" select * from board order by ref desc, re_step asc)aa)" + 
	   				   	" where  rn >=? and rn<=?";
			}else {  //검색
				sql = "select *  from (" + 
	   				    " select rownum rn, aa.*  from ( "+ 
	   				   	" select * from board where "+field +" like '%"+word+"%' " +
	   				   	 "order by ref desc, re_step asc)aa)" + 
	   				   	" where  rn >=? and rn<=?";
			}
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs= ps.executeQuery();
			while(rs.next()) {
				BoardVO bo = new BoardVO();
				bo.setNum(rs.getInt("num"));
				bo.setWriter(rs.getString("writer"));
				bo.setSubject(rs.getString("subject"));
				bo.setContent(rs.getString("content"));
				bo.setEmail(rs.getString("email"));
				bo.setIp(rs.getString("ip"));
				bo.setReg_date(rs.getString("reg_date"));
				bo.setRe_level(rs.getInt("re_level"));
				bo.setReadcount(rs.getInt("readcount"));
				arr.add(bo);
     		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	//상세보기
	public BoardVO  boardView(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BoardVO vo=null;
		String sql ="";
		
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "update board set readcount= readcount+1 where num="+num;
			st.executeUpdate(sql);
			sql = "select * from board where num="+num;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				vo = new BoardVO();
				vo.setContent(rs.getString("content"));
				vo.setEmail(rs.getString("email"));
				vo.setIp(rs.getString("ip"));
				vo.setNum(rs.getInt("num"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setSubject(rs.getString("subject"));
				vo.setWriter(rs.getString("writer"));
				vo.setRef(rs.getInt("ref"));
				vo.setRe_step(rs.getInt("re_step"));
				vo.setRe_level(rs.getInt("re_level"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return vo;
	}
	//수정( 비번 맞으면 수정)
	public int bardUpdate(BoardVO vo) {
		Connection con= null;
		PreparedStatement ps = null;
		String sql="";
		ResultSet rs = null;
		int flag = 0; //비번틀림
		
		try {
			con =getConnection();
			sql = "select passwd from board where num="+vo.getNum();
			ps= con.prepareStatement(sql);
			rs = ps.executeQuery(); //비번구함
			if(rs.next()) {
				//rs.setString("passwd") == rs.getString(1)
				if(rs.getString(1).equals(vo.getPasswd())){ //비번 맞음
					sql = "update board set subject=?, email=?, content=? "
							+ " where num=?";
					ps = con.prepareStatement(sql);
					ps.setString(1, vo.getSubject());
					ps.setString(2, vo.getEmail());
					ps.setString(3, vo.getContent());
					ps.setInt(4, vo.getNum());
					flag = ps.executeUpdate(); //정상처리면 1 리턴
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	    finally {
	    	closeConnection(con, ps,rs);
	    }
		return flag;
	}
	//삭제
	public int boardDelete(int num, String passwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag =0;
		String sql="";
		try {
			con = getConnection();
			sql = "select passwd from board where num="+num;
			st = con.createStatement();
			rs= st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString(1).equals(passwd)) {
					sql = "delete from board where num =" + num;
					st = con.createStatement();
					flag = st.executeUpdate(sql);
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return flag;
	}
	// 게시글 수 
	public int getBoardCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql="";
		try {
			con = getConnection();
			if(word.equals("")) {//검색아님
				 sql = "select count(*) from board";
			}else {
				 sql = "select count(*) from board where "+field +" like '%"+word+"%'";
			}
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return count;
	}
	//comment 추가
	 public void commentInsert(CommentVO cvo) {
		 Connection con = null;
		 PreparedStatement ps= null;
		 
		 try {
			con = getConnection();
			//cnum, userid, regdate, msg, bnum
			String sql="insert into commentboard"
					+ "  values(commentboard_seq.nextval,?,sysdate,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, cvo.getUserid());
			ps.setString(2, cvo.getMsg());
			ps.setInt(3, cvo.getBnum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}

	 }
	 //comment 리스트
	 public ArrayList<CommentVO> commentList(int bnum){
		 Connection con = null;
		 Statement st = null;
		 ResultSet rs = null;
		 ArrayList<CommentVO> arr = new ArrayList<CommentVO>();
		 
		 try {
			con = getConnection();
			String sql="select * from commentboard"
					+ "  where bnum="+bnum +" order by cnum desc";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {	
				CommentVO cvo = new CommentVO();
				cvo.setBnum(rs.getInt("bnum"));
				cvo.setCnum(rs.getInt("cnum"));
				cvo.setMsg(rs.getString("msg"));
				cvo.setRegdate(rs.getString("regdate"));
				cvo.setUserid(rs.getString("userid"));
				arr.add(cvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		 return arr;
	 }
 	//닫기
		private void closeConnection(Connection con, Statement st , ResultSet rs) {
			try {
				if(rs!=null) rs.close();
				if(st!=null)  	st.close();
				if(con!=null ) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		private void closeConnection(Connection con, PreparedStatement ps) {
			try {
				if(ps!=null) ps.close();
				if(con!=null ) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}

