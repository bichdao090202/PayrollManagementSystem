package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import entity.TimesheetOffice;
import model.EmployeeOfficeDAO;
import model.TimesheetDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.ListSelectionModel;

public class TimesheetEmployeeOfficeGUI extends JFrame implements ActionListener {

	public TimesheetEmployeeOfficeGUI() {
		getUI();
	}

	private static final long serialVersionUID = 1L;
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private final String[] headerTable = new String[] { "Nhân viên", "Ngày chấm", "Vào sáng", "Ra sáng", "Vào chiều",
			"Ra chiều" };
	private final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	private static final Date CURRENT_DATE = new Date();
	private JPanel contentPane;
	private JTable tblEmp;
	private DefaultTableModel dtmEmp;
	private TimesheetDAO timesheetDAO;
	private EmployeeOfficeDAO employeeOfficeDAO;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnSearch;
	private JComboBox<String> cboEmp;
	private JComboBox<String> cboSearchEmp;
	private JDateChooser dateCheck;
	private JSpinField spinHourCheckInAM;
	private JSpinField spinMinuteCheckInAM;
	private JSpinField spinHourCheckOutAM;
	private JSpinField spinMinuteCheckOutAM;
	private JSpinField spinHourCheckOutPM;
	private JSpinField spinMinuteCheckOutPM;
	private JSpinField spinMinuteCheckInPM;
	private JSpinField spinHourCheckInPM;
	private JCheckBox chkAbsentPM;
	private JCheckBox chkAbsentAM;
	private JDateChooser dateSearchEmp;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimesheetEmployeeOfficeGUI frame = new TimesheetEmployeeOfficeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Component getUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		timesheetDAO = new TimesheetDAO();
		employeeOfficeDAO = new EmployeeOfficeDAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1200, 690);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnInput = new JPanel();
		pnInput.setBackground(new Color(255, 255, 255));
		pnInput.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Nhập thông tin chấm công"));
		contentPane.add(pnInput, BorderLayout.NORTH);
		pnInput.setLayout(new MigLayout("", "[][200px][40px][][][40px][][][40px][grow][]", "[grow][grow]"));

		JLabel lblEmp = new JLabel("Nhân viên");
		pnInput.add(lblEmp, "cell 0 0,alignx left");

		cboEmp = new JComboBox<String>();
		cboEmp.setBackground(Color.WHITE);
		cboEmp.setModel(new DefaultComboBoxModel<String>(employeeOfficeDAO.getAllName().toArray(String[]::new)));
		pnInput.add(cboEmp, "cell 1 0,growx");

		JLabel lblCheckInAM = new JLabel("Check In Sáng");
		pnInput.add(lblCheckInAM, "cell 3 0");

		spinHourCheckInAM = new JSpinField(0, 23);
		spinHourCheckInAM.setBackground(Color.WHITE);
		spinHourCheckInAM.setMaximum(9);
		spinHourCheckInAM.setMinimum(7);
		spinHourCheckInAM.setValue(8);
		spinHourCheckInAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckInAM, "flowx,cell 4 0,growx,aligny center");

		JLabel lblHourCheckInAM = new JLabel("giờ");
		pnInput.add(lblHourCheckInAM, "cell 4 0,alignx center");

		spinMinuteCheckInAM = new JSpinField();
		spinMinuteCheckInAM.setMinimum(0);
		spinMinuteCheckInAM.setMaximum(59);
		spinMinuteCheckInAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckInAM, "cell 4 0,growx,aligny center");

		JLabel lblMinuteCheckInAM = new JLabel("phút");
		pnInput.add(lblMinuteCheckInAM, "cell 4 0,alignx center");

		JLabel lblCheckOutAM = new JLabel("Check Out Sáng");
		pnInput.add(lblCheckOutAM, "cell 6 0");

		spinHourCheckOutAM = new JSpinField();
		spinHourCheckOutAM.setValue(12);
		spinHourCheckOutAM.setMaximum(13);
		spinHourCheckOutAM.setMinimum(11);
		spinHourCheckOutAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckOutAM, "flowx,cell 7 0,alignx left,aligny center");

		btnAdd = new JButton("Thêm   ");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdd.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAdd.setBackground(Color.WHITE);
			}
		});
		btnAdd.setBorder(new LineBorder(COLOR, 2, false));
		btnAdd.setForeground(COLOR);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.addActionListener(this);
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);
		pnInput.add(btnAdd, "flowx,cell 10 0");

		JLabel lblDate = new JLabel("Ngày chấm công:");
		pnInput.add(lblDate, "cell 0 1");

		dateCheck = new JDateChooser();
		dateCheck.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateCheck.setDate(CURRENT_DATE);
		dateCheck.setDateFormatString("yyyy-MM-dd");
		dateCheck.setMaxSelectableDate(CURRENT_DATE);
		pnInput.add(dateCheck, "cell 1 1,grow");

		JLabel lblHourCheckOutAM = new JLabel("giờ");
		pnInput.add(lblHourCheckOutAM, "cell 7 0");

		spinMinuteCheckOutAM = new JSpinField();
		spinMinuteCheckOutAM.setMinimum(0);
		spinMinuteCheckOutAM.setMaximum(59);
		spinMinuteCheckOutAM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckOutAM, "cell 7 0");

		JLabel lblMinuteCheckOutAM = new JLabel("phút");
		pnInput.add(lblMinuteCheckOutAM, "cell 7 0");

		JLabel lblCheckInPM = new JLabel("Check In Chiều");
		pnInput.add(lblCheckInPM, "cell 3 1,alignx center");

		spinHourCheckInPM = new JSpinField();
		spinHourCheckInPM.setValue(13);
		spinHourCheckInPM.setMaximum(14);
		spinHourCheckInPM.setMinimum(12);
		spinHourCheckInPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckInPM, "flowx,cell 4 1,alignx left,aligny center");

		JLabel lblCheckOutPM = new JLabel("Check Out Chiều");
		pnInput.add(lblCheckOutPM, "cell 6 1");

		spinHourCheckOutPM = new JSpinField();
		spinHourCheckOutPM.setValue(17);
		spinHourCheckOutPM.setMaximum(18);
		spinHourCheckOutPM.setMinimum(16);
		spinHourCheckOutPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinHourCheckOutPM, "flowx,cell 7 1,alignx left,aligny center");

		JLabel lblHourCheckInPM = new JLabel("giờ");
		pnInput.add(lblHourCheckInPM, "cell 4 1");

		spinMinuteCheckInPM = new JSpinField();
		spinMinuteCheckInPM.setValue(30);
		spinMinuteCheckInPM.setMinimum(0);
		spinMinuteCheckInPM.setMaximum(59);
		spinMinuteCheckInPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckInPM, "cell 4 1");

		JLabel lblMinuteCheckInPM = new JLabel("phút");
		pnInput.add(lblMinuteCheckInPM, "cell 4 1");

		JLabel lblHourCheckOutPM = new JLabel("giờ");
		pnInput.add(lblHourCheckOutPM, "cell 7 1");

		spinMinuteCheckOutPM = new JSpinField();
		spinMinuteCheckOutPM.setValue(30);
		spinMinuteCheckOutPM.setMinimum(0);
		spinMinuteCheckOutPM.setMaximum(59);
		spinMinuteCheckOutPM.setPreferredSize(new Dimension(40, 20));
		pnInput.add(spinMinuteCheckOutPM, "cell 7 1");

		JLabel lblMinuteCheckOutPM = new JLabel("phút");
		pnInput.add(lblMinuteCheckOutPM, "cell 7 1");

		btnUpdate = new JButton("Cập nhật");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setBackground(Color.WHITE);
			}
		});
		btnUpdate.setBorder(new LineBorder(COLOR, 2, false));
		btnUpdate.setForeground(COLOR);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.addActionListener(this);

		chkAbsentPM = new JCheckBox("Vắng chiều");
		chkAbsentPM.setBackground(new Color(255, 255, 255));
		chkAbsentPM.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					spinHourCheckInPM.setEnabled(false);
					spinMinuteCheckInPM.setEnabled(false);
					spinHourCheckOutPM.setEnabled(false);
					spinMinuteCheckOutPM.setEnabled(false);
					if (chkAbsentAM.isSelected()) {
						chkAbsentAM.setSelected(false);
					}
				} else {
					spinHourCheckInPM.setEnabled(true);
					spinMinuteCheckInPM.setEnabled(true);
					spinHourCheckOutPM.setEnabled(true);
					spinMinuteCheckOutPM.setEnabled(true);
				}
			}
		});
		chkAbsentPM.setFocusable(false);
		pnInput.add(chkAbsentPM, "cell 9 1");
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);
		pnInput.add(btnUpdate, "cell 10 1,growx");

		btnDelete = new JButton("Xóa   ");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(Color.WHITE);
			}
		});
		btnDelete.setBorder(new LineBorder(COLOR, 2, false));
		btnDelete.setForeground(COLOR);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDelete.addActionListener(this);
		btnDelete.setFocusable(false);
		pnInput.add(btnDelete, "cell 10 0");

		chkAbsentAM = new JCheckBox("Vắng sáng");
		chkAbsentAM.setBackground(new Color(255, 255, 255));
		chkAbsentAM.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					spinHourCheckInAM.setEnabled(false);
					spinMinuteCheckInAM.setEnabled(false);
					spinHourCheckOutAM.setEnabled(false);
					spinMinuteCheckOutAM.setEnabled(false);
					if (chkAbsentPM.isSelected()) {
						chkAbsentPM.setSelected(false);
					}
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
		pnInput.add(chkAbsentAM, "cell 9 0");

		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Bảng chấm công"));
		pnTable.setBackground(new Color(255, 255, 255));
		contentPane.add(pnTable, BorderLayout.CENTER);
		pnTable.setLayout(new BorderLayout(0, 0));

		JPanel pnSearch = new JPanel();
		pnSearch.setBackground(new Color(255, 255, 255));
		pnTable.add(pnSearch, BorderLayout.NORTH);
		pnSearch.setLayout(new MigLayout("", "[70px:n][200px:n][40px][][200px:n][][][][]", "[22px]"));

		JLabel lblEmpSearch = new JLabel("Nhân viên");
		pnSearch.add(lblEmpSearch, "cell 0 0,alignx left,aligny center");

		cboSearchEmp = new JComboBox<String>();
		cboSearchEmp.setBackground(Color.WHITE);
		cboSearchEmp.setModel(new DefaultComboBoxModel<String>(timesheetDAO.getAllNameEmp().toArray(String[]::new)));
		pnSearch.add(cboSearchEmp, "cell 1 0,growx,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Ngày chấm công");
		pnSearch.add(lblNewLabel_1, "cell 3 0,alignx left,aligny center");

		dateSearchEmp = new JDateChooser();
		dateSearchEmp.getCalendarButton().setBackground(Color.WHITE);
		dateSearchEmp.setMaxSelectableDate(CURRENT_DATE);
		dateSearchEmp.setDate(CURRENT_DATE);
		dateSearchEmp.setDateFormatString("yyyy-MM-dd");
		pnSearch.add(dateSearchEmp, "cell 4 0,growx,aligny center");

		btnSearch = new JButton();
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(Color.WHITE);
			}
		});
		btnSearch.setBorder(new LineBorder(COLOR, 2, false));
		btnSearch.setForeground(COLOR);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.addActionListener(this);
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("images\\operations\\filter.png"));
		pnSearch.add(btnSearch, "cell 6 0,alignx center,aligny center");

		JScrollPane scrollTable = new JScrollPane();
		scrollTable.setBackground(new Color(255, 255, 255));
		pnTable.add(scrollTable, BorderLayout.CENTER);

		tblEmp = new JTable(dtmEmp = new DefaultTableModel(headerTable, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEmp.setBackground(Color.WHITE);
		tblEmp.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblEmp.getSelectedRow();
				cboEmp.setSelectedItem(dtmEmp.getValueAt(rowSelected, 0).toString());
				try {
					dateCheck.setDate(sdfDate.parse(dtmEmp.getValueAt(rowSelected, 1).toString()));
					try {
						Date checkInAM = sdfTime.parse(dtmEmp.getValueAt(rowSelected, 2).toString());
						spinHourCheckInAM.setValue(checkInAM.getHours());
						spinMinuteCheckInAM.setValue(checkInAM.getMinutes());

						Date checkOutAM = sdfTime.parse(dtmEmp.getValueAt(rowSelected, 3).toString());
						spinHourCheckOutAM.setValue(checkOutAM.getHours());
						spinMinuteCheckOutAM.setValue(checkOutAM.getMinutes());

						chkAbsentAM.setSelected(false);
					} catch (ParseException e2) {
						chkAbsentAM.setSelected(true);
						chkAbsentPM.setSelected(false);
					}

					try {
						Date checkInPM = sdfTime.parse(dtmEmp.getValueAt(rowSelected, 4).toString());
						spinHourCheckInPM.setValue(checkInPM.getHours());
						spinMinuteCheckInPM.setValue(checkInPM.getMinutes());

						Date checkOutPM = sdfTime.parse(dtmEmp.getValueAt(rowSelected, 5).toString());
						spinHourCheckOutPM.setValue(checkOutPM.getHours());
						spinMinuteCheckOutPM.setValue(checkOutPM.getMinutes());

						chkAbsentPM.setSelected(false);
					} catch (ParseException e3) {
						chkAbsentPM.setSelected(true);
						chkAbsentAM.setSelected(false);
					}
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		tblEmp.setFillsViewportHeight(true);
		tblEmp.getTableHeader().setOpaque(false);
		tblEmp.getTableHeader().setBackground(COLOR);
		tblEmp.getTableHeader().setForeground(Color.WHITE);
		scrollTable.setViewportView(tblEmp);

		loadDataToTable(timesheetDAO.getAllTimesheet());
		return contentPane;
	}

	public void loadDataToTable(List<TimesheetOffice> listTimesheets) {
		dtmEmp = new DefaultTableModel(headerTable, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmp.setModel(dtmEmp);
		for (TimesheetOffice timesheets : listTimesheets) {
			dtmEmp.addRow(timesheets.toString().split(","));
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

	public TimesheetOffice createTimesheetOfficeFromInput() {
		String employeeID = cboEmp.getSelectedItem().toString().substring(0, 9);
		Date date = dateCheck.getDate();
		TimesheetOffice timesheets = new TimesheetOffice(date, employeeID);
		if (!chkAbsentAM.isSelected() & !chkAbsentPM.isSelected()) {
			Date checkInAM = getTime(spinHourCheckInAM, spinMinuteCheckInAM);
			Date checkOutAM = getTime(spinHourCheckOutAM, spinMinuteCheckOutAM);
			Date checkInPM = getTime(spinHourCheckInPM, spinMinuteCheckInPM);
			Date checkOutPM = getTime(spinHourCheckOutPM, spinMinuteCheckOutPM);
			timesheets = new TimesheetOffice(date, checkInAM, checkOutAM, checkInPM, checkOutPM, employeeID);
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
		if (e.getSource() == btnAdd) {
			TimesheetOffice timesheet = createTimesheetOfficeFromInput();
			if (timesheetDAO.addTimesheet(timesheet)) {
				loadDataToTable(timesheetDAO.getAllTimesheet());
				JOptionPane.showMessageDialog(this, "Thêm chấm công thành công.", "Thông báo", JOptionPane.NO_OPTION,
						null);
			} else {
				JOptionPane.showMessageDialog(this, "Thêm chấm công không thành công.", "Thông báo",
						JOptionPane.NO_OPTION, null);
			}
		}
		if (e.getSource() == btnUpdate) {
			int rowSelected = tblEmp.getSelectedRow();
			if (rowSelected < 0) {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên trước", "Thông báo", JOptionPane.NO_OPTION,
						null);
			} else {
				try {
					TimesheetOffice timesheets = createTimesheetOfficeFromInput();
					String employeeID = dtmEmp.getValueAt(rowSelected, 0).toString().substring(0, 9);
					Date date = sdfDate.parse(dtmEmp.getValueAt(rowSelected, 1).toString());
					if (timesheets.getDate().compareTo(date) != 0 & timesheets.getEmployeeID() != employeeID) {
						JOptionPane.showMessageDialog(this, "Không được cập nhật nhân viên và ngày chấm công",
								"Thông báo", JOptionPane.NO_OPTION, null);
					} else {
						if (timesheetDAO.updateTimesheet(timesheets)) {
							loadDataToTable(timesheetDAO.getAllTimesheet());
							JOptionPane.showMessageDialog(this, "Cập nhật chấm công thành công.", "Thông báo",
									JOptionPane.NO_OPTION, null);
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật chấm công không thành công.", "Thông báo",
									JOptionPane.NO_OPTION, null);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == btnDelete) {
			int rowSelected = tblEmp.getSelectedRow();
			if (rowSelected < 0) {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên trước", "Thông báo", JOptionPane.NO_OPTION,
						null);
			} else {
				try {
					String employeeID = dtmEmp.getValueAt(rowSelected, 0).toString().substring(0, 9);
					Date date = sdfDate.parse(dtmEmp.getValueAt(rowSelected, 1).toString());
					if (timesheetDAO.deleteTimesheet(employeeID, date)) {
						loadDataToTable(timesheetDAO.getAllTimesheet());
						JOptionPane.showMessageDialog(this, "Xóa chấm công thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Xóa chấm công không thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == btnSearch) {
			Date date = dateSearchEmp.getDate();
			String emp = cboSearchEmp.getSelectedItem().toString();
			if (date == null) {
				if (emp.equals("Tất cả nhân viên")) {
					loadDataToTable(timesheetDAO.getAllTimesheet());
				} else {
					loadDataToTable(timesheetDAO.searchByEmp(emp));
				}
			} else {
				loadDataToTable(timesheetDAO.searchByDateAndEmp(emp, date));
			}
		}
	}
}
