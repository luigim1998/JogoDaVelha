package com.luigim1998.JogoDaVelha.application;

import java.util.Scanner;

import com.luigim1998.JogoDaVelha.game.Jogada;
import com.luigim1998.JogoDaVelha.game.PecaEnum;
import com.luigim1998.JogoDaVelha.game.Tabuleiro;

/**
 * Classe com funções utilitárias para gerar texto.
 */
public class UI {

	/** Converte a entrada em um vetor de coordenadas [linha, coluna]. Retorna [-1, -1], caso a entrada seja inválida.
	 * 
	 * @param entrada Texto onde o primeiro caractere é o número da linha e depois  letra sendo a coluna (a=0, b=1, c=2).
	 * @return Par de coordenadas onde o primeiro elemento é a linha e o segundo é a coluna. Retorna [-1, -1], caso a entrada seja inválida.
	 */
	public static int[] converterEntradaCoord(String entrada) {
		int[] coord = new int[2];
		try {
			int linhaEscolhida = Integer.parseInt(entrada.substring(0, 1));
			int colunaEscolhida = entrada.charAt(1) - 'a';

			if (linhaEscolhida < 0 || linhaEscolhida >= 3 || colunaEscolhida < 0 || colunaEscolhida >= 3) {
				coord[0] = -1;
				coord[1] = -1;
			} else {
				coord[0] = linhaEscolhida;
				coord[1] = colunaEscolhida;
			}
		} catch (RuntimeException e) {
			coord[0] = -1;
			coord[1] = -1;
		}
		return coord;
	}

	/** Retorna PecaEnum.X caso o caractere seja 'x', retorna PecaEnum.O caso o caractere seja 'o' e null caso seja outro caractere.
	 * @param c Caractere de entrada.
	 * @return um enum PecaEnum ou null.
	 */
	public static PecaEnum converterEntradaPeca(char c) {
		switch (c) {
		case 'x':
			return PecaEnum.X;
		case 'o':
			return PecaEnum.O;
		default:
			return null;
		}
	}

	/** Imprime o tabuleiro da jogada no seguinte modelo:
	 * 2 X - O
	 * 1 - X O
	 * 0 - O X
	 *   a b c
	 * 
	 * @param jogada Jogada a ser gerada o texto da partida.
	 * @return Texto da partida.
	 */
	public static String imprimirPartida(Jogada jogada) {
		StringBuilder texto = new StringBuilder(40);
		Tabuleiro tab = jogada.getTabuleiro();

		for (int linha = 2; linha >= 0; linha--) {
			texto.append(linha);
			for (int peca = 0; peca <= 2; peca++) {
				texto.append(" ").append(tab.imprimirPeca(linha, peca));
			}
			texto.append("\n");
		}
		texto.append("  a b c");
		texto.append("\n");
		return texto.toString();
	}

	/** Imprime tabuleiro da jogada colocando identação no seguinte modelo:
	 * X O -
	 * O X O
	 * X - X
	 * @param jogada Jogada a ser gerada o texto do tabuleiro.
	 * @param indentacao Tamanho do espaço a ser colocado antes de cada linha.
	 * @return Texto do tabuleiro.
	 */
	public static String imprimirTabuleiro(Jogada jogada, int indentacao) {
		StringBuilder texto = new StringBuilder(40);
		Tabuleiro tab = jogada.getTabuleiro();

		for (int linha = 0; linha < tab.getMatriz().length; linha++) {
			texto.append(" ".repeat(indentacao));
			for (int peca = 0; peca < tab.getMatriz()[linha].length; peca++) {
				texto.append(tab.imprimirPeca(linha, peca));
			}
			texto.append("\n");
		}

		return texto.toString();
	}

	/** Solicita do usuário a coordenada e retorna as coordenadas com base no método  converterEntradaCoord, caso a entrada seja inválida, será solicatada nova entrada até obter entrada válida.
	 * @param sc Objeto Scanner.
	 * @return Par de coordenadas onde o primeiro elemento é a linha e o segundo é a coluna.
	 */
	public static int[] lerCoordenada(Scanner sc) {
		int[] coord = new int[2];
		while (true) {
			try {
				System.out.print("Digite a linha e coluna para colocar sua peça: ");
				String s = sc.nextLine();
				
				coord = converterEntradaCoord(s);

				if (coord[0] == -1 || coord[1] == -1) {
					System.out.println("Escolha inválida.");
				} else {
					break;
				}
			} catch (RuntimeException e) {
				System.out.println("Escolha inválida.");
			}
		}
		return coord;
	}

	/** Solicita do usuário uma peça e retorna a peça com base no método  converterEntradaPeca, caso a entrada seja inválida, será solicatada nova entrada até obter entrada válida.
	 * @param sc Objeto Scanner.
	 * @return Objeto PecaEnum.
	 */
	public static PecaEnum lerPecaEscolhida(Scanner sc) {
		while (true) {
			try {
				System.out.print("Digite 'x' para escolher X ou 'o' para escolher O: ");
				String s = sc.nextLine();
				PecaEnum escolha = converterEntradaPeca(s.charAt(0));
				if (escolha != null) {
					return escolha;
				}
			} catch (RuntimeException e) {
				System.out.println("Escolha inválida.");
			}
		}
	}

	/** Pergunta se o usuário quer jogar primeiro, True se for 's' ou False para 'n'.
	 * @param sc Objeto Scanner.
	 * @return True se for 's' ou False para 'n'.
	 */
	public static boolean usuarioJogaPrimeiro(Scanner sc) {
		boolean escolha = false;
		while (true) {
			try {
				System.out.print("Deseja jogar primeiro (s/n): ");
				String s = sc.nextLine();
				char escolhaChar = s.charAt(0);

				if (escolhaChar != 's' && escolhaChar != 'n') {
					System.out.println("Escolha inválida.");
					continue;
				} else {
					escolha = escolhaChar == 's' ? true : false;
					break;
				}
			} catch (RuntimeException e) {
				System.out.println("Escolha inválida.");
			}
		}

		return escolha;
	}
}
