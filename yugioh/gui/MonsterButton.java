package eg.edu.guc.yugioh.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterButton extends CardButton {
	String name;

	public MonsterButton(String name) {
		super(name) ;
		this.name = name;
	}

}
