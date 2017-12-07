package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster {
	public HeavyStorm(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		monster = null;
		super.action(monster);
		// System.out.println(super.getBoard().getOpponentPlayer().getField()
		// .getGraveyard().size());
		/*
		 * super.getBoard() .getOpponentPlayer() .getField()
		 * .removeSpellToGraveyard(
		 * super.getBoard().getOpponentPlayer().getField() .getSpellArea());
		 */
		// System.out.println(super.getBoard().getOpponentPlayer().getField()
		// .getSpellArea().size());

		// System.out.println(super.getBoard().getOpponentPlayer().getField()
		// .getGraveyard().size());
		super.getBoard()
				.getActivePlayer()
				.getField()
				.removeSpellToGraveyard(
						super.getBoard().getActivePlayer().getField()
								.getSpellArea());

		// super.getBoard().getActivePlayer().getField()
		// .removeSpellToGraveyard(this);

	}
}
