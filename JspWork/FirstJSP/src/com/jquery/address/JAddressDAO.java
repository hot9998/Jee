package com.jquery.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JAddressDAO {

	private static JAddressDAO instance = new JAddressDAO();

	public static JAddressDAO getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	public void addressInsert(AddressVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into address values(address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getAddr());
			ps.setString(3, vo.getZipcode());
			ps.setString(4, vo.getTel());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}

	public ArrayList<AddressVO> getList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<AddressVO> arr = new ArrayList<AddressVO>();
		try {
			con = getConnection();
			String sql = "select * from address order by num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				AddressVO vo = new AddressVO();
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddr(rs.getString("addr"));
				vo.setTel(rs.getString("tel"));
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

	public ArrayList<ZipcodeVO> zipSearch(String dong) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ZipcodeVO> zarr = new ArrayList<ZipcodeVO>();
		try {
			con = getConnection();
			String sql = "select * from zipcode where dong like '%" + dong + "%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ZipcodeVO z = new ZipcodeVO();
				z.setBunji(rs.getString("bunji"));
				z.setDong(rs.getString("dong"));
				z.setGugun(rs.getString("gugun"));
				z.setSido(rs.getString("sido"));
				z.setZipcode(rs.getString("zipcode"));
				zarr.add(z);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return zarr;

	}
	
	public ArrayList<AddressVO> getSearch(String field, String word){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;		
		ArrayList<AddressVO> arr = new ArrayList<AddressVO>();
		try {
			con = getConnection();
			String sql = "select * from address where "+field+" like '%"+word+"%' order by num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				AddressVO vo = new AddressVO();
				vo.setNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddr(rs.getString("addr"));
				vo.setTel(rs.getString("tel"));
				arr.add(vo);				
			}			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}

}

