package salud.isa.gsonMedDB;

import java.io.*;
import com.google.gson.stream.JsonReader;

public abstract class ClaseCadenaMando {

	protected ClaseCadenaMando siguiente;

	public ClaseCadenaMando(ClaseCadenaMando cm) {
		siguiente = cm;
	}

	public StringBuffer leeC(JsonReader jsr, String n)
			throws IOException{
		return siguiente.leeC(jsr, n);
	}

	public StringBuffer comun(JsonReader jsr, String n)
			throws IOException{
		StringBuffer data = new StringBuffer();
		jsr.beginArray();
		while(jsr.hasNext()) {
			jsr.beginObject();
			data.append(leeE(jsr)).append("\n");
			jsr.endObject();
		}

		data.append("\n");
		jsr.endArray();
		return data;
	}

	public abstract String leeE(JsonReader jsr) throws IOException;

}
