package FirstProject.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.windows.resources.windows;

import FirstProject.model.Bar;
import FirstProject.model.BarDBA;
import FirstProject.model.Snack;
import FirstProject.model.SnackDBA;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class BarMain extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton table1;
	private JButton table2;
	private JButton table3;
	private JButton table4;
	private JButton table5;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JButton button;
	BarDBA bdba = new BarDBA();
	private JButton btnReset;
	private JButton btnList;
	public BarTable t1;
	public BarTable t2;
	public BarTable t3;
	public BarTable t4;
	public BarTable t5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarMain frame = new BarMain();
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
	public BarMain() {
		setTitle("인생 술집");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 767, 583);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.PINK);
			panel.setLayout(null);
			panel.add(getTable1());
			panel.add(getTable2());
			panel.add(getTable3());
			panel.add(getTable4());
			panel.add(getTable5());
			panel.add(getBtnNewButton());
			panel.add(getLblNewLabel_1());
			panel.add(getButton());
			panel.add(getBtnReset());
			panel.add(getBtnList());
		}
		return panel;
	}

	private JButton getTable1() {
		if (table1 == null) {
			table1 = new JButton("테이블1");
			table1.setBackground(new Color(204, 204, 255));
			table1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t1 = new BarTable("table1", "테이블1", BarMain.this);
				}
			});
			table1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			table1.setBounds(30, 60, 103, 114);
		}
		return table1;
	}

	private JButton getTable2() {
		if (table2 == null) {
			table2 = new JButton("테이블2");
			table2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					t2 = new BarTable("table2", "테이블2", BarMain.this);
				}
			});
			table2.setBackground(new Color(204, 204, 255));
			table2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			table2.setBounds(188, 60, 103, 114);
		}
		return table2;
	}

	private JButton getTable3() {
		if (table3 == null) {
			table3 = new JButton("테이블3");
			table3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t3 = new BarTable("table3", "테이블3", BarMain.this);
				}
			});
			table3.setBackground(new Color(204, 204, 255));
			table3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			table3.setBounds(30, 207, 103, 114);
		}
		return table3;
	}

	private JButton getTable4() {
		if (table4 == null) {
			table4 = new JButton("테이블4");
			table4.setForeground(new Color(0, 0, 0));
			table4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t4 = new BarTable("table4", "테이블4", BarMain.this);
				}
			});
			table4.setBackground(new Color(204, 204, 255));
			table4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			table4.setBounds(188, 207, 103, 114);
		}
		return table4;
	}

	private JButton getTable5() {
		if (table5 == null) {
			table5 = new JButton("테이블5");
			table5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t5 = new BarTable("table5", "테이블5", BarMain.this);
				}
			});
			table5.setForeground(new Color(0, 0, 0));
			table5.setBackground(new Color(204, 204, 255));
			table5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			table5.setBounds(30, 362, 262, 114);
		}
		return table5;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("상품 추가");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new SnackAdd();
				}
			});
			btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnNewButton.setBounds(471, 191, 116, 58);
		}
		return btnNewButton;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("인생 술집");
			lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD | Font.ITALIC, 25));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(284, 10, 138, 40);
		}
		return lblNewLabel_1;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("재고 관리");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new SnackStock();
				}
			});
			button.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			button.setBounds(471, 278, 116, 58);
		}
		return button;
	}

	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("초기화");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "정말 초기화할까요?", "삭제",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						bdba.barReset();
					}
				}
			});
			btnReset.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnReset.setBounds(471, 346, 116, 58);
		}
		return btnReset;
	}

	private JButton getBtnList() {
		if (btnList == null) {
			btnList = new JButton("판매 내역");
			btnList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new BarList();	
				}
			});
			btnList.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnList.setBounds(471, 123, 116, 58);
		}
		return btnList;
	}
}
