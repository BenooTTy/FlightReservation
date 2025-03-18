import java.util.ArrayList;

/*
 *  Class to model an airline flight. In this simple system, all flights originate from Toronto
 *  
 *  This class models a simple flight that has only economy seats
 */
public class Flight
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};

	String flightNum;
	String airline;
	String origin, dest;
	String departureTime;
	Status status;
	int flightDuration;
	Aircraft aircraft;
	protected int passengers;
  	ArrayList<Passenger> listOfPassengers;
	public Flight()
	{
		this.flightNum = "AC100";
		this.airline = "Air Canada";
		this.dest = "New York";
		this.origin = "Toronto";
		this.departureTime = "22:30";
		this.flightDuration = 150;
		this.aircraft = new Aircraft(300,"Boeing 737");
		passengers = 0;
		status = Status.ONTIME;
		listOfPassengers = new ArrayList<Passenger>();
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		passengers = 0;
		status = Status.ONTIME;
		listOfPassengers = new ArrayList<Passenger>();
		
	}
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getPassengers()
	{
		return passengers;
	}
	public void setPassengers(int passengers)
	{
		this.passengers = passengers;
	}
	
	
	public boolean seatsAvailable()
	{
		// your code here
		if (this.aircraft.getNumSeats()>this.getPassengers()){
			return true;
		}
		return false;
	}
	
	/*
	 * Cancel a seat - essentially reduce the passenger count by 1. Make sure the count does not
	 * fall below 0 (see instance variable passenger)
	 */
	public void cancelSeat()
	{
		/**
		 * param : none
		 * is there are passengers on the flight
		 * decrease the passenger count by 1
		 */
		// your code here
		if (this.passengers>0){
			this.passengers--;
		}
	}
	
	/*
	 * reserve a seat on this flight - essentially increases the passenger count by 1 only if there is room for more
	 * economy passengers on the aircraft used for this flight (see instance variables above)
	 */
	public boolean reserveSeat()
	{
		/**
		 * param : none
		 * if there are seats available, increment passenger count and return true
		 */
		// your code here
		if (seatsAvailable()){
			passengers++;

		}
		return true;
	}

	public boolean reserveSeat(Passenger aPassenger){
		/**
		 * param : passenger
		 * if reserve seat returns true, we increment passenger count
		 * based on the maximum number of seats on the aircraft, we generate a random number and assign to the passenger
		 * that becomes their seat number
		 * we will use the passenger setSeatNum method to change the seatNum attribute
		 * then will add the passenger to out arraylist of passengers
		 */

		if (reserveSeat()){
			reserveSeat();

			int maxSeatNum = this.aircraft.getNumSeats();
			aPassenger.setSeatNum(0 + (int)(Math.random() * maxSeatNum));
			listOfPassengers.add(aPassenger);
			return true;
		}
		return false;
}
	public boolean cancelSeat(Passenger aPassenger){
		/**
		 * param : passenger
		 * given a passenger, we cancel their seat on the flight
		 * if this is successful we return true
		 * if we cannot find the passenger, we will return false
		 */
		for (int i=0;i<listOfPassengers.size();i++){
			if (listOfPassengers.get(i).equals(aPassenger)){
				listOfPassengers.remove(i);
				return true;
			}
		}
		return false;
	}


	public void printPassengers(){
		/**
		 * param : none
		 * we print all passengers that are on the flight
		 * Each passenger's string version is defined in the Passenger class in the toString method
		 */
		if (listOfPassengers.size()==0){
			System.out.println("Flight Empty");
		}
		else{
			for (int j = 0 ; j < listOfPassengers.size() ; j++){
				System.out.println(listOfPassengers.get(j).toString());
			}
		}

	}
	public int convertTime() {
		/**
		 * this method converts each flights time of departure to minutes with 00:00 as the origin
		 * for example 22:30 is 22 times 60 + 30 minutes which is 1320 minutes in total.
		 * this method will be used in flightManager to sort flights based on their departure time
		 * Every string that represent departure time has four letters
		 * The two on the left represent the hour
		 * and the two on the right represent the minute
		 * @ return : int durationInInt
		 */
		String time = this.getDepartureTime();
		int durationInInt = 0;
		int hour = Integer.parseInt(time.substring(0,2));
		int minute = Integer.parseInt(time.substring(2,4));
		durationInInt += hour * 60 + minute;

		return durationInInt;
	}
	public boolean contains(Passenger aPassenger){
		/**
		 * @ param : Passenger
		 * given a passenger object, we will check to see if a passenger is on the passengers list
		 * if the list already contains that passenger, we will return true
		 * else will return fals
		 * the method will be used when we want to reserve a seat for a passenger and we want to make sure they
		 * are not already on the flight
		 */
		for (int j = 0; j < listOfPassengers.size() ; j++){
			if (listOfPassengers.get(j).equals(aPassenger)){
				return true;
			}
		}
		return false;
	}
	public String toString()
	{
		//Returns a string version of the flight
		 return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
		
	}

  
}
