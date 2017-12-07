package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard {
	public CardDestruction(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		monster = null ;
	
		int size1 = super.getBoard().getActivePlayer().getField().getHand()
				.size();
		int size2 = super.getBoard().getOpponentPlayer().getField().getHand()
				.size();

		for (int i = 0; i < size1; i++) {
			Card c = super.getBoard().getActivePlayer().getField().getHand()
					.remove(0);
			super.getBoard().getActivePlayer().getField().getGraveyard().add(c);
			c.setLocation(Location.GRAVEYARD);

		}
		super.getBoard().getActivePlayer().getField().addNCardsToHand(size1);

		for (int j = 0; j < size2; j++) {
			Card x = super.getBoard().getOpponentPlayer().getField().getHand()
					.remove(0);
			super.getBoard().getOpponentPlayer().getField().getGraveyard()
					.add(x);
			x.setLocation(Location.GRAVEYARD);
		}

		super.getBoard().getOpponentPlayer().getField().addNCardsToHand(size2);
		//super.getBoard().getActivePlayer().getField()
			//	.removeSpellToGraveyard(this);
		
	}

}
