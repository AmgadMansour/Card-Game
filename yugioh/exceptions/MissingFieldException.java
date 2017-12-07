package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class MissingFieldException  extends UnexpectedFormatException{

	public MissingFieldException(String sourceFile, int sourceLine) {
		super(sourceFile, sourceLine);
		// TODO Auto-generated constructor stub
		 String[] m = { "ok"};
			Object selected = JOptionPane.showInputDialog(null,
					"There is a Field missing in csv files", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
	}

}
