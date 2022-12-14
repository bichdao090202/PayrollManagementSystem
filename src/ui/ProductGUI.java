package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import com.itextpdf.awt.geom.misc.RenderingHints.Key;

import custom_field.JTextFieldHint;
import model.DetailPRoductionDAO;
import model.ProductDAO;
import entity.Product;
import entity.Produre;
import entity.TopProduct;

import javax.swing.ImageIcon;
//import javax.swing.JComboBox;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JScrollBar;

import java.io.*;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D; 
import org.jfree.data.general.DefaultPieDataset; 
import org.jfree.chart.ChartUtilities;

public class ProductGUI extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private JTextField txtIdProduct;
	private JTextField txtNameProduct;
	private JTextField txtIdProcedure;
	private JTextField txtNameProcedure;
	private JTextField txtPriceProcedure;
	private JTextField txtSearchProduct;
	private JTextField txtSearchProcedure;
	private JPanel pnlProcedure;
	private JSpinner spnOrder;
	private JButton btnInsertProcedure;
	private JButton btnInsertProduct;
	private JButton btnSearchIdProduct;
	private JButton btnDeleteProduct;
	private JButton btnUpdateProduct;
	private JButton btnSearchIdProcedure;
	private JButton btnDeleteProcedure;
	private JButton btnUpdateProcedure;
	private JButton btnClean;
	private JButton btnChange;
	private JButton btnModal;
	private JTable tblListProduct;
	private JTable tblListProcedure;
	private JTable tblProcedure;
	private DefaultTableModel dtmProduct;
	private DefaultTableModel dtmListProcedure;
	private DefaultTableModel dtmProcedure;
	private ProductDAO Dao_Product = new ProductDAO();
	private DetailPRoductionDAO detailDAO = new DetailPRoductionDAO();
	private List<Product> listProduct = new ArrayList<Product>();
	private List<Produre> ListProcedure = new ArrayList<Produre>();
	private List<Produre> ListProcedureSearch = new ArrayList<Produre>();
	private List<entity.DetailProduction> listDetails = new ArrayList<entity.DetailProduction>();
	private List<TopProduct> listTopProduct = Dao_Product.getTopFiveProduct();
	private int rowCountTblProcedure = 0;
	private String prevState;
	private JLabel lblChart;

	public ProductGUI() {
		getContentPane().setBackground(Color.WHITE);
//		getUI();
	}
	
	public Component getUI() {
		setSize(1200, 690);
		getContentPane().setLayout(null);

		JPanel pnlProduct = new JPanel();
		pnlProduct.setBackground(Color.WHITE);
		pnlProduct.setBorder(new TitledBorder(new LineBorder(new Color(0, 140, 140)), "S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 140, 140)));
		pnlProduct.setBounds(10, 10, 460, 191);
		getContentPane().add(pnlProduct);
		pnlProduct.setLayout(null);

		JLabel lblIdProduct = new JLabel("Mã sản phẩm:");
		lblIdProduct.setForeground(Color.BLACK);
		lblIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdProduct.setBounds(37, 35, 87, 13);
		pnlProduct.add(lblIdProduct);

		txtIdProduct = new JTextField();
		txtIdProduct.setBackground(Color.WHITE);
		txtIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdProduct.setBounds(162, 35, 245, 19);
		pnlProduct.add(txtIdProduct);
		txtIdProduct.setColumns(10);

		JLabel lblNameProduct = new JLabel("Tên sản phẩm:");
		lblNameProduct.setForeground(Color.BLACK);
		lblNameProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameProduct.setBounds(37, 75, 87, 13);
		pnlProduct.add(lblNameProduct);

		txtNameProduct = new JTextField();
		txtNameProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameProduct.setColumns(10);
		txtNameProduct.setBounds(162, 75, 245, 19);
		pnlProduct.add(txtNameProduct);
		txtNameProduct.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (Dao_Product.searchProductByIdProduct(txtIdProduct.getText()) == null
						&& dtmProcedure.getRowCount() == 0 && txtNameProcedure.getText().isEmpty()
						&& txtPriceProcedure.getText().isEmpty() && !txtIdProduct.getText().isEmpty()) {
					randomIdProcedure();

				}
			}

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		JLabel lblDetailProduction = new JLabel("Chi Tiết Sản Xuất : ");
		lblDetailProduction.setForeground(Color.BLACK);
		lblDetailProduction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDetailProduction.setBounds(37, 115, 115, 13);
		pnlProduct.add(lblDetailProduction);

		btnClean = new JButton();
		btnClean.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClean.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnClean.setBackground(Color.WHITE);
			}
		});
		btnClean.setBorder(new LineBorder(COLOR, 2, false));
		btnClean.setForeground(COLOR);
		btnClean.setBackground(Color.WHITE);
		btnClean.setFocusable(false);
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClean.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnClean.setBounds(37, 149, 55, 21);
		pnlProduct.add(btnClean);

		pnlProcedure = new JPanel();
		pnlProcedure.setBackground(Color.WHITE);
		pnlProcedure.setBorder(new TitledBorder(new LineBorder(new Color(0, 140, 140)), "Quy tr\u00ECnh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 140, 140)));
		pnlProcedure.setBounds(513, 10, 485, 191);
		getContentPane().add(pnlProcedure);
		pnlProcedure.setLayout(null);

		JLabel lblIdProcedure = new JLabel("Mã quy trình :");
		lblIdProcedure.setForeground(Color.BLACK);
		lblIdProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdProcedure.setBounds(40, 32, 87, 13);
		pnlProcedure.add(lblIdProcedure);

		txtIdProcedure = new JTextField();
		txtIdProcedure.setBackground(Color.WHITE);
		txtIdProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdProcedure.setColumns(10);
		txtIdProcedure.setBounds(137, 29, 106, 19);
		pnlProcedure.add(txtIdProcedure);
		txtIdProcedure.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
//	            System.out.println("focus gained");
				if (btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
					btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
					btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
				}
			}/* from w ww . j a va2 s. c o m */

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		JLabel lblNameProcedure = new JLabel("Tên quy trình :");
		lblNameProcedure.setForeground(Color.BLACK);
		lblNameProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameProcedure.setBounds(40, 70, 87, 13);
		pnlProcedure.add(lblNameProcedure);

		txtNameProcedure = new JTextField();
		txtNameProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameProcedure.setColumns(10);
		txtNameProcedure.setBounds(137, 67, 130, 19);
		pnlProcedure.add(txtNameProcedure);
		txtNameProcedure.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
//	            System.out.println("focus gained");
				if (btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
					btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
					btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
				}
				else if(txtIdProcedure.getText().isEmpty()) {
					randomIdProcedure();
				}
			}/* from w ww . j a va2 s. c o m */

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		JLabel lblPriceProcedure = new JLabel("Giá :");
		lblPriceProcedure.setForeground(Color.BLACK);
		lblPriceProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPriceProcedure.setBounds(294, 32, 30, 13);
		pnlProcedure.add(lblPriceProcedure);

		txtPriceProcedure = new JTextField();
		txtPriceProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPriceProcedure.setColumns(10);
		txtPriceProcedure.setBounds(330, 29, 106, 19);
		pnlProcedure.add(txtPriceProcedure);
		txtPriceProcedure.addFocusListener((FocusListener) new FocusListener() {
			public void focusGained(FocusEvent e) {
//	            System.out.println("focus gained");
				if (btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
					btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
					btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
				}
				else if(txtIdProcedure.getText().isEmpty()) {
					randomIdProcedure();
				}
			}/* from w ww . j a va2 s. c o m */

			public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
			}
		});

		btnInsertProcedure = new JButton();
		btnInsertProcedure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnInsertProcedure.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnInsertProcedure.setBackground(Color.WHITE);
			}
		});
		btnInsertProcedure.setBorder(new LineBorder(COLOR, 2, false));
		btnInsertProcedure.setForeground(COLOR);
		btnInsertProcedure.setBackground(Color.WHITE);
		btnInsertProcedure.setFocusable(false);
		btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnInsertProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertProcedure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInsertProcedure.setBounds(310, 67, 70, 21);
		pnlProcedure.add(btnInsertProcedure);

		JScrollPane scrProcedure = new JScrollPane();
		scrProcedure.setBounds(40, 93, 396, 88);
		pnlProcedure.add(scrProcedure);

		tblProcedure = new JTable();
		tblProcedure.setFillsViewportHeight(true);
		tblProcedure.setBackground(Color.WHITE);
		tblProcedure.setGridColor(new Color(0, 140, 140));
		tblProcedure.setBorder(new LineBorder(new Color(0, 140, 140)));
		tblProcedure.setForeground(Color.BLACK);
		JTableHeader tblHeaderProcedure = tblProcedure.getTableHeader();
		tblHeaderProcedure.setBackground(new Color(14,85,78));
		tblHeaderProcedure.setForeground(Color.WHITE);
		tblProcedure.setModel(dtmProcedure = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã quy trình", "Tên quy trình", "Giá", "Thứ tự" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		tblProcedure.getColumnModel().getColumn(1).setPreferredWidth(152);
		tblProcedure.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblProcedure.getColumnModel().getColumn(3).setPreferredWidth(45);
		tblProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrProcedure.setViewportView(tblProcedure);

		btnInsertProduct = new JButton("Thêm sản phẩm");
		btnInsertProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnInsertProduct.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnInsertProduct.setBackground(Color.WHITE);
			}
		});
		btnInsertProduct.setBorder(new LineBorder(COLOR, 2, false));
		btnInsertProduct.setForeground(COLOR);
		btnInsertProduct.setBackground(Color.WHITE);
		btnInsertProduct.setFocusable(false);
		btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnInsertProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertProduct.setBounds(1022, 90, 154, 40);
		getContentPane().add(btnInsertProduct);

		JPanel pnlListProducts = new JPanel();
		pnlListProducts.setBackground(Color.WHITE);
		pnlListProducts.setBounds(0, 211, 1186, 442);
		getContentPane().add(pnlListProducts);
		pnlListProducts.setLayout(null);

		JPanel pnlListProduct = new JPanel();
		pnlListProduct.setBackground(Color.WHITE);
		pnlListProduct.setBorder(new TitledBorder(new LineBorder(new Color(0, 140, 140)), "Danh sách sản phẩm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 140, 140)));
		pnlListProduct.setBounds(10, 10, 580, 432);
		pnlListProducts.add(pnlListProduct);
		pnlListProduct.setLayout(null);

		txtSearchProduct = new JTextFieldHint("Nhập mã sản phẩm...");
		txtSearchProduct.setPreferredSize(new Dimension(200, 25));
		txtSearchProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchProduct.setColumns(10);
		txtSearchProduct.setBounds(10, 18, 277, 19);
		pnlListProduct.add(txtSearchProduct);

		btnSearchIdProduct = new JButton();
		btnSearchIdProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearchIdProduct.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSearchIdProduct.setBackground(Color.WHITE);
			}
		});
		btnSearchIdProduct.setBorder(new LineBorder(COLOR, 2, false));
		btnSearchIdProduct.setForeground(COLOR);
		btnSearchIdProduct.setBackground(Color.WHITE);
		btnSearchIdProduct.setFocusable(false);
		btnSearchIdProduct.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdProduct.setBounds(297, 18, 70, 21);
		pnlListProduct.add(btnSearchIdProduct);

		btnDeleteProduct = new JButton();
		btnDeleteProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDeleteProduct.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDeleteProduct.setBackground(Color.WHITE);
			}
		});
		btnDeleteProduct.setBorder(new LineBorder(COLOR, 2, false));
		btnDeleteProduct.setForeground(COLOR);
		btnDeleteProduct.setBackground(Color.WHITE);
		btnDeleteProduct.setFocusable(false);
		btnDeleteProduct.setIcon(new ImageIcon("images\\Close-2-icon.png"));
		btnDeleteProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteProduct.setBounds(382, 18, 70, 21);
		pnlListProduct.add(btnDeleteProduct);

		btnUpdateProduct = new JButton();
		btnUpdateProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdateProduct.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdateProduct.setBackground(Color.WHITE);
			}
		});
		btnUpdateProduct.setBorder(new LineBorder(COLOR, 2, false));
		btnUpdateProduct.setForeground(COLOR);
		btnUpdateProduct.setBackground(Color.WHITE);
		btnUpdateProduct.setFocusable(false);
		btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnUpdateProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateProduct.setBounds(462, 18, 70, 21);
		pnlListProduct.add(btnUpdateProduct);

		JScrollPane scrProduct = new JScrollPane();
		scrProduct.setBounds(10, 45, 560, 377);
		pnlListProduct.add(scrProduct);

		tblListProduct = new JTable();
		tblListProduct.setFillsViewportHeight(true);
		tblListProduct.setBackground(Color.WHITE);
		tblListProduct.setForeground(Color.BLACK);
		tblListProduct.setGridColor(new Color(0, 140, 140));
		tblListProduct.setRowHeight(25);
		tblListProduct.setBorder(new LineBorder(new Color(0, 140, 140)));
		JTableHeader tblHeaderListProduct = tblListProduct.getTableHeader();
		tblHeaderListProduct.setBackground(new Color(14,85,78));
		tblHeaderListProduct.setForeground(Color.WHITE);
		tblHeaderListProduct.setPreferredSize(new Dimension(100, 30));
		tblHeaderListProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblListProduct.setModel(dtmProduct = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã sản phẩm", "Tên sản phẩm"}) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrProduct.setViewportView(tblListProduct);

		JPanel pnlListProcedure = new JPanel();
		pnlListProcedure.setBackground(Color.WHITE);
		pnlListProcedure.setBorder(new TitledBorder(new LineBorder(new Color(0, 140, 140)), "Chi tiết sản phẩm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 140, 140)));
		pnlListProcedure.setBounds(596, 10, 580, 432);
		pnlListProducts.add(pnlListProcedure);
		pnlListProcedure.setLayout(null);

		JScrollPane scrListProcedure = new JScrollPane();
		scrListProcedure.setBounds(10, 45, 560, 150);
		pnlListProcedure.add(scrListProcedure);

		tblListProcedure = new JTable();
		tblListProcedure.setBackground(Color.WHITE);
		tblListProcedure.setFillsViewportHeight(true);
		tblListProcedure.setForeground(Color.BLACK);
		tblListProcedure.setGridColor(new Color(0, 140, 140));
		tblListProcedure.setRowHeight(25);
		tblListProcedure.setBorder(new LineBorder(new Color(0, 140, 140)));
		JTableHeader tblHeaderListProcedure = tblListProcedure.getTableHeader();
		tblHeaderListProcedure.setBackground(new Color(14,85,78));
		tblHeaderListProcedure.setForeground(Color.WHITE);
		tblHeaderListProcedure.setPreferredSize(new Dimension(100, 30));
		tblHeaderListProcedure.setFont(new Font("Tahoma", Font.BOLD, 12));
		tblListProcedure.setModel(dtmListProcedure = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã quy trình", "Tên quy trình", "Giá", "Thứ tự" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		tblListProcedure.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblListProcedure.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblListProcedure.getColumnModel().getColumn(3).setPreferredWidth(50);
		scrListProcedure.setViewportView(tblListProcedure);

		txtSearchProcedure = new JTextFieldHint("Nhập mã quy trình...");
		txtSearchProcedure.setPreferredSize(new Dimension(200, 25));
		txtSearchProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchProcedure.setColumns(10);
		txtSearchProcedure.setBounds(10, 18, 277, 19);
		pnlListProcedure.add(txtSearchProcedure);

		btnSearchIdProcedure = new JButton();
		btnSearchIdProcedure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearchIdProcedure.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSearchIdProcedure.setBackground(Color.WHITE);
			}
		});
		btnSearchIdProcedure.setBorder(new LineBorder(COLOR, 2, false));
		btnSearchIdProcedure.setForeground(COLOR);
		btnSearchIdProcedure.setBackground(Color.WHITE);
		btnSearchIdProcedure.setFocusable(false);
		btnSearchIdProcedure.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchIdProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdProcedure.setBounds(297, 17, 70, 21);
		pnlListProcedure.add(btnSearchIdProcedure);

		btnDeleteProcedure = new JButton();
		btnDeleteProcedure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDeleteProcedure.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDeleteProcedure.setBackground(Color.WHITE);
			}
		});
		btnDeleteProcedure.setBorder(new LineBorder(COLOR, 2, false));
		btnDeleteProcedure.setForeground(COLOR);
		btnDeleteProcedure.setBackground(Color.WHITE);
		btnDeleteProcedure.setFocusable(false);
		btnDeleteProcedure.setIcon(new ImageIcon("images\\Close-2-icon.png"));
		btnDeleteProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteProcedure.setBounds(382, 17, 70, 21);
		pnlListProcedure.add(btnDeleteProcedure);

		btnUpdateProcedure = new JButton();
		btnUpdateProcedure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdateProcedure.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdateProcedure.setBackground(Color.WHITE);
			}
		});
		btnUpdateProcedure.setBorder(new LineBorder(COLOR, 2, false));
		btnUpdateProcedure.setForeground(COLOR);
		btnUpdateProcedure.setBackground(Color.WHITE);
		btnUpdateProcedure.setFocusable(false);
		btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnUpdateProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateProcedure.setBounds(462, 17, 70, 21);
		pnlListProcedure.add(btnUpdateProcedure);

		btnChange = new JButton();
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnChange.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnChange.setBackground(Color.WHITE);
			}
		});
		btnChange.setBorder(new LineBorder(COLOR, 2, false));
		btnChange.setForeground(COLOR);
		btnChange.setBackground(Color.WHITE);
		btnChange.setFocusable(false);
		btnChange.setBounds(386, 67, 50, 21);
		pnlProcedure.add(btnChange);
		btnChange.setIcon(new ImageIcon("images\\exchange.png"));
		if (btnInsertProcedure.getIcon().toString().equals("images\\math-add-icon.png")) {
			btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		} else if (btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
			btnChange.setIcon(new ImageIcon("images\\exchange.png"));
		}
		btnModal = new JButton("Thêm/Sửa/Xóa Hợp Đồng");
		btnModal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnModal.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnModal.setBackground(Color.WHITE);
			}
		});
		btnModal.setBorder(new LineBorder(COLOR, 2, false));
		btnModal.setForeground(COLOR);
		btnModal.setBackground(Color.WHITE);
		btnModal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnModal.setBounds(162, 112, 245, 25);
		pnlProduct.add(btnModal);
		txtIdProcedure.setEditable(false);

		spnOrder = new JSpinner();
		spnOrder.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spnOrder.setBounds(270, 67, 30, 20);
		pnlProcedure.add(spnOrder);
//		tblHeaderListDetail.setBackground(new Color(14,85,78));
//		tblHeaderListDetail.setForeground(Color.WHITE);
//		tblHeaderListDetail.setPreferredSize(new Dimension(100, 30));
//		tblHeaderListDetail.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		loadListProcedure();
		randomIdProduct();
		txtIdProduct.setEditable(false);
		txtIdProcedure.setEditable(false);
		
		DocumentListener enventChangeSearchProduct = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				listProduct = Dao_Product.searchListProduct(txtSearchProduct.getText());
				deleteDataOnTableModelProduct();
				loadListProductWithListProducted(listProduct);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				listProduct = Dao_Product.searchListProduct(txtSearchProduct.getText());
				deleteDataOnTableModelProduct();
				loadListProductWithListProducted(listProduct);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				listProduct = Dao_Product.searchListProduct(txtSearchProduct.getText());
				deleteDataOnTableModelProduct();
				loadListProductWithListProducted(listProduct);
			}

        };
        
        DocumentListener enventChangeSearchProdure = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				ListProcedureSearch = Dao_Product.searchListProdure(txtSearchProcedure.getText());
				deleteDataOnTableModel(dtmListProcedure);
				loadListProcedureWithListProdured(ListProcedureSearch,dtmListProcedure);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				ListProcedureSearch = Dao_Product.searchListProdure(txtSearchProcedure.getText());
				deleteDataOnTableModel(dtmListProcedure);
				loadListProcedureWithListProdured(ListProcedureSearch,dtmListProcedure);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				ListProcedureSearch = Dao_Product.searchListProdure(txtSearchProcedure.getText());
				deleteDataOnTableModel(dtmListProcedure);
				loadListProcedureWithListProdured(ListProcedureSearch,dtmListProcedure);
			}

        };
        
        txtSearchProduct.getDocument().addDocumentListener(enventChangeSearchProduct);
        txtSearchProcedure.getDocument().addDocumentListener(enventChangeSearchProdure);
		chartTopFiveProduct(listTopProduct);
        
		btnClean.addActionListener(this);
		btnChange.addActionListener(this);
		btnInsertProcedure.addActionListener(this);
		btnInsertProduct.addActionListener(this);
		btnSearchIdProduct.addActionListener(this);
		btnSearchIdProcedure.addActionListener(this);
		btnDeleteProduct.addActionListener(this);
		btnDeleteProcedure.addActionListener(this);
		btnUpdateProduct.addActionListener(this);
		btnUpdateProcedure.addActionListener(this);
		btnModal.addActionListener(this);
		
		tblListProduct.addMouseListener(this);
		tblListProcedure.addMouseListener(this);
		tblProcedure.addMouseListener(this);
		
		btnClean.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnChange.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnInsertProcedure.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnInsertProduct.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnSearchIdProduct.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnSearchIdProcedure.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnDeleteProduct.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnDeleteProcedure.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnUpdateProduct.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnUpdateProcedure.setBorder(new LineBorder(new Color(14,85,78), 2));
		btnModal.setBorder(new LineBorder(new Color(14,85,78), 2));

		btnClean.setFocusPainted(false);
		btnChange.setFocusPainted(false);
		btnUpdateProcedure.setFocusPainted(false);
		btnModal.setFocusPainted(false);
		btnInsertProcedure.setFocusPainted(false);
		btnInsertProduct.setFocusPainted(false);
		btnSearchIdProduct.setFocusPainted(false);
		btnSearchIdProcedure.setFocusPainted(false);
		btnDeleteProduct.setFocusPainted(false);
		btnDeleteProcedure.setFocusPainted(false);
		
		JPanel pnlStatistical = new JPanel();
		pnlStatistical.setBackground(Color.WHITE);
		pnlStatistical.setBounds(10, 196, 560, 230);
		pnlListProcedure.add(pnlStatistical);
		pnlStatistical.setLayout(null);
		
		lblChart = new JLabel("");
		lblChart.setBackground(Color.WHITE);
		lblChart.setIcon(new ImageIcon("statistical/TopFiveProductInMonth_pie_Chart3D.jpeg"));
		lblChart.setBounds(0, 0, 560, 230);
		pnlStatistical.add(lblChart);
		btnUpdateProduct.setFocusPainted(false);
		
		btnClean.setMnemonic(KeyEvent.VK_C);
		btnClean.setToolTipText("Phím tắt : Alt + C");
		btnInsertProduct.setMnemonic(KeyEvent.VK_A);
		btnInsertProduct.setToolTipText("Phím tắt : Alt + A");
		btnSearchIdProduct.setMnemonic(KeyEvent.VK_S);
		btnSearchIdProduct.setToolTipText("Phím tắt : Alt + S");
		btnDeleteProduct.setMnemonic(KeyEvent.VK_D);
		btnDeleteProduct.setToolTipText("Phím tắt : Alt + D");
		btnUpdateProduct.setMnemonic(KeyEvent.VK_U);
		btnUpdateProduct.setToolTipText("Phím tắt : Alt + U");
		btnModal.setMnemonic(KeyEvent.VK_M);
		btnModal.setToolTipText("Phím tắt : Alt + M");
		
		return getContentPane();
	}
	
	// Lấy và hiển thị tất cả sản phẩm hiện có
	public void loadListProduct() {
		List<entity.Product> listProduct;
		listProduct = Dao_Product.getListProduct();
		for (entity.Product product : listProduct) {
			addRowProduct(product);
		}
	}
	
	// Lấy và hiển thị danh sách sản phẩm với danh sách đã cho
	public void loadListProductWithListProducted(List<Product> listProducted) {
		for (entity.Product product : listProducted) {
			addRowProduct(product);
		}
	}
	
	// Thêm sản phẩm vào bảng sản phẩm
	public void addRowProduct(entity.Product product) {
		String[] row = { product.getProductID(), product.getName()};
		dtmProduct.addRow(row);
	}
	
	// Lấy và hiển thị tất cả quy trình hiện có
	public void loadListProcedure() {
		List<Produre> ListProdure;
		ListProdure = Dao_Product.getListProcedure();
		for (Produre qt : ListProdure) {
			addRowListProcedure(qt, dtmListProcedure);
		}
	}
	
	// Lấy và hiển thị tất cả quy trình hiện có
	public void loadListProcedureWithListProdured(List<Produre> listProdure, DefaultTableModel dtm) {
		for (Produre qt : listProdure) {
			addRowListProcedure(qt, dtm);
		}
	}
	
	// Lấy và hiển thị tất cả quy trình hiện có của sản phẩm được chọn
	public void loadListProcedureByIdProduct(String idProduct, DefaultTableModel dtm) {
		List<Produre> ListProcedure;
		ListProcedure = Dao_Product.getListProcedurebyIdProduct(idProduct);
		for (Produre qt : ListProcedure) {
			addRowListProcedure(qt, dtm);
		}
	}

	// Thêm quy trình vào bảng quy trình
	public void addRowListProcedure(Produre procedure, DefaultTableModel dtm) {
		String[] row = { procedure.getProcedureID(), procedure.getName(), procedure.getPrice() + "",
				procedure.getNumberOrdinal() + "" };
		dtm.addRow(row);
	}
	
	// Kiểm tra quy trình đã được thêm vào bảng quy trình
	public boolean checkProcedureOnTable(String idProcedure) {
		int rowCount = dtmProcedure.getRowCount();
		if (rowCount > 0) {
			for (int i = 0; i < rowCount; i++) {
				if (dtmProcedure.getValueAt(i, 0).toString().equals(idProcedure)) {
					return true;
				}
			}
		}
		return false;
	}

	// Tạo mã sản phẩm
	public void randomIdProduct() {
		int order = (int) (Math.random() * (999 - 1 + 1) + 1);
		String idProduct = "";
		for (int i = 0; i < 2; i++) {
			idProduct += (char) (Math.random() * (90 - 65 + 1) + 65) + "";
		}
		if (order < 10) {
			idProduct += "00" + order;
		} else if (order >= 10 && order <= 99) {
			idProduct += "0" + order;
		} else {
			idProduct += order;
		}

		boolean test = true;
		while (Dao_Product.searchProductByIdProduct(idProduct) == null && test) {
			cleanProduct();
			txtIdProduct.setText(idProduct);
			test = false;
			break;
		}
		if (test) {
			randomIdProduct();
		}
	}
	
	// Tạo mã quy trình
	public void randomIdProcedure() {
		int order = (int) (Math.random() * (99 - 1 + 1) + 1);
		String idProcedure = "";
		if (order >= 10) {
			idProcedure = txtIdProduct.getText() + order + "";
		} else {
			idProcedure = txtIdProduct.getText() + "0" + order;
		}

		boolean test = true;
		while (Dao_Product.searchProcedureByIdProcedure(idProcedure) == null && !checkProcedureOnTable(idProcedure)
				&& test) {
			cleanTextFieldProcedure();
			txtIdProcedure.setText(idProcedure);
			test = false;
			break;
		}
		if (test) {
			randomIdProcedure();
		}
	}

	// Xóa dữ liệu trên bảng sản phẩm
	private void deleteDataOnTableModelProduct() {
		dtmProduct.setRowCount(0);
	}

	// Xóa dữ liệu trên bảng quy trình
	private void deleteDataOnTableModel(DefaultTableModel dtm) {
		dtm.setRowCount(0);
	}

	// Kiểm tra sản phẩm đã tồn tại
	private boolean checkContainProduct(entity.Product product) {
		List<entity.Product> listProduct = Dao_Product.getListProduct();
		if (listProduct.contains(product)) {
			return true;
		}
		return false;
	}

	// Làm mới JtextField của quy trình
	public void cleanTextFieldProcedure() {
		txtIdProcedure.setText("");
		txtNameProcedure.setText("");
		txtPriceProcedure.setText("");
	}

	// Làm mới tất cả JtextField
	public void cleanProduct() {
		deleteDataOnTableModelProduct();
		deleteDataOnTableModel(dtmListProcedure);
		deleteDataOnTableModel(dtmProcedure);
		cleanTextFieldProcedure();
		loadListProduct();
		loadListProcedure();
		txtNameProduct.requestFocus();
		txtIdProduct.setText("");
		txtNameProduct.setText("");
		btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnInsertProduct.setText("Thêm sản phẩm");
		listDetails = new ArrayList<entity.DetailProduction>();
	}

	// Cập nhật lại các nút của quy trình
	public void resetProcedure() {
		btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
	}
	
	// Top 5 sản phẩm có nhiều hợp đồng nhất trong tháng bằng biểu đồ hình tròn
	@SuppressWarnings("removal")
	public void chartTopFiveProduct(List<TopProduct> listProduct) {
		DefaultPieDataset dataset = new DefaultPieDataset( );
		for(TopProduct pr : listProduct) {
			dataset.setValue( pr.getProductID() , new Double( pr.getQuantityDetail() ) ); 
		}
		final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
	      JFreeChart chart = ChartFactory.createPieChart3D( 
	         "Top 5 Sản Phẩm" ,  // chart title                   
	         dataset ,         // data 
	         true ,            // include legend                   
	         true, 
	         false);

	      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );   
	      plot.setStartAngle( 270 );
	      plot.setForegroundAlpha( 0.60f );         
	      plot.setLabelGenerator(labelGenerator);
	      int width = 560;   /* Width of the image */             
	      int height = 230;  /* Height of the image */                             
	      File pieChart3D = new File( "statistical/TopFiveProductInMonth_pie_Chart3D.jpeg" );                      
	      try {
			ChartUtilities.saveChartAsJPEG( pieChart3D , chart , width , height );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Thống kê danh sách hợp đồng của sản phẩm bằng biểu đồ tròn
	@SuppressWarnings("removal")
	public void chartDetailOfProductInMonth(List<entity.DetailProduction> listDetail) {
		String idProduct = dtmProduct.getValueAt(tblListProduct.getSelectedRow(), 0).toString();
		int quantityProduction = 0;
		int quantityStopProduction = 0;
		int quantityFinish = 0;
		DefaultPieDataset dataset = new DefaultPieDataset( );
		if(listDetail.size() > 0) {
			for(entity.DetailProduction dt : listDetail) {
				if(dt.getState().equals("Sản Xuất")) {
					quantityProduction += 1;
				}
				else if(dt.getState().equals("Ngưng Sản Xuất")) {
					quantityStopProduction += 1;
				}
				else {
					quantityFinish += 1;
				}
			}
			dataset.setValue( "Sản Xuất" , new Double( quantityProduction ) ); 
			dataset.setValue( "Ngưng Sản Xuất" , new Double( quantityStopProduction ) ); 
			dataset.setValue( "Hoàn Thành" , new Double( quantityFinish ) ); 
		}
		else {
			dataset.setValue( "Chưa Sản Xuất" , new Double( 1 ) ); 
		}
		final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
	      JFreeChart chart = ChartFactory.createPieChart3D( 
	         "Hợp Đồng Sản Xuất" ,  // chart title                   
	         dataset ,         // data 
	         true ,            // include legend                   
	         true, 
	         false);

	      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( ); 
	      plot.setStartAngle( 270 );
	      plot.setForegroundAlpha( 0.60f );    
	      plot.setLabelGenerator(labelGenerator);
	      int width = 560;   /* Width of the image */             
	      int height = 230;  /* Height of the image */                             
	      File pieChart3D = new File( "statistical/DetailProductInMonthOf" + idProduct + "_pie_Chart3D.jpeg" );                      
	      try {
			ChartUtilities.saveChartAsJPEG( pieChart3D , chart , width , height );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Main
	public static void main(String[] args) {
		ProductGUI product = new ProductGUI();
		product.getUI();
		product.setVisible(true);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblListProduct)) {
			deleteDataOnTableModel(dtmListProcedure);
			deleteDataOnTableModel(dtmProcedure);
			btnInsertProduct.setText("Thêm sản phẩm");
			btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
			btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			String idProduct;
			int rowProductSelected = tblListProduct.getSelectedRow();
			idProduct = dtmProduct.getValueAt(rowProductSelected, 0).toString();
			List<Produre> listProcedure = Dao_Product.getListProcedurebyIdProduct(idProduct);
			ListProcedure = listProcedure;
			if (rowProductSelected >= 0 && listProcedure.size() > 0) {
				loadListProcedureByIdProduct(idProduct, dtmListProcedure);
				loadListProcedureByIdProduct(idProduct, dtmProcedure);
				txtIdProduct.setText(dtmProduct.getValueAt(rowProductSelected, 0).toString());
				txtNameProduct.setText(dtmProduct.getValueAt(rowProductSelected, 1).toString());
				txtIdProcedure.setText(dtmListProcedure.getValueAt(0, 0).toString());
				txtNameProcedure.setText(dtmListProcedure.getValueAt(0, 1).toString());
				txtPriceProcedure.setText(dtmListProcedure.getValueAt(0, 2).toString());
				spnOrder.setValue(Integer.parseInt(dtmListProcedure.getValueAt(0, 3).toString()));
				txtSearchProcedure.setText("");
				resetProcedure();
			} else if (listProcedure.size() <= 0) {
				txtIdProduct.setText(dtmProduct.getValueAt(rowProductSelected, 0).toString());
				txtNameProduct.setText(dtmProduct.getValueAt(rowProductSelected, 1).toString());
				cleanTextFieldProcedure();
				txtSearchProcedure.setText("");
			}
			List<entity.DetailProduction> listDetailInMonth = detailDAO.getListDetailbyIdProductInMonth(idProduct);
			chartDetailOfProductInMonth(listDetailInMonth);
			lblChart.setIcon(new ImageIcon("statistical/DetailProductInMonthOf" + idProduct + "_pie_Chart3D.jpeg"));
		} else if (o.equals(tblListProcedure)) {
			int rowProcedureSelected = tblListProcedure.getSelectedRow();
			int rowProductSelected = tblListProduct.getSelectedRow();
			if (rowProcedureSelected >= 0) {
				resetProcedure();
				txtIdProcedure.setText(dtmListProcedure.getValueAt(rowProcedureSelected, 0).toString());
				txtNameProcedure.setText(dtmListProcedure.getValueAt(rowProcedureSelected, 1).toString());
				txtPriceProcedure.setText(dtmListProcedure.getValueAt(rowProcedureSelected, 2).toString());
				spnOrder.setValue(Integer.parseInt(dtmListProcedure.getValueAt(rowProcedureSelected, 3).toString()));
				Produre procedure = Dao_Product.searchProcedureByIdProcedure(txtIdProcedure.getText());
				entity.Product product = Dao_Product.searchProductByIdProduct(procedure.getProductID());
				txtIdProduct.setText(product.getProductID());
				txtNameProduct.setText(product.getName());
				deleteDataOnTableModel(dtmProcedure);
				loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
				btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
				if (rowProductSelected >= 0 && txtSearchProcedure.getText().compareToIgnoreCase("ALL") == 0) {
					tblListProduct.removeRowSelectionInterval(rowProductSelected, rowProductSelected);
				}
				ListProcedure = Dao_Product.getListProcedurebyIdProduct(txtIdProduct.getText());
			}
		} else if (o.equals(tblProcedure)) {
			int rowQTrinhSelected = tblProcedure.getSelectedRow();
			if (rowQTrinhSelected >= 0) {
				txtIdProcedure.setText(dtmProcedure.getValueAt(rowQTrinhSelected, 0).toString());
				txtNameProcedure.setText(dtmProcedure.getValueAt(rowQTrinhSelected, 1).toString());
				txtPriceProcedure.setText(dtmProcedure.getValueAt(rowQTrinhSelected, 2).toString());
				spnOrder.setValue(Integer.parseInt(dtmProcedure.getValueAt(rowQTrinhSelected, 3).toString()));
				btnInsertProcedure.setIcon(new ImageIcon("images\\Close-2-icon.png"));
				btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\exchange.png"));
			}
		}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnClean)) {
			cleanProduct();
			randomIdProduct();
			spnOrder.setValue(1);
			lblChart.setIcon(new ImageIcon("statistical/TopFiveProductInMonth_pie_Chart3D.jpeg"));
		} else if (o.equals(btnChange)) {
			int rowSelected = tblProcedure.getSelectedRow();
			if (rowSelected >= 0) {
				tblProcedure.removeRowSelectionInterval(rowSelected, rowSelected);
			}
			btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
			btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			cleanTextFieldProcedure();
			txtNameProcedure.requestFocus();
			if (!txtIdProduct.getText().isEmpty()) {
				randomIdProcedure();
			}
		}
		else if(o.equals(btnModal)) {
			DetailProduction detail = new DetailProduction(txtIdProduct.getText(), listDetails);
			detail.setVisible(true);
		}
		else if (o.equals(btnInsertProcedure)) {
			boolean regexProcedure = regexProcedure();
			if (regexProcedure) {
				if (btnInsertProcedure.getIcon().toString().equals("images\\math-add-icon.png")) {
					int rowCountProcedure = dtmProcedure.getRowCount();
					boolean checkContainProcedure = false;
					for (int i = 0; i < rowCountProcedure; i++) {
						if (dtmProcedure.getValueAt(i, 0).toString().equals(txtIdProcedure.getText())) {
							checkContainProcedure = true;
							JOptionPane.showMessageDialog(this, "Quy trình đã được thêm vào table");
							break;
						}
					}

					if (!checkContainProcedure) {
						Produre procedure = new Produre(txtIdProcedure.getText(), txtNameProcedure.getText(),
								Double.parseDouble(txtPriceProcedure.getText()),
								Integer.parseInt(spnOrder.getValue().toString()), txtIdProduct.getText());
						addRowListProcedure(procedure, dtmProcedure);
						ListProcedure.add(procedure);
						JOptionPane.showMessageDialog(this, "Thêm quy trình thành công!!!");
						randomIdProcedure();
						List<entity.Product> listProduct = Dao_Product.getListProduct();
						if (listProduct.contains(Dao_Product.searchProductByIdProduct(txtIdProduct.getText()))) {
							List<Produre> listProcedure = Dao_Product
									.getListProcedurebyIdProduct(txtIdProduct.getText());
							if (dtmProcedure.getRowCount() != listProcedure.size()) {
								btnInsertProduct.setText("Sửa sản phẩm");
								btnInsertProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
								btnUpdateProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
							}
						} else {
							randomIdProcedure();
						}
					}

				} else {
					if (btnInsertProcedure.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
						Produre procedure = new Produre(txtIdProcedure.getText(), txtNameProcedure.getText(),
								Double.parseDouble(txtPriceProcedure.getText()),
								Integer.parseInt(spnOrder.getValue().toString()), txtIdProduct.getText());
						if (Dao_Product.updateProcedure(procedure)) {
							JOptionPane.showMessageDialog(this, "Sửa quy trình thành công!!!");
							deleteDataOnTableModel(dtmListProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
							deleteDataOnTableModel(dtmProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
						} else {
							JOptionPane.showMessageDialog(this, "Sửa quy trình thất bại!!!");
						}
					} else {
						int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa Quy Trình này không?");
						if(confirm == JOptionPane.YES_OPTION) {
							Product product = Dao_Product.searchProductByIdProduct(txtIdProduct.getText());
							if(product != null) {
								List<Produre> listProdureOfProduct = Dao_Product.getListProcedurebyIdProduct(product.getProductID());
								List<Produre> listProdureOnTableProdure = new ArrayList<Produre>();
								for(int i = 0; i < dtmProcedure.getRowCount(); i++) {
									listProdureOnTableProdure.add(new Produre(dtmProcedure.getValueAt(i, 0).toString(), dtmProcedure.getValueAt(i, 1).toString(), Double.parseDouble(dtmProcedure.getValueAt(i, 2).toString()), Integer.parseInt(dtmProcedure.getValueAt(i, 3).toString()), txtIdProduct.getText()));
								}
								if(listProdureOfProduct.containsAll(listProdureOnTableProdure)) {
									if(Dao_Product.isDeleteProdure(txtIdProcedure.getText())) {
										if (Dao_Product.deleteProcedure(txtIdProcedure.getText())) {
											JOptionPane.showMessageDialog(this, "Xóa quy trình thành công!!!");
											deleteDataOnTableModel(dtmListProcedure);
											deleteDataOnTableModel(dtmProcedure);
											loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
											loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
											btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
											btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
										} else {
											JOptionPane.showMessageDialog(this, "Xóa quy trình thất bại!!! ");
										}
									}
									else {
										JOptionPane.showMessageDialog(this, "Quy trình đã hoặc đang được thực hiện, không thể xóa ");
									}
								}
								else {
									Produre p = Dao_Product.searchProcedureByIdProcedure(txtIdProcedure.getText());
									if(p != null) {
										if(Dao_Product.isDeleteProdure(txtIdProcedure.getText())) {
											if (Dao_Product.deleteProcedure(txtIdProcedure.getText())) {
												JOptionPane.showMessageDialog(this, "Xóa quy trình thành công!!!");
												deleteDataOnTableModel(dtmListProcedure);
												deleteDataOnTableModel(dtmProcedure);
												loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
												listProdureOnTableProdure.remove(p);
												loadListProcedureWithListProdured(listProdureOnTableProdure, dtmProcedure);
												btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
												btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
											} else {
												JOptionPane.showMessageDialog(this, "Xóa quy trình thất bại!!! ");
											}
										}
										else {
											JOptionPane.showMessageDialog(this, "Quy trình đã hoặc đang được thực hiện, không thể xóa ");
										}
									}
									else {
										JOptionPane.showMessageDialog(this, "Xóa quy trình thành công!!!");
										listProdureOnTableProdure.remove(tblProcedure.getSelectedRow());
										deleteDataOnTableModel(dtmProcedure);
										loadListProcedureWithListProdured(listProdureOnTableProdure, dtmProcedure);
									}
								}
							}
							else {
								JOptionPane.showMessageDialog(this, "Xóa quy trình thành công!!!");
								ListProcedure.remove(tblProcedure.getSelectedRow());
								deleteDataOnTableModel(dtmProcedure);
								for(Produre produre : ListProcedure) {
									addRowListProcedure(produre, dtmProcedure);
								}
							}
						}
						else if(confirm == JOptionPane.NO_OPTION) {
							JOptionPane.showMessageDialog(this, "Hủy xóa Quy Trình thành công!!!");
						}
					}

				}
			}
		} else if (o.equals(btnInsertProduct)) {
			boolean regexProduct = regexProduct();
			if (regexProduct) {
				if (btnInsertProduct.getText().equals("Thêm sản phẩm")) {
					entity.Product product = new entity.Product(txtIdProduct.getText(), txtNameProduct.getText());
					boolean checkContainProduct = checkContainProduct(product);
					ListProcedure.removeAll(ListProcedure);
					int rowCountProcedure = dtmProcedure.getRowCount();
					Produre procedure = new Produre();
					for (int i = 0; i < rowCountProcedure; i++) {
						procedure = new Produre(dtmProcedure.getValueAt(i, 0).toString(),
								dtmProcedure.getValueAt(i, 1).toString(),
								Double.parseDouble(dtmProcedure.getValueAt(i, 2).toString()),
								Integer.parseInt(dtmProcedure.getValueAt(i, 3).toString()), txtIdProduct.getText());
						ListProcedure.add(procedure);
					}
					if (!checkContainProduct) {
						boolean insertProduct = Dao_Product.insertProduct(product);
						boolean insertListProcedure = Dao_Product.insertListProcedure(ListProcedure);
						if (insertProduct && insertListProcedure) {
							boolean isInsertDetail = true;
							for(entity.DetailProduction detailNew : listDetails) {
								if(!detailDAO.insertDetailProduction(detailNew)) {
									isInsertDetail = false;
									break;
								}
							}
							if(isInsertDetail) {
								JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!!!");
								ListProcedure = new ArrayList<Produre>();
								listDetails = new ArrayList<entity.DetailProduction>();
								deleteDataOnTableModelProduct();
								cleanProduct();
								randomIdProduct();
							}
							else {
								JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại!!!");
					}
				} else {
					int rowCountProcedure = dtmProcedure.getRowCount();
					ListProcedure = new ArrayList<Produre>();
					Produre procedure = new Produre();
					for (int i = 0; i < rowCountProcedure; i++) {
						procedure = new Produre(dtmProcedure.getValueAt(i, 0).toString(),
								dtmProcedure.getValueAt(i, 1).toString(),
								Double.parseDouble(dtmProcedure.getValueAt(i, 2).toString()),
								Integer.parseInt(dtmProcedure.getValueAt(i, 3).toString()), txtIdProduct.getText());
						ListProcedure.add(procedure);
					}
					boolean updateListProcedure = Dao_Product.updateListProcedure(ListProcedure, txtIdProduct.getText());
					entity.Product product = new entity.Product(txtIdProduct.getText(), txtNameProduct.getText());
					boolean updateProduct = Dao_Product.updateProduct(product);
					if (updateProduct == true && updateListProcedure == true) {
						JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!!!");
						ListProcedure = new ArrayList<Produre>();
						deleteDataOnTableModelProduct();
						deleteDataOnTableModel(dtmListProcedure);
						loadListProduct();
						loadListProcedureByIdProduct(product.getProductID(), dtmListProcedure);
						btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
						btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
						btnInsertProduct.setText("Thêm sản phẩm");
					}
				}
			}
		} else if (o.equals(btnDeleteProduct)) {
			List<entity.DetailProduction> listDetail = detailDAO.getListDetailbyIdProduct(txtIdProduct.getText());
			int rowSelectedTblProduct = tblListProduct.getSelectedRow();
			if(rowSelectedTblProduct >= 0) {
				if(listDetail.size() == 0) {
					int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa Sản Phẩm này không?");
					if(confirm == JOptionPane.YES_OPTION) {
						if (Dao_Product.deleteProduct(dtmProduct.getValueAt(rowSelectedTblProduct, 0).toString())) {
							JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!!!");
							deleteDataOnTableModelProduct();
							deleteDataOnTableModel(dtmListProcedure);
							loadListProduct();
						}
					}
					else if(confirm == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(this, "Hủy xóa Sản Phẩm thành công!!!");
					}
				}
				else{
					int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa Sản Phẩm này không?");
					if(confirm == JOptionPane.YES_OPTION) {
						boolean isDeleteProduct = true;
						for(entity.DetailProduction detailDelete : listDetail) {
							if(!detailDAO.isDeleteDetail(detailDelete.getDetailProductionID())) {
								JOptionPane.showMessageDialog(this, "Sản phẩm đã được sản xuất hoặc đang được sản xuất, không thể xóa!!! ");
								isDeleteProduct = false;
								break;
							}
						}
						if(isDeleteProduct) {
							boolean deleteListDetail = detailDAO.deleteListDetail(dtmProduct.getValueAt(rowSelectedTblProduct, 0).toString());
							if(Dao_Product.deleteProduct(dtmProduct.getValueAt(rowSelectedTblProduct, 0).toString()) && deleteListDetail) {
								JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!!!");
								deleteDataOnTableModelProduct();
								deleteDataOnTableModel(dtmListProcedure);
								loadListProduct();
							}
						}
					}
					else if(confirm == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(this, "Hủy xóa Sản Phẩm thành công!!!");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần xóa");
			}
		} else if (o.equals(btnDeleteProcedure)) {
			if(tblListProcedure.getSelectedRow() >= 0) {
				int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa Quy Trình này không?");
				if (confirm == JOptionPane.YES_OPTION) {
					if(Dao_Product.isDeleteProdure(txtIdProcedure.getText())) {
						if(Dao_Product.deleteProcedure(txtIdProcedure.getText())) {
							JOptionPane.showMessageDialog(this, "Xóa quy trình thành công!!!");
							deleteDataOnTableModel(dtmListProcedure);
							deleteDataOnTableModel(dtmProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
						}
					}
					else {
						JOptionPane.showMessageDialog(this, "Quy trình đã hoặc đang được thực hiện, không thể xóa ");
					}
				} else if(confirm == JOptionPane.NO_OPTION){
					JOptionPane.showMessageDialog(this, "Hủy xóa Quy Trình thành công!!!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chọn quy trình cần xóa");
			}
		} else if (o.equals(btnUpdateProduct)) {
			if (btnUpdateProduct.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
				btnInsertProduct.setText("Sửa sản phẩm");
				btnInsertProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnUpdateProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
				if (!txtIdProduct.getText().isEmpty()) {
					randomIdProcedure();
				}
			} else {
				btnInsertProduct.setText("Thêm sản phẩm");
				btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				cleanProduct();
				randomIdProduct();
			}
		} else if (o.equals(btnUpdateProcedure)) {
			if (btnUpdateProcedure.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
				btnInsertProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnUpdateProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
			} else {
				btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			}
		} else if (o.equals(btnSearchIdProduct)) {
			if (txtSearchProduct.getText().isEmpty()) {
				dtmProduct.setRowCount(0);
				loadListProduct();
			} else {
				entity.Product product = Dao_Product.searchProductByIdProduct(txtSearchProduct.getText());
				if (product != null) {
					dtmProduct.setRowCount(0);
					addRowProduct(product);
				} else {
					dtmProduct.setRowCount(0);
				}
			}
		} else if (o.equals(btnSearchIdProcedure)) {
			if (txtSearchProcedure.getText().isEmpty()) {
				dtmListProcedure.setRowCount(0);
				loadListProcedure();
			}else {
				Produre procedure = Dao_Product.searchProcedureByIdProcedure(txtSearchProcedure.getText());
				if (procedure != null) {
					dtmListProcedure.setRowCount(0);
					addRowListProcedure(procedure, dtmListProcedure);
				} else {
					dtmListProcedure.setRowCount(0);
				}
			}
		}

	}

	// Kiểm tra dữ liệu nhập của sản phẩm
	public boolean regexProduct() {
		String announce = "";
		String regexIdProduct = "^[A-Z]{2}[0-9]{3}$";
		String regexNameProduct = "^([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+)$";
//		String regexQuantityProductive = "(^[1-9][0-9]*)+";
		if (txtIdProduct.getText().isEmpty() || txtNameProduct.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin sản phẩm";
		} else {
			if (!txtIdProduct.getText().matches(regexIdProduct)) {
				announce += "Mã sản phẩm phải bắt đầu bằng 2 chữ in hoa và theo sau là 3 chữ số \n";
				txtIdProduct.requestFocus();
				txtIdProduct.selectAll();
			} 
			else if(!txtNameProduct.getText().matches(regexNameProduct)) {
				announce += "Tên sản phẩm không chứa kí tự đặc biệt \n";
				txtNameProduct.requestFocus();
				txtNameProduct.selectAll();
			}
		}
		if (announce.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, announce);
			return false;
		}
	}

	// Kiểm tra dữ liệu nhập của quy trình
	public boolean regexProcedure() {
		String announce = "";
		String regexIdProcedure = "^" + txtIdProduct.getText() + "[0-9]{2}$";
		String regexNameProdure = "^([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\s]+)$";
		String regexPriceProcedure = "(^[1-9][0-9]*(\\.[0-9])?)+";
		if (txtIdProcedure.getText().isEmpty() || txtNameProcedure.getText().isEmpty()
				|| txtPriceProcedure.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin quy trình";
		} else {
			if (!txtIdProcedure.getText().matches(regexIdProcedure)) {
				announce += "Mã quy trình phải bắt đầu bằng mã sản phẩm và theo sau 2 chữ số \n";
				txtIdProcedure.requestFocus();
				txtIdProcedure.selectAll();
			} else if (!txtPriceProcedure.getText().matches(regexPriceProcedure)) {
				announce += "Giá quy trình phải lớn hơn 0";
				txtPriceProcedure.requestFocus();
				txtPriceProcedure.selectAll();
			}
			else if (!txtNameProcedure.getText().matches(regexNameProdure)) {
				announce += "Tên quy trình không chứa kí tự đặc biệt \n";
				txtNameProcedure.requestFocus();
				txtNameProcedure.selectAll();
			}
		}
		if (announce.isEmpty()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, announce);
			return false;
		}

	}
}
