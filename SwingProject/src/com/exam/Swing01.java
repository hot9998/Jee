package com.exam;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Swing01 extends JFrame {

	private JPanel contentPane;
	private JButton btn1;
	private JTextField tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing01 frame = new Swing01();
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
	public Swing01() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(getBtn1());
		contentPane.add(getTf());
	}
	private JButton getBtn1() {
		if (btn1 == null) {
			btn1 = new JButton("클릭");
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tf.setText("안녕");
				}
			});
			btn1.setBackground(Color.ORANGE);
			btn1.setBounds(62, 69, 141, 86);
		}
		return btn1;
	}
	private JTextField getTf() {
		if (tf == null) {
			tf = new JTextField();
			tf.setHorizontalAlignment(SwingConstants.CENTER);
			tf.setBounds(62, 165, 304, 51);
			tf.setColumns(10);
		}
		return tf;
	}
}
