package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;


public class DatabaseJSonReader {

	ClaseCadenaMando siguiente;

	public DatabaseJSonReader(ClaseCadenaMando cm) {
		siguiente = cm;
	}

	public StringBuffer leeC(JsonReader jsr, String n) 
			throws IOException{
		return siguiente.leeC(jsr, n);
	}

	public String parse(String jsonFileName)
			throws IOException{
		InputStream userIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(userIS, "UTF-8"));
		reader.beginObject();
		StringBuffer readData = new StringBuffer();

		while (reader.hasNext()) {
			String name = reader.nextName();
			readData.append(name.toUpperCase()).append("\n").append(leeC(reader, name)).append("\n");
		}

		reader.endObject();
		reader.close();
		userIS.close();
		return new String(readData);
	}
}
