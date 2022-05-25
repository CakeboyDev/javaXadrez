package chess.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;

public class Peao extends PecaXad{

	public Peao(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		Posicao p = new Posicao(0,0);
		if(getCor()==Cor.BRANCO) {
			p.setValores(posic.getRow()-1, posic.getColumn());
			if(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
				mat[p.getRow()][p.getColumn()]=true;
			}
			p.setValores(posic.getRow()-2, posic.getColumn());
			Posicao p2=new Posicao(posic.getRow()-1,posic.getColumn());
			if(getTabs().posicExiste(p)&&!getTabs().temPeca(p)&&getTabs().posicExiste(p2)&&!getTabs().temPeca(p2)&&getMoverContagem()==0) {
				mat[p.getRow()][p.getColumn()]=true;
			}
			p.setValores(posic.getRow()-1, posic.getColumn()-1);
			if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
				mat[p.getRow()][p.getColumn()]=true;
			}
			p.setValores(posic.getRow()-1, posic.getColumn()+1);
			if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
				mat[p.getRow()][p.getColumn()]=true;
			}
		}
		else {
			p.setValores(posic.getRow()+1, posic.getColumn());
			if(getTabs().posicExiste(p)&&!getTabs().temPeca(p)) {
				mat[p.getRow()][p.getColumn()]=true;
			}
			p.setValores(posic.getRow()+2, posic.getColumn());
			Posicao p2=new Posicao(posic.getRow()+1,posic.getColumn());
			if(getTabs().posicExiste(p)&&!getTabs().temPeca(p)&&getTabs().posicExiste(p2)&&!getTabs().temPeca(p2)&&getMoverContagem()==0) {
				mat[p.getRow()][p.getColumn()]=true;
			}
			p.setValores(posic.getRow()+1, posic.getColumn()-1);
			if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
				mat[p.getRow()][p.getColumn()]=true;
			}
			p.setValores(posic.getRow()+1, posic.getColumn()+1);
			if(getTabs().posicExiste(p)&&haPecaOponente(p)) {
				mat[p.getRow()][p.getColumn()]=true;
			}
		}
		return mat;
	}
	@Override
	public String toString() {
		return "P";
	}

}
