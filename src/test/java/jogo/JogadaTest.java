package jogo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JogadaTest {

	@Test
	void testPrimeiraJogada() {
		Jogada jogo = new Jogada(PecaEnum.O);
		Jogada proximaPartida = jogo.getJogadasProximas().get(0);
		assertEquals(PecaEnum.O, proximaPartida.getTabuleiro().getPeca(0, 0));
	}

	@Test
	void testVerificarVitoria() {
		Tabuleiro t1 = new Tabuleiro();
		t1.setPeca(0, 0, PecaEnum.X);
		t1.setPeca(0, 1, PecaEnum.X);
		t1.setPeca(0, 2, PecaEnum.X);
		t1.setPeca(1, 1, PecaEnum.O);
		t1.setPeca(2, 2, PecaEnum.O);
		Tabuleiro t2 = new Tabuleiro();
		t2.setPeca(0, 1, PecaEnum.O);
		t2.setPeca(1, 1, PecaEnum.O);
		t2.setPeca(2, 1, PecaEnum.O);
		t2.setPeca(1, 0, PecaEnum.X);
		t2.setPeca(2, 2, PecaEnum.X);
		Tabuleiro t3 = new Tabuleiro();
		t3.setPeca(0, 0, PecaEnum.O);
		t3.setPeca(1, 1, PecaEnum.O);
		t3.setPeca(2, 2, PecaEnum.O);
		t3.setPeca(0, 2, PecaEnum.X);
		t3.setPeca(2, 0, PecaEnum.X);
		Tabuleiro t4 = new Tabuleiro();
		t4.setPeca(0, 0, PecaEnum.O);
		t4.setPeca(2, 2, PecaEnum.O);
		t4.setPeca(0, 2, PecaEnum.X);
		t4.setPeca(1, 1, PecaEnum.X);
		t4.setPeca(2, 0, PecaEnum.X);
		assertAll(() -> assertEquals(Jogada.verificarVitoria(t1), VencedorEnum.X),
				() -> assertEquals(Jogada.verificarVitoria(t2), VencedorEnum.O),
				() -> assertEquals(Jogada.verificarVitoria(t3), VencedorEnum.O),
				() -> assertEquals(Jogada.verificarVitoria(t4), VencedorEnum.X));
	}
}
