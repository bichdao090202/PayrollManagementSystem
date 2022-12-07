package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import imgavt.ImageAvatar;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuGUI extends JFrame {
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(0, 140, 140);
	private static final long serialVersionUID = 1L;
	private JPanel[] menu;
	private Component[] content;
	private JPanel contentPane;
	private SideMenuPanel side;
	private JLabel lblName;
	private boolean showMenu = false;
	private boolean[] arraySelectedItem;
	private EmployeeGUI employeeGUI;
	private TimesheetGUI timesheetGUI;
	private DepartmentGUI departmentGUI;
	private ProductGUI productGUI;
	private AccountGUI accountGUI;
	private AssignmentGUI assignmentGUI;
	private SalaryGUI salaryGUI;
	private FactoryGUI factoryGUI;
	private JLabel lblPhanCong;
	private JPanel pnMenu;
	private ImageAvatar imageAvatar;
	private JPanel pnContent;

	public MenuGUI(Employee employee) {
		timesheetGUI = new TimesheetGUI();
		employeeGUI = new EmployeeGUI();
		departmentGUI = new DepartmentGUI();
//		productGUI = new ProductGUI();
		accountGUI = new AccountGUI();
		assignmentGUI = new AssignmentGUI(employee);
		salaryGUI = new SalaryGUI();
		factoryGUI = new FactoryGUI();

		menu = new JPanel[8];

		content = new Component[8];
		content[0] = employeeGUI.getUI();
		content[1] = departmentGUI.getUI();
		content[2] = factoryGUI.getUI();
		content[3] = assignmentGUI.getUI();
		content[4] = timesheetGUI.getUI();
//		content[5] = productGUI.getUI();
		content[6] = salaryGUI.getUI();
		content[7] = accountGUI.getUI();

		arraySelectedItem = new boolean[8];
		arraySelectedItem[0] = true;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnContainer = new JPanel();
		contentPane.add(pnContainer);

		pnContent = new JPanel();
		pnContent.setBounds(64, 0, 1201, 690);
		pnContent.setBackground(new Color(128, 128, 0));

		JPanel pnSideMenu = new JPanel();
		pnSideMenu.setBounds(0, 0, 65, 691);
		pnSideMenu.setBorder(new EmptyBorder(0, 10, 0, 0));
		pnSideMenu.setBackground(COLOR);

		imageAvatar = new ImageAvatar();
		imageAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					UserProfileGUI dialog = new UserProfileGUI(employee, MenuGUI.this);
					dialog.setLocationRelativeTo(null);
					dialog.setAlwaysOnTop(true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		imageAvatar.setBounds(0, 50, 108, 100);
		imageAvatar.setIcon(new ImageIcon("images\\avatar.jpg"));
		imageAvatar.setVisible(false);

		lblName = new JLabel();
		lblName.setText(employee.getName());
		lblName.setBounds(0, 149, 160, 33);
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblName.setVisible(false);
		lblName.setPreferredSize(new Dimension(pnSideMenu.getWidth(), pnSideMenu.getHeight()));

		pnMenu = new JPanel();
		pnMenu.setBounds(0, 180, 190, 465);
		pnMenu.setBackground(COLOR);
		pnMenu.setLayout(new GridLayout(8, 1, 0, 10));

		menu[0] = new JPanel();
		menu[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[0].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(0);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				clickMenuItem(0);
			}
		});
		menu[0].setBackground(COLOR);
		menu[0].setBackground(COLOR_HOVER);
		menu[0].setSize(new Dimension(100, 100));
		pnMenu.add(menu[0]);
		menu[0].setLayout(new BorderLayout(0, 0));

		JLabel lblNhanSu = new JLabel("NHÂN SỰ");
		lblNhanSu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhanSu.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanSu.setForeground(new Color(255, 255, 255));
		menu[0].add(lblNhanSu);

		JLabel lblIconNhanSu = new JLabel();
		lblIconNhanSu.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconNhanSu.setIcon(new ImageIcon("images\\side_menu\\businessman.png"));
		menu[0].add(lblIconNhanSu, BorderLayout.WEST);

		menu[1] = new JPanel();
		menu[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[1].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(1);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				clickMenuItem(1);
			}
		});
		menu[1].setBorder(null);
		menu[1].setBackground(COLOR);
		pnMenu.add(menu[1]);
		menu[1].setLayout(new BorderLayout(0, 0));

		JLabel lblPhongBan = new JLabel("PHÒNG BAN");
		lblPhongBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhongBan.setForeground(new Color(255, 255, 255));
		lblPhongBan.setHorizontalAlignment(SwingConstants.CENTER);
		menu[1].add(lblPhongBan, BorderLayout.CENTER);

		JLabel lblIconPhongBan = new JLabel();
		lblIconPhongBan.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconPhongBan.setIcon(new ImageIcon("images\\side_menu\\dept.png"));
		menu[1].add(lblIconPhongBan, BorderLayout.WEST);

		menu[2] = new JPanel();
		menu[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[2].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(2);
			}

			public void mouseClicked(MouseEvent e) {
				clickMenuItem(2);
			}
		});
		menu[2].setBorder(null);
		menu[2].setBackground(COLOR);
		pnMenu.add(menu[2]);
		menu[2].setLayout(new BorderLayout(0, 0));

		JLabel lblPhanXuong = new JLabel("PHÂN XƯỞNG");
		lblPhanXuong.setForeground(new Color(255, 255, 255));
		lblPhanXuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhanXuong.setHorizontalAlignment(SwingConstants.CENTER);
		menu[2].add(lblPhanXuong, BorderLayout.CENTER);

		JLabel lblIconPhanXuong = new JLabel();
		lblIconPhanXuong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconPhanXuong.setIcon(new ImageIcon("images\\side_menu\\factory.png"));
		menu[2].add(lblIconPhanXuong, BorderLayout.WEST);

		menu[3] = new JPanel();
		menu[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[3].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(3);
			}

			public void mouseClicked(MouseEvent e) {
				clickMenuItem(3);
			}
		});
		menu[3].setBorder(null);
		menu[3].setBackground(COLOR);
		pnMenu.add(menu[3]);
		menu[3].setLayout(new BorderLayout(0, 0));

		lblPhanCong = new JLabel("PHÂN CÔNG");
		lblPhanCong.setForeground(new Color(255, 255, 255));
		lblPhanCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhanCong.setHorizontalAlignment(SwingConstants.CENTER);
		menu[3].add(lblPhanCong, BorderLayout.CENTER);

		JLabel lblIconPhanCong = new JLabel();
		lblIconPhanCong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconPhanCong.setIcon(new ImageIcon("images\\side_menu\\allotment.png"));
		menu[3].add(lblIconPhanCong, BorderLayout.WEST);

		menu[4] = new JPanel();
		menu[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[4].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(4);
			}

			public void mouseClicked(MouseEvent e) {
				clickMenuItem(4);
			}
		});
		menu[4].setBorder(null);
		menu[4].setBackground(COLOR);
		pnMenu.add(menu[4]);
		menu[4].setLayout(new BorderLayout(0, 0));

		JLabel lblChamCong = new JLabel("CHẤM CÔNG");
		lblChamCong.setForeground(new Color(255, 255, 255));
		lblChamCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChamCong.setHorizontalAlignment(SwingConstants.CENTER);
		menu[4].add(lblChamCong, BorderLayout.CENTER);

		JLabel lblIconChamCong = new JLabel();
		lblIconChamCong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconChamCong.setIcon(new ImageIcon("images\\side_menu\\timekeeping.png"));
		menu[4].add(lblIconChamCong, BorderLayout.WEST);

		menu[5] = new JPanel();
		menu[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[5].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(5);
			}

			public void mouseClicked(MouseEvent e) {
				clickMenuItem(5);
			}
		});
		menu[5].setBorder(null);
		menu[5].setBackground(COLOR);
		pnMenu.add(menu[5]);
		menu[5].setLayout(new BorderLayout(0, 0));

		JLabel lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setForeground(new Color(255, 255, 255));
		lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		menu[5].add(lblSanPham, BorderLayout.CENTER);

		JLabel lblIconSanPham = new JLabel();
		lblIconSanPham.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconSanPham.setIcon(new ImageIcon("images\\side_menu\\product.png"));
		menu[5].add(lblIconSanPham, BorderLayout.WEST);

		menu[6] = new JPanel();
		menu[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[6].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(6);
			}

			public void mouseClicked(MouseEvent e) {
				clickMenuItem(6);
			}
		});
		menu[6].setBorder(null);
		menu[6].setBackground(COLOR);
		pnMenu.add(menu[6]);
		menu[6].setLayout(new BorderLayout(0, 0));

		JLabel lblLuong = new JLabel("LƯƠNG");
		lblLuong.setForeground(new Color(255, 255, 255));
		lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLuong.setHorizontalAlignment(SwingConstants.CENTER);
		menu[6].add(lblLuong, BorderLayout.CENTER);

		JLabel lblIconLuong = new JLabel();
		lblIconLuong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconLuong.setIcon(new ImageIcon("images\\side_menu\\salary.png"));
		menu[6].add(lblIconLuong, BorderLayout.WEST);

		menu[7] = new JPanel();
		menu[7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu[7].setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hoverOutItem(7);
			}

			public void mouseClicked(MouseEvent e) {
				clickMenuItem(7);
			}
		});
		menu[7].setBorder(null);
		menu[7].setBackground(COLOR);
		pnMenu.add(menu[7]);
		menu[7].setLayout(new BorderLayout(0, 0));

		JLabel lblTaiKhoan = new JLabel("TÀI KHOẢN");
		lblTaiKhoan.setForeground(new Color(255, 255, 255));
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		menu[7].add(lblTaiKhoan, BorderLayout.CENTER);

		JLabel lblIconTaiKhoan = new JLabel();
		lblIconTaiKhoan.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconTaiKhoan.setIcon(new ImageIcon("images\\side_menu\\account.png"));
		menu[7].add(lblIconTaiKhoan, BorderLayout.WEST);

		JLabel lblVersion = new JLabel("Version 1.0.1");
		lblVersion.setBounds(10, 666, 190, 14);
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

		JLabel lblShowMenu = new JLabel();
		lblShowMenu.setIcon(new ImageIcon("images\\side_menu\\menu.png"));
		lblShowMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickShowMenu();
			}
		});
		lblShowMenu.setBounds(10, 11, 46, 14);
		pnSideMenu.add(lblShowMenu);
		pnContainer.add(pnContent);
		pnContent.setLayout(new BorderLayout(0, 0));

		pnContent.add(content[0]);
		content[0].setVisible(true);
	}

	public void clickShowMenu() {
		side.onSideMenu();
		if (!showMenu) {
			imageAvatar.setVisible(true);
			lblName.setVisible(true);
			showMenu = true;
		} else {
			imageAvatar.setVisible(false);
			lblName.setVisible(false);
			showMenu = false;
		}
	}

	public void clickMenuItem(int index) {
		if (showMenu) {
			side.onSideMenu();
			imageAvatar.setVisible(false);
			lblName.setVisible(false);
			showMenu = false;
		}
		for (int j = 0; j < arraySelectedItem.length; j++) {
			if (index != j) {
				arraySelectedItem[j] = false;
				menu[j].setBackground(COLOR);
			} else {
				arraySelectedItem[j] = true;
				menu[j].setBackground(COLOR_HOVER);
				pnContent.removeAll();
				pnContent.add(content[j]);
				content[j].setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				pnMenu.repaint();
			}
		}
	}

	public void hoverOutItem(int index) {
		if (!arraySelectedItem[index]) {
			menu[index].setBackground(COLOR);
		}
	}
}
