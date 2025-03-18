/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.ServiceTicketDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import model.ServiceTicket;

/**
 *
 * @author ASUS
 */
public class ServiceTicketService {

    public ArrayList<ServiceTicket> getTickets() {
        return ServiceTicketDAO.getAllServiceModels();
    }
    
    public ServiceTicket getServiceTicketById(String ticketID, HttpSession session) {
        /*
            Get the fetched list first
            If for some reasons the list didnt exist yet, get it from the database
        */
        ArrayList<ServiceTicket> searchList = (ArrayList<ServiceTicket>) session.getAttribute("ticketList");
        if (searchList==null) searchList = getTickets();
        ServiceTicket result=null;
        
        for (ServiceTicket serviceTicket : searchList) {
            if(serviceTicket.getServiceTicketID().equals(ticketID)) {
                result = serviceTicket;
            }
        }
        return result;
    }

    public ArrayList<ServiceTicket> getPaginatedServiceTickets(ArrayList<ServiceTicket> tickets, int page, int recordsPerPage) {
        int totalServiceTickets = tickets.size();

        int startIndex = (page - 1) * recordsPerPage;
        int endIndex = Math.min(startIndex + recordsPerPage, totalServiceTickets);

        //using calculated data(start, end, totalPage) to break customer data into the data of the current page number
        return new ArrayList<>(tickets.subList(startIndex, endIndex));
    }
    
    public int getTotalPages(ArrayList<ServiceTicket> customers, int recordsPerPage){
        int totalServiceTickets = customers.size();
        
        return (int) Math.ceil((double) totalServiceTickets / recordsPerPage);
    }

}
