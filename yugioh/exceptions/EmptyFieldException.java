package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class EmptyFieldException extends UnexpectedFormatException {
	int sourceField ;
	public EmptyFieldException( String sourceFile , int sourceLine , int sourceField ) {
		super(sourceFile , sourceLine) ;
		this.sourceField=sourceField ;
		 String[] m = { "ok"};
			Object selected = JOptionPane.showInputDialog(null,
					"There is a Field empty in csv files", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
		
	}
	public int getSourceField() {
		return sourceField;
	}
	public void setSourceField(int sourceField) {
		this.sourceField = sourceField;
	}
	 

}
