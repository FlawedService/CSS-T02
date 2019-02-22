package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A sale
 *	
 * @author css017
 * @version 1.1 (17/04/2015)
 * 
 */
@Entity
@NamedQuery(name=Sale.FIND_BY_SALE_ID, query="SELECT s FROM Sale s WHERE s.id = :" + 
		Sale.SALE_ID)
public class Sale {

	// Named query name constants
	public static final String FIND_BY_SALE_ID = "Sale.getId";
	public static final String SALE_ID = "id";


	@Id @GeneratedValue private int id;


	/**
	 * The date the sale was made 
	 */
	@Temporal(TemporalType.DATE)
	private Date date;

	/**
	 * Whether the sale is open or closed. 
	 */
	//@Column(nullable = false) 
	@Enumerated(EnumType.STRING)private SaleStatus status;

	/**
	 * Customer belonging to the sale
	 */
	@JoinColumn(nullable = false) @OneToOne 
	private Customer customer;

	/**
	 * employee belonging to the sale
	 */
	@JoinColumn(nullable = false) @OneToOne 
	private Employee employee;

	/**
	 * The products of the sale
	 */
	@OneToMany(cascade=CascadeType.PERSIST) 
	private List<SaleProduct> saleProducts;


	// 1. constructor

	public Sale(){
	}

	/**
	 * Creates a new sale given the date it occurred and the customer that
	 * made the purchase.
	 * 
	 * @param date The date that the sale occurred
	 * @param customer The customer that made the purchase
	 * 
	 * , Employee employee
	 */
	public Sale(Date date, Customer customer, Employee employee) {
		this.date = date;
		this.customer = customer;
		this.employee = employee;
		this.status = SaleStatus.OPEN;
		this.saleProducts = new ArrayList<SaleProduct>();
	}


	// 2. getters and setters
	/**
	 * Gets the sale Id
	 * 
	 * @return the sale id
	 */
	public int getId(){
		return id;
	}
	/**
	 * 
	 * @return the sale Status
	 */
	public SaleStatus getStatus(){
		return status;
	}
	/**
	 * Change the sale Status
	 * @param status, the status to change
	 */
	public void setStatus(SaleStatus status){
		this.status = status;
	}
	/**
	 * @return The sale's total 
	 */
	public double total() {
		double total = 0;
		for (SaleProduct sp : saleProducts)
			total += sp.getSubTotal();
		return total;
	}

	/**
	 * @return The sale's amount eligible for discount
	 */
	public double eligibleDiscountTotal () {
		double total = 0;
		for (SaleProduct sp : saleProducts)
			total += sp.getEligibleSubtotal();
		return total;
	}

	/**
	 * @return Computes the sale's discount amount based on the discount type of the customer.
	 */
	public double discount () {
		Discount discount = customer.getDiscountType();
		return discount.computeDiscount(this);
	}

	/**
	 * @return Whether the sale is open
	 */
	public boolean isOpen() {
		return status == SaleStatus.OPEN;
	}

	/**
	 * Adds a product to the sale
	 * 
	 * @param product The product to sale
	 * @param qty The amount of the product being sold
	 * @throws ApplicationException 
	 */
	public void addProductToSale(Product product, double qty) 
			throws ApplicationException {
		if (!isOpen())
			throw new ApplicationException("Cannot add products to a closed sale.");

		// if there is enough stock
		if (product.getQty() >= qty) {
			// adds product to sale
			product.setQty(product.getQty() - qty);
			saleProducts.add(new SaleProduct(product, qty));
		} else
			throw new ApplicationException("Product " + product.getProdCod() + " has stock ("  + 
					product.getQty() + ") which is insuficient for the current sale");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (SaleProduct sp : saleProducts)
			sb.append(" ["+sp.getProduct().getProdCod() +":"+sp.getQty()+"]");
		return sb.toString();
	}
}
