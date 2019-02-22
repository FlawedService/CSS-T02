package business;

public class DepartmentHandler {

	/**
	 * The departments's catalog
	 */
	public DepartmentCatalog departmentcatalog;
	/**
	 * the employee Catalog
	 */
	public EmployeeCatalog employeeCatalog;
	/**
	 * Creates a handler for the department use case given
	 * the department and the employee catalogs.
	 *  
	 * @param departmentCatalog
	 * @param employeeCatalog
	 */
	public DepartmentHandler( DepartmentCatalog departmentCatalog, EmployeeCatalog employeeCatalog){
		this.departmentcatalog = departmentCatalog;
		this.employeeCatalog = employeeCatalog;
	}
	
	/**
	 * Obtains the department 
	 * 
	 * @param id of the department
	 * @throws ApplicationException
	 */
	public void getDepartment(int id) throws ApplicationException{
		departmentcatalog.getDepartment(id);
	}
	/**
	 * Creates a new Department
	 * 
	 * @param name of the department
	 * @param phoneNumber of the department
	 * @throws ApplicationException
	 */
	public void createDepartment(String name, int phoneNumber) throws ApplicationException{
		departmentcatalog.createDepartment(name, phoneNumber);
	}
	/**
	 * Change given Employee to other department
	 * 
	 * 
	 * @param id of the new department the employee is going
	 * @param employeeId the id o the employee
	 * @throws ApplicationException
	 */
	public void switchEmployee(int id, int employeeId) throws ApplicationException{
		departmentcatalog.switchEmployee(departmentcatalog.getDepartment(id), employeeCatalog.getEmployee(employeeId));
	}
	
	
}
