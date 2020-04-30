package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Inhalers extends ClaseCadenaMando{

	private static final String I_TAGNAME = "inhalers";
	private static final String NAME_TAGNAME = "name";
	private static final String IMAGE_TAGNAME = "image";
	private static final String FIELD_SEP = ";";

	public Inhalers(ClaseCadenaMando siguiente) {
		super(siguiente);

	}

	public StringBuffer leeC(JsonReader jsr, String n) 
			throws IOException {
		if(n.equals(I_TAGNAME)) {
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
		String inha_name = null;
		String inha_ima = null;
		while(jsr.hasNext()) {
			String name = jsr.nextName();
			if(name.equals(NAME_TAGNAME)) {
				inha_name = jsr.nextString();
			}
			else if(name.equals(IMAGE_TAGNAME)) {
				inha_ima = jsr.nextString();
			}
			else {
				jsr.skipValue();
			}
		}

		return inha_name + FIELD_SEP + inha_ima;
	}

}
