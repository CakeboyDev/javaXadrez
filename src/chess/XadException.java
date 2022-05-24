//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess;
import boardgame.TabException;
//EXCEPTIONS-------------------------------------------------------------------------------------------------------------------------
public class XadException extends TabException{
	private static final long serialVersionUID = 1L;
	
	public XadException(String msg) {
		super(msg);
	}
}