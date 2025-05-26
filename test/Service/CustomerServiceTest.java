package Service;

import DAO.CustomerDAO;
import model.Customer;

import org.junit.Test;
import static org.junit.Assert.*;

import mockit.Mocked;     // For marking an object/class as mock object
import mockit.Expectations; // For defining expected behaviors
import mockit.Verifications; // For verifying 

import java.util.ArrayList;


public class CustomerServiceTest {

    @Mocked
    // This declaration tells JMockit to mock CustomerDAO.class, we don't actually use this object in our test
    CustomerDAO mockedCustomerDAO;

    // The instance of the class under test, we use this instance to make method calls to test
    private CustomerService customerService = new CustomerService();
    
    // Typically, if we use dependency injection, we should have something like this to 
    // assign the customerService the mocked instance of its dependency instead of the real instance
    /*
    
    @Before
    public void setUp() {
        // Inject the mocked instance into the CustomerService DI constructor
        this.customerService = new CustomerService(mockedCustomerDAO);
    }
    
    */
    
    /**
     * Test of getCustomers method (no arguments), of class CustomerService.
     * Scenario: CustomerDAO returns a list of customers. 
     * Expectation: customerService returns the same list
     * Pass criteria:
     *  + the method doesn't return a null object,
     *  + the method return the exact list the database returned
     */
    @Test
    public void testGetCustomers_returnsCustomers() {
        // Record
        ArrayList<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer(1, "Alice Smith", "111-222", "F", "123 Main St"));
        expectedCustomers.add(new Customer(2, "Bob Johnson", "333-444", "M", "456 Oak Ave"));

        // JMockit's Expectations block
        // Defines how the mocked static method CustomerDAO.getAllCustomers() should behave.
        new Expectations() {
            {
                // when this method get called...
                CustomerDAO.getAllCustomers();

                // ... then it will return the declared list, bypassing any logic in the method
                result = expectedCustomers;
            }
        };

        // Replay
        ArrayList<Customer> actualCustomers = customerService.getCustomers();

        // Verify
        
        // Jmockit's Verifications block
        // Defines what methods should have been called on the mock object, and optionally, how many times and with what arguments
        new Verifications() {{
            // Verify that this method was called, not any other methods
            CustomerDAO.getAllCustomers();
            
            // Verify it was called exactly once
            // In other scenarios where a function could have used other function multiple times, change this
            times = 1; 
        }};
        
        
        // Verify the outcome of the method, if any declared assertions is failed, the whole test method will be failed
        assertNotNull(actualCustomers); // Verify that the list returned by the tested method is not null
        assertEquals(expectedCustomers.size(), actualCustomers.size()); //Verify that the size of two lists are the same

        // Verify that the contents in the expected and the actual list are the same.
        assertEquals(expectedCustomers.get(0).getCustID(), actualCustomers.get(0).getCustID());
        assertEquals(expectedCustomers.get(0).getCustName(), actualCustomers.get(0).getCustName());
        assertEquals(expectedCustomers.get(1).getCustID(), actualCustomers.get(1).getCustID());
        assertEquals(expectedCustomers.get(1).getCustName(), actualCustomers.get(1).getCustName());

    }

    /**
     * Test of getCustomers method (no arguments), of class CustomerService.
     * Scenario: CustomerDAO returns an empty list (when there is no customers
     * data in DB).
     * Expectation: customerService also return an empty list 
     * Pass criteria:
     *  + the method doesn't return a null object
     *  + the method does return an empty list
     */
    @Test
    public void testGetCustomers_returnsEmptyList() {
        // Arrange
        ArrayList<Customer> expectedCustomers = new ArrayList<>(); // An empty list, simulate an empty database

        new Expectations() {
            {
                CustomerDAO.getAllCustomers();
                result = expectedCustomers; // Tell the mock to return an empty list
            }
        };

        // Act
        ArrayList<Customer> actualCustomers = customerService.getCustomers();

        // Assert
        assertNotNull(actualCustomers);
        assertTrue(actualCustomers.isEmpty());
        
        

    }    
}

