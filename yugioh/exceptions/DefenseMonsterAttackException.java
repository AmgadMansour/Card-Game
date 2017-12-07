package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;
import javax.swing.JWindow;

public class DefenseMonsterAttackException extends RuntimeException {
  public  DefenseMonsterAttackException() {
	  super("g") ;
	  String[] m = { "ok"};
		Object selected = JOptionPane.showInputDialog(null,
				"Defense Monster Cannot Attack", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
  }
}
