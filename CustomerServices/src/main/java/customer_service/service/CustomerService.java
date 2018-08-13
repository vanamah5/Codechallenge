package customer_service.service;

import customer_service.model.Customer;

public interface CustomerService {

	Customer findById(long id);
	
	Customer findByName(String name);
	
	boolean saveCustomer(Customer customer);
	
	boolean updateCustomer(Customer customer);
	
	boolean deleteCustomerById(long id);

}
