package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.ProductDAO;
import entity.Produre;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class ProductGUI extends JFrame implements ActionListener, MouseListener {
	private JTextField txtIdProduct;
	private JTextField txtNameProduct;
	private JTextField txtQuantityProductive;
	private JTextField txtIdProcedure;
	private JTextField txtNameProcedure;
	private JTextField txtPriceProcedure;
	private JTextField txtSearchProduct;
	private JTable tblListProduct;
	private JTextField txtSearchProcedure;
	private JTable tblListProcedure;
	private ProductDAO Dao_Product = new ProductDAO();
	private DefaultTableModel dtmProduct;
	private DefaultTableModel dtmListProcedure;
	private JButton btnInsertProcedure;
	private JButton btnInsertProduct;
	private JButton btnSearchIdProduct;
	private JButton btnDeleteProduct;
	private JButton btnUpdateProduct;
	private JButton btnSearchIdProcedure;
	private JButton btnDeleteProcedure;
	private JButton btnUpdateProcedure;
	private List<Produre> ListProcedure = new ArrayList<Produre>();
	private JTable tblProcedure;
	private DefaultTableModel dtmProcedure;
	private JButton btnClean;
	private JButton btnChange;
	private int rowCountTblProcedure = 0;
	
	public ProductGUI() {
		setSize(1200, 690);
		getContentPane().setLayout(null);
		
		JPanel pnlProduct = new JPanel();
		pnlProduct.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "S\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlProduct.setBounds(10, 10, 460, 191);
		getContentPane().add(pnlProduct);
		pnlProduct.setLayout(null);
		
		JLabel lblIdProduct = new JLabel("Mã sản phẩm:");
		lblIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdProduct.setBounds(37, 35, 87, 13);
		pnlProduct.add(lblIdProduct);
		
		txtIdProduct = new JTextField();
		txtIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdProduct.setBounds(162, 35, 245, 19);
		pnlProduct.add(txtIdProduct);
		txtIdProduct.setColumns(10);
		
		JLabel lblNameProduct = new JLabel("Tên sản phẩm:");
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
	            if(
	            		Dao_Product.searchProductByIdProduct(txtIdProduct.getText()) == null &&
	            		dtmProcedure.getRowCount() == 0 && txtNameProcedure.getText().isEmpty() &&
	            		txtPriceProcedure.getText().isEmpty() && !txtIdProduct.getText().isEmpty()
	            ) {
	            	randomIdProcedure();
	            	
	            }
	         }

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		JLabel lblQuantityProductive = new JLabel("Số lượng:");
		lblQuantityProductive.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantityProductive.setBounds(37, 115, 64, 13);
		pnlProduct.add(lblQuantityProductive);
		
		txtQuantityProductive = new JTextField();
		txtQuantityProductive.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuantityProductive.setColumns(10);
		txtQuantityProductive.setBounds(162, 115, 245, 19);
		pnlProduct.add(txtQuantityProductive);
		
		btnClean = new JButton("");
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClean.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnClean.setBounds(37, 149, 55, 21);
		pnlProduct.add(btnClean);
		
		JPanel pnlProcedure = new JPanel();
		pnlProcedure.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Quy tr\u00ECnh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlProcedure.setBounds(513, 10, 485, 191);
		getContentPane().add(pnlProcedure);
		pnlProcedure.setLayout(null);
		
		JLabel lblIdProcedure = new JLabel("Mã quy trình:");
		lblIdProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdProcedure.setBounds(40, 32, 87, 13);
		pnlProcedure.add(lblIdProcedure);
		
		txtIdProcedure = new JTextField();
		txtIdProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIdProcedure.setColumns(10);
		txtIdProcedure.setBounds(137, 29, 106, 19);
		pnlProcedure.add(txtIdProcedure);
		txtIdProcedure.addFocusListener((FocusListener) new FocusListener() {
	         public void focusGained(FocusEvent e) {
//	            System.out.println("focus gained");
	            if(btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
	            	btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
	            	btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
	            }
	         }/*from w  ww  .  j a va2 s. c o m*/

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		JLabel lblNameProcedure = new JLabel("Tên quy trình:");
		lblNameProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameProcedure.setBounds(40, 70, 87, 13);
		pnlProcedure.add(lblNameProcedure);
		
		txtNameProcedure = new JTextField();
		txtNameProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNameProcedure.setColumns(10);
		txtNameProcedure.setBounds(137, 67, 160, 19);
		pnlProcedure.add(txtNameProcedure);
		txtNameProcedure.addFocusListener((FocusListener) new FocusListener() {
	         public void focusGained(FocusEvent e) {
//	            System.out.println("focus gained");
	            if(btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
	            	btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
	            	btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
	            }
	         }/*from w  ww  .  j a va2 s. c o m*/

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		JLabel lblPriceProcedure = new JLabel("Giá:");
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
	            if(btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
	            	btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
	            	btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
	            }
	         }/*from w  ww  .  j a va2 s. c o m*/

	         public void focusLost(FocusEvent e) {
//	        	 System.out.println("focus lost");
	         }
	      });
		
		btnInsertProcedure = new JButton("");
		btnInsertProcedure.setForeground(Color.BLACK);
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
		tblProcedure.setModel(dtmProcedure = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 quy tr\u00ECnh", "T\u00EAn quy tr\u00ECnh", "Gi\u00E1 quy tr\u00ECnh"
			}
		)
			{
				public boolean isCellEditable(int rowIndex, int columnIndex) {
				    return false;
				}
			}
		);
		tblProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrProcedure.setViewportView(tblProcedure);
		
		btnInsertProduct = new JButton("Thêm sản phẩm");
		btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnInsertProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertProduct.setBounds(1022, 90, 154, 40);
		getContentPane().add(btnInsertProduct);
		
		JPanel pnlListProducts = new JPanel();
		pnlListProducts.setBounds(0, 211, 1186, 442);
		getContentPane().add(pnlListProducts);
		pnlListProducts.setLayout(null);
		
		JPanel pnlListProduct = new JPanel();
		pnlListProduct.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlListProduct.setBounds(10, 10, 580, 432);
		pnlListProducts.add(pnlListProduct);
		pnlListProduct.setLayout(null);
		
		JLabel lblNoteIdProduct = new JLabel("Nhập mã sản phẩm:");
		lblNoteIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdProduct.setBounds(25, 17, 118, 15);
		pnlListProduct.add(lblNoteIdProduct);
		
		txtSearchProduct = new JTextField();
		txtSearchProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchProduct.setColumns(10);
		txtSearchProduct.setBounds(153, 16, 134, 19);
		pnlListProduct.add(txtSearchProduct);
		
		btnSearchIdProduct = new JButton("");
		btnSearchIdProduct.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchIdProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdProduct.setBounds(297, 15, 70, 21);
		pnlListProduct.add(btnSearchIdProduct);
		
		btnDeleteProduct = new JButton("");
		btnDeleteProduct.setIcon(new ImageIcon("images\\Close-2-icon.png"));
		btnDeleteProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteProduct.setBounds(382, 15, 70, 21);
		pnlListProduct.add(btnDeleteProduct);
		
		btnUpdateProduct = new JButton("");
		btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnUpdateProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateProduct.setBounds(462, 15, 70, 21);
		pnlListProduct.add(btnUpdateProduct);
		
		JScrollPane scrProduct = new JScrollPane();
		scrProduct.setBounds(10, 57, 560, 365);
		pnlListProduct.add(scrProduct);
		
		tblListProduct = new JTable();
		tblListProduct.setModel(dtmProduct = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng"
			}
			) {
				public boolean isCellEditable(int rowIndex, int columnIndex) {
				    return false;
				}
			});
		scrProduct.setViewportView(tblListProduct);
		
		JPanel pnlListProcedure = new JPanel();
		pnlListProcedure.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch quy tr\u00ECnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlListProcedure.setBounds(596, 10, 580, 432);
		pnlListProducts.add(pnlListProcedure);
		pnlListProcedure.setLayout(null);
		
		JScrollPane scrListProcedure = new JScrollPane();
		scrListProcedure.setBounds(10, 57, 560, 365);
		pnlListProcedure.add(scrListProcedure);
		
		tblListProcedure = new JTable();
		tblListProcedure.setModel(dtmListProcedure = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 quy tr\u00ECnh", "T\u00EAn quy tr\u00ECnh", "Gi\u00E1"
			}
		)
			{
				public boolean isCellEditable(int rowIndex, int columnIndex) {
				    return false;
				}
			}
		);
		scrListProcedure.setViewportView(tblListProcedure);
		
		JLabel lblNoteIdProcedure = new JLabel("Nhập mã quy trình:");
		lblNoteIdProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoteIdProcedure.setBounds(25, 19, 118, 15);
		pnlListProcedure.add(lblNoteIdProcedure);
		
		txtSearchProcedure = new JTextField();
		txtSearchProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearchProcedure.setColumns(10);
		txtSearchProcedure.setBounds(153, 18, 134, 19);
		pnlListProcedure.add(txtSearchProcedure);
		
		btnSearchIdProcedure = new JButton("");
		btnSearchIdProcedure.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchIdProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchIdProcedure.setBounds(297, 17, 70, 21);
		pnlListProcedure.add(btnSearchIdProcedure);
		
		btnDeleteProcedure = new JButton("");
		btnDeleteProcedure.setIcon(new ImageIcon("images\\Close-2-icon.png"));
		btnDeleteProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteProcedure.setBounds(382, 17, 70, 21);
		pnlListProcedure.add(btnDeleteProcedure);
		
		btnUpdateProcedure = new JButton("");
		btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnUpdateProcedure.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateProcedure.setBounds(462, 17, 70, 21);
		pnlListProcedure.add(btnUpdateProcedure);
		
		btnChange = new JButton("");
		btnChange.setBounds(386, 67, 50, 21);
		pnlProcedure.add(btnChange);
		btnChange.setIcon(new ImageIcon("images\\exchange.png"));
		if(btnInsertProcedure.getIcon().toString().equals("images\\math-add-icon.png")){
			btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		}
		else if(btnInsertProcedure.getIcon().toString().equals("images\\Close-2-icon.png")) {
			btnChange.setIcon(new ImageIcon("images\\exchange.png"));
		}
		
		tblListProduct.addMouseListener(this);
		tblListProcedure.addMouseListener(this);
		tblProcedure.addMouseListener(this);
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
		
		btnInsertProcedure.setFocusPainted(false);
		btnInsertProduct.setFocusPainted(false);
		btnSearchIdProduct.setFocusPainted(false);
		btnSearchIdProcedure.setFocusPainted(false);
		btnDeleteProduct.setFocusPainted(false);
		btnDeleteProcedure.setFocusPainted(false);
		btnUpdateProduct.setFocusPainted(false);
		btnUpdateProcedure.setFocusPainted(false);
		
		loadListProduct();
		loadListProcedure();
		txtSearchProcedure.setText("ALL");
		randomIdProduct();
		txtIdProduct.setEditable(false);
		txtIdProcedure.setEditable(false);
		
		
	}
	
	public void loadListProduct() {
		List<entity.Product> listProduct;
		listProduct = Dao_Product.getListProduct();
		for (entity.Product sanPham : listProduct) {
			addRowProduct(sanPham);
		}
	}
	
	public void loadListProcedure() {
		List<Produre> ListProdure;
		ListProdure = Dao_Product.getListProcedure();
		for (Produre qt : ListProdure) {
			addRowListProcedure(qt, dtmListProcedure);
		}
	}
	
	public void addRowProduct(entity.Product product) {
		String[] row = { product.getProductID(), product.getName(), product.getQuantity()+"" };
		dtmProduct.addRow(row);
	}
	
	public void loadListProcedureByIdProduct(String idProduct, DefaultTableModel dtm) {
		List<Produre> ListProcedure;
		ListProcedure = Dao_Product.getListProcedurebyIdProduct(idProduct);
		for (Produre qt : ListProcedure) {
			addRowListProcedure(qt, dtm);
		}
	}
	
	public void addRowListProcedure(Produre procedure, DefaultTableModel dtm) {
		String[] row = { procedure.getProcedureID(), procedure.getName(), procedure.getPrice()+"" };
		dtm.addRow(row);
	}
	
	public boolean checkProcedureOnTable(String idProcedure) {
		int rowCount = dtmProcedure.getRowCount();
		if(rowCount > 0) {
			for(int i = 0; i < rowCount; i++) {
				if(dtmProcedure.getValueAt(i, 0).toString().equals(idProcedure)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void randomIdProduct() {
		int order = (int)(Math.random()*(999-1+1)+1);
		String idProduct = "";
		for(int i = 0; i < 2; i++) {
			idProduct += (char)(Math.random()*(90-65+1)+65)+"";
		}
		if(order < 10) {
			idProduct += "00"+order;
		}
		else if(order >= 10 && order <= 99) {
			idProduct += "0"+order;
		}
		else {
			idProduct += order;
		}
		
    	boolean test = true;
    	while(Dao_Product.searchProductByIdProduct(idProduct) == null && test) {
    		cleanProduct();
    		txtIdProduct.setText(idProduct);
    		test = false;
    		break;
    	}
    	if(test) {
    		randomIdProduct();
    	}
	}
	
	public void randomIdProcedure() {
		int order = (int)(Math.random()*(99-1+1)+1);
		String idProcedure = "";
		if(order >= 10) {
			idProcedure = txtIdProduct.getText() + order+"";
		}
		else {
			idProcedure = txtIdProduct.getText() + "0"+order;
		}
		
    	boolean test = true;
    	while(Dao_Product.searchProcedureByIdProcedure(idProcedure) == null &&
    			!checkProcedureOnTable(idProcedure) && test
    		) {
    		cleanTextFieldProcedure();
    		txtIdProcedure.setText(idProcedure);
    		test = false;
    		break;
    	}
    	if(test) {
    		randomIdProcedure();
    	}
	}
	
	private void deleteDataOnTableModelProduct() {
		dtmProduct.setRowCount(0);
	}
	
	private void deleteDataOnTableModelProcedure(DefaultTableModel dtm) {
		dtm.setRowCount(0);
	}
	
	private boolean checkContainProduct(entity.Product product) {
		List<entity.Product> listProduct = Dao_Product.getListProduct();
		if(listProduct.contains(product)) {
			return true;
		}
		return false;
	}
	
	public void cleanTextFieldProcedure() {
		txtIdProcedure.setText("");
		txtNameProcedure.setText("");
		txtPriceProcedure.setText("");
	}
	
	public void cleanProduct() {
		deleteDataOnTableModelProduct();
		deleteDataOnTableModelProcedure(dtmListProcedure);
		deleteDataOnTableModelProcedure(dtmProcedure);
		cleanTextFieldProcedure();
		loadListProduct();
		loadListProcedure();
		txtNameProduct.requestFocus();
		txtIdProduct.setText("");
		txtNameProduct.setText("");
		txtQuantityProductive.setText("");
		txtSearchProcedure.setText("ALL");
		btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnInsertProduct.setText("Thêm sản phẩm");
	}
	
	public void resetProcedure() {
		btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnUpdateProcedure	.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
	}
	
	public static void main(String[] args) {
		new ProductGUI().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(tblListProduct)) {
			deleteDataOnTableModelProcedure(dtmListProcedure);
			deleteDataOnTableModelProcedure(dtmProcedure);
			btnInsertProduct.setText("Thêm sản phẩm");
			btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
			btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			String idProduct;
			int rowProductSelected = tblListProduct.getSelectedRow();
			idProduct = dtmProduct.getValueAt(rowProductSelected, 0).toString();
			List<Produre> listProcedure = Dao_Product.getListProcedurebyIdProduct(idProduct);
			if(rowProductSelected >= 0 && listProcedure.size() > 0) {
				loadListProcedureByIdProduct(idProduct, dtmListProcedure);
				loadListProcedureByIdProduct(idProduct, dtmProcedure);
				txtIdProduct.setText(dtmProduct.getValueAt(rowProductSelected, 0).toString());
				txtNameProduct.setText(dtmProduct.getValueAt(rowProductSelected, 1).toString());
				txtQuantityProductive.setText(dtmProduct.getValueAt(rowProductSelected, 2).toString());
				txtIdProcedure.setText(dtmListProcedure.getValueAt(0, 0).toString());
				txtNameProcedure.setText(dtmListProcedure.getValueAt(0, 1).toString());
				txtPriceProcedure.setText(dtmListProcedure.getValueAt(0, 2).toString());
				txtSearchProcedure.setText("");
				resetProcedure();
			}
			else if(listProcedure.size() <= 0) {
				txtIdProduct.setText(dtmProduct.getValueAt(rowProductSelected, 0).toString());
				txtNameProduct.setText(dtmProduct.getValueAt(rowProductSelected, 1).toString());
				txtQuantityProductive.setText(dtmProduct.getValueAt(rowProductSelected, 2).toString());
				cleanTextFieldProcedure();
				txtSearchProcedure.setText("");
			}
		}
		else if(o.equals(tblListProcedure)) {
			int rowProcedureSelected = tblListProcedure.getSelectedRow();
			int rowProductSelected = tblListProduct.getSelectedRow();
			if(rowProcedureSelected >= 0) {
				txtIdProcedure.setText(dtmListProcedure.getValueAt(rowProcedureSelected, 0).toString());
				txtNameProcedure.setText(dtmListProcedure.getValueAt(rowProcedureSelected, 1).toString());
				txtPriceProcedure.setText(dtmListProcedure.getValueAt(rowProcedureSelected, 2).toString());
				Produre procedure = Dao_Product.searchProcedureByIdProcedure(txtIdProcedure.getText());
				entity.Product product = Dao_Product.searchProductByIdProduct(procedure.getProductID());
				txtIdProduct.setText(product.getProductID());
				txtNameProduct.setText(product.getName());
				txtQuantityProductive.setText(product.getQuantity()+"");
				deleteDataOnTableModelProcedure(dtmProcedure);
				loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
				btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
				if(rowProductSelected >= 0 && txtSearchProcedure.getText().compareToIgnoreCase("ALL") == 0) {
					tblListProduct.removeRowSelectionInterval(rowProductSelected, rowProductSelected);
				}
			}
		}
		else if(o.equals(tblProcedure)) {
			int rowQTrinhSelected = tblProcedure.getSelectedRow();
			if(rowQTrinhSelected >= 0) {
				txtIdProcedure.setText(dtmProcedure.getValueAt(rowQTrinhSelected, 0).toString());
				txtNameProcedure.setText(dtmProcedure.getValueAt(rowQTrinhSelected, 1).toString());
				txtPriceProcedure.setText(dtmProcedure.getValueAt(rowQTrinhSelected, 2).toString());
				btnInsertProcedure.setIcon(new ImageIcon("images\\Close-2-icon.png"));
				btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\exchange.png"));
			}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnClean)) {
			cleanProduct();
			randomIdProduct();
		}
		else if(o.equals(btnChange)) {
			int rowSelected = tblProcedure.getSelectedRow();
			if(rowSelected >= 0) {
				tblProcedure.removeRowSelectionInterval(rowSelected, rowSelected);
			}
//			if(btnChange.getIcon().toString().equals("images\\Clear-icon.png")) {
//				deleteDataOnTableModelProcedure(dtmProcedure);
//			}
			btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
			btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			cleanTextFieldProcedure();
			txtNameProcedure.requestFocus();
			if(!txtIdProduct.getText().isEmpty()) {
				randomIdProcedure();
			}
		}
		else if(o.equals(btnInsertProcedure)) {
			boolean regexProcedure = regexProcedure();
			if(regexProcedure) {
				if(btnInsertProcedure.getIcon().toString().equals("images\\math-add-icon.png")) {
					int rowCountProcedure = dtmProcedure.getRowCount();
					boolean checkContainProcedure = false;
					for(int i = 0; i < rowCountProcedure; i++) {
						if(dtmProcedure.getValueAt(i, 0).toString().equals(txtIdProcedure.getText())) {
							checkContainProcedure = true;
							JOptionPane.showMessageDialog(this, "Quy trình đã được thêm vào table");
							break;
						}
					}
					
					if(!checkContainProcedure) {
						Produre procedure = new Produre(txtIdProcedure.getText(), txtNameProcedure.getText(), Float.parseFloat(txtPriceProcedure.getText()), 2, txtIdProduct.getText());
						addRowListProcedure(procedure, dtmProcedure);
						JOptionPane.showMessageDialog(this, "Thêm quy trình thành công!!!");
						List<entity.Product> listProduct = Dao_Product.getListProduct();
						if(listProduct.contains(Dao_Product.searchProductByIdProduct(txtIdProduct.getText()))) {
							List<Produre> listProcedure = Dao_Product.getListProcedurebyIdProduct(txtIdProduct.getText());
							if(dtmProcedure.getRowCount() != listProcedure.size()) {
								btnInsertProduct.setText("Sửa sản phẩm");
								btnInsertProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
								btnUpdateProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
							}
						}
						else {
							randomIdProcedure();
						}
					}
					
				}
				else {
					if(btnInsertProcedure.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
						Produre procedure = new Produre(txtIdProcedure.getText(), txtNameProcedure.getText(), Float.parseFloat(txtPriceProcedure.getText()), 2,txtIdProduct.getText());
						if(Dao_Product.updateProcedure(procedure)) {
							JOptionPane.showMessageDialog(this, "Sửa quy trình thành công!!!");
							deleteDataOnTableModelProcedure(dtmListProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
							deleteDataOnTableModelProcedure(dtmProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
						}
						else {
							JOptionPane.showMessageDialog(this, "Sửa quy trình thất bại!!!");
						}
					}
					else {
						if(Dao_Product.deleteProcedure(txtIdProcedure.getText())) {
							JOptionPane.showMessageDialog(this, "Xóa quy trình thành công!!!");
							txtIdProcedure.setEditable(true);
							deleteDataOnTableModelProcedure(dtmListProcedure);
							deleteDataOnTableModelProcedure(dtmProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
							loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
							btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
							btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
						}
						else {
							JOptionPane.showMessageDialog(this, "Xóa quy trình thất bại!!! ");
						}
					}
					
				}
			}
		}
		else if(o.equals(btnInsertProduct)) {
			boolean regexProduct = regexProduct();
			if(regexProduct) {
				if(btnInsertProduct.getText().equals("Thêm sản phẩm")) {
					entity.Product product = new entity.Product(txtIdProduct.getText(), txtNameProduct.getText(), Integer.parseInt(txtQuantityProductive.getText()));
					boolean checkContainProduct = checkContainProduct(product);
					int rowCountProcedure = dtmProcedure.getRowCount();
					Produre procedure = new Produre();
					for(int i = 0; i < rowCountProcedure; i++) {
						procedure = new Produre(dtmProcedure.getValueAt(i, 0).toString(), dtmProcedure.getValueAt(i, 1).toString(), Float.parseFloat(dtmProcedure.getValueAt(i, 2).toString()), 2,txtIdProduct.getText());
						ListProcedure.add(procedure);
					}
					if(!checkContainProduct) {
						boolean insertProduct = Dao_Product.insertProduct(product);
						boolean insertListProcedure = Dao_Product.insertListProcedure(ListProcedure);
						if(insertProduct) {
							JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!!!");
							ListProcedure = new ArrayList<Produre>();
							deleteDataOnTableModelProduct();
							cleanProduct();
							randomIdProduct();
						}
					}
					else {
						JOptionPane.showMessageDialog(this, "Sản phẩm Đã Tồn Tại!!!");
					}
				}
				else {
					int rowCountProcedure = dtmProcedure.getRowCount();
					Produre procedure = new Produre();
					for(int i = 0; i < rowCountProcedure; i++) {
						procedure = new Produre(dtmProcedure.getValueAt(i, 0).toString(),dtmProcedure.getValueAt(i, 1).toString(),Float.parseFloat(dtmProcedure.getValueAt(i, 2).toString()),2,txtIdProduct.getText());
						ListProcedure.add(procedure);
					}
					boolean updateListProcedure = Dao_Product.updateListProcedure(ListProcedure, txtIdProduct.getText());
					entity.Product product = new entity.Product(txtIdProduct.getText(), txtNameProduct.getText(), Integer.parseInt(txtQuantityProductive.getText()));
					boolean updateProduct = Dao_Product.updateProduct(product);
					if(updateProduct == true && updateListProcedure == true) {
						JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!!!");
						ListProcedure = new ArrayList<Produre>();
						deleteDataOnTableModelProduct();
						deleteDataOnTableModelProcedure(dtmListProcedure);
						loadListProduct();
						loadListProcedureByIdProduct(product.getProductID(), dtmListProcedure);
						btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
						btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
						btnInsertProduct.setText("Thêm sản phẩm");
					}
				}
			}
		}
		else if(o.equals(btnDeleteProduct)) {
			if(Dao_Product.deleteProduct(txtIdProduct.getText())) {
				JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!!!");
				deleteDataOnTableModelProduct();
				deleteDataOnTableModelProcedure(dtmListProcedure);
				loadListProduct();
			}
			else {
				JOptionPane.showMessageDialog(this, "Sản phẩm hiện đang có quy trình thực hiện,không thể xóa sản phẩm!!! ");
			}
		}
		else if(o.equals(btnDeleteProcedure)) {
			if(Dao_Product.deleteProcedure(txtIdProcedure.getText())) {
				JOptionPane.showMessageDialog(this, "Xóa quy trình thành công!!!");
				deleteDataOnTableModelProcedure(dtmListProcedure);
				deleteDataOnTableModelProcedure(dtmProcedure);
				loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
				loadListProcedureByIdProduct(txtIdProduct.getText(), dtmProcedure);
			}
			else {
				JOptionPane.showMessageDialog(this, "Xóa quy trình thất bại!!! ");
			}
		}
		else if(o.equals(btnUpdateProduct)) {
			if(btnUpdateProduct.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
				btnInsertProduct.setText("Sửa sản phẩm");
				btnInsertProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnUpdateProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
				if(!txtIdProduct.getText().isEmpty()) {
					randomIdProcedure();
				}
			}
			else {
				btnInsertProduct.setText("Thêm sản phẩm");
				btnInsertProduct.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnUpdateProduct.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				cleanProduct();
				randomIdProduct();
			}
		}
		else if(o.equals(btnUpdateProcedure)) {
			if(btnUpdateProcedure.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
				btnInsertProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnUpdateProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
			}
			else {
				btnInsertProcedure.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnUpdateProcedure.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnChange.setIcon(new ImageIcon("images\\Clear-icon.png"));
			}
		}
		else if(o.equals(btnSearchIdProduct)) {
			if(txtSearchProduct.getText().isEmpty()) {
				dtmProduct.setRowCount(0);
				loadListProduct();
			}
			else {
				entity.Product product = Dao_Product.searchProductByIdProduct(txtSearchProduct.getText());
				if(product != null) {
					dtmProduct.setRowCount(0);
					addRowProduct(product);
				}
				else {
					dtmProduct.setRowCount(0);
				}
			}
		}
		else if(o.equals(btnSearchIdProcedure)) {
			if(txtSearchProcedure.getText().isEmpty()) {
				dtmListProcedure.setRowCount(0);
				loadListProcedureByIdProduct(txtIdProduct.getText(), dtmListProcedure);
			}
			else if(txtSearchProcedure.getText().compareToIgnoreCase("all") == 0) {
				dtmListProcedure.setRowCount(0);
				loadListProcedure();
			}
			else {
				Produre procedure = Dao_Product.searchProcedureByIdProcedure(txtSearchProcedure.getText());
				if(procedure != null) {
					dtmListProcedure.setRowCount(0);
					addRowListProcedure(procedure, dtmListProcedure);
				}
				else {
					dtmListProcedure.setRowCount(0);
				}
			}
		}
		
	}
	
	public boolean regexProduct() {
		String announce = "";
		String regexIdProduct = "^[A-Z]{2}[0-9]{3}$";
//		String regexNameProduct = "^([A-Z]([a-z]*\\s?))+(\\S)$";
		String regexQuantityProductive = "(^[1-9][0-9]*)+";
		if(txtIdProduct.getText().isEmpty() || txtNameProduct.getText().isEmpty() || txtQuantityProductive.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin Sản Phẩm";
		}
		else {
			if(!txtIdProduct.getText().matches(regexIdProduct)) {
				announce += "Mã sản phẩm phải bắt đầu bằng 2 chữ in hoa và theo sau là 3 chữ số \n";
				txtIdProduct.requestFocus();
				txtIdProduct.selectAll();
			}
//			else if(!txtNameProduct.getText().matches(regexNameProduct)) {
//				announce += "Tên sản phẩm phải bắt đầu bằng chữ hoa đối với mỗi từ, giữa các từ cách nhau bởi khoảng trắng và không chứa kí tự đặc biệt \n";
//				txtNameProduct.requestFocus();
//				txtNameProduct.selectAll();
//			}
			else if(!txtQuantityProductive.getText().matches(regexQuantityProductive)) {
				announce += "Số lượng phải lớn hơn 0";
				txtQuantityProductive.requestFocus();
				txtQuantityProductive.selectAll();
			}
		}
		if(announce.isEmpty()) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(this, announce);
			return false;
		}
	}
	
	public boolean regexProcedure() {
		String announce = "";
		String regexIdProcedure = "^"+txtIdProduct.getText()+"[0-9]{2}$";
//		String regexNameProcedure = "^([A-Z]([a-z]*\\s?))+(\\S)$";
		String regexPriceProcedure = "(^[1-9][0-9]*(\\.[0-9])?)+";
		if(txtIdProcedure.getText().isEmpty() || txtNameProcedure.getText().isEmpty() || txtPriceProcedure.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin Quy Trình";
		}
		else {
			if(!txtIdProcedure.getText().matches(regexIdProcedure)) {
				announce += "Mã quy trình phải bắt đầu bằng mã sản phẩm và theo sau 2 chữ số \n";
				txtIdProcedure.requestFocus();
				txtIdProcedure.selectAll();
			}
//			else if(!txtNameProcedure.getText().matches(regexNameProcedure)) {
//				announce += "Tên quy trình phải bắt đầu bằng chữ hoa đối với mỗi từ, giữa các từ cách nhau bởi khoảng trắng và không chứa kí tự đặc biệt \n";
//				txtNameProcedure.requestFocus();
//				txtNameProcedure.selectAll();
//			}
			else if(!txtPriceProcedure.getText().matches(regexPriceProcedure)) {
				announce += "Giá quy trình phải lớn hơn 0";
				txtPriceProcedure.requestFocus();
				txtPriceProcedure.selectAll();
			}
		}
		if(announce.isEmpty()) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(this, announce);
			return false;
		}
		
	}
}
