package customer_service.dao;

import customer_service.model.Customer;

public interface CustomerDao {

	Customer findById(long id);
	
	Customer findByName(String name);
	
	boolean saveCustomer(Customer customer);
	
	boolean updateCustomer(Customer customer);
	
	boolean deleteCustomerById(long id);

}
