package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class MultipleMonsterAdditionException extends RuntimeException{
	public MultipleMonsterAdditionException() {
		super("g") ;
		 String[] m = { "ok"};
			Object selected = JOptionPane.showInputDialog(null,
					"Only one monster can be added", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
	}

}
