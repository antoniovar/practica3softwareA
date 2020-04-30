package salud.isa.gsonMedDB;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class ActiveIngredients extends ClaseCadenaMando{

	private static final String AI_TAGNAME = "activeIngredients";
	private static final String NAME_TAGNAME = "name";

	public ActiveIngredients(ClaseCadenaMando siguiente) {
		super(siguiente);
	}

	public StringBuffer leeC(JsonReader jsr, String n)
			throws IOException{
		if(n.equals(AI_TAGNAME)) {
			return super.comun(jsr, n);
		}
		else {
			if(siguiente != null) {
				return super.leeC(jsr, n);
			}
			else {
				jsr.skipValue();
				System.err.println("Error en la categoria");
				return new StringBuffer("");
			}
		}
	}

	public String leeE(JsonReader jsr) 
			throws IOException {
		String acting_name = null;
		while (jsr.hasNext()) {
			String name = jsr.nextName();
			if(name.equals(NAME_TAGNAME)) {
				acting_name = jsr.nextString();
			}
			else {
				jsr.skipValue();
			}
		}

		return acting_name;
	}
}
