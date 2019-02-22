package business;

import javax.persistence.EntityManagerFactory;

import presentation.EmployeeService;

/**
 * The initial object. The first domain object built during the startup.
 * 
 * @author fmartins
 * @version 1.0 (17/04/2015)
 *
 */
public class SaleSys {

	/**
	 * The add customer use case handler
	 */
	private AddCustomerHandler addCustomerHandler;
	
	/**
	 * The process sale use case handler 
	 */
	private ProcessSaleHandler processSaleHandler;
	
	
	private EmployeeHandler employeeHandler;
	
	
	private DepartmentHandler departmentHandler;
	/**
	 * Performs the start up use case
	 */
	public SaleSys(EntityManagerFactory emf) {
		CustomerCatalog customerCatalog = new CustomerCatalog(emf);
		this.addCustomerHandler = new AddCustomerHandler(customerCatalog, new DiscountCatalog(emf));
		this.processSaleHandler = new ProcessSaleHandler(new SaleCatalog(emf), customerCatalog, new ProductCatalog(emf), new EmployeeCatalog(emf));
		this.employeeHandler = new EmployeeHandler(new EmployeeCatalog(emf), new DepartmentCatalog(emf));
		this.departmentHandler = new DepartmentHandler(new DepartmentCatalog(emf), new EmployeeCatalog(emf));
	}
	
	/**
	 * @return The add customer use case handler
	 */
	public AddCustomerHandler getAddCustomerHandler () {
		return addCustomerHandler;
	}

	/**
	 * @return The process sale use case handler
	 */
	public ProcessSaleHandler getProcessSaleHandler() {
		return processSaleHandler;
	}
	
	/**
	 * @return The add Employee use case handler
	 */
	public EmployeeHandler getEmployeeHandler(){
		return employeeHandler;
	}
	
	/**
	 * @return The add Department use case handler
	 */
	public DepartmentHandler getDepartmentHandler(){
		return departmentHandler;
	}
}
