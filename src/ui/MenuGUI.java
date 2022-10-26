package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import imgavt.ImageAvatar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Image ICON_APPLICATION = new ImageIcon("images\\icon_application.png").getImage();
	private JPanel contentPane;
	private SideMenuPanel side;
	private JLabel lblName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JPanel pnMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					MenuGUI frame = new MenuGUI();
					frame.setTitle("Phần mềm tính lương nhân sự");
					frame.setIconImage(ICON_APPLICATION);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnContainer = new JPanel();
		contentPane.add(pnContainer);
		
		JPanel pnContent = new JPanel();
		pnContent.setBounds(66, 0, 1199, 690);
		pnContent.setBackground(new Color(128, 128, 0));
		
		JPanel pnSideMenu = new JPanel();
		pnSideMenu.setBounds(0, 0, 60, 691);
		pnSideMenu.setBorder(new EmptyBorder(0, 10, 0, 0));
		pnSideMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				side.onSideMenu();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				side.onSideMenu();
			}
		});
		pnSideMenu.setBackground(new Color(16, 84, 129));
		
		ImageAvatar imageAvatar = new ImageAvatar();
		imageAvatar.setBounds(0, 23, 108, 100);
		imageAvatar.setIcon(new ImageIcon("images\\avatar.jpg"));
		
		lblName = new JLabel("Nguyễn Văn A");
		lblName.setBounds(0, 134, 160, 33);
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setPreferredSize(new Dimension(pnSideMenu.getWidth(), pnSideMenu.getHeight()));
//		lblName.setVisible(false);
		
		pnMenu = new JPanel();
		pnMenu.setBounds(0, 180, 230, 465);
		pnMenu.setBackground(new Color(16, 84, 129));
		pnMenu.setLayout(new GridLayout(8, 1, 0, 10));
		
		JPanel pnNhanSu = new JPanel();
		pnNhanSu.setBackground(new Color(16, 84, 129));
		pnNhanSu.setSize(new Dimension(100, 100));
		pnNhanSu.setBorder(new MatteBorder(0, 10, 0, 0, Color.RED));
		pnMenu.add(pnNhanSu);
		pnNhanSu.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNhanSu = new JLabel("NHÂN SỰ");
		lblNhanSu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhanSu.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanSu.setForeground(new Color(255, 255, 255));
		pnNhanSu.add(lblNhanSu);
		
		JLabel lblIconNhanSu = new JLabel();
		Image imgNhanSu = new ImageIcon("images\\side_menu\\businessman.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconNhanSu.setIcon(new ImageIcon(imgNhanSu));
		pnNhanSu.add(lblIconNhanSu, BorderLayout.WEST);
		
		JPanel pnPhongBan = new JPanel();
		pnPhongBan.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(16, 84, 129)));
		pnPhongBan.setBackground(new Color(16, 84, 129));
		pnMenu.add(pnPhongBan);
		pnPhongBan.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPhongBan = new JLabel("PHÒNG BAN");
		lblPhongBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhongBan.setForeground(new Color(255, 255, 255));
		lblPhongBan.setHorizontalAlignment(SwingConstants.CENTER);
		pnPhongBan.add(lblPhongBan, BorderLayout.CENTER);
		
		JLabel lblIconPhongBan = new JLabel();
		Image imgPhongBan = new ImageIcon("images\\side_menu\\dept.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconPhongBan.setIcon(new ImageIcon(imgPhongBan));
		pnPhongBan.add(lblIconPhongBan, BorderLayout.WEST);
		
		JPanel pnPhanCong = new JPanel();
		pnPhanCong.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(16, 84, 129)));
		pnPhanCong.setBackground(new Color(16, 84, 129));
		pnMenu.add(pnPhanCong);
		pnPhanCong.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPhanCong = new JLabel("PHÂN CÔNG");
		lblPhanCong.setForeground(new Color(255, 255, 255));
		lblPhanCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhanCong.setHorizontalAlignment(SwingConstants.CENTER);
		pnPhanCong.add(lblPhanCong, BorderLayout.CENTER);
		
		JLabel lblIconPhanCong = new JLabel();
		Image imgPhanCong = new ImageIcon("images\\side_menu\\allotment.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconPhanCong.setIcon(new ImageIcon(imgPhanCong));
		pnPhanCong.add(lblIconPhanCong, BorderLayout.WEST);
		
		JPanel pnChamCong = new JPanel();
		pnChamCong.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(16, 84, 129)));
		pnChamCong.setBackground(new Color(16, 84, 129));
		pnMenu.add(pnChamCong);
		pnChamCong.setLayout(new BorderLayout(0, 0));
		
		JLabel lblChamCong = new JLabel("CHẤM CÔNG");
		lblChamCong.setForeground(new Color(255, 255, 255));
		lblChamCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChamCong.setHorizontalAlignment(SwingConstants.CENTER);
		pnChamCong.add(lblChamCong, BorderLayout.CENTER);
		
		JLabel lblIconChamCong = new JLabel();
		Image imgChamCong = new ImageIcon("images\\side_menu\\timekeeping.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconChamCong.setIcon(new ImageIcon(imgChamCong));
		pnChamCong.add(lblIconChamCong, BorderLayout.WEST);
		
		JPanel pnSanPham = new JPanel();
		pnSanPham.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(16, 84, 129)));
		pnSanPham.setBackground(new Color(16, 84, 129));
		pnMenu.add(pnSanPham);
		pnSanPham.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setForeground(new Color(255, 255, 255));
		lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		pnSanPham.add(lblSanPham, BorderLayout.CENTER);
		
		JLabel lblIconSanPham = new JLabel();
		Image imgSanPham = new ImageIcon("images\\side_menu\\product.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconSanPham.setIcon(new ImageIcon(imgSanPham));
		pnSanPham.add(lblIconSanPham, BorderLayout.WEST);
		
		JPanel pnLuong = new JPanel();
		pnLuong.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(16, 84, 129)));
		pnLuong.setBackground(new Color(16, 84, 129));
		pnMenu.add(pnLuong);
		pnLuong.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLuong = new JLabel("LƯƠNG");
		lblLuong.setForeground(new Color(255, 255, 255));
		lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLuong.setHorizontalAlignment(SwingConstants.CENTER);
		pnLuong.add(lblLuong, BorderLayout.CENTER);
		
		JLabel lblIconLuong = new JLabel();
		Image imgLuong = new ImageIcon("images\\side_menu\\salary.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconLuong.setIcon(new ImageIcon(imgLuong));
		pnLuong.add(lblIconLuong, BorderLayout.WEST);
		
		JPanel pnThongKe = new JPanel();
		pnThongKe.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(16, 84, 129)));
		pnThongKe.setBackground(new Color(16, 84, 129));
		pnMenu.add(pnThongKe);
		pnThongKe.setLayout(new BorderLayout(0, 0));
		
		JLabel lblThongKe = new JLabel("THỐNG KÊ");
		lblThongKe.setForeground(new Color(255, 255, 255));
		lblThongKe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		pnThongKe.add(lblThongKe, BorderLayout.CENTER);
		
		JLabel lblIconThongKe = new JLabel();
		Image imgThongKe = new ImageIcon("images\\side_menu\\statistical.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconThongKe.setIcon(new ImageIcon(imgThongKe));
		pnThongKe.add(lblIconThongKe, BorderLayout.WEST);
		
		JPanel pnTaiKhoan = new JPanel();
		pnTaiKhoan.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(16, 84, 129)));
		pnTaiKhoan.setBackground(new Color(16, 84, 129));
		pnMenu.add(pnTaiKhoan);
		pnTaiKhoan.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTaiKhoan = new JLabel("TÀI KHOẢN");
		lblTaiKhoan.setForeground(new Color(255, 255, 255));
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		pnTaiKhoan.add(lblTaiKhoan, BorderLayout.CENTER);
		
		JLabel lblIconTaiKhoan = new JLabel();
		Image imgTaiKhoan = new ImageIcon("images\\side_menu\\account.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		lblIconTaiKhoan.setIcon(new ImageIcon(imgTaiKhoan));
		pnTaiKhoan.add(lblIconTaiKhoan, BorderLayout.WEST);
		
		JLabel lblVersion = new JLabel("Version 1.0.1");
		lblVersion.setBounds(20, 666, 190, 14);
		lblVersion.setFont(new Font("Microsoft JhengHei Light", Font.PLAIN, 9));
		lblVersion.setForeground(new Color(255, 255, 255));
		
		side = new SideMenuPanel(this);
		side.setMinWidth(65);
		side.setMaxWidth(190);
		side.setSpeed(4);
		side.setSide(pnSideMenu);
		side.setMain(pnContent);
		pnContainer.setLayout(null);
		pnContainer.add(pnSideMenu);
		pnSideMenu.setLayout(null);
		pnSideMenu.add(imageAvatar);
		pnSideMenu.add(lblName);
		pnSideMenu.add(lblVersion);
		pnSideMenu.add(pnMenu);
		pnContainer.add(pnContent);
		pnContent.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		pnContent.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Nhân viên hành chính", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 4, true), "Nhập thông tin nhân sự"));
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_3.add(lblNewLabel, "cell 0 0,alignx left");
		
		textField = new JTextField();
		panel_3.add(textField, "cell 1 0,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_3.add(lblNewLabel_1, "cell 2 0,alignx trailing");
		
		textField_1 = new JTextField();
		panel_3.add(textField_1, "cell 3 0,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_3.add(lblNewLabel_2, "cell 4 0,alignx trailing");
		
		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox, "cell 5 0,growx");
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel_3.add(lblNewLabel_3, "cell 0 1");
		
		textField_2 = new JTextField();
		panel_3.add(textField_2, "cell 1 1,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel_3.add(lblNewLabel_4, "cell 2 1,alignx trailing");
		
		textField_3 = new JTextField();
		panel_3.add(textField_3, "cell 3 1,growx");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel_3.add(lblNewLabel_5, "cell 4 1,alignx trailing");
		
		JComboBox comboBox_1 = new JComboBox();
		panel_3.add(comboBox_1, "cell 5 1,growx");
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		panel_3.add(lblNewLabel_6, "cell 0 2,alignx trailing");
		
		JComboBox comboBox_2 = new JComboBox();
		panel_3.add(comboBox_2, "cell 1 2,growx");
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		panel_3.add(lblNewLabel_7, "cell 2 2,alignx trailing");
		
		JComboBox comboBox_3 = new JComboBox();
		panel_3.add(comboBox_3, "cell 3 2,growx");
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		panel_3.add(lblNewLabel_8, "cell 4 2,alignx trailing");
		
		JComboBox comboBox_4 = new JComboBox();
		panel_3.add(comboBox_4, "cell 5 2,growx");
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_5.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_5.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_5.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_5.add(btnNewButton_3);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"c\u1ED9t 1", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		panel_6.add(table);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Nhân viên sản xuất", null, panel_2, null);
		
		
		
	}
}
