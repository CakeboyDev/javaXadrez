//IMPORTA��ES------------------------------------------------------------------------------------------------------------------------
package chess;
import boardgame.Posicao;
//-----------------------------------------------------------------------------------------------------------------------------------
public class PosicXad {
//VARI�VEIS E LISTAS-----------------------------------------------------------------------------------------------------------------	
	private char column;
	private int row;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public PosicXad(char column, int row) {
		if(column < 'a'|| column>'h'||row<1||row>8) {
			throw new XadException("Erro ao instanciar PosicXad. Valores v�lidos s�o de a1 � h8.");
		}
		this.column = column;	
		this.row = row;
	}
//GETTERS E SETTERS------------------------------------------------------------------------------------------------------------------
	public char getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
//FUN��ES----------------------------------------------------------------------------------------------------------------------------
	protected Posicao toPosic() {
		return new Posicao(8-row, column-'a');
	}
	protected static PosicXad fromPosic(Posicao pos) {
		return new PosicXad((char)('a'+pos.getColumn()), 8-pos.getRow()); 
	}
	@Override
	public String toString() {
		return ""+column+row;
	}	
}