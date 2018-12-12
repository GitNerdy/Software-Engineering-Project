package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class SeatAtShowing {
    int showTimeID;
    int seatShowID;
    int seatID;
    int seatShowingID;
    String seatName;

    String status;

    public int getShowTimeID(){
        return showTimeID;
    }
    public void setShowTimeID(int showTimeID){
        this.showTimeID = showTimeID;
    }
    public int getseatShowID(){
        return seatShowID;
    }
    public void setseatShowID(int seatShowID){
        this.seatShowID = seatShowID;
    }
    public float getSeatID(){
        return seatID;
    }

    public int getSeatShowID() {
        return seatShowID;
    }

    public void setSeatShowID(int seatShowID) {
        this.seatShowID = seatShowID;
    }

    public int getSeatShowingID() {
        return seatShowingID;
    }

    public void setSeatShowingID(int seatShowingID) {
        this.seatShowingID = seatShowingID;
    }

    public void setSeatID(int seatID){
        this.seatID = seatID;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public void makeSeatAtShowing() {
        try {
            int id = 0;

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "contrasena");
            Statement myStatement = myConn.createStatement();
            System.out.println("Connected!");
            String sql = "INSERT INTO seat (seatID, showTimeID, status) VALUES ('"+ this.seatID + "', '" + this.showTimeID+ "', '" + this.status + "')";
            //myStatement.executeQuery("INSERT INTO user (emailAddress, password, firstshowTimeID, lastshowTimeID) VALUES ('" + this.usershowTimeID + "', '" + this.password + "', '" + this.fshowTimeID + "', '" + this.lshowTimeID + "')");
            myStatement.executeUpdate(sql);

            ResultSet myResult = myStatement.executeQuery("SELECT *  FROM seatAtShowTime WHERE seatID ='" + this.seatID + "'" + "AND status ='" + this.status + "'");

            if (myResult.next() == true) {
                id = myResult.getInt("seatID");
            }
            this.seatShowID = id;
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

    public static void updateSeat(List<SeatAtShowing> seatList) {
        for (SeatAtShowing seat: seatList) {
            try {

                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
                Statement myStatement = myConn.createStatement();
                String sql = "UPDATE seatAtShowTime SET status = 'taken' WHERE seatShowID = '" + seat.seatShowID + "'";
                myStatement.executeUpdate(sql);
            }

            catch(Exception  ex){
                ex.printStackTrace();
            }
        }

    }

}