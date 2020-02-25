package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.Player;
import com.model.PlayerDBA;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerView extends JFrame {

	PlayerDBA dba = new PlayerDBA();
	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField tfnum;
	private JTextField tfname;
	private JTextField tfbirth;
	private JTextField tfweight;
	private JTextField tfheight;
	private JTextField tfkind;
	private JButton btnInsert;
	private JButton btnView;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JSplitPane splitPane_1;
	private JScrollPane scrollPane;
	private JTextArea ta;
	private JPanel panel_1;
	private JComboBox comboBox;
	private JTextField searchtf;
	private JButton searchbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerView frame = new PlayerView();
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
	public PlayerView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setDividerSize(1);
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getSplitPane_1());
			splitPane.setDividerLocation(250);
		}
		return splitPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getLblNewLabel_1());
			panel.add(getLblNewLabel_3());
			panel.add(getLblNewLabel_4());
			panel.add(getLblNewLabel_5());
			panel.add(getLblNewLabel_6());
			panel.add(getTfnum());
			panel.add(getTfname());
			panel.add(getTfbirth());
			panel.add(getTfweight());
			panel.add(getTfheight());
			panel.add(getTfkind());
			panel.add(getBtnInsert());
			panel.add(getBtnView());
			panel.add(getBtnUpdate());
			panel.add(getBtnDelete());
		}
		return panel;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("번호");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(12, 24, 57, 15);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("이름");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(12, 52, 57, 15);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("생일");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_3.setBounds(12, 81, 57, 15);
		}
		return lblNewLabel_3;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("몸무게");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setBounds(12, 109, 57, 15);
		}
		return lblNewLabel_4;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("키");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_5.setBounds(12, 137, 57, 15);
		}
		return lblNewLabel_5;
	}

	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("종목");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_6.setBounds(12, 165, 57, 15);
		}
		return lblNewLabel_6;
	}

	private JTextField getTfnum() {
		if (tfnum == null) {
			tfnum = new JTextField();
			tfnum.setEditable(false);
			tfnum.setBounds(81, 21, 116, 21);
			tfnum.setColumns(10);
		}
		return tfnum;
	}

	private JTextField getTfname() {
		if (tfname == null) {
			tfname = new JTextField();
			tfname.setColumns(10);
			tfname.setBounds(81, 49, 116, 21);
		}
		return tfname;
	}

	private JTextField getTfbirth() {
		if (tfbirth == null) {
			tfbirth = new JTextField();
			tfbirth.setColumns(10);
			tfbirth.setBounds(81, 78, 116, 21);
		}
		return tfbirth;
	}

	private JTextField getTfweight() {
		if (tfweight == null) {
			tfweight = new JTextField();
			tfweight.setColumns(10);
			tfweight.setBounds(81, 106, 116, 21);
		}
		return tfweight;
	}

	private JTextField getTfheight() {
		if (tfheight == null) {
			tfheight = new JTextField();
			tfheight.setColumns(10);
			tfheight.setBounds(81, 134, 116, 21);
		}
		return tfheight;
	}

	private JTextField getTfkind() {
		if (tfkind == null) {
			tfkind = new JTextField();
			tfkind.setColumns(10);
			tfkind.setBounds(81, 162, 116, 21);
		}
		return tfkind;
	}

	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("추가");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Player p = new Player();
						p.setName(tfname.getText());
						p.setBirth(tfbirth.getText());
						p.setWeight(Double.parseDouble(tfweight.getText()));
						p.setHeight(Double.parseDouble(tfheight.getText()));
						p.setKind(tfkind.getText());
						dba.playerInsert(p);
						btnView.doClick();
						clearText();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "제대로 된 값을 입력하세요");
					}
				}
			});
			btnInsert.setBounds(12, 204, 97, 23);
		}
		return btnInsert;
	}

	private JButton getBtnView() {
		if (btnView == null) {
			btnView = new JButton("보기");
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ta.setText("");
					ArrayList<Player> arr = dba.playerView(); // ArrayList<Player> 형태로 값을 받아옴
					for (Player p : arr) {
						ta.append("번호:" + p.getNum() + "\n");
						ta.append("이름:" + p.getName() + "\n");
						ta.append("생일:" + p.getBirth() + "\n");
						ta.append("몸무게:" + p.getWeight() + "\n");
						ta.append("키:" + p.getHeight() + "\n");
						ta.append("종목:" + p.getKind() + "\n\n");
					}
				}
			});
			btnView.setBounds(128, 204, 97, 23);
		}
		return btnView;
	}

	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int result = JOptionPane.showConfirmDialog(null, "정말 수정할까요?", "Confirm",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							Player p = new Player();
							p.setNum(Integer.parseInt(tfnum.getText()));
							p.setName(tfname.getText());
							p.setBirth(tfbirth.getText());
							p.setWeight(Double.parseDouble(tfweight.getText()));
							p.setHeight(Double.parseDouble(tfheight.getText()));
							p.setKind(tfkind.getText());
							dba.playerUpdate(p);
							btnView.doClick();
							clearText();
						}

					} catch (Exception ex) {
						// TODO: handle exception
					}

				}
			});
			btnUpdate.setBounds(12, 237, 97, 23);
		}
		return btnUpdate;

	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int num = Integer.parseInt(tfnum.getText());
						int result = JOptionPane.showConfirmDialog(null, "정말 삭제할까요?", "Confirm",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							dba.playerDelete(num);
							clearText();
						}

						btnView.doClick();
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "제대로 된 값을 입력하세요");
					}

				}
			});
			btnDelete.setBounds(128, 237, 97, 23);
		}
		return btnDelete;
	}

	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setDividerSize(3);
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setLeftComponent(getScrollPane());
			splitPane_1.setRightComponent(getPanel_1());
			splitPane_1.setDividerLocation(170);
		}
		return splitPane_1;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTa());
		}
		return scrollPane;
	}

	private JTextArea getTa() {
		if (ta == null) {
			ta = new JTextArea();
			ta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					try {
						int num = Integer.parseInt(ta.getSelectedText().trim());
						tfnum.setText(num + "");
						Player p = dba.playerDetail(tfnum.getText());
						tfname.setText(p.getName());
						tfbirth.setText(p.getBirth());
						tfheight.setText(p.getHeight() + "");
						tfweight.setText(p.getWeight() + "");
						tfkind.setText(p.getKind());
					} catch (NullPointerException n) {
						JOptionPane.showMessageDialog(null, "번호를 선택하세요"); // 오류창을 띄워줌
					} catch (NumberFormatException n) {
						JOptionPane.showMessageDialog(null, "번호를 선택하세요"); // 오류창을 띄워줌
					}

				}
			});
		}
		return ta;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getComboBox());
			panel_1.add(getSearchtf());
			panel_1.add(getSearchbtn());
		}
		return panel_1;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "이름", "종목" }));
			comboBox.setBounds(0, 45, 72, 21);
		}
		return comboBox;
	}

	private JTextField getSearchtf() {
		if (searchtf == null) {
			searchtf = new JTextField();
			searchtf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					searchbtn.doClick();
				}
			});
			searchtf.setBounds(71, 45, 137, 21);
			searchtf.setColumns(10);
		}
		return searchtf;
	}

	private JButton getSearchbtn() {
		if (searchbtn == null) {
			searchbtn = new JButton("검색");
			searchbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ta.setText("");
					int idx = comboBox.getSelectedIndex();
					String column = "";
					if (idx == 0) {
						column = "name";
					}
					if (idx == 1) {
						column = "kind";
					}
					ArrayList<Player> arr = dba.playerSearch(column, searchtf.getText());
					for (Player p : arr) {
						ta.append("번호:" + p.getNum() + "\n");
						ta.append("이름:" + p.getName() + "\n");
						ta.append("생일:" + p.getBirth() + "\n");
						ta.append("몸무게:" + p.getWeight() + "\n");
						ta.append("키:" + p.getHeight() + "\n");
						ta.append("종목:" + p.getKind() + "\n\n");
					}
					searchtf.setText("");
				}
			});
			searchbtn.setBounds(220, 45, 69, 21);
		}
		return searchbtn;
	}

	private void clearText() {
		tfnum.setText("");
		tfname.setText("");
		tfbirth.setText("");
		tfweight.setText("");
		tfheight.setText("");
		tfkind.setText("");
	}

}
