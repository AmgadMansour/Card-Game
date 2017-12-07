package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki {
	public DarkHole(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		super.action(monster);
		monster = null;
		super.getBoard()
				.getActivePlayer()
				.getField()
				.removeMonsterToGraveyard(
						super.getBoard().getActivePlayer().getField()
								.getMonstersArea());

		super.getBoard()
				.getOpponentPlayer()
				.getField()
				.removeMonsterToGraveyard(
						super.getBoard().getOpponentPlayer().getField()
								.getMonstersArea());

		// super.getBoard().getActivePlayer().getField()
		// .removeSpellToGraveyard(this);
	}

}
