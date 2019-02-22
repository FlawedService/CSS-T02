package presentation;

import business.ApplicationException;
import business.Department;
import business.DepartmentHandler;

public class DepartmentService {
	
	private DepartmentHandler departmentHandler;
	
	public DepartmentService(DepartmentHandler departmentHandler){
		this.departmentHandler = departmentHandler;
	}
	
	public void getDepartment(int id) throws ApplicationException{
		departmentHandler.getDepartment(id);
	}
	
	public void createDepartment(String name, int phoneNumber) throws ApplicationException{
		departmentHandler.createDepartment(name, phoneNumber);
	}
	
	public void switchEmployee(int id, int employeeId) throws ApplicationException{
		departmentHandler.switchEmployee(id, employeeId);
	}
	
	
}
