package ui;

import java.awt.EventQueue;

import javax.swing.UIManager;


public class Application {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					LoginGUI frame = new LoginGUI();
					frame.setTitle("Quản lý tiền lương");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
