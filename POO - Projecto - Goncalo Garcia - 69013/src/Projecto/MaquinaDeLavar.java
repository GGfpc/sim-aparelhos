package Projecto;

import java.util.ArrayList;

public class MaquinaDeLavar extends AparelhoPotenciaVariavel {

	private ArrayList<Programa> programas;
	private Programa programaActual = null;

	MaquinaDeLavar(String id, double potenciaMaxima, ArrayList<Programa> programas) {
		super(id, potenciaMaxima);
		this.programas = programas;
	}

	public void setProgActual(String progActual) {
		for (Programa programa : programas) {
			if (programa.getId().equals(progActual)) {
				programaActual = programa;

			}
		}

	}

	public Programa getProgramaActual() {
		return programaActual;
	}

	@Override
	public double potenciaAtual() {
		if (programaActual != null) {
			programaActual.trocaCiclo();
			return programaActual.potenciaCicloActual();
		}
		return 0;
	}

	@Override
	public void liga() {
		super.liga();
		if (programaActual != null) {
			programaActual.iniciaProg();
		}
	}
}
