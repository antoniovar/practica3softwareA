package salud.isa.gsonMedDB;

import java.io.*;
import com.google.gson.stream.JsonReader;

public class UserManualSteps extends ClaseCadenaMando{

	private static final String UMS_TAGNAME = "userManualSteps";
	private static final String STEPITLE_TAGNAME = "stepTitle";
	private static final String STEPIMAGE_TAGNAME = "stepImage";
	private static final String STEPTEXT_TAGNAME = "stepText";
	private static final String INHREF_TAGNAME = "inhalerRef";
	private static final String SEP = ";";

	public UserManualSteps(ClaseCadenaMando siguiente) {
		super(siguiente);
	}

	public StringBuffer leeC(JsonReader jsr, String n) throws IOException {
		if (n.equals(UMS_TAGNAME)) {
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
		String inh_ref = null;
		while (jsr.hasNext()) {
			String name = jsr.nextName();
			if (name.equals(STEPITLE_TAGNAME)) {
				s_title = jsr.nextString();
			} else if (name.equals(STEPIMAGE_TAGNAME)) {
				s_ima = jsr.nextString();
			} else if (name.equals(STEPTEXT_TAGNAME)) {
				s_text = jsr.nextString();
			} else if (name.equals(INHREF_TAGNAME)) {
				inh_ref = jsr.nextString();
			} else {
				jsr.skipValue();
			}
		}
		return s_title + SEP + s_ima + SEP + s_text + SEP + inh_ref;
	}
}
