package ui;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DetailPRoductionDAO;
import model.ProductDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import custom_field.JTextFieldHint;
import entity.Product;

public class DetailProduction extends JFrame implements ActionListener, MouseListener {
	private static String productID;
	private static entity.DetailProduction detail = null;
	private DetailPRoductionDAO detail_DAO = new DetailPRoductionDAO();
	private JTextField txtDetailProductionId;
	private JTextField txtQuantityProduction;
	private JComboBox cmbState;
	private JButton btnCancel;
	private JButton btnInsertDetail;
	private JButton btnCleanDetail;
	private JPanel pnlListDetail;
	private JTextField txtSearchDetail;
	private JButton btnUpdateDetail;
	private JButton btnSearchDetail;
	private JComboBox cmbSearchState;
	private JTable tblListDetail;
	private DefaultTableModel dtmListDetail;
	private ProductDAO Dao_Product = new ProductDAO();
	private JButton btnDeleteDetail;
	private String preState = "";
	private static List<entity.DetailProduction> listDetailOnTable = new ArrayList<entity.DetailProduction>();
	private List<entity.DetailProduction> listDetailSearch = new ArrayList<entity.DetailProduction>();

	public DetailProduction(String productid, List<entity.DetailProduction> listDetails) {
		getContentPane().setBackground(Color.WHITE);
		setSize(1100, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		productID = productid;
		listDetailOnTable = listDetails;
		
		JPanel pnllFormDetail = new JPanel();
		pnllFormDetail.setBackground(Color.WHITE);
		pnllFormDetail.setBorder(new TitledBorder(new LineBorder(new Color(0, 140, 140)), "Th\u00F4ng tin s\u1EA3n xu\u1EA5t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 140, 140)));
		pnllFormDetail.setBounds(10, 10, 462, 193);
		getContentPane().add(pnllFormDetail);
		pnllFormDetail.setLayout(null);
		
		JLabel lblDetailProductionId = new JLabel("Mã Sản Xuất : ");
		lblDetailProductionId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDetailProductionId.setBounds(26, 30, 99, 21);
		pnllFormDetail.add(lblDetailProductionId);
		
		txtDetailProductionId = new JTextField();
		txtDetailProductionId.setBackground(Color.WHITE);
		txtDetailProductionId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDetailProductionId.setEditable(false);
		txtDetailProductionId.setColumns(10);
		txtDetailProductionId.setBounds(174, 30, 246, 22);
		pnllFormDetail.add(txtDetailProductionId);
		
		JLabel lblQuantityProduction = new JLabel("Số Lượng Sản Xuất : ");
		lblQuantityProduction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantityProduction.setBounds(26, 70, 138, 21);
		pnllFormDetail.add(lblQuantityProduction);
		
		txtQuantityProduction = new JTextField();
		txtQuantityProduction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuantityProduction.setColumns(10);
		txtQuantityProduction.setBounds(174, 70, 246, 22);
		pnllFormDetail.add(txtQuantityProduction);
		
		JLabel lblState = new JLabel("Tình Trạng : ");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblState.setBounds(26, 110, 99, 21);
		pnllFormDetail.add(lblState);
		
		cmbState = new JComboBox();
		cmbState.setBackground(Color.WHITE);
		cmbState.setModel(new DefaultComboBoxModel(new String[] {"Sản Xuất", "Ngưng Sản Xuất", "Hoàn Thành"}));
		cmbState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbState.setBounds(174, 110, 246, 22);
		pnllFormDetail.add(cmbState);
		
		btnCleanDetail = new JButton("");
		btnCleanDetail.setIcon(new ImageIcon("images\\Clear-icon.png"));
		btnCleanDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCleanDetail.setFocusPainted(false);
		btnCleanDetail.setBounds(26, 150, 63, 25);
		pnllFormDetail.add(btnCleanDetail);
		
		btnInsertDetail = new JButton("Thêm");
		btnInsertDetail.setIcon(new ImageIcon("images\\math-add-icon.png"));
		btnInsertDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertDetail.setFocusPainted(false);
		btnInsertDetail.setBounds(215, 150, 109, 25);
		pnllFormDetail.add(btnInsertDetail);
		
		btnCancel = new JButton("Thoát");
		btnCancel.setIcon(new ImageIcon("images\\arrow-back-homeicon.png"));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.setFocusPainted(false);
		btnCancel.setBounds(335, 150, 85, 25);
		pnllFormDetail.add(btnCancel);
		
		pnlListDetail = new JPanel();
		pnlListDetail.setBackground(Color.WHITE);
		pnlListDetail.setLayout(null);
		pnlListDetail.setBorder(new TitledBorder(new LineBorder(new Color(0, 140, 140)), "Danh s\u00E1ch s\u1EA3n xu\u1EA5t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 140, 140)));
		pnlListDetail.setBounds(485, 10, 591, 193);
		getContentPane().add(pnlListDetail);
		
		txtSearchDetail = new JTextFieldHint("Nhập mã sản xuất...");
		txtSearchDetail.setPreferredSize(new Dimension(200,25));
		txtSearchDetail.setBounds(10, 22, 189, 19);
		pnlListDetail.add(txtSearchDetail);
		txtSearchDetail.setColumns(10);
		
		btnUpdateDetail = new JButton("");
		btnUpdateDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdateDetail.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
		btnUpdateDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdateDetail.setFocusPainted(false);
		btnUpdateDetail.setBounds(512, 22, 69, 20);
		pnlListDetail.add(btnUpdateDetail);
		
		btnDeleteDetail = new JButton("");
		btnDeleteDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteDetail.setIcon(new ImageIcon("images\\Close-2-icon.png"));
		btnDeleteDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeleteDetail.setFocusPainted(false);
		btnDeleteDetail.setBounds(433, 22, 69, 20);
		pnlListDetail.add(btnDeleteDetail);
		
		btnSearchDetail = new JButton("");
		btnSearchDetail.setIcon(new ImageIcon("images\\Zoom-icon.png"));
		btnSearchDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearchDetail.setFocusPainted(false);
		btnSearchDetail.setBounds(354, 22, 69, 20);
		pnlListDetail.add(btnSearchDetail);
		
		cmbSearchState = new JComboBox();
		cmbSearchState.setBackground(Color.WHITE);
		cmbSearchState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbSearchState.setModel(new DefaultComboBoxModel(new String[] {"Tất Cả", "Sản Xuất", "Ngưng Sản Xuất", "Hoàn Thành"}));
		cmbSearchState.setBounds(209, 22, 130, 21);
		pnlListDetail.add(cmbSearchState);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 571, 132);
		pnlListDetail.add(scrollPane);
		
		tblListDetail = new JTable();
		tblListDetail.setBackground(Color.WHITE);
		tblListDetail.setFillsViewportHeight(true);
		tblListDetail.setForeground(Color.BLACK);
		tblListDetail.setGridColor(new Color(0, 140, 140));
		tblListDetail.setRowHeight(25);
		tblListDetail.setBorder(new LineBorder(new Color(0, 140, 140)));
		JTableHeader tblHeaderListDetail = tblListDetail.getTableHeader();
		tblHeaderListDetail.setBackground(new Color(14,85,78));
		tblHeaderListDetail.setForeground(Color.WHITE);
		tblHeaderListDetail.setPreferredSize(new Dimension(100, 30));
		tblHeaderListDetail.setFont(new Font("Tahoma", Font.BOLD, 10));
		tblListDetail.setModel(dtmListDetail = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 CTSX", "S\u1ED1 l\u01B0\u1EE3ng s\u1EA3n xu\u1EA5t", "S\u1ED1 l\u01B0\u1EE3ng ho\u00E0n th\u00E0nh", "T\u00ECnh tr\u1EA1ng", "Ng\u00E0y l\u1EADp"
			}
		) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		scrollPane.setViewportView(tblListDetail);
		
		cmbSearchState.addItemListener((ItemEvent e) -> {
			Object item = e.getItem();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				// Item has been selected
				deleteDataOnTableModel(dtmListDetail);
				if(item.toString().equals("Sản Xuất")) {
					loadListDetailByState(productID, "Sản Xuất");
				}
				else if(item.toString().equals("Ngưng Sản Xuất")) {
					loadListDetailByState(productID,"Ngưng Sản Xuất");
				}
				else if(item.toString().equals("Hoàn Thành")){
					loadListDetailByState(productID,"Hoàn Thành");
				}
				else {
					loadListDetail(productID);
				}
				
			}
		});
		
		
		
		if(Dao_Product.searchProductByIdProduct(productid) == null && listDetailOnTable.size() > 0) {
			randomIdDetailProductNull(listDetailOnTable.get(listDetailOnTable.size()-1).getDetailProductionID());
			deleteDataOnTableModel(dtmListDetail);
			for(entity.DetailProduction detail : listDetailOnTable) {
				addRowDetail(detail);
			}
		}
		else {
			randomIdDetailProduction();
			loadListDetail(productID);
		}
		txtQuantityProduction.setText("");
		cmbState.setSelectedIndex(0);
		
		DocumentListener enventChangeSearchDetail = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				listDetailSearch = detail_DAO.searchListDetailProduction(txtSearchDetail.getText(), productID);
				deleteDataOnTableModel(dtmListDetail);
				loadListDetailWithListDetail(listDetailSearch);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				listDetailSearch = detail_DAO.searchListDetailProduction(txtSearchDetail.getText(), productID);
				deleteDataOnTableModel(dtmListDetail);
				loadListDetailWithListDetail(listDetailSearch);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				listDetailSearch = detail_DAO.searchListDetailProduction(txtSearchDetail.getText(), productID);
				deleteDataOnTableModel(dtmListDetail);
				loadListDetailWithListDetail(listDetailSearch);
			}

        };
        
        txtSearchDetail.getDocument().addDocumentListener(enventChangeSearchDetail);
		
		btnCleanDetail.addActionListener(this);
		btnInsertDetail.addActionListener(this);
		btnCancel.addActionListener(this);
		btnSearchDetail.addActionListener(this);
		btnDeleteDetail.addActionListener(this);
		btnUpdateDetail.addActionListener(this);
		
		tblListDetail.addMouseListener(this);
		
		btnCleanDetail.setMnemonic(KeyEvent.VK_C);
		btnCleanDetail.setToolTipText("Phím tắt : Alt + C");
		btnInsertDetail.setMnemonic(KeyEvent.VK_A);
		btnInsertDetail.setToolTipText("Phím tắt : Alt + A");
		btnSearchDetail.setMnemonic(KeyEvent.VK_S);
		btnSearchDetail.setToolTipText("Phím tắt : Alt + S");
		btnDeleteDetail.setMnemonic(KeyEvent.VK_D);
		btnDeleteDetail.setToolTipText("Phím tắt : Alt + D");
		btnUpdateDetail.setMnemonic(KeyEvent.VK_U);
		btnUpdateDetail.setToolTipText("Phím tắt : Alt + U");
		btnCancel.setMnemonic(KeyEvent.VK_E);
		btnCancel.setToolTipText("Phím tắt : Alt + E");
		
	}
	

	// Tạo mã hợp đồng sản xuất
	public void randomIdDetailProduction() {
		int order = detail_DAO.getOrderDetailPresent() + 1;
		txtDetailProductionId.setText(order+"");
		txtQuantityProduction.setText("");
		cmbState.setSelectedIndex(0);
	}
	
	// Tạo mã hợp đồng sản xuất khi sản phẩm chưa tồn tại
	public void randomIdDetailProductNull(int detailID) {
		int order = detailID + 1;
		txtDetailProductionId.setText(order+"");
		txtQuantityProduction.setText("");
		cmbState.setSelectedIndex(0);
	}
	
	// Lấy và hiển thị danh sách hợp đồng sản xuất theo mã sản phẩm
	public void loadListDetail(String productID) {
		List<entity.DetailProduction> listDetail;
		listDetail = detail_DAO.getListDetailbyIdProduct(productID);
		for (entity.DetailProduction detail : listDetail) {
			addRowDetail(detail);
		}
	}
	
	// Lấy và hiển thị danh sách hợp đồng sản xuất với danh sách đã cho
	public void loadListDetailWithListDetail(List<entity.DetailProduction> listDetail) {
		for (entity.DetailProduction detail : listDetail) {
			addRowDetail(detail);
		}
	}
	
	// Thêm hợp đồng sản xuất vào bảng hợp đồng
	public void addRowDetail(entity.DetailProduction detail) {
		boolean isStateFinished = detail.getQuantityProduction() == detail.getQuantityFinished() ? true : false;
		if(isStateFinished) {
			detail_DAO.updateStateFinishDetailProduct(detail.getDetailProductionID());
		}
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		String[] row = { detail.getDetailProductionID()+"", detail.getQuantityProduction()+"", detail.getQuantityFinished()+"", detail.getState(), DateFor.format(detail.getDate()) };
		dtmListDetail.addRow(row);
	}
	
	// Lấy và hiển thị danh sách hợp đồng sản xuất theo mã sản phẩm và tình trạng
	public void loadListDetailByState(String productID, String state) {
		List<entity.DetailProduction> listDetail;
		listDetail = detail_DAO.getListDetailbyIdProductAndState(productID, state);
		for (entity.DetailProduction detail : listDetail) {
			addRowDetail(detail);
		}
	}
	
	// Xóa dữ liệu trên bảng
	private void deleteDataOnTableModel(DefaultTableModel dtm) {
		dtm.setRowCount(0);
	}
	
	
	public static void main(String[] args) {
		new DetailProduction(productID, listDetailOnTable).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnCancel)) {
			setVisible(false);
		}
		else if(o.equals(btnCleanDetail)) {
			Product product = Dao_Product.searchProductByIdProduct(productID);
			if(product != null) {
				randomIdDetailProduction();
				btnInsertDetail.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnInsertDetail.setText("Thêm");
				btnUpdateDetail.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			}
			else {
				randomIdDetailProductNull(listDetailOnTable.get(listDetailOnTable.size() - 1).getDetailProductionID());
				btnInsertDetail.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnInsertDetail.setText("Thêm");
				btnUpdateDetail.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			}
		}
		else if(o.equals(btnInsertDetail)) {
			if(regexDetailProduct()) {
				if(btnInsertDetail.getText().equals("Thêm")) {
					if(Dao_Product.searchProductByIdProduct(productID) != null) {
						if(detail_DAO.searchDetailByIdDetail(txtDetailProductionId.getText(), productID) == null) {
							entity.DetailProduction detailInsert = new entity.DetailProduction(Integer.parseInt(txtDetailProductionId.getText()), Integer.parseInt(txtQuantityProduction.getText()), cmbState.getSelectedItem().toString(), productID, null);
							boolean isInsertDetail = detail_DAO.insertDetailProduction(detailInsert);
							if(isInsertDetail) {
								JOptionPane.showMessageDialog(this, "Thêm hợp đồng sản xuất thành công!!!");
								cmbSearchState.setSelectedItem(detailInsert.getState());
								deleteDataOnTableModel(dtmListDetail);
								loadListDetailByState(productID, detailInsert.getState());
								randomIdDetailProduction();
								
							}
							else {
								JOptionPane.showMessageDialog(this, "Thêm hợp đồng sản xuất thất bại");
							}
						}
						else {
							JOptionPane.showMessageDialog(this, "Hợp đồng sản xuất đã tồn tại");
						}
					}
					else {
						if(detail_DAO.searchDetailByIdDetail(txtDetailProductionId.getText(), productID) == null) {
							deleteDataOnTableModel(dtmListDetail);
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
							String date = format.format(cal.getTime());// United States style of format.
							java.util.Date myDate = null;
							java.sql.Date sqlDate = null;
							try {
								myDate = format.parse(date);
								sqlDate = new java.sql.Date( myDate.getTime() );
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							entity.DetailProduction detailNew = new entity.DetailProduction(Integer.parseInt(txtDetailProductionId.getText()), Integer.parseInt(txtQuantityProduction.getText()), cmbState.getSelectedItem().toString(), productID, sqlDate);
							listDetailOnTable.add(detailNew);
							for(entity.DetailProduction detail : listDetailOnTable) {
								addRowDetail(detail);
							}
							randomIdDetailProductNull(listDetailOnTable.get(listDetailOnTable.size() - 1).getDetailProductionID());
						}
						else {
							JOptionPane.showMessageDialog(this, "Hợp đồng sản xuất đã tồn tại");
						}
					}
					
				}
				else {
					entity.DetailProduction detailUpdate = detail_DAO.searchDetailByIdDetail(txtDetailProductionId.getText(), productID);
					detailUpdate.setQuantityProduction(Integer.parseInt(txtQuantityProduction.getText()));
					detailUpdate.setState(cmbState.getSelectedItem().toString());
					boolean updateDetail = detail_DAO.updateDetail(detailUpdate);
					if(
						(preState.equals("Ngưng Sản Xuất") && cmbState.getSelectedItem().toString().equals("Ngưng Sản Xuất") && !dtmListDetail.getValueAt(tblListDetail.getSelectedRow(), 1).toString().equals(txtQuantityProduction.getText()))
						|| (preState.equals("Hoàn Thành") && cmbState.getSelectedItem().toString().equals("Hoàn Thành") && !dtmListDetail.getValueAt(tblListDetail.getSelectedRow(), 1).toString().equals(txtQuantityProduction.getText()))
					) {
						if(cmbState.getSelectedItem().toString().equals("Ngưng Sản Xuất")) {
							JOptionPane.showMessageDialog(this, "Trước và sau khi cập nhật, hợp đồng sản xuất hiện vẫn ngưng sản xuất nên không thể thay đổi số lượng sản xuất của hợp đồng");
						}
						else {
							JOptionPane.showMessageDialog(this, "Trước và sau khi cập nhật, hợp đồng sản xuất hiện vẫn hoàn thành nên không thể thay đổi số lượng sản xuất của hợp đồng");
						}
					}
					else if(updateDetail) {
						if(cmbState.getSelectedItem().toString().equals("Sản Xuất")) {
							detail_DAO.updateQuantityFinishDetail(0, Integer.parseInt(dtmListDetail.getValueAt(tblListDetail.getSelectedRow(), 0).toString()));
						}
						JOptionPane.showMessageDialog(this, "Cập nhật hợp đồng sản xuất thành công!!!");
						cmbSearchState.setSelectedItem(detailUpdate.getState());
						deleteDataOnTableModel(dtmListDetail);
						loadListDetailByState(productID, cmbState.getSelectedItem().toString());
					}
					else {
						JOptionPane.showMessageDialog(this, "Cập nhật hợp đồng sản xuất thất bại");
					}
				}
			}
		}
		else if(o.equals(btnSearchDetail)) {
			if(!txtSearchDetail.getText().isEmpty()) {
				entity.DetailProduction detail = detail_DAO.searchDetailByIdDetail(txtSearchDetail.getText(), productID);
				if(detail != null) {
					cmbSearchState.setSelectedItem(detail.getState());
					deleteDataOnTableModel(dtmListDetail);
					addRowDetail(detail);
				}
				else {
					deleteDataOnTableModel(dtmListDetail);
				}
			}
			else {
				deleteDataOnTableModel(dtmListDetail);
				loadListDetail(productID);
				cmbSearchState.setSelectedIndex(0);
			}
		}
		else if(o.equals(btnUpdateDetail)) {
			if(btnUpdateDetail.getIcon().toString().equals("images\\Text-Edit-icon.png")) {
				btnInsertDetail.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
				btnInsertDetail.setText("Cập nhật");
				btnUpdateDetail.setIcon(new ImageIcon("images\\math-add-icon.png"));
			}else {
				btnInsertDetail.setIcon(new ImageIcon("images\\math-add-icon.png"));
				btnInsertDetail.setText("Thêm");
				btnUpdateDetail.setIcon(new ImageIcon("images\\Text-Edit-icon.png"));
			}
		}
		else if(o.equals(btnDeleteDetail)) {
			if(tblListDetail.getSelectedRow() >= 0) {
				int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa sản xuất này không?");
				if(confirm == JOptionPane.YES_OPTION) {
					int rowSelected = tblListDetail.getSelectedRow();
					if(rowSelected >= 0) {
						boolean isDeleteDetail = detail_DAO.isDeleteDetail(Integer.parseInt(dtmListDetail.getValueAt(rowSelected, 0).toString()));
						if(isDeleteDetail) {
							if(detail_DAO.deleteDetail(Integer.parseInt(dtmListDetail.getValueAt(rowSelected, 0).toString()))){
								JOptionPane.showMessageDialog(this, "Xóa hợp đồng sản xuất thành công!!!");
								deleteDataOnTableModel(dtmListDetail);
								if(cmbSearchState.getSelectedItem().toString().equals("Tất Cả")) {
									loadListDetail(productID);
								}
								else {
									loadListDetailByState(productID, cmbSearchState.getSelectedItem().toString());
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(this, "Hợp đồng sản xuất đã hoặc đang được thực hiện, không thể xóa");
						}
					}
				}
				else if(confirm == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this, "Hủy xóa sản xuất thành công!!!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Chọn hợp đồng sản xuất cần xóa");
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(tblListDetail)) {
			int rowSelected = tblListDetail.getSelectedRow();
			txtDetailProductionId.setText(dtmListDetail.getValueAt(rowSelected, 0).toString());
			txtQuantityProduction.setText(dtmListDetail.getValueAt(rowSelected, 1).toString());
			cmbState.setSelectedItem(dtmListDetail.getValueAt(rowSelected, 3));
			preState = dtmListDetail.getValueAt(rowSelected, 3).toString();
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
	
	// Kiểm tra thông tin nhập của hợp đồng sản xuất
	public boolean regexDetailProduct() {
		String announce = "";
		String regexIdProduction = "^([1-9][0-9]*)+";
		String regexQuantityProduct = "^([1-9][0-9]*)+";
		if (txtDetailProductionId.getText().isEmpty() || txtQuantityProduction.getText().isEmpty()) {
			announce += "Vui lòng nhập đầy đủ thông tin hợp đồng";
		} else {
			if (!txtDetailProductionId.getText().matches(regexIdProduction)) {
				announce += "Mã chi tiết sản xuất phải bắt đầu bằng mã sản phẩm và theo sau 2 chữ số \n";
				txtDetailProductionId.requestFocus();
				txtDetailProductionId.selectAll();
			} else if (!txtQuantityProduction.getText().matches(regexQuantityProduct)) {
				announce += "Số lượng phải lớn hơn 0";
				txtQuantityProduction.requestFocus();
				txtQuantityProduction.selectAll();
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
