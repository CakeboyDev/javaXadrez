//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package aplicativo;
import java.util.InputMismatchException;
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
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
		while(true) {
			try {
				UI.limparTela();
				UI.printTab(parxad.getPecas());
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
			}catch(XadException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}