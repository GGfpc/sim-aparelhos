package Projecto;

public class LampadaVariavel extends AparelhoPotenciaVariavel {
	
	public LampadaVariavel(String id, double potencia) {
		super(id, potencia);
	}
	
	@Override
	public void liga() {
		super.liga();
		aumentoRandom();
	}

}
