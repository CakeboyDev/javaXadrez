package chess.pecas;

import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;

public class Torre extends PecaXad {

	public Torre(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
	@Override
	public String toString() {
		return "T";
	}

}
