package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class WrongPhaseException  extends RuntimeException{
 public WrongPhaseException() {
	 super("ag") ;
	 String[] m = { "ok"};
		Object selected = JOptionPane.showInputDialog(null,
				"Wrong phase", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
 }
}
