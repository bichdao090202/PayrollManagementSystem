package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class DepartmentGUI extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnCreate;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnRefresh;
	private JTable tblDepartment;
	private DefaultTableModel tblModel;
	private JTable tblEmp;
	private DefaultTableModel tblModelEmp;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	private DepartmentDAO depDAO;
	private JTextField txtName;
	private int index, num;
	private List<Department> newList;
	private List<Department> listDep;

	public DepartmentGUI() {
		setSize(1200, 690);
		depDAO = new DepartmentDAO();
		listDep = new ArrayList<>();
		listDep = depDAO.getAllDepartments();
		num = 30;
		index = 0;
		getContentPane().add(getUI());
	}

	public Component getUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnDepartment = new JPanel();
		pnDepartment.setSize(1200, 690);

		JPanel pnInformation = new JPanel();
		pnInformation.setSize(486, 63);
		pnInformation.setLocation(10, 11);
		System.setProperty("Color", "0X000099");
		pnInformation.setBorder(
				new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Nhập thông tin phòng ban"));
		pnInformation.setLayout(new MigLayout());
		pnInformation.add(new JLabel("Tên phòng ban:   "));
		txtName = new JTextField();
		pnInformation.add(txtName);
		txtName.setColumns(50);

		JPanel pnButton = new JPanel();
		pnButton.setSize(537, 42);
		pnButton.setLocation(564, 21);
		FlowLayout fl_pnButton = new FlowLayout();
		fl_pnButton.setHgap(20);
		pnButton.setLayout(fl_pnButton);

		btnCreate = new JButton("Thêm");
		btnCreate.setFocusable(false);
		pnButton.add(btnCreate);
		pnDepartment.setLayout(null);
		pnDepartment.add(pnInformation);
		pnDepartment.add(pnButton);

		Image imgAdd = new ImageIcon("images\\operations\\new.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnCreate.setIcon(new ImageIcon(imgAdd));
		pnButton.add(btnDelete = new JButton("Xóa"));
		btnDelete.setFocusable(false);
		Image imgDelete = new ImageIcon("images\\operations\\delete.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnDelete.setIcon(new ImageIcon(imgDelete));
		pnButton.add(btnUpdate = new JButton("Cập nhật"));
		btnUpdate.setFocusable(false);
		Image imgUpdate = new ImageIcon("images\\operations\\update.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnUpdate.setIcon(new ImageIcon(imgUpdate));
		pnButton.add(btnRefresh = new JButton("Làm mới"));
		btnDelete.setFocusable(false);

		JPanel pnTable = new JPanel();
		pnTable.setSize(730, 569);
		pnTable.setLocation(0, 82);
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách phòng ban"));
		tblDepartment = new JTable();
		String[] row0 = { "Mã", "Tên phòng ban", "Tên trưởng phòng", "Số lượng nhân viên" };
		pnTable.setLayout(new BorderLayout(0, 0));
		tblDepartment = new JTable(tblModel = new DefaultTableModel(row0, 0));
		JScrollPane sp = new JScrollPane(tblDepartment);
		sp.setPreferredSize(new Dimension(1150, 470));
		pnTable.add(sp);
		tblDepartment.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblDepartment.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblDepartment.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblDepartment.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		JPanel pnTableEmp = new JPanel();
		pnTableEmp.setSize(435, 530);
		pnTableEmp.setLocation(746, 82);
		pnTableEmp.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách nhân viên"));
		tblEmp = new JTable();
		String[] row1 = { "Mã nhân viên", "Tên nhân viên"};
		pnTableEmp.setLayout(new BorderLayout(0, 0));
		tblEmp = new JTable(tblModelEmp = new DefaultTableModel(row1, 0));
		JScrollPane sp1 = new JScrollPane(tblEmp);
		sp1.setPreferredSize(new Dimension(1150, 470));
		pnTableEmp.add(sp1);
		tblEmp.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblEmp.getColumnModel().getColumn(1).setPreferredWidth(150);
		
		JPanel pnChangePage = new JPanel();
		pnChangePage.add(btnGoFirstPage = new JButton());
		btnGoFirstPage.setFocusable(false);
		Image imgFirst = new ImageIcon("images\\jump page\\first.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnGoFirstPage.setIcon(new ImageIcon(imgFirst));
		pnChangePage.add(btnPreviousPage = new JButton());
		btnPreviousPage.setFocusable(false);
		Image imgPrevious = new ImageIcon("images\\jump page\\previous.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnPreviousPage.setIcon(new ImageIcon(imgPrevious));
		pnChangePage.add(btnNextPage = new JButton());
		btnNextPage.setFocusable(false);
		Image imgNext = new ImageIcon("images\\jump page\\next.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnNextPage.setIcon(new ImageIcon(imgNext));
		pnChangePage.add(btnGoLastPage = new JButton(""));
		btnGoLastPage.setFocusable(false);
		Image imgLast = new ImageIcon("images\\jump page\\last.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnGoLastPage.setIcon(new ImageIcon(imgLast));

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
		tblDepartment.addMouseListener(this);
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
			Department newDep = new Department(depDAO.getNewDeparmentID(), txtName.getText());
			if (newDep.getDepartmentID().equals("PB00")) {
				JOptionPane.showMessageDialog(this, "Phòng ban đã đầy, hãy bảo trì hệ thống");
				return;
			}
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
					txtName.getText());
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
		String name = txtName.getText();
		if (name == null || name.trim().isEmpty() || !Pattern.matches(
				"[a-zA-Z0-9\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+",
				name))
			return false;
		return true;
	}

	public static void main(String[] args) {
		new DepartmentGUI().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
}