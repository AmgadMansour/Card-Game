package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class MonsterMultipleAttackException extends RuntimeException{
	public MonsterMultipleAttackException() {
		super("g") ;
		 String[] m = { "ok"};
			Object selected = JOptionPane.showInputDialog(null,
					"Monster cannot attack more than once", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
	}

}
