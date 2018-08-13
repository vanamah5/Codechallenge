package customer_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import customer_service.model.Customer;
import customer_service.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService; 

	//-------------------Retrieve Single Customer--------------------------------------------------------
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
		System.out.println("Fetching Customer with id " + id);
		Customer customer = customerService.findById(id);
		if (customer == null) {
			System.out.println("Customer with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	//-------------------Create a Customer--------------------------------------------------------
	
	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Customer " + customer.getName());

		customerService.saveCustomer(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	//------------------- Update a Customer --------------------------------------------------------
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
		System.out.println("Updating Customer " + id);
		
		Customer currentCustomer = customerService.findById(id);
		
		if (currentCustomer==null) {
			System.out.println("Customer with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		currentCustomer.setName(customer.getName());
		currentCustomer.setCity(customer.getCity());
		currentCustomer.setState(customer.getState());
		currentCustomer.setRefNo(customer.getRefNo());
		
		customerService.updateCustomer(currentCustomer);
		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}

	//------------------- Delete a Customer --------------------------------------------------------
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Customer with id " + id);

		Customer customer = customerService.findById(id);
		if (customer == null) {
			System.out.println("Unable to delete. Customer with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		customerService.deleteCustomerById(id);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

}
