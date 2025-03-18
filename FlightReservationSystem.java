import java.util.ArrayList;
import java.util.Scanner;

public class FlightReservationSystem
{
	public static void main(String[] args)
	{
		FlightManager manager = new FlightManager();

		ArrayList<Reservation> myReservations = new ArrayList<Reservation>();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			Scanner commandLine = new Scanner(inputLine);

			String action = commandLine.next();

			if (action == null || action.equals("")) continue;

			if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("LIST"))
			{
				manager.printAllFlights(); 
			}
			else if (action.equalsIgnoreCase("RESPSNGR")){
				String flightnum = "";
				String name = "";
				Reservation theres = new Reservation(flightnum,"");
				int passportnum = 0;
				if (commandLine.hasNext()) {
					flightnum = commandLine.next();
					if (commandLine.hasNext()){
						name  = commandLine.next();
						if (commandLine.hasNext()){
							passportnum = commandLine.nextInt();
							Passenger thePassenger = new Passenger(name,passportnum);
							theres = manager.reserveSeatOnFlight(flightnum,thePassenger);
						}

					}
				}




			if (theres!=null){
				theres.print();
			}


			}
			else if (action.equalsIgnoreCase("RES"))
			{
				if (commandLine.hasNext()){
					String command = commandLine.next();
					Reservation theres = manager.reserveSeatOnFlight(command,"Economy Seat");
					if (theres==null){
						manager.errorMsg = "Flight " + command +  " Not Found";
						System.out.println(manager.getErrorMessage());
					}
					else{
						theres.print();
						myReservations.add(theres);
					}
					}
			}
			else if (action.equalsIgnoreCase("PSNGRS")){
				if (commandLine.hasNext()){
					String flightNum = commandLine.next();
					manager.printPassengersList(flightNum);
				}

			}
			else if (action.equalsIgnoreCase("RESFCL"))
			{
				if (commandLine.hasNext()){
					String flightnum = commandLine.next();

					Reservation theres = manager.reserveSeatOnFlight(flightnum,"First Class Seat");
					if (theres==null){
						System.out.println(manager.getErrorMessage());
					}
					else{
						theres.print();
						myReservations.add(theres);
					}
				}
			}
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum = null;

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();

					if (manager.seatsAvailable(flightNum))
					{
						System.out.println("Seats are available");
					}
					else
					{
						System.out.println(manager.getErrorMessage());
					}
				}
			}
			else if (action.equalsIgnoreCase("CANCEL")) {

				boolean found = false;
				String flightNum = null;
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
				}
				for (int j = 0; j < myReservations.size(); j++) {
					if (myReservations.get(j).flightNum.equals(flightNum)) {
						manager.cancelReservation(myReservations.get(j));
						myReservations.remove(j);
						found = true;

					}

				}
				if (found == false) {
					manager.errorMsg = "Reservation Not Found";
					System.out.println(manager.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for (int i=0;i<myReservations.size();i++){
					myReservations.get(i).print();
				}
			}
			else if (action.equalsIgnoreCase("CRAFT"))
		  {
			  manager.printAllAircraft();
			}
			else if (action.equalsIgnoreCase("SORTCRAFT"))
		  {
		  	manager.sortAircraft();
		  }
		  else if (action.equalsIgnoreCase("SORTBYDEP"))
		  {
			  manager.sortByDeparture();
			  
		  }
		  else if (action.equalsIgnoreCase("SORTBYDUR"))
		  {
			  manager.sortByDuration();
		  }




		  else if (action.equalsIgnoreCase("CNCLPSNGR")) {
				/**
				 * cancels a passenger on a flight
				 * needs flight number, passenger name, and passport number
				 * for example, cnclpsngr AC123 Sina 123
				 * cancels the seat of a passenger names Sina with a passport number of 123 on flight AC123
				 */
				if (commandLine.hasNext()) {
					String flightnum = commandLine.next();
					if (commandLine.hasNext()) {
						String name = commandLine.next();
						if (commandLine.hasNext()) {
							int passportnum = commandLine.nextInt();
							Passenger apassenger = new Passenger(name, passportnum);
							manager.cancelReservation(flightnum, apassenger);
						}
					}
				}
			}
			System.out.print("\n>");
		}
  	}

	
}

