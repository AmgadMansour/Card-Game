package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class NoMonsterSpaceException extends NoSpaceException{ 
	public NoMonsterSpaceException () {
		super() ;
		 String[] m = { "ok"};
			Object selected = JOptionPane.showInputDialog(null,
					"Monster area is full", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
	}

}
