package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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

import entity.Employee;
import entity.EmployeeOffice;
import entity.Worker;
import model.AccountDAO;
import model.EmployeeOfficeDAO;
import net.miginfocom.swing.MigLayout;

public class AccountGUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private final String stringCb[] = { "Nhân viên hành chính", "Nhân viên sản xuất" };
	private final String[] row0 = { "Mã nhân viên", "Họ tên", "Phòng ban/phân xưởng", "Chức vụ", "Tài khoản" };
	private JButton btnCreateAccount, btnDeleteAccount, btnSetDefaultPassword, btnSearch;
	private JTextField txtSearch;
	private JTable tblAccount;
	private DefaultTableModel tblModel;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	private int index, num;
	private EmployeeOfficeDAO empOffDAO;
	private List<Employee> listEmpOff;
	private List<Worker> listWorker;
	private AccountDAO accDAO;
	private JComboBox<String> cbEmployee;
	private boolean flag; // flag = true -> search; flag = false -> don't

	public AccountGUI() {
		getContentPane().setBackground(Color.WHITE);
		setSize(1200, 731);
		empOffDAO = new EmployeeOfficeDAO();
		listEmpOff = new ArrayList<>();
		listWorker = new ArrayList<>();
		accDAO = new AccountDAO();
		getContentPane().add(getUI());

		index = 0;
		num = 33;
		flag = false;
		loadTable();
	}

	public Component getUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnAccount = new JPanel();
		pnAccount.setBackground(Color.WHITE);
		pnAccount.setSize(1200, 690);
		pnAccount.setLayout(null);
		System.setProperty("Color", "0X000099");

		JPanel pnSearch = new JPanel();
		pnSearch.setBackground(Color.WHITE);
		pnSearch.setLocation(10, 11);
		pnSearch.setSize(494, 49);
		pnSearch.setLayout(new MigLayout("", "[]10[]10[]", "[]"));
		pnSearch.add(new JLabel("Nhập mã/tên nhân viên: "), "cell 0 0");
		pnSearch.add(txtSearch = new JTextField());
		txtSearch.setColumns(30);
		pnSearch.add(btnSearch = new JButton());
		btnSearch.setMnemonic(KeyEvent.VK_S);
		btnSearch.setToolTipText("Tìm kiếm (Alt + S)");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(Color.WHITE);
			}
		});
		btnSearch.setBorder(new LineBorder(COLOR, 3, false));
		btnSearch.setForeground(COLOR);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setIcon(new ImageIcon("images\\operations\\search.png"));

		JPanel pnButton = new JPanel();
		pnButton.setBackground(Color.WHITE);
		pnButton.setLocation(528, 4);
		pnButton.setSize(401, 50);
		pnButton.setLayout(null);
		pnButton.add(btnCreateAccount = new JButton("Cấp tài khoản"));
		btnCreateAccount.setMnemonic(KeyEvent.VK_A);
		btnCreateAccount.setToolTipText("Cấp tài khoản cho nhân viên (Alt + A)");
		btnCreateAccount.setBounds(14, 11, 114, 28);
		btnCreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreateAccount.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCreateAccount.setBackground(Color.WHITE);
			}
		});
		btnCreateAccount.setBorder(new LineBorder(COLOR, 3, false));
		btnCreateAccount.setForeground(COLOR);
		btnCreateAccount.setBackground(Color.WHITE);
		
		pnButton.add(btnDeleteAccount = new JButton("Xóa tài khoản"));
		btnDeleteAccount.setMnemonic(KeyEvent.VK_D);
		btnDeleteAccount.setToolTipText("Xóa tài khoản của nhân viên (Alt + D)");
		btnDeleteAccount.setLocation(142, 11);
		btnDeleteAccount.setSize(114, 28);
		btnDeleteAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDeleteAccount.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDeleteAccount.setBackground(Color.WHITE);
			}
		});
		btnDeleteAccount.setBorder(new LineBorder(COLOR, 3, false));
		btnDeleteAccount.setForeground(COLOR);
		btnDeleteAccount.setBackground(Color.WHITE);
		
		pnButton.add(btnSetDefaultPassword = new JButton("Đặt lại mật khẩu"));
		btnSetDefaultPassword.setMnemonic(KeyEvent.VK_U);
		btnSetDefaultPassword.setToolTipText("Đặt lại mật khẩu mặc định 123456789 (Alt + U)");
		btnSetDefaultPassword.setLocation(270, 11);
		btnSetDefaultPassword.setSize(114, 28);
		btnSetDefaultPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSetDefaultPassword.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSetDefaultPassword.setBackground(Color.WHITE);
			}
		});
		btnSetDefaultPassword.setBorder(new LineBorder(COLOR, 3, false));
		btnSetDefaultPassword.setForeground(COLOR);
		btnSetDefaultPassword.setBackground(Color.WHITE);
		
		cbEmployee = new JComboBox<>(stringCb);
		cbEmployee.setBackground(Color.WHITE);
		cbEmployee.setLocation(978, 15);
		cbEmployee.setSize(172, 30);

		JPanel pnTable = new JPanel();
		pnTable.setBackground(Color.WHITE);
		pnTable.setSize(1184, 583);
		pnTable.setLocation(0, 70);
		pnTable.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Danh sách nhân viên"));
		
		tblAccount = new JTable(tblModel = new DefaultTableModel(row0, 0));
		tblAccount.setFillsViewportHeight(true);
		tblAccount.getTableHeader().setOpaque(false);
		tblAccount.getTableHeader().setBackground(COLOR);
		tblAccount.getTableHeader().setForeground(Color.WHITE);
		JScrollPane sp = new JScrollPane(tblAccount);
		sp.setPreferredSize(new Dimension(1165, 551));
		pnTable.add(sp);

		JPanel pnChangePage = new JPanel();
		pnChangePage.setBackground(Color.WHITE);
		pnChangePage.setLocation(10, 650);
		pnChangePage.setSize(1164, 36);
		
		pnChangePage.add(btnGoFirstPage = new JButton());
		btnGoFirstPage.setBackground(Color.WHITE);
		btnGoFirstPage.setIcon(new ImageIcon("images\\jump page\\first.png"));
		
		pnChangePage.add(btnPreviousPage = new JButton());
		btnPreviousPage.setBackground(Color.WHITE);
		btnPreviousPage.setIcon(new ImageIcon("images\\jump page\\previous.png"));
		
		pnChangePage.add(btnNextPage = new JButton());
		btnNextPage.setBackground(Color.WHITE);
		btnNextPage.setIcon(new ImageIcon("images\\jump page\\next.png"));
		
		pnChangePage.add(btnGoLastPage = new JButton());
		btnGoLastPage.setBackground(Color.WHITE);
		btnGoLastPage.setIcon(new ImageIcon("images\\jump page\\last.png"));

		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(Color.WHITE);
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
					x.getPosition(),
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
				String search = txtSearch.getText().trim();
				for (int i = 0; i < listEmpOff.size(); i++)
					if (!listEmpOff.get(i).getEmployeeID().toLowerCase().contains(search.toLowerCase())
							&& !listEmpOff.get(i).getName().toLowerCase().contains(search.toLowerCase())) {
						listEmpOff.remove(listEmpOff.get(i));
						i--;
					}
			}
			loadListOfficeEmployee();
		} else {
			listWorker = accDAO.getListAccountWorker();
			if (flag == true) {
				String search = txtSearch.getText().trim();
				for (int i = 0; i < listWorker.size(); i++)
					if (!listWorker.get(i).getEmployeeID().toLowerCase().contains(search.toLowerCase())
							&& !listWorker.get(i).getName().toLowerCase().contains(search.toLowerCase())) {
						listWorker.remove(listWorker.get(i));
						i--;
					}
			}
			loadListWorker();
		}
	}

}
