package principal;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Color;
import xadrez.XadrezPartida;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Visual {
	
	//metodos//
	
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static XadrezPosicao lerPosicao(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new XadrezPosicao(coluna, linha);
		}catch(RuntimeException e) {
			throw new InputMismatchException("Erro ao ler a posição do xadrez. Valores validos são de A1 até H8.");
		}
	}
	
	public static void printPartida(XadrezPartida partida, List<XadrezPeca> capturada) {
		printMesa(partida.getPeca());
		System.out.println();
		printPecaCapturada(capturada);
		System.out.println();
		System.out.println("Turno:" + partida.getTurno());
		
		if(!partida.getCheckMate()) {
		
		System.out.println("Esperando a jogada do jogador "+ partida.getJogadorAtual());
			if(partida.getCheck()) {
				System.out.println("CHECK!");
			}
		}else {
			System.out.println("CHECKMATE!");
			System.out.println("Ganhador: "+ partida.getJogadorAtual());
		}
	}
	
	public static void printMesa(XadrezPeca[][] pecas){
		for(int i=0;i < pecas.length;i++){
			System.out.print((8-i) + " ");
			for(int j=0;j < pecas.length;j++){
				printPeca(pecas[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");	
	}
	
	public static void printMesa(XadrezPeca[][] pecas, boolean[][] movimentosPossiveis){
		for(int i=0;i < pecas.length;i++){
			System.out.print((8-i) + " ");
			for(int j=0;j < pecas.length;j++){
				printPeca(pecas[i][j],movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");	
	}
	
	//https://stackoverflow.com/questions/29793383/java-clear-the-console
	public static void  limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	private static void printPeca(XadrezPeca peca, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
    	if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printPecaCapturada(List<XadrezPeca> capturada) {
		List<XadrezPeca> brancas = capturada.stream().filter(x -> x.getCor() == Color.WHITE).collect(Collectors.toList());
		List<XadrezPeca> pretas = capturada.stream().filter(z -> z.getCor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Pecas capturadas :");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.print(ANSI_RESET);	
	}
}
        
