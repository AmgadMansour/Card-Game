package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard {
	public MonsterReborn(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		monster = null;
		
		MonsterCard max1 = new MonsterCard(null, null, 0, 0, 0);
		MonsterCard max2 = new MonsterCard(null, null, 0, 0, 0);
		int size1 = super.getBoard().getActivePlayer().getField()
				.getGraveyard().size();
		int size2 = super.getBoard().getOpponentPlayer().getField()
				.getGraveyard().size();
		for (int i = 0; i < size1; i++) {
			if (super.getBoard().getActivePlayer().getField().getGraveyard()
					.get(i) instanceof MonsterCard) {
				MonsterCard current = (MonsterCard) super.getBoard()
						.getActivePlayer().getField().getGraveyard().get(i);
				if (current.getAttackPoints() >= max1.getAttackPoints()) {
					max1 = current;
				}
			}
		}
		for (int j = 0; j < size2; j++) {
			if (super.getBoard().getOpponentPlayer().getField().getGraveyard()
					.get(j) instanceof MonsterCard) {
				MonsterCard current2 = (MonsterCard) super.getBoard()
						.getOpponentPlayer().getField().getGraveyard().get(j);
				if (current2.getAttackPoints() >= max2.getAttackPoints()) {
					max2 = current2;
				}
			}
		}
		if (max1.getAttackPoints() >= max2.getAttackPoints()) {
			super.getBoard().getActivePlayer().getField().getGraveyard()
					.remove(max1);
			//super.getBoard().getOpponentPlayer().getField().getGraveyard().remove(max1);
			if (super.getBoard().getActivePlayer().getField().getMonstersArea()
					.size() >= 5) {
				System.out.println("no free space ");
			} else {
				super.getBoard().getActivePlayer().getField().getMonstersArea()
						.add(max1);
				System.out.println(getBoard().getActivePlayer().getField().getMonstersArea().size()+"hhh");
				max1.setLocation(Location.FIELD);
				max1.setHidden(false);
				max1.setMode(Mode.ATTACK);
			}
		} else {
			super.getBoard().getOpponentPlayer().getField().getGraveyard()
					.remove(max2);
			super.getBoard().getActivePlayer().getField().getGraveyard().remove(max2);
			if (super.getBoard().getOpponentPlayer().getField()
					.getMonstersArea().size() >= 5) {
				System.out.println("no free space ");
			} else {
				super.getBoard().getActivePlayer().getField()
						.getMonstersArea().add(max2);
				max2.setLocation(Location.FIELD);
				max2.setHidden(false);
				max2.setMode(Mode.ATTACK);
			}
		}
		//super.getBoard().getActivePlayer().getField()
			//	.removeSpellToGraveyard(this);
	}

}
