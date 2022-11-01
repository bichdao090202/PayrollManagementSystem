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

import javax.swing.Box;
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
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	private DepartmentDAO depDAO;
	private JTextField txtID, txtName;
	private int index, num;
	private List<Department> newList;
	private List<Department> listDep;

	public DepartmentGUI() {
		setSize(1200, 690);
		depDAO = new DepartmentDAO();
		listDep = new ArrayList<>();
		listDep = depDAO.getAllDepartments();
		num = 29;
		index = 0;
		getContentPane().add(tabDepartment());
		refresh();
	}

	public Component tabDepartment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnDepartment = new JPanel();
		pnDepartment.setSize(1200, 690);

		JPanel pnInformation = new JPanel();
		System.setProperty("Color", "0X000099");
		pnInformation.setBorder(
				new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Nhập thông tin phòng ban"));
		pnInformation.setLayout(new MigLayout());

		pnInformation.add(Box.createHorizontalStrut(20));
		pnInformation.add(new JLabel("Mã phòng ban:    "));
		txtID = new JTextField();
		txtID.setEditable(false);
		pnInformation.add(txtID);
		txtID.setColumns(20);
		pnInformation.add(Box.createHorizontalStrut(100));

		pnInformation.add(new JLabel("Tên phòng ban:   "));
		txtName = new JTextField();
		pnInformation.add(txtName);
		txtName.setColumns(50);
		pnInformation.add(Box.createHorizontalStrut(20));

		JPanel pnButton = new JPanel();
		FlowLayout fl_pnButton = new FlowLayout();
		fl_pnButton.setHgap(20);
		pnButton.setLayout(fl_pnButton);
		btnCreate = new JButton("Thêm");
		btnCreate.setFocusable(false);
		pnButton.add(btnCreate);

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
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách phòng ban"));
		tblDepartment = new JTable();
		String[] row0 = { "Mã phòng ban", "Tên phòng ban", "Tên trưởng phòng", "Số lượng nhân viên" };
		pnTable.setLayout(new BorderLayout(0, 0));
		tblDepartment = new JTable(tblModel = new DefaultTableModel(row0, 0));
		JScrollPane sp = new JScrollPane(tblDepartment);
		sp.setPreferredSize(new Dimension(1150, 470));
		pnTable.add(sp, BorderLayout.CENTER);

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
		pnDepartment.setLayout(new BorderLayout(0, 0));

		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout(0, 0));
		pnNorth.add(pnInformation, BorderLayout.NORTH);
		pnNorth.add(pnButton);
		pnDepartment.add(pnNorth, BorderLayout.NORTH);
		pnDepartment.add(pnTable);
		pnTable.add(pnChangePage, BorderLayout.SOUTH);
		btnGoFirstPage.setFocusable(false);
		btnGoLastPage.setFocusable(false);
		btnNextPage.setFocusable(false);
		btnPreviousPage.setFocusable(false);
		btnGoFirstPage.addActionListener(this);
		btnGoLastPage.addActionListener(this);
		btnPreviousPage.addActionListener(this);
		btnNextPage.addActionListener(this);
		btnCreate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRefresh.addActionListener(this);
		tblDepartment.addMouseListener(this);
		loadTable();
		return pnDepartment;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCreate)) {
			if (tblDepartment.getSelectedRow() != -1) {
				JOptionPane.showMessageDialog(this, "Hãy nhấn nút 'Làm mới' rồi thêm phòng ban mới");
				return;
			}
			Department newDep = getDepartment();
			if (newDep == null) {
				JOptionPane.showMessageDialog(this, "Tên phòng ban không thể để trống hoặc chứa kí tự đặc biệt");
				return;
			}
			if (newDep.getDepartmentID().equals("PB00")) {
				JOptionPane.showMessageDialog(this, "Phòng ban đã đầy, hãy bảo trì hệ thống");
				return;
			}
			if (depDAO.addDepartment(newDep) == false) {
				JOptionPane.showMessageDialog(this, "Xóa phòng ban thất bại, vui lòng thử lại sau");
				return;
			}
			index += num;
			refresh();
			JOptionPane.showMessageDialog(this, "Thêm phòng ban thành công");
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
			if (depDAO.deleteDepartment(id) == false) {
				JOptionPane.showMessageDialog(this, "Xóa phòng ban thất bại, vui lòng thử lại sau");
				return;
			}
			index -= num;
			refresh();
			JOptionPane.showMessageDialog(this, "Xóa phòng ban thành công");
		}
		if (o.equals(btnUpdate)) {
			int row = tblDepartment.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn phòng ban để cập nhật");
				return;
			}
			Department newDep = getDepartment();
			if (newDep == null) {
				JOptionPane.showMessageDialog(this, "Tên phòng ban không thể để trống hoặc chứa kí tự đặc biệt");
				return;
			}
			if (depDAO.updateDepartment(newDep.getDepartmentID(), newDep.getName()) == false) {
				JOptionPane.showMessageDialog(this, "Cập nhật phòng ban thất bại, vui lòng thử lại sau");
				return;
			}
			refresh();
			JOptionPane.showMessageDialog(this, "Cập nhật phòng ban thành công");
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
		txtID.setText(depDAO.getNewDeparmentID());
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
			String[] row = { x.getDepartmentID(), x.getName(), depDAO.getNameManagerByID(x.getManagerID()), depDAO.getQuantityEmployee(x.getDepartmentID()) };
			tblModel.addRow(row);
		}
	}

	public Department getDepartment() {
		String name = txtName.getText();
		if (name == null || name.trim().isEmpty() || !Pattern.matches("[a-zA-Z0-9\s]+", name))
			return null;
		Department newDep = new Department();
		newDep.setDepartmentID(txtID.getText());
		newDep.setName(txtName.getText());
		return newDep;
	}

	public static void main(String[] args) {
		new DepartmentGUI().setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblDepartment.getSelectedRow();
		txtID.setText((String) tblDepartment.getValueAt(row, 0));
		txtName.setText((String) tblDepartment.getValueAt(row, 1));
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

}
