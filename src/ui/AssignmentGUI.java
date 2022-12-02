package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entity.Assignment;
import entity.Employee;
import entity.Product;
import entity.Produre;
import entity.Worker;
import model.AssignmentDAO;
import model.ProductDAO;
import model.ProdureDAO;
import model.WorkerDAO;

public class AssignmentGUI extends JFrame implements ActionListener, MouseListener {
	private JTable tblWorker, tblProduct, tblProdure, tblAssignment;
	private DefaultTableModel modelWorker, modelProduct, modelProdure, modelAssignment;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	private JButton btnGoFirstPage1, btnGoLastPage1, btnNextPage1, btnPreviousPage1;
	private JButton btnGoFirstPage2, btnGoLastPage2, btnNextPage2, btnPreviousPage2;
	private JButton btnGoFirstPage3, btnGoLastPage3, btnNextPage3, btnPreviousPage3;
	private int index, num, indexWorker, numWorker, indexProduct, numProduct, indexProdure, numProdure;
	private WorkerDAO daoWorker;
	private List<Worker> listWorker;
	private ProductDAO daoProduct;
	private List<Product> listProduct;
	private ProdureDAO daoProdure;
	private List<Produre> listProdure;
	private AssignmentDAO daoAssignment;
	private List<Assignment> listAssignment;
	private List<Assignment> subListAssignment;
	private List<Produre> subListProdure;
	private List<Product> subListProduct;
	private List<Worker> subListWorker;
	private JDateChooser calendar;
	private JLabel lbDate;
	private Worker worker;
	private JButton btnCreate, btnUpdate, btnDelete, btnRefresh;

	public AssignmentGUI(Employee worker) {
		setSize(1200, 690);
		daoAssignment = new AssignmentDAO();
		listAssignment = new ArrayList<>();
		daoWorker = new WorkerDAO();
		listWorker = new ArrayList<>();
		daoProduct = new ProductDAO();
		listProduct = new ArrayList<>();
		daoProdure = new ProdureDAO();
		listProdure = new ArrayList<>();
		num = 17;
		index = indexProduct = indexWorker = indexProdure = 0;
		numWorker = numProduct = numProdure = 13;
		this.worker = daoWorker.getWorkerByID(worker.getEmployeeID());
		if (this.worker == null){
			this.worker = new Worker();
			this.worker.setTeamID("admin");
		}
		getContentPane().add(getUI());
	}

	public Component getUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnAssignment = new JPanel();
		pnAssignment.setSize(1200, 690);
		pnAssignment.setLayout(new BoxLayout(pnAssignment, BoxLayout.Y_AXIS));
		System.setProperty("Color", "0X000099");

		JPanel pn1 = new JPanel();

		JPanel pnWorker = new JPanel();
		pnWorker.setBounds(0, 0, 381, 288);
		pnWorker.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Công nhân"));
		tblWorker = new JTable();
		String[] row1 = { "Mã", "Họ tên", "Tổ" };
		pn1.setLayout(null);
		pn1.add(pnWorker);
		tblWorker = new JTable(modelWorker = new DefaultTableModel(row1, 0));
		JScrollPane sp1 = new JScrollPane(tblWorker);
		tblWorker.getColumnModel().getColumn(0).setPreferredWidth(70);
		tblWorker.getColumnModel().getColumn(1).setPreferredWidth(170);
		tblWorker.getColumnModel().getColumn(2).setPreferredWidth(80);

		sp1.setLocation(10, 21);
		sp1.setSize(359, 231);
		pnWorker.add(sp1);
		sp1.setPreferredSize(new Dimension(415, 200));

		JPanel pnChangePage_1 = new JPanel();
		pnChangePage_1.setLocation(10, 249);
		pnChangePage_1.setSize(359, 34);
		pnWorker.add(pnChangePage_1);

		pnWorker.setLayout(null);
		pnChangePage_1.add(btnGoFirstPage1 = new JButton());
		btnGoFirstPage1.setLocation(0, 0);
		Image imgFirst = new ImageIcon("images\\jump page\\first.png").getImage().getScaledInstance(15, 15,
				Image.SCALE_DEFAULT);
		btnGoFirstPage1.setIcon(new ImageIcon(imgFirst));
		pnChangePage_1.add(btnPreviousPage1 = new JButton());
		Image imgPrevious = new ImageIcon("images\\jump page\\previous.png").getImage().getScaledInstance(15, 15,
				Image.SCALE_DEFAULT);
		btnPreviousPage1.setIcon(new ImageIcon(imgPrevious));
		pnChangePage_1.add(btnNextPage1 = new JButton());
		Image imgNext = new ImageIcon("images\\jump page\\next.png").getImage().getScaledInstance(15, 15,
				Image.SCALE_DEFAULT);
		btnNextPage1.setIcon(new ImageIcon(imgNext));
		pnChangePage_1.add(btnGoLastPage1 = new JButton(""));
		Image imgLast = new ImageIcon("images\\jump page\\last.png").getImage().getScaledInstance(15, 15,
				Image.SCALE_DEFAULT);
		btnGoLastPage1.setIcon(new ImageIcon(imgLast));

		JPanel pnProduct = new JPanel();
		pnProduct.setBounds(391, 0, 427, 288);
		pnProduct.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Sản phẩm"));
		tblProduct = new JTable();
		String[] row2 = { "Mã", "Tên sản phẩm","SLQT", "SLSX", "SLHT" };
		tblProduct = new JTable(modelProduct = new DefaultTableModel(row2, 0));
		JScrollPane sp2 = new JScrollPane(tblProduct);
		pnProduct.setLayout(null);
		pnProduct.add(sp2);
		sp2.setLocation(10, 21);
		sp2.setSize(406, 231);
		tblProduct.getColumnModel().getColumn(0).setPreferredWidth(70);
		tblProduct.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblProduct.getColumnModel().getColumn(2).setPreferredWidth(80);
		tblProduct.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblProduct.getColumnModel().getColumn(4).setPreferredWidth(80);
		pn1.add(pnProduct);

		JPanel pnChangePage_2 = new JPanel();
		pnChangePage_2.setLocation(10, 249);
		pnChangePage_2.setSize(380, 34);
		pnProduct.add(pnChangePage_2);

		pnChangePage_2.add(btnGoFirstPage2 = new JButton());
		btnGoFirstPage2.setLocation(0, 0);
		btnGoFirstPage2.setIcon(new ImageIcon(imgFirst));
		pnChangePage_2.add(btnPreviousPage2 = new JButton());
		btnPreviousPage2.setIcon(new ImageIcon(imgPrevious));
		pnChangePage_2.add(btnNextPage2 = new JButton());
		btnNextPage2.setIcon(new ImageIcon(imgNext));
		pnChangePage_2.add(btnGoLastPage2 = new JButton(""));
		btnGoLastPage2.setIcon(new ImageIcon(imgLast));

		JPanel pnProdure = new JPanel();
		pnProdure.setBounds(828, 0, 346, 288);
		pnProdure.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Quy trình"));
		tblProdure = new JTable();
		String[] row3 = { "Mã", "Tên quy trình", "Thứ tự" };
		tblProdure = new JTable(modelProdure = new DefaultTableModel(row3, 0));
		JScrollPane sp3 = new JScrollPane(tblProdure);
		pnProdure.setLayout(null);
		pnProdure.add(sp3);
		sp3.setLocation(10, 21);
		sp3.setSize(326, 231);
		tblProdure.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblProdure.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblProdure.getColumnModel().getColumn(2).setPreferredWidth(60);
		pn1.add(pnProdure);

		JPanel pnChangePage_3 = new JPanel();
		pnChangePage_3.setLocation(10, 249);
		pnChangePage_3.setSize(326, 34);
		pnProdure.add(pnChangePage_3);

		pnChangePage_3.add(btnGoFirstPage3 = new JButton());
		btnGoFirstPage3.setLocation(0, 0);
		btnGoFirstPage3.setIcon(new ImageIcon(imgFirst));
		pnChangePage_3.add(btnPreviousPage3 = new JButton());
		btnPreviousPage3.setIcon(new ImageIcon(imgPrevious));
		pnChangePage_3.add(btnNextPage3 = new JButton());
		btnNextPage3.setIcon(new ImageIcon(imgNext));
		pnChangePage_3.add(btnGoLastPage3 = new JButton(""));
		btnGoLastPage3.setIcon(new ImageIcon(imgLast));
		Image imgFirst1 = new ImageIcon("images\\jump page\\first.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		Image imgPrevious1 = new ImageIcon("images\\jump page\\previous.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		Image imgNext1 = new ImageIcon("images\\jump page\\next.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		Image imgLast1 = new ImageIcon("images\\jump page\\last.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);

		JPanel pn3 = new JPanel();
		pn3.setBounds(0, 291, 1033, 356);
		pn3.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Phân công"));
		tblAssignment = new JTable();
		String[] row4 = { "Mã", "Công nhân", "Sản phẩm", "Quy trình", "Ngày bắt đầu" };
		pn3.setLayout(null);
		tblAssignment = new JTable(modelAssignment = new DefaultTableModel(row4, 0));
		JScrollPane sp4 = new JScrollPane(tblAssignment);
		sp4.setBounds(10, 21, 1013, 297);
		pn3.add(sp4);
		sp4.setPreferredSize(new Dimension(1150, 300));
		tblAssignment.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblAssignment.getColumnModel().getColumn(1).setPreferredWidth(250);
		tblAssignment.getColumnModel().getColumn(2).setPreferredWidth(180);
		tblAssignment.getColumnModel().getColumn(3).setPreferredWidth(180);
		tblAssignment.getColumnModel().getColumn(4).setPreferredWidth(100);
		pn1.add(pn3);

		JPanel pnChangePage = new JPanel();
		pnChangePage.setBounds(30, 315, 992, 36);
		pn3.add(pnChangePage);
		pnChangePage.add(btnGoFirstPage = new JButton());
		btnGoFirstPage.setIcon(new ImageIcon(imgFirst1));
		pnChangePage.add(btnPreviousPage = new JButton());
		btnPreviousPage.setIcon(new ImageIcon(imgPrevious1));
		pnChangePage.add(btnNextPage = new JButton());
		btnNextPage.setIcon(new ImageIcon(imgNext1));
		pnChangePage.add(btnGoLastPage = new JButton(""));
		btnGoLastPage.setIcon(new ImageIcon(imgLast1));

		btnGoFirstPage.setFocusable(false);
		btnGoLastPage.setFocusable(false);
		btnNextPage.setFocusable(false);
		btnPreviousPage.setFocusable(false);

		JPanel pnButton = new JPanel();
		pnButton.setBounds(1035, 299, 149, 348);
		pn1.add(pnButton);
		pnButton.setLayout(null);

		pnButton.add(lbDate = new JLabel("Từ ngày: "));
		lbDate.setLocation(10, 0);
		lbDate.setSize(129, 20);

		pnButton.add(calendar = new JDateChooser(new Date()));
		calendar.setLocation(10, 23);
		calendar.setSize(129, 20);
		calendar.setDateFormatString("dd-MM-yyyy");

		pnButton.add(btnCreate = new JButton("Phân công"));
		btnCreate.setSize(129, 36);
		btnCreate.setLocation(10, 54);
		pnButton.add(btnUpdate = new JButton("Phân công lại"));
		btnUpdate.setLocation(10, 104);
		btnUpdate.setSize(129, 36);
		pnButton.add(btnDelete = new JButton("Xóa phân công"));
		btnDelete.setLocation(10, 154);
		btnDelete.setSize(129, 36);
		pnButton.add(btnRefresh = new JButton("Làm mới"));
		btnRefresh.setLocation(10, 204);
		btnRefresh.setSize(129, 36);
		JLabel lbTeam = new JLabel(worker.getTeamID());
		lbTeam.setLocation(53, 320);
		lbTeam.setSize(86, 28);
		pnButton.add(lbTeam);

		btnCreate.setFocusable(false);
		btnUpdate.setFocusable(false);
		btnDelete.setFocusable(false);
		btnRefresh.setFocusable(false);
		btnGoFirstPage1.setFocusable(false);
		btnGoLastPage1.setFocusable(false);
		btnNextPage1.setFocusable(false);
		btnPreviousPage1.setFocusable(false);
		btnGoFirstPage2.setFocusable(false);
		btnGoLastPage2.setFocusable(false);
		btnNextPage2.setFocusable(false);
		btnPreviousPage2.setFocusable(false);
		btnGoFirstPage3.setFocusable(false);
		btnGoLastPage3.setFocusable(false);
		btnNextPage3.setFocusable(false);
		btnPreviousPage3.setFocusable(false);

		tblAssignment.addMouseListener(this);
		tblProduct.addMouseListener(this);
		tblWorker.addMouseListener(this);
		tblProdure.addMouseListener(this);
		btnGoFirstPage.addActionListener(this);
		btnGoLastPage.addActionListener(this);
		btnPreviousPage.addActionListener(this);
		btnNextPage.addActionListener(this);
		btnCreate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRefresh.addActionListener(this);

		btnGoFirstPage.addActionListener(this);
		btnGoLastPage.addActionListener(this);
		btnNextPage.addActionListener(this);
		btnPreviousPage.addActionListener(this);

		btnGoFirstPage1.addActionListener(this);
		btnGoLastPage1.addActionListener(this);
		btnNextPage1.addActionListener(this);
		btnPreviousPage1.addActionListener(this);

		btnGoFirstPage2.addActionListener(this);
		btnGoLastPage2.addActionListener(this);
		btnNextPage2.addActionListener(this);
		btnPreviousPage2.addActionListener(this);

		btnGoFirstPage3.addActionListener(this);
		btnGoLastPage3.addActionListener(this);
		btnNextPage3.addActionListener(this);
		btnPreviousPage3.addActionListener(this);
		refresh();
		return pn1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		Assignment assignment;
		if (o.equals(btnCreate)) {
			assignment = getAssignment();
			if (assignment == null)
				return;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate = formatter.format(calendar.getDate());
			if (!daoAssignment.checkExistDateOfWorker(assignment.getWorkerID(), stringDate)) {
				JOptionPane.showMessageDialog(this,
						"Vào ngày này, nhân viên này đã được phân công. Hãy xóa hoặc sửa Phân công trước đó");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn thêm phân công này?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (daoAssignment.createAssignment(assignment) == false) {
					JOptionPane.showMessageDialog(this, "Phân công thất bại, vui lòng thử lại sau");
					return;
				}
				JOptionPane.showMessageDialog(this, "Phân công thành công");
				refresh();
				loadTable4();
			}	
		}
		if (o.equals(btnDelete)) {			
			int row = tblAssignment.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn phân công để xóa");
				return;
			}
			if (daoAssignment.checkExistsAssignmenDetail((String)tblAssignment.getValueAt(row, 0))) {
				JOptionPane.showMessageDialog(this, "Phân công này đã được chấm công, không thể xóa");
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa phân công này không?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				daoAssignment.deleteAssignment((String)tblAssignment.getValueAt(row, 0));
				refresh();
				JOptionPane.showMessageDialog(this, "Xóa phân công thành công");
			}
		}
		if (o.equals(btnUpdate)) {
			int rowAssignment = tblAssignment.getSelectedRow();
			if (rowAssignment == -1) {
				JOptionPane.showMessageDialog(this, "Chưa chọn phân công để phân công lại");
				return;
			}
			assignment = getAssignment();
			if (assignment == null) {
				return;
			}
			assignment.setAssignmentID(tblAssignment.getValueAt(rowAssignment, 0).toString());
			if (JOptionPane.showConfirmDialog(this, "Bạn có chắn muốn phân công lại?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (daoAssignment.updateAssignment(assignment) == false) {
					JOptionPane.showMessageDialog(this, "Thao tác thất bại, vui lòng thử lại sau");
					return;
				}
				if (index == listProdure.size() - 1)
					index -= num;
				refresh();
				JOptionPane.showMessageDialog(this, "Phân công lại thành công");
			}
		}
		if (o.equals(btnRefresh))
			refresh();
		if (o.equals(btnGoFirstPage)) {
			index = 0;
			loadTable4();
		}
		if (o.equals(btnPreviousPage)) {
			if (index == 0)
				return;
			else
				index -= num;
			loadTable4();
		}
		if (o.equals(btnNextPage)) {
			if (index + num >= listAssignment.size())
				return;
			else
				index += num;
			loadTable4();
		}
		if (o.equals(btnGoLastPage)) {
			index = (listAssignment.size() - 1) / num * num;
			loadTable4();
		}

		if (o.equals(btnGoFirstPage1)) {
			indexWorker = 0;
			loadTable1();
		}
		if (o.equals(btnPreviousPage1)) {
			if (indexWorker == 0)
				return;
			else
				indexWorker -= numWorker;
			loadTable1();
		}
		if (o.equals(btnNextPage1)) {
			if (indexWorker + numWorker >= listWorker.size())
				return;
			else
				indexWorker += numWorker;
			loadTable1();
		}
		if (o.equals(btnGoLastPage1)) {
			indexWorker = (listWorker.size() - 1) / numWorker * numWorker;
			loadTable1();
		}

		if (o.equals(btnGoFirstPage2)) {
			indexProduct = 0;
			loadTable2();
		}
		if (o.equals(btnPreviousPage2)) {
			if (indexProduct == 0)
				return;
			else
				indexProduct -= numProduct;
			loadTable2();
		}
		if (o.equals(btnNextPage2)) {
			if (indexProduct + numProduct >= listProduct.size())
				return;
			else
				indexProduct += numProduct;
			loadTable2();
		}
		if (o.equals(btnGoLastPage2)) {
			indexProduct = (listProduct.size() - 1) / numProduct * numProduct;
			loadTable2();
		}
		if (o.equals(btnGoFirstPage3)) {
			indexProdure = 0;
			String id = tblProduct.getSelectedRow() == -1 ? ""
					: tblProduct.getValueAt(tblProduct.getSelectedRow(), 0).toString();
			loadTable3(id);
		}
		if (o.equals(btnPreviousPage3)) {
			if (indexProdure == 0)
				return;
			else
				indexProdure -= numProdure;
			String id = tblProduct.getSelectedRow() == -1 ? ""
					: tblProduct.getValueAt(tblProduct.getSelectedRow(), 0).toString();
			loadTable3(id);
		}
		if (o.equals(btnNextPage3)) {
			if (indexProdure + numProdure >= listProdure.size())
				return;
			else
				indexProdure += numProdure;
			String id = tblProduct.getSelectedRow() == -1 ? ""
					: tblProduct.getValueAt(tblProduct.getSelectedRow(), 0).toString();
			loadTable3(id);
		}
		if (o.equals(btnGoLastPage3)) {
			indexProdure = (listProdure.size() - 1) / numProdure * numProdure;
			String id = tblProduct.getSelectedRow() == -1 ? ""
					: tblProduct.getValueAt(tblProduct.getSelectedRow(), 0).toString();
			loadTable3(id);
		}

	}

	public void loadTable1() {
		subListWorker = listWorker.subList(indexWorker, Math.min(indexWorker + numWorker, listWorker.size()));
		while (tblWorker.getRowCount() != 0)
			modelWorker.removeRow(0);
		for (Worker x : subListWorker) {
			String[] row = { x.getEmployeeID(), x.getName(), x.getTeamID() };
			modelWorker.addRow(row);
		}

	}

	public void loadTable2() {
		subListProduct = listProduct.subList(indexProduct, Math.min(indexProduct + numProduct, listProduct.size()));
		while (tblProduct.getRowCount() != 0)
			modelProduct.removeRow(0);
		for (Product x : subListProduct) {
			String[] row = { x.getProductID(), x.getName(), daoProdure.getAllProdureByProductID(x.getProductID()).size() + "", daoAssignment.getQuanProduct(x.getProductID())+"", daoAssignment.getQuanDoneProduct(x.getProductID())+"" };
			modelProduct.addRow(row);

		}
	}

	public void loadTable3(String id) {
		listProdure = daoProdure.getAllProdureByProductID(id);
		subListProdure = listProdure.subList(indexProdure, Math.min(indexProdure + numProdure, listProdure.size()));
		while (tblProdure.getRowCount() != 0)
			modelProdure.removeRow(0);
		for (Produre x : subListProdure) {
			String[] row = { x.getProcedureID(), x.getName(), x.getNumberOrdinal() + "" };
			modelProdure.addRow(row);
		}
	}

	public void loadTable4() {
		subListAssignment = listAssignment.subList(index, Math.min(index + num, listAssignment.size()));
		while (tblAssignment.getRowCount() != 0)
			modelAssignment.removeRow(0);
		for (Assignment x : subListAssignment) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
			String[] row = { x.getAssignmentID(),
					"(" + x.getWorkerID() + ")  " + daoWorker.getWorkerNameByID(x.getWorkerID()),
					daoProduct.getProductByProdureID(x.getProdureID()).getName(),
					daoProdure.getProdureByID(x.getProdureID()).getName(), formatter.format(x.getDate()) };
			modelAssignment.addRow(row);
		}
	}

	public void refresh() {
		index = indexProduct = indexWorker = indexProdure = 0;
		listWorker = daoWorker.getListWorker(worker.getTeamID());
		loadTable1();
		listProduct = daoProduct.getAllProduct();
		loadTable2();
		loadTable3("");
		listAssignment = daoAssignment.getAllAssignments(worker.getTeamID());
		loadTable4();
		calendar.setDate(new Date());
	}

	public Assignment getAssignment() {
		String idProdure, idWorker;
		int rowWorker = tblWorker.getSelectedRow();
		int rowProdure = tblProdure.getSelectedRow();
		if (rowWorker == -1) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn công nhân, hãy chọn công nhân để tiếp tục");
			return null;
		}
		if (rowProdure == -1) {
			JOptionPane.showMessageDialog(this,
					"Bạn chưa chọn quy trình, hãy chọn sản phẩm rồi chọn quy trình để tiếp tục");
			return null;
		}
		Assignment assignment;
		idWorker = (String) tblWorker.getValueAt(rowWorker, 0);
		idProdure = (String) tblProdure.getValueAt(rowProdure, 0);
		Date date = calendar.getDate();
		LocalDate local = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
		LocalDate now = LocalDate.now();
		Date nowDate = java.sql.Date.valueOf(now);
		if (date.compareTo(nowDate) < 0) {
			JOptionPane.showMessageDialog(this, "Ngày được chọn không thể trước ngày hiện tại");
			return null;
		}
		assignment = new Assignment(daoAssignment.getNewAssignmentID() + "", idProdure, idWorker, local);
		return assignment;
	}

	public static void main(String[] args) {
		Employee worker = new Worker();
		worker.setEmployeeID("NVSX0002");
		new AssignmentGUI(worker).setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblWorker)) {
			listAssignment = daoAssignment.getAllAssignments(worker.getTeamID());
			int row = tblWorker.getSelectedRow();
			String id = tblWorker.getValueAt(row, 0).toString();
			for (int i = 0; i < listAssignment.size(); i++) {
				if (!listAssignment.get(i).getWorkerID().equals(id)) {
					listAssignment.remove(listAssignment.get(i));
					i--;
				}
			}
			index = 0;
			loadTable4();
		}
		if (o.equals(tblProduct)) {
			int row = tblProduct.getSelectedRow();
			indexProdure = 0;
			loadTable3((String) tblProduct.getValueAt(row, 0));
		}
		if (o.equals(tblAssignment)) {
			String id = (String) tblAssignment.getValueAt(tblAssignment.getSelectedRow(), 0);
			Assignment assignment = daoAssignment.getAssignmentByID(id);

			Worker w = new Worker();
			w.setEmployeeID(assignment.getWorkerID());
			indexWorker = listWorker.indexOf(w)/numWorker*numWorker;
			loadTable1();
			for (int i = 0; i <= tblWorker.getRowCount(); i++)
				if (tblWorker.getValueAt(i, 0).equals(assignment.getWorkerID())) {
					tblWorker.setRowSelectionInterval(i, i);
					break;
				}
			String idProduct = daoProduct.getProductByProdureID(assignment.getProdureID()).getProductID();
			Product p = new Product(idProduct);
			indexProduct = listProduct.indexOf(p)/numProduct*numProduct;
			loadTable2();
			for (int i = 0; i <= tblProduct.getRowCount(); i++)
				if (tblProduct.getValueAt(i, 0).equals(idProduct)) {
					tblProduct.setRowSelectionInterval(i, i);
					break;
				}
			Produre pe = new Produre(assignment.getProdureID());
			indexProdure = 0;
			listProdure = daoProdure.getAllProdureByProductID(idProduct);
			indexProdure = listProdure.indexOf(pe)/numProdure*numProdure;
			loadTable3(idProduct);
			for (int i = 0; i <= tblProdure.getRowCount(); i++)
				if (tblProdure.getValueAt(i, 0).equals(assignment.getProdureID())) {
					tblProdure.setRowSelectionInterval(i, i);
					break;
				}
			Date date = Date.from(assignment.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			calendar.setDate(date);
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
