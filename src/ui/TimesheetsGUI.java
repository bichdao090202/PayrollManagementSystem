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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import com.toedter.calendar.JDateChooser;

import javax.swing.table.DefaultTableModel;
import com.toedter.components.JSpinField;

import entity.TimesheetsFactory;
import entity.TimesheetsOffice;
import model.EmployeeOfficeDAO;
import model.TimesheetsDAO;
import model.TimesheetsFactoryDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;

import javax.swing.border.TitledBorder;
import custom_field.JTextFieldHint;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TimesheetsGUI extends JFrame implements ActionListener {
	public TimesheetsGUI() {
		tabTimesheet();
	}

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfTime;
	private static final Date CURRENT_DATE = new Date();
	private JPanel contentPane;
	private JTable tblEmpOffice;
	private String[] headerTableEmpOffice = new String[] { "Nhân viên", "Ngày chấm", "Vào sáng", "Ra sáng", "Vào chiều",
			"Ra chiều" };
	private String[] headerTableTimesheetsWorker = new String[] { "Mã chấm công", "Nhân viên", "Sản phẩm", "Quy trình",
			"Ngày chấm công", "Số lượng thành phẩm" };
	private String[] headerTableAssignmentWorker = new String[] { "Mã phân công", "Nhân viên", "Sản phẩm", "Quy trình",
			"Ngày tham gia" };
	private DefaultTableModel dtmEmpOffice;
	private DefaultTableModel dtmAssignment;
	private DefaultTableModel dtmWorker;
	private TimesheetsDAO timesheetsDAO;
	private TimesheetsFactoryDAO timesheetsFactoryDAO;
	private EmployeeOfficeDAO employeeOfficeDAO;
	private JButton btnUpdateOfEmpOffice;
	private JButton btnAddOfEmpOffice;
	private JButton btnDeleteOfEmpOffice;
	private JButton btnSearch;
	private JTable tblAssignmentWorker;
	private JTable tblTimesheetsWorker;
	private JButton btnSearchOfWorker;
	private JButton btnDeleteOfWorker;
	private JButton btnUpdateOfWorker;
	private JButton btnAddOfWorker;
	private JComboBox<String> cboEmpOffice;
	private JDateChooser dateTimesheetOffice;
	private JSpinField spinHourCheckInAM;
	private JSpinField spinMinuteCheckInAM;
	private JSpinField spinHourCheckOutAM;
	private JSpinField spinMinuteCheckOutAM;
	private JSpinField spinHourCheckOutPM;
	private JSpinField spinMinuteCheckOutPM;
	private JSpinField spinMinuteCheckInPM;
	private JSpinField spinHourCheckInPM;
	private JSpinField spinAmountCompleted;
	private JCheckBox chkAbsentPM;
	private JPanel pnInputEmpOffice;
	private JCheckBox chkAbsentAM;
	private JDateChooser dateSearchEmpOffice;
	private JComboBox<String> cboSearchEmpOffice;
	private JTextField txtWorker;
	private JTextField txtProduct;
	private JTextField txtProduce;
	private JDateChooser dateJoin;

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

	public Component tabTimesheet() {
		sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		sdfTime = new SimpleDateFormat("HH:mm:ss");
		timesheetsDAO = new TimesheetsDAO();
		employeeOfficeDAO = new EmployeeOfficeDAO();
		timesheetsFactoryDAO = new TimesheetsFactoryDAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1200, 690);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel tabWorker = new JPanel();
		tabbedPane.addTab("Nhân viên sản xuất", null, tabWorker, null);
		tabWorker.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		tabWorker.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new MigLayout("", "[][100px:n:110px,grow][][:50px:50px]", "[][][][][][]"));

		JLabel lblIDWorker = new JLabel("Nhân viên");
		panel_2.add(lblIDWorker, "cell 0 0,alignx left");

		txtWorker = new JTextField();
		txtWorker.setEditable(false);
		panel_2.add(txtWorker, "cell 1 0,growx, span 3");
		txtWorker.setColumns(10);

		JLabel lblProductID = new JLabel("Sản phẩm");
		panel_2.add(lblProductID, "cell 0 1,alignx left");

		txtProduct = new JTextField();
		txtProduct.setEditable(false);
		panel_2.add(txtProduct, "cell 1 1,growx, span 3");
		txtProduct.setColumns(10);

		JLabel lblProduceID = new JLabel("Quy trình");
		panel_2.add(lblProduceID, "cell 0 2,alignx left");

		txtProduce = new JTextField();
		txtProduce.setEditable(false);
		panel_2.add(txtProduce, "cell 1 2,growx, span 3");
		txtProduce.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Ngày chấm công:");
		panel_2.add(lblNewLabel_17, "cell 0 3,alignx left,aligny top");

		dateJoin = new JDateChooser();
		dateJoin.setMaxSelectableDate(CURRENT_DATE);
		dateJoin.setDate(CURRENT_DATE);
		dateJoin.setDateFormatString("yyyy-MM-dd");
		panel_2.add(dateJoin, "cell 1 3,growx,aligny top");

		JLabel lblNewLabel_18 = new JLabel("Số lượng thành phẩm:");
		panel_2.add(lblNewLabel_18, "cell 2 3,alignx left,aligny top");

		spinAmountCompleted = new JSpinField();
		spinAmountCompleted.setValue(1);
		spinAmountCompleted.setMinimum(1);
		panel_2.add(spinAmountCompleted, "cell 3 3,growx,aligny top");

		JPanel pnOperationOfWorker = new JPanel();
		panel_2.add(pnOperationOfWorker, "flowx,cell 0 4 4 1,alignx center");
		pnOperationOfWorker.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAddOfWorker = new JButton("Thêm chấm công");
		btnAddOfWorker.addActionListener(this);
		btnAddOfWorker.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAddOfWorker.setFocusable(false);
		pnOperationOfWorker.add(btnAddOfWorker);

		btnDeleteOfWorker = new JButton("Xóa chấm công");
		btnDeleteOfWorker.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDeleteOfWorker.setEnabled(false);
		btnDeleteOfWorker.setFocusable(false);
		pnOperationOfWorker.add(btnDeleteOfWorker);

		btnUpdateOfWorker = new JButton("Cập nhật chấm công");
		btnUpdateOfWorker.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdateOfWorker.setFocusable(false);
		btnUpdateOfWorker.addActionListener(this);
		panel_2.add(btnUpdateOfWorker, "cell 1 5");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Bảng phân công"));
		scrollPane_2.setPreferredSize(new Dimension(600, 200));
		scrollPane_2.setMaximumSize(new Dimension(32767, 167));
		panel.add(scrollPane_2, BorderLayout.CENTER);

		tblAssignmentWorker = new JTable(dtmAssignment = new DefaultTableModel(headerTableAssignmentWorker, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblAssignmentWorker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblAssignmentWorker.getSelectedRow();
				if (rowSelected >= 0) {
					txtWorker.setText(tblAssignmentWorker.getValueAt(rowSelected, 1).toString());
					txtProduct.setText(tblAssignmentWorker.getValueAt(rowSelected, 2).toString());
					txtProduce.setText(tblAssignmentWorker.getValueAt(rowSelected, 3).toString());
					dateJoin.setDate(CURRENT_DATE);
					spinAmountCompleted.setValue(1);
				}
			}
		});
		tblAssignmentWorker.setPreferredSize(new Dimension(0, 300));
		scrollPane_2.setViewportView(tblAssignmentWorker);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(20, 0, 0, 0));
		panel_1.setAlignmentX(10.0f);
		tabWorker.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Bảng chấm công"));
		panel_1.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1);

		tblTimesheetsWorker = new JTable(dtmWorker = new DefaultTableModel(headerTableTimesheetsWorker, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTimesheetsWorker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblTimesheetsWorker.getSelectedRow();
				if (rowSelected >= 0) {
					txtWorker.setText(tblTimesheetsWorker.getValueAt(rowSelected, 1).toString());
					txtProduct.setText(tblTimesheetsWorker.getValueAt(rowSelected, 2).toString());
					txtProduce.setText(tblTimesheetsWorker.getValueAt(rowSelected, 3).toString());
					try {
						Date date = sdfDate.parse(tblTimesheetsWorker.getValueAt(rowSelected, 4).toString());
						dateJoin.setDate(date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					spinAmountCompleted
							.setValue(Integer.parseInt(tblTimesheetsWorker.getValueAt(rowSelected, 5).toString()));
				}
			}
		});
		scrollPane_1.setViewportView(tblTimesheetsWorker);

		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new MigLayout("", "[7px][][]", "[20px,grow]"));

		JTextFieldHint textFieldHint = new JTextFieldHint("Nhập mã nhân viên");
		textFieldHint.setMinimumSize(new Dimension(150, 20));
		panel_4.add(textFieldHint, "cell 0 0,grow");

		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setMinimumSize(new Dimension(150, 20));
		panel_4.add(dateChooser_3, "cell 1 0,grow");

		btnSearchOfWorker = new JButton("Tìm");
		btnSearchOfWorker.setFocusable(false);
		panel_4.add(btnSearchOfWorker, "cell 2 0");

		JPanel tabEmpOffice = new JPanel();
		tabEmpOffice.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Nhân viên hành chính", null, tabEmpOffice, null);
		tabEmpOffice.setLayout(new BorderLayout(0, 0));

		pnInputEmpOffice = new JPanel();
		pnInputEmpOffice.setBackground(new Color(255, 255, 255));
		pnInputEmpOffice.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tabEmpOffice.add(pnInputEmpOffice, BorderLayout.NORTH);
		pnInputEmpOffice.setLayout(new MigLayout("", "[][200px][40px][][][40px][][][40px][grow][]", "[grow][grow]"));

		JLabel lblEmpOffice = new JLabel("Nhân viên");
		pnInputEmpOffice.add(lblEmpOffice, "cell 0 0,alignx left");

		cboEmpOffice = new JComboBox<String>();
		cboEmpOffice.setModel(new DefaultComboBoxModel<String>(employeeOfficeDAO.getAllName().toArray(String[]::new)));
		pnInputEmpOffice.add(cboEmpOffice, "cell 1 0,growx");

		JLabel lblCheckInAM = new JLabel("Check In Sáng");
		pnInputEmpOffice.add(lblCheckInAM, "cell 3 0");

		spinHourCheckInAM = new JSpinField(0, 23);
		spinHourCheckInAM.setMaximum(9);
		spinHourCheckInAM.setMinimum(7);
		spinHourCheckInAM.setValue(8);
		spinHourCheckInAM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinHourCheckInAM, "flowx,cell 4 0,growx,aligny center");

		JLabel lblNewLabel_4 = new JLabel("giờ");
		pnInputEmpOffice.add(lblNewLabel_4, "cell 4 0,alignx center");

		spinMinuteCheckInAM = new JSpinField();
		spinMinuteCheckInAM.setMinimum(0);
		spinMinuteCheckInAM.setMaximum(59);
		spinMinuteCheckInAM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinMinuteCheckInAM, "cell 4 0,growx,aligny center");

		JLabel lblNewLabel_5 = new JLabel("phút");
		pnInputEmpOffice.add(lblNewLabel_5, "cell 4 0,alignx center");

		JLabel lblCheckOutAM = new JLabel("Check Out Sáng");
		pnInputEmpOffice.add(lblCheckOutAM, "cell 6 0");

		spinHourCheckOutAM = new JSpinField();
		spinHourCheckOutAM.setValue(12);
		spinHourCheckOutAM.setMaximum(13);
		spinHourCheckOutAM.setMinimum(11);
		spinHourCheckOutAM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinHourCheckOutAM, "flowx,cell 7 0,alignx left,aligny center");

		btnAddOfEmpOffice = new JButton("Thêm");
		btnAddOfEmpOffice.setBackground(new Color(255, 255, 255));
		btnAddOfEmpOffice.addActionListener(this);
		btnAddOfEmpOffice.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAddOfEmpOffice.setFocusable(false);
		pnInputEmpOffice.add(btnAddOfEmpOffice, "flowx,cell 10 0");

		JLabel lblDateTimesheets = new JLabel("Ngày chấm công:");
		pnInputEmpOffice.add(lblDateTimesheets, "cell 0 1");

		dateTimesheetOffice = new JDateChooser();
		dateTimesheetOffice.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateTimesheetOffice.setDate(CURRENT_DATE);
		dateTimesheetOffice.setDateFormatString("yyyy-MM-dd");
		dateTimesheetOffice.setMaxSelectableDate(CURRENT_DATE);
		pnInputEmpOffice.add(dateTimesheetOffice, "cell 1 1,grow");

		JLabel lblNewLabel_8 = new JLabel("giờ");
		pnInputEmpOffice.add(lblNewLabel_8, "cell 7 0");

		spinMinuteCheckOutAM = new JSpinField();
		spinMinuteCheckOutAM.setMinimum(0);
		spinMinuteCheckOutAM.setMaximum(59);
		spinMinuteCheckOutAM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinMinuteCheckOutAM, "cell 7 0");

		JLabel lblNewLabel_9 = new JLabel("phút");
		pnInputEmpOffice.add(lblNewLabel_9, "cell 7 0");

		JLabel lblCheckInPM = new JLabel("Check In Chiều");
		pnInputEmpOffice.add(lblCheckInPM, "cell 3 1,alignx center");

		spinHourCheckInPM = new JSpinField();
		spinHourCheckInPM.setValue(13);
		spinHourCheckInPM.setMaximum(14);
		spinHourCheckInPM.setMinimum(12);
		spinHourCheckInPM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinHourCheckInPM, "flowx,cell 4 1,alignx left,aligny center");

		JLabel lblCheckOutPM = new JLabel("Check Out Chiều");
		pnInputEmpOffice.add(lblCheckOutPM, "cell 6 1");

		spinHourCheckOutPM = new JSpinField();
		spinHourCheckOutPM.setValue(17);
		spinHourCheckOutPM.setMaximum(18);
		spinHourCheckOutPM.setMinimum(16);
		spinHourCheckOutPM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinHourCheckOutPM, "flowx,cell 7 1,alignx left,aligny center");

		JLabel lblNewLabel_12 = new JLabel("giờ");
		pnInputEmpOffice.add(lblNewLabel_12, "cell 4 1");

		spinMinuteCheckInPM = new JSpinField();
		spinMinuteCheckInPM.setValue(30);
		spinMinuteCheckInPM.setMinimum(0);
		spinMinuteCheckInPM.setMaximum(59);
		spinMinuteCheckInPM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinMinuteCheckInPM, "cell 4 1");

		JLabel lblNewLabel_13 = new JLabel("phút");
		pnInputEmpOffice.add(lblNewLabel_13, "cell 4 1");

		JLabel lblNewLabel_14 = new JLabel("giờ");
		pnInputEmpOffice.add(lblNewLabel_14, "cell 7 1");

		spinMinuteCheckOutPM = new JSpinField();
		spinMinuteCheckOutPM.setValue(30);
		spinMinuteCheckOutPM.setMinimum(0);
		spinMinuteCheckOutPM.setMaximum(59);
		spinMinuteCheckOutPM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinMinuteCheckOutPM, "cell 7 1");

		JLabel lblNewLabel_15 = new JLabel("phút");
		pnInputEmpOffice.add(lblNewLabel_15, "cell 7 1");

		btnUpdateOfEmpOffice = new JButton("Cập nhật");
		btnUpdateOfEmpOffice.setBorder(new LineBorder(new Color(8, 96, 100), 2));
		btnUpdateOfEmpOffice.setBackground(new Color(255, 255, 255));
		btnUpdateOfEmpOffice.addActionListener(this);

		chkAbsentPM = new JCheckBox("Vắng chiều");
		chkAbsentPM.setBackground(new Color(255, 255, 255));
		chkAbsentPM.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					spinHourCheckInPM.setEnabled(false);
					spinMinuteCheckInPM.setEnabled(false);
					spinHourCheckOutPM.setEnabled(false);
					spinMinuteCheckOutPM.setEnabled(false);
				} else {
					spinHourCheckInPM.setEnabled(true);
					spinMinuteCheckInPM.setEnabled(true);
					spinHourCheckOutPM.setEnabled(true);
					spinMinuteCheckOutPM.setEnabled(true);
				}
			}
		});
		chkAbsentPM.setFocusable(false);
		pnInputEmpOffice.add(chkAbsentPM, "cell 9 1");
		btnUpdateOfEmpOffice.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdateOfEmpOffice.setFocusable(false);
		pnInputEmpOffice.add(btnUpdateOfEmpOffice, "cell 10 1,growx");

		btnDeleteOfEmpOffice = new JButton("Xóa");
		btnDeleteOfEmpOffice.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDeleteOfEmpOffice.setFocusable(false);
		btnDeleteOfEmpOffice.setEnabled(false);
		pnInputEmpOffice.add(btnDeleteOfEmpOffice, "cell 10 0");

		chkAbsentAM = new JCheckBox("Vắng sáng");
		chkAbsentAM.setBackground(new Color(255, 255, 255));
		chkAbsentAM.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					spinHourCheckInAM.setEnabled(false);
					spinMinuteCheckInAM.setEnabled(false);
					spinHourCheckOutAM.setEnabled(false);
					spinMinuteCheckOutAM.setEnabled(false);
				} else {
					spinHourCheckInAM.setEnabled(true);
					spinMinuteCheckInAM.setEnabled(true);
					spinHourCheckOutAM.setEnabled(true);
					spinMinuteCheckOutAM.setEnabled(true);
				}
			}
		});
		chkAbsentAM.setForeground(new Color(0, 0, 0));
		chkAbsentAM.addActionListener(this);
		chkAbsentAM.setFocusable(false);
		pnInputEmpOffice.add(chkAbsentAM, "cell 9 0");

		JPanel pnTableEmpOffice = new JPanel();
		pnTableEmpOffice.setBackground(new Color(255, 255, 255));
		tabEmpOffice.add(pnTableEmpOffice, BorderLayout.CENTER);
		pnTableEmpOffice.setLayout(new BorderLayout(0, 0));

		JPanel pnSearchEmpOffice = new JPanel();
		pnSearchEmpOffice.setBackground(new Color(255, 255, 255));
		pnTableEmpOffice.add(pnSearchEmpOffice, BorderLayout.NORTH);
		pnSearchEmpOffice.setLayout(new MigLayout("", "[70px:n][200px:n][40px][][200px:n][][][][]", "[22px]"));

		JLabel lblNewLabel = new JLabel("Nhân viên");
		pnSearchEmpOffice.add(lblNewLabel, "cell 0 0,alignx left,aligny center");

		cboSearchEmpOffice = new JComboBox<String>();
		cboSearchEmpOffice
				.setModel(new DefaultComboBoxModel<String>(timesheetsDAO.getAllNameEmp().toArray(String[]::new)));
		pnSearchEmpOffice.add(cboSearchEmpOffice, "cell 1 0,growx,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Ngày chấm công");
		pnSearchEmpOffice.add(lblNewLabel_1, "cell 3 0,alignx left,aligny center");

		dateSearchEmpOffice = new JDateChooser();
		dateSearchEmpOffice.setMaxSelectableDate(CURRENT_DATE);
		dateSearchEmpOffice.setDate(CURRENT_DATE);
		dateSearchEmpOffice.setDateFormatString("yyyy-MM-dd");
		pnSearchEmpOffice.add(dateSearchEmpOffice, "cell 4 0,growx,aligny center");

		btnSearch = new JButton("");
		btnSearch.addActionListener(this);
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("images\\operations\\filter.png"));
		pnSearchEmpOffice.add(btnSearch, "cell 6 0,alignx center,aligny center");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		pnTableEmpOffice.add(scrollPane, BorderLayout.CENTER);

		tblEmpOffice = new JTable(dtmEmpOffice = new DefaultTableModel(headerTableEmpOffice, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmpOffice.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblEmpOffice.getSelectedRow();
				if (rowSelected >= 0) {
					btnUpdateOfEmpOffice.setEnabled(true);
					cboEmpOffice.setSelectedItem(dtmEmpOffice.getValueAt(rowSelected, 0).toString());
					try {
						dateTimesheetOffice.setDate(sdfDate.parse(dtmEmpOffice.getValueAt(rowSelected, 1).toString()));
						try {
							Date checkInAM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 2).toString());
							spinHourCheckInAM.setValue(checkInAM.getHours());
							spinMinuteCheckInAM.setValue(checkInAM.getMinutes());

							Date checkOutAM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 3).toString());
							spinHourCheckOutAM.setValue(checkOutAM.getHours());
							spinMinuteCheckOutAM.setValue(checkOutAM.getMinutes());
						} catch (ParseException e2) {
							chkAbsentAM.setSelected(true);
							chkAbsentPM.setSelected(false);
						}

						try {
							Date checkInPM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 4).toString());
							spinHourCheckInPM.setValue(checkInPM.getHours());
							spinMinuteCheckInPM.setValue(checkInPM.getMinutes());

							Date checkOutPM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 5).toString());
							spinHourCheckOutPM.setValue(checkOutPM.getHours());
							spinMinuteCheckOutPM.setValue(checkOutPM.getMinutes());
						} catch (ParseException e3) {
							chkAbsentPM.setSelected(true);
							chkAbsentAM.setSelected(false);
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(tblEmpOffice);

		loadDataToTable(timesheetsDAO.getAllTimesheetsOffices());
		loadDateToTableAssignment();
		loadDateToTableTimesheetsWorker();
		return contentPane;
	}

	public void loadDateToTableAssignment() {
		dtmAssignment = new DefaultTableModel(headerTableAssignmentWorker, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblAssignmentWorker.setModel(dtmAssignment);
		List<String> listAssignment = timesheetsFactoryDAO.getAllAssignment();
		for (String assignment : listAssignment) {
			dtmAssignment.addRow(assignment.split(";"));
		}
	}

	public void loadDateToTableTimesheetsWorker() {
		dtmWorker = new DefaultTableModel(headerTableTimesheetsWorker, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTimesheetsWorker.setModel(dtmWorker);
		List<String> listTimesheets = timesheetsFactoryDAO.getAllTimesheetFactory();
		for (String timesheet : listTimesheets) {
			dtmWorker.addRow(timesheet.split(";"));
		}
	}

	public void loadDataToTable(List<TimesheetsOffice> timesheetsOffice) {
		dtmEmpOffice = new DefaultTableModel(headerTableEmpOffice, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmpOffice.setModel(dtmEmpOffice);
		for (TimesheetsOffice timesheets : timesheetsOffice) {
			dtmEmpOffice.addRow(timesheets.toString().split(","));
		}
	}

	public Date getTime(JSpinField spinHour, JSpinField spinMinute) {
		Date time = null;
		try {
			time = sdfTime.parse(spinHour.getValue() + ":" + spinMinute.getValue() + ":00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public TimesheetsOffice createTimesheetsOfficeFromInput() {
		String employeeID = cboEmpOffice.getSelectedItem().toString().substring(0, 9);
		Date date = dateTimesheetOffice.getDate();
		TimesheetsOffice timesheets = new TimesheetsOffice(date, employeeID);
		if (!chkAbsentAM.isSelected() & !chkAbsentPM.isSelected()) {
			Date checkInAM = getTime(spinHourCheckInAM, spinMinuteCheckInAM);
			Date checkOutAM = getTime(spinHourCheckOutAM, spinMinuteCheckOutAM);
			Date checkInPM = getTime(spinHourCheckInPM, spinMinuteCheckInPM);
			Date checkOutPM = getTime(spinHourCheckOutPM, spinMinuteCheckOutPM);
			timesheets = new TimesheetsOffice(date, checkInAM, checkOutAM, checkInPM, checkOutPM, employeeID);
		} else if (!chkAbsentAM.isSelected() | chkAbsentPM.isSelected()) {
			Date checkInPM = getTime(spinHourCheckInPM, spinMinuteCheckInPM);
			Date checkOutAM = getTime(spinHourCheckOutAM, spinMinuteCheckOutAM);
			timesheets.setCheckInAM(checkInPM);
			timesheets.setCheckOutAM(checkOutAM);
		} else {
			Date checkInPM = getTime(spinHourCheckInPM, spinMinuteCheckInPM);
			Date checkOutPM = getTime(spinHourCheckOutPM, spinMinuteCheckOutPM);
			timesheets.setCheckInPM(checkInPM);
			timesheets.setCheckOutPM(checkOutPM);
		}
		return timesheets;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddOfEmpOffice) {
			TimesheetsOffice timesheets = createTimesheetsOfficeFromInput();
			if (timesheetsDAO.addTimesheetsOffice(timesheets)) {
				loadDataToTable(timesheetsDAO.getAllTimesheetsOffices());
				JOptionPane.showMessageDialog(this, "Thêm chấm công thành công.", "Thông báo", JOptionPane.NO_OPTION,
						null);
			} else {
				JOptionPane.showMessageDialog(this, "Thêm chấm công không thành công.", "Thông báo",
						JOptionPane.NO_OPTION, null);
			}
		}
		if (e.getSource() == btnUpdateOfEmpOffice) {
			int rowSelected = tblEmpOffice.getSelectedRow();
			if (rowSelected < 0) {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên trước", "Thông báo", JOptionPane.NO_OPTION,
						null);
			} else {
				try {
					TimesheetsOffice timesheets = createTimesheetsOfficeFromInput();
					String employeeID = dtmEmpOffice.getValueAt(rowSelected, 0).toString().substring(0, 7);
					Date date = sdfDate.parse(dtmEmpOffice.getValueAt(rowSelected, 1).toString());
					if (timesheets.getDate().compareTo(date) != 0 & timesheets.getEmployeeID() != employeeID) {
						JOptionPane.showMessageDialog(this, "Không được cập nhật nhân viên và ngày chấm công",
								"Thông báo", JOptionPane.NO_OPTION, null);
					} else {
						if (timesheetsDAO.updateTimesheetsOffice(timesheets)) {
							loadDataToTable(timesheetsDAO.getAllTimesheetsOffices());
							JOptionPane.showMessageDialog(this, "Cập nhật chấm công thành công.", "Thông báo",
									JOptionPane.NO_OPTION, null);
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật chấm công không thành công.", "Thông báo",
									JOptionPane.NO_OPTION, null);
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == btnSearch) {
			Date date = dateSearchEmpOffice.getDate();
			String emp = cboSearchEmpOffice.getSelectedItem().toString();
			if (date == null) {
				if (emp.equals("Tất cả nhân viên")) {
					loadDataToTable(timesheetsDAO.getAllTimesheetsOffices());
				} else {
					loadDataToTable(timesheetsDAO.searchByEmp(emp));
				}
			} else {
				loadDataToTable(timesheetsDAO.searchByDateAndEmp(emp, date));
			}
		}
		if (e.getSource() == btnAddOfWorker) {
			int rowSelected = tblAssignmentWorker.getSelectedRow();
			int assignmentID = Integer.parseInt(tblAssignmentWorker.getValueAt(rowSelected, 0).toString());
			int quantity = spinAmountCompleted.getValue();
			String emp = txtWorker.getText();
			Date date = dateJoin.getDate();
			TimesheetsFactory timesheetsFactory = new TimesheetsFactory(date, quantity, assignmentID);
			if (JOptionPane.showConfirmDialog(this,
					"Bạn có chắn chắn muốn thêm chấm công cho nhân viên " + emp + " không?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (timesheetsFactoryDAO.addTimesheetsFactory(timesheetsFactory)) {
					loadDateToTableTimesheetsWorker();
					JOptionPane.showMessageDialog(this, "Thêm chấm công thành công.", "Thông báo",
							JOptionPane.NO_OPTION, null);
				} else {
					JOptionPane.showMessageDialog(this, "Thêm chấm công không thành công.", "Thông báo",
							JOptionPane.NO_OPTION, null);
				}
			}
		}
		if (e.getSource() == btnUpdateOfWorker) {
			int rowSelected = tblTimesheetsWorker.getSelectedRow();
			int timesheetID = Integer.parseInt(tblTimesheetsWorker.getValueAt(rowSelected, 0).toString());
			int quantity = spinAmountCompleted.getValue();
			String emp = txtWorker.getText();
			Date date = dateJoin.getDate();
			TimesheetsFactory timesheetsFactory = new TimesheetsFactory(timesheetID, date, quantity);
			if (JOptionPane.showConfirmDialog(this,
					"Bạn có chắn chắn muốn cập nhật chấm công cho nhân viên " + emp + " không?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (timesheetsFactoryDAO.updateTimesheetsFactory(timesheetsFactory)) {
					loadDateToTableTimesheetsWorker();
					JOptionPane.showMessageDialog(this, "Cập nhật chấm công thành công.", "Thông báo",
							JOptionPane.NO_OPTION, null);
				} else {
					JOptionPane.showMessageDialog(this, "Cập nhật chấm công không thành công.", "Thông báo",
							JOptionPane.NO_OPTION, null);
				}
			}
		}
	}

}
