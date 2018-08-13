package customer_service.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.JdbcTemplate;

import customer_service.model.Customer;

public class CustomerDaoImpl implements CustomerDao{

	JdbcTemplate jdbcTemplate;

	
	public CustomerDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class CustomerMapper implements RowMapper {
		public Customer mapRow(ResultSet resultSet, int i) throws SQLException {

			Customer customer = new Customer();
			customer.setId(resultSet.getInt("id"));
			customer.setName(resultSet.getString("name"));
			customer.setCity(resultSet.getString("city"));
			customer.setState(resultSet.getString("State"));
			customer.setRefNo(resultSet.getString("Ref_No"));
			return customer;
		}

		public int[] getRowsForPaths(TreePath[] path) {
			return null;
		}
	}
	
	public Customer findById(long id) {
		String SQL_FIND_Customer = "select * from Customer where id = ?";
		return (Customer) jdbcTemplate.queryForList(SQL_FIND_Customer, new Object[] { id }, new CustomerMapper());
	}

	public boolean saveCustomer(Customer customer) {
		String SQL_INSERT_Customer = "insert into Customer(id, first_name, city, age) values(?,?,?,?)";
		return jdbcTemplate.update(SQL_INSERT_Customer, customer.getId(), customer.getName(), customer.getCity(), customer.getState(),
				customer.getRefNo()) > 0;
		
	}

	public boolean updateCustomer(Customer customer) {
		String SQL_UPDATE_Customer = "update Customer set first_name = ?, last_name = ?, age  = ? where id = ?";
		return jdbcTemplate.update(SQL_UPDATE_Customer, customer.getId(), customer.getName(), customer.getCity(), customer.getState(),
				customer.getRefNo()) > 0;
		
	}

	public boolean deleteCustomerById(long id) {
		String SQL_DELETE_Customer = "delete from Customer where id = ?";
		return jdbcTemplate.update(SQL_DELETE_Customer, id) > 0;
	}

	public Customer findByName(String name) {
		String SQL_FIND_Customer_Name = "select * from Customer where name = ?";
		return (Customer) jdbcTemplate.queryForList(SQL_FIND_Customer_Name, new Object[] { name }, new CustomerMapper());
	}



}
