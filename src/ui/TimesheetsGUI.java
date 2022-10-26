package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.table.DefaultTableModel;
import com.toedter.components.JSpinField;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;

public class TimesheetsGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimesheetsGUI frame = new TimesheetsGUI();
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
	public TimesheetsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1200,690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Nhân viên sản xuất", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Nhân viên hành chính", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new MigLayout("", "[][200px:n:300px][40px][][][40px][][][grow][]", "[grow][grow]"));
		
		JLabel lblNewLabel_2 = new JLabel("Nhân viên");
		panel_4.add(lblNewLabel_2, "cell 0 0,alignx left");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"NV00001 - Nguyễn Minh Quân"}));
		panel_4.add(comboBox_1, "cell 1 0,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Check In Sáng");
		panel_4.add(lblNewLabel_3, "cell 3 0");
		
		JSpinField spinField = new JSpinField();
		spinField.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField, "flowx,cell 4 0,growx,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("giờ");
		panel_4.add(lblNewLabel_4, "cell 4 0,alignx center");
		
		JSpinField spinField_1 = new JSpinField();
		spinField_1.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField_1, "cell 4 0,growx,aligny center");
		
		JLabel lblNewLabel_5 = new JLabel("phút");
		panel_4.add(lblNewLabel_5, "cell 4 0,alignx center");
		
		JLabel lblNewLabel_7 = new JLabel("Check Out Sáng");
		panel_4.add(lblNewLabel_7, "cell 6 0");
		
		JSpinField spinField_2 = new JSpinField();
		spinField_2.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField_2, "flowx,cell 7 0,alignx left,aligny center");
		
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);
		panel_4.add(btnAdd, "flowx,cell 9 0");
		
		JLabel lblNewLabel_6 = new JLabel("Ngày chấm công:");
		panel_4.add(lblNewLabel_6, "cell 0 1");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		panel_4.add(dateChooser_1, "cell 1 1,grow");
		
		JLabel lblNewLabel_8 = new JLabel("giờ");
		panel_4.add(lblNewLabel_8, "cell 7 0");
		
		JSpinField spinField_3 = new JSpinField();
		spinField_3.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField_3, "cell 7 0");
		
		JLabel lblNewLabel_9 = new JLabel("phút");
		panel_4.add(lblNewLabel_9, "cell 7 0");
		
		JLabel lblNewLabel_10 = new JLabel("Check In Chiều");
		panel_4.add(lblNewLabel_10, "cell 3 1,alignx center");
		
		JSpinField spinField_5 = new JSpinField();
		spinField_5.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField_5, "flowx,cell 4 1,alignx left,aligny center");
		
		JLabel lblNewLabel_11 = new JLabel("Check Out Chiều");
		panel_4.add(lblNewLabel_11, "cell 6 1");
		
		JSpinField spinField_4 = new JSpinField();
		spinField_4.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField_4, "flowx,cell 7 1,alignx left,aligny center");
		
		JLabel lblNewLabel_12 = new JLabel("giờ");
		panel_4.add(lblNewLabel_12, "cell 4 1");
		
		JSpinField spinField_6 = new JSpinField();
		spinField_6.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField_6, "cell 4 1");
		
		JLabel lblNewLabel_13 = new JLabel("phút");
		panel_4.add(lblNewLabel_13, "cell 4 1");
		
		JLabel lblNewLabel_14 = new JLabel("giờ");
		panel_4.add(lblNewLabel_14, "cell 7 1");
		
		JSpinField spinField_7 = new JSpinField();
		spinField_7.setPreferredSize(new Dimension(40, 20));
		panel_4.add(spinField_7, "cell 7 1");
		
		JLabel lblNewLabel_15 = new JLabel("phút");
		panel_4.add(lblNewLabel_15, "cell 7 1");
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);
		panel_4.add(btnUpdate, "cell 9 1,growx");
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDelete.setFocusable(false);
		panel_4.add(btnDelete, "cell 9 0");
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new MigLayout("", "[70px:n][200px:n][40px][][100px:n][][]", "[22px]"));
		
		JLabel lblNewLabel = new JLabel("Nhân viên");
		panel_5.add(lblNewLabel, "cell 0 0,alignx left,aligny center");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NV00001 - Nguyễn Minh Quân"}));
		panel_5.add(comboBox, "cell 1 0,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Ngày chấm công");
		panel_5.add(lblNewLabel_1, "cell 3 0,alignx left,aligny center");
		
		JDateChooser dateChooser = new JDateChooser();
		panel_5.add(dateChooser, "cell 4 0,growx,aligny center");
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFocusable(false);
		btnNewButton.setIcon(new ImageIcon("images\\operations\\filter.png"));
		panel_5.add(btnNewButton, "cell 6 0,alignx center,aligny center");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
	}

}
