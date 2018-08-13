package customer_service.service;

import org.springframework.stereotype.Service;

import customer_service.dao.CustomerDao;
import customer_service.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	CustomerDao customerDao;
	
	public Customer findById(long id) {
		return customerDao.findById(id);
	}

	public Customer findByName(String name) {
		return customerDao.findByName(name);
	}

	public boolean saveCustomer(Customer customer) {
		return customerDao.saveCustomer(customer);
	}

	public boolean updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}

	public boolean deleteCustomerById(long id) {
		return customerDao.deleteCustomerById(id);
	}

}
