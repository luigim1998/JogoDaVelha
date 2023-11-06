package jogo;

public enum PecaEnum {
	X,
	O;
	
	public static PecaEnum pecaOposta(PecaEnum peca) {
		return peca == PecaEnum.O ? PecaEnum.X : PecaEnum.O ;
	}
}
