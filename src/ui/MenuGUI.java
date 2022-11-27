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
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class MenuGUI extends JFrame {
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(0, 140, 140);
	private static final long serialVersionUID = 1L;
	private static final Image ICON_APPLICATION = new ImageIcon("images\\icon_application.png").getImage();
	private JPanel[] menu;
	private JPanel contentPane;
	private SideMenuPanel side;
	private JLabel lblName;
	private boolean click = true;
	private JPanel pnNhanSu;
	private JPanel pnPhongBan;
	private JPanel pnPhanCong;
	private JPanel pnChamCong;
	private JPanel pnSanPham;
	private JPanel pnLuong;
	private JPanel pnPhanXuong;
	private JPanel pnTaiKhoan;
	private boolean pnNhanSuClicked = true;
	private boolean pnPhongBanClicked = false;
	private boolean pnPhanCongClicked = false;
	private boolean pnChamCongClicked = false;
	private boolean pnSanPhamClicked = false;
	private boolean pnLuongClicked = false;
	private boolean pnThongKeClicked = false;
	private boolean pnTaiKhoanClicked = false;
	private EmployeeGUI employeeGUI;
	private Component pnContentNhanSu;
	private TimesheetsGUI timesheetsGUI;
	private Component pnContentChamCong;
	private DepartmentGUI departmentGUI;
	private Component pnContentPhongBan;
	private ProductGUI productGUI;
	private Component pnContentSanPham;
	private AccountGUI accountGUI;
	private Component pnContentAccount;
	private AssignmentGUI assignmentGUI;
	private Component pnContentPhanCong;
	private SalaryGUI salaryGUI;
	private Component pnContentSalary;
	private FactoryGUI factoryGUI;
	private Component pnContentFactory;
	private JLabel lblPhanCong;
	private JPanel pnMenu;
	private ImageAvatar imageAvatar;
	private Employee employee;
	
	public MenuGUI(Employee employee) {
		this.employee = employee;
		timesheetsGUI = new TimesheetsGUI();
		pnContentChamCong = timesheetsGUI.tabTimesheet();
		employeeGUI = new EmployeeGUI();
		pnContentNhanSu = employeeGUI.tabEmployee();
		departmentGUI = new DepartmentGUI();
		pnContentPhongBan = departmentGUI.tabDepartment();
		productGUI = new ProductGUI();
		pnContentSanPham = productGUI.getView();
		accountGUI = new AccountGUI();
		pnContentAccount = accountGUI.tabAccount();
		assignmentGUI = new AssignmentGUI(employee);
		pnContentPhanCong = assignmentGUI.tabAssignment();
		salaryGUI = new SalaryGUI();
		pnContentSalary = salaryGUI.getView();
		factoryGUI = new FactoryGUI();
		pnContentFactory = factoryGUI.getView();
		
		menu = new JPanel[8];
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnContainer = new JPanel();
		contentPane.add(pnContainer);

		JPanel pnContent = new JPanel();
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
					UserProfileGUI dialog = new UserProfileGUI(employee);
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

		pnNhanSu = new JPanel();
		pnNhanSu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnNhanSu.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnNhanSuClicked) {
					pnNhanSu.setBackground(COLOR);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = true;
				pnPhongBanClicked = false;
				pnPhanCongClicked = false;
				pnChamCongClicked = false;
				pnSanPhamClicked = false;
				pnLuongClicked = false;
				pnThongKeClicked = false;
				pnTaiKhoanClicked = false;
				pnNhanSu.setBackground(COLOR_HOVER);
				pnPhongBan.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR);
				pnChamCong.setBackground(COLOR);
				pnSanPham.setBackground(COLOR);
				pnLuong.setBackground(COLOR);
				pnPhanXuong.setBackground(COLOR);
				pnTaiKhoan.setBackground(COLOR);
				
				pnContent.removeAll();
				pnContent.add(pnContentNhanSu);
				pnContentNhanSu.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnNhanSu.setBackground(COLOR);
		pnNhanSu.setBackground(COLOR_HOVER);
		pnNhanSu.setSize(new Dimension(100, 100));
		pnMenu.add(pnNhanSu);
		pnNhanSu.setLayout(new BorderLayout(0, 0));

		JLabel lblNhanSu = new JLabel("NHÂN SỰ");
		lblNhanSu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhanSu.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhanSu.setForeground(new Color(255, 255, 255));
		pnNhanSu.add(lblNhanSu);

		JLabel lblIconNhanSu = new JLabel();
		lblIconNhanSu.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconNhanSu.setIcon(new ImageIcon("images\\side_menu\\businessman.png"));
		pnNhanSu.add(lblIconNhanSu, BorderLayout.WEST);

		pnPhongBan = new JPanel();
		pnPhongBan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnPhongBan.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnPhongBanClicked) {
					pnPhongBan.setBackground(COLOR);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = false;
				pnPhongBanClicked = true;
				pnPhanCongClicked = false;
				pnChamCongClicked = false;
				pnSanPhamClicked = false;
				pnLuongClicked = false;
				pnThongKeClicked = false;
				pnTaiKhoanClicked = false;
				pnPhongBan.setBackground(COLOR_HOVER);
				pnNhanSu.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR);
				pnChamCong.setBackground(COLOR);
				pnSanPham.setBackground(COLOR);
				pnLuong.setBackground(COLOR);
				pnPhanXuong.setBackground(COLOR);
				pnTaiKhoan.setBackground(COLOR);
				
				pnContent.removeAll();
				pnContent.add(pnContentPhongBan);
				pnContentPhongBan.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnPhongBan.setBorder(null);
		pnPhongBan.setBackground(COLOR);
		pnMenu.add(pnPhongBan);
		pnPhongBan.setLayout(new BorderLayout(0, 0));

		JLabel lblPhongBan = new JLabel("PHÒNG BAN");
		lblPhongBan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhongBan.setForeground(new Color(255, 255, 255));
		lblPhongBan.setHorizontalAlignment(SwingConstants.CENTER);
		pnPhongBan.add(lblPhongBan, BorderLayout.CENTER);

		JLabel lblIconPhongBan = new JLabel();
		lblIconPhongBan.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconPhongBan.setIcon(new ImageIcon("images\\side_menu\\dept.png"));
		pnPhongBan.add(lblIconPhongBan, BorderLayout.WEST);
		
		pnPhanXuong = new JPanel();
		pnPhanXuong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnPhanXuong.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnThongKeClicked) {
					pnPhanXuong.setBackground(COLOR);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = false;
				pnPhongBanClicked = false;
				pnPhanCongClicked = false;
				pnChamCongClicked = false;
				pnSanPhamClicked = false;
				pnLuongClicked = false;
				pnThongKeClicked = true;
				pnTaiKhoanClicked = false;
				pnNhanSu.setBackground(COLOR);
				pnPhongBan.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR);
				pnChamCong.setBackground(COLOR);
				pnSanPham.setBackground(COLOR);
				pnLuong.setBackground(COLOR);
				pnPhanXuong.setBackground(COLOR_HOVER);
				pnTaiKhoan.setBackground(COLOR);
				
				pnContent.removeAll();
				pnContent.add(pnContentFactory);
				pnContentFactory.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnPhanXuong.setBorder(null);
		pnPhanXuong.setBackground(COLOR);
		pnMenu.add(pnPhanXuong);
		pnPhanXuong.setLayout(new BorderLayout(0, 0));

		JLabel lblPhanXuong = new JLabel("PHÂN XƯỞNG");
		lblPhanXuong.setForeground(new Color(255, 255, 255));
		lblPhanXuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhanXuong.setHorizontalAlignment(SwingConstants.CENTER);
		pnPhanXuong.add(lblPhanXuong, BorderLayout.CENTER);

		JLabel lblIconPhanXuong = new JLabel();
		lblIconPhanXuong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconPhanXuong.setIcon(new ImageIcon("images\\side_menu\\factory.png"));
		pnPhanXuong.add(lblIconPhanXuong, BorderLayout.WEST);

		pnPhanCong = new JPanel();
		pnPhanCong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnPhanCong.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnPhanCongClicked) {
					pnPhanCong.setBackground(COLOR);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = false;
				pnPhongBanClicked = false;
				pnPhanCongClicked = true;
				pnChamCongClicked = false;
				pnSanPhamClicked = false;
				pnLuongClicked = false;
				pnThongKeClicked = false;
				pnTaiKhoanClicked = false;
				pnNhanSu.setBackground(COLOR);
				pnPhongBan.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR_HOVER);
				pnChamCong.setBackground(COLOR);
				pnSanPham.setBackground(COLOR);
				pnLuong.setBackground(COLOR);
				pnPhanXuong.setBackground(COLOR);
				pnTaiKhoan.setBackground(COLOR);
				
				pnContent.removeAll();
				pnContent.add(pnContentPhanCong);
				pnContentPhanCong.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnPhanCong.setBorder(null);
		pnPhanCong.setBackground(COLOR);
		pnMenu.add(pnPhanCong);
		pnPhanCong.setLayout(new BorderLayout(0, 0));

		lblPhanCong = new JLabel("PHÂN CÔNG");
		lblPhanCong.setForeground(new Color(255, 255, 255));
		lblPhanCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhanCong.setHorizontalAlignment(SwingConstants.CENTER);
		pnPhanCong.add(lblPhanCong, BorderLayout.CENTER);

		JLabel lblIconPhanCong = new JLabel();
		lblIconPhanCong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconPhanCong.setIcon(new ImageIcon("images\\side_menu\\allotment.png"));
		pnPhanCong.add(lblIconPhanCong, BorderLayout.WEST);

		pnChamCong = new JPanel();
		pnChamCong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnChamCong.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnChamCongClicked) {
					pnChamCong.setBackground(COLOR);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = false;
				pnPhongBanClicked = false;
				pnPhanCongClicked = false;
				pnChamCongClicked = true;
				pnSanPhamClicked = false;
				pnLuongClicked = false;
				pnThongKeClicked = false;
				pnTaiKhoanClicked = false;
				pnNhanSu.setBackground(COLOR);
				pnPhongBan.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR);
				pnChamCong.setBackground(COLOR_HOVER);
				pnSanPham.setBackground(COLOR);
				pnLuong.setBackground(COLOR);
				pnPhanXuong.setBackground(COLOR);
				pnTaiKhoan.setBackground(COLOR);
				
				pnContent.removeAll();
				pnContent.add(pnContentChamCong);
				pnContentChamCong.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnChamCong.setBorder(null);
		pnChamCong.setBackground(COLOR);
		pnMenu.add(pnChamCong);
		pnChamCong.setLayout(new BorderLayout(0, 0));

		JLabel lblChamCong = new JLabel("CHẤM CÔNG");
		lblChamCong.setForeground(new Color(255, 255, 255));
		lblChamCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChamCong.setHorizontalAlignment(SwingConstants.CENTER);
		pnChamCong.add(lblChamCong, BorderLayout.CENTER);

		JLabel lblIconChamCong = new JLabel();
		lblIconChamCong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconChamCong.setIcon(new ImageIcon("images\\side_menu\\timekeeping.png"));
		pnChamCong.add(lblIconChamCong, BorderLayout.WEST);

		pnSanPham = new JPanel();
		pnSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnSanPham.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnSanPhamClicked) {
					pnSanPham.setBackground(COLOR);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = false;
				pnPhongBanClicked = false;
				pnPhanCongClicked = false;
				pnChamCongClicked = false;
				pnSanPhamClicked = true;
				pnLuongClicked = false;
				pnThongKeClicked = false;
				pnTaiKhoanClicked = false;
				pnNhanSu.setBackground(COLOR);
				pnPhongBan.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR);
				pnChamCong.setBackground(COLOR);
				pnSanPham.setBackground(COLOR_HOVER);
				pnLuong.setBackground(COLOR);
				pnPhanXuong.setBackground(COLOR);
				pnTaiKhoan.setBackground(COLOR);
				pnContent.removeAll();
				pnContent.add(pnContentSanPham);
				pnContentSanPham.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnSanPham.setBorder(null);
		pnSanPham.setBackground(COLOR);
		pnMenu.add(pnSanPham);
		pnSanPham.setLayout(new BorderLayout(0, 0));

		JLabel lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setForeground(new Color(255, 255, 255));
		lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		pnSanPham.add(lblSanPham, BorderLayout.CENTER);

		JLabel lblIconSanPham = new JLabel();
		lblIconSanPham.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconSanPham.setIcon(new ImageIcon("images\\side_menu\\product.png"));
		pnSanPham.add(lblIconSanPham, BorderLayout.WEST);

		pnLuong = new JPanel();
		pnLuong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnLuong.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnLuongClicked) {
					pnLuong.setBackground(COLOR);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = false;
				pnPhongBanClicked = false;
				pnPhanCongClicked = false;
				pnChamCongClicked = false;
				pnSanPhamClicked = false;
				pnLuongClicked = true;
				pnThongKeClicked = false;
				pnTaiKhoanClicked = false;
				pnNhanSu.setBackground(COLOR);
				pnPhongBan.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR);
				pnChamCong.setBackground(COLOR);
				pnSanPham.setBackground(COLOR);
				pnLuong.setBackground(COLOR_HOVER);
				pnPhanXuong.setBackground(COLOR);
				pnTaiKhoan.setBackground(COLOR);
				
				pnContent.removeAll();
				pnContent.add(pnContentSalary);
				pnContentSalary.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnLuong.setBorder(null);
		pnLuong.setBackground(COLOR);
		pnMenu.add(pnLuong);
		pnLuong.setLayout(new BorderLayout(0, 0));

		JLabel lblLuong = new JLabel("LƯƠNG");
		lblLuong.setForeground(new Color(255, 255, 255));
		lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLuong.setHorizontalAlignment(SwingConstants.CENTER);
		pnLuong.add(lblLuong, BorderLayout.CENTER);

		JLabel lblIconLuong = new JLabel();
		lblIconLuong.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconLuong.setIcon(new ImageIcon("images\\side_menu\\salary.png"));
		pnLuong.add(lblIconLuong, BorderLayout.WEST);

		pnTaiKhoan = new JPanel();
		pnTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnTaiKhoan.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pnTaiKhoanClicked) {
					pnTaiKhoan.setBackground(COLOR);
				}
			}
			
			public void mouseClicked(MouseEvent e) {
				pnNhanSuClicked = false;
				pnPhongBanClicked = false;
				pnPhanCongClicked = false;
				pnChamCongClicked = false;
				pnSanPhamClicked = false;
				pnLuongClicked = false;
				pnThongKeClicked = false;
				pnTaiKhoanClicked = true;
				pnNhanSu.setBackground(COLOR);
				pnPhongBan.setBackground(COLOR);
				pnPhanCong.setBackground(COLOR);
				pnChamCong.setBackground(COLOR);
				pnSanPham.setBackground(COLOR);
				pnLuong.setBackground(COLOR);
				pnPhanXuong.setBackground(COLOR);
				pnTaiKhoan.setBackground(COLOR_HOVER);
				
				pnContent.removeAll();
				pnContent.add(pnContentAccount);
				pnContentAccount.setVisible(true);
				pnContent.repaint();
				pnContent.revalidate();
				clickMenu();
			}
		});
		pnTaiKhoan.setBorder(null);
		pnTaiKhoan.setBackground(COLOR);
		pnMenu.add(pnTaiKhoan);
		pnTaiKhoan.setLayout(new BorderLayout(0, 0));

		JLabel lblTaiKhoan = new JLabel("TÀI KHOẢN");
		lblTaiKhoan.setForeground(new Color(255, 255, 255));
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		pnTaiKhoan.add(lblTaiKhoan, BorderLayout.CENTER);

		JLabel lblIconTaiKhoan = new JLabel();
		lblIconTaiKhoan.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblIconTaiKhoan.setIcon(new ImageIcon("images\\side_menu\\account.png"));
		pnTaiKhoan.add(lblIconTaiKhoan, BorderLayout.WEST);

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
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("images\\side_menu\\menu.png"));
		lblNewLabel.addMouseListener(new MouseAdapter()  { 
			@Override
		    public void mouseClicked(MouseEvent e)  {  
				clickMenu();
		    }  
		}); 
		lblNewLabel.setBounds(10, 11, 46, 14);
		pnSideMenu.add(lblNewLabel);
		pnContainer.add(pnContent);
		pnContent.setLayout(new BorderLayout(0, 0));

		
		pnContent.add(pnContentNhanSu);
		pnContentNhanSu.setVisible(true);
	}

	public void clickMenu() {
		side.onSideMenu();
    	if (click) {
    		imageAvatar.setVisible(true);
	    	lblName.setVisible(true);
	    	click = false;
    	}else {
    		imageAvatar.setVisible(false);
	    	lblName.setVisible(false);
	    	click = true;
    	}
	}
	
	public void showInfoEmployee() {
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
}
