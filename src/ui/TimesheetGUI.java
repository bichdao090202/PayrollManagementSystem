package ui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Employee;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class TimesheetGUI extends JFrame {
	
	public TimesheetGUI() {
		getUI();
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TimesheetEmployeeOfficeGUI timesheetsEmployeeOfficeGUI;
	private TimesheetWorkerGUI timesheetWorkerGUI;
	private Employee employee;
	
	

	public TimesheetGUI(Employee employee) {
		super();
		this.employee = employee;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimesheetGUI frame = new TimesheetGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Component getUI() {
		timesheetsEmployeeOfficeGUI = new TimesheetEmployeeOfficeGUI();
		timesheetWorkerGUI = new TimesheetWorkerGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1200, 690);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);
		
		if (employee.getPosition().equals("Tổ Trưởng")) {
			tabbedPane.addTab("Nhân viên sản xuất", null, timesheetWorkerGUI.getUI(), null);
		} else if (employee.getPosition().equals("Nhân Viên")){
			tabbedPane.addTab("Nhân viên hành chính", null, timesheetsEmployeeOfficeGUI.getUI(), null);
		} else {
			tabbedPane.addTab("Nhân viên hành chính", null, timesheetsEmployeeOfficeGUI.getUI(), null);
			tabbedPane.addTab("Nhân viên sản xuất", null, timesheetWorkerGUI.getUI(), null);
		}
		return contentPane;
	}

}
