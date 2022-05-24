//IMPORTA��ES------------------------------------------------------------------------------------------------------------------------
package boardgame;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Posicao {
//VARI�VEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private int row;
	private int column;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------
	public Posicao() {}
	public Posicao(int row, int column) {
		this.row=row;
		this.column=column;
	}
//GETTERS E SETTERS------------------------------------------------------------------------------------------------------------------
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
//FUN��ES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return row +", "+ column;
	}
}