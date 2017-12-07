package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public abstract  class SpellCard extends Card  implements Cloneable{
	public SpellCard(String name, String description) {
		super(name, description);
	}
	@Override
	public abstract  void action(MonsterCard monster) ;
		// TODO Auto-generated method stub
		
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone(); 
	 }
	
	
}
