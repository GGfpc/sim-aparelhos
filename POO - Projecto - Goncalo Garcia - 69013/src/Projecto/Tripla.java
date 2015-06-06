package Projecto;

import java.util.ArrayList;

public class Tripla extends AparelhoPotenciaVariavel {

	private int numTomadas;
	private ArrayList<Tomada> tomadas = new ArrayList<Tomada>();

	Tripla(String id, double potenciaMaxima, int numTomadas) {
		super(id, potenciaMaxima);
		this.numTomadas = numTomadas;
		for(int i = 0; i <= numTomadas; i++){
			Tomada tomadatripla = new Tomada();
			tomadatripla.setId("tripla");
			tomadas.add(tomadatripla);
		}
	}
	
	public void adicionaTomadas(Linha linha) {
		for(Tomada tomada : tomadas){
			linha.adicionaTomadas(tomada);
		}

	}
	
	public int getNumTomadas() {
		return numTomadas;
	}




	@Override
	public String toString() {
		return super.toString() + "tom " + numTomadas;
	}

}
