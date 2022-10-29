package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;

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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Worker;
import model.FactoryDAO;
import entity.TeamProducing;
import javax.swing.ImageIcon;

public class FactoryGUI extends JFrame implements ActionListener, MouseListener {
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
	public FactoryGUI() {
		setSize(1200, 690);
		getContentPane().setLayout(null);
		
		JPanel pnlFactory = new JPanel();
		pnlFactory.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ph\u00E2n x\u01B0\u1EDFng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlFactory.setBounds(10, 10, 460, 191);
		getContentPane().add(pnlFactory);
		pnlFactory.setLayout(null);
		
		JLabel lblIdFactory = new JLabel("Mã phân xưởng:");
		lblIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdFactory.setBounds(37, 35, 87, 13);
		pnlFactory.add(lblIdFactory);
		
		txtIdFactory = new JTextField();
		txtIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdFactory.setBounds(162, 35, 245, 19);
		pnlFactory.add(txtIdFactory);
		txtIdFactory.setColumns(10);
		
		JLabel lblNameFactory = new JLabel("Tên phân xưởng:");
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
	            if(
	            		Dao_Factory.searchFactoryByIdFactory(txtIdFactory.getText()) == null &&
	            		dtmTeam.getRowCount() == 0 && txtNameTeam.getText().isEmpty() &&
	            		txtIdLeadTeam.getText().isEmpty() && !txtIdFactory.getText().isEmpty()
	            ) {
	            	randomIdTeam();
	            	
	            }
	         }

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		JLabel lblIdLeadFactory = new JLabel("Mã quản đốc:");
		lblIdLeadFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdLeadFactory.setBounds(37, 115, 87, 13);
		pnlFactory.add(lblIdLeadFactory);
		
		txtIdLeadFactory = new JTextField();
		txtIdLeadFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdLeadFactory.setColumns(10);
		txtIdLeadFactory.setBounds(162, 115, 245, 19);
		pnlFactory.add(txtIdLeadFactory);
		
		btnClean = new JButton("");
		btnClean.setIcon(new ImageIcon("img\\Clear-icon.png"));
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClean.setFocusPainted(false);
		btnClean.setBounds(37, 149, 50, 21);
		pnlFactory.add(btnClean);
		
		JPanel pnlTeam = new JPanel();
		pnlTeam.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u1ED5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTeam.setBounds(513, 10, 485, 191);
		getContentPane().add(pnlTeam);
		pnlTeam.setLayout(null);
		
		JLabel lblIdTeam = new JLabel("Mã tổ:");
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
	            if(btnInsertTeam.getIcon().toString().equals("img\\Close-2-icon.png")) {
	            	btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
//	            	btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
	            }
	         }/*from w  ww  .  j a va2 s. c o m*/

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		JLabel lblNameTeam = new JLabel("Tên tổ:");
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
	            if(btnInsertTeam.getIcon().toString().equals("img\\Close-2-icon.png")) {
	            	btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
//	            	btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
	            }
	         }/*from w  ww  .  j a va2 s. c o m*/

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		JLabel lblIdLeadTeam = new JLabel("Mã tổ trưởng:");
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
	            if(btnInsertTeam.getIcon().toString().equals("img\\Close-2-icon.png")) {
	            	btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
//	            	btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
	            }
	         }/*from w  ww  .  j a va2 s. c o m*/

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		btnInsertTeam = new JButton("");
		btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
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
		tblTeam.setModel(dtmTeam = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 t\u1ED5", "T\u00EAn t\u1ED5", "M\u00E3 t\u1ED5 tr\u01B0\u1EDFng"
			}
		)
			{
				public boolean isCellEditable(int rowIndex, int columnIndex) {
				    return false;
				}
			}
		);
		scrTeam.setViewportView(tblTeam);
		
		btnInsertFactory = new JButton("Thêm phân xưởng");
		btnInsertFactory.setIcon(new ImageIcon("img\\math-add-icon.png"));
		btnInsertFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertFactory.setBounds(1008, 90, 168, 40);
		getContentPane().add(btnInsertFactory);
		
		JPanel pnlListFactories = new JPanel();
		pnlListFactories.setBounds(-10, 211, 1186, 442);
		getContentPane().add(pnlListFactories);
		pnlListFactories.setLayout(null);
		
		JPanel pnlListFactory = new JPanel();
		pnlListFactory.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch ph\u00E2n x\u01B0\u1EDFng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlListFactory.setBounds(10, 10, 580, 432);
		pnlListFactories.add(pnlListFactory);
		pnlListFactory.setLayout(null);
		
		JLabel lblNoteIdFactory = new JLabel("Nhập mã phân xưởng:");
		lblNoteIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdFactory.setBounds(25, 17, 120, 15);
		pnlListFactory.add(lblNoteIdFactory);
		
		txtSearchIdFactory = new JTextField();
		txtSearchIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdFactory.setColumns(10);
		txtSearchIdFactory.setBounds(153, 16, 134, 19);
		pnlListFactory.add(txtSearchIdFactory);
		
		btnSearchIdFactory = new JButton("");
		btnSearchIdFactory.setIcon(new ImageIcon("img\\Zoom-icon.png"));
		btnSearchIdFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdFactory.setBounds(297, 15, 70, 21);
		pnlListFactory.add(btnSearchIdFactory);
		
		btnDeleteFactory = new JButton("");
		btnDeleteFactory.setIcon(new ImageIcon("img\\Close-2-icon.png"));
		btnDeleteFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteFactory.setBounds(382, 15, 70, 21);
		pnlListFactory.add(btnDeleteFactory);
		
		btnUpdateFactory = new JButton("");
		btnUpdateFactory.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
		btnUpdateFactory.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateFactory.setBounds(462, 15, 70, 21);
		pnlListFactory.add(btnUpdateFactory);
		
		JScrollPane scrListFactory = new JScrollPane();
		scrListFactory.setBounds(10, 57, 560, 365);
		pnlListFactory.add(scrListFactory);
		
		tblListFactory = new JTable();
		tblListFactory.setModel(dtmListFactory = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 ph\u00E2n x\u01B0\u1EDFng", "T\u00EAn ph\u00E2n x\u01B0\u1EDFng", "M\u00E3 qu\u1EA3 \u0111\u1ED1c"
			}
		)
			{
				public boolean isCellEditable(int rowIndex, int columnIndex) {
				    return false;
				}
			}
		);
		scrListFactory.setViewportView(tblListFactory);
		
		JPanel pnlListTeam = new JPanel();
		pnlListTeam.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch t\u1ED5", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlListTeam.setBounds(596, 10, 580, 211);
		pnlListFactories.add(pnlListTeam);
		pnlListTeam.setLayout(null);
		
		JScrollPane scrListTeam = new JScrollPane();
		scrListTeam.setBounds(10, 57, 560, 144);
		pnlListTeam.add(scrListTeam);
		
		tblListTeam = new JTable();
		tblListTeam.setModel(dtmListTeam = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 t\u1ED5", "T\u00EAn t\u1ED5", "M\u00E3 t\u1ED5 tr\u01B0\u1EDFng"
			}
		)
			{
				public boolean isCellEditable(int rowIndex, int columnIndex) {
				    return false;
				}
			}
		);
		scrListTeam.setViewportView(tblListTeam);
		
		JLabel lblNoteIdTeam = new JLabel("Nhập mã tổ:");
		lblNoteIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdTeam.setBounds(25, 19, 70, 15);
		pnlListTeam.add(lblNoteIdTeam);
		
		txtSearchIdTeam = new JTextField();
		txtSearchIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchIdTeam.setColumns(10);
		txtSearchIdTeam.setBounds(101, 18, 186, 19);
		pnlListTeam.add(txtSearchIdTeam);
		
		btnSearchIdTeam = new JButton("");
		btnSearchIdTeam.setIcon(new ImageIcon("img\\Zoom-icon.png"));
		btnSearchIdTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdTeam.setBounds(297, 17, 70, 21);
		pnlListTeam.add(btnSearchIdTeam);
		
		btnDeleteTeam = new JButton("");
		btnDeleteTeam.setIcon(new ImageIcon("img\\Close-2-icon.png"));
		btnDeleteTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteTeam.setBounds(382, 17, 70, 21);
		pnlListTeam.add(btnDeleteTeam);
		
		btnUpdateTeam = new JButton("");
		btnUpdateTeam.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
		btnUpdateTeam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateTeam.setBounds(462, 17, 70, 21);
		pnlListTeam.add(btnUpdateTeam);
		
		JPanel pnlListEmployeeByTeam = new JPanel();
		pnlListEmployeeByTeam.setLayout(null);
		pnlListEmployeeByTeam.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlListEmployeeByTeam.setBounds(596, 231, 580, 201);
		pnlListFactories.add(pnlListEmployeeByTeam);
		
		JScrollPane scrListEmployeeByTeam = new JScrollPane();
		scrListEmployeeByTeam.setBounds(10, 20, 560, 171);
		pnlListEmployeeByTeam.add(scrListEmployeeByTeam);
		
		tblEmployeeByTeam = new JTable();
		tblEmployeeByTeam.setModel(dtmListEmployee = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Gi\u1EDBi t\u00EDnh", "Ng\u00E0y sinh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i"
			}
		)
			{
				public boolean isCellEditable(int rowIndex, int columnIndex) {
				    return false;
				}
			}
		);
		scrListEmployeeByTeam.setViewportView(tblEmployeeByTeam);
		
		btnChange = new JButton("");
		btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
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
		String[] row = { factory.getFactoryID(), factory.getName(), factory.getHeadForemanID()};
		dtmListFactory.addRow(row);
	}
	
	public void addRowEmployee(Worker employee) {
		String[] row = { employee.getEmployeeID(), employee.getName(), employee.isGender()?"Nam":"Nữ", employee.getBirthday().toString(), employee.getPhone()};
		dtmListEmployee.addRow(row);
	}
	
	public boolean checkTeamOnTable(String idProcedure) {
		int rowCount = dtmTeam.getRowCount();
		if(rowCount > 0) {
			for(int i = 0; i < rowCount; i++) {
				if(dtmTeam.getValueAt(i, 0).toString().equals(idProcedure)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void randomIdFactory() {
		int order = (int)(Math.random()*(99-1+1)+1);
		String idFactory = "PX";
		if(order < 10) {
			idFactory += "0"+order;
		}
		else {
			idFactory += order;
		}
    	boolean test = true;
    	while(Dao_Factory.searchFactoryByIdFactory(idFactory) == null && test) {
    		cleanFactory();
    		txtIdFactory.setText(idFactory);
    		test = false;
    		break;
    	}
    	if(test) {
    		randomIdFactory();
    	}
	}
	
	public void randomIdTeam() {
		int order = (int)(Math.random()*(99-1+1)+1);
		String idTeam = "";
		if(order >= 10) {
			idTeam = txtIdFactory.getText() + order+"";
		}
		else {
			idTeam = txtIdFactory.getText() + "0"+order;
		}
    	
		boolean test = true;
    	while(Dao_Factory.searchTeamByIdTeam(idTeam) == null &&
    			!checkTeamOnTable(idTeam) && test
    		) {
    		cleanTextFieldTeam();
    		txtIdTeam.setText(idTeam);
    		test = false;
    		break;
    	}
    	if(test) {
    		randomIdTeam();
    	}
	}
	
	private void deleteDataOnTableModel(DefaultTableModel dtm) {
		dtm.setRowCount(0);
	}
	
	private boolean checkContainFactory(entity.Factory factory) {
		List<entity.Factory> listFactory = Dao_Factory.getListFactory();
		if(listFactory.contains(factory)) {
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
		btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
		btnInsertFactory.setIcon(new ImageIcon("img\\math-add-icon.png"));
		btnUpdateFactory.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
		btnInsertFactory.setText("Thêm phân xưởng");
	}
	
	public void resetTeam() {
		btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
		btnUpdateTeam.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
	}
	
	public static void main(String[] args) {
		new FactoryGUI().setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(tblListFactory)) {
			deleteDataOnTableModel(dtmListTeam);
			deleteDataOnTableModel(dtmTeam);
			deleteDataOnTableModel(dtmListEmployee);
			btnInsertFactory.setText("Thêm phân xưởng");
			btnInsertFactory.setIcon(new ImageIcon("img\\math-add-icon.png"));
			btnUpdateFactory.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
			String idFactory;
			int rowFactorySelected = tblListFactory.getSelectedRow();
			idFactory = dtmListFactory.getValueAt(rowFactorySelected, 0).toString();
			List<TeamProducing> listTeam = Dao_Factory.getListTeamByIdFactory(idFactory);
			if(rowFactorySelected >= 0 && listTeam.size() > 0) {
				txtIdFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 0).toString());
				txtNameFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 1).toString());
				txtIdLeadFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 2).toString());
				
				loadListTeamByIdFactory(dtmListFactory.getValueAt(rowFactorySelected, 0).toString(), dtmListTeam);
				loadListTeamByIdFactory(dtmListFactory.getValueAt(rowFactorySelected, 0).toString(), dtmTeam);
				txtIdTeam.setText(dtmTeam.getValueAt(0, 0).toString());
				txtNameTeam.setText(dtmTeam.getValueAt(0, 1).toString());
				txtIdLeadTeam.setText(dtmTeam.getValueAt(0, 2).toString());
				resetTeam();
			}
			else if(listTeam.size() <= 0) {
				txtIdFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 0).toString());
				txtNameFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 1).toString());
				txtIdLeadFactory.setText(dtmListFactory.getValueAt(rowFactorySelected, 2).toString());
				cleanTextFieldTeam();
				txtSearchIdTeam.setText("");
			}
		}
		else if(o.equals(tblListTeam)) {
			int rowTeamSelected = tblListTeam.getSelectedRow();
			int rowFactorySelected = tblListFactory.getSelectedRow();
			if(rowTeamSelected >= 0) {
				txtIdTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				txtNameTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 1).toString());
				txtIdLeadTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 2).toString());
				
				deleteDataOnTableModel(dtmListEmployee);
				loadListEmployeeByIdTeam(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				deleteDataOnTableModel(dtmTeam);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
				
				btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
				btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
			}
			
		}
		else if(o.equals(tblTeam)) {
			int rowTeamSelected = tblTeam.getSelectedRow();
			if(rowTeamSelected >= 0) {
				txtIdTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
				txtNameTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 1).toString());
				txtIdLeadTeam.setText(dtmListTeam.getValueAt(rowTeamSelected, 2).toString());
				btnInsertTeam.setIcon(new ImageIcon("img\\Close-2-icon.png"));
				btnUpdateTeam.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("img\\exchange.png"));
				
				deleteDataOnTableModel(dtmListEmployee);
				tblListTeam.setRowSelectionInterval(rowTeamSelected, rowTeamSelected);
				loadListEmployeeByIdTeam(dtmListTeam.getValueAt(rowTeamSelected, 0).toString());
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnClean)) {
			cleanFactory();
			randomIdFactory();
		}
		else if(o.equals(btnChange)) {
			int rowSelected = tblTeam.getSelectedRow();
			if(rowSelected >= 0) {
				tblTeam.removeRowSelectionInterval(rowSelected, rowSelected);
			}
//			if(btnChange.getIcon().toString().equals("img\\Clear-icon.png")) {
//				deleteDataOnTableModel(dtmTeam);
//			}
			btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
			btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
			btnUpdateTeam.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
			cleanTextFieldTeam();
			txtNameTeam.requestFocus();
			if(!txtIdFactory.getText().isEmpty()) {
				randomIdTeam();
			}
		}
		else if(o.equals(btnInsertTeam)) {
//			boolean regexProcedure = regexProcedure();
			boolean regexProcedure = true;
			if(regexProcedure) {
				if(btnInsertTeam.getIcon().toString().equals("img\\math-add-icon.png")) {
					int rowCountTeam = dtmTeam.getRowCount();
					boolean checkContainTeam = false;
					for(int i = 0; i < rowCountTeam; i++) {
						if(dtmTeam.getValueAt(i, 0).toString().equals(txtIdTeam.getText())) {
							checkContainTeam = true;
							JOptionPane.showMessageDialog(this, "Tổ đã được thêm vào table");
							break;
						}
					}
					
					if(!checkContainTeam) {
						TeamProducing team = new TeamProducing(txtIdTeam.getText(), txtNameTeam.getText(), txtIdLeadTeam.getText(), txtIdFactory.getText());
						addRowListTeam(team, dtmTeam);
						JOptionPane.showMessageDialog(this, "Thêm tổ thành công!!!");
						List<entity.Factory> listFactory = Dao_Factory.getListFactory();
						if(listFactory.contains(Dao_Factory.searchFactoryByIdFactory(txtIdFactory.getText()))) {
							List<TeamProducing> listTeam = Dao_Factory.getListTeamByIdFactory(txtIdFactory.getText());
							if(dtmTeam.getRowCount() != listTeam.size()) {
								btnInsertFactory.setText("Sửa phân xưởng");
								btnInsertFactory.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
								btnUpdateFactory.setIcon(new ImageIcon("img\\math-add-icon.png"));
							}
						}
						else {
							randomIdTeam();
						}
					}
					
				}
				else {
					if(btnInsertTeam.getIcon().toString().equals("img\\Text-Edit-icon.png")) {
						TeamProducing team = new TeamProducing(txtIdTeam.getText(), txtNameTeam.getText(), txtIdLeadTeam.getText(), txtIdFactory.getText());
						if(Dao_Factory.updateTeam(team)) {
							JOptionPane.showMessageDialog(this, "Sửa tổ thành công!!!");
							deleteDataOnTableModel(dtmListTeam);
							loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
							deleteDataOnTableModel(dtmTeam);
							loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
						}
						else {
							JOptionPane.showMessageDialog(this, "Sửa tổ thất bại!!!");
						}
					}
					else {
						if(Dao_Factory.deleteTeam(txtIdTeam.getText()) && !Dao_Factory.checkEmployeeOfTeam(txtIdTeam.getText())) {
							JOptionPane.showMessageDialog(this, "Xóa tổ thành công!!!");
							txtIdTeam.setEditable(true);
							deleteDataOnTableModel(dtmListTeam);
							deleteDataOnTableModel(dtmTeam);
							loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
							loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
							btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
							btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
						}
						else {
							JOptionPane.showMessageDialog(this, "Tổ hiện đang có nhân viên làm việc,không thể xóa tổ");
						}
					}
					
				}
			}
		}
		else if(o.equals(btnInsertFactory)) {
//			boolean regexProduct = regexProduct();
			boolean regexProduct = true;
			if(regexProduct) {
				if(btnInsertFactory.getText().equals("Thêm phân xưởng")) {
					entity.Factory factory = new entity.Factory(txtIdFactory.getText(), txtNameFactory.getText(), txtIdLeadFactory.getText());
					boolean checkContainFactory = checkContainFactory(factory);
					int rowCountTeam = dtmTeam.getRowCount();
					TeamProducing team = new TeamProducing();
					for(int i = 0; i < rowCountTeam; i++) {
						team = new TeamProducing(dtmTeam.getValueAt(i, 0).toString(), dtmTeam.getValueAt(i, 1).toString(), dtmTeam.getValueAt(i, 2).toString(), txtIdFactory.getText());
						ListTeam.add(team);
					}
					if(!checkContainFactory) {
						boolean insertFactory = Dao_Factory.insertFactory(factory);
						boolean insertListTeam = Dao_Factory.insertListTeam(ListTeam);
						if(insertFactory) {
							JOptionPane.showMessageDialog(this, "Thêm phân xưởng thành công!!!");
							ListTeam = new ArrayList<TeamProducing>();
							deleteDataOnTableModel(dtmListFactory);
							cleanFactory();
							randomIdFactory();
						}
					}
					else {
						JOptionPane.showMessageDialog(this, "Phân xưởng đã tồn tại!!!");
					}
				}
				else {
					int rowCountTeam = dtmTeam.getRowCount();
					TeamProducing team = new TeamProducing();
					for(int i = 0; i < rowCountTeam; i++) {
						team = new TeamProducing(dtmTeam.getValueAt(i, 0).toString(),dtmTeam.getValueAt(i, 1).toString(),dtmTeam.getValueAt(i, 2).toString(),txtIdFactory.getText());
						ListTeam.add(team);
					}
					boolean updateListTeam = Dao_Factory.updateListTeam(ListTeam, txtIdFactory.getText());
					entity.Factory factory = new entity.Factory(txtIdFactory.getText(), txtNameFactory.getText(), txtIdLeadFactory.getText());
					boolean updateFactory = Dao_Factory.updateFactory(factory);
					if(updateFactory == true && updateListTeam == true) {
						JOptionPane.showMessageDialog(this, "Sửa phân xưởng thành công!!!");
						ListTeam = new ArrayList<TeamProducing>();
						deleteDataOnTableModel(dtmListFactory);
						deleteDataOnTableModel(dtmListTeam);
						loadListFactory();
						loadListTeamByIdFactory(factory.getFactoryID(), dtmListTeam);
					}
				}
			}
		}
		else if(o.equals(btnDeleteFactory)) {
			if(Dao_Factory.deleteFactory(txtIdFactory.getText())) {
				JOptionPane.showMessageDialog(this, "Xóa phân xưởng thành công!!!");
				deleteDataOnTableModel(dtmListFactory);
				deleteDataOnTableModel(dtmListTeam);
				loadListFactory();
			}
			else {
				JOptionPane.showMessageDialog(this, "Phân xưởng hiện đang có Tổ thực hiện,không thể xóa phân xưởng!!! ");
			}
		}
		else if(o.equals(btnDeleteTeam)) {
			if(Dao_Factory.deleteTeam(txtIdTeam.getText()) && !Dao_Factory.checkEmployeeOfTeam(txtIdTeam.getText())) {
				JOptionPane.showMessageDialog(this, "Xóa tổ thành công!!!");
				deleteDataOnTableModel(dtmListTeam);
				deleteDataOnTableModel(dtmTeam);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmTeam);
			}
			else {
				JOptionPane.showMessageDialog(this, "Tổ hiện đang có nhân viên làm việc,không thể xóa tổ");
			}
		}
		else if(o.equals(btnUpdateFactory)) {
			if(btnUpdateFactory.getIcon().toString().equals("img\\Text-Edit-icon.png")) {
				btnInsertFactory.setText("Sửa phân xưởng");
				btnInsertFactory.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
				btnUpdateFactory.setIcon(new ImageIcon("img\\math-add-icon.png"));
				if(!txtIdFactory.getText().isEmpty()) {
					randomIdTeam();
				}
			}
			else {
				btnInsertFactory.setText("Thêm phân xưởng");
				btnInsertFactory.setIcon(new ImageIcon("img\\math-add-icon.png"));
				btnUpdateFactory.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
				cleanFactory();
				randomIdFactory();
			}
		}
		else if(o.equals(btnUpdateTeam)) {
			if(btnUpdateTeam.getIcon().toString().equals("img\\Text-Edit-icon.png")) {
				btnInsertTeam.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
				btnUpdateTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
			}
			else {
				btnInsertTeam.setIcon(new ImageIcon("img\\math-add-icon.png"));
				btnUpdateTeam.setIcon(new ImageIcon("img\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("img\\Clear-icon.png"));
			}
		}
		else if(o.equals(btnSearchIdFactory)) {
			if(txtSearchIdFactory.getText().isEmpty()) {
				dtmListFactory.setRowCount(0);
				loadListFactory();
			}
			else {
				entity.Factory factory = Dao_Factory.searchFactoryByIdFactory(txtSearchIdFactory.getText());
				if(factory != null) {
					dtmListFactory.setRowCount(0);
					addRowFactory(factory);
				}
				else {
					dtmListFactory.setRowCount(0);
				}
			}
		}
		else if(o.equals(btnSearchIdTeam)) {
			if(txtSearchIdTeam.getText().isEmpty()) {
				dtmListTeam.setRowCount(0);
				loadListTeamByIdFactory(txtIdFactory.getText(), dtmListTeam);
			}
			else {
				TeamProducing team = Dao_Factory.searchTeamByIdTeam(txtSearchIdTeam.getText());
				if(team != null) {
					dtmListTeam.setRowCount(0);
					addRowListTeam(team, dtmListTeam);
				}
				else {
					dtmListTeam.setRowCount(0);
				}
			}
		}
		
	}
	
}
