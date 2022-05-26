//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package aplicativo;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import chess.PartidaXad;
import chess.PecaXad;
import chess.PosicXad;
import chess.XadException;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Programa {

	public static void main(String[] args) {
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------		
		Scanner sc = new Scanner(System.in);
		PartidaXad parxad = new PartidaXad();
		List<PecaXad> capturadas = new ArrayList<>();
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
		while(!parxad.getXequeMate()) {
			try {
				UI.limparTela();
				UI.printPartida(parxad, capturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicXad origem = UI.lerPosicXad(sc);
				boolean[][] possiveisMovimentos = parxad.possiveisMovimentos(origem);
				UI.limparTela();
				UI.printTab(parxad.getPecas(), possiveisMovimentos);
				System.out.println();
				System.out.print("Alvo: ");
				PosicXad alvo = UI.lerPosicXad(sc);
				PecaXad pecaCapturada = parxad.fazerMovimentoXad(origem,alvo);
				if(pecaCapturada!=null) {
					capturadas.add(pecaCapturada);
				}
				if(parxad.getPromovido()!=null) {
					System.out.print("Digite a peça para qual deseja promover(T/B/C/H): ");
					String tipo = sc.nextLine();
					parxad.trocarPecaPromovida(tipo.toUpperCase());
				}
			}catch(XadException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.limparTela();
		UI.printPartida(parxad, capturadas);
	}
}