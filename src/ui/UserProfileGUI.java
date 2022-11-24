package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import entity.Employee;
import entity.PasswordBasedEncryption;
import imgavt.ImageAvatar;
import model.AccountDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

public class UserProfileGUI extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JPasswordField txtCurrentPassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtConfirmPassword;
	private final JPanel contentPane = new JPanel();
	private JButton btnChangePwd;
	private JButton btnOk;
	private AccountDAO accountDAO;
	private Employee employee;

	public static void main(String[] args) {
		try {
			UserProfileGUI dialog = new UserProfileGUI(new Employee());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserProfileGUI(Employee employee) {
		
		this.employee = employee;
		accountDAO = new AccountDAO();
		setIconImage(new ImageIcon("images\\login\\user_profile.png").getImage());
		setTitle("Thông tin tài khoản");
//		this.setLocationRelativeTo(null);
//		setAlwaysOnTop(true);
//		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 791, 342);
		contentPane.setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new BorderLayout());
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ImageAvatar imageAvatar = new ImageAvatar();
		imageAvatar.setBounds(41, 11, 100, 100);
		imageAvatar.setIcon(new ImageIcon("images\\avatar.jpg"));
		contentPane.add(imageAvatar);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(102, 102, 102));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(183, 11, 592, 210);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[grow][30px][grow]", "[][30px][10px][][30px][10px][][30px]"));
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel_2 = new JLabel("Tên nhân viên");
		lblNewLabel_2.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_2, "cell 2 0");
		
		JLabel lblEmpID = new JLabel();
		lblEmpID.setText(employee.getEmployeeID());
		lblEmpID.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblEmpID, "cell 0 1,growx");
		
		JLabel lblName = new JLabel();
		lblName.setText(employee.getName());
		lblName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblName, "cell 2 1,growx");
		
		JLabel lblNewLabel_4 = new JLabel("Giới tính");
		lblNewLabel_4.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_4, "cell 0 3");
		
		JLabel lblNewLabel_5 = new JLabel("Ngày sinh");
		lblNewLabel_5.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_5, "cell 2 3");
		
		JLabel lblGender = new JLabel();
		lblGender.setText((employee.isGender() == true) ? "Nam" : "Nữ");
		lblGender.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblGender, "cell 0 4,growx");
		
		JLabel lblDob = new JLabel();
		lblDob.setText(employee.getBirthday().toString());
		lblDob.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblDob, "cell 2 4,growx");
		
		JLabel lblNewLabel_8 = new JLabel("Địa chỉ");
		lblNewLabel_8.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_8, "cell 0 6");
		
		JLabel lblNewLabel_9 = new JLabel("Số điện thoại");
		lblNewLabel_9.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_9, "cell 2 6");
		
		JLabel lblAddress = new JLabel();
		lblAddress.setText(employee.getAddress());
		lblAddress.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblAddress, "cell 0 7,growx");
		
		JLabel lblPhone = new JLabel();
		lblPhone.setText(employee.getPhone());
		lblPhone.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblPhone, "cell 2 7,growx");
		
		btnChangePwd = new JButton("Đổi mật khẩu");
		btnChangePwd.addActionListener(this);
		btnChangePwd.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		btnChangePwd.setBackground(new Color(255, 255, 255));
		btnChangePwd.setFocusable(false);
		btnChangePwd.setBounds(29, 146, 126, 23);
		contentPane.add(btnChangePwd);
		
		btnOk = new JButton("Ok");
		btnOk.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		btnOk.setBackground(new Color(255, 255, 255));
		btnOk.setFocusable(false);
		btnOk.setBounds(343, 265, 89, 23);
		btnOk.addActionListener(this);
		contentPane.add(btnOk);
	}
	
	public boolean checkResetPassword(String currentPassword, String newPassword, String confirmPassword) {
		if (currentPassword.isBlank() || newPassword.isBlank() || confirmPassword.isBlank()) {
			JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin trước");
			return false;
		}
		if (!newPassword.matches("^[^\s]{12,}$")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu ít nhất 12 ký tự và không chứa khoảng trắng");
			return false;
		}
		if (currentPassword.equals(newPassword)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới không được trùng mật khẩu cũ");
			return false;
		}
		if (!newPassword.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không khớp");
			return false;
		}
		HashMap<String, String> account = accountDAO.getPasswordEncryption(employee.getEmployeeID());
		if (!PasswordBasedEncryption.verifyUserPassword(currentPassword, account.get("password hash"), account.get("salt"))) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ không khớp");
			return false;
		}
		return true;
	}
	
	public String dialogChangePassword(JDialog dialog) {
		JPanel pnChangePassword = new JPanel(new BorderLayout(5, 5));
		JPanel pnLabel = new JPanel(new GridLayout(0, 1, 2, 2));
		pnLabel.add(new JLabel("Mật khẩu cũ", SwingConstants.RIGHT));
		pnLabel.add(new JLabel("Mật khẩu mới", SwingConstants.RIGHT));
		pnLabel.add(new JLabel("Nhập lại mật khẩu", SwingConstants.RIGHT));
		pnChangePassword.add(pnLabel, BorderLayout.WEST);

		JPanel pnControls = new JPanel(new GridLayout(0, 1, 2, 2));
		txtCurrentPassword = new JPasswordField(15);
		pnControls.add(txtCurrentPassword);
		txtNewPassword = new JPasswordField(15);
		pnControls.add(txtNewPassword);
		txtConfirmPassword = new JPasswordField(15);
		pnControls.add(txtConfirmPassword);
		pnChangePassword.add(pnControls, BorderLayout.CENTER);
		int response;
		String currentPassword, newPassword, confirmPassword;
		do {
			response = JOptionPane.showConfirmDialog(dialog, pnChangePassword, "Đổi mật khẩu", JOptionPane.OK_CANCEL_OPTION);
			currentPassword = new String(txtCurrentPassword.getPassword());
			newPassword = new String(txtNewPassword.getPassword());
			confirmPassword = new String(txtConfirmPassword.getPassword());
		} while (response == JOptionPane.OK_OPTION
				&& !checkResetPassword(currentPassword, newPassword, confirmPassword));
		
		return (response == JOptionPane.OK_OPTION) ? newPassword : null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChangePwd) {
			String password = dialogChangePassword(this);
			if (password != null) {
				String salt = PasswordBasedEncryption.getSaltValue(30);
				if (accountDAO.updatePasword(PasswordBasedEncryption.generateSecurePassword(password, salt), salt, employee.getEmployeeID())) {
					JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công");
				}else {
					JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu không thành công");
				}
			}
		}
		if (e.getSource() == btnOk) {
			dispose();
		}
	}

}
