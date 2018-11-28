package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Seat {
    String name;
    int hallID;
    int seatID;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getHallID(){
        return hallID;
    }
    public void setHallID(int hallID){
        this.hallID = hallID;
    }
    public float getSeatID(){
        return seatID;
    }

    public void setSeatID(int seatID){
        this.seatID = seatID;
    }
    public void makeSeat() {
        try {
            int id = 0;

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            System.out.println("Connected!");
            String sql = "INSERT INTO seat (hallID, name) VALUES ('"+ this.hallID + "', '" + this.name + "')";
            //myStatement.executeQuery("INSERT INTO user (emailAddress, password, firstName, lastName) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "')");
            myStatement.executeUpdate(sql);

            ResultSet myResult = myStatement.executeQuery("SELECT *  FROM seat WHERE hallID ='" + this.hallID + "'" + "AND name ='" + this.name + "'");

            if (myResult.next() == true) {
                id = myResult.getInt("seatID");
            }
            this.seatID = id;
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

}