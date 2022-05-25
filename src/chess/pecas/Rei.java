//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package chess.pecas;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import chess.Cor;
import chess.PartidaXad;
import chess.PecaXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Rei extends PecaXad {
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------
	private PartidaXad parxad;
//CONSTRUCTORS-----------------------------------------------------------------------------------------------------------------------	
	public Rei(Tabuleiro tab, Cor cor, PartidaXad parxad) {
		super(tab, cor);
		this.parxad=parxad;
	}
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "R";
	}
	private boolean podeMover(Posicao pos) {
		PecaXad p = (PecaXad)getTabs().pec(pos);
		return p==null || p.getCor()!=getCor(); 
	}
	private boolean testeRoqueTorre(Posicao pos) {
		PecaXad p = (PecaXad)getTabs().pec(pos);
		return p!=null&& p instanceof Torre&&p.getCor()==getCor()&&p.getMoverContagem()==0;
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][]mat = new boolean[getTabs().getRows()][getTabs().getColumns()];
		Posicao p = new Posicao(0,0);
		//ACIMA
		p.setValores(posic.getRow()-1, posic.getColumn());
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALESQUERDA
		p.setValores(posic.getRow()-1, posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//ESQUERDA
		p.setValores(posic.getRow(), posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALINFESQUERDA
		p.setValores(posic.getRow()+1, posic.getColumn()-1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//BAIXO
		p.setValores(posic.getRow()+1, posic.getColumn());
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALINFDIRREITA
		p.setValores(posic.getRow()+1, posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIREITA
		p.setValores(posic.getRow(), posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//DIAGONALDIRREITA
		p.setValores(posic.getRow()-1, posic.getColumn()+1);
		if(getTabs().posicExiste(p)&&podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//MOVIMENTO ESPECIAL ROQUE!!!
		if(getMoverContagem()==0&&!parxad.getXeque()) {
			//Roque pequeno
			Posicao posT1 = new Posicao(posic.getRow(),posic.getColumn()+3);
			if(testeRoqueTorre(posT1)) {
				Posicao p1 = new Posicao(posic.getRow(),posic.getColumn()+1);
				Posicao p2 = new Posicao(posic.getRow(),posic.getColumn()+2);
				if(getTabs().pec(p1)==null &&getTabs().pec(p2)==null) {
					mat[posic.getRow()][posic.getColumn()+2]=true;
				}
			}
			//Roque grande
			Posicao posT2 = new Posicao(posic.getRow(),posic.getColumn()-4);
			if(testeRoqueTorre(posT2)) {
				Posicao p1 = new Posicao(posic.getRow(),posic.getColumn()-1);
				Posicao p2 = new Posicao(posic.getRow(),posic.getColumn()-2);
				Posicao p3 = new Posicao(posic.getRow(),posic.getColumn()-3);
				if(getTabs().pec(p1)==null &&getTabs().pec(p2)==null&&getTabs().pec(p3)==null) {
					mat[posic.getRow()][posic.getColumn()-2]=true;
				}
			}
		}
		
		return mat;
	}
}