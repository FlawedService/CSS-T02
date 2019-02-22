package business;

import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.mappings.EmbeddableMapping;

public class EmployeeCatalog {
	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructs a Employee's catalog giving a entity manager factory
	 */
	public EmployeeCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Find  an Employee given its id
	 * 
	 * @param employee_id The employee Id to fetch from the repository
	 * @return The Employee object corresponding to the employee with the employee_id.
	 * @throws ApplicationException
	 */
	public Employee getEmployee(int id) throws ApplicationException {
		EntityManager em =  emf.createEntityManager();
		TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_BY_EMPLOYEE_ID, Employee.class);
		query.setParameter(Employee.ID, id);
		try{
			return query.getSingleResult();
		}catch(PersistenceException e){
			throw new ApplicationException("Employee not found", e);
		}finally {
			em.close();
		}
	}
	
	
	
	/**
	 * Adds a new Employee
	 * 
	 * @param employee_id The employee Identification number
	 * @param departmentId That the employee is associated with
	 * @throws ApplicationException 
	 */
	public void addEmployee (Department department) throws ApplicationException{
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Employee e = new Employee(department);
			em.persist(e);
			em.getTransaction().commit();
		}catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding employee", e);
		}finally {
			em.close();
		}
	}
	
	/**
	 * Removes a Employee
	 * 
	 * @param employee_id The employee Identification number
	 * @throws ApplicationException
	 */
	public void removeEmployeeById (Department department) throws ApplicationException{
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Employee e = em.find(Employee.class, department);
			em.remove(e);
			em.getTransaction().commit();
		}catch (Exception e){
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error removing employee", e);
		}finally {
			em.close();
		}
	}
	/**
	 * Gets all Employees of given department
	 * 
	 * @return a list of EMployees
	 * @throws ApplicationException
	 */
	public Iterable<Employee> getAllEmployees() throws ApplicationException{
		EntityManager em = emf.createEntityManager();
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
		return new LinkedList<>(query.getResultList());	
	}
}
