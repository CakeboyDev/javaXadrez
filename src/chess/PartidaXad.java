package chess;

import boardgame.Posicao;
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
	
	private void setupInicial() {
		tabs.posicPeca(new Torre(tabs, Cor.PRETO), new Posicao(2,1));
		tabs.posicPeca(new Rei(tabs, Cor.PRETO), new Posicao(7,7));
		tabs.posicPeca(new Rei(tabs, Cor.PRETO), new Posicao(7,4));
	}
	
}
