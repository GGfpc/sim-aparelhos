package Projecto;

public class Tomada {

	private String id;
	private Ligavel ligado = null;
	private boolean Livre;

	Tomada() {
		super();
		Livre = true;
	}

	public boolean isLivre() {
		return Livre;
	}

	public void setLivre(boolean estaLivre) {
		this.Livre = estaLivre;
	}

	public Ligavel getLigado() {
		return ligado;
	}

	public void setLigado(Ligavel ligado) {
		this.ligado = ligado;
		Livre = false;
	}

	public double getPotencia() {
		if (ligado != null) {
			double potencia = ligado.potenciaAtual();
			return potencia;
		}
		return 0;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

}
