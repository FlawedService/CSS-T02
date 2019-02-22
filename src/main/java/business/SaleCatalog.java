package business;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 * A catalog for sales
 * 
 * @author css017
 * @version 1.1 (17/04/2015)
 *
 */
public class SaleCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructs a sale's catalog giving a entity manager factory
	 */
	public SaleCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Creates a new sale and adds it to the repository
	 * 
	 * @param customer The customer the sales belongs to
	 * @return The newly created sale 
	 */
	public Sale newSale (Customer customer, Employee employee) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		
		try{	
			em.getTransaction().begin();
			Sale s = new Sale(new Date(), customer, employee);
			em.persist(s);
			em.getTransaction().commit();
			return s;
		}catch(Exception e){
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding sale", e);
		} finally {
			em.close();

		}
	}

	/**
	 * Adds a Product to the existing Sale
	 * 
	 * @param sale the sale info 
	 * @param product the product info to be added to the sale
	 * @param qty the quantity of the product
	 * @throws ApplicationException
	 */
	public Sale addProductToSale (Sale sale, Product product, double qty) 
			throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			sale.addProductToSale(product, qty);
			em.merge(product);
			em.getTransaction().commit();
			return sale;

		}catch(Exception e){
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error adding product to sale", e);
		}finally{
			em.close();
		}
	}

	/**
	 * The Sale Delivery process
	 * 
	 * @param sale
	 * @param customer
	 * @param address
	 * @return the delivery Status updated
	 * @throws ApplicationException
	 */
	public Delivery saleDelivery(Sale sale , Customer customer, String address) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();			
			Delivery delivery = new Delivery(sale.getId(),customer.getId(),address);			
			em.persist(delivery);
			sale = em.merge(sale);
			sale.setStatus(SaleStatus.DELIVERED);
			//em.persist(sale);
			em.getTransaction().commit();
			return delivery;
		}catch(Exception e){
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new ApplicationException("Error delivering sale", e);
		} finally {
			em.close();
			
		}
	}
	
	/**
	 * 
	 * @param saleId
	 * @return
	 * @throws ApplicationException
	 */
	public Sale getSale(int saleId) throws ApplicationException{
		EntityManager em = emf.createEntityManager();
		TypedQuery<Sale> query = em.createNamedQuery(Sale.FIND_BY_SALE_ID, Sale.class);
		query.setParameter(Sale.SALE_ID, saleId);
		try{
			return query.getSingleResult();
		}catch (PersistenceException e) {
			throw new ApplicationException("Sale not found.");
		}finally {
			em.close();
		}
	}
}
