package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.Friend;
import com.model.FriendDBA;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FriendView extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField tfname;
	private JTextField tfbirth;
	private JTextField tfphone;
	private JTextField tfaddr;
	private JButton btnview;
	private JButton btninsert;
	private JSplitPane splitPane_1;
	private JScrollPane scrollPane;
	private JTextArea ta;
	private JButton btnupdate;
	private JButton btndelete;
	private JPanel panel_1;
	private JComboBox searchbox;
	private JTextField searchtf;
	private JButton btnsearch;
	private JLabel lblNewLabel_2;
	private JTextField tfnum;
	private FriendDBA fdba = new FriendDBA();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FriendView frame = new FriendView();
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
	public FriendView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setLeftComponent(getPanel());
			splitPane.setRightComponent(getSplitPane_1());
			splitPane.setDividerLocation(300);
		}
		return splitPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLabel());
			panel.add(getLabel_1());
			panel.add(getLblNewLabel());
			panel.add(getLblNewLabel_1());
			panel.add(getTfname());
			panel.add(getTfbirth());
			panel.add(getTfphone());
			panel.add(getTfaddr());
			panel.add(getBtnview());
			panel.add(getBtninsert());
			panel.add(getBtnupdate());
			panel.add(getBtndelete());
			panel.add(getLblNewLabel_2());
			panel.add(getTfnum());

		}
		return panel;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("이름");
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setBounds(48, 62, 57, 15);
		}
		return label;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("생일");
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setBounds(48, 107, 57, 15);
		}
		return label_1;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("전화번호");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(48, 155, 57, 15);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("주소");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setBounds(48, 198, 57, 15);
		}
		return lblNewLabel_1;
	}

	private JTextField getTfname() {
		if (tfname == null) {
			tfname = new JTextField();
			tfname.setBounds(117, 59, 116, 21);
			tfname.setColumns(10);
		}
		return tfname;
	}

	private JTextField getTfbirth() {
		if (tfbirth == null) {
			tfbirth = new JTextField();
			tfbirth.setBounds(117, 104, 116, 21);
			tfbirth.setColumns(10);
		}
		return tfbirth;
	}

	private JTextField getTfphone() {
		if (tfphone == null) {
			tfphone = new JTextField();
			tfphone.setBounds(117, 152, 116, 21);
			tfphone.setColumns(10);
		}
		return tfphone;
	}

	private JTextField getTfaddr() {
		if (tfaddr == null) {
			tfaddr = new JTextField();
			tfaddr.setBounds(117, 195, 116, 21);
			tfaddr.setColumns(10);
		}
		return tfaddr;
	}

	private JButton getBtnview() {
		if (btnview == null) {
			btnview = new JButton("전체보기");
			btnview.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ArrayList<Friend> arr = fdba.viewFriend();
					ta.setText("");
					for (Friend f : arr) {
						ta.append("번호:" + f.getNum() + "\n");
						ta.append("이름:" + f.getName() + "\n");
						ta.append("생일:" + f.getBirth() + "\n");
						ta.append("전화번호:" + f.getPhone() + "\n");
						ta.append("주소:" + f.getAddr() + "\n\n");
					}
				}
			});
			btnview.setBounds(39, 242, 97, 23);
		}
		return btnview;
	}

	private JButton getBtninsert() {
		if (btninsert == null) {
			btninsert = new JButton("친구등록");
			btninsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (tfname.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "값을 입력하세요");
					} else {
						Friend f = new Friend();
						f.setName(tfname.getText());
						f.setBirth(tfbirth.getText());
						f.setPhone(tfphone.getText());
						f.setAddr(tfaddr.getText());

						fdba.insertFriend(f);
						btnview.doClick();
						textClear();

					}

				}
			});
			btninsert.setBounds(148, 242, 97, 23);
		}
		return btninsert;

	}

	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setLeftComponent(getScrollPane());
			splitPane_1.setRightComponent(getPanel_1());
			splitPane_1.setDividerLocation(250);
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
		}
		return ta;
	}

	private void textClear() {
		tfname.setText("");
		tfbirth.setText("");
		tfphone.setText("");
		tfaddr.setText("");
	}

	private JButton getBtnupdate() {
		if (btnupdate == null) {
			btnupdate = new JButton("수정하기");
			btnupdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {

						Friend f = new Friend();
						f.setNum(Integer.parseInt(tfnum.getText()));
						f.setName(tfname.getText());
						f.setBirth(tfbirth.getText());
						f.setPhone(tfphone.getText());
						f.setAddr(tfaddr.getText());
						fdba.updateFriend(f);
						btnview.doClick();
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "번호를 입력하세요");
					}
				}
			});
			btnupdate.setBounds(39, 332, 97, 23);
		}
		return btnupdate;
	}

	private JButton getBtndelete() {
		if (btndelete == null) {
			btndelete = new JButton("삭제하기");
			btndelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int result = JOptionPane.showConfirmDialog(null, "정말 삭제할까요?", "Confirm",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							fdba.deleteFriend(Integer.parseInt(tfnum.getText()));
							btnview.doClick();
						}
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "번호를 입력하세요");
					} catch (NullPointerException n) {
						JOptionPane.showMessageDialog(null, "번호를 입력하세요");
					}
				}
			});
			btndelete.setBounds(148, 332, 97, 23);
		}
		return btndelete;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getSearchbox());
			panel_1.add(getSearchtf());
			panel_1.add(getBtnsearch());
		}
		return panel_1;
	}

	private JComboBox getSearchbox() {
		if (searchbox == null) {
			searchbox = new JComboBox();
			searchbox.setModel(new DefaultComboBoxModel(new String[] { "선택", "이름", "생일", "전화번호", "주소" }));
			searchbox.setBounds(12, 66, 75, 21);
		}
		return searchbox;
	}

	private JTextField getSearchtf() {
		if (searchtf == null) {
			searchtf = new JTextField();
			searchtf.setBounds(99, 66, 92, 21);
			searchtf.setColumns(10);
		}
		return searchtf;
	}

	private JButton getBtnsearch() {
		if (btnsearch == null) {
			btnsearch = new JButton("검색");
			btnsearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ta.setText("");
					String column = "";
					if (searchbox.getSelectedIndex() == 1) {
						column = "name";
					} else if (searchbox.getSelectedIndex() == 2) {
						column = "birth";
					} else if (searchbox.getSelectedIndex() == 3) {
						column = "phone";
					} else if (searchbox.getSelectedIndex() == 4) {
						column = "addr";
					} else if (searchbox.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(null, "검색항목을 선택하세요");
						return;
					}
					if (searchtf.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "검색어를 입력하세요");
						return;
					}
					ArrayList<Friend> arr = fdba.searchFriend(column, searchtf.getText());
					for (Friend f : arr) {
						ta.append("번호:" + f.getNum() + "\n");
						ta.append("이름:" + f.getName() + "\n");
						ta.append("생일:" + f.getBirth() + "\n");
						ta.append("전화번호:" + f.getPhone() + "\n");
						ta.append("주소:" + f.getAddr() + "\n\n");
					}
					searchtf.setText("");

				}
			});
			btnsearch.setBounds(203, 65, 66, 23);
		}
		return btnsearch;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("번호");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(39, 294, 57, 15);
		}
		return lblNewLabel_2;
	}

	private JTextField getTfnum() {
		if (tfnum == null) {
			tfnum = new JTextField();
			tfnum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Friend f = fdba.numFriend(Integer.parseInt(tfnum.getText()));
						tfname.setText(f.getName());
						tfbirth.setText(f.getBirth());
						tfphone.setText(f.getPhone());
						tfaddr.setText(f.getAddr());
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(null, "번호를 입력하세요");
					}
				}
			});
			tfnum.setBounds(108, 291, 139, 21);
			tfnum.setColumns(10);
		}
		return tfnum;
	}
}
