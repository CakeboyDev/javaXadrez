package chess;

import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;

public class PartidaXad {

	private Tabuleiro tabs;
	
	public PartidaXad() {
		tabs = new Tabuleiro(8,8);
		setupInicial();
	}
	
	public PecaXad[][] getPecas(){
		PecaXad[][] mat = new PecaXad[tabs.getRows()][tabs.getColumns()];
		for(int i=0;i<tabs.getRows();i++) {
			for(int j=0;j<tabs.getColumns();j++) {
				mat[i][j]= (PecaXad)tabs.pec(i,j);
			}
		}
		return mat;
	}
	
	private void posicionarNovaPeca(char column, int row, PecaXad peca) {
		tabs.posicPeca(peca, new PosicXad(column,row).toPosic());
	}
	
	private void setupInicial() {
		posicionarNovaPeca('b',6,new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('e',8,new Rei(tabs, Cor.PRETO));
		posicionarNovaPeca('e',1,new Rei(tabs, Cor.PRETO));
	}
	
}
