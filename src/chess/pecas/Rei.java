//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess.pecas;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Rei extends PecaXad {
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "R";
	}
	private boolean podeMover(Posicao pos) {
		PecaXad p = (PecaXad)getTabs().pec(pos);
		return p==null || p.getCor()!=getCor(); 
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		Posicao p = new Posicao(0,0);
		//ACIMA
		p.setValores(posic.getRow()-1, posic.getColumn());
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALESQUERDA
		p.setValores(posic.getRow()-1, posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//ESQUERDA
		p.setValores(posic.getRow(), posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALINFESQUERDA
		p.setValores(posic.getRow()+1, posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//BAIXO
		p.setValores(posic.getRow()+1, posic.getColumn());
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALINFDIRREITA
		p.setValores(posic.getRow()+1, posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIREITA
		p.setValores(posic.getRow(), posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALDIRREITA
		p.setValores(posic.getRow()-1, posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
	}
}