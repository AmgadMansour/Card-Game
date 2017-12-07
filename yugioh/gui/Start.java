package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.sun.prism.Image;

import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.listeners.Controller;

public class Start extends JFrame implements ActionListener {
	// Image im;
	JButton b ;
	JTextField x ;
	JTextField y ;
	
	public Start() throws MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException, IOException, CloneNotSupportedException {

		super();

		setSize(800, 800);
		// JLabel i = new JLabel (new
		//ImageIcon(getClass().getResource("a.jpg"))) ;
		// i.setBounds(0, 0, 800, 800);
		 getContentPane().setBackground(Color.cyan);
		// add(i) ;
		 this.setContentPane(new JLabel(new ImageIcon(
					"src/img.png")));
		 

		getContentPane().setLayout(null);
		JLabel xl = new JLabel("Player 1 Name");
		
		getContentPane().add(xl);
		xl.setForeground(Color.WHITE);
		xl.setBounds(110, 30 , 100, 30);
		 x = new JTextField();
		getContentPane().add(x);
		x.addActionListener(this);

		x.setBounds(110, 80, 200, 30);
		JLabel yl = new JLabel("Player 2 Name");
		getContentPane().add(yl);
		yl.setForeground(Color.WHITE);
		yl.setBounds(510, 30, 100, 30);
		 y = new JTextField();
		y.setBounds(510, 80, 200, 30);

		getContentPane().add(y);
		y.addActionListener(this);
		 b = new JButton("Start");
		getContentPane().add(b);
		b.setBounds(400, 550, 80, 30);
		b.setBackground(Color.WHITE);
		setVisible(true);
		b.addActionListener(this);

  
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b) {
			try {
				Controller con = new Controller(x.getText(), y.getText());
				this.setVisible(false);
			} catch (MissingFieldException | EmptyFieldException
					| UnknownCardTypeException | UnknownSpellCardException
					| IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}

	}
	public static void main(String[] args) throws MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException, IOException, CloneNotSupportedException {
		Start s =new Start() ;
	}

}
