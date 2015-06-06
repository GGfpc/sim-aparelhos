package Projecto;

import java.util.ArrayList;
import java.util.Observable;

public class Linha {

	private ArrayList<Tomada> tomadas = new ArrayList<Tomada>();
	private String divisao;
	private int numTomadas;

	Linha(String divisao, int numTomadas) {
		super();
		this.divisao = divisao;
		this.numTomadas = numTomadas;
		for (int i = 0; i < numTomadas; i++) {
			Tomada novaTomada = new Tomada();
			novaTomada.setId("linha");
			tomadas.add(novaTomada);
		}
	}

	public String getDivisao() {
		return divisao;
	}

	public int getNumTomadas() {
		return numTomadas;
	}

	public boolean temTomadasLivres() {
		boolean tomadaLivre = false;
		for (Tomada tomada : tomadas) {
			if (tomada != null && tomada.isLivre()) {
				tomadaLivre = true;
			}
		}
		return tomadaLivre;
	}

	public void adicionaTomadas(Tomada tomada) {
		tomadas.add(tomada);

	}
			

	public double potenciaNaLinha() {
		double potencia = 0;
		for (Tomada tomada : tomadas) {
				potencia += tomada.getPotencia();
		}
		return potencia;
	}

	public Aparelho getAparelho(String aparelho) {
		for (Tomada tomada : tomadas) {
			if (tomada != null && aparelho != null && tomada.getLigado() != null && aparelho.equals(tomada.getLigado().getId())) {
				return (Aparelho) tomada.getLigado();
			}
		}
		return null;
	}

	public Tomada getTomadaLivre() {
		for (Tomada tomada : tomadas) {
			if (tomada != null && tomada.isLivre()) {
				return tomada;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return getDivisao() + " " + numTomadas;
	}

}
