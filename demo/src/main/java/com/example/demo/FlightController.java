// src/main/java/com/example/demo/FlightController.java

package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // enable CORS for frontend
public class FlightController {

    private final FlightManager manager = new FlightManager();

    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return manager.flights;
    }

    @PostMapping("/reserve")
    public Reservation reserveSeat(@RequestBody ReservationRequest request) {
        Passenger passenger = new Passenger(request.name, request.passportNum);
        return manager.reserveSeatOnFlight(request.flightNum, passenger);
    }

    // DTO for incoming POST request
    static class ReservationRequest {
        public String flightNum;
        public String name;
        public int passportNum;
    }
}
