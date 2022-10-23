package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class PhanCong extends JFrame implements ActionListener {
	private JTable tblNhanVien, tblSanPham, tblQuyTrinh, tblPhanCong;
	private DefaultTableModel modelNhanVien, modelSanPham, modelQuyTrinh, modelPhanCong; 
	private JTable tblPhongBan;
	private DefaultTableModel tblModel;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	
	
	private JButton btnPhanCong, btnPhanCongLai, btnXoaPhanCong;
	public PhanCong() {
		setSize(1200,690);
		add(tabPhanCong());
	}
	
	public Component tabPhanCong(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnPhanCong = new JPanel();
		pnPhanCong.setSize(1200,690);
		pnPhanCong.setLayout(new BoxLayout(pnPhanCong, BoxLayout.Y_AXIS));
		System.setProperty("Color", "0X000099");
		
		JPanel pn1 = new JPanel();	
		pn1.setLayout(new MigLayout("", "[]20[]20[]", ""));
		
		JPanel pnNhanVien = new JPanel();
		pnNhanVien.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách nhân viên"));	
		tblNhanVien = new JTable();
		String[] row1 = { "A","B","B","C" };
		tblNhanVien = new JTable(modelNhanVien = new DefaultTableModel(row1, 0));
		JScrollPane sp1 = new JScrollPane(tblNhanVien);
		pnNhanVien.add(sp1);
		sp1.setPreferredSize(new Dimension(350, 200));
		pn1.add(pnNhanVien, "cell 0 0");
		
		JPanel pnSanPham = new JPanel();
		pnSanPham.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Sản phẩm"));	
		tblSanPham = new JTable();
		String[] row2 = { "A","B","B","C" };
		tblSanPham = new JTable(modelSanPham = new DefaultTableModel(row2, 0));
		JScrollPane sp2 = new JScrollPane(tblSanPham);
		pnSanPham.add(sp2);
		sp2.setPreferredSize(new Dimension(350, 200));
		pn1.add(pnSanPham, "cell 1 0");
		
		JPanel pnQuyTrinh = new JPanel();
		pnQuyTrinh.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Quy trình"));	
		tblQuyTrinh = new JTable();
		String[] row3 = { "A","B","B","C" };
		tblQuyTrinh = new JTable(modelQuyTrinh = new DefaultTableModel(row3, 0));
		JScrollPane sp3 = new JScrollPane(tblQuyTrinh);
		pnQuyTrinh.add(sp3);
		sp3.setPreferredSize(new Dimension(350, 200));
		pn1.add(pnQuyTrinh, "cell 2 0");
		
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new MigLayout("", "[]20[]20[]", ""));
		pnButton.add(btnPhanCong = new JButton("Phân công"));
		pnButton.add(btnPhanCongLai = new JButton("Phân công lại"));
		pnButton.add(btnXoaPhanCong = new JButton("Xóa phân công"));
		
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
		
		JPanel pn3 = new JPanel();
		pn3.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Phân công"));	
		tblPhanCong = new JTable();
		String[] row4 = { "A","B","B","C" };
		tblPhanCong = new JTable(modelPhanCong = new DefaultTableModel(row4, 0));
		JScrollPane sp4 = new JScrollPane(tblPhanCong);
		pn3.add(sp4);
		sp4.setPreferredSize(new Dimension(1150, 300));
		
//		pn3.add(pnChangePage);
		
		pn1.add(pnButton,"cell 1 1");
		pn1.add(pnChangePage, "dock south");
		pn1.add(pn3, "dock south");
		btnPhanCong.setFocusable(false);
		btnPhanCongLai.setFocusable(false);
		btnXoaPhanCong.setFocusable(false);
		return pn1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		
	}
	
	public static void main(String[] args) {
		new PhanCong().setVisible(true);;
	}
}
