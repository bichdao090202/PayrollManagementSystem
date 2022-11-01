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

import entity.TimesheetsOffice;
import model.EmployeeOfficeDAO;
import model.TimesheetsDAO;

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

public class TimesheetsGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfTime;
	private JPanel contentPane;
	private JTable tblEmpOffice;
	private String[] headerTableEmpOffice = new String[] { "Nhân viên", "Ngày chấm", "Vào sáng", "Ra sáng", "Vào chiều",
			"Ra chiều" };
	private String[] headerTableTimesheetsWorker = new String[] { "Mã nhân viên", "Tên nhân viên", "Mã sản phẩm",
			"Tên sản phẩm", "Mã quy trình", "Tên quy trình", "Số lượng thành phẩm" };
	private String[] headerTableAssignmentWorker = new String[] { "Mã nhân viên", "Tên nhân viên", "Mã quy trình",
			"Tên quy trình", "Ngày tham gia" };
	private DefaultTableModel dtmEmpOffice;
	private TimesheetsDAO timesheetsDAO;
	private EmployeeOfficeDAO employeeOfficeDAO;
	private JButton btnUpdateOfEmpOffice;
	private JButton btnAddOfEmpOffice;
	private JButton btnDeleteOfEmpOffice;
	private JButton btnSearch;
	private JTextField txtNameWorker;
	private JTextField txtProductName;
	private JTextField txtProduceName;
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
	private JComboBox<String> cboIDWorker;
	private JComboBox<String> cboProductID;
	private JComboBox<String> cboProduceID;

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
	
	public TimesheetsGUI() {
	}

	/**
	 * Create the frame.
	 */
	public Component getView() {
		sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		sdfTime = new SimpleDateFormat("HH:mm:ss");
		timesheetsDAO = new TimesheetsDAO();
		employeeOfficeDAO = new EmployeeOfficeDAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1200, 690);
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

		JLabel lblIDWorker = new JLabel("Mã nhân viên");
		panel_2.add(lblIDWorker, "cell 0 0,alignx left");

		cboIDWorker = new JComboBox<String>();
		panel_2.add(cboIDWorker, "cell 1 0,growx");

		JLabel lblNameWorker = new JLabel("Tên nhân viên");
		panel_2.add(lblNameWorker, "cell 2 0,alignx left");

		txtNameWorker = new JTextField();
		panel_2.add(txtNameWorker, "cell 3 0,growx");
		txtNameWorker.setColumns(10);

		JLabel lblProductID = new JLabel("Mã sản phẩm");
		panel_2.add(lblProductID, "cell 0 1,alignx left");

		cboProductID = new JComboBox<String>();
		panel_2.add(cboProductID, "cell 1 1,growx");

		JLabel lblProductName = new JLabel("Tên sản phẩm:");
		panel_2.add(lblProductName, "cell 2 1,alignx left");

		txtProductName = new JTextField();
		panel_2.add(txtProductName, "cell 3 1,growx");
		txtProductName.setColumns(10);

		JLabel lblProduceID = new JLabel("Mã quy trình");
		panel_2.add(lblProduceID, "cell 0 2,alignx left");

		cboProduceID = new JComboBox<String>();
		panel_2.add(cboProduceID, "cell 1 2,growx");

		JLabel lblProduceName = new JLabel("Tên quy trình");
		panel_2.add(lblProduceName, "cell 2 2,alignx left");

		txtProduceName = new JTextField();
		panel_2.add(txtProduceName, "cell 3 2,growx");
		txtProduceName.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Ngày chấm công:");
		panel_2.add(lblNewLabel_17, "cell 0 3,alignx left,aligny top");

		JDateChooser dateChooser_2 = new JDateChooser();
		panel_2.add(dateChooser_2, "cell 1 3,growx,aligny top");

		JLabel lblNewLabel_18 = new JLabel("Số lượng thành phẩm:");
		panel_2.add(lblNewLabel_18, "cell 2 3,alignx left,aligny top");

		JSpinField spinField = new JSpinField();
		spinField.setMinimum(0);
		panel_2.add(spinField, "cell 3 3,growx,aligny top");

		JPanel pnOperationOfWorker = new JPanel();
		panel_2.add(pnOperationOfWorker, "flowx,cell 0 4 2097051 1,alignx center");
		pnOperationOfWorker.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAddOfWorker = new JButton("Thêm chấm công");
		btnAddOfWorker.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAddOfWorker.setFocusable(false);
		pnOperationOfWorker.add(btnAddOfWorker);

		btnUpdateOfWorker = new JButton("Cập nhật chấm công");
		btnUpdateOfWorker.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdateOfWorker.setFocusable(false);
		pnOperationOfWorker.add(btnUpdateOfWorker);

		btnDeleteOfWorker = new JButton("Xóa chấm công");
		btnDeleteOfWorker.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDeleteOfWorker.setEnabled(false);
		btnDeleteOfWorker.setFocusable(false);
		pnOperationOfWorker.add(btnDeleteOfWorker);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Bảng phân công"));
		scrollPane_2.setPreferredSize(new Dimension(600, 200));
		scrollPane_2.setMaximumSize(new Dimension(32767, 167));
		panel.add(scrollPane_2, BorderLayout.WEST);

		tblAssignmentWorker = new JTable(new DefaultTableModel(headerTableAssignmentWorker, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
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

		tblTimesheetsWorker = new JTable(new DefaultTableModel(headerTableTimesheetsWorker, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
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
		tabbedPane.addTab("Nhân viên hành chính", null, tabEmpOffice, null);
		tabEmpOffice.setLayout(new BorderLayout(0, 0));

		JPanel pnInputEmpOffice = new JPanel();
		pnInputEmpOffice.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tabEmpOffice.add(pnInputEmpOffice, BorderLayout.NORTH);
		pnInputEmpOffice.setLayout(new MigLayout("", "[][200px:n:300px][40px][][][40px][][][grow][]", "[grow][grow]"));

		JLabel lblEmpOffice = new JLabel("Nhân viên");
		pnInputEmpOffice.add(lblEmpOffice, "cell 0 0,alignx left");

		cboEmpOffice = new JComboBox<String>();
		cboEmpOffice.setModel(new DefaultComboBoxModel<String>(employeeOfficeDAO.getAllName().toArray(String[]::new)));
		pnInputEmpOffice.add(cboEmpOffice, "cell 1 0,growx");

		JLabel lblCheckInAM = new JLabel("Check In Sáng");
		pnInputEmpOffice.add(lblCheckInAM, "cell 3 0");

		spinHourCheckInAM = new JSpinField(0, 23);
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
		spinHourCheckOutAM.setMaximum(23);
		spinHourCheckOutAM.setMinimum(0);
		spinHourCheckOutAM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinHourCheckOutAM, "flowx,cell 7 0,alignx left,aligny center");

		btnAddOfEmpOffice = new JButton("Thêm");
		btnAddOfEmpOffice.addActionListener(this);
		btnAddOfEmpOffice.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAddOfEmpOffice.setFocusable(false);
		pnInputEmpOffice.add(btnAddOfEmpOffice, "flowx,cell 9 0");

		JLabel lblDateTimesheets = new JLabel("Ngày chấm công:");
		pnInputEmpOffice.add(lblDateTimesheets, "cell 0 1");

		dateTimesheetOffice = new JDateChooser();
		dateTimesheetOffice.setDateFormatString("yyyy-MM-dd");
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
		spinHourCheckInPM.setMaximum(23);
		spinHourCheckInPM.setMinimum(0);
		spinHourCheckInPM.setPreferredSize(new Dimension(40, 20));
		pnInputEmpOffice.add(spinHourCheckInPM, "flowx,cell 4 1,alignx left,aligny center");

		JLabel lblCheckOutPM = new JLabel("Check Out Chiều");
		pnInputEmpOffice.add(lblCheckOutPM, "cell 6 1");

		spinHourCheckOutPM = new JSpinField();
		spinHourCheckOutPM.setValue(17);
		spinHourCheckOutPM.setMaximum(23);
		spinHourCheckOutPM.setMinimum(0);
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
		btnUpdateOfEmpOffice.addActionListener(this);
		btnUpdateOfEmpOffice.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdateOfEmpOffice.setFocusable(false);
		pnInputEmpOffice.add(btnUpdateOfEmpOffice, "cell 9 1,growx");

		btnDeleteOfEmpOffice = new JButton("Xóa");
		btnDeleteOfEmpOffice.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDeleteOfEmpOffice.setFocusable(false);
		btnDeleteOfEmpOffice.setEnabled(false);
		pnInputEmpOffice.add(btnDeleteOfEmpOffice, "cell 9 0");

		JPanel pnTableEmpOffice = new JPanel();
		tabEmpOffice.add(pnTableEmpOffice, BorderLayout.CENTER);
		pnTableEmpOffice.setLayout(new BorderLayout(0, 0));

		JPanel pnSearchEmpOffice = new JPanel();
		pnTableEmpOffice.add(pnSearchEmpOffice, BorderLayout.NORTH);
		pnSearchEmpOffice.setLayout(new MigLayout("", "[70px:n][200px:n][40px][][200px:n][][]", "[22px]"));

		JLabel lblNewLabel = new JLabel("Nhân viên");
		pnSearchEmpOffice.add(lblNewLabel, "cell 0 0,alignx left,aligny center");

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(employeeOfficeDAO.getAllName().toArray(String[]::new)));
		pnSearchEmpOffice.add(comboBox, "cell 1 0,growx,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Ngày chấm công");
		pnSearchEmpOffice.add(lblNewLabel_1, "cell 3 0,alignx left,aligny center");

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		pnSearchEmpOffice.add(dateChooser, "cell 4 0,growx,aligny center");

		btnSearch = new JButton("");
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("images\\operations\\filter.png"));
		pnSearchEmpOffice.add(btnSearch, "cell 6 0,alignx center,aligny center");

		JScrollPane scrollPane = new JScrollPane();
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

						Date checkInAM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 2).toString());
						spinHourCheckInAM.setValue(checkInAM.getHours());
						spinMinuteCheckInAM.setValue(checkInAM.getMinutes());

						Date checkOutAM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 3).toString());
						spinHourCheckOutAM.setValue(checkOutAM.getHours());
						spinMinuteCheckOutAM.setValue(checkOutAM.getMinutes());

						Date checkInPM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 4).toString());
						spinHourCheckInPM.setValue(checkInPM.getHours());
						spinMinuteCheckInPM.setValue(checkInPM.getMinutes());

						Date checkOutPM = sdfTime.parse(dtmEmpOffice.getValueAt(rowSelected, 5).toString());
						spinHourCheckOutPM.setValue(checkOutPM.getHours());
						spinMinuteCheckOutPM.setValue(checkOutPM.getMinutes());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(tblEmpOffice);

		loadDataToTable();
		return contentPane;
	}

	public void loadDataToTable() {
		dtmEmpOffice = new DefaultTableModel(headerTableEmpOffice, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmpOffice.setModel(dtmEmpOffice);
		List<TimesheetsOffice> timesheetsOffice = timesheetsDAO.getAllTimesheetsOffices();
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
		String employeeID = cboEmpOffice.getSelectedItem().toString().substring(0, 7);
		Date date = dateTimesheetOffice.getDate();
		Date checkInAM = getTime(spinHourCheckInAM, spinMinuteCheckInAM);
		Date checkOutAM = getTime(spinHourCheckOutAM, spinMinuteCheckOutAM);
		Date checkInPM = getTime(spinHourCheckInPM, spinMinuteCheckInPM);
		Date checkOutPM = getTime(spinHourCheckOutPM, spinMinuteCheckOutPM);
		TimesheetsOffice timesheets = new TimesheetsOffice(date, checkInAM, checkOutAM, checkInPM, checkOutPM,
				employeeID);
		return timesheets;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddOfEmpOffice) {
			TimesheetsOffice timesheets = createTimesheetsOfficeFromInput();
			if (timesheetsDAO.addTimesheetsOffice(timesheets)) {
				loadDataToTable();
				JOptionPane.showMessageDialog(this, "Thêm chấm công thành công.", "Thông báo", JOptionPane.NO_OPTION,
						null);
			} else {
				JOptionPane.showMessageDialog(this, "Thêm chấm công không thành công.", "Thông báo",
						JOptionPane.NO_OPTION, null);
			}
		} else if (e.getSource() == btnUpdateOfEmpOffice) {
			int rowSelected = tblEmpOffice.getSelectedRow();
			if (rowSelected < 0) {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên trước", "Thông báo", JOptionPane.NO_OPTION,
						null);
			}else {
				try {
					TimesheetsOffice timesheets = createTimesheetsOfficeFromInput();
					String employeeID = dtmEmpOffice.getValueAt(rowSelected, 0).toString().substring(0, 7);
					Date date = sdfDate.parse(dtmEmpOffice.getValueAt(rowSelected, 1).toString());
					if (timesheets.getDate().compareTo(date) != 0 & timesheets.getEmployeeID() != employeeID) {
						JOptionPane.showMessageDialog(this, "Không được cập nhật nhân viên và ngày chấm công", "Thông báo", JOptionPane.NO_OPTION,
								null);
					} else {
						if (timesheetsDAO.updateTimesheetsOffice(timesheets)) {
							loadDataToTable();
							JOptionPane.showMessageDialog(this, "Cập nhật chấm công thành công.", "Thông báo", JOptionPane.NO_OPTION,
									null);
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật chấm công không thành công.", "Thông báo", JOptionPane.NO_OPTION,
									null);
						}
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
