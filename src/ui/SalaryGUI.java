package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import model.SalaryDAO;
import entity.TimesheetsOffice;
import entity.Department;
import entity.EmployeeOffice;
import entity.Worker;
import entity.Factory;
import entity.TimesheetsFactory;
import entity.Bonus_Discipline;
import entity.TeamProducing;

import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class SalaryGUI extends JFrame implements ActionListener, MouseListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearchIdEmployee;
	private JTable tblListEmployee;
	private JTextField txtIdEmployee;
	private JTextField txtNameEmployee;
	private JTextField txtGenderEmployee;
	private JTextField txtPhonenumberEmployee;
	private JTextField txtNameBank;
	private JTextField txtSTKBank;
	private JTextField txtNameOwnerBank;
	private JTextField txtSpecializePosition;
	private JTextField txtIdTeamIdDeparment;
	private JTextField txtAddressEmployee;
	private JTextField txtNumberOfWorkDay;
	private JTextField txtNumberOfDayOff;
	private JTextField txtTargets;
	private JTextField txtReason;
	private JTextField txtBonus;
	private JTextField txtFine;
	private JTextField txtTotalSalary;
	private JButton btnSearchIdEmployee;
	private JComboBox<String> cmbTypeEmployee;
	private JComboBox<String> cmbFactoryDeparment;
	private DefaultTableModel dtmListEmployee;
	private JButton btnExportSalary;
	private JDateChooser dateBirthDayEmployee;
	private SalaryDAO Dao_Salary = new SalaryDAO();
	private DefaultComboBoxModel<String> dcmbTypeEmployee;
	private DefaultComboBoxModel<String> dcmbPosition;
	private DefaultComboBoxModel<String> dcmbTeam;
	private DefaultComboBoxModel<String> dcmbFactoryDeparment;
	private JMonthChooser mChMonth;
	private JYearChooser yChYear;

	public Component getUI() {
//	public SalaryGUI() {
		setSize(1200, 690);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0,140,140));

		JPanel pnlTopSalary = new JPanel();
		pnlTopSalary.setBackground(new Color(0,140,140));
		pnlTopSalary.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Nhập thôn tin",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlTopSalary.setBounds(10, 10, 924, 150);
		getContentPane().add(pnlTopSalary);
		pnlTopSalary.setLayout(null);

		JLabel lblNoteIdEmployee = new JLabel("Nhập mã nhân viên :");
		lblNoteIdEmployee.setForeground(Color.WHITE);
		lblNoteIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdEmployee.setBounds(43, 30, 116, 13);
		pnlTopSalary.add(lblNoteIdEmployee);

		txtSearchIdEmployee = new JTextField();
		txtSearchIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdEmployee.setColumns(10);
		txtSearchIdEmployee.setBounds(164, 30, 394, 19);
		pnlTopSalary.add(txtSearchIdEmployee);

		btnSearchIdEmployee = new JButton("Tìm");
		btnSearchIdEmployee.setFocusable(false);
		btnSearchIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdEmployee.setBounds(584, 30, 85, 21);
		pnlTopSalary.add(btnSearchIdEmployee);

		JPanel pnlFilterInformation = new JPanel();
		pnlFilterInformation.setBackground(new Color(0,140,140));
		pnlFilterInformation.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Lọc", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.WHITE));
		pnlFilterInformation.setBounds(10, 53, 904, 87);
		pnlTopSalary.add(pnlFilterInformation);
		pnlFilterInformation.setLayout(null);

		JLabel lblTypeEmployee = new JLabel("Loại nhân sự:");
		lblTypeEmployee.setForeground(Color.WHITE);
		lblTypeEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTypeEmployee.setBounds(36, 35, 85, 13);
		pnlFilterInformation.add(lblTypeEmployee);

		cmbTypeEmployee = new JComboBox<String>();
		dcmbTypeEmployee = new DefaultComboBoxModel<String>(
				new String[] { "All", "Nhân viên sản xuất", "Nhân viên hành chính" });
		cmbTypeEmployee.setModel(dcmbTypeEmployee);
		cmbTypeEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbTypeEmployee.setBounds(120, 35, 145, 21);
		pnlFilterInformation.add(cmbTypeEmployee);

		JLabel lblFactoryDeparment = new JLabel("PX/PB:");
		lblFactoryDeparment.setForeground(Color.WHITE);
		lblFactoryDeparment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFactoryDeparment.setBounds(355, 35, 50, 13);
		pnlFilterInformation.add(lblFactoryDeparment);

		cmbFactoryDeparment = new JComboBox<String>();
		dcmbFactoryDeparment = new DefaultComboBoxModel<String>(new String[] {});
		cmbFactoryDeparment.setModel(dcmbFactoryDeparment);
		cmbFactoryDeparment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbFactoryDeparment.setBounds(430, 35, 145, 21);
		pnlFilterInformation.add(cmbFactoryDeparment);
		dcmbPosition = new DefaultComboBoxModel<String>(new String[] {});

		JLabel lblTeam = new JLabel("Tổ :");
		lblTeam.setForeground(Color.WHITE);
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTeam.setBounds(664, 35, 31, 13);
		pnlFilterInformation.add(lblTeam);

		JComboBox<String> cmbTeam = new JComboBox<String>();
		dcmbTeam = new DefaultComboBoxModel<String>(new String[] {});
		cmbTeam.setModel(dcmbTeam);
		cmbTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbTeam.setBounds(718, 35, 145, 21);
		pnlFilterInformation.add(cmbTeam);
		
		mChMonth = new JMonthChooser();
		mChMonth.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 12));
		mChMonth.setBounds(741, 30, 96, 19);
		pnlTopSalary.add(mChMonth);
		
		yChYear = new JYearChooser();
		yChYear.setBounds(840, 30, 46, 19);
		pnlTopSalary.add(yChYear);

		JPanel pnlTableSalary = new JPanel();
		pnlTableSalary.setBackground(new Color(0,140,140));
		pnlTableSalary.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Bảng lương",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlTableSalary.setBounds(10, 170, 1166, 473);
		getContentPane().add(pnlTableSalary);
		pnlTableSalary.setLayout(null);

		JScrollPane scrListEmployee = new JScrollPane();
		scrListEmployee.setBounds(10, 27, 552, 436);
		pnlTableSalary.add(scrListEmployee);

		tblListEmployee = new JTable();
		tblListEmployee.setBackground(new Color(0,140,140));
		tblListEmployee.setForeground(Color.WHITE);
		tblListEmployee.setGridColor(Color.WHITE);
		tblListEmployee.setRowHeight(25);
		tblListEmployee.setBorder(new LineBorder(Color.WHITE));
		JTableHeader tblHeaderListEmployee = tblListEmployee.getTableHeader();
		tblHeaderListEmployee.setBackground(new Color(14,85,78));
		tblHeaderListEmployee.setForeground(Color.WHITE);
		tblHeaderListEmployee.setPreferredSize(new Dimension(100, 30));
		tblHeaderListEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblListEmployee.setModel(dtmListEmployee = new DefaultTableModel(new Object[][] {}, new String[] {
				"Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrListEmployee.setViewportView(tblListEmployee);

		JPanel pnlDetailSalary = new JPanel();
		pnlDetailSalary.setBackground(new Color(0,140,140));
		pnlDetailSalary.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Thông tin bảng lương",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlDetailSalary.setBounds(604, 27, 552, 436);
		pnlTableSalary.add(pnlDetailSalary);
		pnlDetailSalary.setLayout(null);

		JLabel lblIdEmployee = new JLabel("Mã nhân viên :");
		lblIdEmployee.setForeground(Color.WHITE);
		lblIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdEmployee.setBounds(10, 25, 86, 13);
		pnlDetailSalary.add(lblIdEmployee);

		txtIdEmployee = new JTextField();
		txtIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdEmployee.setBounds(106, 25, 136, 19);
		pnlDetailSalary.add(txtIdEmployee);
		txtIdEmployee.setColumns(10);

		JLabel lblNameEmployee = new JLabel("Tên nhân viên :");
		lblNameEmployee.setForeground(Color.WHITE);
		lblNameEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameEmployee.setBounds(10, 65, 86, 13);
		pnlDetailSalary.add(lblNameEmployee);

		txtNameEmployee = new JTextField();
		txtNameEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameEmployee.setColumns(10);
		txtNameEmployee.setBounds(106, 65, 136, 19);
		pnlDetailSalary.add(txtNameEmployee);

		JLabel lblGenderEmployee = new JLabel("Giới tính :");
		lblGenderEmployee.setForeground(Color.WHITE);
		lblGenderEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGenderEmployee.setBounds(10, 105, 86, 13);
		pnlDetailSalary.add(lblGenderEmployee);

		txtGenderEmployee = new JTextField();
		txtGenderEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGenderEmployee.setColumns(10);
		txtGenderEmployee.setBounds(106, 105, 136, 19);
		pnlDetailSalary.add(txtGenderEmployee);

		JLabel lblBirthDay = new JLabel("Ngày sinh :");
		lblBirthDay.setForeground(Color.WHITE);
		lblBirthDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBirthDay.setBounds(10, 145, 86, 13);
		pnlDetailSalary.add(lblBirthDay);

		JLabel lblPhonenumberEmployee = new JLabel("Số điện thoại :");
		lblPhonenumberEmployee.setForeground(Color.WHITE);
		lblPhonenumberEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhonenumberEmployee.setBounds(10, 185, 86, 13);
		pnlDetailSalary.add(lblPhonenumberEmployee);

		txtPhonenumberEmployee = new JTextField();
		txtPhonenumberEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPhonenumberEmployee.setColumns(10);
		txtPhonenumberEmployee.setBounds(106, 185, 136, 19);
		pnlDetailSalary.add(txtPhonenumberEmployee);

		JLabel lblNameBank = new JLabel("Tên ngân hàng :");
		lblNameBank.setForeground(Color.WHITE);
		lblNameBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameBank.setBounds(288, 25, 102, 13);
		pnlDetailSalary.add(lblNameBank);

		txtNameBank = new JTextField();
		txtNameBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameBank.setColumns(10);
		txtNameBank.setBounds(406, 25, 136, 19);
		pnlDetailSalary.add(txtNameBank);

		JLabel lblSTKBank = new JLabel("Số tài khoản :");
		lblSTKBank.setForeground(Color.WHITE);
		lblSTKBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSTKBank.setBounds(288, 65, 102, 13);
		pnlDetailSalary.add(lblSTKBank);

		txtSTKBank = new JTextField();
		txtSTKBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSTKBank.setColumns(10);
		txtSTKBank.setBounds(406, 65, 136, 19);
		pnlDetailSalary.add(txtSTKBank);

		JLabel lblNameOwnerBank = new JLabel("Người hưởng thụ :");
		lblNameOwnerBank.setForeground(Color.WHITE);
		lblNameOwnerBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameOwnerBank.setBounds(288, 105, 102, 13);
		pnlDetailSalary.add(lblNameOwnerBank);

		txtNameOwnerBank = new JTextField();
		txtNameOwnerBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameOwnerBank.setColumns(10);
		txtNameOwnerBank.setBounds(406, 105, 136, 19);
		pnlDetailSalary.add(txtNameOwnerBank);

		JLabel lblSpecializePosition = new JLabel("Chuyên môn/Chức vụ:");
		lblSpecializePosition.setForeground(Color.WHITE);
		lblSpecializePosition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSpecializePosition.setBounds(274, 145, 128, 13);
		pnlDetailSalary.add(lblSpecializePosition);

		txtSpecializePosition = new JTextField();
		txtSpecializePosition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSpecializePosition.setColumns(10);
		txtSpecializePosition.setBounds(406, 145, 136, 19);
		pnlDetailSalary.add(txtSpecializePosition);

		JLabel lblIdTeamIdDeparment = new JLabel("Mã tổ/Mã PB:");
		lblIdTeamIdDeparment.setForeground(Color.WHITE);
		lblIdTeamIdDeparment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdTeamIdDeparment.setBounds(274, 185, 128, 13);
		pnlDetailSalary.add(lblIdTeamIdDeparment);

		txtIdTeamIdDeparment = new JTextField();
		txtIdTeamIdDeparment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdTeamIdDeparment.setColumns(10);
		txtIdTeamIdDeparment.setBounds(406, 185, 136, 19);
		pnlDetailSalary.add(txtIdTeamIdDeparment);

		JLabel lblAddressEmployee = new JLabel("Địa chỉ :");
		lblAddressEmployee.setForeground(Color.WHITE);
		lblAddressEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddressEmployee.setBounds(10, 225, 63, 13);
		pnlDetailSalary.add(lblAddressEmployee);

		txtAddressEmployee = new JTextField();
		txtAddressEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAddressEmployee.setColumns(10);
		txtAddressEmployee.setBounds(106, 225, 436, 19);
		pnlDetailSalary.add(txtAddressEmployee);

		dateBirthDayEmployee = new JDateChooser();
		dateBirthDayEmployee.setBounds(106, 145, 136, 19);
		pnlDetailSalary.add(dateBirthDayEmployee);

		JLabel lblNumberOfWorkDay = new JLabel("Số ngày làm :");
		lblNumberOfWorkDay.setForeground(Color.WHITE);
		lblNumberOfWorkDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumberOfWorkDay.setBounds(10, 265, 86, 13);
		pnlDetailSalary.add(lblNumberOfWorkDay);

		txtNumberOfWorkDay = new JTextField();
		txtNumberOfWorkDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumberOfWorkDay.setColumns(10);
		txtNumberOfWorkDay.setBounds(106, 265, 136, 19);
		pnlDetailSalary.add(txtNumberOfWorkDay);

		JLabel lblNumberOfDayOff = new JLabel("Số ngày nghỉ :");
		lblNumberOfDayOff.setForeground(Color.WHITE);
		lblNumberOfDayOff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumberOfDayOff.setBounds(288, 265, 102, 13);
		pnlDetailSalary.add(lblNumberOfDayOff);

		txtNumberOfDayOff = new JTextField();
		txtNumberOfDayOff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumberOfDayOff.setColumns(10);
		txtNumberOfDayOff.setBounds(406, 265, 136, 19);
		pnlDetailSalary.add(txtNumberOfDayOff);

		JLabel lblTargets = new JLabel("Chỉ tiêu :");
		lblTargets.setForeground(Color.WHITE);
		lblTargets.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTargets.setBounds(10, 305, 86, 13);
		pnlDetailSalary.add(lblTargets);

		txtTargets = new JTextField();
		txtTargets.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTargets.setColumns(10);
		txtTargets.setBounds(106, 305, 136, 19);
		pnlDetailSalary.add(txtTargets);

		JLabel lblReason = new JLabel("Lý do :");
		lblReason.setForeground(Color.WHITE);
		lblReason.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReason.setBounds(288, 305, 46, 13);
		pnlDetailSalary.add(lblReason);

		txtReason = new JTextField();
		txtReason.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtReason.setColumns(10);
		txtReason.setBounds(340, 305, 200, 19);
		pnlDetailSalary.add(txtReason);

		JLabel lblBonus = new JLabel("Tiền thưởng :");
		lblBonus.setForeground(Color.WHITE);
		lblBonus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBonus.setBounds(10, 345, 86, 13);
		pnlDetailSalary.add(lblBonus);

		txtBonus = new JTextField();
		txtBonus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBonus.setColumns(10);
		txtBonus.setBounds(106, 345, 136, 19);
		pnlDetailSalary.add(txtBonus);

		JLabel lblFine = new JLabel("Tiền phạt :");
		lblFine.setForeground(Color.WHITE);
		lblFine.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFine.setBounds(288, 345, 102, 13);
		pnlDetailSalary.add(lblFine);

		txtFine = new JTextField();
		txtFine.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFine.setColumns(10);
		txtFine.setBounds(406, 345, 136, 19);
		pnlDetailSalary.add(txtFine);

		JLabel lblTotalSalary = new JLabel("Tổng lương :");
		lblTotalSalary.setForeground(Color.WHITE);
		lblTotalSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotalSalary.setBounds(10, 385, 102, 13);
		pnlDetailSalary.add(lblTotalSalary);

		txtTotalSalary = new JTextField();
		txtTotalSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTotalSalary.setColumns(10);
		txtTotalSalary.setBounds(106, 385, 436, 19);
		pnlDetailSalary.add(txtTotalSalary);

		btnExportSalary = new JButton("Xuất lương");
		btnExportSalary.setFocusable(false);
		btnExportSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExportSalary.setBounds(977, 68, 147, 41);
		getContentPane().add(btnExportSalary);
		
		txtIdEmployee.setEditable(false);
		txtNameEmployee.setEditable(false);
		txtGenderEmployee.setEditable(false);
		JTextFieldDateEditor birthDay = (JTextFieldDateEditor) dateBirthDayEmployee.getDateEditor();
		birthDay.setEditable(false);
		txtPhonenumberEmployee.setEditable(false);
		txtAddressEmployee.setEditable(false);
		txtNameBank.setEditable(false);
		txtNameOwnerBank.setEditable(false);
		txtNumberOfDayOff.setEditable(false);
		txtNumberOfWorkDay.setEditable(false);
		txtSTKBank.setEditable(false);
		txtIdTeamIdDeparment.setEditable(false);
		txtSpecializePosition.setEditable(false);
		txtReason.setEditable(false);
		txtBonus.setEditable(false);
		txtFine.setEditable(false);
		txtTotalSalary.setEditable(false);
		txtTargets.setEditable(false);
		
		tblListEmployee.addMouseListener(this);
		btnSearchIdEmployee.addActionListener(this);
		btnExportSalary.addActionListener(this);

		cmbTypeEmployee.addItemListener((ItemEvent e) -> {
			Object item = e.getItem();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				deleteDataOnTableModel();
				dcmbFactoryDeparment.removeAllElements();
				dcmbTeam.removeAllElements();
				dcmbPosition.removeAllElements();
				if (item.equals("Nhân viên sản xuất")) {
					cmbTeam.setEnabled(true);
					List<Worker> ListEmployeeProductive = Dao_Salary.getListEmployeeProductive();
					for (Worker eProductive : ListEmployeeProductive) {
						@SuppressWarnings("deprecation")
						String[] row = { eProductive.getEmployeeID(), eProductive.getName(),
								eProductive.isGender() ? "Nam" : "Nữ", eProductive.getBirthday().toGMTString(),
								eProductive.getPhone(), eProductive.getAddress() };
						dtmListEmployee.addRow(row);
					}
					List<Factory> listFactory = Dao_Salary.getListFactory();
					dcmbFactoryDeparment.addElement("AllPX");
					for (Factory factory : listFactory) {
						dcmbFactoryDeparment.addElement(factory.getFactoryID());
					}
				} else if (item.equals("Nhân viên hành chính")) {
					cmbTeam.setEnabled(false);
					List<EmployeeOffice> ListEmployeeAdministrative = Dao_Salary.getListEmployeeAdministrative();
					for (EmployeeOffice eAdministrative : ListEmployeeAdministrative) {
						@SuppressWarnings("deprecation")
						String[] row = { eAdministrative.getEmployeeID(), eAdministrative.getName(),
								eAdministrative.isGender() ? "Nam" : "Nữ", eAdministrative.getBirthday().toGMTString(),
								eAdministrative.getPhone(), eAdministrative.getAddress() };
						dtmListEmployee.addRow(row);
					}
					List<Department> listDepartment = Dao_Salary.getListDepartment();
					dcmbFactoryDeparment.addElement("AllPB");
					for (Department department : listDepartment) {
						dcmbFactoryDeparment.addElement(department.getDepartmentID());
					}
				} else {
					cmbTeam.setEnabled(true);
					loadListEmployee();
				}
			}
		});
		cmbFactoryDeparment.addItemListener((ItemEvent e) -> {
			Object item = e.getItem();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (dcmbFactoryDeparment.getElementAt(0).equals("AllPX")) {
					List<Worker> listEmployee = new ArrayList<Worker>();
					List<TeamProducing> listTeam = Dao_Salary.getListTeamByIdFactory(item.toString());
					deleteDataOnTableModel();
					dcmbTeam.removeAllElements();
					dcmbTeam.addElement("All");
					if (item.toString() != "AllPX") {
						deleteDataOnTableModel();
						for (TeamProducing team : listTeam) {
							dcmbTeam.addElement(team.getTeamID());
							listEmployee = Dao_Salary.getListEmployeeByIdTeam(team.getTeamID());
							for (Worker eProductive : listEmployee) {
								@SuppressWarnings("deprecation")
								String[] row = { eProductive.getEmployeeID(), eProductive.getName(),
										eProductive.isGender() ? "Nam" : "Nữ", eProductive.getBirthday().toGMTString(),
										eProductive.getPhone(), eProductive.getAddress() };
								dtmListEmployee.addRow(row);
							}
						}
					} else {
						deleteDataOnTableModel();
						List<Worker> ListEmployeeProductive = Dao_Salary.getListEmployeeProductive();
						for (Worker eProductive : ListEmployeeProductive) {
							@SuppressWarnings("deprecation")
							String[] row = { eProductive.getEmployeeID(), eProductive.getName(),
									eProductive.isGender() ? "Nam" : "Nữ", eProductive.getBirthday().toGMTString(),
									eProductive.getPhone(), eProductive.getAddress() };
							dtmListEmployee.addRow(row);
						}
					}
				} else {
					List<EmployeeOffice> listEmployee = Dao_Salary.getListEmployeeByIdDepartment(item.toString());
					deleteDataOnTableModel();
					if (item.toString().equals("AllPB")) {
						List<EmployeeOffice> ListEmployeeAdministrative = Dao_Salary.getListEmployeeAdministrative();
						for (EmployeeOffice eAdministrative : ListEmployeeAdministrative) {
							@SuppressWarnings("deprecation")
							String[] row = { eAdministrative.getEmployeeID(), eAdministrative.getName(),
									eAdministrative.isGender() ? "Nam" : "Nữ",
									eAdministrative.getBirthday().toGMTString(), eAdministrative.getPhone(),
									eAdministrative.getAddress() };
							dtmListEmployee.addRow(row);
						}
					} else {
						for (EmployeeOffice eAdministrative : listEmployee) {
							@SuppressWarnings("deprecation")
							String[] row = { eAdministrative.getEmployeeID(), eAdministrative.getName(),
									eAdministrative.isGender() ? "Nam" : "Nữ",
									eAdministrative.getBirthday().toGMTString(), eAdministrative.getPhone(),
									eAdministrative.getAddress() };
							dtmListEmployee.addRow(row);
						}
					}
				}
			}
		});
		cmbTeam.addItemListener((ItemEvent e) -> {
			Object item = e.getItem();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				// Item has been selected
				deleteDataOnTableModel();
				if (item.toString() != "All") {
					List<Worker> listEmployeeProductive = Dao_Salary.getListEmployeeByIdTeam(item.toString());
					for (Worker eProductive : listEmployeeProductive) {
						@SuppressWarnings("deprecation")
						String[] row = { eProductive.getEmployeeID(), eProductive.getName(),
								eProductive.isGender() ? "Nam" : "Nữ", eProductive.getBirthday().toGMTString(),
								eProductive.getPhone(), eProductive.getAddress() };
						dtmListEmployee.addRow(row);
					}
				} else {
					List<Worker> listEmployee = new ArrayList<Worker>();
					List<TeamProducing> listTeam = Dao_Salary
							.getListTeamByIdFactory(dcmbFactoryDeparment.getSelectedItem().toString());
					for (TeamProducing team : listTeam) {
						listEmployee = Dao_Salary.getListEmployeeByIdTeam(team.getTeamID());
						for (Worker eProductive : listEmployee) {
							@SuppressWarnings("deprecation")
							String[] row = { eProductive.getEmployeeID(), eProductive.getName(),
									eProductive.isGender() ? "Nam" : "Nữ", eProductive.getBirthday().toGMTString(),
									eProductive.getPhone(), eProductive.getAddress() };
							dtmListEmployee.addRow(row);
						}
					}
				}
			}
		});

		loadListEmployee();
		return getContentPane();
	}

	public static void main(String[] args) {
		new SalaryGUI().setVisible(true);
	}

	public void loadListEmployee() {
		List<Worker> ListEmployeeProductive = Dao_Salary.getListEmployeeProductive();
		List<EmployeeOffice> ListEmployeeAdministrative = Dao_Salary.getListEmployeeAdministrative();
		for (Worker eProductive : ListEmployeeProductive) {
			@SuppressWarnings("deprecation")
			String[] row = { eProductive.getEmployeeID(), eProductive.getName(), eProductive.isGender() ? "Nam" : "Nữ",
					eProductive.getBirthday().toGMTString(), eProductive.getPhone(), eProductive.getAddress() };
			dtmListEmployee.addRow(row);
		}
		for (EmployeeOffice eAdministrative : ListEmployeeAdministrative) {
			@SuppressWarnings("deprecation")
			String[] row = { eAdministrative.getEmployeeID(), eAdministrative.getName(),
					eAdministrative.isGender() ? "Nam" : "Nữ", eAdministrative.getBirthday().toGMTString(),
					eAdministrative.getPhone(), eAdministrative.getAddress() };
			dtmListEmployee.addRow(row);
		}
	}

	public void formSalaryEmployeeProductive(Worker eProductive) {
		List<TimesheetsFactory> listTimeKeep = Dao_Salary.workDayOfEmployyProductive(eProductive.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
		List<Bonus_Discipline> listRD = Dao_Salary.listRDEmployeeProductive(eProductive.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
		int targets = 0;
		int numberDayOfMonth = 0;
		double bonus = 0;
		double fine = 0;
		for (Bonus_Discipline rd : listRD) {
			if (rd.getAmountMoney() >= 0) {
				bonus += rd.getAmountMoney();
			} else {
				fine += rd.getAmountMoney();
			}
		}
		for (TimesheetsFactory time : listTimeKeep) {
			targets += time.getQuantity();
		}
		LocalDate date = LocalDate.now();
		if (date.getMonthValue() == 1 || date.getMonthValue() == 3 || date.getMonthValue() == 5
				|| date.getMonthValue() == 7 || date.getMonthValue() == 8 || date.getMonthValue() == 10
				|| date.getMonthValue() == 12) {
			numberDayOfMonth = 31;
		} else if (date.getMonthValue() == 2) {
			numberDayOfMonth = 28;
		} else {
			numberDayOfMonth = 30;
		}
		txtIdEmployee.setText(eProductive.getEmployeeID().trim());
		txtNameEmployee.setText(eProductive.getName().trim());
		txtGenderEmployee.setText(eProductive.isGender() ? "Nam" : "Nữ");
		dateBirthDayEmployee.setDate(eProductive.getBirthday());
		txtPhonenumberEmployee.setText("0" + eProductive.getPhone().trim());
		txtAddressEmployee.setText(eProductive.getAddress().trim());
		txtNameBank.setText(eProductive.getBankName().trim());
		txtSTKBank.setText(eProductive.getAccountNumber().trim());
		txtNameOwnerBank.setText(eProductive.getBeneficiany().trim());
		txtSpecializePosition.setText(eProductive.getSpeciality().trim());
		txtIdTeamIdDeparment.setText(eProductive.getTeamID().trim());
		txtNumberOfWorkDay.setText(listTimeKeep.size() + "");
		txtNumberOfDayOff.setText(numberDayOfMonth - listTimeKeep.size() + "");
		txtTargets.setText(targets >= 15000 ? "Đạt" : "Không đạt");
		txtReason.setText(txtTargets.getText().equals("Không đạt") ? "Số lượng không đạt yêu cầu" : "");
		txtBonus.setText(bonus + "");
		txtFine.setText(fine + "");
		
		double salary = Double.parseDouble(Dao_Salary.totalSalaryOfE(eProductive.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear())+ bonus + fine + "");
		NumberFormat formatter = new DecimalFormat("###.#####");
		String totalSalary = formatter.format(salary);
		txtTotalSalary.setText(totalSalary);
	}

	public void deleteDataOnTableModel() {
		dtmListEmployee.setRowCount(0);
	}

	@SuppressWarnings("deprecation")
	public void FormSalaryEmployeeAdministrative(EmployeeOffice eAdministrative) {
		List<TimesheetsOffice> listTimeKeep = Dao_Salary.hourWorkOfEmployeeAdministrative(eAdministrative.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
		List<Bonus_Discipline> listRD = Dao_Salary.listRDEmployeeAdministrative(eAdministrative.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
		int timeWorkOfMonth = 0;
		int numberDayOfMonth = 0;
		double bonus = 0;
		double fine = 0;
		for (Bonus_Discipline rd : listRD) {
			if (rd.getAmountMoney() >= 0) {
				bonus += rd.getAmountMoney();
			} else {
				fine += rd.getAmountMoney();
			}
		}
		for (TimesheetsOffice time : listTimeKeep) {
			timeWorkOfMonth += (time.getCheckOutAM().getHours() - time.getCheckInAM().getHours())
					+ (time.getCheckOutPM().getHours() - time.getCheckInPM().getHours());
		}
		LocalDate date = LocalDate.now();
		if (date.getMonthValue() == 1 || date.getMonthValue() == 3 || date.getMonthValue() == 5
				|| date.getMonthValue() == 7 || date.getMonthValue() == 8 || date.getMonthValue() == 10
				|| date.getMonthValue() == 12) {
			numberDayOfMonth = 31;
		} else if (date.getMonthValue() == 2) {
			numberDayOfMonth = 28;
		} else {
			numberDayOfMonth = 30;
		}
		txtIdEmployee.setText(eAdministrative.getEmployeeID().trim());
		txtNameEmployee.setText(eAdministrative.getName().trim());
		txtGenderEmployee.setText(eAdministrative.isGender() ? "Nam" : "Nữ");
		dateBirthDayEmployee.setDate(eAdministrative.getBirthday());
		txtPhonenumberEmployee.setText("0" + eAdministrative.getPhone().trim());
		txtAddressEmployee.setText(eAdministrative.getAddress().trim());
		txtNameBank.setText(eAdministrative.getBankName().trim());
		txtSTKBank.setText(eAdministrative.getAccountNumber().trim());
		txtNameOwnerBank.setText(eAdministrative.getBeneficiany().trim());
		txtSpecializePosition.setText(eAdministrative.getPosition().trim());
		txtIdTeamIdDeparment.setText(eAdministrative.getDepartmentID().trim());
		txtNumberOfWorkDay.setText(listTimeKeep.size() + "");
		txtNumberOfDayOff.setText(numberDayOfMonth - listTimeKeep.size() + "");
		txtTargets.setText(timeWorkOfMonth >= 280 ? "Đạt" : "Không đạt");
		txtReason.setText(txtTargets.getText().equals("Không đạt") ? "Số giờ làm không đạt yêu cầu" : "");
		txtBonus.setText(bonus + "");
		txtFine.setText(fine + "");
		txtTotalSalary.setText(timeWorkOfMonth * 30000 + bonus + fine + "");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblListEmployee)) {
			int rowSelected = tblListEmployee.getSelectedRow();
			Worker eProductive = Dao_Salary
					.searchEmployeeProductiveById(dtmListEmployee.getValueAt(rowSelected, 0).toString());
			EmployeeOffice eAdministrative = Dao_Salary
					.searchEmployeeAdministrativeById(dtmListEmployee.getValueAt(rowSelected, 0).toString());
			if (rowSelected >= 0) {
				if (eProductive != null) {
					formSalaryEmployeeProductive(eProductive);
				} else {
					FormSalaryEmployeeAdministrative(eAdministrative);
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnSearchIdEmployee)) {
			Worker eProductive = Dao_Salary.searchEmployeeProductiveById(txtSearchIdEmployee.getText());
			EmployeeOffice eAdministrative = Dao_Salary.searchEmployeeAdministrativeById(txtSearchIdEmployee.getText());
			cmbTypeEmployee.setSelectedIndex(0);
			if (eProductive != null) {
				deleteDataOnTableModel();
				@SuppressWarnings("deprecation")
				String[] row = { eProductive.getEmployeeID(), eProductive.getName(),
						eProductive.isGender() ? "Nam" : "Nữ", eProductive.getBirthday().toGMTString(),
						eProductive.getPhone(), eProductive.getAddress() };
				dtmListEmployee.addRow(row);
			} else if (eAdministrative != null) {
				deleteDataOnTableModel();
				@SuppressWarnings("deprecation")
				String[] row = { eAdministrative.getEmployeeID(), eAdministrative.getName(),
						eAdministrative.isGender() ? "Nam" : "Nữ", eAdministrative.getBirthday().toGMTString(),
						eAdministrative.getPhone(), eAdministrative.getAddress() };
				dtmListEmployee.addRow(row);
			} else if (txtSearchIdEmployee.getText().isEmpty()) {
				deleteDataOnTableModel();
				loadListEmployee();
			} else {
				deleteDataOnTableModel();
			}
		} else if (o.equals(btnExportSalary)) {
			String idE = "Mã nhân viên : " + txtIdEmployee.getText();
			String nameE = "Tên nhân viên : " + txtNameEmployee.getText();
			String gender = "Giới tính : " + txtGenderEmployee.getText();
			SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
			String stringDate = DateFor.format(dateBirthDayEmployee.getDate());
			String birthDay = "Ngày sinh : " + stringDate;
			String phonenumber = "Số điện thoại : " + txtPhonenumberEmployee.getText();
			String address = "Địa chỉ : " + txtAddressEmployee.getText();
			String nameBank = "Tên ngân hàng : " + txtNameBank.getText();
			String stkBank = "Số tài khoản : " + txtSTKBank.getText();
			String nameOwner = "Người hưởng thụ : " + txtNameOwnerBank.getText();
			String specializePosition = "Chuyên môn/Chức vụ : " + txtSpecializePosition.getText();
			String idTeamDepartment = "Mã tổ/Mã phòng ban : " + txtIdTeamIdDeparment.getText();
			String numberOfWorkDay = "Số ngày làm việc : " + txtNumberOfWorkDay.getText();
			String numberOfDayOff = "Số ngày nghỉ : " + txtNumberOfDayOff.getText();
			String targets = "Chỉ tiêu : " + txtTargets.getText();
			String reason = "Lý do : " + txtReason.getText();
			String bonus = "Tiền thưởng : " + txtBonus.getText();
			String fine = "Tiền phạt : " + txtFine.getText();
			String totalSalary = "Tổng lương : " + txtTotalSalary.getText();
			List<String> listInfo = new ArrayList<String>();
			listInfo.add(idE);
			listInfo.add(nameE);
			listInfo.add(gender);
			listInfo.add(birthDay);
			listInfo.add(phonenumber);
			listInfo.add(address);
			listInfo.add(nameBank);
			listInfo.add(stkBank);
			listInfo.add(nameOwner);
			listInfo.add(specializePosition);
			listInfo.add(idTeamDepartment);
			listInfo.add(numberOfWorkDay);
			listInfo.add(numberOfDayOff);
			listInfo.add(targets);
			listInfo.add(reason);
			listInfo.add(bonus);
			listInfo.add(fine);
			listInfo.add(totalSalary);

			LocalDateTime date = LocalDateTime.now();

			try {
				FileWriter fw = new FileWriter("tableSalaryEployee/salary" + date.getMonthValue() + "." + date.getYear()
						+ "" + txtIdEmployee.getText() + ".txt");
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Bảng lương " + date.getMonthValue() + "/" + date.getYear());
				bw.newLine();
				bw.newLine();
				for (String s : listInfo) {
					bw.write(s);
					bw.newLine();
					bw.newLine();
				}
				JOptionPane.showMessageDialog(this, "Xuất lương thành công!!!");
				bw.close();
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}
}
