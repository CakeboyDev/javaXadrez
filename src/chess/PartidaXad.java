//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess;
import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;
//-----------------------------------------------------------------------------------------------------------------------------------
public class PartidaXad {
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private Tabuleiro tabs;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public PartidaXad() {
		tabs = new Tabuleiro(8, 8);
		setupInicial();
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	private void posicionarNovaPeca(char column, int row, PecaXad peca) {
		tabs.posicPeca(peca, new PosicXad(column, row).toPosic());
	}
	
	
	
	public PecaXad[][] getPecas() {
		PecaXad[][] mat = new PecaXad[tabs.getRows()][tabs.getColumns()];
		for (int i = 0; i < tabs.getRows(); i++) {
			for (int j = 0; j < tabs.getColumns(); j++) {
				mat[i][j] = (PecaXad) tabs.pec(i, j);
			}
		}
		return mat;
	}
	private void setupInicial() {
		posicionarNovaPeca('c', 1, new Torre(tabs, Cor.BRANCO));
		posicionarNovaPeca('c', 2, new Torre(tabs, Cor.BRANCO));
		posicionarNovaPeca('d', 2, new Torre(tabs, Cor.BRANCO));
		posicionarNovaPeca('e', 2, new Torre(tabs, Cor.BRANCO));
		posicionarNovaPeca('e', 1, new Torre(tabs, Cor.BRANCO));
		posicionarNovaPeca('d', 1, new Rei(tabs, Cor.BRANCO));

		posicionarNovaPeca('c', 7, new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('c', 8, new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('d', 7, new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('e', 7, new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('e', 8, new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('d', 8, new Rei(tabs, Cor.PRETO));
	}
}