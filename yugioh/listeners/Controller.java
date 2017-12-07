package eg.edu.guc.yugioh.listeners;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.smartcardio.Card;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sun.prism.Image;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.CardButton;
import eg.edu.guc.yugioh.gui.MonsterButton;
import eg.edu.guc.yugioh.gui.SpellButton;
import eg.edu.guc.yugioh.gui.gui;

public class Controller implements ActionListener, MouseListener {

	public gui g;
	int hg = 0;
	int k;
	JButton z;
	JButton firstsactmp = null;
	ArrayList<MonsterCard> sac = new ArrayList<>();
	ImageIcon back = new ImageIcon((getClass().getResource("back.png")));

	public MonsterCard monstertoadd;
	public SpellCard coh;
	public SpellCard oppcoh;

	public JButton jbmonstertoadd;
	public boolean onesacrifice;
	public boolean twosacrifice;
	public int sacrificecount = 0;
	boolean selectoppmons = false;
	MonsterCard attackingmons = null;
	public boolean spellonmons;
	public boolean oppspellonmons;

	public boolean spellonmons2 = false;
	public boolean oppspellonmons2 = false;

	public SpellCard mp;
	public SpellCard oppmp;

	public Controller(String p1, String p2) throws MissingFieldException,
			EmptyFieldException, UnknownCardTypeException,
			UnknownSpellCardException, IOException, CloneNotSupportedException {
		g = new gui(p1, p2);
		int s = 0;
		while (s < g.monstersjbuttons.size()) {
			g.monstersjbuttons.get(s).addActionListener(this);
			g.monstersjbuttons.get(s).addMouseListener(this);

			s++;
		}
		g.S1.addActionListener(this);
		g.S1.addMouseListener(this);
		System.out.println(g.S1.getSize());

		g.S2.addActionListener(this);
		g.S2.addMouseListener(this);

		g.S3.addActionListener(this);
		g.S3.addMouseListener(this);

		g.S4.addActionListener(this);
		g.S4.addMouseListener(this);

		g.S5.addActionListener(this);
		g.S5.addMouseListener(this);

		//
		g.oS1.addActionListener(this);
		g.oS1.addMouseListener(this);

		g.oS2.addActionListener(this);
		g.oS2.addMouseListener(this);

		g.oS3.addActionListener(this);
		g.oS3.addMouseListener(this);

		g.oS4.addActionListener(this);
		g.oS4.addMouseListener(this);

		g.oS5.addActionListener(this);
		g.oS5.addMouseListener(this);

		g.endturn.addActionListener(this);
		g.endphase.addActionListener(this);
		g.oM1.addActionListener(this);
		g.oM1.addMouseListener(this);

		g.oM2.addActionListener(this);
		g.oM2.addMouseListener(this);

		g.oM3.addActionListener(this);
		g.oM3.addMouseListener(this);

		g.oM4.addActionListener(this);
		g.oM4.addMouseListener(this);

		g.oM5.addActionListener(this);
		g.oM5.addMouseListener(this);

		int i = 0;
		while (i < g.handJButtons.size()) {
			g.handJButtons.get(i).addActionListener(this);
			g.handJButtons.get(i).addMouseListener(this);

			i++;
		}
		i = 0;
		while (i < g.ohandJButtons.size()) {
			g.ohandJButtons.get(i).addActionListener(this);
			g.ohandJButtons.get(i).addMouseListener(this);

			i++;
		}
	}

	public void actionPerformed(ActionEvent e) {

		ArrayList<SpellCard> spellareaa = g.c.getActivePlayer().getField()
				.getSpellArea();
		try {
			if (((e.getSource() == g.S1 && spellareaa.get(0) != null)
					|| (e.getSource() == g.S2 && spellareaa.get(1) != null)
					|| (e.getSource() == g.S3 && spellareaa.get(2) != null)
					|| (e.getSource() == g.S4 && spellareaa.get(3) != null) || (e
					.getSource() == g.S5 && spellareaa.get(4) != null))
					&& g.c.getActivePlayer() == g.down) {
				System.out.println(g.c.getActivePlayer().getName()
						+ "da el active" + g.down + "da el down");
				System.out.println(g.c.getOpponentPlayer().getName()
						+ "da el opp" + g.up + "da el up");

				String[] m = { "yes", "no" };
				Object selected = JOptionPane.showInputDialog(null,
						"Do you want to activate this spell ?", "Input",
						JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
				String chos = (String) selected;
				SpellCard scc = g.down.getField().getSpellArea()
						.get(g.spellsjbuttons.indexOf(e.getSource()));
				try{
					
				
				if (chos.equals("yes")) {

					if (scc instanceof MagePower
							|| scc instanceof ChangeOfHeart) {
						if (scc instanceof ChangeOfHeart) {
							String[] tot = { "ok", "no" };
							Object objto = JOptionPane
									.showInputDialog(
											null,
											"Choose Opponent Monster to add To your Field ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, tot, tot[1]);
							String chosen = (String) objto;
							if (chosen.equals("ok")) {
								spellonmons = true;
								coh = scc;

							}
						}
						if (scc instanceof MagePower) {
							if (g.down.getField().getMonstersArea().size() == 0) {
								String[] tyt = { "ok" };
								Object resy = JOptionPane.showInputDialog(null,
										"No Monsters Available ", "Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										tyt, tyt[0]);
							} else {
								String[] tot = { "ok", "no" };
								Object objto = JOptionPane
										.showInputDialog(
												null,
												"Choose a Monster to activate this spell on ",
												"Input",
												JOptionPane.INFORMATION_MESSAGE,
												null, tot, tot[1]);
								String chosen = (String) objto;
								if (chosen.equals("ok")) {
									spellonmons2 = true;
									mp = scc;
								}
							}
						}

					} else {
						try {
							g.down.activateSpell(scc, null);
						} catch (WrongPhaseException wrr) {

						}
						g.updateall();
						int i = 0;
						while (i < g.handJButtons.size()) {
							g.handJButtons.get(i).addActionListener(this);
							g.handJButtons.get(i).addMouseListener(this);

							i++;
						}
						i = 0;
						while (i < g.ohandJButtons.size()) {
							g.ohandJButtons.get(i).addActionListener(this);
							g.ohandJButtons.get(i).addMouseListener(this);

							i++;
						}

					}
				}
				}
				catch(NullPointerException erg ) {
					
				}

			}
		} catch (IndexOutOfBoundsException toto) {

		} catch (MissingFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownCardTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownSpellCardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		spellareaa = g.up.getField().getSpellArea();
		try {
			if (((e.getSource() == g.oS1 && spellareaa.get(0) != null)
					|| (e.getSource() == g.oS2 && spellareaa.get(1) != null)
					|| (e.getSource() == g.oS3 && spellareaa.get(2) != null)
					|| (e.getSource() == g.oS4 && spellareaa.get(3) != null) || (e
					.getSource() == g.oS5 && spellareaa.get(4) != null))
					&& g.c.getActivePlayer() == g.up) {

				String[] m = { "yes", "no" };
				Object selected = JOptionPane.showInputDialog(null,
						"Do you want to activate this spell ?", "Input",
						JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
				String chos = (String) selected;
				SpellCard scc = g.up.getField().getSpellArea()
						.get(g.ospellsjbuttons.indexOf(e.getSource()));
				if (chos.equals("yes")) {

					if (scc instanceof MagePower
							|| scc instanceof ChangeOfHeart) {
						if (scc instanceof ChangeOfHeart) {
							String[] tot = { "ok", "no" };
							Object objto = JOptionPane
									.showInputDialog(
											null,
											"Choose Opponent Monster to add To your Field ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, tot, tot[1]);
							String chosen = (String) objto;
							if (chosen.equals("ok")) {
								oppspellonmons = true;
								oppcoh = scc;

							}
						}
						if (scc instanceof MagePower) {
							if (g.up.getField().getMonstersArea().size() == 0) {
								String[] tyt = { "ok" };
								Object resy = JOptionPane.showInputDialog(null,
										"No Monsters Available ", "Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										tyt, tyt[0]);
							} else {
								String[] tot = { "ok", "no" };
								Object objto = JOptionPane
										.showInputDialog(
												null,
												"Choose a Monster to activate this spell on ",
												"Input",
												JOptionPane.INFORMATION_MESSAGE,
												null, tot, tot[1]);
								String chosen = (String) objto;
								if (chosen.equals("ok")) {
									oppspellonmons2 = true;
									oppmp = scc;
								}
							}
						}

					} else {
						g.up.activateSpell(scc, null);
						g.updateall();
						int i = 0;
						while (i < g.handJButtons.size()) {
							g.handJButtons.get(i).addActionListener(this);
							g.handJButtons.get(i).addMouseListener(this);

							i++;
						}
						i = 0;
						while (i < g.ohandJButtons.size()) {
							g.ohandJButtons.get(i).addActionListener(this);
							g.ohandJButtons.get(i).addMouseListener(this);

							i++;
						}

					}
				}

			}
		} catch (IndexOutOfBoundsException r) {

		} catch (MissingFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownCardTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownSpellCardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (e.getSource() == g.endturn)
			try {
				hg++;

				g.c.getActivePlayer().endTurn();
				g.updateuphand();
				g.updatedownhand();
				int i = 0;
				while (i < g.handJButtons.size()) {
					g.handJButtons.get(i).addActionListener(this);
					g.handJButtons.get(i).addMouseListener(this);

					i++;
				}
				i = 0;
				while (i < g.ohandJButtons.size()) {
					g.ohandJButtons.get(i).addActionListener(this);
					g.ohandJButtons.get(i).addMouseListener(this);

					i++;
				}
				i = 0;
				while (i < g.up.getField().getMonstersArea().size()) {
					g.up.getField().getMonstersArea().get(i).modeb4 = false;
					i++;
				}
				i = 0;
				while (i < g.down.getField().getMonstersArea().size()) {
					g.down.getField().getMonstersArea().get(i).modeb4 = false;
					i++;
				}
				g.activp.setText(g.c.getActivePlayer().getName() + "");

				g.updateLabels();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MissingFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EmptyFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownCardTypeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownSpellCardException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		if (e.getSource() == g.endphase)
			try {

				g.c.getActivePlayer().endPhase();
				g.phasee.setText(g.c.getActivePlayer().getField().getPhase()
						+ "");
				g.updateLabels();
				g.updateuphand();
				g.updatedownhand();
				int i = 0;
				while (i < g.handJButtons.size()) {
					g.handJButtons.get(i).addActionListener(this);
					g.handJButtons.get(i).addMouseListener(this);

					i++;
				}
				i = 0;
				while (i < g.ohandJButtons.size()) {
					g.ohandJButtons.get(i).addActionListener(this);
					g.ohandJButtons.get(i).addMouseListener(this);

					i++;
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MissingFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EmptyFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownCardTypeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownSpellCardException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		String action;
		if (g.handJButtons.contains(e.getSource())
				&& g.c.getActivePlayer() == g.down) {
			k = g.handJButtons.indexOf(e.getSource());
			z = g.handJButtons.get(k);
			if (g.c.getActivePlayer().getField().getHand().get(k) instanceof MonsterCard) {
				MonsterCard mcc = (MonsterCard) g.c.getActivePlayer()
						.getField().getHand().get(k);
				String[] m = { "summon", "set" };
				Object selected = JOptionPane.showInputDialog(null,
						"What do you want to perform?", "Input",
						JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
				action = (String) selected;
				try {
					if (action.equals("summon")) {
						if (mcc.getLevel() < 5
								&& g.c.getActivePlayer().summonMonster(mcc)) {
							g.updatedownhand();
							g.monstersjbuttons.get(
									g.down.getField().getMonstersArea()
											.indexOf(mcc)).setIcon(
									g.hatelsora(mcc.getName()));

							// g.hand.remove(z);
							// g.handJButtons.remove(k);

							int i = 0;
							while (i < g.handJButtons.size()) {
								g.handJButtons.get(i).addActionListener(this);
								g.handJButtons.get(i).addMouseListener(this);

								i++;
							}
							i = 0;
							while (i < g.ohandJButtons.size()) {
								g.ohandJButtons.get(i).addActionListener(this);
								g.ohandJButtons.get(i).addMouseListener(this);

								i++;
							}

						} else if (mcc.getLevel() == 5 || mcc.getLevel() == 6) {

							if (g.down.getField().getMonstersArea().size() == 0) {
								JOptionPane.showMessageDialog(null,
										"No enough Sacrifices");
							} else {
								String[] oo = { "Ok", "No" };
								Object obj = JOptionPane.showInputDialog(null,
										"Select 1 monster to Sacrifice ",
										"Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										oo, oo[1]);
								action = (String) obj;
								if (obj.equals("Ok")) {
									monstertoadd = mcc;
									onesacrifice = true;
									jbmonstertoadd = z;

								}
							}

						} else if (mcc.getLevel() == 7 || mcc.getLevel() == 8) {
							if (g.down.getField().getMonstersArea().size() < 2) {
								JOptionPane.showMessageDialog(null,
										"No enough Sacrifices");
							} else {
								String[] oo = { "Ok", "No" };
								Object obj = JOptionPane.showInputDialog(null,
										"Select 2 monster to Sacrifice ",
										"Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										oo, oo[1]);
								action = (String) obj;
								if (obj.equals("Ok")) {
									monstertoadd = mcc;
									twosacrifice = true;
									jbmonstertoadd = z;

								}
							}
						}
					}
				} catch (NullPointerException hoba) {

				}
				try {
					if (action.equals("set")) {
						if (mcc.getLevel() < 5
								&& g.c.getActivePlayer().setMonster(mcc)) {

							g.monstersjbuttons.get(
									g.down.getField().getMonstersArea()
											.indexOf(mcc)).setIcon(
									g.hatelsora("bakon"));

							g.hand.remove(z);
							g.handJButtons.remove(k);

						} else if (mcc.getLevel() == 5 || mcc.getLevel() == 6) {
							if (g.down.getField().getMonstersArea().size() == 0) {
								JOptionPane.showMessageDialog(null,
										"No Enough Sacrifices");

							} else {
								String[] oo = { "Ok", "No" };
								Object obj = JOptionPane.showInputDialog(null,
										"Select 1 monster to Sacrifice ",
										"Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										oo, oo[1]);
								action = (String) obj;
								if (obj.equals("Ok")) {
									monstertoadd = mcc;
									onesacrifice = true;
									jbmonstertoadd = z;

								}
							}

						} else if (mcc.getLevel() == 7 || mcc.getLevel() == 8) {
							if (g.down.getField().getMonstersArea().size() < 2) {
								JOptionPane.showMessageDialog(null,
										"No Enough Sacrifices");
							} else {
								String[] oo = { "Ok", "No" };
								Object obj = JOptionPane.showInputDialog(null,
										"Select 2 monster to Sacrifice ",
										"Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										oo, oo[1]);
								action = (String) obj;
								if (obj.equals("Ok")) {
									monstertoadd = mcc;
									twosacrifice = true;
									jbmonstertoadd = z;

								}
							}
						}
					}
				} catch (NullPointerException ndj) {

				}

			} else if (g.c.getActivePlayer().getField().getHand().get(k) instanceof SpellCard) {
				SpellCard scc = (SpellCard) g.c.getActivePlayer().getField()
						.getHand().get(k);
				String[] m = { "activate", "set" };
				Object selected = JOptionPane.showInputDialog(null,
						"What do you want to perform?", "Input",
						JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
				action = (String) selected;
				if (action.equals("activate")) {
					if (g.down.getField().getHand().get(k) instanceof MagePower
							|| g.c.getActivePlayer().getField().getHand()
									.get(k) instanceof ChangeOfHeart) {
						if (scc instanceof ChangeOfHeart) {
							String[] tot = { "ok", "no" };
							Object objto = JOptionPane
									.showInputDialog(
											null,
											"Choose Opponent Monster to add To your Field ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, tot, tot[1]);
							String chosen = (String) objto;
							if (chosen.equals("ok")) {
								spellonmons = true;
								coh = scc;

							}
						}
						if (scc instanceof MagePower) {
							if (g.down.getField().getMonstersArea().size() == 0) {
								String[] tyt = { "ok" };
								Object resy = JOptionPane.showInputDialog(null,
										"No Monsters Available ", "Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										tyt, tyt[0]);
							} else {
								String[] tot = { "ok", "no" };
								Object objto = JOptionPane
										.showInputDialog(
												null,
												"Choose a Monster to activate this spell on ",
												"Input",
												JOptionPane.INFORMATION_MESSAGE,
												null, tot, tot[1]);
								String chosen = (String) objto;
								if (chosen.equals("ok")) {
									oppspellonmons2 = true;
									oppmp = scc;
								}
							}
						}

					} else {
						g.down.activateSpell(scc, null);
						try {
							g.updateall();
						} catch (MissingFieldException | EmptyFieldException
								| UnknownCardTypeException
								| UnknownSpellCardException | IOException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int i = 0;
						while (i < g.handJButtons.size()) {
							g.handJButtons.get(i).addActionListener(this);
							g.handJButtons.get(i).addMouseListener(this);

							i++;
						}
						i = 0;
						while (i < g.ohandJButtons.size()) {
							g.ohandJButtons.get(i).addActionListener(this);
							g.ohandJButtons.get(i).addMouseListener(this);

							i++;
						}

					}

				} else if (action.equals("set")) {
					g.c.getActivePlayer().setSpell(scc);
					if (g.c.getActivePlayer().getField().getSpellArea()
							.isEmpty()) {
						g.spellsjbuttons.get(5).setIcon(g.hatelsora("bakon"));
					} else {
						int u = 0;
						while (u < g.c.getActivePlayer().getField()
								.getSpellArea().size()) {
							u++;
						}
						if (u <= 5) {

							g.spellsjbuttons.get(u - 1).setIcon(
									g.hatelsora("bakon"));

						}
						g.hand.remove(z);
						g.handJButtons.remove(z);

					}
				}

			}

		}
		k = 0;
		z = null;
		try {
			if (g.ohandJButtons.contains(e.getSource())
					&& g.c.getActivePlayer() == g.up) {
				k = g.ohandJButtons.indexOf(e.getSource());
				z = g.ohandJButtons.get(k);
				if (g.c.getActivePlayer().getField().getHand().get(k) instanceof MonsterCard) {
					MonsterCard mcc = (MonsterCard) g.c.getActivePlayer()
							.getField().getHand().get(k);
					String[] m = { "summon", "set" };
					Object selected = JOptionPane.showInputDialog(null,
							"What do you want to perform?", "Input",
							JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
					action = (String) selected;
					try {
						if (action.equals("summon")) {
							if (mcc.getLevel() < 5
									&& g.c.getActivePlayer().summonMonster(mcc)) {

								g.omonstersjbuttons.get(
										g.up.getField().getMonstersArea()
												.indexOf(mcc)).setIcon(
										g.hatelsora(mcc.getName()));

								g.ohand.remove(z);
								g.ohandJButtons.remove(k);

							}

							else if (mcc.getLevel() == 5 || mcc.getLevel() == 6) {
								if (g.up.getField().getMonstersArea().size() == 0) {
									JOptionPane.showMessageDialog(null,
											"No Enough Sacrifices");

								} else {
									String[] oo = { "Ok", "No" };
									Object obj = JOptionPane.showInputDialog(
											null,
											"Select 1 monster to Sacrifice ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, oo, oo[1]);
									action = (String) obj;
									if (obj.equals("Ok")) {
										monstertoadd = mcc;
										onesacrifice = true;
										jbmonstertoadd = z;

									}
								}

							} else if (mcc.getLevel() == 7
									|| mcc.getLevel() == 8) {
								if (g.up.getField().getMonstersArea().size() < 2) {
									JOptionPane.showMessageDialog(null,
											"No Enough Sacrifices");
								} else {
									String[] oo = { "Ok", "No" };
									Object obj = JOptionPane.showInputDialog(
											null,
											"Select 2 monster to Sacrifice ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, oo, oo[1]);
									action = (String) obj;
									if (obj.equals("Ok")) {
										monstertoadd = mcc;
										twosacrifice = true;
										jbmonstertoadd = z;

									}
								}
							}
						}
					} catch (MultipleMonsterAdditionException mono) {

					}
					try {
						if (action.equals("set")) {

							if (mcc.getLevel() < 5
									&& g.c.getActivePlayer().setMonster(mcc)) {
								g.omonstersjbuttons.get(
										g.up.getField().getMonstersArea()
												.indexOf(mcc)).setIcon(
										g.hatelsora("bakon"));

								g.ohand.remove(z);
								g.ohandJButtons.remove(k);

							} else if (mcc.getLevel() == 5
									|| mcc.getLevel() == 6) {
								if (g.up.getField().getMonstersArea().size() == 0) {
									JOptionPane.showMessageDialog(null,
											"No Enough Sacrifices");

								} else {
									String[] oo = { "Ok", "No" };
									Object obj = JOptionPane.showInputDialog(
											null,
											"Select 1 monster to Sacrifice ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, oo, oo[1]);
									action = (String) obj;
									if (obj.equals("Ok")) {
										monstertoadd = mcc;
										onesacrifice = true;
										jbmonstertoadd = z;

									}
								}

							} else if (mcc.getLevel() == 7
									|| mcc.getLevel() == 8) {
								if (g.up.getField().getMonstersArea().size() < 2) {
									JOptionPane.showMessageDialog(null,
											"No Enough Sacrifices");
								} else {
									String[] oo = { "Ok", "No" };
									Object obj = JOptionPane.showInputDialog(
											null,
											"Select 2 monster to Sacrifice ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, oo, oo[1]);
									action = (String) obj;
									if (obj.equals("Ok")) {
										monstertoadd = mcc;
										twosacrifice = true;
										jbmonstertoadd = z;

									}
								}
							}

						}
					} catch (MultipleMonsterAdditionException dsd) {

					}

				} else if (g.c.getActivePlayer().getField().getHand().get(k) instanceof SpellCard) {
					SpellCard scc = (SpellCard) g.c.getActivePlayer()
							.getField().getHand().get(k);
					String[] m = { "activate", "set" };
					Object selected = JOptionPane.showInputDialog(null,
							"What do you want to perform?", "Input",
							JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
					action = (String) selected;
					if (action.equals("activate")) {
						if (g.c.getActivePlayer().getField().getHand().get(k) instanceof MagePower
								|| g.c.getActivePlayer().getField().getHand()
										.get(k) instanceof ChangeOfHeart) {
							if (scc instanceof ChangeOfHeart) {
								String[] tot = { "ok", "no" };
								Object objto = JOptionPane
										.showInputDialog(
												null,
												"Choose Opponent Monster to add To your Field ",
												"Input",
												JOptionPane.INFORMATION_MESSAGE,
												null, tot, tot[1]);
								String chosen = (String) objto;
								if (chosen.equals("ok")) {
									oppspellonmons = true;
									oppcoh = scc;

								}
							}
							if (scc instanceof MagePower) {
								if (g.up.getField().getMonstersArea().size() == 0) {
									String[] tyt = { "ok" };
									Object resy = JOptionPane.showInputDialog(
											null, "No Monsters Available ",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, tyt, tyt[0]);
								} else {
									String[] tot = { "ok", "no" };
									Object objto = JOptionPane
											.showInputDialog(
													null,
													"Choose a Monster to activate this spell on ",
													"Input",
													JOptionPane.INFORMATION_MESSAGE,
													null, tot, tot[1]);
									String chosen = (String) objto;
									if (chosen.equals("ok")) {
										oppspellonmons2 = true;
										oppmp = scc;
									}
								}
							}

						} else {
							g.c.getActivePlayer().activateSpell(scc, null);
							g.updateall();
							int i = 0;
							while (i < g.handJButtons.size()) {
								g.handJButtons.get(i).addActionListener(this);
								g.handJButtons.get(i).addMouseListener(this);

								i++;
							}
							i = 0;
							while (i < g.ohandJButtons.size()) {
								g.ohandJButtons.get(i).addActionListener(this);
								g.ohandJButtons.get(i).addMouseListener(this);

								i++;
							}

						}

					} else if (action.equals("set")) {
						g.c.getActivePlayer().setSpell(scc);
						if (g.c.getActivePlayer().getField().getSpellArea()
								.isEmpty()) {
							g.ospellsjbuttons.get(0).setIcon(
									(g.hatelsora("bakon")));
						} else {
							int u = 0;
							while (u < g.c.getActivePlayer().getField()
									.getSpellArea().size()) {
								u++;
							}
							if (u <= 5) {

								g.ospellsjbuttons.get(u - 1).setIcon(
										g.hatelsora("bakon"));

							}
							g.ohand.remove(z);
							g.ohandJButtons.remove(z);

						}
					}

				}
			}
		} catch (NullPointerException poui) {

		} catch (MissingFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownCardTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownSpellCardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<MonsterCard> monsterareaa = g.down.getField()

		.getMonstersArea();
		try{
		if (((e.getSource() == g.M1 && monsterareaa.get(0) != null)
				|| (e.getSource() == g.M2 && monsterareaa.get(1) != null)
				|| (e.getSource() == g.M3 && monsterareaa.get(2) != null)
				|| (e.getSource() == g.M4 && monsterareaa.get(3) != null) || (e
				.getSource() == g.M5 && monsterareaa.get(4) != null))) {
			if (g.c.getActivePlayer() == g.down) {

				if (g.down.getField().getPhase() != Phase.BATTLE
						&& onesacrifice == false && twosacrifice == false
						&& spellonmons2 == false) {

					if (g.down.getField().getMonstersArea()
							.get(g.monstersjbuttons.indexOf(e.getSource())).modeb4 == true) {
						String[] m = { "ok" };
						try {
							Object selected = JOptionPane.showInputDialog(null,
									"Cannot change Mode Twice", "Input",
									JOptionPane.INFORMATION_MESSAGE, null, m,
									m[0]);
						} catch (NullPointerException zoza) {

						}
					}

					else {
						String[] zz = { "yes", "no" };
						Object sel = JOptionPane.showInputDialog(null,
								"Do you want to change Mode of This Monster ?",
								"Input", JOptionPane.INFORMATION_MESSAGE, null,
								zz, zz[1]);
						String st = (String) sel;
						int indexx;
						try {
							if (st.equals("yes")) {
								indexx = g.monstersjbuttons.indexOf(e
										.getSource());
								g.down.switchMonsterMode(g.down.getField()
										.getMonstersArea().get(indexx));
								g.updateLabels();
								g.updatedownmonsterarea();

							}
						} catch (NullPointerException ere) {

						}
					}
				}

				else {
					if (onesacrifice == false && twosacrifice == false
							&& spellonmons2 == false) {
						System.out.println("et3mlet");
						String[] m = { "yes", "no" };
						Object selected = JOptionPane.showInputDialog(null,
								"Do you want to attack ?", "Input",
								JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
						String select = (String) selected;
						int index;
						if (select.equals("yes")
								&& g.up.getField().getMonstersArea().isEmpty()) {
							index = g.monstersjbuttons.indexOf(e.getSource());
							g.down.declareAttack(g.down.getField()
									.getMonstersArea().get(index));
							System.out.println(g.up.getLifePoints());
							g.updateLabels();
						} else if (select.equals("yes")
								&& !(g.up.getField().getMonstersArea()
										.isEmpty())) {
							String[] oo = { "Ok", "No" };
							Object obj = JOptionPane.showInputDialog(null,
									"Select 1 monster to attack ", "Input",
									JOptionPane.INFORMATION_MESSAGE, null, oo,
									oo[1]);
							action = (String) obj;
							if (action.equals("Ok")) {
								selectoppmons = true;
								attackingmons = g.down
										.getField()
										.getMonstersArea()
										.get(g.monstersjbuttons.indexOf(e
												.getSource()));
							}

						}
					} else if (onesacrifice == true && spellonmons2 == false) {
						onesacrifice = false;

						sac.add(g.down.getField().getMonstersArea()
								.get(g.monstersjbuttons.indexOf(e.getSource())));
						g.down.summonMonster(monstertoadd, sac);
						g.monstersjbuttons.get(
								g.monstersjbuttons.indexOf(e.getSource()))
								.setIcon(g.hatelsora(monstertoadd.getName()));
						g.updateLabels();
						// g.hand.remove(jbmonstertoadd);
						//
						// g.handJButtons.remove(g.monstersjbuttons
						// .indexOf(jbmonstertoadd));
						g.updatedownhand();
						int i = 0;
						while (i < g.handJButtons.size()) {
							g.handJButtons.get(i).addActionListener(this);
							g.handJButtons.get(i).addMouseListener(this);

							i++;
						}
						try {
							sac.remove(0);
						} catch (IndexOutOfBoundsException lre) {

						}

					} else if (twosacrifice == true && spellonmons2 == false) {

						sacrificecount++;
						if (firstsactmp != e.getSource())
							sac.add(g.down
									.getField()
									.getMonstersArea()
									.get(g.monstersjbuttons.indexOf(e
											.getSource())));
						if (sacrificecount == 1)
							firstsactmp = (JButton) e.getSource();
						System.out.println("1stdone");

						if (sacrificecount == 2
								&& g.down.summonMonster(monstertoadd, sac)) {
							System.out.println("2nd done");

							sacrificecount = 0;

							twosacrifice = false;

							// g.handJButtons.remove(g.handJButtons
							// .indexOf(jbmonstertoadd));
							// g.hand.remove(jbmonstertoadd);
							g.updatedownhand();
							int i = 0;
							while (i < g.handJButtons.size()) {
								g.handJButtons.get(i).addActionListener(this);
								g.handJButtons.get(i).addMouseListener(this);

								i++;
							}
							i = 0;

							g.updatedownmonsterarea();

							g.updateLabels();

						}
					} else if (spellonmons2 == true) {
						g.down.activateSpell(
								mp,
								g.down.getField()
										.getMonstersArea()
										.get(g.monstersjbuttons.indexOf(e
												.getSource())));
						// g.updatedownmonsterarea();
						g.updatedownspellarea();

					}
				}
			} else {
				System.out.println("sa7 ");
				if (selectoppmons == true && g.c.getActivePlayer() == g.up) {
					selectoppmons = false;
					String[] m = { "yes", "no" };
					Object selected = JOptionPane.showInputDialog(null,
							"Do you want to attack this monster ?", "Input",
							JOptionPane.INFORMATION_MESSAGE, null, m, m[1]);
					String act = (String) selected;
					if (act.equals("yes")) {
						g.up.declareAttack(
								attackingmons,
								g.down.getField()
										.getMonstersArea()
										.get(g.monstersjbuttons.indexOf(e
												.getSource())));
						g.updateupmonsterarea();
						g.updatedownmonsterarea();

						System.out.println("dee");
						g.updateLabels();
					}
				}
				if (oppspellonmons == true) {
					oppspellonmons = false;
					g.up.activateSpell(
							oppcoh,
							g.down.getField()
									.getMonstersArea()
									.get(g.monstersjbuttons.indexOf(e
											.getSource())));

					g.updateall();
					int i = 0;
					while (i < g.handJButtons.size()) {
						g.handJButtons.get(i).addActionListener(this);
						g.handJButtons.get(i).addMouseListener(this);

						i++;
					}
					i = 0;
					while (i < g.ohandJButtons.size()) {
						g.ohandJButtons.get(i).addActionListener(this);
						g.ohandJButtons.get(i).addMouseListener(this);

						i++;
					}
				}
			}
		}
		}
		catch(IndexOutOfBoundsException sf) {
			
		} catch (MissingFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownCardTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownSpellCardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		monsterareaa = g.up.getField().getMonstersArea();
		try {
			if ((e.getSource() == g.oM1 && monsterareaa.get(0) != null)
					|| (e.getSource() == g.oM2 && monsterareaa.get(1) != null)
					|| (e.getSource() == g.oM3 && monsterareaa.get(2) != null)
					|| (e.getSource() == g.oM4 && monsterareaa.get(3) != null)
					|| (e.getSource() == g.oM5 && monsterareaa.get(4) != null)) {
				if (g.c.getActivePlayer() == g.up) {

					if (g.up.getField().getPhase() != Phase.BATTLE
							&& onesacrifice == false && twosacrifice == false
							&& oppspellonmons2 == false) {
						if (g.up.getField()
								.getMonstersArea()
								.get(g.omonstersjbuttons.indexOf(e.getSource())).modeb4 == true) {
							String[] m = { "ok" };
							Object selected = JOptionPane.showInputDialog(null,
									"Cannot Change Phase Twice", "Input",
									JOptionPane.INFORMATION_MESSAGE, null, m,
									m[0]);
						} else {
							String[] zz = { "yes", "no" };
							Object sel = JOptionPane
									.showInputDialog(
											null,
											"Do you want to change Mode of This Monster ?",
											"Input",
											JOptionPane.INFORMATION_MESSAGE,
											null, zz, zz[1]);
							String st = (String) sel;
							int indexx;
							if (st.equals("yes")) {
								indexx = g.omonstersjbuttons.indexOf(e
										.getSource());
								g.up.switchMonsterMode(g.up.getField()
										.getMonstersArea().get(indexx));

								g.updateLabels();
								g.updateupmonsterarea();
								int i = 0;
								while (i < g.ohandJButtons.size()) {
									g.ohandJButtons.get(i).addActionListener(
											this);
									g.ohandJButtons.get(i).addMouseListener(
											this);

									i++;
								}
							}
						}
					} else {
						if (onesacrifice == false && twosacrifice == false) {
							System.out.println("et3mlet");
							String[] m = { "yes", "no" };
							Object selected = JOptionPane.showInputDialog(null,
									"Do you want to attack ?", "Input",
									JOptionPane.INFORMATION_MESSAGE, null, m,
									m[1]);
							String select = (String) selected;
							int index;

							if (select.equals("yes")
									&& g.down.getField().getMonstersArea()
											.isEmpty()) {
								index = g.omonstersjbuttons.indexOf(e
										.getSource());
								try {
									g.up.declareAttack(g.up.getField()
											.getMonstersArea().get(index));
								} catch (DefenseMonsterAttackException dsd) {

								}
								System.out.println(g.down.getLifePoints());
								g.updateLabels();
							} else if (select.equals("yes")
									&& !(g.down.getField().getMonstersArea()
											.isEmpty())) {
								String[] oo = { "Ok", "No" };
								Object obj = JOptionPane.showInputDialog(null,
										"Select 1 monster to attack ", "Input",
										JOptionPane.INFORMATION_MESSAGE, null,
										oo, oo[1]);
								action = (String) obj;
								if (action.equals("Ok")) {
									selectoppmons = true;
									attackingmons = g.up
											.getField()
											.getMonstersArea()
											.get(g.omonstersjbuttons.indexOf(e
													.getSource()));
								}

							}
						} else if (onesacrifice == true) {
							onesacrifice = false;

							sac.add(g.up
									.getField()
									.getMonstersArea()
									.get(g.omonstersjbuttons.indexOf(e
											.getSource())));
							g.up.summonMonster(monstertoadd, sac);
//							g.omonstersjbuttons
//									.get(g.omonstersjbuttons.indexOf(e
//											.getSource()))
//									.setIcon(
//											g.hatelsora(monstertoadd.getName()));
							g.updateupmonsterarea();
							g.updateLabels();
							g.ohand.remove(jbmonstertoadd);
							try {
								g.ohandJButtons.remove(g.omonstersjbuttons
										.indexOf(jbmonstertoadd));
							} catch (ArrayIndexOutOfBoundsException r) {

							}
							try {
								sac.remove(0);
							} catch (IndexOutOfBoundsException rre) {

							}

						} else if (twosacrifice == true) {

							sacrificecount++;
							if (firstsactmp != e.getSource())
								sac.add(g.up
										.getField()
										.getMonstersArea()
										.get(g.omonstersjbuttons.indexOf(e
												.getSource())));
							if (sacrificecount == 1)
								firstsactmp = (JButton) e.getSource();
							System.out.println("1stdone");

							if (sacrificecount == 2
									&& g.up.summonMonster(monstertoadd, sac)) {
								System.out.println("2nd done");

								sacrificecount = 0;

								twosacrifice = false;

								g.ohandJButtons.remove(g.ohandJButtons
										.indexOf(jbmonstertoadd));
								g.ohand.remove(jbmonstertoadd);

								g.updateupmonsterarea();

								g.updateLabels();

							}

						} else if (oppspellonmons2 == true) {
							g.up.activateSpell(
									oppmp,
									g.up.getField()
											.getMonstersArea()
											.get(g.omonstersjbuttons.indexOf(e
													.getSource())));
							g.updateupspellarea();
						}
					}
				} else {
					if (selectoppmons == true && spellonmons == false) {
						selectoppmons = false;
						String[] m = { "yes", "no" };
						Object selected = JOptionPane.showInputDialog(null,
								"Do you want to attack this monster ?",
								"Input", JOptionPane.INFORMATION_MESSAGE, null,
								m, m[1]);
						String act = (String) selected;
						if (act.equals("yes")) {
							g.down.declareAttack(
									attackingmons,
									g.up.getField()
											.getMonstersArea()
											.get(g.omonstersjbuttons.indexOf(e
													.getSource())));
							g.updateupmonsterarea();
							g.updatedownmonsterarea();

							System.out.println("dee");
							g.updateLabels();

						}
					}
					if (spellonmons == true) {
						spellonmons = false;
						g.down.activateSpell(
								coh,
								g.up.getField()
										.getMonstersArea()
										.get(g.omonstersjbuttons.indexOf(e
												.getSource())));

						g.updateall();
						int i = 0;
						while (i < g.handJButtons.size()) {
							g.handJButtons.get(i).addActionListener(this);
							g.handJButtons.get(i).addMouseListener(this);

							i++;
						}
						i = 0;
						while (i < g.ohandJButtons.size()) {
							g.ohandJButtons.get(i).addActionListener(this);
							g.ohandJButtons.get(i).addMouseListener(this);

							i++;
						}
					}
				}
			}
		} catch (NullPointerException tytt) {

		} catch (IndexOutOfBoundsException lml) {

		} catch (MissingFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmptyFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownCardTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownSpellCardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() instanceof MonsterButton
				&& g.monstersjbuttons.contains(e.getSource())
				
				&& (g.down.getField().getMonstersArea().size() > g.monstersjbuttons
						.indexOf(e.getSource()))) {
			int k = g.monstersjbuttons.indexOf(e.getSource());
			MonsterCard mkk = g.down.getField()
					.getMonstersArea().get(k);
			if(mkk.getMode()==Mode.DEFENSE && g.c.getActivePlayer() == g.down || (mkk.getMode()==Mode.ATTACK )) {
			this.g.mous.setVisible(true);
			

			String txt = "Name : " + mkk.getName() + '\n' + "Level : "
					+ mkk.getLevel() + '\n' + "Attak Points : "
					+ mkk.getAttackPoints() + '\n' + "Defense Points :"
					+ mkk.getDefensePoints() + '\n' + "Description : "
					+ mkk.getDescription();

			this.g.mous.setForeground(Color.white);
			this.g.mous.setText(txt);
			g.sora.setIcon(g.hatelsora2(mkk.getName()));
			}

		} else if (e.getSource() instanceof MonsterButton
				&& g.omonstersjbuttons.contains(e.getSource())
				
				&& (g.up.getField().getMonstersArea().size() > g.omonstersjbuttons
						.indexOf(e.getSource()))) {
			
			
			int k = g.omonstersjbuttons.indexOf(e.getSource());
			MonsterCard mkk = g.up.getField().getMonstersArea().get(k);
if(mkk.getMode()==Mode.DEFENSE  && g.c.getActivePlayer() == g.up || (mkk.getMode()==Mode.ATTACK )) { 
	this.g.mous.setVisible(true);
			String txt = "Name : " + mkk.getName() + '\n' + "Level : "
					+ mkk.getLevel() + '\n' + "Attak Points : "
					+ mkk.getAttackPoints() + '\n' + "Defense Points :"
					+ mkk.getDefensePoints() + '\n' + "Description : "
					+ mkk.getDescription();

			this.g.mous.setForeground(Color.white);
			this.g.mous.setText(txt);
			g.sora.setIcon(g.hatelsora2(mkk.getName()));
}

		} else if (e.getSource() instanceof SpellButton
				&& g.spellsjbuttons.contains(e.getSource())
				&& g.c.getActivePlayer() == g.down
				&& (g.down.getField().getSpellArea().size() > g.spellsjbuttons
						.indexOf(e.getSource()))) {
			this.g.mous.setVisible(true);

			int k = g.spellsjbuttons.indexOf(e.getSource());
			SpellCard mkk = g.c.getActivePlayer().getField().getSpellArea()
					.get(k);

			String txt = "Name : " + mkk.getName() + '\n' + "Description : "
					+ mkk.getDescription();

			this.g.mous.setForeground(Color.white);
			this.g.mous.setText(txt);
			g.sora.setIcon(g.hatelsora2(mkk.getName()));

		} else if (e.getSource() instanceof SpellButton
				&& g.ospellsjbuttons.contains(e.getSource())
				&& g.c.getActivePlayer() == g.up
				&& (g.up.getField().getSpellArea().size() > g.ospellsjbuttons
						.indexOf(e.getSource()))) {
			this.g.mous.setVisible(true);

			int k = g.ospellsjbuttons.indexOf(e.getSource());
			SpellCard mkk = g.up.getField().getSpellArea().get(k);

			String txt = "Name : " + mkk.getName() + '\n' + "Description : "
					+ mkk.getDescription();

			this.g.mous.setForeground(Color.white);
			this.g.mous.setText(txt);
			g.sora.setIcon(g.hatelsora2(mkk.getName()));

		} else if (g.handJButtons.contains(e.getSource())
				&& g.c.getActivePlayer() == g.down) {
			this.g.mous.setVisible(true);
			int k = g.handJButtons.indexOf(e.getSource());
			eg.edu.guc.yugioh.cards.Card ccc = g.c.getActivePlayer().getField()
					.getHand().get(k);
			if (ccc instanceof MonsterCard) {

				MonsterCard mkk = (MonsterCard) g.c.getActivePlayer()
						.getField().getHand().get(k);

				String txt = "Name : " + mkk.getName() + '\n' + "Level : "
						+ mkk.getLevel() + '\n' + "Attak Points : "
						+ mkk.getAttackPoints() + '\n' + "Defense Points :"
						+ mkk.getDefensePoints() + '\n' + "Description : "
						+ mkk.getDescription();

				this.g.mous.setForeground(Color.white);
				this.g.mous.setText(txt);
				g.sora.setIcon(g.hatelsora2(mkk.getName()));

			} else {

				SpellCard mkk = (SpellCard) g.c.getActivePlayer().getField()
						.getHand().get(k);

				String txt = "Name : " + mkk.getName() + '\n'
						+ "Description : " + mkk.getDescription();

				this.g.mous.setForeground(Color.white);
				this.g.mous.setText(txt);
				g.sora.setIcon(g.hatelsora2(mkk.getName()));

			}

		} else if (g.ohandJButtons.contains(e.getSource())
				&& g.c.getActivePlayer() == g.up) {
			this.g.mous.setVisible(true);

			int k = g.ohandJButtons.indexOf(e.getSource());
			try {
				eg.edu.guc.yugioh.cards.Card ccc = g.up.getField().getHand()
						.get(k);
				if (ccc instanceof MonsterCard) {

					MonsterCard mkk = (MonsterCard) g.up.getField().getHand()
							.get(k);

					String txt = "Name : " + mkk.getName() + '\n' + "Level : "
							+ mkk.getLevel() + '\n' + "Attak Points : "
							+ mkk.getAttackPoints() + '\n' + "Defense Points :"
							+ mkk.getDefensePoints() + '\n' + "Description : "
							+ mkk.getDescription();

					this.g.mous.setForeground(Color.white);
					this.g.mous.setText(txt);
					g.sora.setIcon(g.hatelsora2(mkk.getName()));

				} else {

					SpellCard mkk = (SpellCard) g.up.getField().getHand()
							.get(k);

					String txt = "Name : " + mkk.getName() + '\n'
							+ "Description : " + mkk.getDescription();

					this.g.mous.setForeground(Color.white);
					this.g.mous.setText(txt);
					g.sora.setIcon(g.hatelsora2(mkk.getName()));

				}
			} catch (IndexOutOfBoundsException i) {

			}

		} else {
			g.sora.setIcon(null);
			g.mous.setVisible(false);
		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
