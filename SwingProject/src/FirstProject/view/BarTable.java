package FirstProject.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import FirstProject.model.Bar;
import FirstProject.model.BarDBA;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarTable extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JLabel label_1;
	private JSplitPane splitPane_1;
	private JPanel panel_1;
	private JLabel label_2;
	private JSplitPane splitPane_2;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_3;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	ArrayList<Bar> arrbar;
	private String tname;
	private BarDBA bdba = new BarDBA();
	private int row1;
	private int row2;
	private int sum;
	public JLabel moneyLabel;
	private JLabel titleLabel;
	BarMain bm;
	BarTable bt;

	public BarTable(String tname, String title, BarMain bm) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		setTitle(title + " 주문 현황");
		setBounds(650, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
		titleLabel.setText(title);
		setSize(450, 350);
		setVisible(true);
		this.bm = bm;
		this.tname = tname;
		showTable();
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setDividerSize(0);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getSplitPane_1());
			splitPane.setDividerLocation(50);
		}
		return splitPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.PINK);
			panel.setLayout(null);
			panel.add(getLabel_1());
			panel.add(getTitleLabel());
		}
		return panel;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("주문 내역");
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			label_1.setBounds(220, 10, 109, 37);
		}
		return label_1;
	}

	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setDividerSize(0);
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setLeftComponent(getPanel_1_1());
			splitPane_1.setRightComponent(getSplitPane_2());
			splitPane_1.setDividerLocation(40);
		}
		return splitPane_1;
	}

	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.PINK);
			panel_1.setLayout(null);
			panel_1.add(getLabel_2());
			panel_1.add(getMoneyLabel());
		}
		return panel_1;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("총액");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			label_2.setBounds(52, 0, 109, 37);
		}
		return label_2;
	}

	private JSplitPane getSplitPane_2() {
		if (splitPane_2 == null) {
			splitPane_2 = new JSplitPane();
			splitPane_2.setDividerSize(0);
			splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_2.setLeftComponent(getPanel_2());
			splitPane_2.setRightComponent(getPanel_3());
			splitPane_2.setDividerLocation(150);
		}
		return splitPane_2;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel_2;
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
				public void mouseClicked(MouseEvent arg0) {
					row1 = table.getSelectedRow();
					row2 = arrbar.get(row1).getNum();
				}
			});
			table.setBackground(Color.PINK);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "상품명", "상품 가격", "갯수", "합계" }));
			table.getColumnModel().getColumn(1).setPreferredWidth(74);
		}
		return table;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(1, 0, 0, 0));
			panel_3.add(getBtnNewButton_2());
			panel_3.add(getBtnNewButton_1());
			panel_3.add(getBtnNewButton());
		}
		return panel_3;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("계산");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int result = JOptionPane.showConfirmDialog(null, "정말 계산할까요?", "삭제",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							for (int i = 0; i < arrbar.size(); i++) {
								bdba.barAccount(tname, arrbar.get(i));
							}
							showTable();
							moneyLabel.setText("0");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			});
			btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			btnNewButton.setBackground(Color.PINK);
		}
		return btnNewButton;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("상품 취소");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (row2 == 0) {
						JOptionPane.showMessageDialog(null, "취소할 상품을 선택하세요");
						return;
					}
					try {
						int result = JOptionPane.showConfirmDialog(null, "정말 취소할까요?", "삭제",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							bdba.barDelete(tname, arrbar.get(row1));
							showTable();
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			});
			btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			btnNewButton_1.setBackground(Color.PINK);
		}
		return btnNewButton_1;

	}

	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("상품 판매");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new SnackOrder(tname, arrbar, BarTable.this);
				}
			});
			btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			btnNewButton_2.setBackground(Color.PINK);
		}
		return btnNewButton_2;
	}

	public void showTable() {
		arrbar = bdba.barView(tname);
		String[] cols = { "상품명", "상품 가격", "갯수", "합계" };
		DefaultTableModel dt = new DefaultTableModel(cols, arrbar.size());
		table.setModel(dt);
		sum = 0;
		for (int i = 0; i < arrbar.size(); i++) {
			dt.setValueAt(arrbar.get(i).getName(), i, 0);
			dt.setValueAt(arrbar.get(i).getPrice() + "원", i, 1);
			dt.setValueAt(arrbar.get(i).getCount(), i, 2);
			dt.setValueAt(arrbar.get(i).getMoney() + "원", i, 3);
			sum += arrbar.get(i).getMoney();
		}
		moneyLabel.setText(sum + "원");
	}

	public JLabel getMoneyLabel() {
		if (moneyLabel == null) {
			moneyLabel = new JLabel("");
			moneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			moneyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			moneyLabel.setBounds(173, 0, 135, 37);
		}
		return moneyLabel;
	}

	private JLabel getTitleLabel() {
		if (titleLabel == null) {
			titleLabel = new JLabel("");
			titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			titleLabel.setBounds(99, 10, 109, 37);
		}
		return titleLabel;
	}

	class TableRunnable implements Runnable {
		@Override
		synchronized public void run() {
			while (true) {
				try {
					Thread.sleep(100);
					showTable();
				} catch (InterruptedException e) {
					return;
				}
			}

		}

	}
}
