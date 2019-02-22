package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * A customer
 *	
 * @author css017
 * @version 1.1 (17/04/2015)
 * 
 */
@Entity  
@NamedQuery(name=Customer.FIND_BY_VAT_NUMBER, query="SELECT c FROM Customer c WHERE c.vatNumber = :" + 
		Customer.NUMBER_VAT_NUMBER)


public class Customer {

	// Named query name constants
	public static final String FIND_BY_VAT_NUMBER = "Customer.findByVATNumber";
	public static final String NUMBER_VAT_NUMBER = "vatNumber";

	
	// Customer attributes 

	/**
	 * Customer primary key. Needed by JPA. Notice that it is not part of the
	 * original domain model.
	 */
	@Id @GeneratedValue 
	private int id;
	
	/**
	 * Customer's VAT number
	 */
	@Column(nullable = false, unique = true) 
	private int vatNumber;
	
	/**
	 * Customer's name. In case of a company, the represents its commercial denomination 
	 */
	@Column(nullable = false) private 
	String designation;
	
	@Column(nullable = false) 
	private String address;
		
	/**
	 * Customer's contact number
	 */
	@SuppressWarnings("unused")
	private int phoneNumber;

	/**
	 * Customer's discount.
	 */
	@OneToOne @JoinColumn(nullable = false) private Discount discount;
	
	
	// 1. constructor 

	/**
	 * Constructor needed by JPA.
	 */
	Customer() {
	}
	
	/**
	 * Creates a new customer given its VAT number, its designation, phone contact, and discount type.
	 * 
	 * @param vatNumber The customer's VAT number
	 * @param designation The customer's designation
	 * @param phoneNumber The customer's phone number
	 * @param discountType The customer's discount type
	 * @pre isValidVAT(vat) 
	 */
	public Customer(int vatNumber, String designation, int phoneNumber, Discount discountType, String address) {
		this.vatNumber = vatNumber;
		this.designation = designation;
		this.phoneNumber = phoneNumber;
		this.discount = discountType;
		this.address = address;
	}

	
	// 2. getters and setters
	
	/**
	 * @return The discount type of the customer
	 */
	public Discount getDiscountType() {
		return discount;
	}

	/**
	 * For testing purposes only.
	 * 
	 * @return The customer number
	 */
	public int getVATNumber() {
		return vatNumber;
	}
	
	/**
	 * @return The adress of the costumer
	 */
	public String getAddress(){
		return address;
		
	}
	
	/**
	 * @return the ID number of the costumer
	 */
	public int getId(){
		return id;
	}
	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The number to be checked.
	 * @return Whether the VAT number is valid. 
	 */
	public static boolean isValidVAT(int vat) {
		// If the number of digits is not 9, error!
		if (vat < 100000000 || vat > 999999999)
			return false;
		
		// If the first number is not 1, 2, 5, 6, 8, 9, error!
		int firstDigit = vat / 100000000;
		if (firstDigit != 1 && firstDigit != 2 && 
			firstDigit != 5 && firstDigit != 6 &&
			firstDigit != 8 && firstDigit != 9)
			return false;
		
		// Checks the congruence modules 11.
		int sum = 0;
		int checkDigit = vat % 10;
		vat /= 10;
		
		for (int i = 2; i < 10 && vat != 0; i++) {
			sum += vat % 10 * i;
			vat /= 10;
		}
		
		int checkDigitCalc = 11 - sum % 11;
		if (checkDigitCalc == 10)
			checkDigitCalc = 0;
		return checkDigit == checkDigitCalc;
	}
}