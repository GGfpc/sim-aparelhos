package Projecto;

public class Evento {

	int tempo;
	int valor;
	String programa;
	String accao;
	String id;

	Evento(int tempo, int valor, String programa, String accao, String id) {
		super();
		this.tempo = tempo;
		this.valor = valor;
		this.programa = programa;
		this.accao = accao;
		this.id = id;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public void selecEvento() {

		Instalacao instal = Instalacao.getInstanciaUnica();
		if (instal.getAparelho(id) != null && Relogio.getInstanciaUnica().getTempoAtual() == tempo) {

			if (accao.equals("LIGA")) {
				instal.getAparelho(id).liga();

			}
			if (accao.equals("DESLIGA")) {
				instal.getAparelho(id).desliga();

			}
			if (accao.equals("AUMENTA")) {
				AparelhoPotenciaVariavel aparelho = (AparelhoPotenciaVariavel) instal.getAparelho(id);
				aparelho.aumenta(valor);
			}
			if (accao.equals("DIMINUI")) {
				AparelhoPotenciaVariavel aparelho = (AparelhoPotenciaVariavel) instal.getAparelho(id);
				aparelho.aumenta(-valor);

			}
			if (accao.equals("PROGRAMA")) {
				MaquinaDeLavar maquina = (MaquinaDeLavar) instal.getAparelho(id);
				maquina.setProgActual(programa);

			}
		}
	}

	@Override
	public String toString() {
		return accao + " " + id;
	}

}
