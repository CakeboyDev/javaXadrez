//IMPORTA��ES------------------------------------------------------------------------------------------------------------------------
package chess;
import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.pecas.Rei;
import chess.pecas.Torre;
//-----------------------------------------------------------------------------------------------------------------------------------
public class PartidaXad {
//VARI�VEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private Tabuleiro tabs;
	private int turno;
	private Cor jogadorAtual;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public PartidaXad() {
		tabs = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual=Cor.BRANCO;
		setupInicial();
	}
//GETTERS E SETTERS------------------------------------------------------------------------------------------------------------------
	public int getTurno() {
		return turno;
	}
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
//FUN��ES----------------------------------------------------------------------------------------------------------------------------
	//MOVIMENTAR---------------------------------------------------------------------------------------------------------------------
	public PecaXad fazerMovimentoXad(PosicXad posDeOrigem, PosicXad posicAlvo) {
		Posicao origem = posDeOrigem.toPosic();
		Posicao alvo = posicAlvo.toPosic();
		validarPosicOrigem(origem);
		validarPosicAlvo(origem, alvo);
		Peca pecaCapturada = fazerMovimento(origem, alvo);
		proximoTurno();
		return (PecaXad)pecaCapturada;
	}
	private Peca fazerMovimento(Posicao origem,Posicao alvo) {
		Peca p = tabs.removePeca(origem);
		Peca pecaCapturada = tabs.removePeca(alvo);
		tabs.posicPeca(p, alvo);
		return pecaCapturada;
	}
	//VALIDAR------------------------------------------------------------------------------------------------------------------------
	private void validarPosicOrigem(Posicao pos) {
		if(!tabs.temPeca(pos)) {
			throw new XadException("N�o existe pe�a na posi��o de origem!");
		}
		if(jogadorAtual!=((PecaXad)tabs.pec(pos)).getCor()) {
			throw new XadException("Essa pe�a n�o � sua!");
		}
		if(!tabs.pec(pos).haQualquerMovimento()) {
			throw new XadException("N�o h� movimentos poss�veis para esta pe�a!");
		}
	}
	private void validarPosicAlvo(Posicao origem, Posicao alvo) {
		if(!tabs.pec(origem).movimentoPossivel(alvo)) {
			throw new XadException("A pe�a escolhida n�o pode se mover para a posi��o desejada.");
		}
	}
	public boolean[][] possiveisMovimentos(PosicXad posicOrigem){
		Posicao posic = posicOrigem.toPosic();
		validarPosicOrigem(posic);
		return tabs.pec(posic).movimentosPossiveis();
	}
	//POSICIONAR----------------------------------------------------------------------------------------------------------------------
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
	//TURNOS---------------------------------------------------------------------------------------------------------------------------
	private void proximoTurno() {
		turno++;
		jogadorAtual=(jogadorAtual ==Cor.BRANCO)? Cor.PRETO : Cor.BRANCO;
	}
	//SETUP---------------------------------------------------------------------------------------------------------------------------
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