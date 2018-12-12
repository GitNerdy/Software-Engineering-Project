package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Ticket {
    int ticketID;
    String showingID;
    int bookingID;
    int ageID;
    int seatID;
    String ageGroup;
    String movieName;
    float cost;

    int numChildTicket;
    int numAdultTicket;
    int numSeniorTicket;

    public int getBookingID(){
        return bookingID;
    }

    public void setBookingID(int bookingID){
        this.bookingID = bookingID;
    }

    public int getTicketID(){
        return ticketID;
    }

    public void setTicketID(int ticketID){
        this.ticketID = ticketID;
    }

    public String getShowingID(){
        return showingID;
    }

    public void setShowingID(String showingID){
        this.showingID = showingID;
    }

    public int getAgeID(){
        return ageID;
    }

    public void setAgeID(int ageID){
        this.ageID = ageID;
    }

    public float getSeatID(){
        return seatID;
    }

    public void setSeatID(int seatID){
        this.seatID = seatID;
    }

    public int getNumChildTicket(){
        return numChildTicket;
    }

    public void setNumChildTicket(int numChildTicket){
        this.numChildTicket = numChildTicket;
    }

    public int getNumAdultTicket(){
        return numAdultTicket;
    }

    public void setNumAdultTicket(int numAdultTicket){
        this.numAdultTicket = numAdultTicket;
    }

    public int getNumSeniorTicket(){
        return numSeniorTicket;
    }

    public void setNumSeniorTicket(int numSeniorTicket){
        this.numSeniorTicket = numSeniorTicket;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public static void makeTicket(List<Ticket> ticketList) {
        for (Ticket ticket: ticketList) {
            try {
                int id = 0;

                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
                Statement myStatement = myConn.createStatement();
                System.out.println("Connected!");
                String sql = "INSERT INTO ticket (showingID, bookingID, ageID, seatID) VALUES ('"+ ticket.showingID + "', '" + ticket.bookingID + "',  '" + ticket.ageID + "', '" + ticket.seatID + "')";
                //myStatement.executeQuery("INSERT INTO user (emailAddress, password, firstName, lastName) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "')");
                myStatement.executeUpdate(sql);

                /*ResultSet myResult = myStatement.executeQuery("SELECT *  FROM seat WHERE showingID ='" + ticket.showingID + "'" + "AND ageID ='" + ticket.ageID + "'" +
                        "AND seatID ='" + ticket.seatID + "'" + "AND bookingID ='" + ticket.bookingID + "'");

                if (myResult.next() == true) {
                    id = myResult.getInt("ticketID");
                }
                ticket.ticketID = id;*/
            }

            catch(Exception  ex){
                ex.printStackTrace();
            }
        }
    }

}