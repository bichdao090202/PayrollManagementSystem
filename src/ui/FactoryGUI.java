package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import model.FactoryDAO;
import entity.Worker;
import entity.TeamProducing;
import javax.swing.ImageIcon;

public class FactoryGUI extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdFactory;
	private JTextField txtNameFactory;
	private JTextField txtIdLeadFactory;
	private JTextField txtIdTeam;
	private JTextField txtNameTeam;
	private JTextField txtIdLeadTeam;
	private JTextField txtSearchIdFactory;
	private JTable tblListFactory;
	private JTextField txtSearchIdTeam;
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
	private FactoryDAO Dao_Factory = new FactoryDAO();
	private List<TeamProducing> ListTeam = new ArrayList<TeamProducing>();
	private JButton btnChange;
	private JButton btnClean;

//	public Component getView() {
	public FactoryGUI() {
		setSize(1200, 690);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0,140,140));

		JPanel pnlFactory = new JPanel();
		pnlFactory.setBackground(new Color(0,140,140));
		pnlFactory.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Phân xưởng", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.WHITE));
		pnlFactory.setBounds(10, 10, 460, 191);
		getContentPane().add(pnlFactory);
		pnlFactory.setLayout(null);

		JLabel lblIdFactory = new JLabel("Mã phân xưởng :");
		lblIdFactory.setForeground(Color.WHITE);
		lblIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdFactory.setBounds(37, 35, 87, 13);
		pnlFactory.add(lblIdFactory);

		txtIdFactory = new JTextField();
		txtIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdFactory.setBounds(162, 35, 245, 19);
		pnlFactory.add(txtIdFactory);
		txtIdFactory.setColumns(10);

		JLabel lblNameFactory = new JLabel("Tên phân xưởng :");
		lblNameFactory.setForeground(Color.WHITE);
		lblNameFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameFactory.setBounds(37, 75, 100, 13);
		pnlFactory.add(lblNameFactory);

		txtNameFactory = new JTextField();
		txtNameFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameFactory.setColumns(10);
		txtNameFactory.setBounds(162, 75, 245, 19);
		pnlFactory.add(txtNameFactory);
		txtNameFactory.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (Dao_Factory.searchFactoryByIdFactory(txtIdFactory.getText()) == null && dtmTeam.getRowCount() == 0
						&& txtNameTeam.getText().isEmpty() && txtIdLeadTeam.getText().isEmpty()
						&& !txtIdFactory.getText().isEmpty()) {
					randomIdTeam();

				}
			}

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		JLabel lblIdLeadFactory = new JLabel("Mã quản đốc :");
		lblIdLeadFactory.setForeground(Color.WHITE);
		lblIdLeadFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdLeadFactory.setBounds(37, 115, 87, 13);
		pnlFactory.add(lblIdLeadFactory);

		txtIdLeadFactory = new JTextField();
		txtIdLeadFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdLeadFactory.setColumns(10);
		txtIdLeadFactory.setBounds(162, 115, 245, 19);
		pnlFactory.add(txtIdLeadFactory);

		btnClean = new JButton("");
		btnClean.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClean.setFocusPainted(false);
		btnClean.setBounds(37, 149, 50, 21);
		pnlFactory.add(btnClean);

		JPanel pnlTeam = new JPanel();
		pnlTeam.setBackground(new Color(0,140,140));
		pnlTeam.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Tổ", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.WHITE));
		pnlTeam.setBounds(513, 10, 485, 191);
		getContentPane().add(pnlTeam);
		pnlTeam.setLayout(null);

		JLabel lblIdTeam = new JLabel("Mã tổ :");
		lblIdTeam.setForeground(Color.WHITE);
		lblIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdTeam.setBounds(40, 32, 53, 13);
		pnlTeam.add(lblIdTeam);

		txtIdTeam = new JTextField();
		txtIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdTeam.setColumns(10);
		txtIdTeam.setBounds(96, 29, 106, 19);
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
		lblNameTeam.setForeground(Color.WHITE);
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
			}/* from w ww . j a va2 s. c o m */

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		JLabel lblIdLeadTeam = new JLabel("Mã tổ trưởng :");
		lblIdLeadTeam.setForeground(Color.WHITE);
		lblIdLeadTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdLeadTeam.setBounds(237, 32, 87, 13);
		pnlTeam.add(lblIdLeadTeam);

		txtIdLeadTeam = new JTextField();
		txtIdLeadTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdLeadTeam.setColumns(10);
		txtIdLeadTeam.setBounds(330, 29, 106, 19);
		pnlTeam.add(txtIdLeadTeam);
		txtIdLeadTeam.addFocusListener((FocusListener) new FocusListener() {
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
		tblTeam.setBackground(new Color(0,140,140));
		tblTeam.setForeground(Color.WHITE);
		tblTeam.setGridColor(Color.WHITE);
		tblTeam.setBorder(new LineBorder(Color.WHITE));
		JTableHeader tblHeaderTeam = tblTeam.getTableHeader();
		tblHeaderTeam.setBackground(new Color(14,85,78));
		tblHeaderTeam.setForeground(Color.WHITE);
		tblTeam.setModel(
				dtmTeam = new DefaultTableModel(new Object[][] {}, new String[] { "Mã tổ", "Tên tổ", "Mã tổ trưởng" }) {
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
		pnlListFactories.setBackground(new Color(0,140,140));
		pnlListFactories.setBounds(-10, 211, 1186, 442);
		getContentPane().add(pnlListFactories);
		pnlListFactories.setLayout(null);

		JPanel pnlListFactory = new JPanel();
		pnlListFactory.setBackground(new Color(0,140,140));
		pnlListFactory.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Danh sách phân xưởng",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlListFactory.setBounds(10, 10, 580, 432);
		pnlListFactories.add(pnlListFactory);
		pnlListFactory.setLayout(null);

		JLabel lblNoteIdFactory = new JLabel("Nhập mã phân xưởng :");
		lblNoteIdFactory.setForeground(Color.WHITE);
		lblNoteIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdFactory.setBounds(25, 17, 120, 15);
		pnlListFactory.add(lblNoteIdFactory);

		txtSearchIdFactory = new JTextField();
		txtSearchIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdFactory.setColumns(10);
		txtSearchIdFactory.setBounds(153, 16, 134, 19);
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
		tblListFactory.setBackground(new Color(0,140,140));
		tblListFactory.setForeground(Color.WHITE);
		tblListFactory.setGridColor(Color.WHITE);
		tblListFactory.setRowHeight(25);
		tblListFactory.setBorder(new LineBorder(Color.WHITE));
		JTableHeader tblHeaderListFactory = tblListFactory.getTableHeader();
		tblHeaderListFactory.setBackground(new Color(14,85,78));
		tblHeaderListFactory.setForeground(Color.WHITE);
		tblHeaderListFactory.setPreferredSize(new Dimension(100, 30));
		tblHeaderListFactory.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblListFactory.setModel(dtmListFactory = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã phân xưởng", "Tên phân xưởng", "Mã quản đốc" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrListFactory.setViewportView(tblListFactory);

		JPanel pnlListTeam = new JPanel();
		pnlListTeam.setBackground(new Color(0,140,140));
		pnlListTeam.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Danh sách tổ", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.WHITE));
		pnlListTeam.setBounds(596, 10, 580, 211);
		pnlListFactories.add(pnlListTeam);
		pnlListTeam.setLayout(null);

		JScrollPane scrListTeam = new JScrollPane();
		scrListTeam.setBounds(10, 57, 560, 144);
		pnlListTeam.add(scrListTeam);

		tblListTeam = new JTable();
		tblListTeam.setBackground(new Color(0,140,140));
		tblListTeam.setForeground(Color.WHITE);
		tblListTeam.setBorder(new LineBorder(Color.WHITE));
		tblListTeam.setGridColor(Color.WHITE);
		tblListTeam.setRowHeight(25);
		JTableHeader tblHeaderListTeam = tblListTeam.getTableHeader();
		tblHeaderListTeam.setBackground(new Color(14,85,78));
		tblHeaderListTeam.setForeground(Color.WHITE);
		tblHeaderListTeam.setPreferredSize(new Dimension(100, 30));
		tblHeaderListTeam.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblListTeam.setModel(dtmListTeam = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã tổ", "Tên tổ", "Mã tổ trưởng" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrListTeam.setViewportView(tblListTeam);

		JLabel lblNoteIdTeam = new JLabel("Nhập mã tổ :");
		lblNoteIdTeam.setForeground(Color.WHITE);
		lblNoteIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdTeam.setBounds(25, 19, 70, 15);
		pnlListTeam.add(lblNoteIdTeam);

		txtSearchIdTeam = new JTextField();
		txtSearchIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdTeam.setColumns(10);
		txtSearchIdTeam.setBounds(101, 18, 186, 19);
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
		pnlListEmployeeByTeam.setBackground(new Color(0,140,140));
		pnlListEmployeeByTeam.setLayout(null);
		pnlListEmployeeByTeam.setBorder(new TitledBorder(new LineBorder(Color.WHITE), "Danh sách nhân viên",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlListEmployeeByTeam.setBounds(596, 231, 580, 201);
		pnlListFactories.add(pnlListEmployeeByTeam);

		JScrollPane scrListEmployeeByTeam = new JScrollPane();
		scrListEmployeeByTeam.setBounds(10, 20, 560, 171);
		pnlListEmployeeByTeam.add(scrListEmployeeByTeam);

		tblEmployeeByTeam = new JTable();
		tblEmployeeByTeam.setBackground(new Color(0,140,140));
		tblEmployeeByTeam.setForeground(Color.WHITE);
		tblEmployeeByTeam.setBorder(new LineBorder(Color.WHITE));
		tblEmployeeByTeam.setGridColor(Color.WHITE);
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

		tblListFactory.addMouseListener(this);
		tblListTeam.addMouseListener(this);
		tblTeam.addMouseListener(this);
		tblEmployeeByTeam.addMouseListener(this);
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
		btnChange.setFocusPainted(false);

		loadListFactory();
		randomIdFactory();
		txtIdFactory.setEditable(false);
		txtIdTeam.setEditable(false);

//		return getContentPane();
	}

	public void loadListFactory() {
		List<entity.Factory> listFactory;
		listFactory = Dao_Factory.getListFactory();
		for (entity.Factory factory : listFactory) {
			addRowFactory(factory);
		}
	}

	public void loadListTeamByIdFactory(String idFactory, DefaultTableModel dtm) {
		List<TeamProducing> listTeam;
		listTeam = Dao_Factory.getListTeamByIdFactory(idFactory);
		for (TeamProducing team : listTeam) {
			addRowListTeam(team, dtm);
		}
	}

	public void loadListEmployeeByIdTeam(String idTeam) {
		List<Worker> listEmployee;
		listEmployee = Dao_Factory.getListEmployeeByIdTeam(idTeam);
		for (Worker employee : listEmployee) {
			addRowEmployee(employee);
		}
	}

	public void addRowListTeam(TeamProducing team, DefaultTableModel dtm) {
		String[] row = { team.getTeamID(), team.getName(), team.getLeaderID(), team.getFactoryID() };
		dtm.addRow(row);
	}

	public void addRowFactory(entity.Factory factory) {
		String[] row = { factory.getFactoryID(), factory.getName(), factory.getHeadForemanID() };
		dtmListFactory.addRow(row);
	}

	public void addRowEmployee(Worker employee) {
		String[] row = { employee.getEmployeeID(), employee.getName(), employee.isGender() ? "Nam" : "Nữ",
				employee.getBirthday().toString(), employee.getPhone() };
		dtmListEmployee.addRow(row);
	}

	public boolean checkTeamOnTable(String idProcedure) {
		int rowCount = dtmTeam.getRowCount();
		if (rowCount > 0) {
			for (int i = 0; i < rowCount; i++) {
				if (dtmTeam.getValueAt(i, 0).toString().equals(idProcedure)) {
					return true;
				}
			}
		}
		return false;
	}

	public void randomIdFactory() {
		int order = (int) (Math.random() * (99 - 1 + 1) + 1);
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

	public void randomIdTeam() {
		int order = (int) (Math.random() * (99 - 1 + 1) + 1);
		String idTeam = "";
		if (order >= 10) {
			idTeam = txtIdFactory.getText() + order + "";
		} else {
			idTeam = txtIdFactory.getText() + "0" + order;
		}

		boolean test = true;
		while (Dao_Factory.searchTeamByIdTeam(idTeam) == null && !checkTeamOnTable(idTeam) && test) {
			cleanTextFieldTeam();
			txtIdTeam.setText(idTeam);
			test = false;
			break;
		}
		if (test) {
			randomIdTeam();
		}
	}

	private void deleteDataOnTableModel(DefaultTableModel dtm) {
		dtm.setRowCount(0);
	}

	private boolean checkContainFactory(entity.Factory factory) {
		List<entity.Factory> listFactory = Dao_Factory.getListFactory();
		if (listFactory.contains(factory)) {
			return true;
		}
		return false;
	}

	public void cleanTextFieldTeam() {
		txtIdTeam.setText("");
		txtNameTeam.setText("");
		txtIdLeadTeam.setText("");
	}

	public void cleanFactory() {
		deleteDataOnTableModel(dtmListFactory);
		deleteDataOnTableModel(dtmListTeam);
		deleteDataOnTableModel(dtmTeam);
		deleteDataOnTableModel(dtmListEmployee);
		loadListFactory();
		txtIdFactory.setText("");
		txtNameFactory.setText("");
		txtIdLeadFactory.setText("");
		cleanTextFieldTeam();
		txtNameFactory.requestFocus();
		btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnInsertFactory.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnUpdateFactory.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnInsertFactory.setText("Thêm phân xưởng");
	}

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
				txtIdLeadFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 2).toString());

				loadListTeamByIdFactory(dtmListFactory.getValueAt(rowFactorySelected, 0).toString(), dtmListTeam);
				loadListTeamByIdFactory(dtmListFactory.getValueAt(rowFactorySelected, 0).toString(), dtmTeam);
				txtIdTeam.setText(dtmTeam.getValueAt(0, 0).toString());
				txtNameTeam.setText(dtmTeam.getValueAt(0, 1).toString());
				txtIdLeadTeam.setText(dtmTeam.getValueAt(0, 2).toString());
				resetTeam();
			} else if (listTeam.size() <= 0) {
				txtIdFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 0).toString());
				txtNameFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 1).toString());
				txtIdLeadFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 2).toString());
				cleanTextFieldTeam();
				txtSearchIdTeam.setText("");
			}
		} else if (o.equals(tblListTeam)) {
			int rowTeamSelected = tblListTeam.getSelectedRow();
			int rowFactorySelected = tblListFactory.getSelectedRow();
			if (rowTeamSelected >= 0) {
				txtIdTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				txtNameTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 1).toString());
				txtIdLeadTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 2).toString());

				deleteDataOnTableModel(dtmListEmployee);
				loadListEmployeeByIdTeam(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				deleteDataOnTableModel(dtmTeam);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);

				btnInsertTeam.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			}

		} else if (o.equals(tblTeam)) {
			int rowTeamSelected = tblTeam.getSelectedRow();
			if (rowTeamSelected >= 0) {
				txtIdTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				txtNameTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 1).toString());
				txtIdLeadTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 2).toString());
				btnInsertTeam.setIcon(new ImageIcon("images\\Close-2-icon.png"));
				btnUpdateTeam.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\exchange.png"));

				deleteDataOnTableModel(dtmListEmployee);
				tblListTeam.setRowSelectionInterval(rowTeamSelected, rowTeamSelected);
				loadListEmployeeByIdTeam(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
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
								txtIdLeadTeam.getText(), txtIdFactory.getText());
						addRowListTeam(team, dtmTeam);
						JOptionPane.showMessageDialog(this, "Thêm tổ thành công!!!");
						randomIdTeam();
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
								txtIdLeadTeam.getText(), txtIdFactory.getText());
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
						if (Dao_Factory.deleteTeam(txtIdTeam.getText())
								&& !Dao_Factory.checkEmployeeOfTeam(txtIdTeam.getText())) {
							JOptionPane.showMessageDialog(this, "Xóa tổ thành công!!!");
							txtIdTeam.setEditable(true);
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

				}
			}
		} else if (o.equals(btnInsertFactory)) {
			boolean regexFactory = regexFactory();
			System.out.println(regexFactory);
			if (regexFactory) {
				if (btnInsertFactory.getText().equals("Thêm phân xưởng")) {
					entity.Factory factory = new entity.Factory(txtIdFactory.getText(), txtNameFactory.getText(),
							txtIdLeadFactory.getText());
					boolean checkContainFactory = checkContainFactory(factory);
					int rowCountTeam = dtmTeam.getRowCount();
					TeamProducing team = new TeamProducing();
					for (int i = 0; i < rowCountTeam; i++) {
						team = new TeamProducing(dtmTeam.getValueAt(i, 0).toString(),
								dtmTeam.getValueAt(i, 1).toString(), dtmTeam.getValueAt(i, 2).toString(),
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
							txtIdLeadFactory.getText());
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
			if (Dao_Factory.deleteFactory(txtIdFactory.getText())) {
				JOptionPane.showMessageDialog(this, "Xóa phân xưởng thành công!!!");
				deleteDataOnTableModel(dtmListFactory);
				deleteDataOnTableModel(dtmListTeam);
				loadListFactory();
			} else {
				JOptionPane.showMessageDialog(this, "Phân xưởng hiện đang có tổ thực hiện, không thể xóa!!! ");
			}
		} else if (o.equals(btnDeleteTeam)) {
			if (Dao_Factory.deleteTeam(txtIdTeam.getText()) && !Dao_Factory.checkEmployeeOfTeam(txtIdTeam.getText())) {
				JOptionPane.showMessageDialog(this, "Xóa tổ thành công!!!");
				deleteDataOnTableModel(dtmListTeam);
				deleteDataOnTableModel(dtmTeam);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
			} else {
				JOptionPane.showMessageDialog(this, "Tổ hiện đang có nhân viên làm việc, không thể xóa");
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
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
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

	public boolean regexFactory() {
		String announce = "";
		String regexIdFactory = "^(PX)[0-9]{2}$";
		String regexIdLeadFactory = "^(NV)[0-9]{5}$";
		if (txtIdFactory.getText().isEmpty() || txtNameFactory.getText().isEmpty()
				|| txtIdLeadFactory.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin phân xưởng";
		} else {
			if (!txtIdFactory.getText().matches(regexIdFactory)) {
				announce += "Mã phân xưởng phải bắt đầu bằng PX và theo sau là 2 chữ số \n";
				txtIdFactory.requestFocus();
				txtIdFactory.selectAll();
			} else if (!txtIdLeadFactory.getText().matches(regexIdLeadFactory)) {
				announce += "Mã quản đốc phải bắt đầu bằng NV và theo sau là 5 chữ số \n";
				txtIdLeadFactory.requestFocus();
				txtIdLeadFactory.selectAll();
			}
		}
		if (announce.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, announce);
			return false;
		}
	}

	public boolean regexTeam() {
		String announce = "";
		String regexIdTeam = "^" + txtIdFactory.getText() + "[0-9]{2}$";
		String regexIdLeadTeam = "^(NV)[0-9]{5}$";
		if (txtIdTeam.getText().isEmpty() || txtNameTeam.getText().isEmpty() || txtIdLeadTeam.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin tổ";
		} else {
			if (!txtIdTeam.getText().matches(regexIdTeam)) {
				announce += "Mã tổ phải bắt đầu bằng mã phân xưởng và theo sau 2 chữ số \n";
				txtIdTeam.requestFocus();
				txtIdTeam.selectAll();
			} else if (!txtIdLeadTeam.getText().matches(regexIdLeadTeam)) {
				announce += "Mã tổ trưởng phải bắt đầu bằng NV và theo sau là 5 chữ số \n";
				txtIdLeadTeam.requestFocus();
				txtIdLeadTeam.selectAll();
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
