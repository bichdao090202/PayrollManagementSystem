package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entity.Account;
import entity.Department;
import entity.Employee;
import entity.EmployeeOffice;
import entity.Worker;
import model.AccountDAO;
import model.DepartmentDAO;
import model.EmployeeOfficeDAO;
import model.WorkerDAO;
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
	private EmployeeOfficeDAO empOffDAO;
	private List<Employee> listEmpOff;
	private WorkerDAO workerDAO;
	private List<Worker> listWorker;
	private AccountDAO accDAO;
	private JComboBox<String> cbEmployee;
	private boolean flag; // flag = true -> search; flag = false -> don't

	public AccountGUI() {
		setSize(1200, 690);
		empOffDAO = new EmployeeOfficeDAO();
		listEmpOff = new ArrayList<>();
		workerDAO = new WorkerDAO();
		listWorker = new ArrayList<>();
		accDAO = new AccountDAO();
		add(getUI());

		index = 0;
		num = 30;
		flag = false;
		loadTable();
	}

	public Component getUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnAccount = new JPanel();
		pnAccount.setSize(1200, 690);
		pnAccount.setLayout(null);
		System.setProperty("Color", "0X000099");

		JPanel pnSearch = new JPanel();
		pnSearch.setLocation(10, 11);
		pnSearch.setSize(494, 49);
		pnSearch.setLayout(new MigLayout("", "[]10[]10[]", "[]"));
		pnSearch.add(new JLabel("Nhập mã/tên nhân viên: "), "cell 0 0");
		pnSearch.add(txtSearch = new JTextField());
		txtSearch.setColumns(30);
		pnSearch.add(btnSearch = new JButton());
		Image imgSearch = new ImageIcon("images\\operations\\search.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnSearch.setIcon(new ImageIcon(imgSearch));

		JPanel pnButton = new JPanel();
		pnButton.setLocation(527, 10);
		pnButton.setSize(401, 50);
		pnButton.setLayout(new MigLayout("", "[]20[]20[]", ""));
		pnButton.add(btnCreateAccount = new JButton("Cấp tài khoản"));
		pnButton.add(btnDeleteAccount = new JButton("Xóa tài khoản"));
		pnButton.add(btnSetDefaultPassword = new JButton("Đặt lại mật khẩu"));

		String stringCb[] = { "Nhân viên hành chính", "Nhân viên sản xuất" };
		cbEmployee = new JComboBox<>(stringCb);
		cbEmployee.setLocation(978, 15);
		cbEmployee.setSize(172, 30);

		JPanel pnTable = new JPanel();
		pnTable.setSize(1184, 531);
		pnTable.setLocation(0, 70);
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách nhân viên"));
		tblAccount = new JTable();
		String[] row0 = { "Mã nhân viên", "Họ tên", "Phòng ban/phân xưởng", "Chức vụ", "Tài khoản" };
		tblAccount = new JTable(tblModel = new DefaultTableModel(row0, 0));
		JScrollPane sp = new JScrollPane(tblAccount);
		sp.setPreferredSize(new Dimension(1165, 503));
		pnTable.add(sp);

		JPanel pnChangePage = new JPanel();
		pnChangePage.setLocation(10, 604);
		pnChangePage.setSize(1164, 36);
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
		pnNorth.setLocation(0, 0);
		pnNorth.setSize(1184, 60);
		pnNorth.setLayout(null);
		pnNorth.add(pnSearch);
		pnNorth.add(pnButton);
		pnNorth.add(cbEmployee);

		pnAccount.add(pnNorth);
		pnAccount.add(pnTable);
		pnAccount.add(pnChangePage);

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
		cbEmployee.addActionListener(this);
		btnSearch.addActionListener(this);
		loadListOfficeEmployee();
		return pnAccount;
	}

	public void loadListOfficeEmployee() {
		List<Employee> newList = listEmpOff.subList(index, Math.min(index + num, listEmpOff.size()));
		while (tblModel.getRowCount() != 0)
			tblModel.removeRow(0);
		for (Employee x : newList) {
			String[] row = { x.getEmployeeID(), x.getName(), empOffDAO.getDepNameByEmpID(x.getEmployeeID()),
					((EmployeeOffice) x).getPosition(),
					(accDAO.checkAccByEmpID(x.getEmployeeID()) == true ? "Có tài khoản" : null) };
			tblModel.addRow(row);
		}
	}

	public void loadListWorker() {
		List<Worker> newList = listWorker.subList(index, Math.min(index + num, listWorker.size()));
		while (tblModel.getRowCount() != 0)
			tblModel.removeRow(0);
		for (Worker x : newList) {
			String[] row = { x.getEmployeeID(), x.getName(), x.getTeamID(), accDAO.getPositionWorker(x.getEmployeeID()),
					(accDAO.checkAccByEmpID(x.getEmployeeID()) == true ? "Có tài khoản" : null) };
			tblModel.addRow(row);
		}
	}

	public static void main(String[] args) {
		new AccountGUI().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		int listSize = (cbEmployee.getSelectedItem().toString().equals("Nhân viên hành chính")) ? listEmpOff.size()
				: listWorker.size();
		if (o.equals(btnCreateAccount)) {
			int row = tblAccount.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên cần cấp tài khoản");
				return;
			}
			String id = (String) tblAccount.getValueAt(row, 0);
			if (accDAO.checkAccByEmpID(id) == true) {
				JOptionPane.showMessageDialog(this, "Nhân viên này đã có tài khoản");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn muốn cấp tài khoản cho nhân viên này?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (accDAO.createAccount(id) == false) {
					JOptionPane.showMessageDialog(this, "Ghi dữ liệu thất bại, vui lòng thử lại sau");
					return;
				}
				loadTable();
				JOptionPane.showMessageDialog(this, "Cấp tài khoản thành công");
			}			
		}
		if (o.equals(btnDeleteAccount)) {
			int row = tblAccount.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên để xóa tài khoản");
				return;
			}
			String id = (String) tblAccount.getValueAt(row, 0);
			if (accDAO.checkAccByEmpID(id) == false) {
				JOptionPane.showMessageDialog(this, "Nhân viên này chưa có tài khoản");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn muốn xóa tài khoản của nhân viên này?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (accDAO.deleteAccount(id) == false) {
					JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại, vui lòng thử lại sau");
					return;
				}
				loadTable();
				JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công");
			}			
		}
		if (o.equals(btnSetDefaultPassword)) {
			int row = tblAccount.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên để đặt lại mật khẩu");
				return;
			}
			String id = (String) tblAccount.getValueAt(row, 0);
			if (accDAO.checkAccByEmpID(id) == false) {
				JOptionPane.showMessageDialog(this, "Nhân viên này chưa có tài khoản");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn muốn đặt lại mật khẩu mặc định cho tài khoản này?",
					"Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (accDAO.setDefaultPassword(id) == false) {
					JOptionPane.showMessageDialog(this, "Thao tác thất bại, vui lòng thử lại sau");
					return;
				}
				loadTable();
				JOptionPane.showMessageDialog(this, "Tài khoản đã đổi về mật khẩu mặc định");
			}			
		}
		if (o.equals(cbEmployee)) {
			flag = false;
			index = 0;
			loadTable();
		}
		if (o.equals(btnSearch)) {
			index = 0;
			flag = true;
			loadTable();
		}

		if (o.equals(btnGoFirstPage)) {
			index = 0;
			loadTable();
		}
		if (o.equals(btnPreviousPage)) {
			if (index == 0)
				return;
			else
				index -= num;
			loadTable();
		}
		if (o.equals(btnNextPage)) {
			if (index + num >= listSize)
				return;
			else
				index += num;
			loadTable();
		}
		if (o.equals(btnGoLastPage)) {
			index = (listSize - 1) / num * num;
			loadTable();
		}
	}

	public void loadTable() {
		if (cbEmployee.getSelectedItem().toString().equals("Nhân viên hành chính")) {
			listEmpOff = accDAO.getListAccountOffice();
			if (flag == true) {
				String search = txtSearch.getText();				
				for (int i = 0; i < listEmpOff.size(); i++)
					if (!listEmpOff.get(i).getEmployeeID().toLowerCase().contains(search.toLowerCase()) && !listEmpOff.get(i).getName().toLowerCase().contains(search.toLowerCase())) {
						listEmpOff.remove(listEmpOff.get(i));
						i--;
					}
			}
			loadListOfficeEmployee();
		} else {
			listWorker = accDAO.getListAccountWorker();
			if (flag == true) {
				String search = txtSearch.getText();
				for (int i = 0; i < listWorker.size(); i++)
					if (!listWorker.get(i).getEmployeeID().toLowerCase().contains(search.toLowerCase()) && !listWorker.get(i).getName().toLowerCase().contains(search.toLowerCase())) {
						listWorker.remove(listWorker.get(i));
						i--;
					}
			}
			loadListWorker();
		}
	}



}
