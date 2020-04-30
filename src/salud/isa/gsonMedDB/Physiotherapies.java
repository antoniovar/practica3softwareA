package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class Physiotherapies extends ClaseCadenaMando{

	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	private static final String FIELD_SEP = "name";

	public Physiotherapies (ClaseCadenaMando s) {
		super(s);
	}

	public StringBuffer leeC(JsonReader jsr, String n)
			throws IOException{
		if(n.equals(PHYSIOTHERAPIES_TAGNAME)) {
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
		String phisio_name = null;
		String phisio_ima = null;
		while (jsr.hasNext()) {
			String name = jsr.nextName();
			if(name.equals(NAME_FIELD_TAGNAME)) {
				phisio_name = jsr.nextString();
			}
			else if (name.equals(IMAGE_FIELD_TAGNAME)){
				phisio_ima = jsr.nextString();
			}
			else {
				jsr.skipValue();
			}
		}

		return phisio_name + FIELD_SEP + phisio_ima;
	}
}
