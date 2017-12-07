package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class NoSpellSpaceException extends NoSpaceException {
public NoSpellSpaceException() {
	super() ;
	 String[] m = { "ok"};
		Object selected = JOptionPane.showInputDialog(null,
				"Spell area is full , you cannot add this spell", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
}
}
