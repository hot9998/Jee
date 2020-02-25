package com.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AddressDAO {
	private static AddressDAO instance = new AddressDAO();

	public static AddressDAO getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	// insert
	public void addrInsert(Address ad) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into address values(address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getAddr());
			ps.setString(3, ad.getZipcode());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}

	}

	public ArrayList<Address> addrList(String field,String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Address> arr = new ArrayList<Address>();
		try {
			con = getConnection();
			String sql = "";
			if(word.equals("")) {
				sql="select * from address order by num";				
			}else {
				sql="select * from address where "+field+" like '%"+word+"%' order by num";				
			}
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Address ad = new Address();
				ad.setNum(rs.getInt("num"));
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
				arr.add(ad);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}

		return arr;
	}

	// 주소록 갯수
	public int getCount(String field,String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "";
		try {
			con = getConnection();
			if(word.equals("")) {
				sql="select count(*) from address";
			}else {
				sql="select count(*) from address where "+field+" like '%"+word+"%'";
			}									
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return count;
	}

	// detail
	public Address addrDetail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Address ad = null;
		try {
			con = getConnection();
			String sql = "select * from address where num=" + num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ad = new Address();
				ad.setNum(rs.getInt("num"));
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return ad;
	}

	//수정 메소드
	public void addrUpdate(Address ad) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "update address set name=?,zipcode=?,addr=?,tel=? where num = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.setInt(5, ad.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}

	}

	// 삭제
	public void addrDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String sql = "delete address where num=" + num;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, st);
		}
	}

	//우편번호 검색
	public ArrayList<ZipCodeBean> zipfinder(String dong) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ZipCodeBean> zarr = new ArrayList<ZipCodeBean>();
		try {
			con = getConnection();
			String sql = "select * from zipcode where dong like '%" + dong + "%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ZipCodeBean z = new ZipCodeBean();
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

	// 종료 메소드
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeConnection(Connection con, Statement st) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
	}

}
