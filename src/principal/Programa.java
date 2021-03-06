package principal;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import mesa.ExcecaoTabuleiro;
import xadrez.XadrezPartida;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		XadrezPartida partida = new XadrezPartida();
		List<XadrezPeca> capturada = new ArrayList<>();
		
		while(!partida.getCheckMate()) {	
			try {
				Visual.limparTela();
				Visual.printPartida(partida, capturada);
			
				System.out.println();
				System.out.println("Origem: ");
			
				XadrezPosicao origem = Visual.lerPosicao(sc);
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				Visual.limparTela();
				Visual.printMesa(partida.getPeca(),movimentosPossiveis);
				
				System.out.println();
				System.out.println("Destino: ");
			
				XadrezPosicao destino = Visual.lerPosicao(sc);	
				XadrezPeca pecaCapturada = partida.performanceMovimento(origem,destino);
				
				if(pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}		
				
				if(partida.getPromocao() != null) {
					System.out.print("Digite a pe�a para a promo��o (Q/B/N/R): ");
					String tipo = sc.nextLine().toUpperCase();
					while(!tipo.equals("B") && !tipo.equals("N") && !tipo.equals("R") && !tipo.equals("Q")) {
						System.out.print("Valor inv�lido! Digite a pe�a para a promo��o (Q/B/N/R): ");
						tipo = sc.nextLine().toUpperCase();
					}
					partida.RealocarPecaPromovida(tipo);
					
				}
				
			}catch(ExcecaoTabuleiro e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		Visual.limparTela();
		
		Visual.printPartida(partida, capturada);
	}
}