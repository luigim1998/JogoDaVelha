package com.luigim1998.JogoDaVelha.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JogadaTest {

	@Test
	void testPrimeiraJogada() {
		Jogada jogo = new Jogada(PecaEnum.O);
		jogo = jogo.getJogadasProximas().get(0);
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.O);
		assertEquals(jogo.getTabuleiro(), t1);
	}
	
	@Test
	void testPrimeiraJogadaComTabuleiro() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(1, 2, PecaEnum.O);
		Jogada j1 = new Jogada(t1, PecaEnum.X);
		j1 = j1.getJogadasProximas().get(0);
		
		Tabuleiro t2 = new Tabuleiro(t1);
		t2.setPeca(0, 0, PecaEnum.X);
		
		assertEquals(j1.getTabuleiro(), t2);
	}
	
	@Test
	void testSetTabuleiroPontoX() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.X);
		t1.setPeca(1, 0, PecaEnum.X);
		t1.setPeca(2, 0, PecaEnum.X);
		Jogada j1 = new Jogada(t1, PecaEnum.O);
		assertAll( () -> assertEquals(j1.getPontosO(), 0),
				() -> assertEquals(j1.getPontosX(), 1),
				() -> assertEquals(j1.getPontosEmpate(), 0)
				);
	}
	
	@Test
	void testSetTabuleiroPontoO() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.O);
		t1.setPeca(0, 1, PecaEnum.O);
		t1.setPeca(0, 2, PecaEnum.O);
		Jogada j1 = new Jogada(t1, PecaEnum.O);
		assertAll( () -> assertEquals(j1.getPontosO(), 1),
				() -> assertEquals(j1.getPontosX(), 0),
				() -> assertEquals(j1.getPontosEmpate(), 0)
				);
	}
	@Test
	void testSetTabuleiroPontoEmpate() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.O);//XOX
		t1.setPeca(0, 1, PecaEnum.X);//XOX
		t1.setPeca(0, 2, PecaEnum.O);//OXO
		t1.setPeca(1, 0, PecaEnum.X);
		t1.setPeca(1, 1, PecaEnum.O);
		t1.setPeca(1, 2, PecaEnum.X);
		t1.setPeca(2, 0, PecaEnum.X);
		t1.setPeca(2, 1, PecaEnum.O);
		t1.setPeca(2, 2, PecaEnum.X);
		Jogada j1 = new Jogada(t1, PecaEnum.O);
		assertAll( () -> assertEquals(j1.getPontosO(), 0),
				() -> assertEquals(j1.getPontosX(), 0),
				() -> assertEquals(j1.getPontosEmpate(), 1)
				);
	}
	
	@Test
	void testSelecionarMelhorJogada() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.X);
		Jogada j1 = new Jogada(t1, PecaEnum.O);
		System.out.println(j1);
		System.out.println();
		
		Tabuleiro t2 = new Tabuleiro();
		t2.setPeca(0, 0, PecaEnum.X).setPeca(1, 1, PecaEnum.O);
		
		System.out.println(j1.getJogadasProximas());
		System.out.println();
		assertEquals(t2, j1.selecionarMelhorJogada().getTabuleiro());
	}
	
	@Test
	void testSelecionarMelhorJogada2() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 1, PecaEnum.X).setPeca(1, 2, PecaEnum.O);
		Jogada j1 = new Jogada(t1, PecaEnum.O);
		
		Tabuleiro t2 = new Tabuleiro(t1);
		t2.setPeca(1, 1, PecaEnum.O);
		
		System.out.println(j1.getJogadasProximas().toString());
		System.out.println();
		assertEquals(t2, j1.selecionarMelhorJogada().getTabuleiro());
	}

	@Test
	void testVerificarVitoria() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.X).setPeca(0, 1, PecaEnum.X).setPeca(0, 2, PecaEnum.X);
		t1.setPeca(1, 1, PecaEnum.O);
		t1.setPeca(2, 2, PecaEnum.O);
		Tabuleiro t2 = new Tabuleiro();
		t2.setPeca(0, 1, PecaEnum.O).setPeca(1, 1, PecaEnum.O);
		t2.setPeca(1, 0, PecaEnum.X);
		t2.setPeca(2, 1, PecaEnum.O).setPeca(2, 2, PecaEnum.X);
		Tabuleiro t3 = new Tabuleiro();
		t3.setPeca(0, 0, PecaEnum.O).setPeca(0, 2, PecaEnum.X);
		t3.setPeca(1, 1, PecaEnum.O);
		t3.setPeca(2, 0, PecaEnum.X).setPeca(2, 2, PecaEnum.O);
		Tabuleiro t4 = new Tabuleiro();
		t4.setPeca(0, 0, PecaEnum.O).setPeca(0, 2, PecaEnum.X);
		t4.setPeca(1, 1, PecaEnum.X);
		t4.setPeca(2, 0, PecaEnum.X).setPeca(2, 2, PecaEnum.O);
		Tabuleiro t5 = new Tabuleiro();
		t5.setPeca(0, 0, PecaEnum.O).setPeca(0, 2, PecaEnum.X);
		t5.setPeca(1, 2, PecaEnum.X);
		t5.setPeca(2, 0, PecaEnum.X).setPeca(2, 2, PecaEnum.O);
		Tabuleiro t6 = new Tabuleiro();
		t6.setPeca(0, 0, PecaEnum.O).setPeca(0, 1, PecaEnum.X).setPeca(0, 2, PecaEnum.O);//OXO
		t6.setPeca(1, 0, PecaEnum.X).setPeca(1, 1, PecaEnum.O).setPeca(1, 2, PecaEnum.X);
		t6.setPeca(2, 0, PecaEnum.X).setPeca(2, 1, PecaEnum.O).setPeca(2, 2, PecaEnum.X);
		Tabuleiro t7 = new Tabuleiro();
		t7.setPeca(0, 0, PecaEnum.X);
		t7.setPeca(1, 0, PecaEnum.X);
		t7.setPeca(2, 0, PecaEnum.X);
		assertAll(() -> assertEquals(Jogada.verificarVitoria(t1), VencedorEnum.X),
				() -> assertEquals(Jogada.verificarVitoria(t2), VencedorEnum.O),
				() -> assertEquals(Jogada.verificarVitoria(t3), VencedorEnum.O),
				() -> assertEquals(Jogada.verificarVitoria(t4), VencedorEnum.X),
				() -> assertEquals(Jogada.verificarVitoria(t5), VencedorEnum.NAO_FINALIZADO),
				() -> assertEquals(Jogada.verificarVitoria(t6), VencedorEnum.EMPATE),
				() -> assertEquals(Jogada.verificarVitoria(t7), VencedorEnum.X)
				);
	}
	
	@Test
	void testCompararResultados() {
		Tabuleiro t1 = new Tabuleiro(); // [-X-,--O,-O-], jogadorAtual=O, pontosEmpate=144, pontosO=304, pontosX=168, Vitória O: 0,49, Vitória X: 0,27
		t1.setPeca(0, 1, PecaEnum.X).setPeca(1, 2, PecaEnum.O).setPeca(2, 1, PecaEnum.O);
		Jogada j1O = new Jogada(t1, PecaEnum.X);
		
		Tabuleiro t2 = new Tabuleiro(); // [-X-,--O,--O], jogadorAtual=O, pontosEmpate=0, pontosO=243, pontosX=174, Vitória O: 0,58, Vitória X: 0,42
		t2.setPeca(0, 1, PecaEnum.X).setPeca(1, 2, PecaEnum.O).setPeca(2, 2, PecaEnum.O);
		Jogada j2O = new Jogada(t2, PecaEnum.X);
		
		
		Tabuleiro t3 = new Tabuleiro(); // [-XX,O-O,---], jogadorAtual=X, pontosEmpate=0, pontosO=32, pontosX=35, Vitória O: 0,48, Vitória X: 0,52
		t3.setPeca(0, 1, PecaEnum.X).setPeca(0, 2, PecaEnum.X).setPeca(1, 0, PecaEnum.O).setPeca(1, 2, PecaEnum.O);
		Jogada j1X = new Jogada(t3, PecaEnum.O);
		
		Tabuleiro t4 = new Tabuleiro(); // [-X-,OXO,---], jogadorAtual=X, pontosEmpate=12, pontosO=24, pontosX=56, Vitória O: 0,26, Vitória X: 0,61
		t4.setPeca(0, 1, PecaEnum.X).setPeca(1, 0, PecaEnum.O).setPeca(1, 1, PecaEnum.X).setPeca(1, 2, PecaEnum.O);
		Jogada j2X = new Jogada(t4, PecaEnum.O);
		
		
		assertAll( () -> assertEquals(j1O.compareTo(j2O), -1),
				() -> assertEquals(j2O.compareTo(j1O), 1),
				() -> assertEquals(j1X.compareTo(j2X), -1),
				() -> assertEquals(j2X.compareTo(j1X), 1),
				() -> assertEquals(j1O.compareTo(j1O), 0),
				() -> assertEquals(j2X.compareTo(j2X), 0));
	}
	
	@Test
	void testSelecionarProximaJogada() {
		Tabuleiro t1 = new Tabuleiro(); // [-X-,--O,O--], jogadorAtual=O
		t1.setPeca(0, 1, PecaEnum.X).setPeca(1, 2, PecaEnum.O).setPeca(2, 0, PecaEnum.O);
		Jogada j1 = new Jogada(t1, PecaEnum.X);
		
		Tabuleiro t2 = new Tabuleiro(t1);
		t2.setPeca(0, 2, PecaEnum.X);
		assertAll(
				() -> assertEquals(t2, j1.selecionarProximaJogada(0, 2).getTabuleiro()),
				() -> assertNull(j1.selecionarProximaJogada(2, 0))
				);
	}
}
