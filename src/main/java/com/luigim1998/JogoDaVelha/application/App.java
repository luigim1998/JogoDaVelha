package com.luigim1998.JogoDaVelha.application;

import java.util.Scanner;

import com.luigim1998.JogoDaVelha.game.Jogada;
import com.luigim1998.JogoDaVelha.game.PecaEnum;
import com.luigim1998.JogoDaVelha.game.Tabuleiro;

public class App {

	public static void main(String[] args) {
		rodarJogo();
		// testInMain();
	}

	public static void rodarJogo() {
		Scanner sc = new Scanner(System.in);
		PecaEnum pecaJogador = UI.lerPecaEscolhida(sc);
		boolean usuarioJogaAgora = UI.usuarioJogaPrimeiro(sc);
		Jogada jogo = usuarioJogaAgora ? new Jogada(pecaJogador) : new Jogada(PecaEnum.pecaOposta(pecaJogador));

		while (!jogo.getJogadasProximas().isEmpty()) {
			System.out.println(UI.imprimirPartida(jogo));
			jogo.getJogadasProximas().stream().forEach((j) -> System.out.println(j));
			if (jogo.getJogadasProximas().get(0).getJogadorAtual() == pecaJogador) {
				while (true) {
					int[] coord = UI.lerCoordenada(sc);
					Jogada novaJogada = jogo.selecionarProximaJogada(coord[0], coord[1]);
					if (novaJogada != null) {
						jogo = novaJogada;
						break;
					}
				}
			} else {
				jogo = jogo.selecionarMelhorJogada();
			}
		}
		System.out.println(UI.imprimirPartida(jogo));
		System.out.println("Vitória de: " + jogo.getVencedor());
	}

	public static void testInMain() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.X);
		Jogada j1 = new Jogada(t1, PecaEnum.O);
		System.out.println(j1);
		System.out.println();

		Tabuleiro t2 = new Tabuleiro();
		t2.setPeca(0, 0, PecaEnum.X).setPeca(1, 1, PecaEnum.O);

		j1.getJogadasProximas().stream().forEach((j) -> System.out.println(j));
	}
}