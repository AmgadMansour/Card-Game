package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard {
	public HarpieFeatherDuster(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		monster = null;
		
		super.getBoard()
				.getOpponentPlayer()
				.getField()
				.removeSpellToGraveyard(
						super.getBoard().getOpponentPlayer().getField()
								.getSpellArea());
		//super.getBoard().getActivePlayer().getField()
		//		.removeSpellToGraveyard(this);
		

	}
}
