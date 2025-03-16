/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.CarDAO;
import java.util.ArrayList;
import model.Car;

/**
 *
 * @author ASUS
 */
public class CarService {

    public ArrayList<Car> getCars() {
        return CarDAO.getAllCars();
    }

    //passing the whole carList either get from database or session
    public ArrayList<Car> getCars(String criteria, String searchQuery, ArrayList<Car> allCars) {
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            ArrayList<Car> filteredCars = new ArrayList<>();
            //do different search based on selected criteria
            switch (criteria) {
                case "carSerialNumber": {
                    for (Car car : allCars) {
                        if (car.getSerialNumber().toLowerCase().contains(searchQuery.toLowerCase())) {
                            filteredCars.add(car);
                        }
                    }
                    break;
                }

                case "carModel": {
                    for (Car car : allCars) {
                        if (car.getModel().toLowerCase().contains(searchQuery.toLowerCase())) {
                            filteredCars.add(car);
                        }
                    }
                    break;
                }

                case "carYear": {
                    for (Car car : allCars) {
                        if (Integer.parseInt(car.getYear()) == Integer.parseInt(searchQuery)) {
                            filteredCars.add(car);
                        }
                    }
                    break;
                }
            }

            return filteredCars;
        }
        return allCars;
    }

    public ArrayList<Car> getPaginatedCars(ArrayList<Car> cars, int page, int recordsPerPage) {
        int totalCars = cars.size();
        
        //Calculating start and end index of each page
        int totalPages = (int) Math.ceil((double) totalCars / recordsPerPage);
        int startIndex = (page - 1) * recordsPerPage;
        int endIndex = Math.min(startIndex + recordsPerPage, totalCars);

        return new ArrayList<>(cars.subList(startIndex, endIndex));
    }
    
    public int getTotalPages(ArrayList<Car> cars, int recordsPerPage) {
        int totalCars = cars.size();
        
        //Calculating total pages needed to display all records
        return (int) Math.ceil((double) totalCars / recordsPerPage);
    }
}
