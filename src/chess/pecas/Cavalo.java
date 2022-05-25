//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess.pecas;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Cavalo extends PecaXad {
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Cavalo(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "C";
	}
	private boolean podeMover(Posicao pos) {
		PecaXad p = (PecaXad)getTabs().pec(pos);
		return p==null || p.getCor()!=getCor(); 
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		Posicao p = new Posicao(0,0);
		//NOROACIMA
		p.setValores(posic.getRow()-2, posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//NOROABAIXO
		p.setValores(posic.getRow()-1, posic.getColumn()-2);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//SUDOACIMA
		p.setValores(posic.getRow()+1, posic.getColumn()-2);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//SUDOABAIXO
		p.setValores(posic.getRow()+2, posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//SUDEABAIXO
		p.setValores(posic.getRow()+2, posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//SUDEACIMA
		p.setValores(posic.getRow()+1, posic.getColumn()+2);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//NORDEABAIXO
		p.setValores(posic.getRow()-1, posic.getColumn()+2);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//NORDEACIMA
		p.setValores(posic.getRow()-2, posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
	}
}