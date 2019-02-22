package business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@Entity
@NamedQueries({
		@NamedQuery(name= Employee.FIND_BY_EMPLOYEE_ID, query="SELECT e FROM Employee e WHERE e.id = :" + 
		Employee.ID)
		})
public class Employee {
	
	// Named query name constants
	public static final String FIND_BY_EMPLOYEE_ID = "Employee.getId";
	public static final String ID = "id";
	
	//Employee attributes
	
	/**
	 * Employee primary key. Needed by JPA. Notice that it is not part of the
	 * original domain model.
	 */
	@Id @GeneratedValue 
	private int id;
	
	
	/**
	 *  Department of the employee
	 */
	@JoinColumn(nullable = false) @ManyToOne
	private Department department;
	
	
	/**Creates a new Employee given it's id and the department id
	 *  
	 * @param employeeId the Employee's Identification number
	 * @param departmentId the Department's Identification number
	 */
	public Employee (Department department){
		this.department = department;
	}
	
	// Constructor need for JPA
	Employee(){	
	}
	
	/**
	 * @return the id of the employee
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * 
	 * @param department of the employee
	 */
	public void setDepartment(Department department) {
		this.department = department;
		
	}

	@Override
	public String toString(){
		return (" ID: " + getId());
		
	}
}
