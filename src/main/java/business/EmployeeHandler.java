package business;

public class EmployeeHandler {

	
	/**
	 * Employee's catalog
	 */
	private EmployeeCatalog employeeCatalog;
	/**
	 * Department's catalog
	 */
	private DepartmentCatalog departmentCatalog;
	
	/**
	 * Creates a new Employee Handler given a employee and a department
	 * 
	 * @param employeeCatalog
	 * @param departmentCatalog
	 */
	public EmployeeHandler(EmployeeCatalog employeeCatalog, DepartmentCatalog departmentCatalog){
		
		this.employeeCatalog = employeeCatalog;
		this.departmentCatalog = departmentCatalog;
	}
	/**
	 * Adds a new Employee
	 * 
	 * @param departmentId, employee department id
	 * @throws ApplicationException
	 */
	public void addEmployee (int departmentId) throws ApplicationException{
		employeeCatalog.addEmployee(departmentCatalog.getDepartment(departmentId));
	}
	/**
	 * Removes Employee by its id
	 * 
	 * @param employee_id, id o employee to remove
	 * @throws ApplicationException
	 */
	public void removeEmployeeById (int employee_id) throws ApplicationException{
		employeeCatalog.removeEmployeeById(departmentCatalog.getDepartment(employee_id));
	}
	/**
	 * Gets the employee by its Id
	 * 
	 * @param employee_id, the id of the employee desired
	 * @throws ApplicationException
	 */
	public void getEmployee(int employee_id) throws ApplicationException{
		employeeCatalog.getEmployee(employee_id);
	}
	/**
	 * gets all Employees
	 * 
	 * @return all employees
	 * @throws ApplicationException
	 */
	public Iterable<Employee> getAllEmployees() throws ApplicationException{
		return employeeCatalog.getAllEmployees();
	}
}
