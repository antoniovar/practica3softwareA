package salud.isa.gsonMedDB;

import java.io.*;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class MedicinePresentations extends ClaseCadenaMando{

	private static final String MEDP_TAGNAME = "medicinePresentations";
	private static final String MEDREF_TAGNAME = "medicineRef";
	private static final String ACTINGREF_TAGNAME = "activeIngRef";
	private static final String INHREF_TAGNAME = "inhalerRef";
	private static final String DOSE_TAGNAME = "dose";
	private static final String POSREF_TAGNAME = "posologyRef";
	private static final String SEP = ";";

	public MedicinePresentations(ClaseCadenaMando siguiente) {
		super(siguiente);
	}

	public StringBuffer leeC(JsonReader jsr, String n) throws IOException {
		if (n.equals(MEDP_TAGNAME)) {
			return super.comun(jsr, n);
		}

		else {
			if (siguiente != null) {
				return super.leeC(jsr, n);
			} else {
				jsr.skipValue();
				System.err.println("Error en la categoria");
				return new StringBuffer("");
			}
		}
	}

	private boolean isArray(JsonReader jsr) throws IOException {
		Boolean esArray = false;
		if (jsr.peek() == JsonToken.BEGIN_ARRAY) {
			esArray = true;
		}
		return esArray;
	}

	public String leeE(JsonReader jsr) throws IOException {
		String med_ref = null;
		String ai_ref = null;
		String in_ref = "";
		String dose = "";
		String p_ref = "";
		while (jsr.hasNext()) {
			String name = jsr.nextName();
			if (name.equals(MEDREF_TAGNAME)) {
				med_ref = jsr.nextString();
			} else if (name.equals(ACTINGREF_TAGNAME)) {
				ai_ref = jsr.nextString();
			} else if (name.equals(INHREF_TAGNAME)) {
				if (!isArray(jsr)) {
					in_ref = jsr.nextString();
				} else if (isArray(jsr)) {
					jsr.beginArray();
					while (jsr.hasNext()) {
						in_ref = in_ref + jsr.nextString() + ", ";
					}
					jsr.endArray();
					in_ref = in_ref.substring(0, in_ref.length() - 2);
				} else {
					in_ref = "ERROR";
				}
			} else if (name.equals(DOSE_TAGNAME)) {
				if (!isArray(jsr)) {
					dose = jsr.nextString();
				} else {
					jsr.beginArray();
					while (jsr.hasNext()) {
						dose = dose + "(" + jsr.nextString() + "), ";
					}
					jsr.endArray();
					dose = dose.substring(0, dose.length() - 2);
				}
			} else if (name.equals(POSREF_TAGNAME)) {
				if (!isArray(jsr)) {
					p_ref = jsr.nextString();
				} else {
					jsr.beginArray();
					while (jsr.hasNext()) {
						p_ref = p_ref + jsr.nextString() + ", ";
					}
					jsr.endArray();
					p_ref = p_ref.substring(0, p_ref.length() - 2);
				}
			} else {
				jsr.skipValue();
			}
		}
		return med_ref + SEP + ai_ref + SEP + in_ref + SEP + dose + SEP + p_ref;
	}	
}


