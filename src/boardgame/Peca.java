//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package boardgame;
//-----------------------------------------------------------------------------------------------------------------------------------
public abstract class Peca {
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	protected Posicao posic;
	private Tabuleiro tabs;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Peca(Tabuleiro tab) {
		this.tabs=tab;
	}
//GETTERS E SETTERS------------------------------------------------------------------------------------------------------------------
	protected Tabuleiro getTabs() {
		return tabs;
	}	
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	public abstract boolean[][] movimentosPossiveis();
	public boolean movimentoPossivel(Posicao pos){
		return movimentosPossiveis()[pos.getRow()][pos.getColumn()];
	}
	public boolean haQualquerMovimento() {
		boolean[][] mat = movimentosPossiveis();
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat.length;j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}