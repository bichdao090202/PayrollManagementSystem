package ui;

import java.awt.BorderLayout;
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
import java.util.regex.Pattern;

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

import entity.Department;
import entity.Employee;
import model.DepartmentDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.ScrollPaneConstants;

public class DepartmentGUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private final String[] row1 = { "Mã nhân viên", "Tên nhân viên"};
	private JButton btnCreate, btnDelete, btnUpdate, btnRefresh;
	private JTable tblDepartment, tblEmp;
	private DefaultTableModel tblModel, tblModelEmp;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	private DepartmentDAO depDAO;
	private JTextField txtName;
	private int index, num;
	private List<Department> newList;
	private List<Department> listDep;

	public DepartmentGUI() {
		getContentPane().setBackground(Color.WHITE);
		setSize(1200, 731);
		depDAO = new DepartmentDAO();
		listDep = new ArrayList<>();
		listDep = depDAO.getAllDepartments();
		num = 33;
		index = 0;
		getContentPane().add(getUI());
	}

	public Component getUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnDepartment = new JPanel();
		pnDepartment.setBackground(Color.WHITE);
		pnDepartment.setSize(1200, 690);

		JPanel pnInformation = new JPanel();
		pnInformation.setBackground(Color.WHITE);
		pnInformation.setSize(730, 63);
		pnInformation.setLocation(0, 11);
		System.setProperty("Color", "0X000099");
		pnInformation.setBorder(
				new TitledBorder(new LineBorder(COLOR, 2, true), "Nhập thông tin phòng ban"));
		pnInformation.setLayout(new MigLayout("", "[][grow]", "[]"));
		pnInformation.add(new JLabel("Tên phòng ban:   "));
		
		txtName = new JTextField();
		txtName.setColumns(50);
		pnInformation.add(txtName, "growx");

		JPanel pnButton = new JPanel();
		pnButton.setBackground(Color.WHITE);
		pnButton.setSize(435, 63);
		pnButton.setLocation(746, 11);
		pnButton.setLayout(null);

		btnCreate = new JButton("Thêm");
		btnCreate.setMnemonic(KeyEvent.VK_A);
		btnCreate.setToolTipText("Thêm phòng ban (Alt + A)");
		btnCreate.setBounds(20, 14, 83, 34);
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreate.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCreate.setBackground(Color.WHITE);
			}
		});
		btnCreate.setBorder(new LineBorder(COLOR, 3, false));
		btnCreate.setForeground(COLOR);
		btnCreate.setBackground(Color.WHITE);
		btnCreate.setFocusable(false);
		btnCreate.setIcon(new ImageIcon("images\\operations\\new.png"));
		pnButton.add(btnCreate);
		
		pnDepartment.setLayout(null);
		pnDepartment.add(pnInformation);
		pnDepartment.add(pnButton);

		pnButton.add(btnDelete = new JButton("Xóa"));
		btnDelete.setMnemonic(KeyEvent.VK_D);
		btnDelete.setToolTipText("Xóa phòng ban (Alt + D)");
		btnDelete.setBounds(123, 14, 83, 34);
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
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));
		
		pnButton.add(btnUpdate = new JButton("Cập nhật"));
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.setToolTipText("Cập nhật phòng ban (Alt + U)");
		btnUpdate.setBounds(226, 14, 83, 34);
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
		btnUpdate.setFocusable(false);
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		
		pnButton.add(btnRefresh = new JButton("Làm mới"));
		btnRefresh.setMnemonic(KeyEvent.VK_N);
		btnRefresh.setToolTipText("Làm mới ô nhập liệu (Alt + N)");
		btnRefresh.setBounds(329, 14, 83, 34);
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRefresh.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRefresh.setBackground(Color.WHITE);
			}
		});
		btnRefresh.setBorder(new LineBorder(COLOR, 3, false));
		btnRefresh.setForeground(COLOR);
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setIcon(new ImageIcon("images\\operations\\refresh.png"));
		btnDelete.setFocusable(false);

		JPanel pnTable = new JPanel();
		pnTable.setBackground(Color.WHITE);
		pnTable.setSize(730, 615);
		pnTable.setLocation(0, 76);
		pnTable.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Danh sách phòng ban"));
		String[] row0 = { "Mã", "Tên phòng ban", "Tên trưởng phòng", "Số lượng nhân viên" };
		pnTable.setLayout(new BorderLayout(0, 0));
		tblDepartment = new JTable(tblModel = new DefaultTableModel(row0, 0));
		tblDepartment.setFillsViewportHeight(true);
		tblDepartment.getTableHeader().setOpaque(false);
		tblDepartment.getTableHeader().setBackground(COLOR);
		tblDepartment.getTableHeader().setForeground(Color.WHITE);
		tblDepartment.setBackground(Color.WHITE);
		tblDepartment.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblDepartment.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblDepartment.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblDepartment.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblDepartment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tblDepartment.getSelectedRow();
				txtName.setText((String) tblDepartment.getValueAt(row, 1));
				List<Employee> listEmp = depDAO.getEmployeeByDepartmentID( tblDepartment.getValueAt(row, 0).toString());
				while (tblModelEmp.getRowCount() != 0)
					tblModelEmp.removeRow(0);
				for (Employee x : listEmp) {
					String[] string = {x.getEmployeeID(), x.getName()};
					tblModelEmp.addRow(string);
				}
				
			}
		});
		
		JScrollPane sp = new JScrollPane(tblDepartment);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setLocation(10, 16);
		sp.setBackground(Color.WHITE);
		sp.setPreferredSize(new Dimension(1150, 474));
		pnTable.add(sp);
		
		JPanel pnTableEmp = new JPanel();
		pnTableEmp.setBackground(Color.WHITE);
		pnTableEmp.setSize(435, 567);
		pnTableEmp.setLocation(746, 82);
		pnTableEmp.setBorder(new TitledBorder(new LineBorder(COLOR, 2, true), "Danh sách nhân viên"));
		pnTableEmp.setLayout(new BorderLayout(0, 0));
		
		tblEmp = new JTable(tblModelEmp = new DefaultTableModel(row1, 0));
		tblEmp.setFillsViewportHeight(true);
		tblEmp.getTableHeader().setOpaque(false);
		tblEmp.getTableHeader().setBackground(COLOR);
		tblEmp.getTableHeader().setForeground(Color.WHITE);
		tblEmp.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblEmp.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		JScrollPane sp1 = new JScrollPane(tblEmp);
		sp1.setPreferredSize(new Dimension(1150, 470));
		pnTableEmp.add(sp1);
		
		
		JPanel pnChangePage = new JPanel();
		pnChangePage.setBackground(Color.WHITE);
		
		pnChangePage.add(btnGoFirstPage = new JButton());
		btnGoFirstPage.setBackground(Color.WHITE);
		btnGoFirstPage.setFocusable(false);
		btnGoFirstPage.setIcon(new ImageIcon("images\\jump page\\first.png"));
		
		pnChangePage.add(btnPreviousPage = new JButton());
		btnPreviousPage.setBackground(Color.WHITE);
		btnPreviousPage.setFocusable(false);
		btnPreviousPage.setIcon(new ImageIcon("images\\jump page\\previous.png"));
		
		pnChangePage.add(btnNextPage = new JButton());
		btnNextPage.setBackground(Color.WHITE);
		btnNextPage.setFocusable(false);
		btnNextPage.setIcon(new ImageIcon("images\\jump page\\next.png"));
		
		pnChangePage.add(btnGoLastPage = new JButton(""));
		btnGoLastPage.setBackground(Color.WHITE);
		btnGoLastPage.setFocusable(false);
		btnGoLastPage.setIcon(new ImageIcon("images\\jump page\\last.png"));
		

		pnDepartment.add(pnTable);
		pnDepartment.add(pnTableEmp);
		pnTable.add(pnChangePage, BorderLayout.SOUTH);
		btnGoFirstPage.setFocusable(false);
		btnGoLastPage.setFocusable(false);
		btnNextPage.setFocusable(false);
		btnPreviousPage.setFocusable(false);
		btnRefresh.setFocusable(false);
		btnGoFirstPage.addActionListener(this);
		btnGoLastPage.addActionListener(this);
		btnPreviousPage.addActionListener(this);
		btnNextPage.addActionListener(this);
		btnCreate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRefresh.addActionListener(this);
		refresh();
		loadTable();
		return pnDepartment;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCreate)) {
			if (checkName() == false) {
				JOptionPane.showMessageDialog(this, "Tên phòng ban không thể để trống hoặc chứa kí tự đặc biệt");
				return;
			}
			Department newDep = new Department(depDAO.getNewDeparmentID(), txtName.getText().trim());
			if (newDep.getDepartmentID().equals("PB00")) {
				JOptionPane.showMessageDialog(this, "Phòng ban đã đầy, hãy bảo trì hệ thống");
				return;
			}
			depDAO.addDepartment(newDep);
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm phòng ban này không?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (depDAO.addDepartment(newDep) == false) {
					JOptionPane.showMessageDialog(this, "Thêm phòng ban thất bại, vui lòng thử lại sau");
					return;
				}
				while (index + num <= listDep.size()) {
					index += num;
				}
				refresh();
				JOptionPane.showMessageDialog(this, "Thêm phòng ban thành công");
			}	
		}
		if (o.equals(btnRefresh))
			refresh();
		if (o.equals(btnDelete)) {
			int row = tblDepartment.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn phòng ban để xóa");
				return;
			}
			String id = (String) tblDepartment.getValueAt(row, 0);
			if (depDAO.getQuantityEmployee(id) != 0) {
				JOptionPane.showMessageDialog(this, "Phòng ban này vẫn còn nhân viên, không thể xóa");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa phòng ban này không?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (depDAO.deleteDepartment(id) == false) {
					JOptionPane.showMessageDialog(this, "Xóa phòng ban thất bại, vui lòng thử lại sau");
					return;
				}
				if (index == listDep.size() - 1)
					index -= num;
				refresh();
				JOptionPane.showMessageDialog(this, "Xóa phòng ban thành công");
			}
		}
		if (o.equals(btnUpdate)) {
			int row = tblDepartment.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn phòng ban để cập nhật");
				return;
			}
			if (checkName() == false) {
				JOptionPane.showMessageDialog(this, "Tên phòng ban không thể để trống hoặc chứa kí tự đặc biệt");
				return;
			}
			Department newDep = new Department(tblDepartment.getValueAt(tblDepartment.getSelectedRow(), 0).toString(),
					txtName.getText().trim());
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn cập nhật tên phòng ban này không?",
					"Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (depDAO.updateDepartment(newDep.getDepartmentID(), newDep.getName()) == false) {
					JOptionPane.showMessageDialog(this, "Cập nhật phòng ban thất bại, vui lòng thử lại sau");
					return;
				}
				refresh();
				JOptionPane.showMessageDialog(this, "Cập nhật phòng ban thành công");
			}
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
			if (index + num >= listDep.size())
				return;
			else
				index += num;
			loadTable();
		}
		if (o.equals(btnGoLastPage)) {
			index = (listDep.size() - 1) / num * num;
			loadTable();
		}
	}

	public void refresh() {
		index = 0;
		txtName.setText("");
		tblDepartment.clearSelection();
		loadTable();
	}

	public void loadTable() {
		listDep = depDAO.getAllDepartments();
		newList = listDep.subList(index, Math.min(index + num, listDep.size()));
		while (tblModel.getRowCount() != 0)
			tblModel.removeRow(0);
		for (Department x : newList) {
			String[] row = { x.getDepartmentID(), x.getName(), depDAO.getNameManagerByID(x.getManagerID()),
					depDAO.getQuantityEmployee(x.getDepartmentID()) == 0 ? "Chưa có nhân viên"
							: depDAO.getQuantityEmployee(x.getDepartmentID()) + "" };
			tblModel.addRow(row);
		}
	}

	public boolean checkName() {
		String name = txtName.getText().trim();
		if (name == null || name.trim().isEmpty() || !Pattern.matches(
				"[a-zA-Z0-9\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+",
				name))
			return false;
		return true;
	}

	public static void main(String[] args) {
		new DepartmentGUI().setVisible(true);
	}

}