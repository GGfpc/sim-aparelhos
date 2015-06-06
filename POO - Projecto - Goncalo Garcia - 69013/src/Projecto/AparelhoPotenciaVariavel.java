package Projecto;

import java.util.Random;

public class AparelhoPotenciaVariavel extends Aparelho {

	private double potenciaRegulada = 0;

	AparelhoPotenciaVariavel(String id, double potenciaMaxima) {
		super();
		super.setPotenciaMaxima(potenciaMaxima);
		super.setId(id);
	}

	@Override
	public void liga() {
		super.liga();
		super.setPotenciaAtual(potenciaRegulada);
	}

	@Override
	public void desliga() {
		super.desliga();
		super.setPotenciaAtual(0);
	}

	public void aumenta(int potencia) {
		if (potenciaRegulada + potencia >= potenciaMaxima()) {
			potenciaRegulada = potenciaMaxima();
			if (estaLigado()) {
				super.setPotenciaAtual(potenciaRegulada);
			}
			if (potenciaRegulada + potencia < 0) {
				potenciaRegulada = 0;
				if (estaLigado()) {
					super.setPotenciaAtual(potenciaRegulada);
				}
			}

		} else {
			potenciaRegulada += potencia;
			if (estaLigado()) {
				super.setPotenciaAtual(potenciaRegulada);
			}
		}
	}
	
	public int aumentoRandom(){
		Random rand = new Random();
		return rand.nextInt((int) potenciaMaxima()) + 1;
		
	}
}
