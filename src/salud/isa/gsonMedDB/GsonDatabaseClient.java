package salud.isa.gsonMedDB;

import java.io.FileNotFoundException;
import java.io.IOException;


public class GsonDatabaseClient {

	public static void main(String[] args) {
		try {
			Medicines mededicines = new Medicines(null);
			ActiveIngredients activeIngredients = new ActiveIngredients(mededicines);
			Physiotherapies physiotherapies = new Physiotherapies(activeIngredients);
			Inhalers inhalers = new Inhalers(physiotherapies);
			Posologies posologies  = new Posologies(inhalers);
			MedicinePresentations medicinePresentations = new MedicinePresentations(posologies );
			RescueMedicinePresentations rescueMedicinePresentations = new RescueMedicinePresentations(medicinePresentations);
			UserManualPhysioSteps userManualPhysioSteps = new UserManualPhysioSteps(rescueMedicinePresentations);
			UserManualSteps userManualSteps = new UserManualSteps(userManualPhysioSteps);
			DatabaseJSonReader databaseJSonReader = new DatabaseJSonReader(userManualSteps);
			try {
				System.out.println(databaseJSonReader.parse("./datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
