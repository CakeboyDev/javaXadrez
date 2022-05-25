//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess.pecas;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Bispo extends PecaXad {
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Bispo(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "B";
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		Posicao p = new Posicao(0,0);
		//NW
		p.setValores(posic.getRow()-1, posic.getColumn()-1);
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValores(p.getRow()-1,p.getColumn()-1);
		}
		if(getTabs().posicExiste(p) && haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//NE
		p.setValores(posic.getRow()-1, posic.getColumn()+1);
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValores(p.getRow()-1,p.getColumn()+1);
		}
		if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//SE
		p.setValores(posic.getRow()+1, posic.getColumn()+1);
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValores(p.getRow()+1,p.getColumn()+1);
		}
		if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//SW
		p.setValores(posic.getRow()+1, posic.getColumn()-1);
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValores(p.getRow()+1,p.getColumn()-1);
		}
		if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}
}