package com.luigim1998.JogoDaVelha.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.luigim1998.JogoDaVelha.game.Jogada;
import com.luigim1998.JogoDaVelha.game.PecaEnum;
import com.luigim1998.JogoDaVelha.game.Tabuleiro;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UITest {

Jogada jogada;
	
	@BeforeAll
	void init() throws Exception {
		PecaEnum[][] mat = new PecaEnum[3][3];
		mat[0][0] = PecaEnum.X;
		mat[0][1] = PecaEnum.O;
		mat[0][2] = PecaEnum.X;
		mat[1][0] = PecaEnum.O;
		mat[1][1] = PecaEnum.X;
		mat[1][2] = PecaEnum.O;
		Tabuleiro tab = new Tabuleiro();
		tab.setMatriz(mat);
		jogada = new Jogada(tab, PecaEnum.X);
	}

	@Test
	void testImprimirTabuleiro() {
		assertEquals("XOX\nOXO\n---\n", UI.imprimirTabuleiro(jogada, 0));
	}
	
	@Test
	void testImprimirPartida() {
		assertEquals("2 - - -\n1 O X O\n0 X O X\n  a b c\n", UI.imprimirPartida(jogada));
	}
	
	@Test
	void testImprimirTabuleiroIndentado() {
		assertEquals("  XOX\n  OXO\n  ---\n", UI.imprimirTabuleiro(jogada, 2));
	}

	@Test
	void testImprimirTabuleiroLinhaUnica() {
		assertEquals("[XOX,OXO,---]", UI.imprimirTabuleiroLinhaUnica(jogada, 0));
	}
	
	@Test
	void testImprimirTabuleiroLinhaUnicaIndentado() {
		assertEquals("  [XOX,OXO,---]", UI.imprimirTabuleiroLinhaUnica(jogada, 2));
	}

	@Test
	void testImprimirTabuleiroRecursivo() {
		assertDoesNotThrow(() -> UI.imprimirTabuleiroRecursivo(jogada, 0));
	}
	
	void testConverterEntradaPeca() {
		assertAll(
				() -> assertEquals(UI.converterEntradaPeca('x'), PecaEnum.X),
				() -> assertEquals(UI.converterEntradaPeca('o'), PecaEnum.O),
				() -> assertNull(UI.converterEntradaPeca('X')),
				() -> assertNull(UI.converterEntradaPeca('O')),
				() -> assertNull(UI.converterEntradaPeca('4')),
				() -> assertNull(UI.converterEntradaPeca('w'))
				);
	}

	@ParameterizedTest
	@CsvSource({"0a,0,0", "0c,0,2", "1b,1,1", "1a,1,0", "2b,2,1", "2a,2,0", "2c,2,2", "3a,-1,-1", "9u,-1,-1", "ae,-1,-1", "0a23,0,0", "2b8y,2,1"})
	void testCconverterEntrada(String entrada, int linha, int coluna) {
		int[] coord = UI.converterEntradaCoord(entrada);
		int[] expectedCoord = {linha, coluna};
		assertArrayEquals(coord, expectedCoord);
	}

}
