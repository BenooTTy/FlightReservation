public class Passenger {
    String name;
    int passportNum;
    int seatNum;
    public Passenger(String name,int passnum,int seatnum){
        this.name = name;
        this.passportNum = passnum;
        this.seatNum = seatnum;
    }
    public Passenger(String name,int passnum){
        this.name = name;
        this.passportNum = passnum;
        this.seatNum = 0;
    }

    public boolean equals(Passenger other){
        /**
         * @ param Passenger other
         * @ return boolean
         * The function will only return true if a passenger's name and passport number are equal to another person's
         */

        if (this.passportNum == other.passportNum && this.name.equals(other.name)){
            return true;
        }
        return false;
    }
    public String toString() {
        /**
         * @ param: none
         * return: String
         * the return will give a string version of a passenger, stating their name, passport number, and seat number
         */
        return "Name: " + this.name + "     Passport Number: " + this.passportNum + "   Seat Number: " + this.seatNum;
    }
    public void setSeatNum(int number){
        /**
         * @ int number
         * given an integer number, set that number as the passenger's seat number
         * This method will be called by class flight after it has reserved a seat for the passenger and wants to give them
         * a seat number based on the airplane's capacity
         */
        this.seatNum = number;
    }


}
