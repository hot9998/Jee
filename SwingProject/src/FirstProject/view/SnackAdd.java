package FirstProject.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import FirstProject.model.Snack;
import FirstProject.model.SnackDBA;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnackAdd extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel label;
	private JTextField tfanju;
	private JButton btnNewButton;
	private JLabel label_1;
	private JTextField tfprice;
	private JLabel label_2;
	private JTextField tfstock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnackAdd frame = new SnackAdd();
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
	public SnackAdd() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		setTitle("상품 추가");
		setBounds(650, 300, 460, 343);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		setSize(450, 350);
		setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.PINK);
			panel.setLayout(null);
			panel.add(getLabel());
			panel.add(getTfanju());
			panel.add(getBtnNewButton());
			panel.add(getLabel_1());
			panel.add(getTfprice());
			panel.add(getLabel_2_1());
			panel.add(getTextField_2_1());
		}
		return panel;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("상품 명");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			label.setBounds(39, 46, 92, 37);
		}
		return label;
	}

	private JTextField getTfanju() {
		if (tfanju == null) {
			tfanju = new JTextField();
			tfanju.setHorizontalAlignment(SwingConstants.RIGHT);
			tfanju.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tfanju.setBackground(Color.PINK);
			tfanju.setBounds(187, 46, 165, 33);
			tfanju.setColumns(10);
		}
		return tfanju;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("추가");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (tfanju.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "안주 이름을 입력하세요");
							return;
						}
						Snack sn = new Snack();
						sn.setName(tfanju.getText());
						sn.setPrice(Integer.parseInt(tfprice.getText()));
						sn.setStock(Integer.parseInt(tfstock.getText()));
						SnackDBA sdba = new SnackDBA();
						sdba.snackInsert(sn);
						tfanju.setText("");
						tfprice.setText("");
						tfstock.setText("");
						JOptionPane.showMessageDialog(null, "해당 상품이 등록 되었습니다.");

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "숫자를 입력하세요");
					}

				}
			});
			btnNewButton.setBackground(Color.ORANGE);
			btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			btnNewButton.setBounds(165, 240, 81, 37);
		}
		return btnNewButton;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("가격");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			label_1.setBounds(39, 103, 92, 37);
		}
		return label_1;
	}

	private JTextField getTfprice() {
		if (tfprice == null) {
			tfprice = new JTextField();
			tfprice.setHorizontalAlignment(SwingConstants.RIGHT);
			tfprice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tfprice.setColumns(10);
			tfprice.setBackground(Color.PINK);
			tfprice.setBounds(187, 103, 165, 33);
		}
		return tfprice;
	}

	private JLabel getLabel_2_1() {
		if (label_2 == null) {
			label_2 = new JLabel("재고");
			label_2.setHorizontalAlignment(SwingConstants.RIGHT);
			label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			label_2.setBounds(39, 158, 92, 37);
		}
		return label_2;
	}

	private JTextField getTextField_2_1() {
		if (tfstock == null) {
			tfstock = new JTextField();
			tfstock.setHorizontalAlignment(SwingConstants.RIGHT);
			tfstock.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tfstock.setColumns(10);
			tfstock.setBackground(Color.PINK);
			tfstock.setBounds(187, 162, 165, 33);
		}
		return tfstock;
	}

}
