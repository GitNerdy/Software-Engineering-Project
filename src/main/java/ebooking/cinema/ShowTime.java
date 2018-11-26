package ebooking.cinema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTime {
    String id;
    String startTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<ShowTime> getShowTimes() {
        List<ShowTime> results = new ArrayList<ShowTime>();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM showtime ";
            ResultSet myResult = myStatement.executeQuery(sql);


            while (myResult.next()) {
                ShowTime temp = new ShowTime();
                temp.id = myResult.getString("showTimeID");
                temp.startTime = myResult.getString("time");
                results.add(temp);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return results;
    }

    public void deleteShowTime(String id) {
        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "DELETE FROM showtime WHERE showTimeID = " + id;
            myStatement.executeUpdate(sql);



        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

    public void insertShowTime(String time) {
        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO showtime (time) VALUES ('" + time + "')";
            myStatement.executeUpdate(sql);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
