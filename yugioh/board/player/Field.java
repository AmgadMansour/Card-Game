package eg.edu.guc.yugioh.board.player;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.monitor.MonitorSettingException;
import javax.swing.text.GapContent;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Field {
	private ArrayList<MonsterCard> monstersArea = new ArrayList<>(5);
	private ArrayList<SpellCard> spellArea = new ArrayList<>(5);
	private ArrayList<Card> hand;
	private Phase phase;
	private ArrayList<Card> graveyard;
	private Deck deck;
	static int i;
	static int k;

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public Deck getDeck() {
		return deck;
	}

	public Field() throws IOException, CloneNotSupportedException, MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException {
		monstersArea = new ArrayList<>(5);
		spellArea = new ArrayList<>(5);
		hand = new ArrayList<>(5);
		phase = phase.MAIN1;
		graveyard = new ArrayList<>();
		deck = new Deck();
		i = 0;
		k = 0;

	}

	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {
		

if (monstersArea.size() >= 5) {
			
			throw  new NoMonsterSpaceException() ;
		}
		if (monstersArea.size() < 5) {
			monster.setMode(m);
			monster.setHidden(isHidden);
			monstersArea.add(monster);
			hand.remove(monster);
			monster.setLocation(Location.FIELD);
			// i++;
		} else {
			System.out
					.println("Maximum number of Monsters in the battle are 5");
		}
	}

	public void addMonsterToField(MonsterCard monster, Mode mode,
			ArrayList<MonsterCard> sacrifices) {

		if (monstersArea.size() >= 5) {
			
			throw  new NoMonsterSpaceException() ;
		} else {
			if (monster.getLevel() < 5) {
				sacrifices = null;
				addMonsterToField(monster, mode, mode == mode.ATTACK ? false
						: true);
				// i++;
			}
			else {
				if (monster.getLevel() == 5 || monster.getLevel() == 6) {
					if (sacrifices.size()!=1) {
						//System.out.println(" need one sacrifice");
						return;
					}
					else {
					addMonsterToField(monster, mode,
							mode == mode.ATTACK ? false : true);
					removeMonsterToGraveyard(sacrifices.remove(0));
					}
				}
				if (monster.getLevel() == 7 || monster.getLevel() == 8) {
					if (sacrifices.size()!=2) {
						//System.out.println("need two sacrifice");
						return;
					}
					else {
					addMonsterToField(monster, mode,
							mode == mode.ATTACK ? false : true);
					
						removeMonsterToGraveyard(sacrifices.remove(0));
						removeMonsterToGraveyard(sacrifices.remove(0));
					}
				}
			}
			
		}

	}

	public void removeMonsterToGraveyard(MonsterCard monster) {
		// i--;
		//System.out.println(graveyard==Card.getBoard().getActivePlayer().getField().getGraveyard());
		if(monster.getLocation()==Location.FIELD) {
		monstersArea.remove(monster);
		graveyard.add(monster);
		monster.setLocation(Location.GRAVEYARD);
	}
		
	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {
		while (monsters.isEmpty() != true) {
			removeMonsterToGraveyard(monsters.remove(0));
		}
	}

	public void addSpellToField(SpellCard action, MonsterCard monster,
			boolean hidden) {

		if (spellArea.size() >= 5) {
			throw new NoSpellSpaceException() ;
		}
		action.setHidden(hidden);
		if (hidden == true) {
			spellArea.add(action);
			hand.remove(action);
			action.setLocation(Location.FIELD);

		} else {
			hand.remove(action);
			spellArea.add(action);

			action.setLocation(Location.FIELD);
			activateSetSpell(action, monster);

		}

	}

	public void activateSetSpell(SpellCard action, MonsterCard monster) {
		if (spellArea.contains(action)) {
			action.setHidden(false);
			action.action(monster);

			removeSpellToGraveyard(action);
		}

	}

	public void removeSpellToGraveyard(SpellCard spell) {
		// k--;
		if(spell.getLocation()==Location.FIELD) {
		graveyard.add(spell);
		spellArea.remove(spell);
		spell.setLocation(Location.GRAVEYARD);
	}
	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
		while (spells.isEmpty() != true) {
			removeSpellToGraveyard(spells.remove(0));
		}
	}

	public void addCardToHand() {
		if (Card.getBoard().getActivePlayer().getField().getDeck().getDeck().size()==0){
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			return ;
		}
		
		else {
		Card x = deck.drawOneCard();
		if (x!=null) {
		x.setLocation(Location.HAND);
				hand.add(x);
		
		}}
	}
	public void addNCardsToHand(int n) {
		while (n != 0 && Card.getBoard().getWinner()==null) {
			addCardToHand();
			n--;
		}
	}

}