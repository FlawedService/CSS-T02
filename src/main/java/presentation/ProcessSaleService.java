package presentation;

import business.ApplicationException;
import business.ProcessSaleHandler;

public class ProcessSaleService {

	private ProcessSaleHandler saleHandler;

	public ProcessSaleService(ProcessSaleHandler saleHandler) {
		this.saleHandler = saleHandler;
	}

	public void newSale(int vat,int employee_id) throws ApplicationException {
		saleHandler.newSale(vat, employee_id);
	}

	public void addProductToSale(int productCode, int qty) 
			throws ApplicationException {
		saleHandler.addProductToSale(productCode, qty);
	}

	public double getSaleDiscount() throws ApplicationException {
		return saleHandler.getSaleDiscount();
	}
	
	public void addDelivery(int vat, String address) throws ApplicationException{
		saleHandler.AddDelivery(vat, address);
	}
	
}
