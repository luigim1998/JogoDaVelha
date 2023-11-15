package com.luigim1998.JogoDaVelha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import jogo.Jogada;
import jogo.PecaEnum;
import jogo.Tabuleiro;

public class App {
	
	public static void main(String[] args) {
		rodarJogo();
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
					if(novaJogada != null) {
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
		t1.setPeca(0, 1, PecaEnum.X);
		t1.setPeca(1, 2, PecaEnum.O);
		Jogada j1 = new Jogada(t1, PecaEnum.O);
		
		List<Jogada> j = new ArrayList<Jogada>();
		j.add(j1);
		j.addAll(j1.getJogadasProximas());
		
		for (Jogada jogo : j1.getJogadasProximas()) {
				j.addAll(jogo.getJogadasProximas());
		}
		
		List<Jogada> jX = j.stream().filter(jog -> jog.getJogadorAtual() == PecaEnum.X).collect(Collectors.toList());
		List<Jogada> jO = j.stream().filter(jog -> jog.getJogadorAtual() == PecaEnum.O).collect(Collectors.toList());;
		
		jX.sort(null);
		jO.sort(null);
		
		for (Jogada jogo : jX) {
			System.out.println("  " + jogo);
		}
		System.out.println();
		
		for (Jogada jogo : jO) {
			System.out.println("  " + jogo);
		}
	}
}