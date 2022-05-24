//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package boardgame;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Tabuleiro {
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private int rows;
	private int columns;
	private Peca[][] pecas;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Tabuleiro(int rows, int columns) {
		if(rows<1||columns<1) {
			throw new TabException("Erro ao criar tabuleiro: o tabuleiro deve ter pelo menos 1 linha e 1 coluna.");
		}
		this.rows = rows;
		this.columns=columns;
		pecas = new Peca[rows][columns];
	}
//GETTERS E SETTERS------------------------------------------------------------------------------------------------------------------
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}	
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	public Peca pec(int row, int column) {
		if(!posicExiste(row,column)) {
			throw new TabException("Posição não existe no tabuleiro!");
		}
		return pecas[row][column];
	}
	public Peca pec(Posicao posic) {
		if(!posicExiste(posic)) {
			throw new TabException("Posição não existe no tabuleiro!");
		}
		return pecas[posic.getRow()][posic.getColumn()];
	}
	public void posicPeca(Peca peca, Posicao posic) {
		if(temPeca(posic)) {
			throw new TabException("Erro ao colocar peça na posição: já existe uma peça na posição "+posic+".");
		}
		pecas[posic.getRow()][posic.getColumn()]=peca;
		peca.posic=posic;
	}
	public boolean posicExiste(int row, int column) {
		return row>=0 && row<rows && column>=0 && column<columns;
	}
	public boolean posicExiste(Posicao pos) {
		return posicExiste(pos.getRow(), pos.getColumn());
	}
//EXCEPTIONS-------------------------------------------------------------------------------------------------------------------------
	public boolean temPeca(Posicao pos) {
		if(!posicExiste(pos)) {
			throw new TabException("Posição não existe no tabuleiro!");
		}
		return pec(pos)!=null;
	}	
}