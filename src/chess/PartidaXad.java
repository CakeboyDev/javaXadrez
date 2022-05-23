package chess;

import boardgame.Tabuleiro;

public class PartidaXad {

	private Tabuleiro tabs;
	
	public PartidaXad() {
		tabs = new Tabuleiro(8,8);
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
	
}
