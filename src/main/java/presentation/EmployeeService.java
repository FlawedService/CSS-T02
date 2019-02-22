package presentation;

import business.ApplicationException;
import business.Employee;
import business.EmployeeHandler;

public class EmployeeService {

	private EmployeeHandler employeeHandler;
	
	public EmployeeService(EmployeeHandler employeehandler){
		this.employeeHandler = employeehandler;
	}
	
	public void addEmployee (int departmentId) throws ApplicationException{
		employeeHandler.addEmployee(departmentId);
	}
	
	public void removeEmployeeById (int employee_id) throws ApplicationException{
		employeeHandler.removeEmployeeById(employee_id);
	}
	
	public void getEmployee(int employee_id) throws ApplicationException{
		employeeHandler.getEmployee(employee_id);
	}
	public Iterable<Employee> getAllEmployees() throws ApplicationException{
		return employeeHandler.getAllEmployees();
	}
	
}
