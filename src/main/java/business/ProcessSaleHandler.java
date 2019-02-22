package business;



/**
 * Handles use case process sale (version with two operations: 
 * newSale followed by an arbitray number of addProductToSale) 
 * 
 * @author fmartins
 *
 */
public class ProcessSaleHandler {
	
	/**
	 * The sale's catalog
	 */
	private SaleCatalog saleCatalog;

	/**
	 * The customer's catalog
	 */
	private CustomerCatalog customerCatalog;
	
	/**
	 * The product's catalog
	 */
	private ProductCatalog productCatalog;
	
	/**
	 * The Employee's Catalog
	 */
	private EmployeeCatalog employeeCatalog;
	
	/**
	 * The current sale
	 */
	private Sale currentSale;

	/**
	 * Creates a handler for the process sale use case given 
	 * the sale, customer, and product catalogs.
	 * 
	 * @param saleCatalog A sale's catalog
	 * @param customerCatalog A customer's catalog
	 * @param productCatalog A product's catalog
	 */
	public ProcessSaleHandler(SaleCatalog saleCatalog, CustomerCatalog customerCatalog, 
			ProductCatalog productCatalog, EmployeeCatalog employeeCatalog) {
		this.saleCatalog = saleCatalog;
		this.customerCatalog = customerCatalog;
		this.productCatalog = productCatalog;
		this.employeeCatalog = employeeCatalog;
	}

	/**
	 * Creates a new sale
	 * 
	 * @param vatNumber The customer's VAT number for the sale
	 * @throws ApplicationException In case the customer is not in the repository
	 */
	public void newSale (int vatNumber, int employee_id) throws ApplicationException {
	
		currentSale = saleCatalog.newSale(customerCatalog.getCustomer(vatNumber), employeeCatalog.getEmployee(employee_id));
	
	}

	/**
	 * Adds a product to the current sale
	 * 
	 * @param prodCod The product code to be added to the sale 
	 * @param qty The quantity of the product sold
	 * @throws ApplicationException When the sale is closed, the product code
	 * is not part of the product's catalog, or when there is not enough stock
	 * to proceed with the sale
	 */
	public void addProductToSale (int prodCod, double qty) throws ApplicationException {
		
		saleCatalog.addProductToSale(currentSale, productCatalog.getProduct(prodCod),qty);
	}

	/**
	 * @return The sale's discount, according to the customer discount type
	 * @throws ApplicationException When some persistence error occurs
	 */
	public double getSaleDiscount () throws ApplicationException  {
		return currentSale.discount();
	}

	/**
	 * @return The sale's total, before discount.
	 * @throws ApplicationException When some persistence error occurs
	 */
	public double getSaleTotal() {
		return currentSale.total();
	}
	/**
	 * 
	 * @param vat
	 * @param address
	 * @throws ApplicationException
	 */
	public void AddDelivery(int vat, String address) throws ApplicationException{
		saleCatalog.saleDelivery(currentSale ,customerCatalog.getCustomer(vat)  , address);
		
	}
	
	
}
