//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess;

//EXCEPTIONS-------------------------------------------------------------------------------------------------------------------------
public class XadException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public XadException(String msg) {
		super(msg);
	}
}