//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess.pecas;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Rainha extends PecaXad {
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Rainha(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "H";
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		Posicao p = new Posicao(0,0);
		//ACIMA
		p.setValores(posic.getRow()-1, posic.getColumn());
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()-1);
		}
		if(getTabs().posicExiste(p) && haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//ESQUERDA
		p.setValores(posic.getRow(), posic.getColumn()-1);
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()-1);
		}
		if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//ABAIXO
		p.setValores(posic.getRow()+1, posic.getColumn());
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()+1);
		}
		if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIREITA
		p.setValores(posic.getRow(), posic.getColumn()+1);
		while(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()+1);
		}
		if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
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