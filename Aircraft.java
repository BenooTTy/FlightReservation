/*
 * 
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of first class seats
 *
 */
public class Aircraft implements Comparable<Aircraft>
{
  int numEconomySeats;
  int numFirstClassSeats;
  
  String model;
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	/*
	 * Write a compareTo method that is part of the Comparable interface
	 */
	public int compareTo(Aircraft otherAircraft){
		if (this.getNumSeats() == otherAircraft.getNumSeats()){
			return this.getNumFirstClassSeats() - otherAircraft.getNumFirstClassSeats();
		}
		return this.getNumSeats()-otherAircraft.getNumSeats();
	}

	
}
  
