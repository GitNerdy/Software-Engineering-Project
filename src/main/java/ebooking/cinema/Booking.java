package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Booking {
    int bookingID;
    int userID;
    int paymentID;
    int numTickets;
    float totalCost;
    int promoID;
    float promoPercentage;

    public int getBookingID(){
        return bookingID;
    }

    public void setBookingID(int bookingID){
        this.bookingID = bookingID;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public int getPaymentID(){
        return paymentID;
    }

    public void setPaymentID(int paymentID){
        this.paymentID = paymentID;
    }

    public int getNumTickets(){
        return numTickets;
    }

    public void setNumTickets(int numTickets){
        this.numTickets = numTickets;
    }

    public float getTotalCost(){
        return totalCost;
    }

    public void setTotalCost(float totalCost){
        this.totalCost = totalCost;
    }

    public int getPromoID(){
        return promoID;
    }

    public void setPromoID(int promoID){
        this.promoID = promoID;
    }

    public float getPromoPercentage() {
        return promoPercentage;
    }

    public void setPromoPercentage(float promoPercentage) {
        this.promoPercentage = promoPercentage;
    }

    public void makeBooking() {
        try {
            int id = 0;

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            System.out.println("Connected!");
            String sql = "INSERT INTO booking (userID, paymentID, numTickets, totalCost, promoID) VALUES ('" + this.userID + "', '" + this.paymentID + "', '" + this.numTickets + "', '" + this.totalCost + "', '" + this.promoID + "')";
            //myStatement.executeQuery("INSERT INTO user (emailAddress, password, firstName, lastName) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "')");
            myStatement.executeUpdate(sql);

            ResultSet myResult = myStatement.executeQuery("SELECT *  FROM booking WHERE userID ='" + this.userID + "' AND paymentID ='" + this.paymentID + "'" + "AND numTickets ='" + this.numTickets + "'" +
                    "AND totalCost ='" + this.totalCost + "'" + "AND promoID ='" + this.promoID + "'");

            if (myResult.next() == true) {
                id = myResult.getInt("bookingID");
            }
            this.bookingID = id;
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

    public void updateTotalCost(int promoID){
        this.promoID = promoID;
        System.out.print("PromoID: " + this.promoID);
        try {
            float id = 0;

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            System.out.println("Connected!");

            ResultSet myResult = myStatement.executeQuery("SELECT *  FROM promo WHERE promoID ='" + this.promoID + "'");

            while (myResult.next()) {
                id = myResult.getFloat("discountAmount");
            }
            this.promoPercentage = id;
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

    public static float getTotalCost(List<Ticket> ticketList, float discount) {
        float totalCost = 0;
        float percentOff = discount / 100;

        for (Ticket ticket : ticketList) {
            totalCost += ticket.cost;
        }

        totalCost = totalCost - (totalCost * percentOff);

        return totalCost;
    }
}
