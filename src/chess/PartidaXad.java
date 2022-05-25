//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.pecas.Bispo;
import chess.pecas.Peao;
import chess.pecas.Rei;
import chess.pecas.Torre;
//-----------------------------------------------------------------------------------------------------------------------------------
public class PartidaXad {
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private Tabuleiro tabs;
	private int turno;
	private Cor jogadorAtual;
	private List<Peca> pecasNoTab = new ArrayList<>();
	private List<Peca> pecasCapturaddas = new ArrayList<>();
	private boolean xeque;
	private boolean xequeMate;
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
	public boolean getXequeMate() {
		return xequeMate;
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	//MOVIMENTAR---------------------------------------------------------------------------------------------------------------------
	public PecaXad fazerMovimentoXad(PosicXad posDeOrigem, PosicXad posicAlvo) {
		Posicao origem = posDeOrigem.toPosic();
		Posicao alvo = posicAlvo.toPosic();
		validarPosicOrigem(origem);
		validarPosicAlvo(origem, alvo);
		Peca pecaCapturada = fazerMovimento(origem, alvo);
		if(testeXeque(jogadorAtual)) {
			desfazerMovimento(origem, alvo, pecaCapturada);
			throw new XadException("Você não pode se colocar em xeque!");
		}
		xeque = (testeXeque(oponente(jogadorAtual)))?true:false;
		if(testeXequeMate(oponente(jogadorAtual))) {
			xequeMate=true;
		}else {
			proximoTurno();
		}
		return (PecaXad)pecaCapturada;
	}
	private Peca fazerMovimento(Posicao origem,Posicao alvo) {
		PecaXad p = (PecaXad)tabs.removePeca(origem);
		p.aumentaMoverContagem();
		Peca pecaCapturada = tabs.removePeca(alvo);
		tabs.posicPeca(p, alvo);
		if(pecaCapturada!=null) {
			pecasNoTab.remove(pecaCapturada);
			pecasCapturaddas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	private void desfazerMovimento(Posicao origem, Posicao alvo, Peca pecaCapturada) {
		PecaXad p = (PecaXad)tabs.removePeca(alvo);
		p.diminuiMoverContagem();
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
			throw new XadException("Não existe peça na posição de origem!");
		}
		if(jogadorAtual!=((PecaXad)tabs.pec(pos)).getCor()) {
			throw new XadException("Essa peça não é sua!");
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
		throw new IllegalStateException("Não há nenhum rei "+cor+" no tabuleiro!");
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
	private boolean testeXequeMate(Cor cor) {
		if(!testeXeque(cor)) {
			return false;
		}
		List<Peca> lista = pecasNoTab.stream().filter(x ->((PecaXad)x).getCor()==cor).collect(Collectors.toList());
		for(Peca p:lista) {
			boolean[][]mat=p.movimentosPossiveis();
			for(int i=0;i<tabs.getRows();i++) {
				for(int j=0;j<tabs.getColumns();j++) {
					if(mat[i][j]) {
						Posicao origem = ((PecaXad)p).getPosicXad().toPosic();
						Posicao alvo = new Posicao(i,j);
						Peca pecaCapturada=fazerMovimento(origem, alvo);
						boolean testeXeque = testeXeque(cor);
						desfazerMovimento(origem, alvo, pecaCapturada);
						if(!testeXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	//TURNOS---------------------------------------------------------------------------------------------------------------------------
	private void proximoTurno() {
		turno++;
		jogadorAtual=(jogadorAtual ==Cor.BRANCO)? Cor.PRETO : Cor.BRANCO;
	}
	//SETUP---------------------------------------------------------------------------------------------------------------------------
	private void setupInicial() {
		posicionarNovaPeca('a', 1, new Torre(tabs, Cor.BRANCO));
		posicionarNovaPeca('c', 1, new Bispo(tabs, Cor.BRANCO));
		posicionarNovaPeca('e', 1, new Rei(tabs, Cor.BRANCO));
		posicionarNovaPeca('f', 1, new Bispo(tabs, Cor.BRANCO));
		posicionarNovaPeca('h', 1, new Torre(tabs, Cor.BRANCO));
		
		posicionarNovaPeca('a', 2, new Peao(tabs, Cor.BRANCO));
		posicionarNovaPeca('b', 2, new Peao(tabs, Cor.BRANCO));
		posicionarNovaPeca('c', 2, new Peao(tabs, Cor.BRANCO));
		posicionarNovaPeca('d', 2, new Peao(tabs, Cor.BRANCO));
		posicionarNovaPeca('e', 2, new Peao(tabs, Cor.BRANCO));
		posicionarNovaPeca('f', 2, new Peao(tabs, Cor.BRANCO));
		posicionarNovaPeca('g', 2, new Peao(tabs, Cor.BRANCO));
		posicionarNovaPeca('h', 2, new Peao(tabs, Cor.BRANCO));

		posicionarNovaPeca('a', 8, new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('c', 8, new Bispo(tabs, Cor.PRETO));
		posicionarNovaPeca('e', 8, new Rei(tabs, Cor.PRETO));
		posicionarNovaPeca('f', 8, new Bispo(tabs, Cor.PRETO));
		posicionarNovaPeca('h', 8, new Torre(tabs, Cor.PRETO));
		
		posicionarNovaPeca('a', 7, new Peao(tabs, Cor.PRETO));
		posicionarNovaPeca('b', 7, new Peao(tabs, Cor.PRETO));
		posicionarNovaPeca('c', 7, new Peao(tabs, Cor.PRETO));
		posicionarNovaPeca('d', 7, new Peao(tabs, Cor.PRETO));
		posicionarNovaPeca('e', 7, new Peao(tabs, Cor.PRETO));
		posicionarNovaPeca('f', 7, new Peao(tabs, Cor.PRETO));
		posicionarNovaPeca('g', 7, new Peao(tabs, Cor.PRETO));
		posicionarNovaPeca('h', 7, new Peao(tabs, Cor.PRETO));
	}
}