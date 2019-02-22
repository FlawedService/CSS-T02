package presentation;

import business.AddCustomerHandler;
import business.ApplicationException;


public class AddCustomerService {

	private AddCustomerHandler customerHandler;

	public AddCustomerService(AddCustomerHandler customerHandler) {
		this.customerHandler = customerHandler;
	}
	
	public void addCustomer(int vat, String denomination, int phoneNumber, 
			int discountType, String address) throws ApplicationException {
		customerHandler.addCustomer(vat, denomination, phoneNumber, discountType, address);
	}
}
