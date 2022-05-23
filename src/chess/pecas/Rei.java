package chess.pecas;

import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;

public class Rei extends PecaXad {

	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
	@Override
	public String toString() {
		return "R";
	}

}
