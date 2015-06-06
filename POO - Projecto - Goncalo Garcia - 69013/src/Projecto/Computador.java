package Projecto;

public class Computador extends AparelhoPotenciaVariavel {

	public Computador(String id, double potencia) {
		super(id, potencia);
		
	}

	@Override
	public double potenciaAtual() {
		if(estaLigado()){
			return aumentoRandom();
		}
		return 0;
	}

}
