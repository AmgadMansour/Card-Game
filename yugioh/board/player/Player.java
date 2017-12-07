package eg.edu.guc.yugioh.board.player;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;

import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist {
	private String name;
	private int lifePoints;
	private Field field;
	boolean summonb4 = false;

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getName() {
		return name;
	}

	public Field getField() {
		return field;
	}

	public Player(String name) throws IOException, CloneNotSupportedException,
			MissingFieldException, EmptyFieldException,
			UnknownCardTypeException, UnknownSpellCardException {
		this.name = name;
		lifePoints = 8000;
		field = new Field();
	}

	@Override
	public boolean summonMonster(MonsterCard monster) {
try{
		if (getField().getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();
}catch(NoMonsterSpaceException dnsj){
	
}
	try{
		if (getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();}
	catch(WrongPhaseException dasda){
		
	}
		try {
			if (summonb4 == true)
			throw new MultipleMonsterAdditionException();}
		catch(MultipleMonsterAdditionException ds){
			
		}
		try{
		if (monster.attkb4 == true)
			throw new MonsterMultipleAttackException();}
		catch(MonsterMultipleAttackException dsb){
			
		}

		if (getField().getMonstersArea().size() != 5
				&& getField().getPhase() != Phase.BATTLE
				&& Card.getBoard().getActivePlayer() == this
				&& monster.getLevel() < 5
				&& Card.getBoard().getWinner() == null && summonb4 == false
				&& monster.attkb4 == false
				&& monster.getLocation() == Location.HAND) {
			Card.getBoard().getActivePlayer().getField()
					.addMonsterToField(monster, Mode.ATTACK, null);
			summonb4 = true;
			return true;
		}

		return false;
	}

	@Override
	public boolean summonMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) {
try{
		if (getField().getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();}
catch(NoMonsterSpaceException dsn){
	
}
	try{	if (getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
	}catch (WrongPhaseException dmsds){
		
	}
		try {
			if (summonb4 == true)
				throw new MultipleMonsterAdditionException();
		} catch (MultipleMonsterAdditionException mmm) {

		}
		try{
		if (monster.attkb4 == true)
			throw new MonsterMultipleAttackException();
		}
		catch(MonsterMultipleAttackException dsds){
			
		}
		if (getField().getMonstersArea().size() != 5
				&& getField().getPhase() != Phase.BATTLE
				&& Card.getBoard().getActivePlayer() == this
				&& monster.getLevel() >= 5
				&& Card.getBoard().getWinner() == null && summonb4 == false
				&& monster.getLocation() == Location.HAND) {
			boolean b = true;
			switch (monster.getLevel()) {
			case 5:
			case 6:
				b = sacrifices.size() == 1;
				break;
			default:
				b = sacrifices.size() == 2;
			}
			if (!b)
				return false;

			Card.getBoard().getActivePlayer().getField()
					.addMonsterToField(monster, Mode.ATTACK, sacrifices);
			summonb4 = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster) {
try{
		if (summonb4 == true)
			throw new MultipleMonsterAdditionException();
}catch(MultipleMonsterAdditionException dds){
	
}
try{
		if (getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
}
catch(WrongPhaseException dsd){
	
}
try{	
if (getField().getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();
}catch(NoMonsterSpaceException sdsda){
	
}
		if (this.getField().getPhase() != Phase.BATTLE
				&& Card.getBoard().getActivePlayer() == this
				&& monster.getLevel() < 5
				&& Card.getBoard().getWinner() == null) {
			Card.getBoard().getActivePlayer().getField()
					.addMonsterToField(monster, Mode.DEFENSE, null);
			summonb4 = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) {
try{
		if (summonb4 == true)
			throw new MultipleMonsterAdditionException();
}catch(MultipleMonsterAdditionException sjk){
	
}
	try{	if (getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
	}catch(WrongPhaseException dskd){
		
	}
		try {if (getField().getMonstersArea().size() == 5)
			throw new NoMonsterSpaceException();}
		catch(NoMonsterSpaceException dskds){
			
		}
		if (this.getField().getPhase() != Phase.BATTLE
				&& Card.getBoard().getActivePlayer() == this
				&& monster.getLevel() > 5
				&& Card.getBoard().getWinner() == null) {
			Card.getBoard().getActivePlayer().getField()
					.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
			summonb4 = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean setSpell(SpellCard spell) {
try{
		if (getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();}
catch(WrongPhaseException llll ){
	
}
	try{
		if (getField().getSpellArea().size() == 5)
			throw new NoSpellSpaceException();}
	catch(NoSpellSpaceException asdksl){
		
	}

		if (spell.getLocation() == Location.HAND
				&& getField().getPhase() != Phase.BATTLE
				&& Card.getBoard().getActivePlayer() == this
				&& Card.getBoard().getWinner() == null) {
			Card.getBoard().getActivePlayer().getField()
					.addSpellToField(spell, null, true);
			return true;
		}
		return false;
	}

	@Override
	public boolean activateSpell(SpellCard spell, MonsterCard monster) {
try{
		if (getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
}catch(WrongPhaseException dskk){
	
}
		if (Card.getBoard().getActivePlayer() == this
				&& Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer().getField().getPhase() != Phase.BATTLE) {

			if (spell.getLocation() == Location.FIELD)
				Card.getBoard().getActivePlayer().getField()
						.activateSetSpell(spell, monster);
			else if (spell.getLocation() == Location.HAND)
				Card.getBoard().getActivePlayer().getField()
						.addSpellToField(spell, monster, false);

			return true;
		}
		return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster,
			MonsterCard opponentMonster) {
try{
		if (activeMonster.attkb4 == true)
			throw new MonsterMultipleAttackException();}
catch(MonsterMultipleAttackException dsds){
	
}
	try{	if (activeMonster.getMode() != Mode.ATTACK)
			throw new DefenseMonsterAttackException();}
	catch(DefenseMonsterAttackException dsva){
		
	}try{
		if (getField().getPhase() != Phase.BATTLE)
			throw new WrongPhaseException();}
	catch(WrongPhaseException dsnds){
		
	}
		if (Card.getBoard().getActivePlayer() == this
				&& activeMonster.attkb4 == false
				&& Card.getBoard().getWinner() == null
				&& activeMonster.getMode() == Mode.ATTACK) {

			/*
			 * if (Card.getBoard().getOpponentPlayer().getField()
			 * .getMonstersArea().isEmpty()) {
			 * 
			 * Card.getBoard() .getOpponentPlayer() .setLifePoints(
			 * Card.getBoard().getOpponentPlayer() .getLifePoints() -
			 * activeMonster.getAttackPoints()); if
			 * (Card.getBoard().getOpponentPlayer().getLifePoints() <= 0)
			 * Card.getBoard() .setWinner(Card.getBoard().getActivePlayer()); }
			 */

			if (opponentMonster.getMode() == Mode.DEFENSE) {
				opponentMonster.setHidden(false);
				if (activeMonster.getAttackPoints() > opponentMonster
						.getDefensePoints()) {
					Card.getBoard().getOpponentPlayer().getField()
							.removeMonsterToGraveyard(opponentMonster);
				} else if (activeMonster.getAttackPoints() < opponentMonster

				.getDefensePoints()) {

					int diff = opponentMonster.getDefensePoints()
							- activeMonster.getAttackPoints();
					Card.getBoard()
							.getActivePlayer()
							.setLifePoints(
									Card.getBoard().getActivePlayer()
											.getLifePoints()
											- diff);

				}

			} else {
				if (activeMonster.getAttackPoints() == opponentMonster
						.getAttackPoints()) {
					Card.getBoard().getOpponentPlayer().getField()
							.removeMonsterToGraveyard(opponentMonster);
					Card.getBoard().getActivePlayer().getField()
							.removeMonsterToGraveyard(activeMonster);
				} else if (activeMonster.getAttackPoints() < opponentMonster
						.getAttackPoints()) {

					int diff = opponentMonster.getAttackPoints()
							- activeMonster.getAttackPoints();
					Card.getBoard().getActivePlayer().getField()
							.removeMonsterToGraveyard(activeMonster);
					Card.getBoard()
							.getActivePlayer()
							.setLifePoints(
									Card.getBoard().getActivePlayer()
											.getLifePoints()
											- diff);
					if (Card.getBoard().getActivePlayer().getLifePoints() <= 0)
						Card.getBoard().setWinner(
								Card.getBoard().getOpponentPlayer());

				} else {
					// int diff = activeMonster.getAttackPoints()
					// - opponentMonster.getAttackPoints();
					// System.out.println(diff + "--diff");
					Card.getBoard().getOpponentPlayer().getField()
							.removeMonsterToGraveyard(opponentMonster);
					Card.getBoard()
							.getOpponentPlayer()
							.setLifePoints(
									Card.getBoard().getOpponentPlayer()
											.getLifePoints()
											- (activeMonster.getAttackPoints() - opponentMonster
													.getAttackPoints()));

					if (Card.getBoard().getOpponentPlayer().getLifePoints() <= 0)
						Card.getBoard().setWinner(
								Card.getBoard().getActivePlayer());

				}

			}

			activeMonster.attkb4 = true;
			System.out.println(Card.getBoard().getActivePlayer()
					.getLifePoints()
					+ "****"
					+ Card.getBoard().getOpponentPlayer().getLifePoints()
					+ "/////*");
			System.out.println("///////////DONE///////////");
			return true;
		}
		System.out.println(Card.getBoard().getActivePlayer().getLifePoints()
				+ "*AAAA***"
				+ Card.getBoard().getOpponentPlayer().getLifePoints()
				+ "/////*");
		System.out.println("//////////END////////////");

		return false;
	}

	/*
	 * void delete(MonsterCard m){
	 * Card.getBoard().getOpponentPlayer().getField().getGraveyard().add(m);
	 * Card
	 * .getBoard().getOpponentPlayer().getField().getMonstersArea().remove(m);
	 * m.setLocation(Location); }
	 */

	@Override
	//
	// activeMonster.getLocation() == Location.FIELD
	public boolean declareAttack(MonsterCard activeMonster) {

		if (activeMonster.attkb4 == true)
			throw new MonsterMultipleAttackException();
		try {
			if (activeMonster.getMode() != Mode.ATTACK)
				throw new DefenseMonsterAttackException();
		} catch (DefenseMonsterAttackException dsl) {

		}
		if (getField().getPhase() != Phase.BATTLE)
			throw new WrongPhaseException();
		if (getField().getPhase() == Phase.BATTLE
				&& Card.getBoard().getActivePlayer() == this
				&& Card.getBoard().getOpponentPlayer().getField()
						.getMonstersArea().size() == 0
				&& activeMonster.getMode() == Mode.ATTACK
				&& activeMonster.attkb4 == false
				&& Card.getBoard().getWinner() == null) {
			Card.getBoard()
					.getOpponentPlayer()
					.setLifePoints(
							Card.getBoard().getOpponentPlayer().getLifePoints()
									- activeMonster.getAttackPoints());
			if (Card.getBoard().getOpponentPlayer().getLifePoints() <= 0)
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
			activeMonster.attkb4 = true;
			return true;
		}

		return false;
	}

	@Override
	public void addCardToHand() {
		Card.getBoard().getActivePlayer().getField().addCardToHand();

	}

	@Override
	public void addNCardsToHand(int n) {
		Card.getBoard().getActivePlayer().getField().addNCardsToHand(n);

	}

	@Override
	public void endPhase() throws IOException, CloneNotSupportedException {
		if (field.getPhase() == Phase.MAIN1
				&& Card.getBoard().getWinner() == null) {
			// int i = 0;
			// while (i < Card.getBoard().getActivePlayer().getField()
			// .getMonstersArea().size()) {
			// Card.getBoard().getActivePlayer().getField().getMonstersArea()
			// .get(i).attkb4 = false;
			// Card.getBoard().getActivePlayer().getField().getMonstersArea()
			// .get(i).modeb4 = false;
			//
			// i++;
			// }
			field.setPhase(Phase.BATTLE);
		} else if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE
				&& Card.getBoard().getWinner() == null) {

			Card.getBoard().getActivePlayer().getField().setPhase(Phase.MAIN2);
			// int i = 0;
			// while (i < Card.getBoard().getActivePlayer().getField()
			// .getMonstersArea().size()) {
			// Card.getBoard().getActivePlayer().getField().getMonstersArea()
			// .get(i).attkb4 = false;
			// Card.getBoard().getActivePlayer().getField().getMonstersArea()
			// .get(i).modeb4 = false;
			//
			// i++;
			// }

		} else if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN2
				&& Card.getBoard().getWinner() == null) {

			field.setPhase(Phase.MAIN1);
			endTurn();

		}
	}

	@Override
	public boolean endTurn() throws IOException, CloneNotSupportedException {
		if (Card.getBoard().getActivePlayer() == this
				&& Card.getBoard().getWinner() == null) {
			int i = 0;
			while (i < Card.getBoard().getActivePlayer().getField()
					.getMonstersArea().size()) {
				Card.getBoard().getActivePlayer().getField().getMonstersArea()
						.get(i).attkb4 = false;
				Card.getBoard().getActivePlayer().getField().getMonstersArea()
						.get(i).modeb4 = false;

				i++;
			}
			summonb4 = false;
			Card.getBoard().nextPlayer();
			return true;
		}
		return false;
	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) {
		try {if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE)
			throw new WrongPhaseException();
		}
		catch(WrongPhaseException ger) {
			
		}
		// TODO Auto-generated method stub
		if (Card.getBoard().getActivePlayer().getField().getPhase() != Phase.BATTLE
				&& Card.getBoard().getActivePlayer() == this
				&& Card.getBoard().getWinner() == null
				&& monster.modeb4 == false
				&& monster.getLocation() == Location.FIELD) {
			if (monster.getMode() == Mode.ATTACK)
				monster.setMode(Mode.DEFENSE);
			else
				monster.setMode(Mode.ATTACK);
			monster.modeb4 = true;
			return true;

		}
		return false;
	}

}
