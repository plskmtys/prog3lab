package org.caesar;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CaesarFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField up = new JTextField(20);
	private JTextField down = new JTextField(20);
	private JButton button = new JButton("Code!");
	private String [] letters =  {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox combo = new JComboBox(letters);
	private boolean felso = true;
	
	CaesarFrame() {
		super("SwingLab");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,110);
		setResizable(false);
		down.setEditable(true);
		button.addActionListener(new OKButtonListener());

		DocumentListener upListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				if (felso) {
					down.setText(caesarCode(up.getText(), combo.getSelectedItem().toString()));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				if (felso) {
					down.setText(caesarCode(up.getText(), combo.getSelectedItem().toString()));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				if (felso) {
					down.setText(caesarCode(up.getText(), combo.getSelectedItem().toString()));
				}
			}
		};
		up.getDocument().addDocumentListener(upListener);

		FocusListener downFocus = new FocusListener() {
			@Override
			public void focusGained(FocusEvent focusEvent) {
				felso = false;
			}

			@Override
			public void focusLost(FocusEvent focusEvent) {}
		};

		FocusListener upFocus = new FocusListener() {
			@Override
			public void focusGained(FocusEvent focusEvent) {
				felso = true;
			}
	
			@Override
			public void focusLost(FocusEvent focusEvent) {
				// semmi
			}
		};
		up.addFocusListener(upFocus);

		down.addFocusListener(downFocus);
		
		JLabel label = new JLabel("Output:");
		setLayout(new GridLayout(2,1));
		
		JPanel upper = new JPanel();
		JPanel downer = new JPanel();
		upper.setLayout(new FlowLayout(FlowLayout.CENTER, 0,5));
		downer.setLayout(new BorderLayout(0, 0));
		
		upper.add(combo);
		upper.add(up);
		upper.add(button);
		
		downer.add(label, BorderLayout.WEST);
		downer.add(down);
		add(upper);
		add(downer);
	}
	final class OKButtonListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        if (felso) {
	            down.setText(caesarCode(up.getText(), combo.getSelectedItem().toString()));
	        } else {
	            up.setText(caesarDecode(down.getText(), combo.getSelectedItem().toString()));
	        }
	    }
	}
	
	private String caesarCode(String input, String offset) {
	    StringBuilder code = new StringBuilder();
	    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    int shift = letters.indexOf(offset.toUpperCase());

	    for (int i = 0; i < input.length(); i++) {
	        char c = input.charAt(i);
	        if (Character.isUpperCase(c)) {
	            char encodedChar = (char) (c + shift);
	            if (encodedChar > 'Z') {
	                encodedChar -= 26;
	            }
	            code.append(encodedChar);
	        } else if (Character.isLowerCase(c)) {
	            char encodedChar = (char) (c + shift);
	            if (encodedChar > 'z') {
	                encodedChar -= 26;
	            }
	            code.append(encodedChar);
	        } else {
	            code.append(c);
	        }
	    }
	    return code.toString();
	}
	
	private String caesarDecode(String input, String offset) {
	    StringBuilder code = new StringBuilder();
	    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    int shift = letters.indexOf(offset.toUpperCase());

	    for (int i = 0; i < input.length(); i++) {
	        char c = input.charAt(i);
	        if (Character.isUpperCase(c)) {
	            char decodedChar = (char) (c - shift);
	            if (decodedChar < 'A') {
	                decodedChar += 26;
	            }
	            code.append(decodedChar);
	        } else if (Character.isLowerCase(c)) {
	            char decodedChar = (char) (c - shift);
	            if (decodedChar < 'a') {
	                decodedChar += 26;
	            }
	            code.append(decodedChar);
	        } else {
	            code.append(c);
	        }
	    }
	    return code.toString();
	}
}
