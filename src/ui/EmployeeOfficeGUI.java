package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import custom_field.JTextFieldHint;
import entity.Employee;
import entity.EmployeeOffice;
import model.DepartmentDAO;
import model.EmployeeOfficeDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class EmployeeOfficeGUI extends JFrame implements ActionListener {
	public EmployeeOfficeGUI() {
		getUI();
	}
	private static final long serialVersionUID = 1L;
	private static final Color COLOR = new Color(14, 85, 78);
	private String[] gender = new String[] { "Nam", "Nữ" };
	private String[] bankName = new String[] { "BIDV" };
	private String[] headerTable = new String[] { "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ", "SDT" };
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtAccountNumber;
	private JTextField txtBeneficiany;
	private JTextFieldHint txtSearch;
	private JFormattedTextField txtSalary;
	private EmployeeOfficeDAO employeeOfficeDAO;
	private DepartmentDAO departmentDAO;
	private DefaultTableModel dtmEmp;
	private DefaultComboBoxModel<String> dcmDepartment;
	private DefaultComboBoxModel<String> dcmPosition;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnReset;
	private JButton btnDelete;
	private JButton btnSearch;
	private JDateChooser txtDob;
	private JComboBox<String> cboGender;
	private JComboBox<String> cboBankName;
	private JComboBox<String> cboDept;
	private JComboBox<String> cboPosition;
	private JTable tblEmp;
	private Date maxSelectedDate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeOfficeGUI frame = new EmployeeOfficeGUI();
					frame.getUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public Component getUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));

		employeeOfficeDAO = new EmployeeOfficeDAO();
		departmentDAO = new DepartmentDAO();

		dcmDepartment = new DefaultComboBoxModel<String>(departmentDAO.getAllNameDepartment().toArray(String[]::new));
		dcmPosition = new DefaultComboBoxModel<String>(new String[] { "Nhân Viên", "Trưởng Phòng" });

		JPanel pnInputInfo = new JPanel();
		pnInputInfo.setBackground(Color.WHITE);
		pnInputInfo.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Nhập thông tin nhân sự"));
		pnInputInfo.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][grow][]"));
		contentPane.add(pnInputInfo, BorderLayout.NORTH);

		JLabel lblName = new JLabel("Họ tên");
		pnInputInfo.add(lblName, "cell 0 0,alignx left");

		txtName = new JTextField();
		pnInputInfo.add(txtName, "cell 1 0,growx");
		txtName.setColumns(10);

		JLabel lblDob = new JLabel("Ngày sinh");
		pnInputInfo.add(lblDob, "cell 2 0,alignx left");
		
		maxSelectedDate = new Date();
		maxSelectedDate.setYear(maxSelectedDate.getYear() - 18);

		txtDob = new JDateChooser();
		txtDob.setMaxSelectableDate(maxSelectedDate);
		txtDob.setDate(maxSelectedDate);
		txtDob.setDateFormatString("yyyy-MM-dd");
		pnInputInfo.add(txtDob, "cell 3 0,growx");

		JLabel lblGender = new JLabel("Giới tính");
		pnInputInfo.add(lblGender, "cell 4 0,alignx left");

		cboGender = new JComboBox(gender);
		cboGender.setBackground(Color.WHITE);
		pnInputInfo.add(cboGender, "cell 5 0,growx");

		JLabel lblAddress = new JLabel("Địa chỉ");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		pnInputInfo.add(lblAddress, "cell 0 1,alignx left");

		txtAddress = new JTextField();
		pnInputInfo.add(txtAddress, "cell 1 1,growx");
		txtAddress.setColumns(10);

		JLabel lblPhone = new JLabel("SDT");
		pnInputInfo.add(lblPhone, "cell 2 1");

		txtPhone = new JTextField();
		pnInputInfo.add(txtPhone, "cell 3 1,growx");
		txtPhone.setColumns(10);

		JLabel lblSalary = new JLabel("Lương");
		pnInputInfo.add(lblSalary, "cell 4 1,alignx left");

		Format amountFormat = NumberFormat.getIntegerInstance(Locale.ITALIAN);

		txtSalary = new JFormattedTextField(amountFormat);
		pnInputInfo.add(txtSalary, "cell 5 1,growx");

		JLabel lblBankName = new JLabel("Ngân hàng");
		pnInputInfo.add(lblBankName, "cell 0 2,alignx left");

		cboBankName = new JComboBox(bankName);
		cboBankName.setBackground(Color.WHITE);
		pnInputInfo.add(cboBankName, "cell 1 2,growx");

		JLabel lblAccountNumber = new JLabel("STK");
		pnInputInfo.add(lblAccountNumber, "cell 2 2,alignx left");

		txtAccountNumber = new JTextField();
		txtAccountNumber.setColumns(10);
		pnInputInfo.add(txtAccountNumber, "cell 3 2,growx");

		JLabel lblBeneficiany = new JLabel("Người thụ hưởng");
		pnInputInfo.add(lblBeneficiany, "cell 4 2,alignx trailing");

		txtBeneficiany = new JTextField();
		txtBeneficiany.setColumns(10);
		pnInputInfo.add(txtBeneficiany, "cell 5 2,growx");

		JLabel lblDept = new JLabel("Phòng ban");
		pnInputInfo.add(lblDept, "cell 0 4,alignx trailing");

		cboDept = new JComboBox<>();
		cboDept.setBackground(Color.WHITE);
		cboDept.addActionListener(this);
		cboDept.setModel(dcmDepartment);
		pnInputInfo.add(cboDept, "cell 1 4,growx");

		JLabel lblPosition = new JLabel("Chức vụ");
		pnInputInfo.add(lblPosition, "cell 2 4,alignx left");

		cboPosition = new JComboBox<>();
		cboPosition.setBackground(Color.WHITE);
		cboPosition.setModel(dcmPosition);
		pnInputInfo.add(cboPosition, "cell 3 4,growx");

		JPanel pnOutputInfo = new JPanel();
		contentPane.add(pnOutputInfo, BorderLayout.CENTER);
		pnOutputInfo.setLayout(new BorderLayout(0, 0));

		JPanel pnOperations = new JPanel();
		pnOperations.setBackground(Color.WHITE);
		pnOutputInfo.add(pnOperations, BorderLayout.NORTH);

		btnAdd = new JButton("Thêm");
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdd.setBackground(new Color(173, 217, 245));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAdd.setBackground(Color.WHITE);
			}
		});
		btnAdd.setBorder(new LineBorder(new Color(16, 84, 129), 3, false));
		btnAdd.setForeground(new Color(16, 84, 129));
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.addActionListener(this);
		btnAdd.setToolTipText("Thêm nhân viên");
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);

		btnUpdate = new JButton("Cập nhật");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setBackground(new Color(173, 217, 245));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setBackground(Color.WHITE);
			}
		});
		btnUpdate.setBorder(new LineBorder(new Color(16, 84, 129), 3, false));
		btnUpdate.setForeground(new Color(16, 84, 129));
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.addActionListener(this);
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);

		btnDelete = new JButton("Xóa");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setBackground(new Color(173, 217, 245));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(Color.WHITE);
			}
		});
		btnDelete.setBorder(new LineBorder(new Color(16, 84, 129), 3, false));
		btnDelete.setForeground(new Color(16, 84, 129));
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(this);
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));

		btnReset = new JButton("Làm mới");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReset.setBackground(new Color(173, 217, 245));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnReset.setBackground(Color.WHITE);
			}
		});
		btnReset.setBorder(new LineBorder(new Color(16, 84, 129), 3, false));
		btnReset.setForeground(new Color(16, 84, 129));
		btnReset.setBackground(new Color(255, 255, 255));
		btnReset.addActionListener(this);
		btnReset.setIcon(new ImageIcon("images\\operations\\refresh.png"));
		btnReset.setFocusable(false);
		GroupLayout gl_pnOperations = new GroupLayout(pnOperations);
		gl_pnOperations.setHorizontalGroup(
			gl_pnOperations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnOperations.createSequentialGroup()
					.addGap(293)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(356, Short.MAX_VALUE))
		);
		gl_pnOperations.setVerticalGroup(
			gl_pnOperations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnOperations.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnOperations.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_pnOperations.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnUpdate, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnOperations.linkSize(SwingConstants.VERTICAL, new Component[] {btnAdd, btnUpdate, btnDelete, btnReset});
		gl_pnOperations.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnAdd, btnUpdate, btnDelete, btnReset});
		pnOperations.setLayout(gl_pnOperations);

		JPanel pnTable = new JPanel();
		pnTable.setBackground(Color.WHITE);
		pnTable.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Danh sách nhân viên hành chính"));
		pnOutputInfo.add(pnTable);
		pnTable.setLayout(new BorderLayout(0, 0));

		tblEmp = new JTable(dtmEmp = new DefaultTableModel(headerTable, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblEmp.getSelectedRow();
				if (rowSelected >= 0) {
					String employeeID = tblEmp.getValueAt(rowSelected, 0).toString();
					Employee emp = employeeOfficeDAO.getEmployeeOffice(employeeID);
					txtName.setText(emp.getName());
					txtDob.setDate(emp.getBirthday());
					txtPhone.setText(emp.getPhone());
					txtAddress.setText(emp.getAddress());
					txtAccountNumber.setText(emp.getAccountNumber());
					txtBeneficiany.setText(emp.getBeneficiany());
					txtSalary.setValue(((EmployeeOffice) emp).getSalary());
					if (emp.isGender()) {
						cboGender.setSelectedIndex(0);
					} else {
						cboGender.setSelectedIndex(1);
					}
					String nameDept = departmentDAO.getNameDepartmentByID(((EmployeeOffice) emp).getDepartmentID());
					cboDept.setSelectedItem(((EmployeeOffice) emp).getDepartmentID() + " - " + nameDept);
					cboPosition.setSelectedItem(((EmployeeOffice) emp).getPosition());
				}
			}
		});
		tblEmp.getTableHeader().setOpaque(false);
		tblEmp.getTableHeader().setBackground(COLOR);
		tblEmp.getTableHeader().setForeground(Color.WHITE);

		JScrollPane scrollTable = new JScrollPane(tblEmp);
		pnTable.add(scrollTable);

		JPanel pnSearch = new JPanel();
		pnSearch.setBackground(Color.WHITE);
		FlowLayout fl_pnSearch = (FlowLayout) pnSearch.getLayout();
		fl_pnSearch.setAlignment(FlowLayout.LEFT);
		pnTable.add(pnSearch, BorderLayout.NORTH);

		txtSearch = new JTextFieldHint("Nhập tên hoặc mã nhân viên");
		txtSearch.setPreferredSize(new Dimension(200, 25));
		pnSearch.add(txtSearch);

		btnSearch = new JButton();
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("images\\operations\\search.png"));
		btnSearch.addActionListener(this);
		pnSearch.add(btnSearch);

		setContentPane(contentPane);

		loadDataToTable(employeeOfficeDAO.getAllEmployeeOffice());
		
		return contentPane;
	}

	public void loadDataToTable(List<Employee> listEmp) {
		dtmEmp = new DefaultTableModel(headerTable, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (Employee emp : listEmp) {
			dtmEmp.addRow(emp.toString().split(";"));
		}
		tblEmp.setModel(dtmEmp);
	}

	public boolean checkInput(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}

	public String validInput(String name, Date birthday, String address, String phone, String accountNumber,
			String beneficiany, Double salary) {
		if (name.isBlank() | birthday == null | address.isEmpty() | phone.isEmpty() | accountNumber.isEmpty()
				| beneficiany.isEmpty() | salary == 0) {
			return "Hãy nhập đầy đủ thông tin trước";
		} else {
			if (!name.matches(
					"^[A-Z][a-zẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ]+(\s[A-Z][a-zẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ]+)*")) {
				return "Tên gồm một hoặc nhiều từ được ngăn cách nhau bởi khoảng trắng. Chữ cái đầu mỗi từ được viết hoa";
			} else if (!address.matches("^[A-Za-z0-9][A-Za-z0-9/,\s]")) {
				return "Địa chỉ bắt đầu bằng chữ cái, chữ số có thể chứa \"/\" hoặc dấu \",\"";
			} else if (phone.matches("^[0-9][0-9]{9}$")) {
				return "Số điện thoại gồm 10 số và bắt đầu bằng số 0";
			} else if (accountNumber.matches("^[A-Z][A-Z\s]$")) {
				return "Tên người thụ hưởng chỉ chứa chữ cái và phải viết in hoa";
			} else if (salary == 0) {
				return "Lương phải lớn hơn 0";
			} else {
				return "";
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			String name = txtName.getText().trim();
			boolean gender;
			if (cboGender.getSelectedItem() == "Nam") {
				gender = true;
			} else {
				gender = false;
			}
			Date birthday = txtDob.getDate();
			String address = txtAddress.getText().trim();
			String phone = txtPhone.getText().trim();
			String bankName = (String) cboBankName.getSelectedItem();
			String accountNumber = txtAccountNumber.getText().trim();
			String beneficiany = txtBeneficiany.getText().trim();
			double salary = 0;
			try {
				salary = Double.parseDouble(txtSalary.getText().replace(".", ""));
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			String position = (String) cboPosition.getSelectedItem();
			String departmentID = ((String) cboDept.getSelectedItem()).substring(0, 4);
			String message = validInput(name, birthday, address, phone, accountNumber, beneficiany, salary);
			if (message.isEmpty()) {
				Employee employee = new EmployeeOffice(name, gender, birthday, address, phone, bankName, accountNumber,
						beneficiany, salary, position, departmentID);
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm nhân viên không?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (employeeOfficeDAO.addEmployeeOffice(employee)) {
						loadDataToTable(employeeOfficeDAO.getAllEmployeeOffice());
						JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công.", "Thông báo xóa",
								JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.NO_OPTION, null);
			}
		}
		if (e.getSource() == btnUpdate) {
			int rowSelected = tblEmp.getSelectedRow();
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
			if (rowSelected >= 0) {
				double salary = Double.parseDouble(txtSalary.getText().replace(".", ""));
				String position = (String) cboPosition.getSelectedItem();
				String departmentID = ((String) cboDept.getSelectedItem()).substring(0, 4);
				String empID = tblEmp.getValueAt(rowSelected, 0).toString();
				Employee employee = new EmployeeOffice(empID, name, gender, birthday, address, phone, bankName,
						accountNumber, beneficiany, salary, position, departmentID);
				if (JOptionPane.showConfirmDialog(this,
						"Bạn có chắn chắn muốn cập nhật lại thông tin của nhân viên này không?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (employeeOfficeDAO.updateEmployeeOffice(employee)) {
						loadDataToTable(employeeOfficeDAO.getAllEmployeeOffice());
						JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật nhân viên không thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					}
				}
			}
		}
		if (e.getSource() == btnDelete) {
			int rowSelected = tblEmp.getSelectedRow();
			if (rowSelected >= 0) {
				String employeeID = tblEmp.getValueAt(rowSelected, 0).toString();
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa nhân viên " + employeeID + " không?",
						"Thông báo xóa", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (employeeOfficeDAO.deleteEmployee(employeeID)) {
						loadDataToTable(employeeOfficeDAO.getAllEmployeeOffice());
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
			txtDob.setDate(maxSelectedDate);
			txtPhone.setText("");
			txtAddress.setText("");
			txtAccountNumber.setText("");
			txtBeneficiany.setText("");
			txtSalary.setText("");
			cboGender.setSelectedIndex(0);
			cboBankName.setSelectedIndex(0);
			cboDept.setSelectedIndex(0);
			cboPosition.setSelectedIndex(0);
		}
		if (e.getSource() == btnSearch) {
			String strSearch = txtSearch.getText().trim();
			if (strSearch.isEmpty()) {
				loadDataToTable(employeeOfficeDAO.getAllEmployeeOffice());
			}
			else if (strSearch.matches("^[A-Za-z]*[0-9]+$")) {
				loadDataToTable(employeeOfficeDAO.searchEmployeeByEmployeeID(strSearch));
			}else {
				loadDataToTable(employeeOfficeDAO.searchEmployeeByName(strSearch));
			}
		}
	}

}
