package business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class DiscountCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;


	/**
	 * Constructs a discount's catalog giving a entity manager factory
	 */
	public DiscountCatalog(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Finds a Discount given its type
	 * 
	 * @param discountType The discount id to find
	 * @return The discount associated with the id
	 * @throws ApplicationException When the discount type is not found
	 */
	public Discount getDiscount (int discountType) throws ApplicationException {
			EntityManager em = emf.createEntityManager();
			TypedQuery<Discount> query = em.createNamedQuery(Discount.FIND_BY_TYPE, Discount.class);
			query.setParameter(Discount.FIND_BY_TYPE_ID, discountType);
			try {
				return query.getSingleResult();
			}catch(PersistenceException e) {
				throw new ApplicationException("Error em getDiscount");
			} finally {
				em.close();
			}
			
	}

	/**
	 * @return The available discounts
	 * @throws ApplicationException When there is an error obtaining the discount list.
	 */
	public List<Discount> getDiscounts() throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Discount> query = em.createQuery(Discount.FIND_ALL,Discount.class);
		try{
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ApplicationException("Error on getDiscounts()", e);
		}finally{
			em.close();
		}
	}
}
