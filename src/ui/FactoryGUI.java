package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.WebColors;

import custom_field.JTextFieldHint;
import model.FactoryDAO;
import entity.Worker;
import entity.Factory;
import entity.TeamProducing;
import javax.swing.ImageIcon;

public class FactoryGUI extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdFactory;
	private JTextField txtNameFactory;
	private JTextField txtIdTeam;
	private JTextField txtNameTeam;
	private JTextField txtSearchIdFactory;
	private JTextField txtSearchIdTeam;
	private JTable tblListFactory;
	private JTable tblListTeam;
	private JTable tblEmployeeByTeam;
	private JTable tblTeam;
	private DefaultTableModel dtmTeam;
	private DefaultTableModel dtmListFactory;
	private DefaultTableModel dtmListTeam;
	private DefaultTableModel dtmListEmployee;
	private JButton btnInsertTeam;
	private JButton btnInsertFactory;
	private JButton btnSearchIdFactory;
	private JButton btnDeleteFactory;
	private JButton btnUpdateFactory;
	private JButton btnSearchIdTeam;
	private JButton btnDeleteTeam;
	private JButton btnUpdateTeam;
	private JButton btnChange;
	private JButton btnClean;
	private FactoryDAO Dao_Factory = new FactoryDAO();
	private List<TeamProducing> ListTeam = new ArrayList<TeamProducing>();
	private List<TeamProducing> ListTeamSearch = new ArrayList<TeamProducing>();
	private List<Factory> listFactorySearch = new ArrayList<Factory>();

	public FactoryGUI() {
//		getUI();
	}
	public Component getUI() {
		setSize(1200, 690);
		getContentPane().setLayout(null);

		JPanel pnlFactory = new JPanel();
		pnlFactory.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Phân xưởng", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0,140,140)));
		pnlFactory.setBounds(10, 10, 460, 191);
		getContentPane().add(pnlFactory);
		pnlFactory.setLayout(null);

		JLabel lblIdFactory = new JLabel("Mã phân xưởng :");
		lblIdFactory.setForeground(Color.BLACK);
		lblIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdFactory.setBounds(37, 35, 115, 13);
		pnlFactory.add(lblIdFactory);

		txtIdFactory = new JTextField();
		txtIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdFactory.setBounds(162, 35, 245, 19);
		pnlFactory.add(txtIdFactory);
		txtIdFactory.setColumns(10);

		JLabel lblNameFactory = new JLabel("Tên phân xưởng :");
		lblNameFactory.setForeground(Color.BLACK);
		lblNameFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameFactory.setBounds(37, 75, 115, 13);
		pnlFactory.add(lblNameFactory);

		txtNameFactory = new JTextField();
		txtNameFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameFactory.setColumns(10);
		txtNameFactory.setBounds(162, 75, 245, 19);
		pnlFactory.add(txtNameFactory);
		txtNameFactory.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (Dao_Factory.searchFactoryByIdFactory(txtIdFactory.getText()) == null && dtmTeam.getRowCount() == 0
						&& txtNameTeam.getText().isEmpty() && !txtIdFactory.getText().isEmpty()) {
					randomIdTeam();

				}
			}

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		btnClean = new JButton("");
		btnClean.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClean.setFocusPainted(false);
		btnClean.setBounds(37, 110, 50, 21);
		pnlFactory.add(btnClean);

		JPanel pnlTeam = new JPanel();
		pnlTeam.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Tổ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0,140,140)));
		pnlTeam.setBounds(513, 10, 485, 191);
		getContentPane().add(pnlTeam);
		pnlTeam.setLayout(null);

		JLabel lblIdTeam = new JLabel("Mã tổ :");
		lblIdTeam.setForeground(Color.BLACK);
		lblIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdTeam.setBounds(40, 32, 53, 13);
		pnlTeam.add(lblIdTeam);

		txtIdTeam = new JTextField();
		txtIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdTeam.setColumns(10);
		txtIdTeam.setBounds(96, 29, 340, 19);
		pnlTeam.add(txtIdTeam);
		txtIdTeam.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
//	            System.out.println("focus gained");
				if (btnInsertTeam.getIcon().toString().equals("images\\Close-2-icon.png")) {
					btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
//	            	btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
				}
			}/* from w ww . j a va2 s. c o m */

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		JLabel lblNameTeam = new JLabel("Tên tổ :");
		lblNameTeam.setForeground(Color.BLACK);
		lblNameTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameTeam.setBounds(40, 70, 53, 13);
		pnlTeam.add(lblNameTeam);

		txtNameTeam = new JTextField();
		txtNameTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameTeam.setColumns(10);
		txtNameTeam.setBounds(96, 67, 215, 19);
		pnlTeam.add(txtNameTeam);
		txtNameTeam.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
//	            System.out.println("focus gained");
				if (btnInsertTeam.getIcon().toString().equals("images\\Close-2-icon.png")) {
					btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
//	            	btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
				}
				else if(txtIdTeam.getText().isEmpty()) {
					randomIdTeam();
				}
			}/* from w ww . j a va2 s. c o m */

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		btnInsertTeam = new JButton("");
		btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnInsertTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInsertTeam.setBounds(315, 67, 67, 21);
		pnlTeam.add(btnInsertTeam);

		JScrollPane scrTeam = new JScrollPane();
		scrTeam.setBounds(40, 93, 396, 88);
		pnlTeam.add(scrTeam);

		tblTeam = new JTable();
		tblTeam.setForeground(Color.BLACK);
		tblTeam.setGridColor(new Color(0,140,140));
		tblTeam.setBorder(new LineBorder(new Color(0,140,140)));
		JTableHeader tblHeaderTeam = tblTeam.getTableHeader();
		tblHeaderTeam.setBackground(new Color(14,85,78));
		tblHeaderTeam.setForeground(Color.WHITE);
		tblTeam.setModel(
				dtmTeam = new DefaultTableModel(new Object[][] {}, new String[] { "Mã tổ", "Tên tổ"}) {
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				});
		scrTeam.setViewportView(tblTeam);

		btnInsertFactory = new JButton("Thêm phân xưởng");
		btnInsertFactory.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnInsertFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertFactory.setBounds(1008, 90, 168, 40);
		getContentPane().add(btnInsertFactory);

		JPanel pnlListFactories = new JPanel();
		pnlListFactories.setBounds(-10, 211, 1186, 442);
		getContentPane().add(pnlListFactories);
		pnlListFactories.setLayout(null);

		JPanel pnlListFactory = new JPanel();
		pnlListFactory.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Danh sách phân xưởng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,140,140)));
		pnlListFactory.setBounds(10, 10, 580, 432);
		pnlListFactories.add(pnlListFactory);
		pnlListFactory.setLayout(null);

		JLabel lblNoteIdFactory = new JLabel("Nhập mã phân xưởng :");
		lblNoteIdFactory.setForeground(Color.BLACK);
		lblNoteIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdFactory.setBounds(10, 17, 134, 15);
		pnlListFactory.add(lblNoteIdFactory);

		txtSearchIdFactory = new JTextFieldHint("Nhập mã phân xưởng...");
		txtSearchIdFactory.setPreferredSize(new Dimension(200,25));
		txtSearchIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdFactory.setColumns(10);
		txtSearchIdFactory.setBounds(142, 16, 145, 19);
		pnlListFactory.add(txtSearchIdFactory);

		btnSearchIdFactory = new JButton("");
		btnSearchIdFactory.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdFactory.setBounds(297, 15, 70, 21);
		pnlListFactory.add(btnSearchIdFactory);

		btnDeleteFactory = new JButton("");
		btnDeleteFactory.setIcon(new ImageIcon("images\\Close-2-icon.png"));
		btnDeleteFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteFactory.setBounds(382, 15, 70, 21);
		pnlListFactory.add(btnDeleteFactory);

		btnUpdateFactory = new JButton("");
		btnUpdateFactory.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnUpdateFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateFactory.setBounds(462, 15, 70, 21);
		pnlListFactory.add(btnUpdateFactory);

		JScrollPane scrListFactory = new JScrollPane();
		scrListFactory.setBounds(10, 57, 560, 365);
		pnlListFactory.add(scrListFactory);

		tblListFactory = new JTable();
		tblListFactory.setForeground(Color.BLACK);
		tblListFactory.setGridColor(new Color(0,140,140));
		tblListFactory.setRowHeight(25);
		tblListFactory.setBorder(new LineBorder(new Color(0,140,140)));
		JTableHeader tblHeaderListFactory = tblListFactory.getTableHeader();
		tblHeaderListFactory.setBackground(new Color(14,85,78));
		tblHeaderListFactory.setForeground(Color.WHITE);
		tblHeaderListFactory.setPreferredSize(new Dimension(100, 30));
		tblHeaderListFactory.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblListFactory.setModel(dtmListFactory = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã phân xưởng", "Tên phân xưởng"}) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrListFactory.setViewportView(tblListFactory);

		JPanel pnlListTeam = new JPanel();
		pnlListTeam.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Danh sách tổ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0,140,140)));
		pnlListTeam.setBounds(596, 10, 580, 211);
		pnlListFactories.add(pnlListTeam);
		pnlListTeam.setLayout(null);

		JScrollPane scrListTeam = new JScrollPane();
		scrListTeam.setBounds(10, 57, 560, 144);
		pnlListTeam.add(scrListTeam);

		tblListTeam = new JTable();
		tblListTeam.setForeground(Color.BLACK);
		tblListTeam.setBorder(new LineBorder(new Color(0,140,140)));
		tblListTeam.setGridColor(new Color(0,140,140));
		tblListTeam.setRowHeight(25);
		JTableHeader tblHeaderListTeam = tblListTeam.getTableHeader();
		tblHeaderListTeam.setBackground(new Color(14,85,78));
		tblHeaderListTeam.setForeground(Color.WHITE);
		tblHeaderListTeam.setPreferredSize(new Dimension(100, 30));
		tblHeaderListTeam.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblListTeam.setModel(dtmListTeam = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã tổ", "Tên tổ"}) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrListTeam.setViewportView(tblListTeam);

		JLabel lblNoteIdTeam = new JLabel("Nhập mã tổ :");
		lblNoteIdTeam.setForeground(Color.BLACK);
		lblNoteIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdTeam.setBounds(10, 19, 85, 15);
		pnlListTeam.add(lblNoteIdTeam);

		txtSearchIdTeam = new JTextFieldHint("Nhập mã tổ...");
		txtSearchIdTeam.setPreferredSize(new Dimension(200,25));
		txtSearchIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdTeam.setColumns(10);
		txtSearchIdTeam.setBounds(91, 18, 196, 19);
		pnlListTeam.add(txtSearchIdTeam);

		btnSearchIdTeam = new JButton("");
		btnSearchIdTeam.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdTeam.setBounds(297, 17, 70, 21);
		pnlListTeam.add(btnSearchIdTeam);

		btnDeleteTeam = new JButton("");
		btnDeleteTeam.setIcon(new ImageIcon("images\\Close-2-icon.png"));
		btnDeleteTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteTeam.setBounds(382, 17, 70, 21);
		pnlListTeam.add(btnDeleteTeam);

		btnUpdateTeam = new JButton("");
		btnUpdateTeam.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnUpdateTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateTeam.setBounds(462, 17, 70, 21);
		pnlListTeam.add(btnUpdateTeam);

		JPanel pnlListEmployeeByTeam = new JPanel();
		pnlListEmployeeByTeam.setLayout(null);
		pnlListEmployeeByTeam.setBorder(new TitledBorder(new LineBorder(new Color(0,140,140)), "Danh sách nhân viên",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,140,140)));
		pnlListEmployeeByTeam.setBounds(596, 231, 580, 201);
		pnlListFactories.add(pnlListEmployeeByTeam);

		JScrollPane scrListEmployeeByTeam = new JScrollPane();
		scrListEmployeeByTeam.setBounds(10, 20, 560, 171);
		pnlListEmployeeByTeam.add(scrListEmployeeByTeam);

		tblEmployeeByTeam = new JTable();
		tblEmployeeByTeam.setForeground(Color.BLACK);
		tblEmployeeByTeam.setBorder(new LineBorder(new Color(0,140,140)));
		tblEmployeeByTeam.setGridColor(new Color(0,140,140));
		tblEmployeeByTeam.setRowHeight(25);
		JTableHeader tblHeaderlistEmployee = tblEmployeeByTeam.getTableHeader();
		tblHeaderlistEmployee.setBackground(new Color(14,85,78));
		tblHeaderlistEmployee.setForeground(Color.WHITE);
		tblHeaderlistEmployee.setPreferredSize(new Dimension(100, 30));
		tblHeaderlistEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblEmployeeByTeam.setModel(dtmListEmployee = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrListEmployeeByTeam.setViewportView(tblEmployeeByTeam);

		btnChange = new JButton("");
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnChange.setFocusPainted(false);
		btnChange.setBounds(386, 67, 50, 21);
		pnlTeam.add(btnChange);
		
		DocumentListener enventChangeSearchFactory = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				listFactorySearch = Dao_Factory.searchListFactory(txtSearchIdFactory.getText());
				deleteDataOnTableModel(dtmListFactory);
				loadListFactoryWithFactoried(listFactorySearch);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				listFactorySearch = Dao_Factory.searchListFactory(txtSearchIdFactory.getText());
				deleteDataOnTableModel(dtmListFactory);
				loadListFactoryWithFactoried(listFactorySearch);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				listFactorySearch = Dao_Factory.searchListFactory(txtSearchIdFactory.getText());
				deleteDataOnTableModel(dtmListFactory);
				loadListFactoryWithFactoried(listFactorySearch);
			}

        };
        
        DocumentListener enventChangeSearchTeam = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				ListTeamSearch = Dao_Factory.searchListTeam(txtSearchIdTeam.getText());
				deleteDataOnTableModel(dtmListTeam);
				loadListTeamByWithTeam(ListTeamSearch, dtmListTeam);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				ListTeamSearch = Dao_Factory.searchListTeam(txtSearchIdTeam.getText());
				deleteDataOnTableModel(dtmListTeam);
				loadListTeamByWithTeam(ListTeamSearch, dtmListTeam);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				ListTeamSearch = Dao_Factory.searchListTeam(txtSearchIdTeam.getText());
				deleteDataOnTableModel(dtmListTeam);
				loadListTeamByWithTeam(ListTeamSearch, dtmListTeam);
			}

        };
        
        txtSearchIdFactory.getDocument().addDocumentListener(enventChangeSearchFactory);
        txtSearchIdTeam.getDocument().addDocumentListener(enventChangeSearchTeam);
		
		btnClean.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnChange.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnInsertFactory.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnInsertTeam.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnSearchIdFactory.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnSearchIdTeam.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnDeleteFactory.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnDeleteTeam.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnUpdateFactory.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnUpdateTeam.setBorder(new LineBorder(new Color(14,85,78), 2));

		tblListFactory.addMouseListener(this);
		tblListTeam.addMouseListener(this);
		tblTeam.addMouseListener(this);
		tblEmployeeByTeam.addMouseListener(this);
		
		btnClean.addMouseListener(this);
		btnChange.addMouseListener(this);
		btnInsertFactory.addMouseListener(this);
		btnInsertTeam.addMouseListener(this);
		btnSearchIdFactory.addMouseListener(this);
		btnSearchIdTeam.addMouseListener(this);
		btnDeleteFactory.addMouseListener(this);
		btnDeleteTeam.addMouseListener(this);
		btnUpdateFactory.addMouseListener(this);
		btnUpdateTeam.addMouseListener(this);
		
		btnClean.addActionListener(this);
		btnChange.addActionListener(this);
		btnInsertFactory.addActionListener(this);
		btnInsertTeam.addActionListener(this);
		btnSearchIdFactory.addActionListener(this);
		btnSearchIdTeam.addActionListener(this);
		btnDeleteFactory.addActionListener(this);
		btnDeleteTeam.addActionListener(this);
		btnUpdateFactory.addActionListener(this);
		btnUpdateTeam.addActionListener(this);

		btnClean.setFocusPainted(false);
		btnChange.setFocusPainted(false);
		btnInsertTeam.setFocusPainted(false);
		btnInsertFactory.setFocusPainted(false);
		btnSearchIdFactory.setFocusPainted(false);
		btnSearchIdTeam.setFocusPainted(false);
		btnDeleteFactory.setFocusPainted(false);
		btnDeleteTeam.setFocusPainted(false);
		btnUpdateFactory.setFocusPainted(false);
		btnUpdateTeam.setFocusPainted(false);
		
		btnClean.setMnemonic(KeyEvent.VK_C);
		btnClean.setToolTipText("Phím tắt : Alt + C");
		btnInsertFactory.setMnemonic(KeyEvent.VK_A);
		btnInsertFactory.setToolTipText("Phím tắt : Alt + A");
		btnSearchIdFactory.setMnemonic(KeyEvent.VK_S);
		btnSearchIdFactory.setToolTipText("Phím tắt : Alt + S");
		btnDeleteFactory.setMnemonic(KeyEvent.VK_D);
		btnDeleteFactory.setToolTipText("Phím tắt : Alt + D");
		btnUpdateFactory.setMnemonic(KeyEvent.VK_U);
		btnUpdateFactory.setToolTipText("Phím tắt : Alt + U");

		loadListFactory();
		loadListTeam();
		randomIdFactory();
		txtIdFactory.setEditable(false);
		txtIdTeam.setEditable(false);

		return getContentPane();
	}

	// Lấy và hiển thị tất cả phân xưởng
	public void loadListFactory() {
		List<entity.Factory> listFactory;
		listFactory = Dao_Factory.getListFactory();
		for (entity.Factory factory : listFactory) {
			addRowFactory(factory);
		}
	}
	
	//Lấy và hiển thị danh sách phân xưởng với phân xưởng đã có
	public void loadListFactoryWithFactoried(List<Factory> listFactory) {
		for (entity.Factory factory : listFactory) {
			addRowFactory(factory);
		}
	}

	// Lấy và hiển thị tất cả tổ
	public void loadListTeam() {
		List<entity.TeamProducing> listTeam;
		listTeam = Dao_Factory.getListTeam();
		for (entity.TeamProducing team : listTeam) {
			addRowListTeam(team, dtmListTeam);
		}
	}
	
	// Lấy và hiển thị danh sách tổ theo mã phân xưởng
	public void loadListTeamByIdFactory(String idFactory, DefaultTableModel dtm) {
		List<TeamProducing> listTeam;
		listTeam = Dao_Factory.getListTeamByIdFactory(idFactory);
		for (TeamProducing team : listTeam) {
			addRowListTeam(team, dtm);
		}
	}
	
	// Lấy và hiển thị danh sách tổ với tổ đã có
	public void loadListTeamByWithTeam(List<TeamProducing> listTeam , DefaultTableModel dtm) {
		for (TeamProducing team : listTeam) {
			addRowListTeam(team, dtm);
		}
	}

	// Lấy và hiển thị nhân viên theo mã tổ
	public void loadListEmployeeByIdTeam(String idTeam) {
		List<Worker> listEmployee;
		listEmployee = Dao_Factory.getListEmployeeByIdTeam(idTeam);
		for (Worker employee : listEmployee) {
			addRowEmployee(employee);
		}
	}

	// Thêm tổ vào bảng tổ
	public void addRowListTeam(TeamProducing team, DefaultTableModel dtm) {
		String[] row = { team.getTeamID(), team.getName(), team.getLeaderID(), team.getFactoryID() };
		dtm.addRow(row);
	}

	// Thêm phân xưởng vào bảng phân xưởng
	public void addRowFactory(entity.Factory factory) {
		String[] row = { factory.getFactoryID(), factory.getName(), factory.getHeadForemanID() };
		dtmListFactory.addRow(row);
	}

	// Thêm nhân viên vào bảng nhân viên
	public void addRowEmployee(Worker employee) {
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		String[] row = { employee.getEmployeeID(), employee.getName(), employee.isGender() ? "Nam" : "Nữ",
				DateFor.format(employee.getBirthday()), "0"+employee.getPhone() };
		dtmListEmployee.addRow(row);
	}

	// Kiểm tra tổ có được thêm vào bảng
	public boolean checkTeamOnTable(String idTeam) {
		int rowCount = dtmTeam.getRowCount();
		if (rowCount > 0) {
			for (int i = 0; i < rowCount; i++) {
				if (dtmTeam.getValueAt(i, 0).toString().equals(idTeam)) {
					return true;
				}
			}
		}
		return false;
	}

	// Tạo mã phân xưởng
	public void randomIdFactory() {
		int order = Dao_Factory.checkOrderFactoryPresent() + 1;
		String idFactory = "PX";
		if (order < 10) {
			idFactory += "0" + order;
		} else {
			idFactory += order;
		}
		boolean test = true;
		while (Dao_Factory.searchFactoryByIdFactory(idFactory) == null && test) {
			cleanFactory();
			txtIdFactory.setText(idFactory);
			test = false;
			break;
		}
		if (test) {
			randomIdFactory();
		}
	}

	// Tạo mã tổ
	public void randomIdTeam() {
		int quantity = dtmTeam.getRowCount();
		int order = 0;
		if(quantity > 0) {
			for(int i = 0; i < quantity; i++) {
				int orderTeam = Integer.parseInt(dtmTeam.getValueAt(i, 0).toString().substring(4));
				if(orderTeam > order) {
					order = orderTeam;
				}
			}
			order += 1;
		}
		else {
			order += 1;
		}
		String idTeam = "";
		if (order >= 10) {
			idTeam = txtIdFactory.getText() + order + "";
		} else {
			idTeam = txtIdFactory.getText() + "0" + order;
		}
		while (Dao_Factory.searchTeamByIdTeam(idTeam) == null && !checkTeamOnTable(idTeam)) {
			cleanTextFieldTeam();
			txtIdTeam.setText(idTeam);
			break;
		}
	}

	// Xóa dữ liệu trên bảng
	private void deleteDataOnTableModel(DefaultTableModel dtm) {
		dtm.setRowCount(0);
	}

	// Kiểm tra phân xưởng có tồn tại
	private boolean checkContainFactory(entity.Factory factory) {
		List<entity.Factory> listFactory = Dao_Factory.getListFactory();
		if (listFactory.contains(factory)) {
			return true;
		}
		return false;
	}

	// Cập nhật textField của tổ về rỗng
	public void cleanTextFieldTeam() {
		txtIdTeam.setText("");
		txtNameTeam.setText("");
	}

	// Cập nhật textField của phân xưởng về rỗng và tạo mã mới
	public void cleanFactory() {
		deleteDataOnTableModel(dtmListFactory);
		deleteDataOnTableModel(dtmListTeam);
		deleteDataOnTableModel(dtmTeam);
		deleteDataOnTableModel(dtmListEmployee);
		loadListFactory();
		loadListTeam();
		txtIdFactory.setText("");
		txtNameFactory.setText("");
		cleanTextFieldTeam();
		txtNameFactory.requestFocus();
		btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnInsertFactory.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnUpdateFactory.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnInsertFactory.setText("Thêm phân xưởng");
	}

	// Cập nhật các nút của tổ về trạng thái ban đầu
	public void resetTeam() {
		btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnUpdateTeam.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
	}

	public static void main(String[] args) {
		new FactoryGUI().setVisible(true);
		FactoryGUI factory = new FactoryGUI();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblListFactory)) {
			deleteDataOnTableModel(dtmListTeam);
			deleteDataOnTableModel(dtmTeam);
			deleteDataOnTableModel(dtmListEmployee);
			btnInsertFactory.setText("Thêm phân xưởng");
			btnInsertFactory.setIcon(new ImageIcon("images\\math-add-icon.png"));
			btnUpdateFactory.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			String idFactory;
			int rowFactorySelected = tblListFactory.getSelectedRow();
			idFactory = dtmListFactory.getValueAt(rowFactorySelected, 0).toString();
			List<TeamProducing> listTeam = Dao_Factory.getListTeamByIdFactory(idFactory);
			if (rowFactorySelected >= 0 && listTeam.size() > 0) {
				txtIdFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 0).toString());
				txtNameFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 1).toString());

				loadListTeamByIdFactory(dtmListFactory.getValueAt(rowFactorySelected, 0).toString(), dtmListTeam);
				loadListTeamByIdFactory(dtmListFactory.getValueAt(rowFactorySelected, 0).toString(), dtmTeam);
				txtIdTeam.setText(dtmTeam.getValueAt(0, 0).toString());
				txtNameTeam.setText(dtmTeam.getValueAt(0, 1).toString());
				resetTeam();
			} else if (listTeam.size() <= 0) {
				txtIdFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 0).toString());
				txtNameFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 1).toString());
				cleanTextFieldTeam();
				txtSearchIdTeam.setText("");
			}
		} else if (o.equals(tblListTeam)) {
			int rowTeamSelected = tblListTeam.getSelectedRow();
			int rowFactorySelected = tblListFactory.getSelectedRow();
			if (rowTeamSelected >= 0) {
				txtIdTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				txtNameTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 1).toString());

				deleteDataOnTableModel(dtmListEmployee);
				loadListEmployeeByIdTeam(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				deleteDataOnTableModel(dtmTeam);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);

				btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			}

		} else if (o.equals(tblTeam)) {
			Factory factory = Dao_Factory.searchFactoryByIdFactory(txtIdFactory.getText());
			int rowTeamSelected = tblTeam.getSelectedRow();
			if (rowTeamSelected >= 0) {
				txtIdTeam.setText(dtmTeam.getValueAt(rowTeamSelected, 0).toString());
				txtNameTeam.setText(dtmTeam.getValueAt(rowTeamSelected, 1).toString());
				btnInsertTeam.setIcon(new ImageIcon("images\\Close-2-icon.png"));
				btnUpdateTeam.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\exchange.png"));

				if(factory != null) {
					deleteDataOnTableModel(dtmListEmployee);
					tblListTeam.setRowSelectionInterval(rowTeamSelected, rowTeamSelected);
					loadListEmployeeByIdTeam(dtmTeam.getValueAt(rowTeamSelected, 0).toString());
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
		if (o.equals(btnClean)) {
			cleanFactory();
			randomIdFactory();
		} else if (o.equals(btnChange)) {
			int rowSelected = tblTeam.getSelectedRow();
			if (rowSelected >= 0) {
				tblTeam.removeRowSelectionInterval(rowSelected, rowSelected);
			}
			btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
			btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			btnUpdateTeam.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			cleanTextFieldTeam();
			txtNameTeam.requestFocus();
			if (!txtIdFactory.getText().isEmpty()) {
				randomIdTeam();
			}
		} else if (o.equals(btnInsertTeam)) {
			boolean regexTeam = regexTeam();
			if (regexTeam) {
				if (btnInsertTeam.getIcon().toString().equals("images\\math-add-icon.png")) {
					int rowCountTeam = dtmTeam.getRowCount();
					boolean checkContainTeam = false;
					for (int i = 0; i < rowCountTeam; i++) {
						if (dtmTeam.getValueAt(i, 0).toString().equals(txtIdTeam.getText())) {
							checkContainTeam = true;
							JOptionPane.showMessageDialog(this, "Tổ đã được thêm vào table");
							break;
						}
					}

					if (!checkContainTeam) {
						TeamProducing team = new TeamProducing(txtIdTeam.getText(), txtNameTeam.getText(),
								null, txtIdFactory.getText());
						addRowListTeam(team, dtmTeam);
						JOptionPane.showMessageDialog(this, "Thêm tổ thành công!!!");
						randomIdTeam();
						txtNameTeam.requestFocus();
						List<entity.Factory> listFactory = Dao_Factory.getListFactory();
						if (listFactory.contains(Dao_Factory.searchFactoryByIdFactory(txtIdFactory.getText()))) {
							List<TeamProducing> listTeam = Dao_Factory.getListTeamByIdFactory(txtIdFactory.getText());
							if (dtmTeam.getRowCount() != listTeam.size()) {
								btnInsertFactory.setText("Sửa phân xưởng");
								btnInsertFactory.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
								btnUpdateFactory.setIcon(new ImageIcon("images\\math-add-icon.png"));
							}
						} else {
							randomIdTeam();
						}
					}

				} else {
					if (btnInsertTeam.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
						TeamProducing team = new TeamProducing(txtIdTeam.getText(), txtNameTeam.getText(),
								null, txtIdFactory.getText());
						if (Dao_Factory.updateTeam(team)) {
							JOptionPane.showMessageDialog(this, "Sửa tổ thành công!!!");
							deleteDataOnTableModel(dtmListTeam);
							loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
							deleteDataOnTableModel(dtmTeam);
							loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
						} else {
							JOptionPane.showMessageDialog(this, "Sửa tổ thất bại!!!");
						}
					} else {
						int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa Tổ này không?");
						if(confirm == JOptionPane.YES_OPTION) {
							Factory factory = Dao_Factory.searchFactoryByIdFactory(txtIdFactory.getText());
							if(factory != null) {
								if (Dao_Factory.deleteTeam(txtIdTeam.getText())
										&& !Dao_Factory.checkEmployeeOfTeam(txtIdTeam.getText())) {
									JOptionPane.showMessageDialog(this, "Xóa tổ thành công!!!");
									deleteDataOnTableModel(dtmListTeam);
									deleteDataOnTableModel(dtmTeam);
									loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
									loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
									btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
									btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
								} else {
									JOptionPane.showMessageDialog(this, "Tổ hiện đang có nhân viên làm việc, không thể xóa");
								}
							}
							else {
								int rowSelectedTblTeam = tblTeam.getSelectedRow();
								dtmTeam.removeRow(rowSelectedTblTeam);
								JOptionPane.showMessageDialog(this, "Xóa tổ thành công!!!");
							}
						}
						else if(confirm == JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(this, "Hủy xóa Tổ thành công!!!");
						}
					}

				}
			}
		} else if (o.equals(btnInsertFactory)) {
			boolean regexFactory = regexFactory();
			if (regexFactory) {
				if (btnInsertFactory.getText().equals("Thêm phân xưởng")) {
					entity.Factory factory = new entity.Factory(txtIdFactory.getText(), txtNameFactory.getText(),
							null);
					boolean checkContainFactory = checkContainFactory(factory);
					int rowCountTeam = dtmTeam.getRowCount();
					TeamProducing team = new TeamProducing();
					for (int i = 0; i < rowCountTeam; i++) {
						team = new TeamProducing(dtmTeam.getValueAt(i, 0).toString(),
								dtmTeam.getValueAt(i, 1).toString(), null,
								txtIdFactory.getText());
						ListTeam.add(team);
					}
					if (!checkContainFactory) {
						boolean insertFactory = Dao_Factory.insertFactory(factory);
						boolean insertListTeam = Dao_Factory.insertListTeam(ListTeam);
						if (insertFactory) {
							JOptionPane.showMessageDialog(this, "Thêm phân xưởng thành công!!!");
							ListTeam = new ArrayList<TeamProducing>();
							deleteDataOnTableModel(dtmListFactory);
							cleanFactory();
							randomIdFactory();
						}
					} else {
						JOptionPane.showMessageDialog(this, "Phân xưởng đã tồn tại!!!");
					}
				} else {
					int rowCountTeam = dtmTeam.getRowCount();
					TeamProducing team = new TeamProducing();
					for (int i = 0; i < rowCountTeam; i++) {
						team = new TeamProducing(dtmTeam.getValueAt(i, 0).toString(),
								dtmTeam.getValueAt(i, 1).toString(), dtmTeam.getValueAt(i, 2).toString(),
								txtIdFactory.getText());
						ListTeam.add(team);
					}
					boolean updateListTeam = Dao_Factory.updateListTeam(ListTeam, txtIdFactory.getText());
					entity.Factory factory = new entity.Factory(txtIdFactory.getText(), txtNameFactory.getText(),
							null);
					boolean updateFactory = Dao_Factory.updateFactory(factory);
					if (updateFactory == true && updateListTeam == true) {
						JOptionPane.showMessageDialog(this, "Sửa phân xưởng thành công!!!");
						ListTeam = new ArrayList<TeamProducing>();
						deleteDataOnTableModel(dtmListFactory);
						deleteDataOnTableModel(dtmListTeam);
						loadListFactory();
						loadListTeamByIdFactory(factory.getFactoryID(), dtmListTeam);
					}
				}
			}
		} else if (o.equals(btnDeleteFactory)) {
			if(tblListFactory.getSelectedRow() >= 0) {
				int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa Phân Xưởng này không?");
				if(confirm == JOptionPane.YES_OPTION) {
					if (Dao_Factory.deleteFactory(txtIdFactory.getText())) {
						JOptionPane.showMessageDialog(this, "Xóa phân xưởng thành công!!!");
						deleteDataOnTableModel(dtmListFactory);
						deleteDataOnTableModel(dtmListTeam);
						loadListFactory();
					} else {
						JOptionPane.showMessageDialog(this, "Phân xưởng hiện đang có tổ thực hiện, không thể xóa!!! ");
					}
				}
				else if(confirm == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this, "Hủy xóa Phân Xưởng thành công!!!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chọn phân xưởng cần xóa");
			}
		} else if (o.equals(btnDeleteTeam)) {
			if(tblListTeam.getSelectedRow() >= 0) {
				int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa Tổ này không?");
				if(confirm == JOptionPane.YES_OPTION) {
					if (Dao_Factory.deleteTeam(txtIdTeam.getText()) && !Dao_Factory.checkEmployeeOfTeam(txtIdTeam.getText())) {
						JOptionPane.showMessageDialog(this, "Xóa tổ thành công!!!");
						deleteDataOnTableModel(dtmListTeam);
						deleteDataOnTableModel(dtmTeam);
						loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
						loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
					} else {
						JOptionPane.showMessageDialog(this, "Tổ hiện đang có nhân viên làm việc, không thể xóa");
					}
				}
				else if(confirm == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this, "Hủy xóa Tổ thành công");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chọn tổ cần xóa");
			}
		} else if (o.equals(btnUpdateFactory)) {
			if (btnUpdateFactory.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
				btnInsertFactory.setText("Sửa phân xưởng");
				btnInsertFactory.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnUpdateFactory.setIcon(new ImageIcon("images\\math-add-icon.png"));
				if (!txtIdFactory.getText().isEmpty()) {
					randomIdTeam();
				}
			} else {
				btnInsertFactory.setText("Thêm phân xưởng");
				btnInsertFactory.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnUpdateFactory.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				cleanFactory();
				randomIdFactory();
			}
		} else if (o.equals(btnUpdateTeam)) {
			if (btnUpdateTeam.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
				btnInsertTeam.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnUpdateTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
			} else {
				btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnUpdateTeam.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			}
		} else if (o.equals(btnSearchIdFactory)) {
			if (txtSearchIdFactory.getText().isEmpty()) {
				dtmListFactory.setRowCount(0);
				loadListFactory();
			} else {
				entity.Factory factory = Dao_Factory.searchFactoryByIdFactory(txtSearchIdFactory.getText());
				if (factory != null) {
					dtmListFactory.setRowCount(0);
					addRowFactory(factory);
				} else {
					dtmListFactory.setRowCount(0);
				}
			}
		} else if (o.equals(btnSearchIdTeam)) {
			if (txtSearchIdTeam.getText().isEmpty()) {
				dtmListTeam.setRowCount(0);
				loadListTeam();
			} else {
				TeamProducing team = Dao_Factory.searchTeamByIdTeam(txtSearchIdTeam.getText());
				if (team != null) {
					dtmListTeam.setRowCount(0);
					addRowListTeam(team, dtmListTeam);
				} else {
					dtmListTeam.setRowCount(0);
				}
			}
		}

	}

	// Kiểm tra thông tin nhập của phân xưởng
	public boolean regexFactory() {
		String announce = "";
		String regexIdFactory = "^(PX)[0-9]{2}$";
		String regexNameFactory = "^([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+)$";
		if (txtIdFactory.getText().isEmpty() || txtNameFactory.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin phân xưởng";
		} else {
			if (!txtIdFactory.getText().matches(regexIdFactory)) {
				announce += "Mã phân xưởng phải bắt đầu bằng PX và theo sau là 2 chữ số \n";
				txtIdFactory.requestFocus();
				txtIdFactory.selectAll();
			}
			else if(!txtNameFactory.getText().matches(regexNameFactory)) {
				announce += "Tên phân xưởng không chứa kí tự đặc biệt \n";
				txtNameFactory.requestFocus();
				txtNameFactory.selectAll();
			}
		}
		if (announce.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, announce);
			return false;
		}
	}

	// Kiểm tra thông tin nhập của tổ
	public boolean regexTeam() {
		String announce = "";
		String regexIdTeam = "^" + txtIdFactory.getText() + "[0-9]{2}$";
		String regexNameTeam = "^([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+)$";
		if (txtIdTeam.getText().isEmpty() || txtNameTeam.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin tổ";
		} else {
			if (!txtIdTeam.getText().matches(regexIdTeam)) {
				announce += "Mã tổ phải bắt đầu bằng mã phân xưởng và theo sau 2 chữ số \n";
				txtIdTeam.requestFocus();
				txtIdTeam.selectAll();
			}
			else if(!txtNameTeam.getText().matches(regexNameTeam)) {
				announce += "Tên tổ không chứa kí tự đặc biệt \n";
				txtNameTeam.requestFocus();
				txtNameTeam.selectAll();
			}
		}
		if (announce.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, announce);
			return false;
		}

	}

}
