package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class RescueMedicinePresentations extends ClaseCadenaMando{

	private static final String RMP_TAGNAME = "rescueMedicinePresentations";
	private static final String MEDICINEREF_TAGNAME = "medicineRef";
	private static final String ACTIVEINGREDIENTS_TAGNAME = "activeIngRef";
	private static final String INHREF_TAGNAME = "inhalerRef";
	private static final String DOSE_TAGNAME = "dose";
	private static final String SEPARATOR = ";";

	public RescueMedicinePresentations(ClaseCadenaMando siguiente) {
		super(siguiente);
	}

	private boolean isArray(JsonReader jsr) throws IOException {
		Boolean esArray = false;

		if (jsr.peek() == JsonToken.BEGIN_ARRAY) {
			esArray = true;
		}
		return esArray;
	}

	public StringBuffer leeC(JsonReader jsr, String n) throws IOException {
		if (n.equals(RMP_TAGNAME)) {
			return super.comun(jsr, n);
		} else {
			if (siguiente != null) {
				return super.leeC(jsr, n);
			} else {
				jsr.skipValue();
				System.err.println("La categoria: '" + n + "' es incorrecta.");
				return new StringBuffer("");
			}
		}
	}

	public String leeE(JsonReader jsr) throws IOException {
		String m_ref = null;
		String ai_ref = null;
		String inh_ref = null;
		String dose = null;
		while (jsr.hasNext()) {
			String name = jsr.nextName();
			if (name.equals(MEDICINEREF_TAGNAME)) {
				m_ref = jsr.nextString();
			} else if (name.equals(ACTIVEINGREDIENTS_TAGNAME)) {
				ai_ref = jsr.nextString();
			} else if (name.equals(INHREF_TAGNAME)) {
				if(!isArray(jsr)) {
					inh_ref = jsr.nextName();
				}
				else if (isArray(jsr)) {
					jsr.beginArray();
					while (jsr.hasNext()) {
						inh_ref = inh_ref + jsr.nextString() + ",";
					}
					jsr.endArray();
				}
				
			} else if (name.equals(DOSE_TAGNAME)) {
				if(!isArray(jsr)) {
					dose = jsr.nextName();
				}
				else if (isArray(jsr)) {
					jsr.beginArray();
					while (jsr.hasNext()) {
						dose = dose + jsr.nextString() + ",";
					}
					jsr.endArray();
				}
			} else {
				jsr.skipValue();
			}
		}
		return m_ref + SEPARATOR + ai_ref + SEPARATOR + inh_ref + SEPARATOR + dose;
	}
}
