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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entity.Account;
import net.miginfocom.swing.MigLayout;

public class AccountGUI extends JFrame implements ActionListener {
	private JButton btnCapTaiKhoan;
	private JButton btnXoaTaiKhoan;
	private JButton btnDatLaiMatKhau;
	private JButton btnTimKiem;
	private JTextField txtTimKiem;
	private JTable tblTaiKhoan;
	private DefaultTableModel tblModel;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	private ArrayList<Account> ds;
	private int index, num;
	private List<Account> newList;

	public AccountGUI() {
		setSize(1200, 690);
		add(tabTaiKhoan());
	}

	public Component tabTaiKhoan() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnTaiKhoan = new JPanel();
		pnTaiKhoan.setSize(1200, 690);
		pnTaiKhoan.setLayout(new BoxLayout(pnTaiKhoan, BoxLayout.Y_AXIS));
		System.setProperty("Color", "0X000099");
		
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setLayout(new MigLayout("", "[]10[]10[]50", ""));
		pnTimKiem.add(new JLabel("Nhập mã/tên nhân viên: "));
		pnTimKiem.add(txtTimKiem = new JTextField());
		txtTimKiem.setColumns(30);
		pnTimKiem.add(btnTimKiem = new JButton());
		Image imgSearch = new ImageIcon("images\\Operation\\search.png").getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT);
		btnTimKiem.setIcon(new ImageIcon(imgSearch));
		
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new MigLayout("", "[]20[]20[]", ""));
		pnButton.add(btnCapTaiKhoan = new JButton("Cấp tài khoản"));
		pnButton.add(btnXoaTaiKhoan = new JButton("Xóa tài khoản"));
		pnButton.add(btnDatLaiMatKhau = new JButton("Đặt lại mật khẩu"));
		
		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách nhân viên"));
		tblTaiKhoan = new JTable();
		String[] row0 = { "A", "B"};
		tblTaiKhoan = new JTable(tblModel = new DefaultTableModel(row0, 0));
		JScrollPane sp = new JScrollPane(tblTaiKhoan);
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
		pnNorth.add(pnTimKiem);
		pnNorth.add(pnButton);
		pnTaiKhoan.add(pnNorth);
		pnTaiKhoan.add(pnTable);
		pnTable.add(pnChangePage);

		btnCapTaiKhoan.setFocusable(false);
		btnDatLaiMatKhau.setFocusable(false);
		btnXoaTaiKhoan.setFocusable(false);
		btnTimKiem.setFocusable(false);
		btnGoFirstPage.setFocusable(false);
		btnGoLastPage.setFocusable(false);
		btnNextPage.setFocusable(false);
		btnPreviousPage.setFocusable(false);
		btnGoFirstPage.addActionListener(this);
		btnGoLastPage.addActionListener(this);
		btnPreviousPage.addActionListener(this);
		btnNextPage.addActionListener(this);
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
		return pnTaiKhoan;
	}
	
	public void loadTable() {
		while (tblModel.getRowCount() != 0)
			tblModel.removeRow(0);
		for (Account x : newList) {
//			String[] row = { x.getAccountID(), x.getPassword()};
//				tblModel.addRow(row);
		}
	}

	public static void main(String[] args) {
		new AccountGUI().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
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
