package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioSteps extends ClaseCadenaMando{

	private static final String UMPS_TAGNAME = "userManualPhysioSteps";
	private static final String STEPTITLE_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_TAGNAME = "stepImage";
	private static final String STEPTEXT_TAGNAME = "stepText";
	private static final String PHYREF_TAGNAME = "physioRef";
	private static final String SEPARATOR = ";";

	public UserManualPhysioSteps(ClaseCadenaMando siguiente) {
		super(siguiente);
	}

	public StringBuffer leeC(JsonReader jsr, String n) throws IOException {
		if (n.equals(UMPS_TAGNAME)) {
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

	public String leeE(JsonReader jsr) throws IOException {
		String s_title = null;
		String s_ima = null;
		String s_text = null;
		String phisio_ref = null;
		while (jsr.hasNext()) {
			String name = jsr.nextName();
			if (name.equals(STEPTITLE_TAGNAME)) {
				s_title = jsr.nextString();
			} else if (name.equals(STEPIMAGE_TAGNAME)) {
				s_ima = jsr.nextString();
			} else if (name.equals(STEPTEXT_TAGNAME)) {
				s_text = jsr.nextString();
			} else if (name.equals(PHYREF_TAGNAME)) {
				phisio_ref = jsr.nextString();
			} else {
				jsr.skipValue();
			}
		}
		return s_title + SEPARATOR + s_ima + SEPARATOR + s_text + SEPARATOR + phisio_ref;
	}

}
