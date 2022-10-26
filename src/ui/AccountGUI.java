package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entity.Account;
import entity.Department;
import entity.Employee;
import entity.EmployeeOffice;
import model.AccountDAO;
import model.DepartmentDAO;
import model.EmployeeOfficeDAO;
import net.miginfocom.swing.MigLayout;

public class AccountGUI extends JFrame implements ActionListener {
	private JButton btnCreateAccount;
	private JButton btnDeleteAccount;
	private JButton btnSetDefaultPassword;
	private JButton btnSearch;
	private JTextField txtSearch;
	private JTable tblAccount;
	private DefaultTableModel tblModel;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	private ArrayList<Account> ds;
	private int index, num;
	private List<Account> newList;
	private  EmployeeOfficeDAO empOffDAO;
	private List<EmployeeOffice> listEmpOff;
	private  DepartmentDAO depDAO;
	private List<Department> listDep;
	private  AccountDAO accDAO;
	
	public AccountGUI() {
		setSize(1200, 690);
		empOffDAO = new EmployeeOfficeDAO();
		listEmpOff = new ArrayList<>();
		listEmpOff = empOffDAO.getAllEmployeeOffice();
		depDAO = new DepartmentDAO();
		listDep = new ArrayList<>();
		listDep = depDAO.getAllDepartments();
		accDAO = new AccountDAO();
		add(tabAccount());
//		System.out.print(listEmpOff);
	}

	public Component tabAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnAccount = new JPanel();
		pnAccount.setSize(1200, 690);
		pnAccount.setLayout(new BoxLayout(pnAccount, BoxLayout.Y_AXIS));
		System.setProperty("Color", "0X000099");
		
		JPanel pnSearch = new JPanel();
		pnSearch.setLayout(new MigLayout("", "[]10[]10[]50", ""));
		pnSearch.add(new JLabel("Nhập mã/tên nhân viên: "));
		pnSearch.add(txtSearch = new JTextField());
		txtSearch.setColumns(30);
		pnSearch.add(btnSearch = new JButton());
		Image imgSearch = new ImageIcon("images\\operations\\search.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnSearch.setIcon(new ImageIcon(imgSearch));
		
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new MigLayout("", "[]20[]20[]", ""));
		pnButton.add(btnCreateAccount = new JButton("Cấp tài khoản"));
		pnButton.add(btnDeleteAccount = new JButton("Xóa tài khoản"));
		pnButton.add(btnSetDefaultPassword = new JButton("Đặt lại mật khẩu"));
		
		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách nhân viên"));
		tblAccount = new JTable();
		String[] row0 = { "Mã nhân viên", "Họ tên", "Loại", "Phòng ban/phân xưởng", "Chức vụ", "Tài khoản"};
		tblAccount = new JTable(tblModel = new DefaultTableModel(row0, 0));
		JScrollPane sp = new JScrollPane(tblAccount);
		sp.setPreferredSize(new Dimension(1150, 480));
		pnTable.add(sp);

		JPanel pnChangePage = new JPanel();
		pnChangePage.add(btnGoFirstPage = new JButton());
		Image imgFirst = new ImageIcon("images\\jump page\\first.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnGoFirstPage.setIcon(new ImageIcon(imgFirst));
		pnChangePage.add(btnPreviousPage = new JButton());
		Image imgPrevious = new ImageIcon("images\\jump page\\previous.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnPreviousPage.setIcon(new ImageIcon(imgPrevious));
		pnChangePage.add(btnNextPage = new JButton());
		Image imgNext = new ImageIcon("images\\jump page\\next.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnNextPage.setIcon(new ImageIcon(imgNext));
		pnChangePage.add(btnGoLastPage = new JButton(""));
		Image imgLast = new ImageIcon("images\\jump page\\last.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnGoLastPage.setIcon(new ImageIcon(imgLast));

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.X_AXIS));
		pnNorth.add(Box.createVerticalStrut(30));
		pnNorth.add(pnSearch);
		pnNorth.add(pnButton);
		pnAccount.add(pnNorth);
		pnAccount.add(pnTable);
		pnTable.add(pnChangePage);

		btnCreateAccount.setFocusable(false);
		btnSetDefaultPassword.setFocusable(false);
		btnDeleteAccount.setFocusable(false);
		btnSearch.setFocusable(false);
		btnGoFirstPage.setFocusable(false);
		btnGoLastPage.setFocusable(false);
		btnNextPage.setFocusable(false);
		btnPreviousPage.setFocusable(false);
		btnGoFirstPage.addActionListener(this);
		btnGoLastPage.addActionListener(this);
		btnPreviousPage.addActionListener(this);
		btnNextPage.addActionListener(this);
		btnCreateAccount.addActionListener(this);
		btnDeleteAccount.addActionListener(this);
		btnSetDefaultPassword.addActionListener(this);
		ds = new ArrayList<>();
		Account acc1 = new Account("1","1");
		Account acc2 = new Account("2","2");
		Account acc3 = new Account("3","3");
		Account acc4 = new Account("4","4");
		Account acc5 = new Account("5","5");
		Account acc6 = new Account("6","6");
		Account acc7 = new Account("7","7");
		Account acc8 = new Account("8","8");
		
		ds.add(acc1);
		ds.add(acc2);
		ds.add(acc3);
		ds.add(acc4);
		ds.add(acc5);	
		ds.add(acc6);
		ds.add(acc7);
		ds.add(acc8);
		index = 0;
		num = 3;
		newList = ds.subList(0,num);
		loadTable();
		return pnAccount;
	}
	
	public void loadTable() {
		while (tblModel.getRowCount() != 0)
			tblModel.removeRow(0);
		for (EmployeeOffice x : listEmpOff) {
			String[] row = { x.getEmployeeID(), x.getName(), "Nhân viên hành chính", empOffDAO.getDepNameByEmpID(x.getEmployeeID()), x.getPosition(), (accDAO.checkAccByEmpID(x.getEmployeeID())==true?"Có tài khoản":null) };
				tblModel.addRow(row);
		}
	}

	public static void main(String[] args) {
		new AccountGUI().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCreateAccount)) {
			int row = tblAccount.getSelectedRow();
			if (row==-1) {
				JOptionPane.showMessageDialog(this,"Hãy chọn nhân viên cần cấp tài khoản");
				return;
			}	
			String id = (String)tblAccount.getValueAt(row, 0);
			if (accDAO.checkAccByEmpID(id)==true) {
				JOptionPane.showMessageDialog(this,"Nhân viên này đã có tài khoản");
				return;
			}
			if (accDAO.createAccount(id)==false) {
				JOptionPane.showMessageDialog(this,"Ghi dữ liệu thất bại, vui lòng thử lại sau");
				return;
			}
			loadTable();
			JOptionPane.showMessageDialog(this,"Cấp tài khoản thành công");
		}
		if (o.equals(btnDeleteAccount)) {
			int row = tblAccount.getSelectedRow();
			if (row==-1) {
				JOptionPane.showMessageDialog(this,"Chưa chọn nhân viên để xóa tài khoản");
				return;
			}	
			String id = (String)tblAccount.getValueAt(row, 0);
			if (accDAO.deleteAccount(id)==false) {
				JOptionPane.showMessageDialog(this,"Nhân viên này chưa có tài khoản");
				return;
			}
			loadTable();
			JOptionPane.showMessageDialog(this,"Xóa tài khoản thành công");
		}
		if (o.equals(btnSetDefaultPassword)) {
			int row = tblAccount.getSelectedRow();
			if (row==-1) {
				JOptionPane.showMessageDialog(this,"Chưa chọn nhân viên để đặt lại mật khẩu");
				return;
			}	
			String id = (String)tblAccount.getValueAt(row, 0);
			if (accDAO.checkAccByEmpID(id)==false) {
				JOptionPane.showMessageDialog(this,"Nhân viên này chưa có tài khoản");
				return;
			}
			if (accDAO.setDefaultPassword(id)==false) {
				JOptionPane.showMessageDialog(this,"Thao tác thật bại, bạn vui lòng thử lại sau");
				return;
			}
			loadTable();
			JOptionPane.showMessageDialog(this,"Tài khoản đã đổi về mật khẩu mặc định");
		}
		if (o.equals(btnGoFirstPage)) {
			index = 0;
			newList = ds.subList(0,num);
			loadTable();
		}
		if (o.equals(btnPreviousPage)) {
			if (index==0)
				return;
			else {
				index-=num;
				newList = ds.subList(index,index+num);
			}			
			loadTable();
		}
		if (o.equals(btnNextPage)) {
			if (index+num>=ds.size())
				return;
			else {
				index+=num;
				if (index+num>ds.size())
					newList = ds.subList(index,ds.size());
				else 
					newList = ds.subList(index,index+num);
			}	
			loadTable();
		}
		if (o.equals(btnGoLastPage)) {
			index = (ds.size()-1)/num*num;			
			newList = ds.subList(index,ds.size());
			loadTable();
		}
	}
}
