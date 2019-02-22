package dbutils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class RunSQLScript {

	public void runScript (EntityManagerFactory emf, String scriptFilename) throws FileNotFoundException, IOException, SQLException {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream(scriptFilename)))) {
		    String command;
		    int i = 1;
		    EntityManager em = emf.createEntityManager();
		    while ((command = br.readLine()) != null) {
		        System.out.println(i + ": " + command);
		        i++;
			    em.getTransaction().begin();
		        Query q = em.createNativeQuery(command.toString());
		        q.executeUpdate();
		        em.getTransaction().commit();
		    }
		    em.close();
		}
	}
	
}
