package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard {
	public MagePower(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		
		int x = super.getBoard().getActivePlayer().getField().getSpellArea()
				.size();
		int c1 = monster.getAttackPoints();
		monster.setAttackPoints(c1 + x * 500);

		int c2 = monster.getDefensePoints();
		monster.setDefensePoints(c2 + x * 500);
		//super.getBoard().getActivePlayer().getField()
			//	.removeSpellToGraveyard(this);
	}
}
