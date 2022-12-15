package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import custom_field.JTextFieldHint;
import entity.Employee;
import entity.Worker;
import model.FactoryDAO;
import model.TeamDAO;
import model.WorkerDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class WorkerGUI extends JFrame implements ActionListener {
	public WorkerGUI() {
		getUI();
	}

	private static final long serialVersionUID = 1L;
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private JPanel contentPane;
	private final String[] gender = new String[] { "Nam", "Nữ" };
	private final String[] bankName = new String[] { "BIDV" };
	private final String[] headerTable = new String[] { "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ", "SDT" };
	private JTextField txtName, txtPhone, txtAddress, txtAccountNumber, txtBeneficiany, txtSpeciality;
	private WorkerDAO workerDAO;
	private FactoryDAO factoryDAO;
	private TeamDAO teamDAO;
	private DefaultTableModel dtmWorker;
	private DefaultComboBoxModel<String> dcmNameFactory, dcmPosition, dcmNameTeam;
	private JButton btnAdd, btnUpdate, btnDelete, btnReset, btnSearch;
	private JDateChooser txtDob;
	private JComboBox<String> cboGender, cboTeam, cboBankName, cboFactory, cboPosition;
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

	@SuppressWarnings("deprecation")
	public Component getUI() {
		workerDAO = new WorkerDAO();
		factoryDAO = new FactoryDAO();
		teamDAO = new TeamDAO();

		dcmNameFactory = new DefaultComboBoxModel<String>(factoryDAO.getAllNameFactory().toArray(String[]::new));
		dcmPosition = new DefaultComboBoxModel<String>(new String[] { "Công Nhân", "Tổ Trưởng", "Quản Đốc" });

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnInputInfo = new JPanel();
		pnInputInfo.setBackground(Color.WHITE);
		pnInputInfo.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Nhập thông tin nhân sự"));
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
		txtDob.getCalendarButton().setBackground(Color.WHITE);
		txtDob.setMaxSelectableDate(minSelectedDate);
		txtDob.setDate(minSelectedDate);
		txtDob.setDateFormatString("yyyy-MM-dd");
		pnInputInfo.add(txtDob, "cell 3 0,growx");

		JLabel lblGender = new JLabel("Giới tính");
		pnInputInfo.add(lblGender, "cell 4 0,alignx left");

		cboGender = new JComboBox<>(gender);
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

		JLabel lblSpeciality = new JLabel("Chuyên môn");
		pnInputInfo.add(lblSpeciality, "cell 4 1,alignx left");

		txtSpeciality = new JTextField();
		pnInputInfo.add(txtSpeciality, "cell 5 1,growx");
		txtSpeciality.setColumns(10);

		JLabel lblBankName = new JLabel("Ngân hàng");
		pnInputInfo.add(lblBankName, "cell 0 2,alignx left");

		cboBankName = new JComboBox<>(bankName);
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

		JLabel lblFactory = new JLabel("Phân xưởng");
		pnInputInfo.add(lblFactory, "cell 0 4,alignx trailing");

		cboFactory = new JComboBox<>();
		cboFactory.setBackground(Color.WHITE);
		cboFactory.addActionListener(this);
		cboFactory.setModel(dcmNameFactory);
		pnInputInfo.add(cboFactory, "cell 1 4,growx");

		JLabel lblTeam = new JLabel("Tổ");
		pnInputInfo.add(lblTeam, "cell 2 4,alignx left");

		cboTeam = new JComboBox<>();
		cboTeam.setBackground(Color.WHITE);
		pnInputInfo.add(cboTeam, "cell 3 4,growx");

		JLabel lblPosition = new JLabel("Chức vụ");
		pnInputInfo.add(lblPosition, "cell 4 4,alignx left");

		cboPosition = new JComboBox<>();
		cboPosition.setBackground(Color.WHITE);
		cboPosition.setModel(dcmPosition);
		pnInputInfo.add(cboPosition, "cell 5 4,growx");

		JPanel pnOutputInfo = new JPanel();
		pnOutputInfo.setBackground(Color.WHITE);
		contentPane.add(pnOutputInfo, BorderLayout.CENTER);
		pnOutputInfo.setLayout(new BorderLayout(0, 0));

		JPanel pnOperations = new JPanel();
		pnOperations.setBackground(Color.WHITE);
		pnOutputInfo.add(pnOperations, BorderLayout.NORTH);

		btnAdd = new JButton("Thêm");
		btnAdd.setMnemonic(KeyEvent.VK_A);
		btnAdd.setToolTipText("Thêm nhân viên (Alt + A)");
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
		btnAdd.setBorder(new LineBorder(COLOR, 3, false));
		btnAdd.setForeground(COLOR);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(this);
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);

		btnUpdate = new JButton("Cập nhật");
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.setToolTipText("Cập nhật nhân viên (Alt + U)");
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
		btnUpdate.setBorder(new LineBorder(COLOR, 3, false));
		btnUpdate.setForeground(COLOR);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.addActionListener(this);
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);

		btnDelete = new JButton("Xóa");
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnDelete.setToolTipText("Xóa nhân viên (Alt + D)");
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
		btnDelete.setBorder(new LineBorder(COLOR, 3, false));
		btnDelete.setForeground(COLOR);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(this);
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));

		btnReset = new JButton("Làm mới");
		btnReset.setMnemonic(KeyEvent.VK_N);
		btnReset.setToolTipText("Làm mới ô nhập liệu (Alt + N)");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnReset.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReset.setBackground(Color.WHITE);
			}
		});
		btnReset.setBorder(new LineBorder(COLOR, 3, false));
		btnReset.setForeground(COLOR);
		btnReset.setBackground(Color.WHITE);
		btnReset.addActionListener(this);
		btnReset.setIcon(new ImageIcon("images\\operations\\refresh.png"));
		btnReset.setFocusable(false);
		GroupLayout gl_pnOperations = new GroupLayout(pnOperations);
		gl_pnOperations.setHorizontalGroup(gl_pnOperations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnOperations.createSequentialGroup().addGap(317).addComponent(btnAdd).addGap(60)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE).addGap(60)
						.addComponent(btnDelete).addGap(59).addComponent(btnReset)
						.addContainerGap(316, Short.MAX_VALUE)));
		gl_pnOperations.setVerticalGroup(gl_pnOperations.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnOperations.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_pnOperations.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_pnOperations.createParallelGroup(Alignment.BASELINE, false)
										.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_pnOperations.linkSize(SwingConstants.VERTICAL, new Component[] { btnAdd, btnUpdate, btnDelete, btnReset });
		gl_pnOperations.linkSize(SwingConstants.HORIZONTAL, new Component[] { btnAdd, btnUpdate, btnDelete, btnReset });
		pnOperations.setLayout(gl_pnOperations);

		JPanel pnTable = new JPanel();
		pnTable.setBackground(Color.WHITE);
		pnTable.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Danh sách nhân viên sản xuất"));
		pnOutputInfo.add(pnTable);
		pnTable.setLayout(new BorderLayout(0, 0));

		tblWorker = new JTable(dtmWorker = new DefaultTableModel(headerTable, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblWorker.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblWorker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblWorker.getSelectedRow();
				if (rowSelected >= 0) {
					String employeeID = tblWorker.getValueAt(rowSelected, 0).toString();
					Employee emp = workerDAO.getWorker(employeeID);
					String factory = ((String) cboFactory.getSelectedItem()).substring(0, 4);
					dcmNameTeam = new DefaultComboBoxModel<String>(
							teamDAO.getAllNameTeam(factory).toArray(String[]::new));
					cboTeam.setModel(dcmNameTeam);

					txtName.setText(emp.getName().trim());
					txtDob.setDate(emp.getBirthday());
					if (emp.isGender()) {
						cboGender.setSelectedIndex(0);
					} else {
						cboGender.setSelectedIndex(1);
					}

					txtPhone.setText(emp.getPhone().trim());
					txtAddress.setText(emp.getAddress().trim());
					txtAccountNumber.setText(emp.getAccountNumber().trim());
					txtBeneficiany.setText(emp.getBeneficiany().trim());
					txtSpeciality.setText(((Worker) emp).getSpeciality().trim());
					String nameFactory = factoryDAO.getNameFactoryByTeamID(((Worker) emp).getTeamID());
					cboFactory.setSelectedItem(nameFactory);
					cboPosition.setSelectedItem(emp.getPosition());
					String nameTeam = teamDAO.getNameTeamByID(((Worker) emp).getTeamID());
					cboTeam.setSelectedItem(nameTeam);
				}
			}
		});
		tblWorker.getTableHeader().setOpaque(false);
		tblWorker.getTableHeader().setBackground(COLOR);
		tblWorker.getTableHeader().setForeground(Color.WHITE);

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
		btnSearch.setMnemonic(KeyEvent.VK_S);
		btnSearch.setToolTipText("Tìm kiếm nhân viên (Alt + S)");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setFocusable(false);
		btnSearch.setIcon(new ImageIcon("images\\operations\\search.png"));
		btnSearch.addActionListener(this);
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

	public void getTeamByFactory() {
		String factory = ((String) cboFactory.getSelectedItem()).substring(0, 4);
		dcmNameTeam = new DefaultComboBoxModel<String>(teamDAO.getAllNameTeam(factory).toArray(String[]::new));
		cboTeam.setModel(dcmNameTeam);
	}

	public void showMessage(JTextField txt, String message) {
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.NO_OPTION, null);
	}

	public boolean validInput(String name, Date birthday, String address, String phone, String accountNumber,
			String beneficiany, String speciality) {
		if (name.isBlank() | birthday == null | address.isEmpty() | phone.isEmpty() | accountNumber.isEmpty()
				| beneficiany.isEmpty() | speciality.isEmpty()) {
			showMessage(txtName, "Hãy nhập đầy đủ thông tin trước");
			return false;
		} else {
			if (!name.matches(
					"^[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸ][a-zàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹ]+(\s[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸ][a-zàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹ]+)*")) {
				showMessage(txtName,
						"Tên gồm một hoặc nhiều từ được ngăn cách nhau bởi khoảng trắng. Chữ cái đầu mỗi từ được viết hoa");
				return false;
			} else if (!address.matches(
					"^[A-Za-z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹ][A-Za-z0-9,\sàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸ]+$")) {
				showMessage(txtAddress, "Địa chỉ bắt đầu bằng chữ cái, chữ số có thể chứa \"/\" hoặc dấu \",\"");
				return false;
			} else if (!phone.matches("^0[35789][0-9]{8}")) {
				showMessage(txtPhone, "Số điện thoại gồm 10 số và bắt đầu bằng 03, 05, 07, 08 hoặc 09");
				return false;
			} else if (!beneficiany.matches(
					"^[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸ][a-zàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹ]+(\s[A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸ][a-zàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹ]+)*")) {
				showMessage(txtBeneficiany,
						"Tên người thụ hưởng gồm một hoặc nhiều từ được ngăn cách nhau bởi khoảng trắng. Chữ cái đầu mỗi từ được viết hoa");
				return false;
			} else if (!accountNumber.matches("^[0-9]{9,14}$")) {
				showMessage(txtAccountNumber, "Số tài khoản ngân hàng từ 9 đến 14 chữ số.");
				return false;
			} else if (!speciality.matches(
					"^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹ][A-Za-z\sàáâãèéêìíòóôõùúăđĩũơàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểưăạảấầẩẫậắằẳẵặẹẻẽềềểễệỉịọỏốồổỗộớờởỡợụủứừễếệỉịọỏốồổỗộớờởỡợụủứừửữựỳỵýỷỹửữựỳỵỷỹÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỄẾỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸỬỮỰỲỴỶỸ]+$")) {
				showMessage(txtSpeciality, "Chuyên môn chỉ chứa chữ cái");
				return false;
			} else {
				return true;
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
			String speciality = txtSpeciality.getText().trim();
			String teamID = cboTeam.getSelectedItem().toString().substring(0, 6);
			String position = (String) cboPosition.getSelectedItem();
			Employee employee = new Worker(name, gender, birthday, address, phone, bankName, accountNumber, beneficiany,
					speciality, teamID, position);
			if (validInput(name, birthday, address, phone, accountNumber, beneficiany, speciality)) {
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
		}
		if (e.getSource() == btnUpdate) {
			int rowSelectedWorker = tblWorker.getSelectedRow();
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
			String position = (String) cboPosition.getSelectedItem();
			if (rowSelectedWorker >= 0) {
				String speciality = txtSpeciality.getText().trim();
				String teamID = cboTeam.getSelectedItem().toString().substring(0, 6);
				String empID = tblWorker.getValueAt(rowSelectedWorker, 0).toString();
				Employee employee = new Worker(empID, name, gender, birthday, address, phone, bankName, accountNumber,
						beneficiany, speciality, teamID, position);
				if (validInput(name, birthday, address, phone, accountNumber, beneficiany, speciality)) {
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
			} else {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên trước.", "Thông báo", JOptionPane.NO_OPTION,
						null);
			}

		}
		if (e.getSource() == btnDelete) {
			int rowSelected = tblWorker.getSelectedRow();
			if (rowSelected >= 0) {
				String employeeID = tblWorker.getValueAt(rowSelected, 0).toString();
				if (!workerDAO.checkDelete_Account(employeeID)) {
					JOptionPane.showMessageDialog(this,
							"Nhân viên " + employeeID + " đã có tài khoản trong hệ thống không thể xóa!!!",
							"Thông báo xóa", JOptionPane.NO_OPTION, null);
				} else if (!workerDAO.checkDelete_Assignment(employeeID)) {
					JOptionPane.showMessageDialog(this,
							"Nhân viên " + employeeID + " đã được phân công không thể xóa!!!", "Thông báo xóa",
							JOptionPane.NO_OPTION, null);
				} else {
					if (JOptionPane.showConfirmDialog(this,
							"Bạn có chắn chắn muốn xóa nhân viên " + employeeID + " không?", "Thông báo xóa",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						if (workerDAO.deleteEmployee(employeeID)) {
							loadDataToTable(workerDAO.getAllWorker());
							JOptionPane.showMessageDialog(this, "Xóa nhân viên " + employeeID + " thành công.",
									"Thông báo xóa", JOptionPane.NO_OPTION, null);
						} else {
							JOptionPane.showMessageDialog(this, "Xóa nhân viên " + employeeID + " không thành công.",
									"Thông báo xóa", JOptionPane.NO_OPTION, null);
						}
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
			String strSearch = txtSearch.getText().trim();
			if (strSearch.isEmpty()) {
				loadDataToTable(workerDAO.getAllWorker());
			} else if (strSearch.matches("^[A-Za-z]*[0-9]+$")) {
				loadDataToTable(workerDAO.searchEmployeeByEmployeeID(strSearch));
			} else {
				loadDataToTable(workerDAO.searchEmployeeByName(strSearch));
			}
		}
		if (e.getSource() == cboFactory) {
			getTeamByFactory();
		}
	}

}
