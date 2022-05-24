//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess.pecas;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Rei extends PecaXad {
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Rei(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "R";
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		return mat;
	}
}