package FirstProject.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import FirstProject.model.Bar;
import FirstProject.model.BarDBA;
import FirstProject.model.Snack;
import FirstProject.model.SnackDBA;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnackOrder extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField tfsell;
	private JButton button;
	private SnackDBA sdba = new SnackDBA();
	private ArrayList<Snack> arrsn;
	private ArrayList<Bar> arrbar;
	private int row1;
	private int row2;
	private String tname;
	private BarDBA bdba = new BarDBA();
	private String sname;
	private Snack sn;
	BarTable bt;


	public SnackOrder(String tname, ArrayList<Bar> arrbar, BarTable bt) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		setTitle("판매");
		setBackground(Color.PINK);
		setBounds(650, 300, 635, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
		this.bt = bt;

		setSize(450, 350);
		setVisible(true);
		snackView();
		this.tname = tname;
		this.arrbar = arrbar;
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
			panel_1.add(getTfsell());
			panel_1.add(getButton());
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
				public void mouseClicked(MouseEvent arg0) {
					row1 = table.getSelectedRow();
					row2 = arrsn.get(row1).getNum();
					sname = arrsn.get(row1).getName();
					sn = arrsn.get(row1);
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

	private JTextField getTfsell() {
		if (tfsell == null) {
			tfsell = new JTextField();
			tfsell.setHorizontalAlignment(SwingConstants.RIGHT);
			tfsell.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tfsell.setColumns(10);
			tfsell.setBackground(Color.PINK);
			tfsell.setBounds(79, 10, 114, 33);
		}
		return tfsell;
	}

	private JButton getButton() {	
		if (button == null) {
			button = new JButton("판매");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					if (row2 == 0) {
						JOptionPane.showMessageDialog(null, "판매할 상품을 선택하세요");
						return;
					}
					try {
						if (arrsn.get(row1).getStock() - Integer.parseInt(tfsell.getText()) >= 0) {
							int stock = arrsn.get(row1).getStock() - Integer.parseInt(tfsell.getText());
							sdba.snackAdd(stock, arrsn.get(row1).getNum());
							bdba.barOrder(tname, sn, Integer.parseInt(tfsell.getText()));
							tfsell.setText("");
							bt.showTable();
							snackView();							
						} else {
							JOptionPane.showMessageDialog(null, "재고가 부족합니다");
						}
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "숫자를 입력하세요");
					}
				}
			});
			button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			button.setBackground(Color.ORANGE);
			button.setBounds(205, 10, 131, 33);
		}
		return button;
	}

	private void snackView() {
		arrsn = sdba.snackView();
		String[] cols = { "상품 명", "상품 가격", "재고" };
		DefaultTableModel dt = new DefaultTableModel(cols, arrsn.size());
		table.setModel(dt);
		for (int i = 0; i < arrsn.size(); i++) {
			dt.setValueAt(arrsn.get(i).getName(), i, 0);
			dt.setValueAt(arrsn.get(i).getPrice()+"원", i, 1);
			dt.setValueAt(arrsn.get(i).getStock(), i, 2);
		}
	}

}
