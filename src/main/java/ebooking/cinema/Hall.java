package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Hall {

    String id;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Hall> getHalls(){
        List<Hall> results = new ArrayList<Hall>();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM hall";
            ResultSet myResult = myStatement.executeQuery(sql);

            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                Hall temp = new Hall();
                temp.id = myResult.getString("hallID");
                temp.name = myResult.getString("hallName");
                results.add(temp);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return results;
    }

    public void deleteHall(String id) {
        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "DELETE FROM hall WHERE hallID = " + id;
            myStatement.executeUpdate(sql);



        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

    public List<Seat> getSeats(){
        List<Seat> results = new ArrayList<Seat>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM seat WHERE hallID ='" + id + "'" ;
            ResultSet myResult = myStatement.executeQuery(sql);

            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                Seat temp = new Seat();
                temp.seatID = myResult.getInt("seatID");
                temp.name = myResult.getString("name");
                results.add(temp);
            }
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
        return results;
    }

}
