//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.pecas.Bispo;
import chess.pecas.Cavalo;
import chess.pecas.Peao;
import chess.pecas.Rainha;
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
	private PecaXad vulneravelEnPassant;
	private PecaXad promovido;
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
	public PecaXad getVulneravelEnPassant() {
		return vulneravelEnPassant;
	}
	public PecaXad getPromovido() {
		return promovido;
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
		PecaXad pecaMovida=(PecaXad)tabs.pec(alvo);
		//PROMOÇÃO===================================================================================================================
		promovido=null;
		if(pecaMovida instanceof Peao) {
			if((pecaMovida.getCor()==Cor.BRANCO&&alvo.getRow()==0)||(pecaMovida.getCor()==Cor.PRETO&&alvo.getRow()==7)) {
				promovido =(PecaXad)tabs.pec(alvo);
				promovido = trocarPecaPromovida("H"); 
				
			}
		}
		
		xeque = (testeXeque(oponente(jogadorAtual)))?true:false;
		if(testeXequeMate(oponente(jogadorAtual))) {
			xequeMate=true;
		}else {
			proximoTurno();
		}
		//En Passant
		if(pecaMovida instanceof Peao&&(alvo.getRow()==origem.getRow()-2||alvo.getRow()==origem.getRow()+2)) {
			vulneravelEnPassant=pecaMovida;
		}else {
			vulneravelEnPassant=null;
		}
		return (PecaXad)pecaCapturada;
	}
	
	public PecaXad trocarPecaPromovida(String tipo) {
		if(promovido==null) {
			throw new IllegalStateException("Não há peça para ser promovida!");
		}
		if(!tipo.equals("H")&&!tipo.equals("T")&&!tipo.equals("B")&&!tipo.equals("C")) {
			return promovido;
		}
		Posicao pos = promovido.getPosicXad().toPosic();
		Peca p = tabs.removePeca(pos);
		pecasNoTab.remove(p);
		PecaXad novaPeca= novaPeca(tipo, promovido.getCor());
		tabs.posicPeca(novaPeca, pos);
		pecasNoTab.add(novaPeca);
		return novaPeca;
	}
	private PecaXad novaPeca(String tipo, Cor cor) {
		if(tipo.equals("B")) {return new Bispo(tabs,cor);}
		if(tipo.equals("C")) {return new Cavalo(tabs,cor);}
		if(tipo.equals("H")) {return new Rainha(tabs,cor);}
		return new Torre(tabs,cor);
	}
	
	private Peca fazerMovimento(Posicao origem,Posicao alvo) {
		PecaXad p = (PecaXad)tabs.removePeca(origem);
		p.aumentaMoverContagem();
		Peca pecaCapturada = tabs.removePeca(alvo);
		tabs.posicPeca(p, alvo);
		//Roque pequeno
		if(p instanceof Rei&&alvo.getColumn()==origem.getColumn()+2) {
			Posicao origemT = new Posicao(origem.getRow(),origem.getColumn()+3);
			Posicao alvoT = new Posicao(origem.getRow(),origem.getColumn()+1);
			PecaXad torre =(PecaXad)tabs.removePeca(origemT);
			tabs.posicPeca(torre, alvoT);
			torre.aumentaMoverContagem();
		}
		//Roque grande
		if(p instanceof Rei&&alvo.getColumn()==origem.getColumn()-2) {
			Posicao origemT = new Posicao(origem.getRow(),origem.getColumn()-4);
			Posicao alvoT = new Posicao(origem.getRow(),origem.getColumn()-1);
			PecaXad torre =(PecaXad)tabs.removePeca(origemT);
			tabs.posicPeca(torre, alvoT);
			torre.aumentaMoverContagem();
		}
		
		if(pecaCapturada!=null) {
			pecasNoTab.remove(pecaCapturada);
			pecasCapturaddas.add(pecaCapturada);
		}
		//En Passant
		if(p instanceof Peao) {
			if(origem.getColumn()!=alvo.getColumn()&&pecaCapturada==null) {
				Posicao peaoPosic;
				if(p.getCor()==Cor.BRANCO) {
					peaoPosic=new Posicao(alvo.getRow()+1,alvo.getColumn());
				}else {
					peaoPosic=new Posicao(alvo.getRow()-1,alvo.getColumn());
				}
			pecaCapturada=tabs.removePeca(peaoPosic);
			pecasCapturaddas.add(pecaCapturada);
			pecasNoTab.remove(pecaCapturada);
			}
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
		//Roque pequeno
		if(p instanceof Rei&&alvo.getColumn()==origem.getColumn()+2) {
			Posicao origemT = new Posicao(origem.getRow(),origem.getColumn()+3);
			Posicao alvoT = new Posicao(origem.getRow(),origem.getColumn()+1);
			PecaXad torre =(PecaXad)tabs.removePeca(alvoT);
			tabs.posicPeca(torre, origemT);
			torre.diminuiMoverContagem();
		}
		//Roque grande
		if(p instanceof Rei&&alvo.getColumn()==origem.getColumn()-2) {
			Posicao origemT = new Posicao(origem.getRow(),origem.getColumn()-4);
			Posicao alvoT = new Posicao(origem.getRow(),origem.getColumn()-1);
			PecaXad torre =(PecaXad)tabs.removePeca(alvoT);
			tabs.posicPeca(torre, origemT);
			torre.diminuiMoverContagem();
		}
		//En Passant
		if(p instanceof Peao) {
			if(origem.getColumn()!=alvo.getColumn()&&pecaCapturada==vulneravelEnPassant) {
				PecaXad peaoo=(PecaXad)tabs.removePeca(alvo);
				
				Posicao peaoPosic;
				if(p.getCor()==Cor.BRANCO) {
					peaoPosic=new Posicao(3,alvo.getColumn());
				}else {
					peaoPosic=new Posicao(4,alvo.getColumn());
				}
				tabs.posicPeca(peaoo, peaoPosic);
			}
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
		posicionarNovaPeca('b', 1, new Cavalo(tabs, Cor.BRANCO));
		posicionarNovaPeca('c', 1, new Bispo(tabs, Cor.BRANCO));
		posicionarNovaPeca('d', 1, new Rainha(tabs, Cor.BRANCO));
		posicionarNovaPeca('e', 1, new Rei(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('f', 1, new Bispo(tabs, Cor.BRANCO));
		posicionarNovaPeca('g', 1, new Cavalo(tabs, Cor.BRANCO));
		posicionarNovaPeca('h', 1, new Torre(tabs, Cor.BRANCO));
		
		posicionarNovaPeca('a', 2, new Peao(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('b', 2, new Peao(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('c', 2, new Peao(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('d', 2, new Peao(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('e', 2, new Peao(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('f', 2, new Peao(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('g', 2, new Peao(tabs, Cor.BRANCO,this));
		posicionarNovaPeca('h', 2, new Peao(tabs, Cor.BRANCO,this));

		posicionarNovaPeca('a', 8, new Torre(tabs, Cor.PRETO));
		posicionarNovaPeca('b', 8, new Cavalo(tabs, Cor.PRETO));
		posicionarNovaPeca('c', 8, new Bispo(tabs, Cor.PRETO));
		posicionarNovaPeca('d', 8, new Rainha(tabs, Cor.PRETO));
		posicionarNovaPeca('e', 8, new Rei(tabs, Cor.PRETO,this));
		posicionarNovaPeca('f', 8, new Bispo(tabs, Cor.PRETO));
		posicionarNovaPeca('g', 8, new Cavalo(tabs, Cor.PRETO));
		posicionarNovaPeca('h', 8, new Torre(tabs, Cor.PRETO));
		
		posicionarNovaPeca('a', 7, new Peao(tabs, Cor.PRETO,this));
		posicionarNovaPeca('b', 7, new Peao(tabs, Cor.PRETO,this));
		posicionarNovaPeca('c', 7, new Peao(tabs, Cor.PRETO,this));
		posicionarNovaPeca('d', 7, new Peao(tabs, Cor.PRETO,this));
		posicionarNovaPeca('e', 7, new Peao(tabs, Cor.PRETO,this));
		posicionarNovaPeca('f', 7, new Peao(tabs, Cor.PRETO,this));
		posicionarNovaPeca('g', 7, new Peao(tabs, Cor.PRETO,this));
		posicionarNovaPeca('h', 7, new Peao(tabs, Cor.PRETO,this));
	}
}