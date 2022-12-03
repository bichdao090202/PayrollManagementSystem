package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import custom_field.JTextFieldHint;
import entity.TimesheetFactory;
//import model.TimesheetDAO;
import model.TimesheetFactoryDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.ListSelectionModel;

public class TimesheetWorkerGUI extends JFrame implements ActionListener {
	public TimesheetWorkerGUI() {
		getUI();
	}

	private static final long serialVersionUID = 1L;
	private final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	private static final Color COLOR = new Color(14, 85, 78);
	private static final Color COLOR_HOVER = new Color(36, 217, 199);
	private static final Date CURRENT_DATE = new Date();
	private final String[] headerTableTimesheet = new String[] { "Mã chấm công", "Nhân viên", "Sản phẩm", "Quy trình",
			"Ngày chấm công", "Số lượng thành phẩm" };
	private final String[] headerTableAssignment = new String[] { "Mã phân công", "Nhân viên", "Sản phẩm", "Quy trình", "Ngày tham gia", "Mã hợp đồng", "Hoàn thành", "Chưa hoàn thành"};
	private JPanel contentPane;
	private DefaultTableModel dtmAssignment, dtmWorker;
	private TimesheetFactoryDAO timesheetFactoryDAO;
	private JTable tblAssignment, tblTimesheet;
	private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
	private JSpinField spinAmountCompleted;
	private JTextField txtWorker, txtProduct, txtProduce;
	private JDateChooser dateTimesheet;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimesheetWorkerGUI frame = new TimesheetWorkerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Component getUI() {

		timesheetFactoryDAO = new TimesheetFactoryDAO();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 690);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnAssignment_Input = new JPanel();
		pnAssignment_Input.setBackground(Color.WHITE);
		contentPane.add(pnAssignment_Input, BorderLayout.NORTH);
		pnAssignment_Input.setLayout(new BorderLayout(0, 0));

		JPanel pnInput = new JPanel();
		pnInput.setBackground(Color.WHITE);
		pnAssignment_Input.add(pnInput, BorderLayout.EAST);
		pnInput.setLayout(new MigLayout("", "[][100px:n:110px,grow][][:50px:50px]", "[][][][][][]"));

		JLabel lblWorker = new JLabel("Nhân viên");
		pnInput.add(lblWorker, "cell 0 0,alignx left");

		txtWorker = new JTextField();
		txtWorker.setBackground(Color.WHITE);
		txtWorker.setEditable(false);
		pnInput.add(txtWorker, "cell 1 0,growx, span 3");
		txtWorker.setColumns(10);

		JLabel lblProductID = new JLabel("Sản phẩm");
		pnInput.add(lblProductID, "cell 0 1,alignx left");

		txtProduct = new JTextField();
		txtProduct.setBackground(Color.WHITE);
		txtProduct.setEditable(false);
		txtProduct.setColumns(10);
		pnInput.add(txtProduct, "cell 1 1,growx, span 3");

		JLabel lblProduceID = new JLabel("Quy trình");
		pnInput.add(lblProduceID, "cell 0 2,alignx left");

		txtProduce = new JTextField();
		txtProduce.setBackground(Color.WHITE);
		txtProduce.setEditable(false);
		txtProduce.setColumns(10);
		pnInput.add(txtProduce, "cell 1 2,growx, span 3");

		JLabel lblDateTimesheet = new JLabel("Ngày chấm công:");
		pnInput.add(lblDateTimesheet, "cell 0 3,alignx left,aligny top");

		dateTimesheet = new JDateChooser();
		dateTimesheet.getCalendarButton().setBackground(Color.WHITE);
		dateTimesheet.setMaxSelectableDate(CURRENT_DATE);
		dateTimesheet.setDate(CURRENT_DATE);
		dateTimesheet.setDateFormatString("yyyy-MM-dd");
		pnInput.add(dateTimesheet, "cell 1 3,growx,aligny top");

		JLabel lblAmountCompleted = new JLabel("Số lượng thành phẩm:");
		pnInput.add(lblAmountCompleted, "cell 2 3,alignx left,aligny top");

		spinAmountCompleted = new JSpinField();
		spinAmountCompleted.setValue(1);
		spinAmountCompleted.setMinimum(1);
		pnInput.add(spinAmountCompleted, "cell 3 3,growx,aligny top");

		JPanel pnOperations = new JPanel();
		pnOperations.setBackground(Color.WHITE);
		pnOperations.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnInput.add(pnOperations, "flowx,cell 0 4 4 1,alignx center");

		btnAdd = new JButton("Thêm chấm công");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdd.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAdd.setBackground(Color.WHITE);
			}
		});
		btnAdd.setBorder(new LineBorder(COLOR, 2, false));
		btnAdd.setForeground(COLOR);
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(this);
		btnAdd.setIcon(new ImageIcon("images\\operations\\new.png"));
		btnAdd.setFocusable(false);
		pnOperations.add(btnAdd);

		btnDelete = new JButton("Xóa chấm công");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(Color.WHITE);
			}
		});
		btnDelete.setBorder(new LineBorder(COLOR, 2, false));
		btnDelete.setForeground(COLOR);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setIcon(new ImageIcon("images\\operations\\delete.png"));
		btnDelete.setFocusable(false);
		pnOperations.add(btnDelete);

		btnUpdate = new JButton("Cập nhật chấm công");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setBackground(Color.WHITE);
			}
		});
		btnUpdate.setBorder(new LineBorder(COLOR, 2, false));
		btnUpdate.setForeground(COLOR);
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setIcon(new ImageIcon("images\\operations\\update.png"));
		btnUpdate.setFocusable(false);
		btnUpdate.addActionListener(this);
		pnInput.add(btnUpdate, "cell 1 5");

		JScrollPane scrollTableAssignment = new JScrollPane();
		scrollTableAssignment.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Bảng phân công"));
		scrollTableAssignment.setPreferredSize(new Dimension(600, 200));
		scrollTableAssignment.setMaximumSize(new Dimension(32767, 167));
		pnAssignment_Input.add(scrollTableAssignment, BorderLayout.CENTER);

		tblAssignment = new JTable(dtmAssignment = new DefaultTableModel(headerTableAssignment, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblAssignment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAssignment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblAssignment.getSelectedRow();
				if (rowSelected >= 0) {
					txtWorker.setText(tblAssignment.getValueAt(rowSelected, 1).toString());
					txtProduct.setText(tblAssignment.getValueAt(rowSelected, 2).toString());
					txtProduce.setText(tblAssignment.getValueAt(rowSelected, 3).toString());
					dateTimesheet.setDate(CURRENT_DATE);
					spinAmountCompleted.setValue(1);
				}
			}
		});
		tblAssignment.setFillsViewportHeight(true);
		tblAssignment.getTableHeader().setOpaque(false);
		tblAssignment.getTableHeader().setBackground(COLOR);
		tblAssignment.getTableHeader().setForeground(Color.WHITE);
		scrollTableAssignment.setViewportView(tblAssignment);

		JPanel pnTable = new JPanel();
		pnTable.setBackground(Color.WHITE);
		pnTable.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2, true), "Bảng chấm công"));
		pnTable.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnTable, BorderLayout.CENTER);

		JScrollPane scrollTableTimesheet = new JScrollPane();
		scrollTableTimesheet.setBackground(Color.WHITE);
		pnTable.add(scrollTableTimesheet);

		tblTimesheet = new JTable(dtmWorker = new DefaultTableModel(headerTableTimesheet, 0)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTimesheet.setFillsViewportHeight(true);
		tblTimesheet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTimesheet.getTableHeader().setBackground(COLOR);
		tblTimesheet.getTableHeader().setForeground(Color.WHITE);
		tblTimesheet.setBackground(Color.WHITE);
		tblTimesheet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = tblTimesheet.getSelectedRow();
				if (rowSelected >= 0) {
					txtWorker.setText(tblTimesheet.getValueAt(rowSelected, 1).toString());
					txtProduct.setText(tblTimesheet.getValueAt(rowSelected, 2).toString());
					txtProduce.setText(tblTimesheet.getValueAt(rowSelected, 3).toString());
					try {
						Date date = sdfDate.parse(tblTimesheet.getValueAt(rowSelected, 4).toString());
						dateTimesheet.setDate(date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					spinAmountCompleted
							.setValue(Integer.parseInt(tblTimesheet.getValueAt(rowSelected, 5).toString()));
				}
			}
		});
		scrollTableTimesheet.setViewportView(tblTimesheet);

		JPanel pnSearch = new JPanel();
		pnSearch.setBackground(Color.WHITE);
		pnTable.add(pnSearch, BorderLayout.NORTH);
		pnSearch.setLayout(new MigLayout("", "[7px][][]", "[20px,grow]"));

		JTextFieldHint txtSearch = new JTextFieldHint("Nhập mã nhân viên");
		txtSearch.setBackground(Color.WHITE);
		txtSearch.setMinimumSize(new Dimension(150, 20));
		pnSearch.add(txtSearch, "cell 0 0,grow");

		JDateChooser dateSearch = new JDateChooser();
		dateSearch.setDateFormatString("yyyy-MM-dd");
		dateSearch.getCalendarButton().setBackground(Color.WHITE);
		dateSearch.setMinimumSize(new Dimension(150, 20));
		pnSearch.add(dateSearch, "cell 1 0,grow");

		btnSearch = new JButton();
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(COLOR_HOVER);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(Color.WHITE);
			}
		});
		btnSearch.setBorder(new LineBorder(COLOR, 2, false));
		btnSearch.setForeground(COLOR);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setIcon(new ImageIcon("images\\operations\\filter.png"));
		btnSearch.setFocusable(false);
		pnSearch.add(btnSearch, "cell 2 0");


		loadDateToTableAssignment();
		loadDateToTableTimesheet();
		return contentPane;
	}

	public void loadDateToTableAssignment() {
		dtmAssignment = new DefaultTableModel(headerTableAssignment, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblAssignment.setModel(dtmAssignment);
		List<String> listAssignment = timesheetFactoryDAO.getAllAssignment();
		for (String assignment : listAssignment) {
			dtmAssignment.addRow(assignment.split(";"));
		}
	}

	public void loadDateToTableTimesheet() {
		dtmWorker = new DefaultTableModel(headerTableTimesheet, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTimesheet.setModel(dtmWorker);
		List<String> listTimesheet = timesheetFactoryDAO.getAllTimesheetFactory();
		for (String timesheet : listTimesheet) {
			dtmWorker.addRow(timesheet.split(";"));
		}
	}

	public Date getTime(JSpinField spinHour, JSpinField spinMinute) {
		Date time = null;
		try {
			time = sdfTime.parse(spinHour.getValue() + ":" + spinMinute.getValue() + ":00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			int rowSelected = tblAssignment.getSelectedRow();
			int assignmentID = Integer.parseInt(tblAssignment.getValueAt(rowSelected, 0).toString());
			int quantity = spinAmountCompleted.getValue();
			String emp = txtWorker.getText();
			Date date = dateTimesheet.getDate();
			TimesheetFactory timesheetsFactory = new TimesheetFactory(date, quantity, assignmentID);
			if (JOptionPane.showConfirmDialog(this,
					"Bạn có chắn chắn muốn thêm chấm công cho nhân viên " + emp + " không?", "Thông báo",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				if (timesheetFactoryDAO.addTimesheetsFactory(timesheetsFactory)) {
					loadDateToTableTimesheet();
					JOptionPane.showMessageDialog(this, "Thêm chấm công thành công.", "Thông báo",
							JOptionPane.NO_OPTION, null);
				} else {
					JOptionPane.showMessageDialog(this, "Thêm chấm công không thành công.", "Thông báo",
							JOptionPane.NO_OPTION, null);
				}
			}
		}
		if (e.getSource() == btnUpdate) {
			int rowSelected = tblTimesheet.getSelectedRow();
			if (rowSelected >= 0) {
				int timesheetID = Integer.parseInt(tblTimesheet.getValueAt(rowSelected, 0).toString());
				int quantity = spinAmountCompleted.getValue();
				String emp = txtWorker.getText();
				Date date = dateTimesheet.getDate();
				TimesheetFactory timesheetsFactory = new TimesheetFactory(timesheetID, date, quantity);
				if (JOptionPane.showConfirmDialog(this,
						"Bạn có chắn chắn muốn cập nhật chấm công cho nhân viên " + emp + " không?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					if (timesheetFactoryDAO.updateTimesheetsFactory(timesheetsFactory)) {
						loadDateToTableTimesheet();
						JOptionPane.showMessageDialog(this, "Cập nhật chấm công thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật chấm công không thành công.", "Thông báo",
								JOptionPane.NO_OPTION, null);
					}
				}
			}else {
				JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên cần cập nhật chấm công trước", "Thông báo",
						JOptionPane.NO_OPTION, null);
			}
			
		}
	}
}
