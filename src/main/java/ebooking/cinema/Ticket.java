package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ticket {
    int ticketID;
    int showingID;
    int bookingID;
    int ageID;
    int seatID;


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
    public int getShowingID(){
        return showingID;
    }
    public void setShowingID(int showingID){
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
    public void makeTicket() {
        try {
            int id = 0;

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            System.out.println("Connected!");
            String sql = "INSERT INTO ticket (showingID, bookingID ageID, seatID) VALUES ('"+ this.showingID + "', '" + this.bookingID + "', '"+ "', '" + this.ageID + "', '" + this.seatID + "')";
            //myStatement.executeQuery("INSERT INTO user (emailAddress, password, firstName, lastName) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "')");
            myStatement.executeUpdate(sql);

            ResultSet myResult = myStatement.executeQuery("SELECT *  FROM seat WHERE showingID ='" + this.showingID + "'" + "AND ageID ='" + this.ageID + "'" +
                    "AND seatID ='" + this.seatID + "'" + "AND bookingID ='" + this.bookingID + "'");

            if (myResult.next() == true) {
                id = myResult.getInt("ticketID");
            }
            this.ticketID = id;
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

}