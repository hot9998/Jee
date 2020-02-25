package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.model.BookBean;
import com.model.BookDBA;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BookView extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;
	private JSplitPane splitPane_2;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JScrollPane scrollPane;
	private JTextArea taView;
	private JPanel panel_1;
	private JButton btnInsert;
	private JButton btnView;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JTextField tfTitle;
	private JTextField tfWriter;
	private JTextField tfIndate;
	private JTextField tfOutdate;
	private JTextField tfGubun;
	private JTextField tfPrice;
	private JScrollPane scrollPane_1;
	private JTable table;
	private BookDBA dba = new BookDBA();
	private ArrayList<BookBean> arr;
	private int row;
	private JComboBox comboBox;
	private JTextField tfSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookView frame = new BookView();
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
	public BookView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getSplitPane(), BorderLayout.CENTER);
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setLeftComponent(getSplitPane_1());
			splitPane.setRightComponent(getSplitPane_2());
			splitPane.setDividerLocation(280);
		}
		return splitPane;
	}

	private JSplitPane getSplitPane_1() {
		if (splitPane_1 == null) {
			splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_1.setRightComponent(getPanel());
			splitPane_1.setLeftComponent(getScrollPane_1());
			splitPane_1.setDividerLocation(200);
		}
		return splitPane_1;
	}

	private JSplitPane getSplitPane_2() {
		if (splitPane_2 == null) {
			splitPane_2 = new JSplitPane();
			splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane_2.setLeftComponent(getScrollPane());
			splitPane_2.setRightComponent(getPanel_1());
			splitPane_2.setDividerLocation(350);
		}
		return splitPane_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getLblNewLabel_1());
			panel.add(getLblNewLabel_2());
			panel.add(getLblNewLabel_3());
			panel.add(getLblNewLabel_4());
			panel.add(getLblNewLabel_5());
			panel.add(getBtnInsert());
			panel.add(getTfTitle());
			panel.add(getTfWriter());
			panel.add(getTfIndate());
			panel.add(getTfOutdate());
			panel.add(getTfGubun());
			panel.add(getTfPrice());
		}
		return panel;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("제목");
			lblNewLabel.setBounds(12, 35, 57, 15);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("저자");
			lblNewLabel_1.setBounds(12, 71, 57, 15);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("입고날짜");
			lblNewLabel_2.setBounds(12, 113, 57, 15);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("출고날짜");
			lblNewLabel_3.setBounds(12, 151, 57, 15);
		}
		return lblNewLabel_3;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("책분류");
			lblNewLabel_4.setBounds(12, 189, 57, 15);
		}
		return lblNewLabel_4;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("가격");
			lblNewLabel_5.setBounds(12, 225, 57, 15);
		}
		return lblNewLabel_5;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTaView());
		}
		return scrollPane;
	}

	private JTextArea getTaView() {
		if (taView == null) {
			taView = new JTextArea();
		}
		return taView;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.add(getBtnView());
			panel_1.add(getBtnUpdate());
			panel_1.add(getBtnDelete());
			panel_1.add(getComboBox());
			panel_1.add(getTfSearch());
		}
		return panel_1;
	}

	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("입력하기");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						String title = (tfTitle.getText());
						String writer = (tfWriter.getText());
						String indate = (tfIndate.getText());
						String outdate = (tfOutdate.getText());
						String gubun = (tfGubun.getText());
						int price = (Integer.parseInt(tfPrice.getText()));
						BookBean book = new BookBean(title, writer, indate, outdate, gubun, price);
						dba.bookInsert(book);
						btnView.doClick();
						clearText();
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "제대로 된 값을 입력하세요");
					}
				}
			});
			btnInsert.setBounds(63, 266, 97, 23);
		}
		return btnInsert;
	}

	private JButton getBtnView() {
		if (btnView == null) {
			btnView = new JButton("보기");
			btnView.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					arr = dba.bookView();
					taView.setText("");
					// ta에 출력
//					for (BookBean book : arr) {
//						taView.append("번호:" + book.getNum() + "\n");
//						taView.append("제목:" + book.getTitle() + "\n");
//						taView.append("저자:" + book.getWriter() + "\n");
//						taView.append("가격:" + book.getPrice() + "\n\n");
//					}

//					 table 에 출력
					String[] cols = { "TITLE", "WRITER", "OUTDATE", "PRICE" };
					DefaultTableModel dt = new DefaultTableModel(cols, arr.size());
					table.setModel(dt);

					for (int i = 0; i < arr.size(); i++) {
						dt.setValueAt(arr.get(i).getTitle(), i, 0);
						dt.setValueAt(arr.get(i).getWriter(), i, 1);
						dt.setValueAt(arr.get(i).getOutdate(), i, 2);
						dt.setValueAt(arr.get(i).getPrice(), i, 3);
					}

				}
			});
			btnView.setBounds(23, 42, 63, 23);
		}
		return btnView;
	}

	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookBean book = new BookBean();
					book.setNum(row);
					book.setTitle(tfTitle.getText());
					book.setWriter(tfWriter.getText());
					book.setIndate(tfIndate.getText());
					book.setOutdate(tfOutdate.getText());
					book.setGubun(tfGubun.getText());
					book.setPrice(Integer.parseInt(tfPrice.getText()));
					dba.bookUpdate(book);
					btnView.doClick();
					clearText();
				}
			});
			btnUpdate.setBounds(100, 42, 70, 23);
		}
		return btnUpdate;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("삭제");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (row == 0) {
						JOptionPane.showMessageDialog(null, "삭제 번호를 선택하세요");
						return;
					}
					try {
						int result = JOptionPane.showConfirmDialog(null, "정말 삭제할까요?", "삭제",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							dba.bookDelete(row);
							clearText();
							btnView.doClick();
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
			btnDelete.setBounds(182, 42, 63, 23);
		}
		return btnDelete;
	}

	private JTextField getTfTitle() {
		if (tfTitle == null) {
			tfTitle = new JTextField();
			tfTitle.setBounds(81, 32, 116, 21);
			tfTitle.setColumns(10);
		}
		return tfTitle;
	}

	private JTextField getTfWriter() {
		if (tfWriter == null) {
			tfWriter = new JTextField();
			tfWriter.setBounds(81, 68, 116, 21);
			tfWriter.setColumns(10);
		}
		return tfWriter;
	}

	private JTextField getTfIndate() {
		if (tfIndate == null) {
			tfIndate = new JTextField();
			tfIndate.setBounds(81, 110, 116, 21);
			tfIndate.setColumns(10);
		}
		return tfIndate;
	}

	private JTextField getTfOutdate() {
		if (tfOutdate == null) {
			tfOutdate = new JTextField();
			tfOutdate.setBounds(81, 148, 116, 21);
			tfOutdate.setColumns(10);
		}
		return tfOutdate;
	}

	private JTextField getTfGubun() {
		if (tfGubun == null) {
			tfGubun = new JTextField();
			tfGubun.setBounds(81, 186, 116, 21);
			tfGubun.setColumns(10);
		}
		return tfGubun;
	}

	private JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setBounds(81, 222, 116, 21);
			tfPrice.setColumns(10);
		}
		return tfPrice;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTable());
		}
		return scrollPane_1;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			String[] cols = { "TITLE", "WRITER", "OUTDATE", "PRICE" };
			DefaultTableModel dt = new DefaultTableModel(cols, 0);
			table.setModel(dt);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					taView.setText("");
					int i = table.getSelectedRow(); // 테이블에 선택된 행 값
					taView.append("번호:" + arr.get(i).getNum() + "\n");
					taView.append("제목:" + arr.get(i).getTitle() + "\n");
					taView.append("저자:" + arr.get(i).getWriter() + "\n");
					taView.append("입고일:" + arr.get(i).getIndate() + "\n");
					taView.append("출고일:" + arr.get(i).getOutdate() + "\n");
					taView.append("구분:" + arr.get(i).getGubun() + "\n");
					taView.append("가격:" + arr.get(i).getPrice() + "\n");

					tfTitle.setText(arr.get(i).getTitle());
					tfWriter.setText(arr.get(i).getWriter());
					tfIndate.setText(arr.get(i).getIndate());
					tfOutdate.setText(arr.get(i).getOutdate());
					tfGubun.setText(arr.get(i).getGubun());
					tfPrice.setText(arr.get(i).getPrice() + "");

					row = arr.get(i).getNum();

				}
			});
		}

		return table;
	}

	private void clearText() {
		tfTitle.setText("");
		tfWriter.setText("");
		tfIndate.setText("");
		tfOutdate.setText("");
		tfGubun.setText("");
		tfPrice.setText("");
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "선택", "제목", "구분" }));
			comboBox.setBounds(23, 90, 63, 21);
		}
		return comboBox;
	}

	private JTextField getTfSearch() {
		if (tfSearch == null) {
			tfSearch = new JTextField();
			tfSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					taView.setText("");
					int index = comboBox.getSelectedIndex();
					String column = "";
					if (index == 1) {
						column = "title";
					} else if (index == 2) {
						column = "gubun";
					} else if (index == 0) {
						JOptionPane.showMessageDialog(null, "검색항목을 선택하세요");
						return;
					}
					if (tfSearch.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "검색어를 입력하세요");
						return;
					} 
					arr = dba.bookSearch(column, tfSearch.getText());
					for (BookBean book : arr) {
						taView.append("번호:" + book.getNum() + "\n");
						taView.append("제목:" + book.getTitle() + "\n");
						taView.append("저자:" + book.getWriter() + "\n");
						taView.append("가격:" + book.getPrice() + "\n\n");
						String[] cols = { "TITLE", "WRITER", "OUTDATE", "PRICE" };
						DefaultTableModel dt = new DefaultTableModel(cols, arr.size());
						table.setModel(dt);
						for (int i = 0; i < arr.size(); i++) {
							dt.setValueAt(arr.get(i).getTitle(), i, 0);
							dt.setValueAt(arr.get(i).getWriter(), i, 1);
							dt.setValueAt(arr.get(i).getOutdate(), i, 2);
							dt.setValueAt(arr.get(i).getPrice(), i, 3);
						}
					}

				}
			});
			tfSearch.setBounds(100, 90, 145, 21);
			tfSearch.setColumns(10);
		}
		return tfSearch;
	}
}