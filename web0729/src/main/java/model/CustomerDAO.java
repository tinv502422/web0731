package model;
import java.util.*;

public class CustomerDAO {
	private List<Customer> customerList = new ArrayList<>();

    private static CustomerDAO ourInstance = new CustomerDAO();

    public static CustomerDAO getInstance() {
        return ourInstance;
    }

    public String addCustomer(Customer customer) {
        String newId = Integer.toString(customerList.size() + 1);
        customer.setId(newId);
        customerList.add(customer);
        return newId;
    }

    public String addCustomer(String name, String address, String phoneNumber) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        return addCustomer(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Customer getCustomerById(String id) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }

        return null;
    }

}
