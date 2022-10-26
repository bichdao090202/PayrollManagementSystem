package ui;

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

public class AssignmentGUI extends JFrame implements ActionListener {
	private JTable tblWorker, tblProduct, tblProdure, tblAssignment;
	private DefaultTableModel modelWorker, modelProduct, modelProdure, modelAssignment; 
	private JTable tblDepartment;
	private DefaultTableModel tblModel;
	private JButton btnGoFirstPage, btnGoLastPage, btnNextPage, btnPreviousPage;
	
	
	private JButton btnAssignment, btnChangeAssignment, btnDeleteAssignment;
	public AssignmentGUI() {
		setSize(1200,690);
		add(tabAssignment());
	}
	
	public Component tabAssignment(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnAssignment = new JPanel();
		pnAssignment.setSize(1200,690);
		pnAssignment.setLayout(new BoxLayout(pnAssignment, BoxLayout.Y_AXIS));
		System.setProperty("Color", "0X000099");
		
		JPanel pn1 = new JPanel();	
		pn1.setLayout(new MigLayout("", "[]20[]20[]", ""));
		
		JPanel pnWorker = new JPanel();
		pnWorker.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách công nhân"));	
		tblWorker = new JTable();
		String[] row1 = { "Mã","Họ tên"};
		tblWorker = new JTable(modelWorker = new DefaultTableModel(row1, 0));
		JScrollPane sp1 = new JScrollPane(tblWorker);
		pnWorker.add(sp1);
		sp1.setPreferredSize(new Dimension(350, 200));
		pn1.add(pnWorker, "cell 0 0");
		
		JPanel pnProduct = new JPanel();
		pnProduct.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Sản phẩm"));	
		tblProduct = new JTable();
		String[] row2 = { "A","B","B","C" };
		tblProduct = new JTable(modelProduct = new DefaultTableModel(row2, 0));
		JScrollPane sp2 = new JScrollPane(tblProduct);
		pnProduct.add(sp2);
		sp2.setPreferredSize(new Dimension(350, 200));
		pn1.add(pnProduct, "cell 1 0");
		
		JPanel pnProdure = new JPanel();
		pnProdure.setBorder(new TitledBorder(new LineBorder(Color.getColor("Color"), 1, true), "Danh sách Quy trình"));	
		tblProdure = new JTable();
		String[] row3 = { "A","B","B","C" };
		tblProdure = new JTable(modelProdure = new DefaultTableModel(row3, 0));
		JScrollPane sp3 = new JScrollPane(tblProdure);
		pnProdure.add(sp3);
		sp3.setPreferredSize(new Dimension(350, 200));
		pn1.add(pnProdure, "cell 2 0");
		
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new MigLayout("", "[]20[]20[]", ""));
		pnButton.add(btnAssignment = new JButton("Phân công"));
		pnButton.add(btnChangeAssignment = new JButton("Phân công lại"));
		pnButton.add(btnDeleteAssignment = new JButton("Xóa phân công"));
		
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
		tblAssignment = new JTable();
		String[] row4 = { "A","B","B","C" };
		tblAssignment = new JTable(modelAssignment = new DefaultTableModel(row4, 0));
		JScrollPane sp4 = new JScrollPane(tblAssignment);
		pn3.add(sp4);
		sp4.setPreferredSize(new Dimension(1150, 300));
		
		pn1.add(pnButton,"cell 1 1");
		pn1.add(pnChangePage, "dock south");
		pn1.add(pn3, "dock south");
		btnAssignment.setFocusable(false);
		btnChangeAssignment.setFocusable(false);
		btnDeleteAssignment.setFocusable(false);
		return pn1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object action = e.getSource();
		
	}
	
	public static void main(String[] args) {
		new AssignmentGUI().setVisible(true);;
	}
}
