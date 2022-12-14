package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.text.pdf.BaseFont;
import model.Salary_DAO;
import entity.TimesheetOffice;
import entity.Department;
import entity.Employee;
import entity.EmployeeOffice;
import entity.Worker;
import entity.Factory;
import entity.TimesheetFactory;
import entity.TeamProducing;

import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import custom_field.JTextFieldHint;

import javax.swing.ImageIcon;

public class SalaryGUI extends JFrame implements ActionListener, MouseListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private JTable tblListEmployee;
	private JTextField txtIdEmployee, txtNameEmployee, txtGenderEmployee, txtPhonenumberEmployee, txtNameBank, txtSTKBank, txtNameOwnerBank, txtSpecializePosition, txtSearchIdEmployee;
	private JTextField txtIdTeamIdDeparment, txtAddressEmployee, txtNumberOfWorkDay, txtNumberOfDayOff, txtTargets, txtReason, txtBonus, txtFine, txtTotalSalary, dateBirthDayEmployee;
	private JButton btnSearchIdEmployee, btnExportSalary;
	private JComboBox<String> cmbTypeEmployee, cmbFactoryDeparment;
	private DefaultTableModel dtmListEmployee;
	private JButton btnExportListSalary;
	private SalaryDAO Dao_Salary = new SalaryDAO();
	private DefaultComboBoxModel<String> dcmbTypeEmployee, dcmbPosition, dcmbTeam, dcmbFactoryDeparment;

	private JMonthChooser mChMonth;
	private JYearChooser yChYear;
	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static final String fontString = "C:\\Windows\\Fonts\\times.ttf";
	private List<Employee> listEmployee = new ArrayList<Employee>();
	

	public SalaryGUI() {
		getContentPane().setBackground(Color.WHITE);
//		getUI();
	}
	
	public Component getUI() {
		setSize(1200, 690);
		getContentPane().setLayout(null);

		JPanel pnlTopSalary = new JPanel();
		pnlTopSalary.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Nhập thôn tin",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,140,140)));
		pnlTopSalary.setBounds(10, 10, 924, 150);
		getContentPane().add(pnlTopSalary);
		pnlTopSalary.setLayout(null);

		JLabel lblNoteIdEmployee = new JLabel("Nhập mã nhân viên :");
		lblNoteIdEmployee.setForeground(Color.BLACK);
		lblNoteIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdEmployee.setBounds(43, 30, 116, 13);
		pnlTopSalary.add(lblNoteIdEmployee);

		txtSearchIdEmployee = new JTextFieldHint("Nhập mã nhân viên...");
		txtSearchIdEmployee.setPreferredSize(new Dimension(200,25));
		txtSearchIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdEmployee.setColumns(10);
		txtSearchIdEmployee.setBounds(164, 30, 394, 19);
		pnlTopSalary.add(txtSearchIdEmployee);

		btnSearchIdEmployee = new JButton();
		btnSearchIdEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearchIdEmployee.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSearchIdEmployee.setBackground(Color.WHITE);
			}
		});
		btnSearchIdEmployee.setBorder(new LineBorder(COLOR, 2, false));
		btnSearchIdEmployee.setForeground(COLOR);
		btnSearchIdEmployee.setBackground(Color.WHITE);
		btnSearchIdEmployee.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchIdEmployee.setFocusable(false);
		btnSearchIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdEmployee.setBounds(584, 30, 85, 21);
		pnlTopSalary.add(btnSearchIdEmployee);

		JPanel pnlFilterInformation = new JPanel();
		pnlFilterInformation.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Lọc", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0,140,140)));
		pnlFilterInformation.setBounds(10, 53, 904, 87);
		pnlTopSalary.add(pnlFilterInformation);
		pnlFilterInformation.setLayout(null);

		JLabel lblTypeEmployee = new JLabel("Loại nhân sự:");
		lblTypeEmployee.setForeground(Color.BLACK);
		lblTypeEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTypeEmployee.setBounds(36, 35, 85, 13);
		pnlFilterInformation.add(lblTypeEmployee);

		cmbTypeEmployee = new JComboBox<String>();
		dcmbTypeEmployee = new DefaultComboBoxModel<String>(
				new String[] { "Tất cả nhân viên", "Nhân viên sản xuất", "Nhân viên hành chính" });
		cmbTypeEmployee.setModel(dcmbTypeEmployee);
		cmbTypeEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbTypeEmployee.setBounds(120, 35, 145, 21);
		pnlFilterInformation.add(cmbTypeEmployee);

		JLabel lblFactoryDeparment = new JLabel("PX/PB:");
		lblFactoryDeparment.setForeground(Color.BLACK);
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
		lblTeam.setForeground(Color.BLACK);
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
		pnlTableSalary.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Bảng lương",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,140,140)));
		pnlTableSalary.setBounds(10, 170, 1166, 473);
		getContentPane().add(pnlTableSalary);
		pnlTableSalary.setLayout(null);

		JScrollPane scrListEmployee = new JScrollPane();
		scrListEmployee.setBounds(10, 27, 552, 436);
		pnlTableSalary.add(scrListEmployee);

		tblListEmployee = new JTable();
		tblListEmployee.setForeground(Color.BLACK);
		tblListEmployee.setGridColor(new Color(0,140,140));
		tblListEmployee.setRowHeight(25);
		tblListEmployee.setBorder(new LineBorder(new Color(0,140,140)));
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
		pnlDetailSalary.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Thông tin bảng lương",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,140,140)));
		pnlDetailSalary.setBounds(604, 27, 552, 436);
		pnlTableSalary.add(pnlDetailSalary);
		pnlDetailSalary.setLayout(null);

		JLabel lblIdEmployee = new JLabel("Mã nhân viên :");
		lblIdEmployee.setForeground(Color.BLACK);
		lblIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdEmployee.setBounds(10, 25, 86, 13);
		pnlDetailSalary.add(lblIdEmployee);

		txtIdEmployee = new JTextField();
		txtIdEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdEmployee.setBounds(106, 25, 136, 19);
		txtIdEmployee.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtIdEmployee);
		txtIdEmployee.setColumns(10);

		JLabel lblNameEmployee = new JLabel("Tên nhân viên :");
		lblNameEmployee.setForeground(Color.BLACK);
		lblNameEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameEmployee.setBounds(10, 65, 86, 13);
		pnlDetailSalary.add(lblNameEmployee);

		txtNameEmployee = new JTextField();
		txtNameEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameEmployee.setColumns(10);
		txtNameEmployee.setBounds(106, 65, 136, 19);
		txtNameEmployee.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtNameEmployee);

		JLabel lblGenderEmployee = new JLabel("Giới tính :");
		lblGenderEmployee.setForeground(Color.BLACK);
		lblGenderEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGenderEmployee.setBounds(10, 105, 86, 13);
		pnlDetailSalary.add(lblGenderEmployee);

		txtGenderEmployee = new JTextField();
		txtGenderEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGenderEmployee.setColumns(10);
		txtGenderEmployee.setBounds(106, 105, 136, 19);
		txtGenderEmployee.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtGenderEmployee);

		JLabel lblBirthDay = new JLabel("Ngày sinh :");
		lblBirthDay.setForeground(Color.BLACK);
		lblBirthDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBirthDay.setBounds(10, 145, 86, 13);
		pnlDetailSalary.add(lblBirthDay);

		JLabel lblPhonenumberEmployee = new JLabel("Số điện thoại :");
		lblPhonenumberEmployee.setForeground(Color.BLACK);
		lblPhonenumberEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhonenumberEmployee.setBounds(10, 185, 86, 13);
		pnlDetailSalary.add(lblPhonenumberEmployee);

		txtPhonenumberEmployee = new JTextField();
		txtPhonenumberEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPhonenumberEmployee.setColumns(10);
		txtPhonenumberEmployee.setBounds(106, 185, 136, 19);
		txtPhonenumberEmployee.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtPhonenumberEmployee);

		JLabel lblNameBank = new JLabel("Tên ngân hàng :");
		lblNameBank.setForeground(Color.BLACK);
		lblNameBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameBank.setBounds(288, 25, 102, 13);
		pnlDetailSalary.add(lblNameBank);

		txtNameBank = new JTextField();
		txtNameBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameBank.setColumns(10);
		txtNameBank.setBounds(406, 25, 136, 19);
		txtNameBank.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtNameBank);

		JLabel lblSTKBank = new JLabel("Số tài khoản :");
		lblSTKBank.setForeground(Color.BLACK);
		lblSTKBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSTKBank.setBounds(288, 65, 102, 13);
		pnlDetailSalary.add(lblSTKBank);

		txtSTKBank = new JTextField();
		txtSTKBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSTKBank.setColumns(10);
		txtSTKBank.setBounds(406, 65, 136, 19);
		txtSTKBank.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtSTKBank);

		JLabel lblNameOwnerBank = new JLabel("Người hưởng thụ :");
		lblNameOwnerBank.setForeground(Color.BLACK);
		lblNameOwnerBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameOwnerBank.setBounds(288, 105, 102, 13);
		pnlDetailSalary.add(lblNameOwnerBank);

		txtNameOwnerBank = new JTextField();
		txtNameOwnerBank.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameOwnerBank.setColumns(10);
		txtNameOwnerBank.setBounds(406, 105, 136, 19);
		txtNameOwnerBank.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtNameOwnerBank);

		JLabel lblSpecializePosition = new JLabel("Chuyên môn/Chức vụ:");
		lblSpecializePosition.setForeground(Color.BLACK);
		lblSpecializePosition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSpecializePosition.setBounds(274, 145, 128, 13);
		pnlDetailSalary.add(lblSpecializePosition);

		txtSpecializePosition = new JTextField();
		txtSpecializePosition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSpecializePosition.setColumns(10);
		txtSpecializePosition.setBounds(406, 145, 136, 19);
		txtSpecializePosition.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtSpecializePosition);

		JLabel lblIdTeamIdDeparment = new JLabel("Mã tổ/Mã PB:");
		lblIdTeamIdDeparment.setForeground(Color.BLACK);
		lblIdTeamIdDeparment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdTeamIdDeparment.setBounds(274, 185, 128, 13);
		pnlDetailSalary.add(lblIdTeamIdDeparment);

		txtIdTeamIdDeparment = new JTextField();
		txtIdTeamIdDeparment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdTeamIdDeparment.setColumns(10);
		txtIdTeamIdDeparment.setBounds(406, 185, 136, 19);
		txtIdTeamIdDeparment.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtIdTeamIdDeparment);

		JLabel lblAddressEmployee = new JLabel("Địa chỉ :");
		lblAddressEmployee.setForeground(Color.BLACK);
		lblAddressEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddressEmployee.setBounds(10, 225, 63, 13);
		pnlDetailSalary.add(lblAddressEmployee);

		txtAddressEmployee = new JTextField();
		txtAddressEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAddressEmployee.setColumns(10);
		txtAddressEmployee.setBounds(106, 225, 436, 19);
		txtAddressEmployee.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtAddressEmployee);

		dateBirthDayEmployee = new JTextField();
		dateBirthDayEmployee.setBounds(106, 145, 136, 19);
		dateBirthDayEmployee.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(dateBirthDayEmployee);

		JLabel lblNumberOfWorkDay = new JLabel("Số ngày làm :");
		lblNumberOfWorkDay.setForeground(Color.BLACK);
		lblNumberOfWorkDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumberOfWorkDay.setBounds(10, 265, 86, 13);
		pnlDetailSalary.add(lblNumberOfWorkDay);

		txtNumberOfWorkDay = new JTextField();
		txtNumberOfWorkDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumberOfWorkDay.setColumns(10);
		txtNumberOfWorkDay.setBounds(106, 265, 136, 19);
		txtNumberOfWorkDay.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtNumberOfWorkDay);

		JLabel lblNumberOfDayOff = new JLabel("Số ngày nghỉ :");
		lblNumberOfDayOff.setForeground(Color.BLACK);
		lblNumberOfDayOff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumberOfDayOff.setBounds(288, 265, 102, 13);
		pnlDetailSalary.add(lblNumberOfDayOff);

		txtNumberOfDayOff = new JTextField();
		txtNumberOfDayOff.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumberOfDayOff.setColumns(10);
		txtNumberOfDayOff.setBounds(406, 265, 136, 19);
		txtNumberOfDayOff.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtNumberOfDayOff);

		JLabel lblTargets = new JLabel("Chỉ tiêu :");
		lblTargets.setForeground(Color.BLACK);
		lblTargets.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTargets.setBounds(10, 305, 86, 13);
		pnlDetailSalary.add(lblTargets);

		txtTargets = new JTextField();
		txtTargets.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTargets.setColumns(10);
		txtTargets.setBounds(106, 305, 136, 19);
		txtTargets.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtTargets);

		JLabel lblReason = new JLabel("Lý do :");
		lblReason.setForeground(Color.BLACK);
		lblReason.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReason.setBounds(288, 305, 46, 13);
		pnlDetailSalary.add(lblReason);

		txtReason = new JTextField();
		txtReason.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtReason.setColumns(10);
		txtReason.setBounds(340, 305, 200, 19);
		txtReason.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtReason);

		JLabel lblBonus = new JLabel("Tiền thưởng :");
		lblBonus.setForeground(Color.BLACK);
		lblBonus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBonus.setBounds(10, 345, 86, 13);
		pnlDetailSalary.add(lblBonus);

		txtBonus = new JTextField();
		txtBonus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBonus.setColumns(10);
		txtBonus.setBounds(106, 345, 136, 19);
		txtBonus.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtBonus);

		JLabel lblFine = new JLabel("Tiền phạt :");
		lblFine.setForeground(Color.BLACK);
		lblFine.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFine.setBounds(288, 345, 102, 13);
		pnlDetailSalary.add(lblFine);

		txtFine = new JTextField();
		txtFine.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFine.setColumns(10);
		txtFine.setBounds(406, 345, 136, 19);
		txtFine.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtFine);

		JLabel lblTotalSalary = new JLabel("Tổng lương :");
		lblTotalSalary.setForeground(Color.BLACK);
		lblTotalSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotalSalary.setBounds(10, 385, 102, 13);
		pnlDetailSalary.add(lblTotalSalary);

		txtTotalSalary = new JTextField();
		txtTotalSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTotalSalary.setColumns(10);
		txtTotalSalary.setBounds(106, 385, 296, 19);
		txtTotalSalary.setBorder(new LineBorder(new Color(0,140,140)));
		pnlDetailSalary.add(txtTotalSalary);

		btnExportListSalary = new JButton("Xuất DS Lương");
		btnExportListSalary.setIcon(new ImageIcon("images\\Adobe-PDF-Document-icon.png"));
		btnExportListSalary.setFocusable(false);
		btnExportListSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExportListSalary.setBounds(977, 68, 147, 41);
		getContentPane().add(btnExportListSalary);
		
		btnExportSalary = new JButton("Xuất Lương");
		btnExportSalary.setIcon(new ImageIcon("images\\Adobe-PDF-Document-icon.png"));
		btnExportSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExportSalary.setBounds(422, 380, 120, 30);
		pnlDetailSalary.add(btnExportSalary);
		
		DocumentListener enventChangeSearchEmployee = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				listEmployee = Dao_Salary.searchListEmployee(txtSearchIdEmployee.getText());
				if(txtSearchIdEmployee.getText().isEmpty()) {
					deleteDataOnTableModel();
					loadListEmployee();
				}
				else if(!txtSearchIdEmployee.getText().isEmpty() && listEmployee != null) {
					deleteDataOnTableModel();
					loadListEmployeeWithListEmployee(listEmployee);
				}
				else {
					deleteDataOnTableModel();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				listEmployee = Dao_Salary.searchListEmployee(txtSearchIdEmployee.getText());
				if(txtSearchIdEmployee.getText().isEmpty()) {
					deleteDataOnTableModel();
					loadListEmployee();
				}
				else if(!txtSearchIdEmployee.getText().isEmpty() && listEmployee != null) {
					deleteDataOnTableModel();
					loadListEmployeeWithListEmployee(listEmployee);
				}
				else {
					deleteDataOnTableModel();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				listEmployee = Dao_Salary.searchListEmployee(txtSearchIdEmployee.getText());
				if(txtSearchIdEmployee.getText().isEmpty()) {
					deleteDataOnTableModel();
					loadListEmployee();
				}
				else if(!txtSearchIdEmployee.getText().isEmpty() && listEmployee != null) {
					deleteDataOnTableModel();
					loadListEmployeeWithListEmployee(listEmployee);
				}
				else {
					deleteDataOnTableModel();
				}
			}

        };
        
        txtSearchIdEmployee.getDocument().addDocumentListener(enventChangeSearchEmployee);
		
		txtIdEmployee.setEditable(false);
		txtNameEmployee.setEditable(false);
		txtGenderEmployee.setEditable(false);
		dateBirthDayEmployee.setEditable(false);
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
		
//		btnSearchIdEmployee.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnExportSalary.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnExportListSalary.setBorder(new LineBorder(new Color(14,85,78), 2));
		
		btnSearchIdEmployee.setFocusPainted(false);
		btnExportSalary.setFocusPainted(false);
		btnExportListSalary.setFocusPainted(false);
		
		
		tblListEmployee.addMouseListener(this);
		btnSearchIdEmployee.addActionListener(this);
		btnExportListSalary.addActionListener(this);
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
					dcmbFactoryDeparment.addElement("Tất cả Phân Xưởng");
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
					dcmbFactoryDeparment.addElement("Tất cả Phòng Ban");
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
				if (dcmbFactoryDeparment.getElementAt(0).equals("Tất cả Phân Xưởng")) {
					List<Worker> listEmployee = new ArrayList<Worker>();
					List<TeamProducing> listTeam = Dao_Salary.getListTeamByIdFactory(item.toString());
					deleteDataOnTableModel();
					dcmbTeam.removeAllElements();
					dcmbTeam.addElement("Tất cả Tổ");
					if (item.toString() != "Tất cả Phân Xưởng") {
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
					if (item.toString().equals("Tất cả Phòng Ban")) {
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
				if (item.toString() != "Tất cả Tổ") {
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
		
		btnSearchIdEmployee.setMnemonic(KeyEvent.VK_S);
		btnSearchIdEmployee.setToolTipText("Phím tắt : Alt + S");
		btnExportListSalary.setMnemonic(KeyEvent.VK_E);
		btnExportListSalary.setToolTipText("Phím tắt : Alt + E");
		btnExportSalary.setMnemonic(KeyEvent.VK_X);
		btnExportSalary.setToolTipText("Phím tắt : Alt + X");

		loadListEmployee();
		return getContentPane();
	}

	public static void main(String[] args) {
		SalaryGUI salaryGui = new SalaryGUI();
		salaryGui.getUI();
		salaryGui.setVisible(true);
	}

	// Lấy và hiển thị tất cả nhân viên
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
	
	// Lấy và hiển thị danh sách nhân viên đã cho
	public void loadListEmployeeWithListEmployee(List<Employee> listEmployee) {
		for(Employee employee : listEmployee) {
			if(employee instanceof Worker) {
				String[] row = { employee.getEmployeeID(), employee.getName(), employee.isGender() ? "Nam" : "Nữ",
						employee.getBirthday().toGMTString(), employee.getPhone(), employee.getAddress() };
				dtmListEmployee.addRow(row);
			}
			else {
				String[] row = { employee.getEmployeeID(), employee.getName(),
						employee.isGender() ? "Nam" : "Nữ", employee.getBirthday().toGMTString(),
								employee.getPhone(), employee.getAddress() };
				dtmListEmployee.addRow(row);
			}
		}
	}

	// hiển thị thông tin nhân viên sản xuất vào form
	public void formSalaryEmployeeProductive(Worker eProductive) {
		List<TimesheetFactory> listTimeKeep = Dao_Salary.getWorkDayOfEmployyProductive(eProductive.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
		DecimalFormat formatter = new DecimalFormat("###,###,### VND");
		int targets = 0;
		int numberDayOfMonth = 0;
		for (TimesheetFactory time : listTimeKeep) {
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
		dateBirthDayEmployee.setText(dateFormat.format(eProductive.getBirthday()));
		txtPhonenumberEmployee.setText(eProductive.getPhone().trim());
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
		txtBonus.setText("0");
		txtFine.setText("0");
		
		double salary = Double.parseDouble(Dao_Salary.calculateTotalSalaryOfE(eProductive.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear()) + "");
		String totalSalary = formatter.format(salary);
		txtTotalSalary.setText(totalSalary);
	}

	// Xóa dữ liệu trên bảng
	public void deleteDataOnTableModel() {
		dtmListEmployee.setRowCount(0);
	}

	// Hiển thị thông tin nhân viên hành chính trên form
	@SuppressWarnings("deprecation")
	public void FormSalaryEmployeeAdministrative(EmployeeOffice eAdministrative) {
		List<TimesheetOffice> listTimeKeep = Dao_Salary.getNumberWorkOfEmployeeAdministrative(eAdministrative.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
		DecimalFormat formatter = new DecimalFormat("###,###,### VND");
		int numberWorkOfMonth = listTimeKeep.size();
		int numberDayOfMonth = 0;
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
		dateBirthDayEmployee.setText(dateFormat.format(eAdministrative.getBirthday()));
		txtPhonenumberEmployee.setText(eAdministrative.getPhone().trim());
		txtAddressEmployee.setText(eAdministrative.getAddress().trim());
		if(eAdministrative.getBankName() != null) {
			txtNameBank.setText(eAdministrative.getBankName().trim());
		}
		else {
			txtNameBank.setText("Chưa có");
		}
		if(eAdministrative.getAccountNumber() != null) {
			txtSTKBank.setText(eAdministrative.getAccountNumber().trim());
		}
		else {
			txtSTKBank.setText("Chưa có");
		}
		if(eAdministrative.getBeneficiany() != null) {
			txtNameOwnerBank.setText(eAdministrative.getBeneficiany().trim());
		}
		else {
			txtNameOwnerBank.setText("Chưa có");
		}
		txtSpecializePosition.setText(eAdministrative.getPosition().trim());
		txtIdTeamIdDeparment.setText(eAdministrative.getDepartmentID().trim());
		txtNumberOfWorkDay.setText(listTimeKeep.size() + "");
		txtNumberOfDayOff.setText(numberDayOfMonth - listTimeKeep.size() + "");
		txtTargets.setText(numberWorkOfMonth > 26 ? "Đạt" : "Không đạt");
		txtReason.setText(txtTargets.getText().equals("Không đạt") ? "Số ngày làm không đạt yêu cầu" : "");
		txtBonus.setText("0");
		txtFine.setText("0");
		
		double salary = ((eAdministrative.getSalary()/26) * numberWorkOfMonth);
		String totalSalary = formatter.format(salary);
		
		txtTotalSalary.setText(totalSalary);
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

	@SuppressWarnings("deprecation")
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
			try {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File  
						(System.getProperty("user.home") + System.getProperty("file.separator")+ "Documents"));
				fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
					@Override
					public String getDescription() {
						return "PDF Documents (*.pdf)";
					}
					
					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
				            return true;
				        } else {
				            return f.getName().toLowerCase().endsWith(".pdf");
				        }
					}
				});
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.showSaveDialog(null);
				
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				if (!path.toLowerCase().endsWith(".pdf")) {
					path = path + ".pdf";
				}
				
				PdfWriter pdfWriter = new PdfWriter(path);
				PdfDocument pdfDocument = new PdfDocument(pdfWriter);
				pdfDocument.addNewPage();
				Document document = new Document(pdfDocument);
				pdfDocument.setDefaultPageSize(PageSize.A4);
				
				PdfFont fontUtf8 = PdfFontFactory.createFont(fontString, BaseFont.IDENTITY_H, true);
				
				
				String nameCity = "Công Ty TNHH Group 14";
				Paragraph paragraphNameCity = new Paragraph(nameCity);
				paragraphNameCity.setTextAlignment(TextAlignment.CENTER);
				paragraphNameCity.setFontSize(30f);
				paragraphNameCity.setFont(fontUtf8);
				
				String title = "PHIẾU LƯƠNG THÁNG " + (mChMonth.getMonth()+1) + "/" + yChYear.getYear();
				Paragraph paragraphTitle = new Paragraph(title);
				paragraphTitle.setTextAlignment(TextAlignment.CENTER);
				paragraphTitle.setFontSize(15f);
				paragraphTitle.setMarginTop(30f);
				paragraphTitle.setFont(fontUtf8);
				
				
				float columnWit[] = {250f,300f,50f,270f,270f}; 
				Table table = new Table(columnWit);
				table.setMargins(30f, 0f, 30f, 60f);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Tên nhân viên : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtNameEmployee.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Mã nhân viên : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtIdEmployee.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Ngày sinh : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(dateBirthDayEmployee.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Giới tính : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtGenderEmployee.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Số điện thoại : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtPhonenumberEmployee.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Địa chỉ : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtAddressEmployee.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Tên ngân hàng : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtNameBank.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Số tài khoản : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtSTKBank.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Người hưởng : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtNameOwnerBank.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("CM/CV : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtSpecializePosition.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Mã tổ : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtIdTeamIdDeparment.getText().contains("PX") ? txtIdTeamIdDeparment.getText() : ""))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Mã Phòng ban : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtIdTeamIdDeparment.getText().contains("PB") ? txtIdTeamIdDeparment.getText() : ""))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Số ngày làm : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtNumberOfWorkDay.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Số ngày nghỉ : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtNumberOfDayOff.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Chỉ tiêu : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtTargets.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Lý do : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtReason.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Tiền thưởng : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtBonus.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().setBorder(Border.NO_BORDER));
				table.addCell(new Cell().add(new Paragraph("Tiền phạt : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell().add(new Paragraph(txtFine.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				//row
				table.addCell(new Cell().add(new Paragraph("Tổng lương : "))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				table.addCell(new Cell(0, 4).add(new Paragraph(txtTotalSalary.getText()))
						.setBorder(Border.NO_BORDER)
						.setHeight(25f)
						.setFont(fontUtf8)
				);
				
				float columnWithComfirm[] = {280f,280f};
				Table tblComfirm = new Table(columnWithComfirm);
				Paragraph comfirm = new Paragraph("Xác nhận của cơ quan chức năng");
				tblComfirm.addCell(new Cell().add(comfirm)
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.CENTER)
						.setFont(fontUtf8)
				);
				tblComfirm.addCell(new Cell().add(new Paragraph("Chữ ký người nhận"))
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.CENTER)
						.setFont(fontUtf8)
				);
				
				String image = "images//logoCity.jpg";
				ImageData imagedata = ImageDataFactory.create(image);
				Image logo = new Image(imagedata);
				logo.setMarginLeft(70f);
				
				Paragraph president = new Paragraph("GIÁM ĐỐC");
				president.setMarginLeft(90f);
				president.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
				president.setFontColor(ColorConstants.RED);
				president.setBold();
				president.setItalic();
				president.setFont(fontUtf8);
				
				
				Paragraph sign = new Paragraph("Nguyễn Minh Quân");
				sign.setMarginLeft(70f);
				try {
					sign.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				sign.setFontColor(ColorConstants.RED);
				sign.setBold();
				sign.setItalic();
				sign.setFont(fontUtf8);
				
				document.add(paragraphNameCity);
				document.add(paragraphTitle);
				document.add(table);
				document.add(tblComfirm);
				document.add(logo);
				document.add(president);
				document.add(sign);
				
				
				document.close();
				JOptionPane.showMessageDialog(this, "Lưu file thành công!!!");
				DisplayPDF display = new DisplayPDF(path);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}

		}
		else if(o.equals(btnExportListSalary)) {
			try {
				String path = "exportSalaryOfEmployee/listSalary" + (mChMonth.getMonth()+1) + "." + (yChYear.getYear()) + ".pdf";
				PdfWriter pdfWriter = new PdfWriter(path);
				PdfDocument pdfDocument = new PdfDocument(pdfWriter);
				pdfDocument.addNewPage();
				Document document = new Document(pdfDocument);
				pdfDocument.setDefaultPageSize(PageSize.A4);
				
				PdfFont fontUtf8 = PdfFontFactory.createFont(fontString, BaseFont.IDENTITY_H, true);
				DecimalFormat formatter = new DecimalFormat("###,###,### VND");
				
				String nameCity = "Công Ty TNHH Group 14";
				Paragraph paragraphNameCity = new Paragraph(nameCity);
				paragraphNameCity.setTextAlignment(TextAlignment.CENTER);
				paragraphNameCity.setFontSize(30f);
				paragraphNameCity.setFont(fontUtf8);
				
				String title = "DANH SÁCH LƯƠNG THÁNG " + (mChMonth.getMonth()+1) + "/" + yChYear.getYear();
				Paragraph paragraphTitle = new Paragraph(title);
				paragraphTitle.setTextAlignment(TextAlignment.CENTER);
				paragraphTitle.setFontSize(15f);
				paragraphTitle.setMarginTop(30f);
				paragraphTitle.setFont(fontUtf8);
				
				String nameListWorker = "Danh sách nhân viên sản xuất : ";
				Paragraph paragraphNameListWorker = new Paragraph(nameListWorker);
				paragraphNameListWorker.setFontSize(10f);
				paragraphNameListWorker.setFont(fontUtf8);
				paragraphNameListWorker.setMargins(30f, 0, 0f, 30f);
				
				
				float columnWitTableListWorker[] = {300f,300f,300f};
				Table tableListWorker = new Table(columnWitTableListWorker);
				tableListWorker.setMargins(30f, 30f, 30f, 30f);
				
				List<Worker> ListEmployeeProductive = Dao_Salary.getListEmployeeProductive();
				List<EmployeeOffice> ListEmployeeAdministrative = Dao_Salary.getListEmployeeAdministrative();
				
				//header
				
				tableListWorker.addCell(new Cell().add(new Paragraph("Mã nhân viên"))
						.setBackgroundColor(WebColors.getRGBColor("#008C8C"))
						.setBold()
						.setFontColor(new ColorConstants().WHITE)
						.setFont(fontUtf8)
						.setHeight(25f)
						.setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setHorizontalAlignment(HorizontalAlignment.CENTER)
				);
				tableListWorker.addCell(new Cell().add(new Paragraph("Tên nhân viên"))
						.setBackgroundColor(WebColors.getRGBColor("#008C8C"))
						.setBold()
						.setFontColor(new ColorConstants().WHITE)
						.setFont(fontUtf8)
						.setHeight(25f)
						.setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setHorizontalAlignment(HorizontalAlignment.CENTER)
				);
				tableListWorker.addCell(new Cell().add(new Paragraph("Lương"))
						.setBackgroundColor(WebColors.getRGBColor("#008C8C"))
						.setBold()
						.setFontColor(new ColorConstants().WHITE)
						.setFont(fontUtf8)
						.setHeight(25f)
						.setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setHorizontalAlignment(HorizontalAlignment.CENTER)
				);
				
				double totalSalaryOfWorker = 0;
				for(Worker worker : ListEmployeeProductive) {
					List<TimesheetFactory> listTimeKeep = Dao_Salary.getWorkDayOfEmployyProductive(worker.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
					int targets = 0;
					int numberDayOfMonth = 0;
					
					double salary = Double.parseDouble(Dao_Salary.calculateTotalSalaryOfE(worker.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear()) + "");
					totalSalaryOfWorker += salary;
					String totalSalary = formatter.format(salary);
					
					//row
					tableListWorker.addCell(new Cell().add(new Paragraph(worker.getEmployeeID()))
							.setFont(fontUtf8)
							.setHeight(25f)
							.setTextAlignment(TextAlignment.CENTER)
							.setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setHorizontalAlignment(HorizontalAlignment.CENTER)
					);
					tableListWorker.addCell(new Cell().add(new Paragraph(worker.getName()))
							.setFont(fontUtf8)
							.setHeight(25f)
							.setTextAlignment(TextAlignment.CENTER)
							.setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setHorizontalAlignment(HorizontalAlignment.CENTER)
					);
					tableListWorker.addCell(new Cell().add(new Paragraph(totalSalary))
							.setFont(fontUtf8)
							.setHeight(25f)
							.setTextAlignment(TextAlignment.CENTER)
							.setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setHorizontalAlignment(HorizontalAlignment.CENTER)
					);
				}
				String nameTotalSalaryOfWorker = "Tổng Lương : " + formatter.format(totalSalaryOfWorker) ;
				Paragraph paragraphNameTotalSalaryOfWorker = new Paragraph(nameTotalSalaryOfWorker);
				paragraphNameTotalSalaryOfWorker.setTextAlignment(TextAlignment.RIGHT);
				paragraphNameTotalSalaryOfWorker.setFontSize(15f);
				paragraphNameTotalSalaryOfWorker.setFont(fontUtf8);
				paragraphNameTotalSalaryOfWorker.setMargins(0, 30f, 30f, 0);
				
				String nameListEOffice = "Danh sách nhân viên hành chính : ";
				Paragraph paragraphNameListEOffice = new Paragraph(nameListEOffice);
				paragraphNameListEOffice.setFontSize(10f);
				paragraphNameListEOffice.setFont(fontUtf8);
				paragraphNameListEOffice.setMargins(30f, 0, 0f, 30f);
				
				float columnWitTableListEOffice[] = {300f,300f,300f};
				Table tableListEOffice = new Table(columnWitTableListEOffice);
				tableListEOffice.setMargins(30f, 30f, 30f, 30f);
				//header
				
				tableListEOffice.addCell(new Cell().add(new Paragraph("Mã nhân viên"))
						.setBackgroundColor(WebColors.getRGBColor("#008C8C"))
						.setBold()
						.setFontColor(new ColorConstants().WHITE)
						.setFont(fontUtf8)
						.setHeight(25f)
						.setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setHorizontalAlignment(HorizontalAlignment.CENTER)
				);
				tableListEOffice.addCell(new Cell().add(new Paragraph("Tên nhân viên"))
						.setBackgroundColor(WebColors.getRGBColor("#008C8C"))
						.setBold()
						.setFontColor(new ColorConstants().WHITE)
						.setFont(fontUtf8)
						.setHeight(25f)
						.setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setHorizontalAlignment(HorizontalAlignment.CENTER)
				);
				tableListEOffice.addCell(new Cell().add(new Paragraph("Lương"))
						.setBackgroundColor(WebColors.getRGBColor("#008C8C"))
						.setBold()
						.setFontColor(new ColorConstants().WHITE)
						.setFont(fontUtf8)
						.setHeight(25f)
						.setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)
						.setHorizontalAlignment(HorizontalAlignment.CENTER)
				);
				
				double totalSalaryOfEOffice = 0;
				for(EmployeeOffice eAdministrative : ListEmployeeAdministrative) {
					List<TimesheetOffice> listTimeKeep = Dao_Salary.getNumberWorkOfEmployeeAdministrative(eAdministrative.getEmployeeID(), mChMonth.getMonth()+1, yChYear.getYear());
					int numberWorkOfMonth = listTimeKeep.size();
					int numberDayOfMonth = 0;
					
					double salary = ((eAdministrative.getSalary()/26) * numberWorkOfMonth);
					totalSalaryOfEOffice += salary;
					String totalSalary = formatter.format(salary);
					//row
					tableListEOffice.addCell(new Cell().add(new Paragraph(eAdministrative.getEmployeeID()))
							.setFont(fontUtf8)
							.setHeight(25f)
							.setTextAlignment(TextAlignment.CENTER)
							.setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setHorizontalAlignment(HorizontalAlignment.CENTER)
					);
					tableListEOffice.addCell(new Cell().add(new Paragraph(eAdministrative.getName()))
							.setFont(fontUtf8)
							.setHeight(25f)
							.setTextAlignment(TextAlignment.CENTER)
							.setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setHorizontalAlignment(HorizontalAlignment.CENTER)
					);
					tableListEOffice.addCell(new Cell().add(new Paragraph(totalSalary))
							.setFont(fontUtf8)
							.setHeight(25f)
							.setTextAlignment(TextAlignment.CENTER)
							.setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setHorizontalAlignment(HorizontalAlignment.CENTER)
					);
				}
				String nameTotalSalaryOfEOffice = "Tổng Lương : " + formatter.format(totalSalaryOfEOffice) ;
				Paragraph paragraphNameTotalSalaryOfEOffice = new Paragraph(nameTotalSalaryOfEOffice);
				paragraphNameTotalSalaryOfEOffice.setTextAlignment(TextAlignment.RIGHT);
				paragraphNameTotalSalaryOfEOffice.setFontSize(15f);
				paragraphNameTotalSalaryOfEOffice.setFont(fontUtf8);
				paragraphNameTotalSalaryOfEOffice.setMargins(0, 30f, 30f, 0);
				
				String nameTotalSalaryOfE = "Tổng Lương công ty phải trả : " + formatter.format( totalSalaryOfWorker + totalSalaryOfEOffice) ;
				Paragraph paragraphNameTotalSalaryOfE = new Paragraph(nameTotalSalaryOfE);
				paragraphNameTotalSalaryOfE.setTextAlignment(TextAlignment.RIGHT);
				paragraphNameTotalSalaryOfE.setFontSize(15f);
				paragraphNameTotalSalaryOfE.setFont(fontUtf8);
				paragraphNameTotalSalaryOfE.setMargins(0, 30f, 50f, 0);
				
				float columnWithComfirm[] = {280f,280f};
				Table tblComfirm = new Table(columnWithComfirm);
				Paragraph comfirm = new Paragraph("Xác nhận của cơ quan chức năng");
				tblComfirm.addCell(new Cell().add(comfirm)
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.CENTER)
						.setFont(fontUtf8)
				);
				tblComfirm.addCell(new Cell().add(new Paragraph("Chữ ký người nhận"))
						.setBorder(Border.NO_BORDER)
						.setTextAlignment(TextAlignment.CENTER)
						.setFont(fontUtf8)
				);
				
				String image = "images//logoCity.jpg";
				ImageData imagedata = ImageDataFactory.create(image);
				Image logo = new Image(imagedata);
				logo.setMarginLeft(70f);
				
				Paragraph president = new Paragraph("GIÁM ĐỐC");
				president.setMarginLeft(90f);
				president.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
				president.setFontColor(ColorConstants.RED);
				president.setBold();
				president.setItalic();
				president.setFont(fontUtf8);
				
				
				Paragraph sign = new Paragraph("Nguyễn Minh Quân");
				sign.setMarginLeft(70f);
				try {
					sign.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				sign.setFontColor(ColorConstants.RED);
				sign.setBold();
				sign.setItalic();
				sign.setFont(fontUtf8);
				
				document.add(paragraphNameCity);
				document.add(paragraphTitle);
				document.add(paragraphNameListWorker);
				document.add(tableListWorker);
				document.add(paragraphNameTotalSalaryOfWorker);
				document.add(paragraphNameListEOffice);
				document.add(tableListEOffice);
				document.add(paragraphNameTotalSalaryOfEOffice);
				document.add(paragraphNameTotalSalaryOfE);
				document.add(tblComfirm);
				document.add(logo);
				document.add(president);
				document.add(sign);
				
				document.close();
				JOptionPane.showMessageDialog(this, "Lưu file thành công!!!");
				DisplayPDF display = new DisplayPDF(path);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}
}
