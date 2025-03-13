/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.CustomerDAO;
import java.util.ArrayList;
import model.Customer;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class CustomerService {
    
    //method with no parameter to get all customers from the database
    public ArrayList<Customer> getCustomers() {
        return CustomerDAO.getAllCustomers();
    }
    
    //Overloaded method
    //retrieve a list of customer based on user choice
    //if user doesn't input any search then return the full list
    //else make a sublist based on what the user search
    public ArrayList<Customer> getCustomers(String searchTerm, ArrayList<Customer> allCustomers) {
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            ArrayList<Customer> filteredCustomers = new ArrayList<>();
            for (Customer customer : allCustomers) {
                if (customer.getCustName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    filteredCustomers.add(customer);
                }
            }
            return filteredCustomers;
        }
        return allCustomers;
    }
    
    //return the customer based on ID
    public Customer getCustomerById(int custID, HttpSession session) {
        /*
            Get the fetched list first
            If for some reasons the list didnt exist yet, get it from the database
        */
        ArrayList<Customer> searchList = (ArrayList<Customer>) session.getAttribute("customerList");
        if (searchList==null) searchList = getCustomers();
        Customer result=null;
        
        for (Customer customer : searchList) {
            if (customer.getCustID()==custID) {
                result= customer;
            }
        }
        return result;
    }
    
    //use the passed customers list, current page and number of row per page to break it into multiple sublists
    //used for passing to the view 
    public ArrayList<Customer> getPaginatedCustomers(ArrayList<Customer> customers, int page, int recordsPerPage) {
        int totalCustomers = customers.size();
        /*
            Calculating the total number of pages needed to display all records
            Initial formula: totalCustomers / recordsPerPage
                Type cast the formula to use "double" value for precise calculation
                wrap the formula in math.ceil() to round the number to nearest integer (ex: 3.5 -> 4)
                Type cast the result back to integer value
        */
        int totalPages = (int) Math.ceil((double) totalCustomers / recordsPerPage);
        
        /*  
            Calculating start and end index to divide the list into sublist for display
            Example (startIndex):
            If page = 1 and recordsPerPage = 10, then startIndex = (1 - 1) * 10 = 0. (The first record)
            If page = 2 and recordsPerPage = 10, then startIndex = (2 - 1) * 10 = 10. (The 11th record)
        */
        int startIndex = (page - 1) * recordsPerPage;
        
        /*
            using Math.min() to ensure the last page doesn't exceed the total number of customer
            Example:
            If startIndex = 30, recordsPerPage = 10, totalCustomers = 35
            Then endIndex = Math.min(30 + 10, 35) = 35
        */
        int endIndex = Math.min(startIndex + recordsPerPage, totalCustomers);
        
        //using calculated data(start, end, totalPage) to break customer data into the data of the current page number
        return new ArrayList<>(customers.subList(startIndex, endIndex));
    }

    
    //Calculating the total number of pages needed to display all records
    //Use in junction with getPaginatedCustomers()
    public int getTotalPages(ArrayList<Customer> customers, int recordsPerPage){
        int totalCustomers = customers.size();
        /*
            Initial formula: totalCustomers / recordsPerPage
                Type cast the formula to use "double" value for precise calculation
                wrap the formula in math.ceil() to round the number to nearest integer (ex: 3.5 -> 4)
                Type cast the result back to integer value
        */
        return (int) Math.ceil((double) totalCustomers / recordsPerPage);
    }
    
    public boolean addCustomer(Customer target) {
        CustomerDAO customerDAO = new CustomerDAO();
        
            

            // Check 2: Check for name duplicate (after trimming)
            String trimmedName = trimString(target.getCustName());
            if (customerDAO.customerExists(trimmedName,target.getPhone())) {
                System.out.println("[CustomerService.java] Name or phone is identical with data in db");
                return false;
            }

            // Add the customer
            int result = CustomerDAO.addCustomer(target);
            return result != 0;
        
    }
    
    public String trimString(String target) {
        return target.replaceAll("\\s+", "");
    }
    
}
