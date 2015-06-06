package Projecto;

import java.util.ArrayList;

public class Programa {

	private String id;
	private ArrayList<Ciclo> ciclos;
	private Ciclo cicloActual;
	private int tempo;

	Programa(String id, ArrayList<Ciclo> ciclos) {
		super();
		this.id = id;
		this.ciclos = ciclos;
	}

	public String getId() {
		return id;
	}

	public Ciclo getCicloActual() {
		return cicloActual;
	}

	public ArrayList<Ciclo> getCiclos() {
		return ciclos;
	}

	public double potenciaCicloActual() {
		if (cicloActual != null) {
			return cicloActual.getPotencia();
		}
		return 0;
	}

	public void iniciaProg() {
		cicloActual = ciclos.get(0);
		tempo = Relogio.getInstanciaUnica().getTempoAtual() + cicloActual.getDuracao();
	}

	public void trocaCiclo() {

		if (cicloActual != null && Relogio.getInstanciaUnica().getTempoAtual() == tempo) {

			if (ciclos.indexOf(cicloActual) + 1 < ciclos.size()) {
				int proximoCiclo = ciclos.indexOf(cicloActual) + 1;
				cicloActual = ciclos.get(proximoCiclo);
				tempo = Relogio.getInstanciaUnica().getTempoAtual() + cicloActual.getDuracao();
			} 
			else {
				cicloActual = null;
			}
		}

	}
}
