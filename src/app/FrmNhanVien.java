package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mindfusion.drawing.Colors;
import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.DAONhanVien;
import dao.DAOTK;
import entity.NhanVien;
import entity.TaiKhoan;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmNhanVien extends JFrame implements ActionListener, MouseListener {
	private Panel pMain;
	private DAONhanVien daoNhanVien;
	private DAOTK daoTK;
	private JLabel lblNhapThongTin;
	private JLabel lblHoTen;
	private JLabel lblMa;
	private JLabel lblCCCD;
	private JLabel NgaySinh;
	private JLabel lblSDT;
	private JLabel lblchucVu;
	private JLabel GioiTinh;
	private JLabel lblDiaChi;
	private JTextField txtTim;
	private JTextField txtHoTen;
	private JTextField txtMa;
	private JTextField txtCCCD;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JComboBox<Object> cbChucVu, cboGioiTinh;
	private JDateChooser chooserNgaySinh;

	private JTable tblNV;
	private DefaultTableModel modelNV;
	private boolean statusEdit = false;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JButton btnTim;
	private JPanel pNhapThongTin;
	private String sHeaderTenNV, sHeaderMaNV;
	private Date dNgayHienTai;
	private SimpleDateFormat dfNgaySinh;
	private DecimalFormat dfLuong;

	public Panel getPanel() {
		return pMain;
	}

	public FrmNhanVien(String sHeaderTenNV, String sHeaderMaNV, Date dNgayHienTai) {
		this.sHeaderMaNV = sHeaderMaNV;
		this.sHeaderTenNV = sHeaderTenNV;
		this.dNgayHienTai = dNgayHienTai;
		try {
			ConnectDB.getinstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		khai bao dao
		daoNhanVien = new DAONhanVien();
		daoTK = new DAOTK();
		dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
		dfLuong = new DecimalFormat("##,###,###");
//		Khai bao entity NhanVIen
		NhanVien nv = new NhanVien();

		getContentPane().setLayout(null);
		pMain = new Panel();
		pMain.setBackground(Color.WHITE);
		pMain.setBounds(0, 0, 1278, 629);
		pMain.setLayout(null);
		getContentPane().add(pMain);

		JLabel lblTim = new JLabel("Tìm kiếm:");
		lblTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTim.setBounds(332, 11, 90, 35);
		pMain.add(lblTim);
//		
		txtTim = new JTextField();
		txtTim.setText("Tìm nhân viên theo mã nhân viên");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);
		txtTim.setBorder(new LineBorder(new Color(114, 23, 153), 2, true));
		txtTim.setBounds(414, 11, 540, 33);
		pMain.add(txtTim);
//		
		btnTim = new JButton("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnTim.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnTim.setBackground(new Color(114, 23, 153));
		btnTim.setBounds(964, 12, 127, 33);

		pMain.add(btnTim);
//		
		pNhapThongTin = new JPanel();
		pNhapThongTin.setBorder(new LineBorder(new Color(114, 23, 153)));
		pNhapThongTin.setBackground(Color.WHITE);
		pNhapThongTin.setBounds(2, 56, 1258, 300);
		pNhapThongTin.setLayout(null);
		pMain.add(pNhapThongTin);
//
		lblNhapThongTin = new JLabel("Nhập thông tin nhân viên");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNhapThongTin.setBounds(10, 10, 239, 29);
		pNhapThongTin.add(lblNhapThongTin);

		JLabel lblHoTen = new JLabel("Họ và tên:");
		lblHoTen.setBounds(10, 61, 90, 29);
		lblHoTen.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblHoTen);
		txtHoTen = new JTextField();
		txtHoTen.setBounds(110, 62, 454, 28);
		txtHoTen.setFont(new Font("arial", Font.PLAIN, 15));
		txtHoTen.setColumns(10);
		txtHoTen.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(txtHoTen);

		lblMa = new JLabel("Mã NV:");
		lblMa.setBounds(596, 61, 90, 29);
		lblMa.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblMa);
		txtMa = new JTextField();
		txtMa.setBounds(675, 61, 200, 29);
		txtMa.setFont(new Font("arial", Font.PLAIN, 15));
		pNhapThongTin.add(txtMa);

		lblCCCD = new JLabel("CCCD:");
		lblCCCD.setBounds(900, 61, 90, 29);
		lblCCCD.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblCCCD);
		txtCCCD = new JTextField();
		txtCCCD.setBounds(970, 61, 200, 29);
		txtCCCD.setFont(new Font("arial", Font.PLAIN, 15));
		pNhapThongTin.add(txtCCCD);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(10, 120, 90, 29);
		lblDiaChi.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblDiaChi);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(110, 120, 454, 28);
		pNhapThongTin.add(txtDiaChi);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setBounds(596, 120, 90, 23);
		lblNgaySinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblNgaySinh);
		chooserNgaySinh = new JDateChooser();
		chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgaySinh.setBounds(675, 120, 200, 25);
		chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
		chooserNgaySinh.setDate(dNgayHienTai);
		chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
		chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
		Icon iconCalendar = IconFontSwing.buildIcon(FontAwesome.CALENDAR, 17, Color.white);
		chooserNgaySinh.setIcon((ImageIcon) iconCalendar);
		pNhapThongTin.add(chooserNgaySinh);

		lblSDT = new JLabel("SDT:");
		lblSDT.setBounds(900, 120, 90, 29);
		lblSDT.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblSDT);
		txtSDT = new JTextField();
		txtSDT.setBounds(970, 120, 200, 29);
		pNhapThongTin.add(txtSDT);

		lblchucVu = new JLabel("Chức vụ:");
		lblchucVu.setBounds(10, 170, 90, 29);
		lblchucVu.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblchucVu);
		cbChucVu = new JComboBox<Object>(new Object[] { "Quản lý", "Nhân viên" });
		cbChucVu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cbChucVu.setBounds(110, 170, 191, 25);
		cbChucVu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cbChucVu.setBackground(Color.white);
		cbChucVu.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		pNhapThongTin.add(cbChucVu);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setBounds(596, 170, 88, 23);
		lblGioiTinh.setFont(new Font("SansSerif", Font.BOLD, 15));
		pNhapThongTin.add(lblGioiTinh);
		cboGioiTinh = new JComboBox<Object>(new Object[] { "Nam", "Nữ" });
		cboGioiTinh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboGioiTinh.setBounds(675, 170, 191, 25);
		cboGioiTinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cboGioiTinh.setBackground(Color.white);
		cboGioiTinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
		cboGioiTinh.setToolTipText("Chọn giới tính");
		pNhapThongTin.add(cboGioiTinh);

		btnThem = new JButton("Thêm");
		btnThem.setForeground(Color.black);
		btnThem.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnThem.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnThem.setBackground(new Color(57, 210, 247));
		btnThem.setBounds(234, 220, 145, 38);
		pNhapThongTin.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setForeground(Color.black);
		btnSua.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSua.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnSua.setBackground(new Color(133, 217, 191));
		btnSua.setBounds(442, 220, 145, 40);
		pNhapThongTin.add(btnSua);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setText("Làm mới");
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnLamMoi.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
		btnLamMoi.setBackground(new Color(114, 23, 153));
		btnLamMoi.setBounds(675, 220, 145, 37);
		pNhapThongTin.add(btnLamMoi);

		String colHeader[] = { "Mã NV", "Họ và tên Nhân viên", "Chức vụ", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT",
				"CCCD", "Lương", "Mật khẩu" };
		modelNV = new DefaultTableModel(colHeader, 0);
		tblNV = new JTable(modelNV);
		JScrollPane scrollPane = new JScrollPane(tblNV);
		scrollPane.setBounds(2, 360, 1258, 295); // replace x, y, width and height with desired values
		pMain.add(scrollPane);


		DocDULieuDatabaseVaoTable();
		checkEdit();

		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		tblNV.addMouseListener(this);
	}

	private void DocDULieuDatabaseVaoTable() {
		List<NhanVien> list = daoNhanVien.getAllDanhSachNV();
		for (NhanVien nv : list) {
			modelNV.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getChucVu(), nv.getGioiTinh(),
					nv.getNgaySinh(), nv.getDiaChi(), nv.getSoDienThoai(), nv.getSoCanCuoc(), nv.getLuong(),
					nv.getTaiKhoan().getMaTK() });
		}

	}

	private void checkEdit() {
//		 if(!statusEdit) {
//			 btnXoa.setVisible(false);
//			 btnHuy.setVisible(false);
//			 btnLamMoiNV.setVisible(true);
//			 btnThemNV.setEnabled(true);
//			 txtMaNV.setEditable(true);
//		 }
//		 else {
//			 btnXoa.setVisible(true);
//			 btnHuy.setVisible(true);
//			 btnLamMoiNV.setVisible(false);
//			 btnThemNV.setEnabled(false);
//		 }

	}
	private void lamMoi() {
		txtTim.setText("Tìm nhân viên theo mã nhân viên");
		txtTim.setFont(new Font("SansSerif", Font.ITALIC, 15));
		txtTim.setForeground(Colors.LightGray);

		txtHoTen.setText("");
		txtMa.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtCCCD.setText("");

		cbChucVu.setSelectedItem("Quản lý");
		cboGioiTinh.setSelectedItem("Nam");
//		chooserNgaySinh.setDate(d);

//		rdoTheoMaNV.setSelected(false);
//		rdoTheoTenNV.setSelected(false);
//		rdoTheoChucVuNV.setSelected(false);
		
		txtHoTen.requestFocus();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
//		private String maNhanVien;
//		private String tenNhanVien;
//		private String chucVu;
//		private String gioiTinh;
//		private Date ngaySinh;
//		private String diaChi;
//		private int soDienThoai;
//		private int soCanCuoc;
//		private double luong;
//		private TaiKhoan taiKhoan;
		int row = tblNV.getSelectedRow();
		txtMa.setText(modelNV.getValueAt(row, 0).toString());
		txtHoTen.setText(modelNV.getValueAt(row, 1).toString());
		cbChucVu.setSelectedItem(modelNV.getValueAt(row, 2).toString());
		cboGioiTinh.setSelectedItem(modelNV.getValueAt(row,3).toString());
		chooserNgaySinh.setDateFormatString(modelNV.getValueAt(row, 4).toString());
		txtDiaChi.setText(modelNV.getValueAt(row, 5).toString());
		txtSDT.setText(modelNV.getValueAt(row, 6).toString());
		txtCCCD.setText(modelNV.getValueAt(row, 7).toString());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLamMoi)) {
			lamMoi();
		}

	}

}
