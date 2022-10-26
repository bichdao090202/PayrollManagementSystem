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

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import custom_field.JTextFieldHint;
import java.awt.Dimension;

public class EmployeeGUI extends JFrame {
	private String[] gender = new String[] { "Nam", "Nữ" };
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JPanel contentPane;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPanel panel;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnReset;
	private String[] headerTableEmployeeOffice = new String[] { "Mã NV", "Tên NV", "Giới tính", "Ngày sinh", "Địa chỉ",
			"SDT" };
	private EmployeeOfficeDAO employeeOfficeDAO;
	
	private DefaultTableModel dtmEmployeeOffice;
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
//		tabbedPane.addTab("Nhân viên hành chính", null, panel_1, null);
		panel.setLayout(new BorderLayout(0, 0));

//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(panel);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Nhập thông tin nhân sự"));
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][][][grow][][]"));

		JLabel lblNewLabel = new JLabel("Họ tên");
		panel_3.add(lblNewLabel, "cell 0 0,alignx left");

		textField = new JTextField();
		panel_3.add(textField, "cell 1 0,growx");
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Ngày sinh");
		panel_3.add(lblNewLabel_1, "cell 2 0,alignx left");

		JDateChooser dateChooser = new JDateChooser();
		panel_3.add(dateChooser, "cell 3 0,growx");

		JLabel lblGender = new JLabel("Giới tính");
		panel_3.add(lblGender, "cell 4 0,alignx left");

		JComboBox<String> cboGender = new JComboBox(gender);
		panel_3.add(cboGender, "cell 5 0,growx");

		JLabel lblNewLabel_3 = new JLabel("SDT");
		panel_3.add(lblNewLabel_3, "cell 0 1");

		textField_2 = new JTextField();
		panel_3.add(textField_2, "cell 1 1,growx");
		textField_2.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Địa chỉ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_4, "cell 2 1,alignx left");

		textField_3 = new JTextField();
		panel_3.add(textField_3, "cell 3 1,growx");
		textField_3.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Chuyên môn");
		panel_3.add(lblNewLabel_5, "cell 4 1,alignx left");

		JComboBox comboBox_1 = new JComboBox();
		panel_3.add(comboBox_1, "cell 5 1,growx");

		JLabel lblBankName = new JLabel("Ngân hàng");
		panel_3.add(lblBankName, "cell 0 2,alignx left");

		JComboBox cboBankName = new JComboBox(new String[] { "BIDV" });
		panel_3.add(cboBankName, "cell 1 2,growx");

		JLabel lblNewLabel_7 = new JLabel("STK");
		panel_3.add(lblNewLabel_7, "cell 2 2,alignx left");

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_3.add(textField_4, "cell 3 2,growx");

		JLabel lblNewLabel_8 = new JLabel("Người thụ hưởng");
		panel_3.add(lblNewLabel_8, "cell 4 2,alignx trailing");

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_3.add(textField_5, "cell 5 2,growx");

		JLabel lblNewLabel_9 = new JLabel("Loại nhân sự");
		panel_3.add(lblNewLabel_9, "cell 0 4");

		JComboBox comboBox_4 = new JComboBox(new String[] { "Nhân viên hành chính", "Nhân viên sản xuất" });
		panel_3.add(comboBox_4, "cell 1 4,growx");

		JLabel lblNewLabel_10 = new JLabel("Phân xưởng/Phòng ban");
		panel_3.add(lblNewLabel_10, "cell 2 4,alignx trailing");

		JComboBox comboBox_3_1 = new JComboBox();
		panel_3.add(comboBox_3_1, "cell 3 4,growx");

		JLabel lblNewLabel_11 = new JLabel("Tổ");
		panel_3.add(lblNewLabel_11, "cell 4 4,alignx left");

		JComboBox comboBox_3_2 = new JComboBox();
		panel_3.add(comboBox_3_2, "cell 5 4,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Chức vụ");
		panel_3.add(lblNewLabel_2, "cell 0 5,alignx left");
		
		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox, "cell 1 5,growx");

		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel pnButtonOperations = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnButtonOperations.getLayout();
		flowLayout.setHgap(60);
		panel_4.add(pnButtonOperations, BorderLayout.NORTH);

		btnAdd = new JButton("Thêm");
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
		btnReset.setIcon(new ImageIcon("images\\operations\\refresh.png"));
		btnReset.setFocusable(false);
		pnButtonOperations.add(btnReset);

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(2, 1, 10, 10));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Danh sách nhân viên hành chính"));
		panel_6.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2, true), "Danh sách nhân viên sản xuất"));
		panel_6.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JTable tbl1 = new JTable(dtmEmployeeOffice = new DefaultTableModel(headerTableEmployeeOffice, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable tbl2 = new JTable(new DefaultTableModel(
				new String[] { "M\u00E3 Nh\u00E2n S\u1EF1", "M\u00E3 Ph\u00F2ng Ban", "H\u1ECD \u0110\u00EAm",
						"T\u00EAn Nh\u00E2n S\u1EF1", "Ng\u00E0y Sinh", "Tu\u1ED5i", "L\u01B0\u01A1ng Ng\u00E0y",
						"Gi\u1EDBi T\u00EDnh", "\u0110\u1ECBa Ch\u1EC9", "SDT", "Chuy\u00EAn M\u00F4n" },
				0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JScrollPane scrollPane = new JScrollPane(tbl1);
		panel_1.add(scrollPane);

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_5, BorderLayout.NORTH);
		
		JTextFieldHint txtfldhntNhpTnHoc = new JTextFieldHint("Nhập tên hoặc mã nhân viên");
		txtfldhntNhpTnHoc.setPreferredSize(new Dimension(200, 25));
		panel_5.add(txtfldhntNhpTnHoc);

		JButton btnNewButton = new JButton();
		btnNewButton.setFocusable(false);
		btnNewButton.setIcon(new ImageIcon("images\\operations\\search.png"));
		panel_5.add(btnNewButton);

		JScrollPane scrollPane_1 = new JScrollPane(tbl2);
		panel_2.add(scrollPane_1);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_7, BorderLayout.NORTH);
		
		JTextFieldHint txtfldhntNhpTnHoc_1 = new JTextFieldHint("Nhập tên hoặc mã nhân viên");
		txtfldhntNhpTnHoc_1.setPreferredSize(new Dimension(200, 25));
		panel_7.add(txtfldhntNhpTnHoc_1);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setIcon(new ImageIcon("images\\operations\\search.png"));
		panel_7.add(btnNewButton_1);

		setContentPane(contentPane);
		
		loadDataToTable();
	}

	public void loadDataToTable() {
		List<Employee> lstEmp = employeeOfficeDAO.getAllEmployeeOffice();
		for (Employee employeeOffice : lstEmp) {
			dtmEmployeeOffice.addRow(employeeOffice.toString().split(","));
		}
	}
}
