package dbutils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class ResetTables {

	public void resetCSSDerbyDB() throws FileNotFoundException, IOException, SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain-model-jpa");
		new RunSQLScript().runScript(emf, "META-INF/resetTables.sql");
		emf.close();		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		new ResetTables().resetCSSDerbyDB();
	}

}
