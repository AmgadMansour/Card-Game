package eg.edu.guc.yugioh.cards.spells;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard {
	public Raigeki(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		monster = null;
		
		ArrayList<MonsterCard> c = super.getBoard().getOpponentPlayer()
				.getField().getMonstersArea();
		super.getBoard().getOpponentPlayer().getField()
				.removeMonsterToGraveyard(c);
		// super.getBoard().getActivePlayer().getField()
		// .removeSpellToGraveyard(this);

	}
}
