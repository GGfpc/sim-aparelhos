package Projecto;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FabricaDeAparelhos {

	public Aparelho novoAparelho(JSONObject obj) {

		String tipo = (String) obj.get("tipo");
		String id = (String) obj.get("id");
		int duracao = 0;
		double potencia = 0;
		int nTomadas = 0;

		if (obj.get("potencia") != null) {
			potencia = (double) obj.get("potencia");
		}

		if (obj.get("potenciaMaxima") != null) {
			potencia = (double) obj.get("potenciaMaxima");
		}

		if (obj.get("nTomadas") != null) {
			nTomadas = (int) (long) obj.get("nTomadas");

		}
		if (tipo.equals("computador")) {
			return new Computador(id, potencia);
		}
		if (tipo.equals("lampada")) {
			return new Lampada(id, potencia);
		}
		if (tipo.equals("microOndas")) {
			return new MicroOndas(id, potencia);
		}
		if (tipo.equals("frigorifico")) {
			return new Frigorifico(id, potencia);
		}
		if (tipo.equals("torradeira")) {
			return new Torradeira(id, potencia);
		}
		if (tipo.equals("tripla")) {
			return new Tripla(id, potencia, nTomadas);
		}
		if (tipo.equals("lampadaVariavel")) {
			return new LampadaVariavel(id, potencia);
		}
		if (tipo.equals("maqLavarRoupa")) {

			JSONArray progArray = (JSONArray) obj.get("programas");
			ArrayList<Programa> listaDeProgramas = new ArrayList<Programa>();

			for (int j = 0; j < progArray.size(); j++) {

				JSONObject prog = (JSONObject) progArray.get(j);

				String idprog = (String) prog.get("id");

				ArrayList<Ciclo> listaDeCiclos = new ArrayList<Ciclo>();
				Programa programa = new Programa(idprog, listaDeCiclos);
				listaDeProgramas.add(programa);

				JSONArray cicArray = (JSONArray) prog.get("ciclos");

				for (int k = 0; k < cicArray.size(); k++) {

					JSONObject cic = (JSONObject) cicArray.get(k);

					String idcic = (String) cic.get("id");
					int duracaocic = (int) (long) cic.get("duracao");
					double potenciacic = (double) cic.get("potencia");

					Ciclo ciclo = new Ciclo(duracaocic, potenciacic);
					listaDeCiclos.add(ciclo);
				}
			}

			return new MaquinaDeLavar(id, potencia, listaDeProgramas);
		}

		return null;
	}

}
