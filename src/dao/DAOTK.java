package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.ConnectDB;
import entity.TaiKhoan;

public class DAOTK implements Serializable{
//	tạo tài khoản khi thêm
	public boolean createTK(TaiKhoan tk) throws SQLException{
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into TaiKhoan values (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tk.getMaTK());
			ps.setString(2, tk.getMatKhau());
			return ps.executeUpdate()>0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close();
		return false;
	}
	public boolean suaTK(TaiKhoan tk) {
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update TaiKhoan set matKhau = ? where maTK = ?");
			stmt.setString(2, tk.getMaTK());
			stmt.setString(1, tk.getMatKhau());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	
	
//	load ds tk
	public ArrayList<TaiKhoan> getDanhSachTK(){
		ArrayList<TaiKhoan> lstTK = new ArrayList<TaiKhoan>();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from TaiKhoan");
			ResultSet rs  = ps.executeQuery();
			while(rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				tk.setMaTK(rs.getString(1));
				tk.setMatKhau(rs.getString(2));
				lstTK.add(tk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstTK;
	}
//Load ds matkhau
		public TaiKhoan getMatKhauTheoMaNV(String ma) {
			TaiKhoan tk=new TaiKhoan();
			ConnectDB.getinstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from TaiKhoan where maTK = '"+ma+"'"; 
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					tk.setMaTK(rs.getString(1));
					tk.setMatKhau(rs.getString(2));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return tk;
		}
		
	public TaiKhoan getTaiKhoanTheoMa(String maTK) { 
		TaiKhoan tk = new TaiKhoan();
		ConnectDB.getinstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from TaiKhoan where maTK = '"+maTK+"'";
		
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				tk.setMaTK(rs.getString(1));
				tk.setMatKhau(rs.getString(2));
				
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
			}
			
			return tk;
		
	}
}
