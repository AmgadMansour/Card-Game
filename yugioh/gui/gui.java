package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.Border;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.prism.Graphics;
import com.sun.prism.Image;
import com.sun.prism.j2d.J2DPipeline;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.listeners.Controller;

public class gui extends JFrame {
	public JTextArea mous;
	public String p1;
	public String p2;
	public Board c;
	public JPanel hand;
	public JPanel ohand;
	public JButton endturn;
	public JButton endphase;
	public Player up;
	public Player down;
	public JPanel y;
	public JLabel olp;
	JLabel or2;
	JLabel or1;
	JLabel r1;
	JLabel r2;
	public SpellButton S1;
	JButton grave;
	JButton ograve;

	public SpellButton S2;

	public SpellButton S3;
	public SpellButton S4;
	public SpellButton S5;
	public ArrayList<JButton> handJButtons;
	public ArrayList<MonsterButton> monstersjbuttons;
	public ArrayList<SpellButton> spellsjbuttons;

	public JLabel activp;
	public MonsterButton M1;

	public MonsterButton M2;

	public MonsterButton M3;
	public JButton sora;
	public MonsterButton M4;

	public MonsterButton M5;
	public SpellButton oS1;
	public SpellButton oS2;
	public SpellButton oS3;
	public SpellButton oS4;
	public SpellButton oS5;
	public MonsterButton oM1;

	public MonsterButton oM2;
	public MonsterButton oM3;
	public JLabel lp;
	ImageIcon back = new ImageIcon((getClass().getResource("back.png")));
	ImageIcon bk = new ImageIcon((getClass().getResource("bk.png")));

	public MonsterButton oM4;
	public JPanel x;
	public MonsterButton oM5;
	public JLabel phasee = new JLabel();

	public ArrayList<JButton> ohandJButtons;
	public ArrayList<MonsterButton> omonstersjbuttons;
	public ArrayList<SpellButton> ospellsjbuttons;

	public gui(String p1, String p2) throws MissingFieldException,
			EmptyFieldException, UnknownCardTypeException,
			UnknownSpellCardException, IOException, CloneNotSupportedException {

		super();
		this.setContentPane(new JLabel(new ImageIcon("src/background.png")));

		this.p1 = p1;
		this.p2 = p2;
		Player a = new Player(p1);
		Player b = new Player(p2);
		c = new Board();
		c.whoStarts(a, b);
		c.startGame(a, b);
		down = c.getActivePlayer();
		up = c.getOpponentPlayer();

		setSize(1500, 750);

		getContentPane().setLayout(null);// to be able to adjust size myself

		x = new JPanel();

		x.setSize(800, 300);// donot change size of buttons but change size of
							// girid layout
		x.setLayout(new GridLayout(2, 5));
		monstersjbuttons = new ArrayList<>(5);
		M1 = new MonsterButton("M1");
		// M1.setIcon(bk);
		M1.setBackground(Color.black);
		// M1.setIcon();

		M2 = new MonsterButton("M2");
		M1.setBackground(Color.black);

		// M2.setIcon(bk);
		M2.setBackground(Color.black);

		M3 = new MonsterButton("M3");
		// M3.setIcon(bk);
		M3.setBackground(Color.black);

		M4 = new MonsterButton("M4");
		// M4.setIcon(bk);
		M4.setBackground(Color.black);

		M5 = new MonsterButton("M5");
		// M5.setIcon(bk);
		M5.setBackground(Color.black);
		x.setOpaque(false);

		S1 = new SpellButton("S1");
		// S1.setIcon(bk);
		S1.setBackground(Color.black);

		S2 = new SpellButton("S2");
		// S2.setIcon(bk);
		S2.setBackground(Color.black);

		S3 = new SpellButton("S3");
		// S3.setIcon(bk);
		S3.setBackground(Color.black);

		S4 = new SpellButton("S4");
		// S4.setIcon(bk);
		S4.setBackground(Color.black);

		S5 = new SpellButton("S5");
		// S5.setIcon(bk);
		S5.setBackground(Color.black);

		monstersjbuttons.add(M1);
		x.add(M1);
		monstersjbuttons.add(M2);
		x.add(M2);
		monstersjbuttons.add(M3);

		x.add(M3);
		monstersjbuttons.add(M4);

		x.add(M4);
		monstersjbuttons.add(M5);

		x.add(M5);// donot define their position
		spellsjbuttons = new ArrayList<>(5);
		x.add(S1);
		spellsjbuttons.add(S1);

		x.add(S2);
		spellsjbuttons.add(S2);

		x.add(S3);
		spellsjbuttons.add(S3);

		x.add(S4);
		spellsjbuttons.add(S4);

		x.add(S5);
		spellsjbuttons.add(S5);
		int z = 0;
		while (z < monstersjbuttons.size()) {
			monstersjbuttons.get(z).setContentAreaFilled(false);
			monstersjbuttons.get(z).setFocusPainted(false);

			z++;
		}
		z = 0;
		while (z < spellsjbuttons.size()) {
			spellsjbuttons.get(z).setContentAreaFilled(false);
			spellsjbuttons.get(z).setFocusPainted(false);

			z++;
		}
		x.setVisible(true);
		sora = new JButton();
		getContentPane().add(sora);
		sora.setBounds(800, 150, 250, 368);
		sora.setOpaque(false);
		sora.setContentAreaFilled(false);
		sora.setBorderPainted(false);

		setVisible(true);
		getContentPane().add(x);
		x.setBounds(160, 350, 585, 234);
		hand = new JPanel();
		hand.setBounds(140, 584, 480, 117);
		hand.setLayout(new GridLayout());
		hand.setOpaque(false);
		int k = 0;
		handJButtons = new ArrayList<>();
		JButton tmp = null;
		MonsterCard mm;
		SpellCard ss;
		while (k < c.getActivePlayer().getField().getHand().size()) {
			if (c.getActivePlayer().getField().getHand().get(k) instanceof SpellCard) {
				ss = (SpellCard) c.getActivePlayer().getField().getHand()
						.get(k);
				tmp = new JButton(hatelsora(

				ss.getName()));
				System.out.println(c.getActivePlayer().getField().getHand()
						.get(k).getName()
						+ "ahoooooooo");

			} else if (c.getActivePlayer().getField().getHand().get(k) instanceof MonsterCard) {
				mm = (MonsterCard) c.getActivePlayer().getField().getHand()
						.get(k);
				tmp = new JButton(hatelsora(mm.getName()));

			}
			System.out.println(tmp.getSize() + "PPPPPPPPPPPPPPPPpPPPPHLIB");
			tmp.setBackground(Color.black);
			tmp.setContentAreaFilled(false);
			tmp.setFocusPainted(false);
			tmp.setBorderPainted(false);
			handJButtons.add(tmp);
			hand.add(tmp);

			k++;
		}

		lp = new JLabel("Life Points :" + " "
				+ c.getActivePlayer().getLifePoints());
		getContentPane().add(lp);
		lp.setBounds(5, 470, 100, 50);
		lp.setForeground(Color.green);
		// lp.setFont(new Font(null,Font.CENTER_BASELINE , 15));

		getContentPane().add(hand);
		JButton deck = new JButton(back);
		deck.setBounds(1250, 590, 80, 117);
		getContentPane().add(deck);
		grave = new JButton(back);
		grave.setBounds(1250, 470, 80, 117);
		getContentPane().add(grave);
		r1 = new JLabel("GraveYard"
				+ c.getActivePlayer().getField().getGraveyard().size());
		r1.setBounds(1170, 550, 210, 30);
		r1.setForeground(Color.green);
		getContentPane().add(r1);
		r2 = new JLabel("Deck" + " "
				+ c.getActivePlayer().getField().getDeck().getDeck().size());
		r2.setBounds(1180, 650, 220, 30);
		getContentPane().add(r2);
		r2.setForeground(Color.green);

		endturn = new JButton("End Turn");
		endturn.setBackground(Color.cyan);
		endphase = new JButton("End Phase");
		endphase.setBackground(Color.CYAN);

		JLabel name = new JLabel("Name :" + "\n"
				+ c.getActivePlayer().getName());
		name.setForeground(Color.green);

		// name.setFont(new Font(null,Font.CENTER_BASELINE , 15));
		name.setBounds(5, 520, 100, 30);
		endturn.setBounds(5, 400, 100, 30);
		endphase.setBounds(5, 300, 100, 30);

		activp = new JLabel("Active : " + c.getActivePlayer().getName() + "");
		activp.setBounds(5, 330, 100, 30);
		activp.setForeground(Color.green);

		getContentPane().add(activp);

		phasee = new JLabel("Main 1");
		getContentPane().add(phasee);
		phasee.setBounds(5, 375, 100, 30);
		phasee.setForeground(Color.green);

		getContentPane().add(endturn);
		getContentPane().add(endphase);
		getContentPane().add(name);
		y = new JPanel();
		y.setOpaque(false);
		y.setSize(1500, 750);// donot change size of buttons but change size of
								// girid layout
		y.setLayout(new GridLayout(2, 4));
		oS1 = new SpellButton("S1");
		// oS1.setIcon(bk);
		oS1.setBackground(Color.black);
		oS2 = new SpellButton("S2");
		// oS2.setIcon(bk);
		oS2.setBackground(Color.black);

		oS3 = new SpellButton("S3");
		// oS3.setIcon(bk);
		oS3.setBackground(Color.black);

		oS4 = new SpellButton("S4");
		// oS4.setIcon(bk);
		oS4.setBackground(Color.black);

		oS5 = new SpellButton("S5");
		// oS5.setIcon(bk);
		oS5.setBackground(Color.black);

		oM1 = new MonsterButton("M1");
		// oM1.setIcon(bk);
		oM1.setBackground(Color.black);

		oM2 = new MonsterButton("M2");
		// oM2.setIcon(bk);
		oM2.setBackground(Color.black);

		oM3 = new MonsterButton("M3");
		// oM3.setIcon(bk);
		oM3.setBackground(Color.black);

		oM4 = new MonsterButton("M4");
		// oM4.setIcon(bk);
		oM4.setBackground(Color.black);

		oM5 = new MonsterButton("M5");
		// oM5.setIcon(bk);
		oM5.setBackground(Color.black);

		omonstersjbuttons = new ArrayList<>(5);
		y.add(oM1);
		omonstersjbuttons.add(oM1);

		y.add(oM2);
		omonstersjbuttons.add(oM2);

		y.add(oM3);
		omonstersjbuttons.add(oM3);

		y.add(oM4);
		omonstersjbuttons.add(oM4);
		ospellsjbuttons = new ArrayList<>(5);
		y.add(oM5);// donot define their position
		omonstersjbuttons.add(oM5);

		y.add(oS1);
		ospellsjbuttons.add(oS1);
		y.add(oS2);
		ospellsjbuttons.add(oS2);

		y.add(oS3);
		ospellsjbuttons.add(oS3);

		y.add(oS4);
		ospellsjbuttons.add(oS4);

		y.add(oS5);
		ospellsjbuttons.add(oS5);

		y.add(oM1);
		omonstersjbuttons.add(oM1);
		y.add(oM2);
		omonstersjbuttons.add(oM2);

		y.add(oM3);
		omonstersjbuttons.add(oM3);

		y.add(oM4);
		omonstersjbuttons.add(oM4);
		y.setOpaque(false);

		y.add(oM5);
		omonstersjbuttons.add(oM5);

		y.setVisible(true);
		y.setBounds(160, 120, 585, 234);
		getContentPane().add(y);
		ohand = new JPanel();
		ohand.setBounds(120, 0, 480, 117);
		ohand.setOpaque(false);
		ohand.setLayout(new GridLayout());
		olp = new JLabel("Life Points" + "\n"
				+ c.getOpponentPlayer().getLifePoints());
		// int j = c.getOpponentPlayer().getField().getHand().size();
		// while (j != 0) {
		// ohand.add(new JButton());
		// j--;
		// }
		int kk = 0;
		ohandJButtons = new ArrayList<>();
		JButton otmp = null;
		MonsterCard omm;

		while (kk < c.getOpponentPlayer().getField().getHand().size()) {
			// if (c.getOpponentPlayer().getField().getHand().get(kk) instanceof
			// SpellCard) {
			// tmp = new JButton(hatelsora(c.getActivePlayer().getField()
			// .getHand().get(kk).getName()));
			// } else if (c.getOpponentPlayer().getField().getHand().get(kk)
			// instanceof MonsterCard) {
			// mm = (MonsterCard) c.getOpponentPlayer().getField().getHand()
			// .get(kk);
			// tmp = new JButton(hatelsora(mm.getName()));
			//
			// }
			tmp = new JButton(back);

			tmp.setContentAreaFilled(false);
			tmp.setFocusPainted(false);
			tmp.setBorderPainted(false);
			tmp.setBackground(Color.black);
			ohandJButtons.add(tmp);
			ohand.add(tmp);

			kk++;
		}
		z = 0;
		while (z < omonstersjbuttons.size()) {
			omonstersjbuttons.get(z).setContentAreaFilled(false);
			omonstersjbuttons.get(z).setFocusPainted(false);

			z++;
		}
		z = 0;
		while (z < ospellsjbuttons.size()) {
			ospellsjbuttons.get(z).setContentAreaFilled(false);
			ospellsjbuttons.get(z).setFocusPainted(false);

			z++;
		}
		ohand.setOpaque(false);
		JLabel oname = new JLabel("Name :" + "\n"
				+ c.getOpponentPlayer().getName());
		// oname.setFont(new Font(null,Font.CENTER_BASELINE , 15));
		oname.setBounds(5, 120, 100, 50);
		oname.setForeground(Color.green);

		getContentPane().add(oname);

		getContentPane().add(olp);
		olp.setBounds(5, 100, 100, 30);
		getContentPane().add(ohand);
		olp.setForeground(Color.green);

		JButton odeck = new JButton(back);
		odeck.setBounds(1250, 153, 80, 117);
		getContentPane().add(odeck);
		ograve = new JButton(back);
		ograve.setBounds(1250, 5, 80, 117);
		getContentPane().add(ograve);
		or1 = new JLabel(" Deck "
				+ c.getOpponentPlayer().getField().getDeck().getDeck().size());
		or1.setBounds(1170, 220, 220, 30);
		or1.setForeground(Color.green);

		getContentPane().add(or1);
		or2 = new JLabel("GraveYard "
				+ c.getOpponentPlayer().getField().getGraveyard().size());
		or2.setBounds(1180, 85, 220, 30);
		getContentPane().add(or2);
		or2.setForeground(Color.green);

		mous = new JTextArea();
		mous.setLineWrap(true);
		mous.setWrapStyleWord(true);

		mous.setVisible(false);
		getContentPane().add(mous);
		mous.setBounds(1120, 265, 220, 200);
		mous.setBackground(Color.black);

		getContentPane().setBackground(Color.black);

	}

	public void updateLabels() throws MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException, IOException, CloneNotSupportedException {
		if (c.getActivePlayer().getLifePoints() <= 0
				|| c.getOpponentPlayer().getLifePoints() <= 0
				|| c.getActivePlayer().getField().getDeck().getDeck().size() == 0
				|| c.getOpponentPlayer().getField().getDeck().getDeck().size() == 0) {
			String[] m = { "yes", "no" };
			Object selected = JOptionPane.showInputDialog(null,
					"The Winner is "+ c.getWinner().getName()+ "Do You Want to Play Again ? ", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
			String chos = (String) selected;
			if(chos.equals("yes")) {
				this.setVisible(false);
				Start s = new Start();
				
			}
			else {
				this.setVisible(false);
				String[] wrg = { "ok"};
				Object selec = JOptionPane.showInputDialog(null,
						"The Winner Is : "+c.getWinner().getName(), "Input",
						JOptionPane.INFORMATION_MESSAGE, null, wrg, wrg[0]);
				
			}
		}
			r1.setText(" GraveYard " + down.getField().getGraveyard().size());
		r1.setBackground(Color.green);
		r2.setText(" Deck " + " " + down.getField().getDeck().getDeck().size());
		r2.setBackground(Color.darkGray);

		or1.setText(" Deck " + up.getField().getDeck().getDeck().size());
		or1.setBackground(Color.darkGray);

		or2.setText("GraveYard " + up.getField().getGraveyard().size());
		or2.setBackground(Color.darkGray);

		lp.setText("Life Points :" + " " + down.getLifePoints());
		lp.setBackground(Color.darkGray);

		olp.setText("Life Points :" + " " + up.getLifePoints());
		olp.setBackground(Color.darkGray);

		activp.setText("Active :" + c.getActivePlayer().getName());
		activp.setBackground(Color.darkGray);

		phasee.setText(c.getActivePlayer().getField().getPhase() + "");
		phasee.setBackground(Color.darkGray);

		if (up.getField().getGraveyard().size() == 0)
			ograve.setIcon(back);
		else {
			ograve.setIcon(hatelsora(up.getField().getGraveyard()
					.get(up.getField().getGraveyard().size() - 1).getName()));
			System.out.println("ajwegkjebrgjsbrgjkbrkjgbegejrsb");
		}
		if (down.getField().getGraveyard().size() == 0)
			grave.setIcon(back);
		else
			grave.setIcon(hatelsora(down.getField().getGraveyard()
					.get(down.getField().getGraveyard().size() - 1).getName()));

	}

	public void updatedownhand() {
		// int i= 0 ;
		JButton t = null;
		// int n=handJButtons.size();
		while (!(handJButtons.isEmpty())) {
			t = handJButtons.get(0);
			hand.remove(t);
			handJButtons.remove(0);

		}
		MonsterCard mm;
		JButton tmp = null;
		int j = 0;
		if (down == c.getActivePlayer()) {

			while (j < down.getField().getHand().size()) {

				if (down.getField().getHand().get(j) instanceof SpellCard) {
					tmp = new JButton(hatelsora(down.getField().getHand()
							.get(j).getName()));
				} else if (down.getField().getHand().get(j) instanceof MonsterCard) {
					mm = (MonsterCard) down.getField().getHand().get(j);
					tmp = new JButton(hatelsora(mm.getName()));

				}
				tmp.setBackground(Color.black);
				tmp.setContentAreaFilled(false);
				tmp.setFocusPainted(false);
				tmp.setBorderPainted(false);
				handJButtons.add(tmp);
				hand.add(tmp);

				j++;

			}
		} else {
			while (j < down.getField().getHand().size()) {
				tmp = new JButton(back);
				tmp.setBackground(Color.black);

				handJButtons.add(tmp);
				hand.add(tmp);

				j++;
			}
		}
	}

	public void updateuphand() {
		JButton t = null;
		while (!(ohandJButtons.isEmpty())) {
			t = ohandJButtons.get(0);
			ohand.remove(t);
			ohandJButtons.remove(0);

		}
		MonsterCard omm;
		JButton otmp = null;
		int j = 0;
		if (up == c.getActivePlayer()) {
			while (j < up.getField().getHand().size()) {

				if (up.getField().getHand().get(j) instanceof SpellCard) {
					otmp = new JButton(hatelsora(up.getField().getHand().get(j)
							.getName()));
				} else if (up.getField().getHand().get(j) instanceof MonsterCard) {
					omm = (MonsterCard) up.getField().getHand().get(j);
					otmp = new JButton(hatelsora(omm.getName()));

				}
				otmp.setBackground(Color.black);
				otmp.setContentAreaFilled(false);
				otmp.setFocusPainted(false);
				otmp.setBorderPainted(false);
				ohandJButtons.add(otmp);
				ohand.add(otmp);

				j++;

			}
		} else {
			while (j < up.getField().getHand().size()) {
				otmp = new JButton(back);
				otmp.setBackground(Color.black);

				ohandJButtons.add(otmp);
				ohand.add(otmp);

				j++;
			}
		}
	}

	public void updateupmonsterarea() {

		int j = 0;
		while (j < 5) {
			if (j < up.getField().getMonstersArea().size()) {
				if (up.getField().getMonstersArea().get(j).getMode() == Mode.DEFENSE) {
					omonstersjbuttons.get(j)
							.setIcon(
									new ImageIcon((getClass()
											.getResource("bakon.png"))));
				} else {
					omonstersjbuttons.get(j).setIcon(
							hatelsora(up.getField().getMonstersArea().get(j)
									.getName()));
				}
			} else {
				omonstersjbuttons.get(j).setIcon(null);

			}
			j++;
		}
	}

	public void updatedownmonsterarea() {

		int j = 0;
		while (j < 5) {
			if (j < down.getField().getMonstersArea().size()) {
				if (down.getField().getMonstersArea().get(j).getMode() == Mode.DEFENSE) {
					monstersjbuttons.get(j)
							.setIcon(
									new ImageIcon((getClass()
											.getResource("bakon.png"))));
				} else {
					monstersjbuttons.get(j).setIcon(
							hatelsora(down.getField().getMonstersArea().get(j)
									.getName()));
				}
			} else {
				monstersjbuttons.get(j).setIcon(null);

			}

			j++;
		}
	}

	public void updatedownspellarea() {

		int j = 0;
		while (j < 5) {
			if (j < down.getField().getSpellArea().size()) {
				try {
					spellsjbuttons.get(j).setIcon(
							new ImageIcon(
									(getClass().getResource("backon.png"))));
				} catch (NullPointerException n) {

				}
			} else {
				spellsjbuttons.get(j).setIcon(null);
			}

			j++;
		}
	}

	public void updateupspellarea() {

		int j = 0;
		while (j < 5) {
			if (j < up.getField().getSpellArea().size()) {
				try {
					ospellsjbuttons.get(j).setIcon(
							new ImageIcon(
									(getClass().getResource("backon.png"))));
				} catch (NullPointerException rtr) {

				}

			} else {
				ospellsjbuttons.get(j).setIcon(null);
			}

			j++;
		}
	}

	public void updateall() throws MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException, IOException, CloneNotSupportedException {
		try {
			updatedownmonsterarea();
		} catch (NullPointerException wet) {

		}
		updatedownspellarea();
		updateLabels();
		updateupmonsterarea();
		updateupspellarea();
		updatedownhand();
		updateuphand();
	}

	public ImageIcon hatelsora(String name) {
		switch (name) {
		case "Card Destruction":
			return new ImageIcon(
					(getClass().getResource("Card Destruction.png")));
		case "Change Of Heart":
			return new ImageIcon(
					(getClass().getResource("Change Of Heart.png")));
		case "Dark Hole":
			return new ImageIcon((getClass().getResource("Dark Hole.png")));
		case "Harpie's Feather Duster":
			return new ImageIcon(
					(getClass().getResource("Harpie's Feather Duster.png")));
		case "Graceful Dice":
			return new ImageIcon((getClass().getResource("Graceful Dice.png")));
		case "Heavy Storm":
			return new ImageIcon((getClass().getResource("Heavy Storm.png")));
		case "Mage Power":
			return new ImageIcon((getClass().getResource("Mage Power.png")));
		case "Pot of Greed":
			return new ImageIcon((getClass().getResource("Pot of Greed.png")));
		case "Raigeki":
			return new ImageIcon((getClass().getResource("Raigeki.png")));

		case "Monster Reborn":
			return new ImageIcon((getClass().getResource("Monster Reborn.png")));
		case "Blue-Eyes White Dragon":
			return new ImageIcon(
					(getClass().getResource("Blue-Eyes White Dragon.png")));
		case "Cosmo Queen":
			return new ImageIcon((getClass().getResource("Cosmo Queen.png")));
		case "Dark Magician":
			return new ImageIcon((getClass().getResource("Dark Magician.png")));

		case "Fence Guard":
			return new ImageIcon((getClass().getResource("Fence Guard.png")));

		case "Red-Eyes Black Dragon":
			return new ImageIcon(
					(getClass().getResource("Red-Eyes Black Dragon.png")));

		case "Gaia The Fierce Knight":
			return new ImageIcon(
					(getClass().getResource("Gaia The Fierce Knight.png")));

		case "Summoned Skull":
			return new ImageIcon((getClass().getResource("Summoned Skull.png")));

		case "Fence Guard Magician":
			return new ImageIcon(
					(getClass().getResource("Fence Guard Magician.png")));

		case "Dark Magician Girl":
			return new ImageIcon(
					(getClass().getResource("Dark Magician Girl.png")));

		case "Curse Of Dragon":
			return new ImageIcon(
					(getClass().getResource("Curse Of Dragon.png")));

		case "Fence Guard Dragon":
			return new ImageIcon(
					(getClass().getResource("Fence Guard Dragon.png")));

		case "Alexandrite Dragon":
			return new ImageIcon(
					(getClass().getResource("Alexandrite Dragon.png")));

		case "Vorse Raider":
			return new ImageIcon((getClass().getResource("Vorse Raider.png")));

		case "Gemini Elf":
			return new ImageIcon((getClass().getResource("Gemini Elf.png")));

		case "Fence Guard Apprentice":
			return new ImageIcon(
					(getClass().getResource("Fence Guard Apprentice.png")));

		case "Beta The Magnet Warrior":
			return new ImageIcon(
					(getClass().getResource("Beta The Magnet Warrior.png")));

		case "Alligator Sword":
			return new ImageIcon(
					(getClass().getResource("Alligator Sword.png")));

		case "Gamma The Magnet Warrior":
			return new ImageIcon(
					(getClass().getResource("Gamma The Magnet Warrior.png")));

		case "Celtic Guardian":
			return new ImageIcon(
					(getClass().getResource("Celtic Guardian.png")));

		case "Alpha The Magnet Warrior":
			return new ImageIcon(
					(getClass().getResource("Alpha The Magnet Warrior.png")));

		case "Harpie Lady":
			return new ImageIcon((getClass().getResource("Harpie Lady.png")));

		case "Big Shield Gardna":
			return new ImageIcon(
					(getClass().getResource("Big Shield Gardna.png")));

		case "Witty Phantom":
			return new ImageIcon((getClass().getResource("Witty Phantom.png")));

		case "Baby Dragon":
			return new ImageIcon((getClass().getResource("Baby Dragon.png")));

		case "Cyber Jar":
			return new ImageIcon((getClass().getResource("Cyber Jar.png")));

		case "Clown Zombie":
			return new ImageIcon((getClass().getResource("Clown Zombie.png")));

		case "Time Wizard":
			return new ImageIcon((getClass().getResource("Time Wizard.png")));

		case "Man-Eater Bug":
			return new ImageIcon((getClass().getResource("Man-Eater Bug.png")));

		case "Kuriboh":
			return new ImageIcon((getClass().getResource("Kuriboh.png")));

		case "Mokey Mokey":
			return new ImageIcon((getClass().getResource("Mokey Mokey.png")));
		case "bakon":
			return new ImageIcon((getClass().getResource("bakon.png")));

		}
		return null;
	}

	public ImageIcon hatelsora2(String name) {
		switch (name) {
		case "Card Destruction":
			return new ImageIcon(
					(getClass().getResource("Card Destruction2.png")));
		case "Change Of Heart":
			return new ImageIcon(
					(getClass().getResource("Change Of Heart2.png")));
		case "Dark Hole":
			return new ImageIcon((getClass().getResource("Dark Hole2.png")));
		case "Harpie's Feather Duster":
			return new ImageIcon(
					(getClass().getResource("Harpie's Feather Duster2.png")));
		case "Graceful Dice":
			return new ImageIcon((getClass().getResource("Graceful Dice2.png")));
		case "Heavy Storm":
			return new ImageIcon((getClass().getResource("Heavy Storm2.png")));
		case "Mage Power":
			return new ImageIcon((getClass().getResource("Mage Power2.png")));
		case "Pot of Greed":
			return new ImageIcon((getClass().getResource("Pot of Greed2.png")));
		case "Raigeki":
			return new ImageIcon((getClass().getResource("Raigeki2.png")));

		case "Monster Reborn":
			return new ImageIcon(
					(getClass().getResource("Monster Reborn2.png")));
		case "Blue-Eyes White Dragon":
			return new ImageIcon(
					(getClass().getResource("Blue-Eyes White Dragon2.png")));
		case "Cosmo Queen":
			return new ImageIcon((getClass().getResource("Cosmo Queen2.png")));
		case "Dark Magician":
			return new ImageIcon((getClass().getResource("Dark Magician2.png")));

		case "Fence Guard":
			return new ImageIcon((getClass().getResource("Fence Guard2.png")));

		case "Red-Eyes Black Dragon":
			return new ImageIcon(
					(getClass().getResource("Red-Eyes Black Dragon2.png")));

		case "Gaia The Fierce Knight":
			return new ImageIcon(
					(getClass().getResource("Gaia The Fierce Knight2.png")));

		case "Summoned Skull":
			return new ImageIcon(
					(getClass().getResource("Summoned Skull2.png")));

		case "Fence Guard Magician":
			return new ImageIcon(
					(getClass().getResource("Fence Guard Magician2.png")));

		case "Dark Magician Girl":
			return new ImageIcon(
					(getClass().getResource("Dark Magician Girl2.png")));

		case "Curse Of Dragon":
			return new ImageIcon(
					(getClass().getResource("Curse Of Dragon2.png")));

		case "Fence Guard Dragon":
			return new ImageIcon(
					(getClass().getResource("Fence Guard Dragon2.png")));

		case "Alexandrite Dragon":
			return new ImageIcon(
					(getClass().getResource("Alexandrite Dragon2.png")));

		case "Vorse Raider":
			return new ImageIcon((getClass().getResource("Vorse Raider2.png")));

		case "Gemini Elf":
			return new ImageIcon((getClass().getResource("Gemini Elf2.png")));

		case "Fence Guard Apprentice":
			return new ImageIcon(
					(getClass().getResource("Fence Guard Apprentice2.png")));

		case "Beta The Magnet Warrior":
			return new ImageIcon(
					(getClass().getResource("Beta The Magnet Warrior2.png")));

		case "Alligator Sword":
			return new ImageIcon(
					(getClass().getResource("Alligator Sword2.png")));

		case "Gamma The Magnet Warrior":
			return new ImageIcon(
					(getClass().getResource("Gamma The Magnet Warrior2.png")));

		case "Celtic Guardian":
			return new ImageIcon(
					(getClass().getResource("Celtic Guardian2.png")));

		case "Alpha The Magnet Warrior":
			return new ImageIcon(
					(getClass().getResource("Alpha The Magnet Warrior2.png")));

		case "Harpie Lady":
			return new ImageIcon((getClass().getResource("Harpie Lady2.png")));

		case "Big Shield Gardna":
			return new ImageIcon(
					(getClass().getResource("Big Shield Gardna2.png")));

		case "Witty Phantom":
			return new ImageIcon((getClass().getResource("Witty Phantom2.png")));

		case "Baby Dragon":
			return new ImageIcon((getClass().getResource("Baby Dragon2.png")));

		case "Cyber Jar":
			return new ImageIcon((getClass().getResource("Cyber Jar2.png")));

		case "Clown Zombie":
			return new ImageIcon((getClass().getResource("Clown Zombie2.png")));

		case "Time Wizard":
			return new ImageIcon((getClass().getResource("Time Wizard2.png")));

		case "Man-Eater Bug":
			return new ImageIcon((getClass().getResource("Man-Eater Bug2.png")));

		case "Kuriboh":
			return new ImageIcon((getClass().getResource("Kuriboh2.png")));

		case "Mokey Mokey":
			return new ImageIcon((getClass().getResource("Mokey Mokey2.png")));
		case "bakon":
			return new ImageIcon((getClass().getResource("bakon2.png")));

		}
		return null;
	}
}
