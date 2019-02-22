package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 /**
  * A deliver
  * @author css017
  *
  */
@Entity
public class Delivery {
	/**
	 * Delivery primary key. Needed by JPA. Notice that it is not part of the
	 * original domain model.
	 */
	@Id @GeneratedValue
	private int id;
	
	/**
	 * A SaleiD ID number
	 */
	@Column(nullable = false, unique = true)
	private int saleId;
	
	/**
	 * Customer that is  paying the sale
	 */
	@Column(nullable = false)
	private int paying_client;
	
	/**
	 * Customer delivery address. 
	 */
	@Column(nullable = false)
	private String delivery_address;
	
	
	
	/**
	 * Creates a new Delivery given  SaleId, paying client and the address to be delivered
	 * 
	 * @param saleId
	 * @param paying_client
	 * @param delivery_address
	 */
	public Delivery(int saleId, int paying_client, String delivery_address) {
		this.saleId = saleId;
		this.paying_client = paying_client;
		this.delivery_address = delivery_address;
	}
	
	/**
	 * Constructor needed by JPA.
	 */
	Delivery() {
	}
	
	/**
	 * @return the address corresponding to the delivery
	 */
	public String getDelivery_Address(){
		return delivery_address; 
	}
	
	/**
	 * @return the number of the client that is paying
	 */
	public int getPaying_client(){
		return paying_client;
	}
	 /**
	  * @return the sale identification number
	  */
	public int getSaleId(){
		return saleId;
	}
	
	/**
	 * @return the delivery ID
	 */
	public int getId(){
		return id;
	}
	
}
