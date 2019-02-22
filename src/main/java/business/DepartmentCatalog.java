package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 * A catalog of employees
 * 
 * @author css017
 * @version 1.0
 *
 */
public class DepartmentCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructs a Department's catalog giving a entity manager factory
	 */
	public DepartmentCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Finds a Department given it's id number
	 * 
	 * @param id corresponding to the Department's id
	 * @return The Department Id object corresponding to the Department with the id number.
	 * @throws ApplicationException
	 */
	public Department getDepartment (int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Department> query = em.createNamedQuery(Department.FIND_BY_DEPARTMENT_ID, Department.class);
			query.setParameter(Department.ID, id);
			return query.getSingleResult();

		} catch (Exception e) {
			throw new ApplicationException ("Department with id " + id + " does not exist");
		} finally {
			em.close();
		}
	}
	/*
	public void setManager(Department department, int managerId) throws ApplicationException{
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Iterable<Employee> ec = new EmployeeCatalog(emf).getAllEmployees();

			//while(ec.iterator().next().getId() != managerId){
			for(Employee e: ec){
				if(ec.iterator().next().getId() != managerId) {

					//Department d = new Department(department.getName(), department.getPhoneNumber(), managerId);
					department.setManager(managerId);
					em.persist(department);
					em.getTransaction().commit();
				}
			}
				// o manager nao pertence a lista de employees do departamento
			}catch(Exception e){
				if(em.getTransaction().isActive())
					em.getTransaction().rollback();
				throw new ApplicationException("Employee with id " + managerId + " doesnt belong to the Department");
			}finally{
				em.close();
			}

		}*/

	/**
	 * Creates a new Department given its name and phone number
	 * 
	 * @param name of the Department
	 * @param phoneNumber of the Department
	 * @return the newly created Department
	 * @throws ApplicationException 
	 */
	public int createDepartment(String name, int phoneNumber) throws ApplicationException{
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Department f = new Department(name, phoneNumber, 0);
			f.setName(name);
			f.setPhoneNumber(phoneNumber);
			//setManager(f, managerId);
			em.persist(f);
			em.getTransaction().commit();
			return f.getId();

		}catch(Exception e){
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error creating department", e);
		} finally {
			em.close();

		}
	}
	/**
	 * 
	 * @param id
	 * @param employeeId
	 * @return
	 * @throws ApplicationException
	 */
	public Department switchEmployee(Department department, Employee employee) throws ApplicationException{
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Employee e = em.find(Employee.class, employee.getId());
			e.setDepartment(department);
			em.persist(e);
			em.getTransaction().commit();
		}catch(Exception e){
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error switching employee", e);
		}finally {
			em.close();
		}
		return null;
	}
}