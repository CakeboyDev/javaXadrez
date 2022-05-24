//IMPORTAÇÕES------------------------------------------------------------------------------------------------------------------------
package aplicativo;
import java.util.Scanner;

import chess.PartidaXad;
import chess.PecaXad;
import chess.PosicXad;
//-----------------------------------------------------------------------------------------------------------------------------------
public class Programa {

	public static void main(String[] args) {
//VARIÁVEIS E LISTAS-----------------------------------------------------------------------------------------------------------------		
		Scanner sc = new Scanner(System.in);
		PartidaXad parxad = new PartidaXad();
//FUNÇÕES----------------------------------------------------------------------------------------------------------------------------
		while(true) {
			UI.printTab(parxad.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			PosicXad origem = UI.lerPosicXad(sc);
			System.out.println();
			System.out.print("Alvo: ");
			PosicXad alvo = UI.lerPosicXad(sc);
			PecaXad pecaCapturada = parxad.fazerMovimentoXad(origem,alvo);
		}
	}
}