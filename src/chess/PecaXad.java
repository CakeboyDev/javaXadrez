//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess;
import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
//-----------------------------------------------------------------------------------------------------------------------------------
public abstract class PecaXad extends Peca{
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private Cor cor;
	private int moverContagem;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------
	public PecaXad(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor=cor;
	}
//GETTERS E SETTERS------------------------------------------------------------------------------------------------------------------
	public Cor getCor() {
		return cor;
	}
	public int getMoverContagem() {
		return moverContagem;
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	public void aumentaMoverContagem() {
		moverContagem++;
	}
	public void diminuiMoverContagem() {
		moverContagem--;
	}
	public PosicXad getPosicXad() {
		return PosicXad.fromPosic(posic);
	}
	
	protected boolean haPecaOponente(Posicao pos) {
		PecaXad p = (PecaXad)getTabs().pec(pos);
		return p != null && p.getCor() != cor;
	}
}