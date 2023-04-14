package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import connection.ConnectDB;
import dao.DAONhanVien;
import dao.DAOTK;
import entity.NhanVien;
import entity.TaiKhoan;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;


public class FrmDangNhap extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtTaiKhoan;
	private JButton btnThoat;
	private JButton btnDangNhap;
	private JPasswordField txtMatKhau;
	private JLabel lblQuenMatKhau;
	private JLabel lblDangNhap;
	private JLabel lblTaiKhoan;
	private JLabel lblMatKhau;
	private JPopupMenu popUp;
	private DAONhanVien daoNhanVien;
	private DAOTK daoTK;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {	
					IconFontSwing.register(FontAwesome.getIconFont());
					FrmDangNhap frm = new FrmDangNhap();
					frm.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	public FrmDangNhap() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Đăng Nhập Quản Lý Linh Kiện Xe Máy");
		setSize(600,550);
		setLocationRelativeTo(null);
//		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.GRAY);
		
//		connect database
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		khai bao DAO
		daoNhanVien = new DAONhanVien();
		daoTK = new DAOTK();
		GUI();
	}
	
	private void GUI() {
		Font ft = new Font("SansSerif",Font.BOLD,30);
		Font ftItalic = new Font("SansSerif",Font.ITALIC,12);
		Font ftBtn = new Font("SansSerif",Font.BOLD,15);
		Box b = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5,b6,b7,b8;
		b.add(b1 = Box.createHorizontalBox());
		ImageIcon imageIcon = new ImageIcon("data\\img\\imgDangNhap.png");
		Image image = imageIcon.getImage();
		Image scaleImage = image.getScaledInstance(600, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaleImage);
		b1.add(new JLabel(scaledImageIcon));
		
		b.add(b2 = Box.createHorizontalBox());
		b2.add(lblDangNhap = new JLabel("Đăng Nhập"));
		lblDangNhap.setForeground(Color.WHITE);
		lblDangNhap.setFont(ft);
		b.add(Box.createVerticalStrut(20));
		
		
		b.add(b3 = Box.createHorizontalBox());
		b3.add(Box.createRigidArea(new Dimension(150,0)));
		b3.add(lblTaiKhoan = new JLabel("Tài Khoản: "));
		b3.add(txtTaiKhoan = new JTextField());
		b3.add(Box.createRigidArea(new Dimension(180,0)));
		b.add(Box.createVerticalStrut(20));
		
		b.add(b4 = Box.createHorizontalBox());
		b4.add(Box.createRigidArea(new Dimension(150,0)));
		b4.add(lblMatKhau = new JLabel("Mật Khẩu: "));
		b4.add(txtMatKhau = new JPasswordField());
		b4.add(Box.createRigidArea(new Dimension(180,0)));
		b.add(Box.createVerticalStrut(5));
		
		

//		b.add(b6 = Box.createHorizontalBox());
//		b6.add(btnDangNhap = new JButton("Đăng Nhập"));
//		btnDangNhap.setPreferredSize(new Dimension(500, 20));
//		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnDangNhap.setForeground(Color.WHITE);
//		btnDangNhap.setFont(ftBtn);
//		btnDangNhap.setBackground(new Color(164, 44,167));
//		btnDangNhap.setBorder(new LineBorder(Color.WHITE,1,true));
//		b.add(Box.createVerticalStrut(20));
//		
//		b.add(b7 = Box.createHorizontalBox());
//		b7.add(btnThoat = new JButton("Thoát"));
//		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnThoat.setForeground(Color.WHITE);
//		btnThoat.setFont(ftBtn);
//		btnThoat.setBackground(new Color(164, 44,167));
//		btnThoat.setBorder(new LineBorder(Color.WHITE,1,true));
//		b.add(Box.createVerticalStrut(20));
		
		b.add(Box.createVerticalStrut(20));
		b.add(b6 = Box.createHorizontalBox());
		b6.add(btnDangNhap = new JButton("Đăng Nhập"));
		btnDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangNhap.setForeground(Color.WHITE);
		btnDangNhap.setFont(ftBtn);
		btnDangNhap.setBackground(new Color(0,255,0));
		btnDangNhap.setBorder(new LineBorder(Color.WHITE,1,true));
		b6.add(Box.createRigidArea(new Dimension(50,0)));
		b6.add(btnThoat = new JButton("Thoát CT"));
		btnThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFont(ftBtn);
		btnThoat.setBackground(new Color(255 ,48 ,48));
		btnThoat.setBorder(new LineBorder(Color.WHITE,1,true));
		b.add(Box.createVerticalStrut(20));
//		

		
		
		add(b, BorderLayout.NORTH);
		
		
//		Canh deu
		lblMatKhau.setPreferredSize(lblTaiKhoan.getPreferredSize());
		btnDangNhap.setPreferredSize(btnThoat.getPreferredSize());
		
//		tai khoan, mat khau dang nhap
//		txtTaiKhoan.setText("NV002");
//		txtMatKhau.setText("NV002");
//		
		
		btnThoat.addActionListener(this);
		btnDangNhap.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThoat)) {
			System.exit(0);
		}else if(o.equals(btnDangNhap)) {
			dangNhap();
		}
		
	}
	private void dangNhap() {
		String maTK = txtTaiKhoan.getText().toString().trim();
		String mk = txtMatKhau.getText().toString().trim();
		TaiKhoan tk = daoTK.getTaiKhoanTheoMa(maTK);
		if(tk.getMaTK()== null) {
			JOptionPane.showMessageDialog(this, "Tài khoản không đúng!\nVui lòng kiểm tra lại.");
		}else if(!tk.getMatKhau().equalsIgnoreCase(mk)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không đúng!\nVui lòng kiểm tra lại.");
		}else {
			NhanVien nv = daoNhanVien.getNVTheoTK(tk.getMaTK());
			FrmQuanLy frmQLy = new FrmQuanLy(nv);
			frmQLy.setVisible(true);
			this.setVisible(false);
		}
	}

}
