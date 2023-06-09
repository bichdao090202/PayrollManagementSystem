package ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import custom_field.JPasswordFieldHint;
import custom_field.JTextFieldHint;
import entity.Employee;
import entity.EmployeeOffice;
import entity.PasswordBasedEncryption;
import model.AccountDAO;
import model.EmployeeOfficeDAO;
import model.WorkerDAO;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;

public class LoginGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Image ICON_APPLICATION = new ImageIcon("images\\icon_application.png").getImage();
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private JPanel contentPane;
	private JButton btnLogin;
	private JTextFieldHint txtUser;
	private JPasswordFieldHint txtPassword;
	private AccountDAO accountDAO;
	private EmployeeOfficeDAO employeeOfficeDAO;
	private WorkerDAO workerDAO;
	private Employee emp;

	public LoginGUI() {
		accountDAO = new AccountDAO();
		employeeOfficeDAO = new EmployeeOfficeDAO();
		workerDAO = new WorkerDAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(ICON_APPLICATION);
		setTitle("Đăng nhập");
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1001, 566);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnMainLeft = new JPanel();
		pnMainLeft.setBackground(COLOR);
		contentPane.add(pnMainLeft, BorderLayout.WEST);
		pnMainLeft.setLayout(new BorderLayout(0, 0));

		JLabel lblCompanyName = new JLabel("<html><br>&emsp;Công ty TNHH Group 14&emsp;</html>");
		lblCompanyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanyName.setForeground(new Color(255, 255, 255));
		lblCompanyName.setBackground(new Color(255, 255, 255));
		lblCompanyName.setFont(new Font("Arial", Font.PLAIN, 40));
		pnMainLeft.add(lblCompanyName, BorderLayout.NORTH);

		JLabel lblImageIcon = new JLabel();
		lblImageIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageIcon.setIcon(new ImageIcon("images\\login\\programmer.png"));
		pnMainLeft.add(lblImageIcon);

		JPanel pnMainRight = new JPanel();
		pnMainRight.setBackground(new Color(255, 255, 255));
		contentPane.add(pnMainRight);
		pnMainRight.setLayout(null);

		btnLogin = new JButton("Đăng nhập");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(Color.WHITE);
			}
		});
		btnLogin.setBorder(new LineBorder(COLOR, 3, false));
		btnLogin.setForeground(COLOR);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setMnemonic(KeyEvent.VK_ENTER);
		btnLogin.setFocusable(false);
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 17));
		btnLogin.setBounds(166, 345, 140, 36);
		btnLogin.addActionListener(this);
		pnMainRight.add(btnLogin);

		JLabel lblLogin = new JLabel("Đăng nhập");
		lblLogin.setBackground(Color.WHITE);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 30));
		lblLogin.setForeground(COLOR);
		lblLogin.setBounds(159, 70, 155, 36);
		pnMainRight.add(lblLogin);

		JPanel pnUser = new JPanel();
		pnUser.setBackground(new Color(255, 255, 255));
		pnUser.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 0)));
		pnUser.setBounds(70, 131, 333, 50);
		pnMainRight.add(pnUser);
		pnUser.setLayout(new BorderLayout(0, 0));

		txtUser = new JTextFieldHint("Tên đăng nhập");
		txtUser.setText("NVHC00000");
		txtUser.setLocation(55, 0);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUser.setBorder(null);
		pnUser.add(txtUser);

		JLabel lblIconUser = new JLabel();
		lblIconUser.setLabelFor(txtUser);
		lblIconUser.setBackground(new Color(255, 255, 255));
		lblIconUser.setIcon(new ImageIcon("images\\login\\user.png"));
		pnUser.add(lblIconUser, BorderLayout.WEST);

		JPanel pnPassword = new JPanel();
		pnPassword.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 0)));
		pnPassword.setBackground(new Color(255, 255, 255));
		pnPassword.setBounds(70, 242, 333, 50);
		pnMainRight.add(pnPassword);
		pnPassword.setLayout(new BorderLayout(0, 0));

		JLabel lblIconPassword = new JLabel();
		lblIconPassword.setBackground(new Color(255, 255, 255));
		lblIconPassword.setIcon(new ImageIcon("images\\login\\password.png"));
		pnPassword.add(lblIconPassword, BorderLayout.WEST);

		txtPassword = new JPasswordFieldHint("Mật khảu");
		lblIconPassword.setLabelFor(txtPassword);
		txtPassword.setText("111111111111");
		txtPassword.setLocation(55, 0);
		txtPassword.setBorder(null);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnPassword.add(txtPassword, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			String username = txtUser.getText().trim();
			String pwd = String.valueOf(txtPassword.getPassword()).trim();
			if (username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống", "Lỗi", JOptionPane.NO_OPTION,
						null);
				txtUser.requestFocus();
			} else if (pwd.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống", "Lỗi", JOptionPane.NO_OPTION, null);
				txtPassword.requestFocus();
			} else {
				HashMap<String, String> account = accountDAO.getPasswordEncryption(username);
				if (!account.isEmpty() & PasswordBasedEncryption.verifyUserPassword(pwd, account.get("password hash"),
						account.get("salt"))) {
					emp = null;
					if (username.contains("NVHC") || username.equals("QUANTRI")) {
						emp = employeeOfficeDAO.getEmployeeOffice(username);
					} else {
						emp = workerDAO.getWorker(username);
					}
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
								setVisible(false);
								dispose();
								MenuGUI menuGUI = new MenuGUI(emp);
								menuGUI.setLocationRelativeTo(null);
								menuGUI.setResizable(false);
								menuGUI.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else {
					JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác! Vui lòng nhập lại.",
							"Đăng nhập thất bại", JOptionPane.NO_OPTION, null);
					txtUser.requestFocus();
				}
			}
		}
	}
}
