package salud.isa.gsonMedDB;

import java.io.*;

import com.google.gson.stream.JsonReader;

public class Medicines extends ClaseCadenaMando{

	private static final String MED_TAGNAME = "medicines";
	private static final String NAME_TAGNAME = "name";

	public Medicines(ClaseCadenaMando siguiente) {
		super(siguiente);
	}

	
	public StringBuffer leeC(JsonReader jsr, String n) 
			throws IOException{
		if(n.equals(MED_TAGNAME)) {
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
			throws IOException{
		String med_name = null;
		while (jsr.hasNext()) {
			String name = jsr.nextName();
			if (name.equals(NAME_TAGNAME)) {
				med_name = jsr.nextString();
			}
			else {
				jsr.skipValue();
			}
		}

		return med_name;
	}

}
