package Projecto;

public class Relogio {

	private static Relogio instanciaUnica = null;
	private int horas = -1;


	private Relogio() {
		super();
	}

	public void tique() {
		horas++;
	}

	public int getTempoAtual() {
		return horas;
	}

	public static Relogio getInstanciaUnica() {
		if (instanciaUnica == null) {
			instanciaUnica = new Relogio();
		}
		return instanciaUnica;
	}
	
	

}
