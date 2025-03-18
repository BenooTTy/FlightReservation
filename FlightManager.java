import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class FlightManager
{
	ArrayList<Flight> flights = new ArrayList<Flight>();
  
  String[] cities = 	{"Dallas", "New York", "London", "Paris", "Tokyo"};
  final int DALLAS = 0;  final int NEWYORK = 1;  final int LONDON = 2;  final int PARIS = 3; final int TOKYO = 4;
  
  int[] flightTimes = { 3, // Dallas
  											1, // New York
  											7, // London
  											8, // Paris
  											16// Tokyo
  										};
  
  ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();  
  
  String errorMsg = null; 
  
  Random random = new Random();
  
  
  public FlightManager()
  {
  	airplanes.add(new Aircraft(85, "Boeing 737"));
  	airplanes.add(new Aircraft(180,"Airbus 320"));
  	airplanes.add(new Aircraft(37, "Dash-8 100"));
  	airplanes.add(new Aircraft(12, "Bombardier 5000"));
  	airplanes.add(new Aircraft(592, 14, "Boeing 747"));
  	
  	String flightNum = generateFlightNumber("United Airlines");
  	Flight flight = new Flight(flightNum, "United Airlines", "Dallas", "1400", flightTimes[DALLAS], airplanes.get(0));
  	flights.add(flight);
  	flight.setStatus(Flight.Status.DELAYED);
  	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "London", "2300", flightTimes[LONDON], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "Paris", "2200", flightTimes[PARIS], airplanes.get(1));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("Porter Airlines");
   	flight = new Flight(flightNum, "Porter Airlines", "New York", "1200", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "New York", "0900", flightTimes[NEWYORK], airplanes.get(3));
   	flights.add(flight);
   	flight.setStatus(Flight.Status.INFLIGHT);
   	
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new Flight(flightNum, "Air Canada", "New York", "0600", flightTimes[NEWYORK], airplanes.get(2));
   	flights.add(flight);
   	flight.setStatus(Flight.Status.INFLIGHT);
   	
   	
   	flightNum = generateFlightNumber("United Airlines");
   	flight = new Flight(flightNum, "United Airlines", "Paris", "2330", flightTimes[PARIS], airplanes.get(0));
   	flights.add(flight);
   	
    /*
     * Add this code back in when you are ready to tackle class LongHaulFlight and have implemented its methods
     */
   	flightNum = generateFlightNumber("Air Canada");
   	flight = new LongHaulFlight(flightNum, "Air Canada", "Tokyo", "2200", flightTimes[TOKYO], airplanes.get(4));
   	flights.add(flight);
  }
  
  /*
   * This private helper method generates and returns a flight number string from the airline name parameter
   * For example, if parameter string airline is "Air Canada" the flight number should be "ACxxx" where xxx is 
   * a random 3 digit number between 101 and 300 (Hint: use class Random - see variable random at top of class)
   * you can assume every airline name is always 2 words. 
   * 
   */
  private String generateFlightNumber(String airline)
  {
      /**
       * @ param : String airline
       * Given an airline, we generate a random number beween 101 and 300
       * Then, we will add that number to the two letter short version of the airline and return a string as the flight number
       */
    // Your code here
      int randomNum = 101 + (int)(Math.random() * 201);
      String flightnumrand = Integer.toString(randomNum);
      if (airline == "Air Canada"){
          return "AC" + flightnumrand;
      }
      else if (airline == "United Airlines"){
          return "UA" + flightnumrand;
      }
      else if (airline == "Porter Airlines"){
          return "PA" + flightnumrand;
      }
      return null;

  }
  public void printAllFlights()
  {
      /**
       * print all flights by going through the flights arraylist and printing the string version of each flight
       */
  	for (int j = 0; j < flights.size(); j++)
  	{
  		System.out.println(flights.get(j).toString());
  	}
  }
  
  // Given a flight number (e.g. "UA220"), check to see if there are economy seats available
  // if so return true, if not return false
  public boolean seatsAvailable(String flightNum)
  {

    // First check for a valid flight number
    // if it is not found, set errorMsg String and return false
    // To determine if the given flightNum is valid, search the flights array list and find 
    // the  Flight object with matching flightNum string
    // Once a Flight object is found, check if seats are available (see class Flight) 
    // if flight is full, set errorMsg to an appropriate message (see video) and return false
    // otherwise return true
    for (int j = 0 ; j < flights.size() ; j++){//looking for flight in the arraylist using the flight number
        Flight flightToBeChecked = flights.get(j);
        if (flightToBeChecked.flightNum.equals(flightNum)){//if the flight is in the list
            if (flightToBeChecked.seatsAvailable()){//if there are seats available
                return true;//return true if both conditions above are true
            }
            //Exception 1
            errorMsg = "No Seats Available";//if the if condition above returns false, that means there are no seats available
            getErrorMessage();//so we set the appropriate error message and call getErroMessage()
            return false;//then we will return false
        }

    }
      //Exception 2
      errorMsg = "Flight not found";//if teh program reaches this point it means that it has not been able to find the flight
      System.out.println(getErrorMessage());//Therefore, the flight is not in the list
      return false;//return false
  }
  
  
  // Given a flight number string flightNum and a seat type, reserve a seat on a flight
  // If successful, return a Reservation object
  // NOTE: seat types are not used for basic Flight objects (seats are all "Economy Seats")
  // class LongHaulFlight defines two seat types
  // I  suggest you first write this method *without* considering class LongHaulFlight 
  // once that works (test it!!), add the long haul flight code
  public Reservation reserveSeatOnFlight(String flightNum,Passenger aPassenger) {
      /**
       * @ param : String flightNum
       * @ param : Passenger aPassenger
       * if we can find the flight num and the flight does not contain that passenger, we will reserve a seat for them
       * @ return : reservation
       */
      for (int j = 0 ; j < flights.size() ; j++){//trying to find the flight in the arraylist
          if (flights.get(j).flightNum.equals(flightNum)){//if a flight object has a flight num that equals the flightNUm param
              Flight theflight = flights.get(j);
              if (theflight.contains(aPassenger)==false){// if the flight does not already contain the param aPassenger
                  boolean result = theflight.reserveSeat(aPassenger);// the result variable is the return value the reserveSeat method of class flight
                  if (result==true) {// if the result of the reserveSeat method is true, that means the passenger has reserved a place
                      Reservation theres = new Reservation(flightNum, "flightNum: " + flightNum + "  Dest: " + theflight.dest + "   Duration: " + theflight.flightDuration + "    Status: " + theflight.status + "   " + aPassenger.toString());
                      return theres;// the reservation object has the information of the flight and the passenger information as well as the flight
                  }
              }
              else{
                  errorMsg = "Passenger is already on flight " + flightNum;//this will be executed if the result is false
                  //this means the passenger is already on the flight
                  System.out.println(getErrorMessage());
                  return null;
              }

          }

      }
      errorMsg = "Flight Not Found";//if we get here that means the flight was not found in the for loop
      System.out.println(getErrorMessage());
      return null;
  }


  public Reservation reserveSeatOnFlight(String flightNum, String seatType)
  {
    for (int j = 0 ; j < flights.size() ; j++){
        if (flights.get(j).flightNum.equals(flightNum)){
            Flight theflight = flights.get(j);
            if (flights.get(j) instanceof LongHaulFlight) {
                LongHaulFlight longflight = (LongHaulFlight) (theflight);
                if (seatType == "First Class Seat") {
                    longflight.reserveSeat("First Class Seat");
                    Reservation toBeReturned = new Reservation(longflight.getFlightNum(),"flightNum: " + flightNum + "  Dest: " + longflight.dest + "   Duration: " + longflight.flightDuration + "     Status: " + longflight.status + " LONG HAUL and First Class");
                    toBeReturned.setFirstClass();
                    return toBeReturned;

                } else {
                    if (longflight.passengers < longflight.aircraft.getNumSeats()) {
                        longflight.reserveSeat("Economy Seat");
                        Reservation toBeReturned = new Reservation(longflight.getFlightNum(),"flightNum: " + flightNum + "  Dest: " + longflight.dest + "   Duration: " + longflight.flightDuration + "     Status: " + longflight.status + " LONG HAUL");
                        return toBeReturned;
                    }
                    else{
                        errorMsg = "Flight Full";
                        System.out.println(getErrorMessage());
                    }

                }
            }
            else if (flights.get(j) instanceof LongHaulFlight == false && seatType=="Economy Seat"){
                if (theflight.passengers<theflight.aircraft.getNumSeats()){
                    theflight.reserveSeat();
                    Reservation toBeReturned = new Reservation(theflight.getFlightNum(),"flightNum: " + flightNum + "  Dest: " + theflight.dest + "   Duration: " + theflight.flightDuration + "    Status: " + theflight.status );
                    return toBeReturned;
                }
                else{
                    errorMsg = "Flight full";
                    return null;
                }

            }
            errorMsg = "Regular flights do not have first class seats and " + flightNum + " is a regular flight";
            return null;


        }
    }
    // if we have come this far, this means that the flight has not been found yet
    errorMsg = "Flight " + flightNum + " not found";
    return null;
  }
  
  public String getErrorMessage()
  {
  	return errorMsg;
  }
  
  /*
   * Given a Reservation object, cancel the seat on the flight
   */
  public boolean cancelReservation(Reservation res)
  {
  	// Get the flight number string from res
  	// Search flights to find the Flight object - if not found, set errorMsg variable and return false
  	// if found, cancel the seat on the flight (see class Flight)
  	String flightNumber = res.flightNum;//assigning the flight number to a string object
  	if (res.firstClass==false) {//if the reservation is not first class
        for (int j = 0; j < flights.size(); j++) {
            if (flights.get(j).flightNum == flightNumber) {//find the flight
                flights.get(j).cancelSeat();//cancel it
                return true;
            }
        }
        //if we get to this point, that means the for loop could not find the flight so we return false
        errorMsg = "Reservation Not Found";
        System.out.println(getErrorMessage());
        return false;
    }

  	// Once you have the above basic functionality working, try to get it working for canceling a first class reservation
  	// If this is a first class reservation (see class Reservation) and the flight is a LongHaulFlight (Hint use instanceof)
  	// then cancel the first class seat on the LongHaulFlight (Hint: you will need to cast)   
    else{//reservation is first class
        for (int j = 0 ; j < flights.size() ; j++){
            if (flights.get(j).flightNum==flightNumber){// find the flight
                if (flights.get(j) instanceof LongHaulFlight){// if it is a long haul flight
                    LongHaulFlight theflight = (LongHaulFlight)(flights.get(j));// cast the flight obkect to make it a long haul
                    theflight.cancelSeat("First Class Seat");// cancel a first class seat using cancelSeat()
                    return true;
                }
                errorMsg = "Flight not Long Haul";// if we get to this point, that means the if above returned false
                //thus the flight is not long haul and regular flights don't have first class seats
                System.out.println(getErrorMessage());
                return false;
            }

        }
        errorMsg = "Flight not Found";// if we get here, that means we couldn't find the flight
        System.out.println(getErrorMessage());
    }
    return false;


  }

    public void cancelReservation(String flightnum,Passenger aPassenger){
        /**
         * @ param : String flightNUm
         * @ param : Passenger aPassenger
         * this method will be used if we want to cancel a reservation for a passenger
         */
        for (int j = 0 ; j < flights.size() ; j++){
            if (flights.get(j).flightNum.equals(flightnum)){//finding the flight
                boolean result = flights.get(j).cancelSeat(aPassenger);
                if (result == true){// if result is true, that means passenger was removed
                    System.out.println("Passenger: "+ aPassenger.toString() + " removed from flight " + flightnum);
                }
                else{//if it result is false that means we could not find the reservation
                    System.out.println("Reservation not Found");
                }
            }
        }
    }
  public void sortByDeparture()
  {//Sorting using collections.sort with DepartureTimeComparator object
	  Collections.sort(flights,new DepartureTimeComparator());
  }
  // Write a simple inner class that implements the Comparator interface (NOTE: not *Comparable*)
  // This means you will be able to compare two Flight objects by departure time
  private class DepartureTimeComparator implements Comparator<Flight>
  {
    public int compare(Flight X,Flight Y){
        int xx = X.convertTime();
        int yy = Y.convertTime();
        return xx - yy;
    }

  }


  //Sort the array list of flights by increasing flight duration  
  // Essentially one line of code but you will be making use of a Comparator object below
  public void sortByDuration()
  {
	  Collections.sort(flights,new DurationComparator());
  }
  //Write a simple inner class that implements the Comparator interface (NOTE: not *Comparable*)
 // This means you will be able to compare two Flight objects by duration
  private class DurationComparator implements Comparator<Flight>
  {
  	public int compare(Flight X,Flight Y){
  	    int xx = X.getFlightDuration();
  	    int yy = Y.getFlightDuration();
  	    return xx - yy;
    }
  }
  // Prints all aircraft in airplanes array list. 
  // See class Aircraft for a print() method
  public void printAllAircraft()
  {
  	for (int j = 0 ; j < airplanes.size() ; j++){
  	    airplanes.get(j).print();
    }
  }
  
  // Sort the array list of Aircraft objects 
  // This is one line of code. Make sure class Aircraft implements the Comparable interface
  public void sortAircraft()
  {
  	Collections.sort(airplanes);
  }
  public void printPassengersList(String flightnum){
      /**
       * @ param : String flightNum
       * each flight has a print Passengers method
       * we call that
       */
      for (int j = 0 ; j <flights.size() ; j++){
          if (flights.get(j).flightNum.equals(flightnum)){//find the flight object
              flights.get(j).printPassengers();

          }
      }


  }

}
