package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class ChangeOfHeart extends SpellCard {
	public ChangeOfHeart(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		
		super.getBoard()
				.getActivePlayer()
				.getField()
				.addMonsterToField(monster, monster.getMode(),
						monster.isHidden());
		super.getBoard().getOpponentPlayer().getField().getMonstersArea()
				.remove(monster);

		//super.getBoard().getActivePlayer().getField()
			//	.removeSpellToGraveyard(this);

	}

}
