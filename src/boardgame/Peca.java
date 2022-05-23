package boardgame;

public class Peca {

	protected Posicao posic;
	private Tabuleiro tabs;
	
	public Peca(Tabuleiro tab) {
		this.tabs=tab;
	}

	protected Tabuleiro getTabs() {
		return tabs;
	}	
	
	
}
