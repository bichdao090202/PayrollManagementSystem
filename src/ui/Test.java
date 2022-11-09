package ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import imgavt.ImageAvatar;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import javax.swing.border.MatteBorder;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageAvatar imageAvatar = new ImageAvatar();
		imageAvatar.setBounds(41, 11, 100, 100);
		imageAvatar.setIcon(new ImageIcon("images\\avatar.jpg"));
		contentPane.add(imageAvatar);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(102, 102, 102));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(183, 11, 584, 210);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[grow][30px][grow]", "[][30px][10px][][30px][10px][][30px]"));
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel, "cell 0 0");
		
		JLabel lblNewLabel_2 = new JLabel("Tên nhân viên");
		lblNewLabel_2.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_2, "cell 2 0");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblNewLabel_1, "cell 0 1,growx");
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblNewLabel_3, "cell 2 1,growx");
		
		JLabel lblNewLabel_4 = new JLabel("Giới tính");
		lblNewLabel_4.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_4, "cell 0 3");
		
		JLabel lblNewLabel_5 = new JLabel("Ngày sinh");
		lblNewLabel_5.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_5, "cell 2 3");
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblNewLabel_6, "cell 0 4,growx");
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblNewLabel_7, "cell 2 4,growx");
		
		JLabel lblNewLabel_8 = new JLabel("Địa chỉ");
		lblNewLabel_8.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_8, "cell 0 6");
		
		JLabel lblNewLabel_9 = new JLabel("Số điện thoại");
		lblNewLabel_9.setForeground(new Color(102, 102, 102));
		panel.add(lblNewLabel_9, "cell 2 6");
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblNewLabel_10, "cell 0 7,growx");
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(lblNewLabel_11, "cell 2 7,growx");
		
		JButton btnNewButton = new JButton("Đổi mật khẩu");
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(27, 146, 126, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(303, 265, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
