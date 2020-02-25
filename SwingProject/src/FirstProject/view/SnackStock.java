package FirstProject.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import FirstProject.model.Snack;
import FirstProject.model.SnackDBA;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnackStock extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField tfstock;
	private JButton button;
	private SnackDBA sdba = new SnackDBA();
	private ArrayList<Snack> arr;
	private JButton button_1;
	private int row1;
	private int row2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnackStock frame = new SnackStock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SnackStock() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		setTitle("재고 관리");
		setBackground(Color.PINK);
		setBounds(650, 300, 635, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
		setSize(450, 350);
		setVisible(true);
		snackView();
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setDividerSize(0);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getPanel_1());
			splitPane.setDividerLocation(250);
		}
		return splitPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.PINK);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.PINK);
			panel_1.setLayout(null);
			panel_1.add(getTfstock());
			panel_1.add(getButton());
			panel_1.add(getButton_1());
		}
		return panel_1;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.getViewport().setBackground(Color.PINK);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					row1 = table.getSelectedRow();
					row2 = arr.get(row1).getNum();
				}
			});
			table.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
			table.setBackground(Color.PINK);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "상품 명", "상품 가격", "재고" })

			);
		}
		return table;
	}

	private JTextField getTfstock() {
		if (tfstock == null) {
			tfstock = new JTextField();
			tfstock.setHorizontalAlignment(SwingConstants.RIGHT);
			tfstock.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tfstock.setColumns(10);
			tfstock.setBackground(Color.PINK);
			tfstock.setBounds(44, 12, 114, 33);
		}
		return tfstock;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("재고 추가");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (row2 == 0) {
						JOptionPane.showMessageDialog(null, "재고 추가 할 상품을 선택하세요");
						return;
					}
					try {
						int stock = arr.get(row1).getStock() + Integer.parseInt(tfstock.getText());
						sdba.snackAdd(stock, arr.get(row1).getNum());
						tfstock.setText("");						
						snackView();
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "숫자를 입력하세요");
					}

				}
			});
			button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			button.setBackground(Color.ORANGE);
			button.setBounds(170, 10, 131, 33);
		}
		return button;
	}

	private void snackView() {
		arr = sdba.snackView();
		String[] cols = { "상품 명", "상품 가격", "재고" };
		DefaultTableModel dt = new DefaultTableModel(cols, arr.size());
		table.setModel(dt);
		for (int i = 0; i < arr.size(); i++) {
			dt.setValueAt(arr.get(i).getName(), i, 0);
			dt.setValueAt(arr.get(i).getPrice()+"원", i, 1);
			dt.setValueAt(arr.get(i).getStock(), i, 2);
		}
	}

	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("삭제");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (row2 == 0) {
						JOptionPane.showMessageDialog(null, "삭제할 상품을 선택하세요");
						return;
					}
					try {
						int result = JOptionPane.showConfirmDialog(null, "정말 삭제할까요?", "삭제",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							sdba.snackDelete(row2);
							snackView();
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			button_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			button_1.setBackground(Color.ORANGE);
			button_1.setBounds(313, 10, 85, 33);

		}
		return button_1;

	}
}
