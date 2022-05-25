//IMPORTA��ES------------------------------------------------------------------------------------------------------------------------
package chess;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private List<Peca> pecasNoTab = new ArrayList<>();
	private List<Peca> pecasCapturaddas = new ArrayList<>();
	private boolean xeque;
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
	public boolean getXeque() {
		return xeque;
	}
//FUN��ES----------------------------------------------------------------------------------------------------------------------------
	//MOVIMENTAR---------------------------------------------------------------------------------------------------------------------
	public PecaXad fazerMovimentoXad(PosicXad posDeOrigem, PosicXad posicAlvo) {
		Posicao origem = posDeOrigem.toPosic();
		Posicao alvo = posicAlvo.toPosic();
		validarPosicOrigem(origem);
		validarPosicAlvo(origem, alvo);
		Peca pecaCapturada = fazerMovimento(origem, alvo);
		if(testeXeque(jogadorAtual)) {
			desfazerMovimento(origem, alvo, pecaCapturada);
			throw new XadException("Voc� n�o pode se colocar em xeque!");
		}
		xeque = (testeXeque(oponente(jogadorAtual)))?true:false;
		proximoTurno();
		return (PecaXad)pecaCapturada;
	}
	private Peca fazerMovimento(Posicao origem,Posicao alvo) {
		Peca p = tabs.removePeca(origem);
		Peca pecaCapturada = tabs.removePeca(alvo);
		tabs.posicPeca(p, alvo);
		if(pecaCapturada!=null) {
			pecasNoTab.remove(pecaCapturada);
			pecasCapturaddas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	private void desfazerMovimento(Posicao origem, Posicao alvo, Peca pecaCapturada) {
		Peca p = tabs.removePeca(alvo);
		tabs.posicPeca(p, origem);
		if(pecaCapturada!=null) {
			tabs.posicPeca(pecaCapturada, alvo);
			pecasCapturaddas.remove(pecaCapturada);
			pecasNoTab.add(pecaCapturada);
		}
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
		pecasNoTab.add(peca);
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
	//OPONENTE-------------------------------------------------------------------------------------------------------------------------
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO)? Cor.PRETO : Cor.BRANCO;
	}
	private PecaXad rei(Cor cor) {
		List<Peca> lista = pecasNoTab.stream().filter(x ->((PecaXad)x).getCor()==cor).collect(Collectors.toList());
		for(Peca p:lista) {
			if(p instanceof Rei) {
				return (PecaXad)p;
			}
		}
		throw new IllegalStateException("N�o h� nenhum rei "+cor+" no tabuleiro!");
	}
	private boolean testeXeque(Cor cor) {
		Posicao reiPosicao=rei(cor).getPosicXad().toPosic();
		List<Peca> pecasOponentes = pecasNoTab.stream().filter(x ->((PecaXad)x).getCor()==oponente(cor)).collect(Collectors.toList());
		for(Peca p:pecasOponentes) {
			boolean[][]mat=p.movimentosPossiveis();
			if(mat[reiPosicao.getRow()][reiPosicao.getColumn()]) {
				return true;
			}
		}
		return false;
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