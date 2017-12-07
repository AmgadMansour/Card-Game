package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class UnknownCardTypeException extends UnexpectedFormatException {
	

	String unknownType ;
	public UnknownCardTypeException(String sourceFile, int sourceLine , String  unknownType) {
		super(sourceFile, sourceLine);
		this.unknownType=unknownType ;
		 String[] m = { "ok"};
			Object selected = JOptionPane.showInputDialog(null,
					"There is an unkown card Type in csv files", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
		// TODO Auto-generated constructor stub
	}
	public String getUnknownType() {
		return unknownType;
	}
	public void setUnknownType(String unknownType) {
		this.unknownType = unknownType;
	}

}
