package app;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import entity.NhanVien;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class FrmQuanLy extends JFrame implements ActionListener, MouseListener {
	private NhanVien headerNV;
	private JPanel panelHeader;
	private JPanel pContent;
	private JLabel lblHeaderDate;
	private JLabel lblNgayHienTai;
	private JLabel lblHeaderTen;
	private JLabel lblHeaderMa;
	private JLabel lblMaNhanVien;
	private JButton btnDangXuat;
	private JButton btnMenuQLNhanVien;
	private JButton btnMenuQLLinhKien;
	private JButton btnMenuQLKhachHang;
	private JButton btnHeaderInfo;
	private JButton btnMenuQLThongKe;
	private Date dNow;
	private LocalDate now;
	private int ngay;
	private int thang;
	private int nam;
	private JPopupMenu popUp;
	private JMenuItem popItem;
	private FrmNhanVien frmNhanVien;

	public FrmQuanLy(NhanVien nv) {
		this.headerNV = nv;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Quản lý");
		setSize(1290, 800);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);
		Font ft = new Font("SansSerif", Font.BOLD, 15);
		Font ftDateTime = new Font("SansSerif", Font.BOLD, 13);
		Color colorViolet = new Color(114, 23, 153);
		Color colorWhite = new Color(255, 255, 255);
//		header
		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(0, 0, 1281, 54);
		panelHeader.setBackground(new Color(0 ,128 ,128));
		getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

//		hien thong tin nhan vien ngay tren header
//		ten NhanVien
		lblHeaderTen = new JLabel(headerNV.getTenNhanVien());
		lblHeaderTen.setFont(ft);
		lblHeaderTen.setForeground(colorWhite);
		lblHeaderTen.setBounds(100, 10, 170, 20);
		panelHeader.add(lblHeaderTen);
//		ma Nhan vien
		lblMaNhanVien = new JLabel("Mã nhân viên:");
		lblMaNhanVien.setForeground(Color.WHITE);
		lblMaNhanVien.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMaNhanVien.setBounds(100, 28, 110, 20);
		panelHeader.add(lblMaNhanVien);

		lblHeaderMa = new JLabel(headerNV.getMaNhanVien());
		lblHeaderMa.setForeground(Color.WHITE);
		lblHeaderMa.setFont(new Font("SansSerif", Font.ITALIC, 15));
		lblHeaderMa.setBounds(220, 28, 60, 20);
		panelHeader.add(lblHeaderMa);

//		hien ngay thang nam
		now = LocalDate.now();
		ngay = now.getDayOfMonth();
		thang = now.getMonthValue();
		nam = now.getYear();

		dNow = new Date(nam - 1900, thang - 1, ngay);
		lblHeaderDate = new JLabel("Hiện tại:");
		lblHeaderDate.setForeground(Color.WHITE);
		lblHeaderDate.setFont(ftDateTime);
		lblHeaderDate.setBounds(600, 23, 66, 21);
		panelHeader.add(lblHeaderDate);

		lblNgayHienTai = new JLabel(ngay + " / " + thang + " / " + nam);
		lblNgayHienTai.setForeground(Color.WHITE);
		lblNgayHienTai.setFont(ftDateTime);
		lblNgayHienTai.setBounds(670, 24, 151, 21);
		panelHeader.add(lblNgayHienTai);

		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(ft);
		btnDangXuat.setBounds(1100, 10, 150, 35);
		btnDangXuat.setBackground(new Color(0xE91940));
		Icon iconDangXuat = IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 25, new Color(255, 255, 255));
		btnDangXuat.setIcon(iconDangXuat);
		panelHeader.add(btnDangXuat);

		panelHeader.add(lblHeaderMa);
		btnHeaderInfo = new JButton();
		if (nv.getChucVu().equalsIgnoreCase("Thu ngân")) // quan ly????
			btnHeaderInfo.setText("QL");
		else
			btnHeaderInfo.setText("NV");
		JPanel pMenu = new JPanel();
//		pMenu.setBackground(new Color(221, 160, 221));   backgrough menu ???
		pMenu.setBounds(0, 54, 1281, 31);
		getContentPane().add(pMenu);
		pMenu.setLayout(null);

		int x = 119; // vi tri chieu ngang cua item

		btnMenuQLNhanVien = new JButton("Quản lý nhân viên");
		if(btnHeaderInfo.getText().contains("QL")) {
			
			btnMenuQLNhanVien.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnMenuQLNhanVien.setBackground(new Color(255, 240, 245));
			btnMenuQLNhanVien.setBounds(x, 0, 132, 31);
			btnMenuQLNhanVien.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnMenuQLNhanVien);
		
			
			x = x + 142; //  chuyen vi tri sang mot doan 
		}
		btnMenuQLKhachHang = new JButton("Quản lý khách Hàng");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("NV")) {
			
			
			btnMenuQLKhachHang.setLayout(null);
			btnMenuQLKhachHang.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnMenuQLKhachHang.setBackground(new Color(255, 240, 245));
			btnMenuQLKhachHang.setBounds(x, 0, 142, 31);  // 875
			btnMenuQLKhachHang.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnMenuQLKhachHang);
			x += 142;
			
			
		}
		btnMenuQLLinhKien = new JButton("Quản lý mặt hàng linh kiện");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("NV")) {
			
			if(btnMenuQLLinhKien.getText().contains("")) // ??? them dieu kien vao day
				x += 10;
			
			btnMenuQLLinhKien.setLayout(null);
			btnMenuQLLinhKien.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnMenuQLLinhKien.setBackground(new Color(255, 240, 245));
			btnMenuQLLinhKien.setBounds(x, 0, 149, 31);   // 574
			btnMenuQLLinhKien.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnMenuQLLinhKien);
			x = x + 142;
		}
		btnMenuQLThongKe = new JButton("Quản lý thống kê");
		if(btnHeaderInfo.getText().contains("QL")|| btnHeaderInfo.getText().contains("NV")) {
			x += 10;
			
			btnMenuQLThongKe.setLayout(null);
			btnMenuQLThongKe.setBorder(new LineBorder(new Color(0, 146, 182), 2, true));
			btnMenuQLThongKe.setBackground(new Color(255, 240, 245));
			btnMenuQLThongKe.setBounds(x, 0, 132, 31); // 1056
			btnMenuQLThongKe.setFont(new Font("SansSerif", Font.BOLD, 13));
			pMenu.add(btnMenuQLThongKe);
		}
		pContent = new JPanel();
		pContent.setBounds(0, 86, 1269, 629);
		getContentPane().add(pContent);
		pContent.setLayout(null);
		
		if(btnHeaderInfo.getText().contains("QL")){
			loadFrmNhanVien();
		}
//		su kien
		btnDangXuat.addActionListener(this);
		btnMenuQLNhanVien.addActionListener(this);
	}

	public void loadFrmNhanVien() {
		setTitle("Quản lý Nhân viên");
		pContent.removeAll();
		btnMenuQLNhanVien.setBackground(new Color(192, 255, 255));
		btnMenuQLNhanVien.setBorder(BorderFactory.createLineBorder(Color.white));
		frmNhanVien = new FrmNhanVien("QL", lblHeaderMa.getText(), dNow);
		pContent.add(frmNhanVien.getPanel());

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		if (o.equals(btnDangXuat)) {
			dangXuat();
		} else if (o.equals(btnMenuQLNhanVien)) {
			loadFrmNhanVien();
		}

	}

	private void dangXuat() {
		int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn đăng xuất không", "Chú ý",
				JOptionPane.YES_NO_OPTION);
		if (hoiNhac == JOptionPane.YES_OPTION) {
			FrmDangNhap frm = new FrmDangNhap();
			frm.setVisible(true);
			this.setVisible(false);
		}

	}
}
