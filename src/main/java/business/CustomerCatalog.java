package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * A catalog of customers
 * 
 * @author fmartins
 * @version 1.1 (17/04/2015)
 *
 */
public class CustomerCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;
	
	/**
	 * Constructs a Customer catalog giving a entity manager factory
	 */
	public CustomerCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * Finds a customer given its VAT number.
	 * 
	 * @param vat The VAT number of the customer to fetch from the repository
	 * @return The Customer object corresponding to the customer with the vat number.
	 * @throws ApplicationException When the customer with the vat number is not found.
	 */
	public Customer getCustomer (int vat) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery(Customer.FIND_BY_VAT_NUMBER, Customer.class);
		query.setParameter(Customer.NUMBER_VAT_NUMBER, vat);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Customer not found.");
		} finally {
			em.close();
		}
	}
	
	/**
	 * Adds a new customer
	 * 
	 * @param vat The customer's VAT number
	 * @param designation The customer's designation
	 * @param phoneNumber The customer's phone number
	 * @param discountType The customer's discount type
	 * @throws ApplicationException When the customer is already in the repository or the 
	 * vat number is invalid.
	 */
	public void addCustomer (int vat, String designation, int phoneNumber, Discount discountType, String address) 
			throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			discountType = em.merge(discountType);
			Customer c = new Customer(vat, designation, phoneNumber, discountType, address);
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding customer", e);
		} finally {
			em.close();
		}
	}
}
