package chess;

import boardgame.Peca;
import boardgame.Tabuleiro;

public class PecaXad extends Peca{
	
	private Cor cor;
	
	public PecaXad(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor=cor;
	}

	public Cor getCor() {
		return cor;
	}


	
}
