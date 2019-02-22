package client;

import presentation.AddCustomerService;
import presentation.DepartmentService;
import presentation.EmployeeService;
import presentation.ProcessSaleService;
import business.ApplicationException;
import business.SaleCatalog;

/**
 * A simple application client that uses both application handlers.
 *	
 * @author fmartins
 * @version 1.1 (17/04/2015)
 */
public class SimpleClient {

	private AddCustomerService addCustomerService;
	private ProcessSaleService processSaleService;
	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public SimpleClient(AddCustomerService addCustomerService,
			ProcessSaleService processSaleService, EmployeeService employeeService,	
			DepartmentService departmentService) {
		this.addCustomerService = addCustomerService;
		this.processSaleService = processSaleService;
		this.employeeService = employeeService;
		this.departmentService = departmentService;
	}
	
	/**
	 * A simple interaction with the application services
	 */
	public void createASale() {
		
		// the interaction
		try {
			departmentService.createDepartment("yoloDepart", 808200201);
			
			departmentService.createDepartment("SwagDepart", 808200202);
			departmentService.createDepartment("DepartOfFame", 808200300);
			departmentService.createDepartment("BlazeDepart", 808200250);
			
			// adds a customer.
			addCustomerService.addCustomer(1680278562, "Customer 1", 217500255, 2, "Banana Del'Rei");
			employeeService.addEmployee(1);
			employeeService.addEmployee(1);
			employeeService.addEmployee(2);
			employeeService.addEmployee(3);
			employeeService.addEmployee(4);
			
			// starts a new sale
			processSaleService.newSale(1680278562, 2);
		
			
			
			// adds two products to the sale
			processSaleService.addProductToSale(123, 6);
			processSaleService.addProductToSale(124, 5);
			processSaleService.addProductToSale(123, 4);
	
			processSaleService.addDelivery(1680278562, "Filhos do homem");
			
			//departmentService.createDepartment("Yolo", 808200205, 0);
			departmentService.switchEmployee(2, 2);
			
			System.out.println(employeeService.getAllEmployees());
			
			//employeeService.removeEmployeeById(69);
			
			// gets the discount amount
			System.out.println(processSaleService.getSaleDiscount());
		} catch (ApplicationException e) {
			System.out.println("Error: " + e.getMessage());
			// for debugging purposes only. Typically, in the application
			// this information can be associated with a "details" button when
			// the error message is displayed.
			if (e.getCause() != null) 
				System.out.println("Cause: ");
			e.printStackTrace();
		}
	}
}
