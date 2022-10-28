package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import model.EmployeeOfficeDAO;
import model.WorkerDAO;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import custom_field.JTextFieldHint;
import java.awt.Dimension;

public class EmployeeGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private String[] gender = new String[] { "Nam", "Nữ" };
	private String[] bankName = new String[] {"BIDV"};
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JPanel contentPane;
	private JTextField txtAccountNumber;
	private JTextField txtBeneficiany;
	private JPanel panel;
	private String[] headerTableEmployeeOffice = new String[] { "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ",
			"SDT" };
	private String[] headerTableWorker = new String[] {"Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ", "SDT"};
	private EmployeeOfficeDAO employeeOfficeDAO;
	private WorkerDAO workerDAO;

	private DefaultTableModel dtmEmployeeOffice;
	private DefaultTableModel dtmWorker;
	private JButton btnUpdate;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnReset;
	private JButton btnSearchWorker;
	private JButton btnSearchEmpOffice;
	private JDateChooser txtDob;
	private JComboBox<String> cboGender;
	private JComboBox<String> cboTeam;
	private JComboBox<String> cboBankName;
	private JComboBox<String> cboSpeciality;
	private JComboBox<String> cboDept_Factory;
	private JComboBox<String> cboTypeEmployee;
	private JComboBox<String> cboPosition;
	private JTable tblEmpOffice;
	private JTable tblWorker;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EmployeeGUI() {
		employeeOfficeDAO = new EmployeeOfficeDAO();
		workerDAO = new WorkerDAO();

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

		cboSpeciality = new JComboBox();
		panel_3.add(cboSpeciality, "cell 5 1,growx");

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
		panel_3.add(cboTypeEmployee, "cell 1 4,growx");

		JLabel lblDept_Factory = new JLabel("Phân xưởng/Phòng ban");
		panel_3.add(lblDept_Factory, "cell 2 4,alignx trailing");

		cboDept_Factory = new JComboBox();
		panel_3.add(cboDept_Factory, "cell 3 4,growx");

		JLabel lblTeam = new JLabel("Tổ");
		panel_3.add(lblTeam, "cell 4 4,alignx left");

		cboTeam = new JComboBox();
		panel_3.add(cboTeam, "cell 5 4,growx");

		JLabel lblPosition = new JLabel("Chức vụ");
		panel_3.add(lblPosition, "cell 0 5,alignx left");

		cboPosition = new JComboBox();
		panel_3.add(cboPosition, "cell 1 5,growx");

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
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);
		pnButtonOperations.add(btnUpdate);

		btnDelete = new JButton("Xóa");
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDelete.setFocusable(false);
		pnButtonOperations.add(btnDelete);

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

		tblWorker = new JTable(dtmWorker = new DefaultTableModel(headerTableWorker, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

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
	}

	public void loadDataToTable() {
		List<Employee> lstEmpOffice = employeeOfficeDAO.getAllEmployeeOffice();
		for (Employee emp : lstEmpOffice) {
			dtmEmployeeOffice.addRow(emp.toString().split(","));
		}
		
		List<Employee> lstWorker = workerDAO.getAllWorker();
		for (Employee emp : lstWorker) {
			dtmWorker.addRow(emp.toString().split(","));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {

		} else if (e.getSource() == btnUpdate) {

		} else if (e.getSource() == btnDelete) {
			
		} else if (e.getSource() == btnReset) {
			txtName.setText("");
			txtDob.setDate(null);
			cboGender.setSelectedIndex(0);
			
			txtPhone.setText("");
			txtAddress.setText("");
			cboSpeciality.setSelectedIndex(0);
			
			cboBankName.setSelectedIndex(0);
			txtAccountNumber.setText("");
			txtBeneficiany.setText("");
			
			cboTypeEmployee.setSelectedIndex(0);
			cboDept_Factory.setSelectedIndex(0);
			cboTeam.setSelectedIndex(0);
			
			cboPosition.setSelectedIndex(0);			
		} else if (e.getSource() == btnSearchEmpOffice) {

		} else {

		}
	}
}
