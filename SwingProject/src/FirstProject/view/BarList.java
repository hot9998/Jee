package FirstProject.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import FirstProject.model.Bar;
import FirstProject.model.BarDBA;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.ArrayList;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BarList extends JFrame {

	private JPanel contentPane;
	private BarDBA bdba = new BarDBA();
	private ArrayList<Bar> arrbar;
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel label;
	private JLabel moneyLabel;
	private int sum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarList frame = new BarList();
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
	public BarList() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		setTitle("판매 내역");
		setBackground(Color.PINK);
		setBounds(650, 300, 635, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getSplitPane_1(), BorderLayout.CENTER);
		setSize(450, 350);
		setVisible(true);
		listView();
	}

	private void listView() {
		arrbar = bdba.barView("table0");
		String[] cols = { "상품 명", "상품 가격", "판매 개수", "합계" };
		DefaultTableModel dt = new DefaultTableModel(cols, arrbar.size());
		table.setModel(dt);
		for (int i = 0; i < arrbar.size(); i++) {
			dt.setValueAt(arrbar.get(i).getName(), i, 0);
			dt.setValueAt(arrbar.get(i).getPrice() + "원", i, 1);
			dt.setValueAt(arrbar.get(i).getCount(), i, 2);
			dt.setValueAt(arrbar.get(i).getMoney() + "원", i, 3);
			sum += arrbar.get(i).getMoney();
		}
		moneyLabel.setText(sum + "원");

	}

	private JSplitPane getSplitPane_1() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setDividerSize(0);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getPanel_1());
			splitPane.setDividerLocation(80);
		}
		return splitPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.PINK);
			panel.setLayout(null);
			panel.add(getLabel());
			panel.add(getMoneyLabel());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel_1;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
			scrollPane.getViewport().setBackground(Color.PINK);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
			table.setBackground(Color.PINK);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "상품 명", "상품 가격", "판매 갯수", "합계" }));
		}

		return table;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("총액");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			label.setBounds(64, 21, 109, 37);
		}
		return label;
	}

	private JLabel getMoneyLabel() {
		if (moneyLabel == null) {
			moneyLabel = new JLabel("");
			moneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			moneyLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			moneyLabel.setBounds(185, 21, 109, 37);
		}
		return moneyLabel;
	}
}
