package eg.edu.guc.yugioh.cards;

public class MonsterCard extends Card {
	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	public boolean attkb4=false ;
	public boolean modeb4 =false;

	public MonsterCard(String name, String description, int level,
			int attackPoints, int defencePoints) {
		super(name, description);
		this.level = level;
		this.attackPoints = attackPoints;
		this.defensePoints = defencePoints;
		mode = Mode.DEFENSE;
		attkb4= false ;
		modeb4=false;

	}

	@Override
	public void action(MonsterCard activemonster ) {
		/*attkb4=true ;
		// TODO Auto-generated method stub
		if(activemonster==null && super.getBoard().getOpponentPlayer().getField().getMonstersArea().isEmpty()) {
			super.getBoard().getOpponentPlayer().setLifePoints(super.getBoard().getOpponentPlayer().getLifePoints()-this.attackPoints);
			if(super.getBoard().getOpponentPlayer().getLifePoints()<=0) 
				super.getBoard().setWinner(super.getBoard().getActivePlayer());
		}
		if(activemonster.getMode()==Mode.DEFENSE) {
			activemonster.setHidden(false); 
			if(this.attackPoints > activemonster.defensePoints ) {
				super.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(activemonster);
			}
			if(this.attackPoints < activemonster.defensePoints ) {
				int diff= activemonster.defensePoints-this.attackPoints ;
				super.getBoard().getActivePlayer().setLifePoints(super.getBoard().getActivePlayer().getLifePoints()-diff);
			}
			
			
		}
		else {
			if(this.attackPoints == activemonster.attackPoints) {
				super.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(activemonster);
				super.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(this);
			}
			if(this.attackPoints >activemonster.attackPoints) {
				int diff = this.attackPoints-activemonster.attackPoints ;
				super.getBoard().getOpponentPlayer().setLifePoints(super.getBoard().getOpponentPlayer().getLifePoints()-diff);
				if(super.getBoard().getOpponentPlayer().getLifePoints()<=0) 
					super.getBoard().setWinner(super.getBoard().getActivePlayer());
			}
			if(this.attackPoints < activemonster.attackPoints) {
				int diff = activemonster.attackPoints-this.attackPoints ;
				super.getBoard().getActivePlayer().setLifePoints(super.getBoard().getActivePlayer().getLifePoints()-diff);
				if(super.getBoard().getActivePlayer().getLifePoints()<=0) 
					super.getBoard().setWinner(super.getBoard().getOpponentPlayer());
				
				
			} 
			
		}*/
		
		super.getBoard().getActivePlayer().declareAttack(this, activemonster) ;

			
	}
	public void action() {
		super.getBoard().getActivePlayer().declareAttack(this) ;
	}
	

	public int getLevel() {
		return level;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

}
