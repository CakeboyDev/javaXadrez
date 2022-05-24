//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess.pecas;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PecaXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Torre extends PecaXad {
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Torre(Tabuleiro tab, Cor cor) {
		super(tab, cor);
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "T";
	}
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		return mat;
	}
}