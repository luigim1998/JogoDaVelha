package com.luigim1998.JogoDaVelha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import jogo.Jogada;
import jogo.PecaEnum;
import jogo.Tabuleiro;

public class UI {
	public static String imprimirPartida(Jogada jogada) {
		StringBuilder texto = new StringBuilder(40);
		Tabuleiro tab = jogada.getTabuleiro();

		for (int linha = 2; linha >= 0; linha--) {
			texto.append(linha);
			for (int peca = 0; peca <= 2; peca++) {
				texto.append(" ").append(imprimirPeca(tab.getPeca(linha, peca)));
			}
			texto.append("\n");
		}
		texto.append("  a b c");
		texto.append("\n");
		return texto.toString();
	}

	public static String imprimirTabuleiro(Jogada jogada, int indentacao) {
		StringBuilder texto = new StringBuilder(40);
		Tabuleiro tab = jogada.getTabuleiro();

		for (int linha = 0; linha < tab.getMatriz().length; linha++) {
			texto.append(" ".repeat(indentacao));
			for (int peca = 0; peca < tab.getMatriz()[linha].length; peca++) {
				texto.append(imprimirPeca(tab.getPeca(linha, peca)));
			}
			texto.append("\n");
		}

		return texto.toString();
	}

	public static String imprimirTabuleiroLinhaUnica(Jogada jogada, int indentacao) {
		StringJoiner texto = new StringJoiner(",");
		Tabuleiro tab = jogada.getTabuleiro();
		List<StringBuilder> linhas = new ArrayList<StringBuilder>();

		for (int linha = 0; linha < tab.getMatriz().length; linha++) {
			linhas.add(new StringBuilder(3));
			for (int peca = 0; peca < tab.getMatriz()[linha].length; peca++) {
				linhas.get(linha).append(imprimirPeca(tab.getPeca(linha, peca)));
			}
		}
		linhas.stream().map(t -> t.toString()).forEach(t -> texto.add(t));

		return " ".repeat(indentacao) + "[" + texto.toString() + "]";
	}

	public static String imprimirTabuleiroRecursivo(Jogada jogada, int indentacao) {
		StringBuilder texto = new StringBuilder();
		texto.append(" ".repeat(indentacao)).append("Jogador Atual: ").append(jogada.getJogadorAtual());
		texto.append("\n");
		texto.append(imprimirTabuleiro(jogada, indentacao));
		for (Jogada jogFilho : jogada.getJogadasProximas()) {
			texto.append(imprimirTabuleiroRecursivo(jogFilho, indentacao + 2));
		}

		return texto.toString();
	}

	public static String imprimirPeca(PecaEnum peca) {
		return peca == null ? "-" : peca.toString();
	}
	
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

	public static PecaEnum lerPecaEscolhida(Scanner sc) {
		while (true) {
			try {
				System.out.print("Digite 'X' para escolher X ou 'O' para escolher O: ");
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
