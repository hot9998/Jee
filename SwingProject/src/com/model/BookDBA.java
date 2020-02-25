package com.model;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDBA {
	// db 세팅
	private String url, user, pwd;

	public BookDBA() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user = "hr";
			pwd = "1234";
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// 추가
	public void bookInsert(BookBean book) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into book values(book_seq.nextval,?,?,?,?,?,?)";
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getWriter());
			ps.setString(3, book.getIndate());
			ps.setString(4, book.getOutdate());
			ps.setString(5, book.getGubun());
			ps.setInt(6, book.getPrice());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, ps);
		}

	}

	// 수정
	public void bookUpdate(BookBean book) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "update book set title=?,writer=?," + "indate=?,outdate=?,gubun=?,price=? where num = ?";
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getWriter());
			ps.setString(3, book.getIndate());
			ps.setString(4, book.getOutdate());
			ps.setString(5, book.getGubun());
			ps.setInt(6, book.getPrice());
			ps.setInt(7, book.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, ps);
		}

	}

	// 삭제
	public void bookDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			String sql = "delete from book where num = " + num;
			con = DriverManager.getConnection(url, user, pwd);
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, st);
		}

	}

	// 전체보기
	public ArrayList<BookBean> bookView() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BookBean> arr = new ArrayList<BookBean>();
		try {
			String sql = "select * from book order by num";
			con = DriverManager.getConnection(url, user, pwd);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				BookBean book = new BookBean();
				book.setNum(rs.getInt("num"));
				book.setTitle(rs.getString("title"));
				book.setWriter(rs.getString("writer"));
				book.setIndate(rs.getString("indate"));
				book.setOutdate(rs.getString("outdate"));
				book.setGubun(rs.getString("gubun"));
				book.setPrice(rs.getInt("price"));
				arr.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;

	}

	// 검색
	public ArrayList<BookBean> bookSearch(String column, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BookBean> arr = new ArrayList<BookBean>();
		try {
			String sql = "select * from book where " + column + " like '%" + word + "%'";
			con = DriverManager.getConnection(url, user, pwd);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				BookBean book = new BookBean();
				book.setNum(rs.getInt("num"));
				book.setTitle(rs.getString("title"));
				book.setWriter(rs.getString("writer"));
				book.setIndate(rs.getString("indate"));
				book.setOutdate(rs.getString("outdate"));
				book.setGubun(rs.getString("gubun"));
				book.setPrice(rs.getInt("price"));
				arr.add(book);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
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
			// TODO: handle exception
		}

	}

	public void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void closeConnection(Connection con, Statement st) {
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
