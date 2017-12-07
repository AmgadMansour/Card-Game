package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class PotOfGreed extends SpellCard {
	public PotOfGreed(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		monster = null ;
		
		super.getBoard().getActivePlayer().getField().addCardToHand();
		super.getBoard().getActivePlayer().getField().addCardToHand();
		

		
		//super.getBoard().getActivePlayer().getField()
		//.removeSpellToGraveyard(this);
	}
}
