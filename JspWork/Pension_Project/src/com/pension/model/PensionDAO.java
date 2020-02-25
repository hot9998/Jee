package com.pension.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PensionDAO {
	private static PensionDAO instance;

	public static PensionDAO getInstance() {
		if (instance == null) {
			instance = new PensionDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

//	∞¥Ω« ±€æ≤±‚
	public void pensionWrite(PensionDTO p) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into pension_rooms values(pension_rooms_seq.nextval,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getContent());
			ps.setInt(3, p.getPeakPrice());
			ps.setInt(4, p.getLowseasonPrice());
			ps.setInt(5, p.getSperson());
			ps.setInt(6, p.getMperson());
			ps.setString(7, p.getSrc());
			ps.setString(8, p.getImgname());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, null, null);
		}
	}

//	∞¥Ω« ∏ÆΩ∫∆Æ
	public ArrayList<PensionDTO> pensionList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<PensionDTO> arr = new ArrayList<PensionDTO>();
		try {
			con = getConnection();
			String sql = "select * from pension_rooms order by r_num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				PensionDTO p = new PensionDTO();
				p.setContent(rs.getString("r_content"));
				p.setLowseasonPrice(rs.getInt("r_lowseason_price"));
				p.setMperson(rs.getInt("r_maximum_person"));
				p.setName(rs.getString("r_name"));
				p.setNum(rs.getInt("r_num"));
				p.setPeakPrice(rs.getInt("r_peak_price"));
				p.setSperson(rs.getInt("r_standard_person"));
				p.setSrc(rs.getString("r_img_src"));
				p.setImgname(rs.getString("r_img_name"));
				arr.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

//	∞¥Ω« ¡§∫∏
	public PensionDTO pensionView(int rnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PensionDTO p = new PensionDTO();
		try {
			con = getConnection();
			String sql = "select * from pension_rooms where r_num=" + rnum;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				p.setContent(rs.getString("r_content"));
				p.setLowseasonPrice(rs.getInt("r_lowseason_price"));
				p.setMperson(rs.getInt("r_maximum_person"));
				p.setName(rs.getString("r_name"));
				p.setNum(rs.getInt("r_num"));
				p.setPeakPrice(rs.getInt("r_peak_price"));
				p.setSperson(rs.getInt("r_standard_person"));
				p.setSrc(rs.getString("r_img_src"));
				p.setImgname(rs.getString("r_img_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, null, st, rs);
		}
		return p;

	}

//	∞¥Ω« ±€ªË¡¶
	public void pensionDelete(int rnum) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String sql = "delete from pension_rooms where r_num=" + rnum;
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
