package ui;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.DetailPRoductionDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class DetailProduction extends JFrame implements ActionListener {
	private JTextField txtDetailProductionId;
	private JTextField txtQuantityProduction;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JComboBox cmbState;
	private static String productID;
	private static entity.DetailProduction detail = null;
	private DetailPRoductionDAO detail_DAO = new DetailPRoductionDAO();

	public DetailProduction(String productid, entity.DetailProduction details) {
		setSize(500, 250);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0,140,140));
		productID = productid;
		detail = details;
		
		JLabel lblDetailProductionId = new JLabel("Mã Sản Xuất : ");
		lblDetailProductionId.setForeground(Color.WHITE);
		lblDetailProductionId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDetailProductionId.setBounds(36, 27, 99, 21);
		getContentPane().add(lblDetailProductionId);
		
		txtDetailProductionId = new JTextField();
		txtDetailProductionId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDetailProductionId.setBounds(184, 28, 246, 22);
		getContentPane().add(txtDetailProductionId);
		txtDetailProductionId.setColumns(10);
		
		JLabel lblQuantityProduction = new JLabel("Số Lượng Sản Xuất : ");
		lblQuantityProduction.setForeground(Color.WHITE);
		lblQuantityProduction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantityProduction.setBounds(36, 77, 138, 21);
		getContentPane().add(lblQuantityProduction);
		
		txtQuantityProduction = new JTextField();
		txtQuantityProduction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuantityProduction.setColumns(10);
		txtQuantityProduction.setBounds(184, 77, 246, 22);
		getContentPane().add(txtQuantityProduction);
		
		JLabel lblState = new JLabel("Tình Trạng : ");
		lblState.setForeground(Color.WHITE);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblState.setBounds(36, 127, 99, 21);
		getContentPane().add(lblState);
		
		cmbState = new JComboBox();
		cmbState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbState.setModel(new DefaultComboBoxModel(new String[] {"Sản Xuất", "Ngưng Sản Xuất", "Hoàn Thành"}));
		cmbState.setBounds(184, 127, 246, 22);
		getContentPane().add(cmbState);
		
		btnConfirm = new JButton("Xác nhận");
		btnConfirm.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\PayrollManagementSystem\\images\\Accept-icon.png"));
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfirm.setBounds(229, 171, 109, 25);
		getContentPane().add(btnConfirm);
		
		btnCancel = new JButton("Hủy");
		btnCancel.setIcon(new ImageIcon("C:\\Users\\DELL\\eclipse-workspace\\PayrollManagementSystem\\images\\Button-Close-icon.png"));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.setBounds(345, 171, 85, 25);
		getContentPane().add(btnCancel);
		// TODO Auto-generated constructor stub
		
		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);
		
		txtDetailProductionId.setEditable(false);

		if(detail.getDetailProductionID() == null) {
			randomIdDetailProduction(productID);
		}
		else {
			txtDetailProductionId.setText(detail.getDetailProductionID());
			txtQuantityProduction.setText(detail.getQuantityProduction()+"");
		}
	}
	

	public void randomIdDetailProduction(String detailID) {
		int order = (int) (Math.random() * (99 - 1 + 1) + 1);
		String idDetail = "";
		if (order >= 10) {
			idDetail = detailID + order + "";
		} else {
			idDetail = detailID + "0" + order;
		}
		
		txtDetailProductionId.setText(idDetail);

		boolean test = true;
		while (detail_DAO.searchDetailProductionById(idDetail) == null && test) {
			txtDetailProductionId.setText(idDetail);
			test = false;
			break;
		}
		if (test) {
			randomIdDetailProduction(detailID);
		}
	}
	
	
	public static void main(String[] args) {
		new DetailProduction(productID,detail).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnCancel)) {
			setVisible(false);
		}
		else {
			detail.setDetailProductionID(txtDetailProductionId.getText());
			if(!txtQuantityProduction.getText().isEmpty()) {
				detail.setQuantityProduction(Integer.parseInt(txtQuantityProduction.getText()));
			}
			detail.setState(cmbState.getSelectedItem().toString());
			detail.setProductId(productID);
			setVisible(false);
		}
		
	}
}
