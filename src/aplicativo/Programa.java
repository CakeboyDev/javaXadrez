//IMPORTA��ES------------------------------------------------------------------------------------------------------------------------
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
//VARI�VEIS E LISTAS-----------------------------------------------------------------------------------------------------------------		
		Scanner sc = new Scanner(System.in);
		PartidaXad parxad = new PartidaXad();
		List<PecaXad> capturadas = new ArrayList<>();
//FUN��ES----------------------------------------------------------------------------------------------------------------------------
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
					System.out.print("Digite a pe�a para qual deseja promover(T/B/C/H): ");
					String tipo = sc.nextLine().toUpperCase();
					while(!tipo.equals("H")&&!tipo.equals("T")&&!tipo.equals("B")&&!tipo.equals("C")) {
						System.out.print("Valor inv�lido! Digite a pe�a para qual deseja promover(T/B/C/H): ");
						tipo = sc.nextLine().toUpperCase();
					}
					parxad.trocarPecaPromovida(tipo);
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