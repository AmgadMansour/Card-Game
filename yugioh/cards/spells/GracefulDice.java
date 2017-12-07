package eg.edu.guc.yugioh.cards.spells;

import java.util.Random;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard {
	public GracefulDice(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		// monster = null ;
		
		int x = (new Random()).nextInt(10) + 1;
		int size = super.getBoard().getActivePlayer().getField()
				.getMonstersArea().size();
		for (int i = 0; i < size; i++) {
			int c1 = super.getBoard().getActivePlayer().getField()
					.getMonstersArea().get(i).getAttackPoints();
			super.getBoard().getActivePlayer().getField().getMonstersArea()
					.get(i).setAttackPoints(c1 + (x * 100));

			int c2 = super.getBoard().getActivePlayer().getField()
					.getMonstersArea().get(i).getDefensePoints();
			super.getBoard().getActivePlayer().getField().getMonstersArea()
					.get(i).setDefensePoints(c2 + x * 100);
		}
		// super.getBoard().getActivePlayer().getField()
		// .removeSpellToGraveyard(this);

	}

}
