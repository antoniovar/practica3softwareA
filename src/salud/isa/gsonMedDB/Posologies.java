package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Posologies extends ClaseCadenaMando{

	private static final String P_TAGNAME = "posologies";
	private static final String DESCRIP_TAGNAME = "description";

	public Posologies(ClaseCadenaMando siguiente) {
		super(siguiente);		
	}

	public StringBuffer leeC(JsonReader jsr, String n)
			throws IOException{
		if (n.equals(P_TAGNAME)) {
			return super.comun(jsr, n);
		}
		else {
			if (siguiente != null) {
				return super.leeC(jsr, n);
			}
			else {
				jsr.skipValue();
				System.err.println("Error en la categoria");
				return new StringBuffer("");
			}
		}
	}

	public String leeE(JsonReader jsr) throws IOException {
		String poso_name = null;
		while(jsr.hasNext()) {
			String name = jsr.nextName();
			if (name.equals(DESCRIP_TAGNAME)) {
				poso_name = jsr.nextString();
			}
			else {
				jsr.skipValue();
			}
		}
		return poso_name;
	}

}
