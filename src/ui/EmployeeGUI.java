package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;

public class EmployeeGUI extends JFrame {
	public EmployeeGUI() {
		getUI();
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private EmployeeOfficeGUI employeeOfficeGUI;
	private WorkerGUI workerGUI;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeGUI frame = new EmployeeGUI();
					frame.getUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Component getUI() {
		employeeOfficeGUI = new EmployeeOfficeGUI();
		workerGUI = new WorkerGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);
		
		tabbedPane.addTab("Nhân viên hành chính", null, employeeOfficeGUI.getUI(), null);
		tabbedPane.addTab("Nhân viên sản xuất", null, workerGUI.getUI(), null);
		
		return contentPane;
	}

}
