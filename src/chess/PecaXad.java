//IMPORTA��ES------------------------------------------------------------------------------------------------------------------------
package chess;
import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
//-----------------------------------------------------------------------------------------------------------------------------------
public abstract class PecaXad extends Peca{
//VARI�VEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private Cor cor;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------
	public PecaXad(Tabuleiro tab, Cor cor) {
		super(tab);
		this.cor=cor;
	}
//GETTERS E SETTERS------------------------------------------------------------------------------------------------------------------
	public Cor getCor() {
		return cor;
	}
//FUN��ES----------------------------------------------------------------------------------------------------------------------------
	protected boolean haPecaOponente(Posicao pos) {
		PecaXad p = (PecaXad)getTabs().pec(pos);
		return p != null && p.getCor() != cor;
	}
}