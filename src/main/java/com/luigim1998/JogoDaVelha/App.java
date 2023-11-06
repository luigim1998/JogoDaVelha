package com.luigim1998.JogoDaVelha;

import java.util.Scanner;

import jogo.Jogada;
import jogo.PecaEnum;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PecaEnum pecaJogador = UI.lerPecaEscolhida(sc);
		boolean usuarioJogaAgora = UI.usuarioJogaPrimeiro(sc);
		Jogada jogo = usuarioJogaAgora ? new Jogada(pecaJogador) : new Jogada(PecaEnum.pecaOposta(pecaJogador));
		
		while (!jogo.getJogadasProximas().isEmpty()) {
			UI.imprimirPartida(jogo);
			if (jogo.getJogadasProximas().get(0).getJogadorAtual() == pecaJogador) {
				while (true) {
					int[] coord = UI.lerCoordenada(sc);
					Jogada novaJogada = jogo.selecionarProximaJogada(coord[0], coord[1]);
					if(novaJogada != null) {
						jogo = novaJogada;
						break;
					}
				}
			} else {
				jogo = jogo.selecionarMelhorJogada();
			}
		}
		UI.imprimirPartida(jogo);
		System.out.println("Vitória de: " + jogo.getJogadorAtual());
	}
}
