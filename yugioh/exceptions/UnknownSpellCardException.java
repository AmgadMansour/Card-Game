package eg.edu.guc.yugioh.exceptions;

import javax.swing.JOptionPane;

public class UnknownSpellCardException extends UnexpectedFormatException {
	

	String unknownSpell ;
	public UnknownSpellCardException(String sourceFile, int sourceLine , String unkownSpell) {
		super(sourceFile, sourceLine);
		this.unknownSpell=unkownSpell ;
		// TODO Auto-generated constructor stub
		 String[] m = { "ok"};
			Object selected = JOptionPane.showInputDialog(null,
					"There is unkown spell card in csv file", "Input",
					JOptionPane.INFORMATION_MESSAGE, null, m, m[0]);
	}
	public String getUnknownSpell() {
		return unknownSpell;
	}
	public void setUnknownSpell(String unknownSpell) {
		this.unknownSpell = unknownSpell;
	}

}
