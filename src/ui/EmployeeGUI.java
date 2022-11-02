package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import entity.Employee;
import entity.EmployeeOffice;
import entity.Worker;
import model.DepartmentDAO;
import model.EmployeeOfficeDAO;
import model.FactoryDAO;
import model.TeamDAO;
import model.WorkerDAO;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JScrollPane;
import custom_field.JTextFieldHint;
import java.awt.Dimension;
import java.text.Format;
import java.text.NumberFormat;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;

public class EmployeeGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String[] gender = new String[] { "Nam", "Nữ" };
	private String[] bankName = new String[] { "BIDV" };
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JPanel contentPane;
	private JTextField txtAccountNumber;
	private JTextField txtBeneficiany;
	private JPanel panel;
	private String[] headerTableEmployeeOffice = new String[] { "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ",
			"SDT" };
	private String[] headerTableWorker = new String[] { "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ", "SDT" };
	private EmployeeOfficeDAO employeeOfficeDAO;
	private WorkerDAO workerDAO;
	private DepartmentDAO departmentDAO;
	private FactoryDAO factoryDAO;
	private TeamDAO teamDAO;

	private DefaultTableModel dtmEmployeeOffice;
	private DefaultTableModel dtmWorker;
	private DefaultComboBoxModel<String> dcmNameDepartment;
	private DefaultComboBoxModel<String> dcmNameFactory;
	private DefaultComboBoxModel<String> dcmNameTeam;
	private DefaultComboBoxModel<String> dcmPositionOfDept;
	private DefaultComboBoxModel<String> dcmPositionOfFactory;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnDeleteEmpOffice;
	private JButton btnReset;
	private JButton btnSearchWorker;
	private JButton btnSearchEmpOffice;
	private JDateChooser txtDob;
	private JComboBox<String> cboGender;
	private JComboBox<String> cboTeam;
	private JComboBox<String> cboBankName;
	private JComboBox<String> cboDept_Factory;
	private JComboBox<String> cboTypeEmployee;
	private JComboBox<String> cboPosition;
	private JTable tblEmpOffice;
	private JTable tblWorker;
	private JLabel lblDept_Factory;
	private JButton btnDeleteWorker;
	private JFormattedTextField txtSalary;
	private JLabel lblSalary;
	private JTextField txtSpeciality;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeGUI frame = new EmployeeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EmployeeGUI() {}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Component getView() {
		employeeOfficeDAO = new EmployeeOfficeDAO();
		workerDAO = new WorkerDAO();
		departmentDAO = new DepartmentDAO();
		factoryDAO = new FactoryDAO();
		teamDAO = new TeamDAO();

		dcmNameDepartment = new DefaultComboBoxModel<String>(
				departmentDAO.getAllNameDepartment().toArray(String[]::new));
		dcmNameFactory = new DefaultComboBoxModel<String>(factoryDAO.getAllNameFactory().toArray(String[]::new));
		dcmPositionOfDept = new DefaultComboBoxModel(new String[] { "Nhân Viên", "Trưởng Phòng" });
		dcmPositionOfFactory = new DefaultComboBoxModel(new String[] { "Công nhân", "Tổ trưởng", "Quản đốc" });

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Nhập thông tin nhân sự"));
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][grow][][]"));

		JLabel lblName = new JLabel("Họ tên");
		panel_3.add(lblName, "cell 0 0,alignx left");

		txtName = new JTextField();
		panel_3.add(txtName, "cell 1 0,growx");
		txtName.setColumns(10);

		JLabel lblDob = new JLabel("Ngày sinh");
		panel_3.add(lblDob, "cell 2 0,alignx left");

		txtDob = new JDateChooser();
		txtDob.setDateFormatString("yyyy-MM-dd");
		panel_3.add(txtDob, "cell 3 0,growx");

		JLabel lblGender = new JLabel("Giới tính");
		panel_3.add(lblGender, "cell 4 0,alignx left");

		cboGender = new JComboBox(gender);
		panel_3.add(cboGender, "cell 5 0,growx");

		JLabel lblPhone = new JLabel("SDT");
		panel_3.add(lblPhone, "cell 0 1");

		txtPhone = new JTextField();
		panel_3.add(txtPhone, "cell 1 1,growx");
		txtPhone.setColumns(10);

		JLabel lblAddress = new JLabel("Địa chỉ");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblAddress, "cell 2 1,alignx left");

		txtAddress = new JTextField();
		panel_3.add(txtAddress, "cell 3 1,growx");
		txtAddress.setColumns(10);

		JLabel lblSpeciality = new JLabel("Chuyên môn");
		panel_3.add(lblSpeciality, "cell 4 1,alignx left");

		txtSpeciality = new JTextField();
		panel_3.add(txtSpeciality, "cell 5 1,growx");
		txtSpeciality.setColumns(10);

		JLabel lblBankName = new JLabel("Ngân hàng");
		panel_3.add(lblBankName, "cell 0 2,alignx left");

		cboBankName = new JComboBox(bankName);
		panel_3.add(cboBankName, "cell 1 2,growx");

		JLabel lblAccountNumber = new JLabel("STK");
		panel_3.add(lblAccountNumber, "cell 2 2,alignx left");

		txtAccountNumber = new JTextField();
		txtAccountNumber.setColumns(10);
		panel_3.add(txtAccountNumber, "cell 3 2,growx");

		JLabel lblBeneficiany = new JLabel("Người thụ hưởng");
		panel_3.add(lblBeneficiany, "cell 4 2,alignx trailing");

		txtBeneficiany = new JTextField();
		txtBeneficiany.setColumns(10);
		panel_3.add(txtBeneficiany, "cell 5 2,growx");

		JLabel lblTypeEmployee = new JLabel("Loại nhân sự");
		panel_3.add(lblTypeEmployee, "cell 0 4");

		cboTypeEmployee = new JComboBox(new String[] { "Nhân viên hành chính", "Nhân viên sản xuất" });
		cboTypeEmployee.addActionListener(this);
		panel_3.add(cboTypeEmployee, "cell 1 4,growx");

		lblDept_Factory = new JLabel("Phòng ban");
		panel_3.add(lblDept_Factory, "cell 2 4,alignx trailing");

		cboDept_Factory = new JComboBox();
		cboDept_Factory.addActionListener(this);
		cboDept_Factory.setModel(dcmNameDepartment);
		panel_3.add(cboDept_Factory, "cell 3 4,growx");

		JLabel lblTeam = new JLabel("Tổ");
		panel_3.add(lblTeam, "cell 4 4,alignx left");

		cboTeam = new JComboBox();
		cboTeam.setEnabled(false);
		panel_3.add(cboTeam, "cell 5 4,growx");

		JLabel lblPosition = new JLabel("Chức vụ");
		panel_3.add(lblPosition, "cell 0 5,alignx left");

		cboPosition = new JComboBox();
		cboPosition.setModel(dcmPositionOfDept);
		panel_3.add(cboPosition, "cell 1 5,growx");

		lblSalary = new JLabel("Lương");
		panel_3.add(lblSalary, "cell 2 5,alignx left");

		Format amountFormat = NumberFormat.getIntegerInstance(Locale.ITALIAN);

		txtSalary = new JFormattedTextField(amountFormat);
		panel_3.add(txtSalary, "cell 3 5,growx");

		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel pnButtonOperations = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnButtonOperations.getLayout();
		flowLayout.setHgap(60);
		panel_4.add(pnButtonOperations, BorderLayout.NORTH);

		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(this);
		btnAdd.setToolTipText("Thêm nhân viên");
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);
		pnButtonOperations.add(btnAdd);

		btnUpdate = new JButton("Sửa");
		btnUpdate.addActionListener(this);
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);
		pnButtonOperations.add(btnUpdate);

		btnDeleteEmpOffice = new JButton("Xóa nhân viên hành chính");
		btnDeleteEmpOffice.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDeleteEmpOffice.addActionListener(this);
		btnDeleteEmpOffice.setFocusable(false);
		pnButtonOperations.add(btnDeleteEmpOffice);

		btnDeleteWorker = new JButton("Xóa nhân viên sản xuất");
		btnDeleteWorker.setFocusable(false);
		btnDeleteWorker.addActionListener(this);
		btnDeleteWorker.setIcon(new ImageIcon("images\\operations\\delete.png"));
		pnButtonOperations.add(btnDeleteWorker);

		btnReset = new JButton("Làm mới");
		btnReset.addActionListener(this);
		btnReset.setIcon(new ImageIcon("images\\operations\\refresh.png"));
		btnReset.setFocusable(false);
		pnButtonOperations.add(btnReset);

		JPanel pnEmployee = new JPanel();
		panel_4.add(pnEmployee, BorderLayout.CENTER);
		pnEmployee.setLayout(new GridLayout(2, 1, 10, 10));

		JPanel pnEmpOffice = new JPanel();
		pnEmpOffice.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Danh sách nhân viên hành chính"));
		pnEmployee.add(pnEmpOffice);
		pnEmpOffice.setLayout(new BorderLayout(0, 0));

		JPanel pnWorker = new JPanel();
		pnWorker.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Danh sách nhân viên sản xuất"));
		pnEmployee.add(pnWorker);
		pnWorker.setLayout(new BorderLayout(0, 0));

		tblEmpOffice = new JTable(dtmEmployeeOffice = new DefaultTableModel(headerTableEmployeeOffice, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmpOffice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblEmpOffice.getSelectedRow();
				if (rowSelected >= 0) {
					String employeeID = tblEmpOffice.getValueAt(rowSelected, 0).toString();
					Employee emp = employeeOfficeDAO.getEmployeeOffice(employeeID);
					cboTypeEmployee.setSelectedIndex(0);
					cboDept_Factory.setModel(dcmNameDepartment);
					lblDept_Factory.setText("Phòng ban");
					cboTeam.setEnabled(false);
					txtSalary.setEnabled(true);

					txtName.setText(emp.getName());
					txtDob.setDate(emp.getBirthday());
					if (emp.isGender()) {
						cboGender.setSelectedIndex(0);
					} else {
						cboGender.setSelectedIndex(1);
					}

					txtPhone.setText(emp.getPhone());
					txtAddress.setText(emp.getAddress());

					txtAccountNumber.setText(emp.getAccountNumber());
					txtBeneficiany.setText(emp.getBeneficiany());
					String nameDept = departmentDAO.getNameDepartmentByID(((EmployeeOffice) emp).getDepartmentID());
					cboDept_Factory.setSelectedItem(((EmployeeOffice) emp).getDepartmentID() + " - " + nameDept);
					cboPosition.setSelectedItem(((EmployeeOffice) emp).getPosition());
					txtSalary.setValue(((EmployeeOffice) emp).getSalary());
				}
			}
		});

		tblWorker = new JTable(dtmWorker = new DefaultTableModel(headerTableWorker, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblWorker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblWorker.getSelectedRow();
				if (rowSelected >= 0) {
					String employeeID = tblWorker.getValueAt(rowSelected, 0).toString();
					Employee emp = workerDAO.getWorker(employeeID);
					cboTypeEmployee.setSelectedIndex(1);
					cboDept_Factory.setModel(dcmNameFactory);
					lblDept_Factory.setText("Phân xưởng");
					cboTeam.setEnabled(true);
					String factory = ((String) cboDept_Factory.getSelectedItem()).substring(0, 4);
					dcmNameTeam = new DefaultComboBoxModel<String>(
							teamDAO.getAllNameTeam(factory).toArray(String[]::new));
					cboTeam.setModel(dcmNameTeam);
					txtSalary.setEnabled(false);

					txtName.setText(emp.getName());
					txtDob.setDate(emp.getBirthday());
					if (emp.isGender()) {
						cboGender.setSelectedIndex(0);
					} else {
						cboGender.setSelectedIndex(1);
					}

					txtPhone.setText(emp.getPhone());
					txtAddress.setText(emp.getAddress());
					txtAccountNumber.setText(emp.getAccountNumber());
					txtBeneficiany.setText(emp.getBeneficiany());
					txtSpeciality.setText(((Worker) emp).getSpeciality());
					String nameFactory = factoryDAO.getNameFactoryByTeamID(((Worker) emp).getTeamID());
					cboDept_Factory.setSelectedItem(nameFactory);
					String nameTeam = teamDAO.getNameTeamByID(((Worker) emp).getTeamID());
					cboTeam.setSelectedItem(nameTeam);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tblEmpOffice);
		pnEmpOffice.add(scrollPane);

		JPanel pnSearchEmpOffice = new JPanel();
		FlowLayout fl_pnSearchEmpOffice = (FlowLayout) pnSearchEmpOffice.getLayout();
		fl_pnSearchEmpOffice.setAlignment(FlowLayout.LEFT);
		pnEmpOffice.add(pnSearchEmpOffice, BorderLayout.NORTH);

		JTextFieldHint txtSearchEmpOffice = new JTextFieldHint("Nhập tên hoặc mã nhân viên");
		txtSearchEmpOffice.setPreferredSize(new Dimension(200, 25));
		pnSearchEmpOffice.add(txtSearchEmpOffice);

		btnSearchEmpOffice = new JButton();
		btnSearchEmpOffice.setFocusable(false);
		btnSearchEmpOffice.setIcon(new ImageIcon("images\\operations\\search.png"));
		pnSearchEmpOffice.add(btnSearchEmpOffice);

		JScrollPane scrollPane_1 = new JScrollPane(tblWorker);
		pnWorker.add(scrollPane_1);

		JPanel pnSearchWorker = new JPanel();
		FlowLayout fl_pnSearchWorker = (FlowLayout) pnSearchWorker.getLayout();
		fl_pnSearchWorker.setAlignment(FlowLayout.LEFT);
		pnWorker.add(pnSearchWorker, BorderLayout.NORTH);

		JTextFieldHint txtSearchWorker = new JTextFieldHint("Nhập tên hoặc mã nhân viên");
		txtSearchWorker.setPreferredSize(new Dimension(200, 25));
		pnSearchWorker.add(txtSearchWorker);

		btnSearchWorker = new JButton();
		btnSearchWorker.setFocusable(false);
		btnSearchWorker.setIcon(new ImageIcon("images\\operations\\search.png"));
		pnSearchWorker.add(btnSearchWorker);

		setContentPane(contentPane);

		loadDataToTable();
		
		return contentPane;
	}

	public void loadDataToTable() {
		dtmEmployeeOffice = new DefaultTableModel(headerTableEmployeeOffice, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtmWorker = new DefaultTableModel(headerTableWorker, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		List<Employee> lstEmpOffice = employeeOfficeDAO.getAllEmployeeOffice();
		for (Employee emp : lstEmpOffice) {
			dtmEmployeeOffice.addRow(emp.toString().split(";"));
		}

		List<Employee> lstWorker = workerDAO.getAllWorker();
		for (Employee emp : lstWorker) {
			dtmWorker.addRow(emp.toString().split(";"));
		}
		tblWorker.setModel(dtmWorker);
		tblEmpOffice.setModel(dtmEmployeeOffice);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			String name = txtName.getText();
			boolean gender;
			if (cboGender.getSelectedItem() == "Nam") {
				gender = true;
			} else {
				gender = false;
			}
			Date birthday = txtDob.getDate();
			String address = txtAddress.getText();
			String phone = txtPhone.getText();
			String bankName = (String) cboBankName.getSelectedItem();
			String accountNumber = txtAccountNumber.getText();
			String beneficiany = txtBeneficiany.getText();
			if (cboTypeEmployee.getSelectedIndex() == 0) {
				double salary = Double.parseDouble(txtSalary.getText().replace(".", ""));
				String position = (String) cboPosition.getSelectedItem();
				String departmentID = ((String) cboDept_Factory.getSelectedItem()).substring(0, 4);
				Employee employee = new EmployeeOffice(name, gender, birthday, address, phone, bankName, accountNumber,
						beneficiany, salary, position, departmentID);
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm nhân viên không?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (employeeOfficeDAO.addEmployeeOffice(employee)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công.", "Thông báo xóa",
								JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					}
				}
			} else {
				String speciality = txtSpeciality.getText();
				String teamID = cboTeam.getSelectedItem().toString().substring(0, 6);
				Employee employee = new Worker(name, gender, birthday, address, phone, bankName, accountNumber,
						beneficiany, speciality, teamID);
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm nhân viên không?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (workerDAO.addWorker(employee)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công.", "Thông báo xóa",
								JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					}
				}
			}
		}
		if (e.getSource() == btnUpdate) {
			int rowSelectedEmpOffice = tblEmpOffice.getSelectedRow();
			int rowSelectedWorker = tblWorker.getSelectedRow();
			String name = txtName.getText();
			boolean gender;
			if (cboGender.getSelectedItem() == "Nam") {
				gender = true;
			} else {
				gender = false;
			}
			Date birthday = txtDob.getDate();
			String address = txtAddress.getText();
			String phone = txtPhone.getText();
			String bankName = (String) cboBankName.getSelectedItem();
			String accountNumber = txtAccountNumber.getText();
			String beneficiany = txtBeneficiany.getText();
			if (rowSelectedEmpOffice >= 0) {
				if (cboTypeEmployee.getSelectedIndex() == 0) {
					double salary = Double.parseDouble(txtSalary.getText().replace(".", ""));
					String position = (String) cboPosition.getSelectedItem();
					String departmentID = ((String) cboDept_Factory.getSelectedItem()).substring(0, 4);
					String empID = tblEmpOffice.getValueAt(rowSelectedEmpOffice, 0).toString();
					Employee employee = new EmployeeOffice(empID, name, gender, birthday, address, phone, bankName,
							accountNumber, beneficiany, salary, position, departmentID);
					if (JOptionPane.showConfirmDialog(this,
							"Bạn có chắn chắn muốn cập nhật lại thông tin của nhân viên này không?", "Thông báo",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						if (employeeOfficeDAO.updateEmployeeOffice(employee)) {
							loadDataToTable();
							JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công.", "Thông báo",
									JOptionPane.NO_OPTION, null);
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công.", "Thông báo",
									JOptionPane.NO_OPTION, null);
						}
					}
				}
			}
			if (rowSelectedWorker >= 0) {
				if (cboTypeEmployee.getSelectedIndex() == 1) {
					String speciality = txtSpeciality.getText();
					String teamID = cboTeam.getSelectedItem().toString().substring(0, 6);
					String empID = tblWorker.getValueAt(rowSelectedEmpOffice, 0).toString();
					Employee employee = new Worker(empID, name, gender, birthday, address, phone, bankName, accountNumber,
							beneficiany, speciality, teamID);
					if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm nhân viên không?", "Thông báo",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						if (workerDAO.updateWorker(employee)) {
							loadDataToTable();
							JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công.", "Thông báo",
									JOptionPane.NO_OPTION, null);
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên không thành công.",
									"Thông báo", JOptionPane.NO_OPTION, null);
						}
					}
				}
			}

		}
		if (e.getSource() == btnDeleteEmpOffice)

		{
			int rowSelected = tblEmpOffice.getSelectedRow();
			if (rowSelected >= 0) {
				String employeeID = tblEmpOffice.getValueAt(rowSelected, 0).toString();
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa nhân viên " + employeeID + " không?",
						"Thông báo xóa", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (employeeOfficeDAO.deleteEmployee(employeeID)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(this, "Xóa nhân viên " + employeeID + " thành công.",
								"Thông báo xóa", JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Xóa nhân viên " + employeeID + " không thành công.",
								"Thông báo xóa", JOptionPane.NO_OPTION, null);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên muốn xóa trước", "Thông báo xóa",
						JOptionPane.NO_OPTION, null);
			}
		}
		if (e.getSource() == btnDeleteWorker) {
			int rowSelected = tblWorker.getSelectedRow();
			if (rowSelected >= 0) {
				String employeeID = tblWorker.getValueAt(rowSelected, 0).toString();
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa nhân viên " + employeeID + " không?",
						"Thông báo xóa", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (workerDAO.deleteEmployee(employeeID)) {
						loadDataToTable();
						JOptionPane.showMessageDialog(this, "Xóa nhân viên " + employeeID + " thành công.",
								"Thông báo xóa", JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Xóa nhân viên " + employeeID + " không thành công.",
								"Thông báo xóa", JOptionPane.NO_OPTION, null);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên muốn xóa trước", "Thông báo xóa",
						JOptionPane.NO_OPTION, null);
			}
		}
		if (e.getSource() == btnReset) {
			txtName.setText("");
			txtDob.setDate(null);
			cboGender.setSelectedIndex(0);

			txtPhone.setText("");
			txtAddress.setText("");
			txtSpeciality.setText("");

			cboBankName.setSelectedIndex(0);
			txtAccountNumber.setText("");
			txtBeneficiany.setText("");

			cboTypeEmployee.setSelectedIndex(0);
			cboDept_Factory.setSelectedIndex(0);
			cboTeam.setSelectedIndex(0);

			cboPosition.setSelectedIndex(0);
		}
		if (e.getSource() == btnSearchEmpOffice) {

		}
		if (e.getSource() == cboTypeEmployee) {
			if (cboTypeEmployee.getSelectedIndex() == 0) {
				cboDept_Factory.setModel(dcmNameDepartment);
				lblDept_Factory.setText("Phòng ban");
				cboPosition.setModel(dcmPositionOfDept);
				cboTeam.setEnabled(false);
				txtSalary.setEnabled(true);
			} else {
				cboDept_Factory.setModel(dcmNameFactory);
				lblDept_Factory.setText("Phân xưởng");
				cboTeam.setEnabled(true);
				String factory = ((String) cboDept_Factory.getSelectedItem()).substring(0, 4);
				dcmNameTeam = new DefaultComboBoxModel<String>(teamDAO.getAllNameTeam(factory).toArray(String[]::new));
				cboPosition.setModel(dcmPositionOfFactory);
				cboTeam.setModel(dcmNameTeam);
				txtSalary.setEnabled(false);
			}
		}
		if (e.getSource() == cboDept_Factory) {
			String factory = ((String) cboDept_Factory.getSelectedItem()).substring(0, 4);
			dcmNameTeam = new DefaultComboBoxModel<String>(teamDAO.getAllNameTeam(factory).toArray(String[]::new));
			cboTeam.setModel(dcmNameTeam);
		}
	}
}
