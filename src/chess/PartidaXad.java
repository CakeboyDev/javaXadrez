//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess;
import boardgame.Peca;
import boardgame.Posicao;
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
	public PecaXad fazerMovimentoXad(PosicXad posDeOrigem, PosicXad posicAlvo) {
		Posicao origem = posDeOrigem.toPosic();
		Posicao alvo = posicAlvo.toPosic();
		validarPosicOrigem(origem);
		validarPosicAlvo(origem, alvo);
		Peca pecaCapturada = fazerMovimento(origem, alvo);
		return (PecaXad)pecaCapturada;
	}
	private Peca fazerMovimento(Posicao origem,Posicao alvo) {
		Peca p = tabs.removePeca(origem);
		Peca pecaCapturada = tabs.removePeca(alvo);
		tabs.posicPeca(p, alvo);
		return pecaCapturada;
	}
	private void validarPosicOrigem(Posicao pos) {
		if(!tabs.temPeca(pos)) {
			throw new XadException("Não existe peça na posição de origem!");
		}
		if(!tabs.pec(pos).haQualquerMovimento()) {
			throw new XadException("Não há movimentos possíveis para esta peça!");
		}
	}
	private void validarPosicAlvo(Posicao origem, Posicao alvo) {
		if(!tabs.pec(origem).movimentoPossivel(alvo)) {
			throw new XadException("A peça escolhida não pode se mover para a posição desejada.");
		}
	}
	
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