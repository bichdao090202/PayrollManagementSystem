package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

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

import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;

public class DepartmentGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JTable tblPhongBan;
	private DefaultTableModel tblModel;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	
	public DepartmentGUI() {
		setSize(1200,690);
		getContentPane().add(tabPhongBan());
	}
	
	public Component tabPhongBan(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnPhongBan = new JPanel();
		pnPhongBan.setSize(1200,690);
		
		JPanel pnNhapThongTin = new JPanel();
		System.setProperty("Color", "0X000099");
		pnNhapThongTin.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Nhập thông tin phòng ban"));
		pnNhapThongTin.setLayout(new MigLayout());
		
		pnNhapThongTin.add(Box.createHorizontalStrut(20));
		pnNhapThongTin.add(new JLabel("Mã phòng ban:    "));
		JTextField txtMaPB = new JTextField();
		txtMaPB.setEditable(false);
		pnNhapThongTin.add(txtMaPB);
		txtMaPB.setColumns(20);
		pnNhapThongTin.add(Box.createHorizontalStrut(100));
	
		pnNhapThongTin.add(new JLabel("Tên phòng ban:   "));
		JTextField txtTenPB = new JTextField();
		pnNhapThongTin.add(txtTenPB);
		txtTenPB.setColumns(50);
		pnNhapThongTin.add(Box.createHorizontalStrut(20));
	
		JPanel pnButton = new JPanel();
		FlowLayout fl_pnButton = new FlowLayout();
		fl_pnButton.setHgap(20);
		pnButton.setLayout(fl_pnButton);
		btnThem=new JButton("Thêm");
		btnThem.setFocusable(false);
		pnButton.add(btnThem);
		
		Image imgThem = new ImageIcon("images\\operations\\new.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnThem.setIcon(new ImageIcon(imgThem));
		pnButton.add(btnXoa=new JButton("Xóa"));
		btnXoa.setFocusable(false);
		Image imgXoa = new ImageIcon("images\\operations\\delete.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnXoa.setIcon(new ImageIcon(imgXoa));
		pnButton.add(btnCapNhat=new JButton("Cập nhật"));
		btnCapNhat.setFocusable(false);
		Image imgCapNhat = new ImageIcon("images\\operations\\update.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnCapNhat.setIcon(new ImageIcon(imgCapNhat));
		
		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách phòng ban"));
		tblPhongBan = new JTable();
		String[] row0 = { "A","B","B","C" };
		pnTable.setLayout(new BorderLayout(0, 0));
		tblPhongBan = new JTable(tblModel = new DefaultTableModel(row0, 0));
		JScrollPane sp = new JScrollPane(tblPhongBan);
		sp.setPreferredSize(new Dimension(1150, 470));
		pnTable.add(sp, BorderLayout.CENTER);
		
		JPanel pnChangePage = new JPanel();
		pnChangePage.add(btnGoFirstPage = new JButton());
		btnGoFirstPage.setFocusable(false);
		Image imgFirst = new ImageIcon("images\\jump page\\first.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnGoFirstPage.setIcon(new ImageIcon(imgFirst));
		pnChangePage.add(btnPreviousPage = new JButton());
		btnPreviousPage.setFocusable(false);
		Image imgPrevious = new ImageIcon("images\\jump page\\previous.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnPreviousPage.setIcon(new ImageIcon(imgPrevious));
		pnChangePage.add(btnNextPage = new JButton());
		btnNextPage.setFocusable(false);
		Image imgNext = new ImageIcon("images\\jump page\\next.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnNextPage.setIcon(new ImageIcon(imgNext));
		pnChangePage.add(btnGoLastPage = new JButton(""));
		btnGoLastPage.setFocusable(false);
		Image imgLast = new ImageIcon("images\\jump page\\last.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnGoLastPage.setIcon(new ImageIcon(imgLast));
		pnPhongBan.setLayout(new BorderLayout(0, 0));
		
		JPanel pnNorth = new JPanel();
		pnNorth.setLayout(new BorderLayout(0, 0));
		pnNorth.add(pnNhapThongTin, BorderLayout.NORTH);
		pnNorth.add(pnButton);
		pnPhongBan.add(pnNorth, BorderLayout.NORTH);
		pnPhongBan.add(pnTable);	
		pnTable.add(pnChangePage, BorderLayout.SOUTH);
		
		return pnPhongBan;
	}

	public static void main(String[] args) {
		new DepartmentGUI().setVisible(true);;
	}

}
