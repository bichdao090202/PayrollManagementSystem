package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import entity.Worker;
import model.DepartmentDAO;
import model.EmployeeOfficeDAO;
import model.FactoryDAO;
import model.TeamDAO;
import model.WorkerDAO;
import net.miginfocom.swing.MigLayout;

public class WorkerGUI extends JFrame implements ActionListener {
	public WorkerGUI() {
		getUI();
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String[] gender = new String[] { "Nam", "Nữ" };
	private String[] bankName = new String[] { "BIDV" };
	private String[] headerTable = new String[] { "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ", "SDT" };
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtAccountNumber;
	private JTextField txtBeneficiany;
	private JTextField txtSpeciality;
	private EmployeeOfficeDAO employeeOfficeDAO;
	private WorkerDAO workerDAO;
	private FactoryDAO factoryDAO;
	private TeamDAO teamDAO;
	private DefaultTableModel dtmWorker;
	private DefaultComboBoxModel<String> dcmNameFactory;
	private DefaultComboBoxModel<String> dcmNameTeam;
	private DefaultComboBoxModel<String> dcmPosition;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnReset;
	private JButton btnSearch;
	private JDateChooser txtDob;
	private JComboBox<String> cboGender;
	private JComboBox<String> cboTeam;
	private JComboBox<String> cboBankName;
	private JComboBox<String> cboFactory;
	private JComboBox<String> cboPosition;
	private JTable tblWorker;
	private Date minSelectedDate;
	private JTextFieldHint txtSearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerGUI frame = new WorkerGUI();
					frame.getUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Component getUI() {
		employeeOfficeDAO = new EmployeeOfficeDAO();
		workerDAO = new WorkerDAO();
		factoryDAO = new FactoryDAO();
		teamDAO = new TeamDAO();

		dcmNameFactory = new DefaultComboBoxModel<String>(factoryDAO.getAllNameFactory().toArray(String[]::new));
		dcmPosition = new DefaultComboBoxModel<String>(new String[] { "Công nhân", "Tổ trưởng", "Quản đốc" });

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnInputInfo = new JPanel();
		pnInputInfo.setBackground(Color.WHITE);
		pnInputInfo.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Nhập thông tin nhân sự"));
		contentPane.add(pnInputInfo, BorderLayout.NORTH);
		pnInputInfo.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][grow][]"));

		JLabel lblName = new JLabel("Họ tên");
		pnInputInfo.add(lblName, "cell 0 0,alignx left");

		txtName = new JTextField();
		pnInputInfo.add(txtName, "cell 1 0,growx");
		txtName.setColumns(10);

		JLabel lblDob = new JLabel("Ngày sinh");
		pnInputInfo.add(lblDob, "cell 2 0,alignx left");
		
		minSelectedDate = new Date();
		minSelectedDate.setYear(minSelectedDate.getYear() - 18);

		txtDob = new JDateChooser();
		txtDob.setMaxSelectableDate(minSelectedDate);
		txtDob.setDate(minSelectedDate);
		txtDob.setDateFormatString("yyyy-MM-dd");
		pnInputInfo.add(txtDob, "cell 3 0,growx");

		JLabel lblGender = new JLabel("Giới tính");
		pnInputInfo.add(lblGender, "cell 4 0,alignx left");

		cboGender = new JComboBox(gender);
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

		JLabel lblSpeciality = new JLabel("Chuyên môn");
		pnInputInfo.add(lblSpeciality, "cell 4 1,alignx left");

		txtSpeciality = new JTextField();
		pnInputInfo.add(txtSpeciality, "cell 5 1,growx");
		txtSpeciality.setColumns(10);

		JLabel lblBankName = new JLabel("Ngân hàng");
		pnInputInfo.add(lblBankName, "cell 0 2,alignx left");

		cboBankName = new JComboBox(bankName);
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

		JLabel lblFactory = new JLabel("Phân xưởng");
		pnInputInfo.add(lblFactory, "cell 0 4,alignx trailing");

		cboFactory = new JComboBox();
		cboFactory.addActionListener(this);
		cboFactory.setModel(dcmNameFactory);
		pnInputInfo.add(cboFactory, "cell 1 4,growx");

		JLabel lblTeam = new JLabel("Tổ");
		pnInputInfo.add(lblTeam, "cell 2 4,alignx left");

		cboTeam = new JComboBox();
		pnInputInfo.add(cboTeam, "cell 3 4,growx");

		JLabel lblPosition = new JLabel("Chức vụ");
		pnInputInfo.add(lblPosition, "cell 4 4,alignx left");

		cboPosition = new JComboBox();
		cboPosition.setModel(dcmPosition);
		pnInputInfo.add(cboPosition, "cell 5 4,growx");

		JPanel pnOutputInfo = new JPanel();
		pnOutputInfo.setBackground(Color.WHITE);
		contentPane.add(pnOutputInfo, BorderLayout.CENTER);
		pnOutputInfo.setLayout(new BorderLayout(0, 0));

		JPanel pnOperations = new JPanel();
		pnOperations.setBackground(Color.WHITE);
		FlowLayout fl_pnOperations = (FlowLayout) pnOperations.getLayout();
		fl_pnOperations.setHgap(60);
		pnOutputInfo.add(pnOperations, BorderLayout.NORTH);

		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(this);
		btnAdd.setToolTipText("Thêm nhân viên");
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);
		pnOperations.add(btnAdd);

		btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(this);
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);
		pnOperations.add(btnUpdate);

		btnDelete = new JButton("Xóa");
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(this);
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));
		pnOperations.add(btnDelete);

		btnReset = new JButton("Làm mới");
		btnReset.addActionListener(this);
		btnReset.setIcon(new ImageIcon("images\\operations\\refresh.png"));
		btnReset.setFocusable(false);
		pnOperations.add(btnReset);

		JPanel pnTable = new JPanel();
		pnTable.setBackground(Color.WHITE);
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Danh sách nhân viên sản xuất"));
		pnOutputInfo.add(pnTable);
		pnTable.setLayout(new BorderLayout(0, 0));

		tblWorker = new JTable(dtmWorker = new DefaultTableModel(headerTable, 0)) {
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
					cboFactory.setModel(dcmNameFactory);
					lblFactory.setText("Phân xưởng");
					cboTeam.setEnabled(true);
					String factory = ((String) cboFactory.getSelectedItem()).substring(0, 4);
					dcmNameTeam = new DefaultComboBoxModel<String>(
							teamDAO.getAllNameTeam(factory).toArray(String[]::new));
					cboTeam.setModel(dcmNameTeam);

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
					cboFactory.setSelectedItem(nameFactory);
					String nameTeam = teamDAO.getNameTeamByID(((Worker) emp).getTeamID());
					cboTeam.setSelectedItem(nameTeam);
				}
			}
		});

		JScrollPane scrollTable = new JScrollPane(tblWorker);
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
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("images\\operations\\search.png"));
		pnSearch.add(btnSearch);

		setContentPane(contentPane);

		loadDataToTable(workerDAO.getAllWorker());
		getTeamByFactory();
		
		return contentPane;
	}

	public void loadDataToTable(List<Employee> listWorker) {
		dtmWorker = new DefaultTableModel(headerTable, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (Employee emp : listWorker) {
			dtmWorker.addRow(emp.toString().split(";"));
		}
		tblWorker.setModel(dtmWorker);
	}

	public boolean checkInput(String input, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher macth = pattern.matcher(input);
		return macth.matches();
	}
	
	public void getTeamByFactory() {
		String factory = ((String) cboFactory.getSelectedItem()).substring(0, 4);
		dcmNameTeam = new DefaultComboBoxModel<String>(teamDAO.getAllNameTeam(factory).toArray(String[]::new));
		cboTeam.setModel(dcmNameTeam);
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
			String speciality = txtSpeciality.getText();
			String teamID = cboTeam.getSelectedItem().toString().substring(0, 6);
			Employee employee = new Worker(name, gender, birthday, address, phone, bankName, accountNumber, beneficiany,
					speciality, teamID);
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm nhân viên không?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (workerDAO.addWorker(employee)) {
					loadDataToTable(workerDAO.getAllWorker());
					JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công.", "Thông báo xóa",
							JOptionPane.NO_OPTION, null);
				} else {
					JOptionPane.showMessageDialog(this, "Thêm nhân viên không thành công.", "Thông báo",
							JOptionPane.NO_OPTION, null);
				}
			}
		}
		if (e.getSource() == btnUpdate) {
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
			if (rowSelectedWorker >= 0) {
				String speciality = txtSpeciality.getText();
				String teamID = cboTeam.getSelectedItem().toString().substring(0, 6);
				String empID = tblWorker.getValueAt(rowSelectedWorker, 0).toString();
				Employee employee = new Worker(empID, name, gender, birthday, address, phone, bankName, accountNumber,
						beneficiany, speciality, teamID);
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm nhân viên không?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (workerDAO.updateWorker(employee)) {
						loadDataToTable(workerDAO.getAllWorker());
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên không thành công.",
								"Thông báo", JOptionPane.NO_OPTION, null);
					}
				}
			}

		}
		if (e.getSource() == btnDelete) {
			int rowSelected = tblWorker.getSelectedRow();
			if (rowSelected >= 0) {
				String employeeID = tblWorker.getValueAt(rowSelected, 0).toString();
				if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa nhân viên " + employeeID + " không?",
						"Thông báo xóa", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (workerDAO.deleteEmployee(employeeID)) {
						loadDataToTable(workerDAO.getAllWorker());
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
			txtDob.setDate(minSelectedDate);
			txtPhone.setText("");
			txtAddress.setText("");
			txtAccountNumber.setText("");
			txtBeneficiany.setText("");
			txtSpeciality.setText("");
			cboGender.setSelectedIndex(0);
			cboBankName.setSelectedIndex(0);
			cboFactory.setSelectedIndex(0);
			cboTeam.setSelectedIndex(0);
			cboPosition.setSelectedIndex(0);
		}
		if (e.getSource() == btnSearch) {

		}
		if (e.getSource() == cboFactory) {
			getTeamByFactory();
		}
	}

}
