package eg.edu.guc.yugioh.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.spells.SpellCard;

public class SpellButton extends CardButton {
	String name;
	public SpellButton(String name) {
		super(name) ;
		this.name=name;	
}
}
