package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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

public class PhongBan extends JFrame implements ActionListener{
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JTable tblPhongBan;
	private DefaultTableModel tblModel;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	
	public PhongBan() {
		setSize(1200,690);
		add(tabPhongBan());
	}
	
	public Component tabPhongBan(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnPhongBan = new JPanel();
		pnPhongBan.setSize(1200,690);
		pnPhongBan.setLayout(new BoxLayout(pnPhongBan, BoxLayout.Y_AXIS));
		
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
		pnButton.setLayout(new MigLayout("","[]50[]50[]",""));
		pnButton.add(btnThem=new JButton("Thêm"));
		Image imgThem = new ImageIcon("images\\Operation\\new.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnThem.setIcon(new ImageIcon(imgThem));
		pnButton.add(btnXoa=new JButton("Xóa"));
		Image imgXoa = new ImageIcon("images\\Operation\\delete.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnXoa.setIcon(new ImageIcon(imgXoa));
		pnButton.add(btnCapNhat=new JButton("Cập nhật"));
		Image imgCapNhat = new ImageIcon("images\\Operation\\update.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnCapNhat.setIcon(new ImageIcon(imgCapNhat));
		
		JPanel pnTable = new JPanel();
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách phòng ban"));
		tblPhongBan = new JTable();
		String[] row0 = { "A","B","B","C" };
		tblPhongBan = new JTable(tblModel = new DefaultTableModel(row0, 0));
		JScrollPane sp = new JScrollPane(tblPhongBan);
		sp.setPreferredSize(new Dimension(1150, 470));
		pnTable.add(sp);
		
		JPanel pnChangePage = new JPanel();
		pnChangePage.add(btnGoFirstPage = new JButton());
		Image imgFirst = new ImageIcon("images\\jump page\\first.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnGoFirstPage.setIcon(new ImageIcon(imgFirst));
		pnChangePage.add(btnPreviousPage = new JButton());
		Image imgPrevious = new ImageIcon("images\\jump page\\previous.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnPreviousPage.setIcon(new ImageIcon(imgPrevious));
		pnChangePage.add(btnNextPage = new JButton());
		Image imgNext = new ImageIcon("images\\jump page\\next.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnNextPage.setIcon(new ImageIcon(imgNext));
		pnChangePage.add(btnGoLastPage = new JButton(""));
		Image imgLast = new ImageIcon("images\\jump page\\last.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		btnGoLastPage.setIcon(new ImageIcon(imgLast));
		
		JPanel pnNorth = new JPanel();
		pnNorth.add(pnNhapThongTin);
		pnNorth.add(pnButton);
		pnPhongBan.add(pnNorth);
		pnPhongBan.add(pnTable);	
		pnTable.add(pnChangePage);
		
		btnThem.setFocusable(false);
		btnXoa.setFocusable(false);
		btnCapNhat.setFocusable(false);
		btnGoFirstPage.setFocusable(false);
		btnGoLastPage.setFocusable(false);
		btnNextPage.setFocusable(false);
		btnPreviousPage.setFocusable(false);
		return pnPhongBan;
	}
	

	public static void main(String[] args) {
		new PhongBan().setVisible(true);;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
