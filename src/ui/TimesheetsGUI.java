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

import entity.TimesheetsOffice;
import model.TimesheetsDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import custom_field.JTextFieldHint;
import java.awt.Rectangle;

public class TimesheetsGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblEmpOffice;
	private String[] headerTableEmpOffice = new String[] {"Nhân viên", "Ngày chấm", "Vào sáng", "Ra sáng", "Vào chiều", "Ra chiều"};
	private DefaultTableModel dtmEmpOffice;
	private TimesheetsDAO timesheetsDAO;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnSearch;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
		timesheetsDAO = new TimesheetsDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1200,690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel tabWorker = new JPanel();
		tabbedPane.addTab("Nhân viên sản xuất", null, tabWorker, null);
		tabWorker.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		tabWorker.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new MigLayout("", "[][150px:n,grow][][150px:n,grow]", "[][][][][]"));
		
		JLabel lblNewLabel_2 = new JLabel("Mã nhân viên");
		panel_2.add(lblNewLabel_2, "cell 0 0,alignx left");
		
		JComboBox comboBox_2 = new JComboBox();
		panel_2.add(comboBox_2, "cell 1 0,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Tên nhân viên");
		panel_2.add(lblNewLabel_3, "cell 2 0,alignx left");
		
		textField = new JTextField();
		panel_2.add(textField, "cell 3 0,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Mã sản phẩm");
		panel_2.add(lblNewLabel_7, "cell 0 1,alignx left");
		
		JComboBox comboBox_3 = new JComboBox();
		panel_2.add(comboBox_3, "cell 1 1,growx");
		
		JLabel lblNewLabel_10 = new JLabel("Tên sản phẩm:");
		panel_2.add(lblNewLabel_10, "cell 2 1,alignx left");
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "cell 3 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Mã quy trình");
		panel_2.add(lblNewLabel_11, "cell 0 2,alignx left");
		
		JComboBox comboBox_4 = new JComboBox();
		panel_2.add(comboBox_4, "cell 1 2,growx");
		
		JLabel lblNewLabel_16 = new JLabel("Tên quy trình");
		panel_2.add(lblNewLabel_16, "cell 2 2,alignx left");
		
		textField_2 = new JTextField();
		panel_2.add(textField_2, "cell 3 2,growx");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("Ngày chấm công:");
		panel_2.add(lblNewLabel_17, "cell 0 3,alignx left,aligny top");
		
		JDateChooser dateChooser_2 = new JDateChooser();
		panel_2.add(dateChooser_2, "cell 1 3,growx,aligny top");
		
		JLabel lblNewLabel_18 = new JLabel("Số lượng thành phẩm:");
		panel_2.add(lblNewLabel_18, "cell 2 3,alignx left,aligny top");
		
		JSpinField spinField = new JSpinField();
		spinField.setMinimum(0);
		panel_2.add(spinField, "cell 3 3,growx,aligny top");
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "flowx,cell 0 4 2097051 1,alignx center");
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Thêm chấm công");
		btnNewButton.setFocusable(false);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cập nhật chấm công");
		btnNewButton_1.setFocusable(false);
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Xóa chấm công");
		btnNewButton_2.setFocusable(false);
		panel_3.add(btnNewButton_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Bảng phân công"));
		scrollPane_2.setPreferredSize(new Dimension(600, 200));
		scrollPane_2.setMaximumSize(new Dimension(32767, 167));
		panel.add(scrollPane_2, BorderLayout.WEST);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setPreferredSize(new Dimension(0, 300));
		scrollPane_2.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentX(10.0f);
//		panel_1.setBorder(new TitledBorder(new Lin, getTitle(), ALLBITS, ABORT, getFont()));
		tabWorker.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.NORTH);
		
		JTextFieldHint textFieldHint = new JTextFieldHint();
		panel_4.add(textFieldHint);
		
		JPanel tabEmpOffice = new JPanel();
		tabbedPane.addTab("Nhân viên hành chính", null, tabEmpOffice, null);
		tabEmpOffice.setLayout(new BorderLayout(0, 0));
		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
//		tabEmpOffice.add(panel_2, BorderLayout.NORTH);
//		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel pnInput = new JPanel();
//		panel_2.add(pnInput, BorderLayout.CENTER);
		pnInput.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tabEmpOffice.add(pnInput, BorderLayout.NORTH);
		pnInput.setLayout(new MigLayout("", "[][200px:n:300px][40px][][][40px][][][grow][]", "[grow][grow]"));
		
		JLabel lblEmp = new JLabel("Nhân viên");
		pnInput.add(lblEmp, "cell 0 0,alignx left");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"NV00001 - Nguyễn Minh Quân"}));
		pnInput.add(comboBox_1, "cell 1 0,growx");
		
		JLabel lblCheckInAM = new JLabel("Check In Sáng");
		pnInput.add(lblCheckInAM, "cell 3 0");
		
		JSpinField spinHourCheckInAM = new JSpinField(0, 23);
		spinHourCheckInAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckInAM, "flowx,cell 4 0,growx,aligny center");
		
		JLabel lblNewLabel_4 = new JLabel("giờ");
		pnInput.add(lblNewLabel_4, "cell 4 0,alignx center");
		
		JSpinField spinMinuteCheckInAM = new JSpinField();
		spinMinuteCheckInAM.setMinimum(0);
		spinMinuteCheckInAM.setMaximum(59);
		spinMinuteCheckInAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckInAM, "cell 4 0,growx,aligny center");
		
		JLabel lblNewLabel_5 = new JLabel("phút");
		pnInput.add(lblNewLabel_5, "cell 4 0,alignx center");
		
		JLabel lblCheckOutAM = new JLabel("Check Out Sáng");
		pnInput.add(lblCheckOutAM, "cell 6 0");
		
		JSpinField spinHourCheckOutAM = new JSpinField();
		spinHourCheckOutAM.setMaximum(23);
		spinHourCheckOutAM.setMinimum(0);
		spinHourCheckOutAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckOutAM, "flowx,cell 7 0,alignx left,aligny center");
		
		btnAdd = new JButton("Thêm");
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);
		pnInput.add(btnAdd, "flowx,cell 9 0");
		
		JLabel lblNewLabel_6 = new JLabel("Ngày chấm công:");
		pnInput.add(lblNewLabel_6, "cell 0 1");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		pnInput.add(dateChooser_1, "cell 1 1,grow");
		
		JLabel lblNewLabel_8 = new JLabel("giờ");
		pnInput.add(lblNewLabel_8, "cell 7 0");
		
		JSpinField spinMinuteCheckOutAM = new JSpinField();
		spinMinuteCheckOutAM.setMinimum(0);
		spinMinuteCheckOutAM.setMaximum(59);
		spinMinuteCheckOutAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckOutAM, "cell 7 0");
		
		JLabel lblNewLabel_9 = new JLabel("phút");
		pnInput.add(lblNewLabel_9, "cell 7 0");
		
		JLabel lblCheckInPM = new JLabel("Check In Chiều");
		pnInput.add(lblCheckInPM, "cell 3 1,alignx center");
		
		JSpinField spinHourCheckInPM = new JSpinField();
		spinHourCheckInPM.setMaximum(23);
		spinHourCheckInPM.setMinimum(0);
		spinHourCheckInPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckInPM, "flowx,cell 4 1,alignx left,aligny center");
		
		JLabel lblCheckOutPM = new JLabel("Check Out Chiều");
		pnInput.add(lblCheckOutPM, "cell 6 1");
		
		JSpinField spinHourCheckOutPM = new JSpinField();
		spinHourCheckOutPM.setMaximum(23);
		spinHourCheckOutPM.setMinimum(0);
		spinHourCheckOutPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckOutPM, "flowx,cell 7 1,alignx left,aligny center");
		
		JLabel lblNewLabel_12 = new JLabel("giờ");
		pnInput.add(lblNewLabel_12, "cell 4 1");
		
		JSpinField spinMinuteCheckInPM = new JSpinField();
		spinMinuteCheckInPM.setMinimum(0);
		spinMinuteCheckInPM.setMaximum(59);
		spinMinuteCheckInPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckInPM, "cell 4 1");
		
		JLabel lblNewLabel_13 = new JLabel("phút");
		pnInput.add(lblNewLabel_13, "cell 4 1");
		
		JLabel lblNewLabel_14 = new JLabel("giờ");
		pnInput.add(lblNewLabel_14, "cell 7 1");
		
		JSpinField spinMinuteCheckOutPM = new JSpinField();
		spinMinuteCheckOutPM.setMinimum(0);
		spinMinuteCheckOutPM.setMaximum(59);
		spinMinuteCheckOutPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckOutPM, "cell 7 1");
		
		JLabel lblNewLabel_15 = new JLabel("phút");
		pnInput.add(lblNewLabel_15, "cell 7 1");
		
		btnUpdate = new JButton("Cập nhật");
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);
		pnInput.add(btnUpdate, "cell 9 1,growx");
		
		btnDelete = new JButton("Xóa");
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDelete.setFocusable(false);
		btnDelete.setEnabled(false);
		pnInput.add(btnDelete, "cell 9 0");
		
		JPanel pnTable = new JPanel();
		tabEmpOffice.add(pnTable, BorderLayout.CENTER);
		pnTable.setLayout(new BorderLayout(0, 0));
		
		JPanel pnSearch = new JPanel();
		pnTable.add(pnSearch, BorderLayout.NORTH);
		pnSearch.setLayout(new MigLayout("", "[70px:n][200px:n][40px][][100px:n][][]", "[22px]"));
		
		JLabel lblNewLabel = new JLabel("Nhân viên");
		pnSearch.add(lblNewLabel, "cell 0 0,alignx left,aligny center");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NV00001 - Nguyễn Minh Quân"}));
		pnSearch.add(comboBox, "cell 1 0,growx,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Ngày chấm công");
		pnSearch.add(lblNewLabel_1, "cell 3 0,alignx left,aligny center");
		
		JDateChooser dateChooser = new JDateChooser();
		pnSearch.add(dateChooser, "cell 4 0,growx,aligny center");
		
		btnSearch = new JButton("");
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("images\\operations\\filter.png"));
		pnSearch.add(btnSearch, "cell 6 0,alignx center,aligny center");
		
		JScrollPane scrollPane = new JScrollPane();
		pnTable.add(scrollPane, BorderLayout.CENTER);
		
		tblEmpOffice = new JTable(dtmEmpOffice = new DefaultTableModel(headerTableEmpOffice, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(tblEmpOffice);
		
		loadDataToTable();
	}

	public void loadDataToTable() {
		List<TimesheetsOffice> timesheetsOffice = timesheetsDAO.getAllTimesheetsOffices();
		for (TimesheetsOffice timesheets : timesheetsOffice) {
			dtmEmpOffice.addRow(timesheets.toString().split(","));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
