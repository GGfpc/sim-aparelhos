package Projecto;

public class AparelhoPotenciaFixa extends Aparelho{

	
	
	AparelhoPotenciaFixa(String id, double potenciaMaxima) {
		super();
		super.setPotenciaMaxima(potenciaMaxima);
		super.setId(id);
	}
	
	@Override
	public void liga() {
		super.liga();
		super.setPotenciaAtual(super.potenciaMaxima());
	}
	
	@Override
	public void desliga() {
		super.desliga();
		super.setPotenciaAtual(0);
	}
	
	
	
	
	
	
	
}
