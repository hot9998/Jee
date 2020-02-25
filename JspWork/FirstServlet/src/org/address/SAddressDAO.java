package org.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SAddressDAO {
	private static SAddressDAO instance;

	public static SAddressDAO getInstance() {
		if (instance == null) {
			instance = new SAddressDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	// 추가
	public void addrInsert(AddressDTO ad) {
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
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}

	}

	// 리스트
	public ArrayList<AddressDTO> addressList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<AddressDTO> arr = new ArrayList<AddressDTO>();
		try {
			String sql = "select * from address order by num";
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				AddressDTO ad = new AddressDTO();
				ad.setNum(rs.getInt("num"));
				ad.setName(rs.getString("name"));
				ad.setAddr(rs.getString("addr"));
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

	// 상세 보기
	public AddressDTO addrView(int num) {
		AddressDTO ad = new AddressDTO();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from address where num = " + num;
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getInt("num"));
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

	// 수정 하기
	public void addrUpdate(AddressDTO ad) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String sql = "update address set name=?,addr=?,zipcode=?,tel=? where num = ?";
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getAddr());
			ps.setString(3, ad.getZipcode());
			ps.setString(4, ad.getTel());
			ps.setInt(5, ad.getNum());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}

	// 삭제하기
	public void addrDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			String sql = "delete from address where num="+num;
			con = getConnection();
			st = con.createStatement();
			st.executeUpdate(sql);			
		}catch (Exception e) {
			e.printStackTrace(); 
		}finally {
			closeConnection(con, st, null);
		}
	}

	// 우편번호 검색
	public ArrayList<ZipCodeDTO> zipSearch(String dong) {
		ArrayList<ZipCodeDTO> arr = new ArrayList<ZipCodeDTO>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from zipcode where dong like '%" + dong + "%'";
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ZipCodeDTO zip = new ZipCodeDTO();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSeq(rs.getInt("seq"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				arr.add(zip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
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
