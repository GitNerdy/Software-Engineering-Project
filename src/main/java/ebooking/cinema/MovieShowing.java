package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieShowing {

    String id;
    String movieID;
    String hallID;
    String showTimeID;


    String movieName;
    String hallName;
    String showTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getHallID() {
        return hallID;
    }

    public void setHallID(String hallID) {
        this.hallID = hallID;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(String showTimeID) {
        this.showTimeID = showTimeID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }


    public List<MovieShowing> getShowings() {
        List<MovieShowing> results = new ArrayList<MovieShowing>();
        MovieShowing temp = new MovieShowing();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM movieShowing " +
                    "INNER JOIN hall ON hall.hallID=movieShowing.hallID " +
                    "INNER JOIN movie ON movie.movieID=movieShowing.movieID " +
                    "INNER JOIN showTime ON showTime.showTimeID=movieShowing.showTime";

            ResultSet myResult = myStatement.executeQuery(sql);

            String hallSql = "SELECT * FROM hall WHERE hallID = '" + temp.hallID + "'";
            String movieSql = "SELECT * FROM movie WHERE movieID = '" + temp.movieID + "'";
            String showTimeSql = "SELECT * FROM showTime WHERE showTimeID = '" + temp.showTimeID + "'";

            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                temp = new MovieShowing();

                temp.id = myResult.getString("showingID");
                temp.movieID = myResult.getString("movieID");
                temp.hallID = myResult.getString("hallID");
                temp.showTimeID = myResult.getString("showTime");

                temp.movieName = myResult.getString("movieTitle");
                temp.hallName = myResult.getString("hallName");
                temp.showTime = myResult.getString("time");

                results.add(temp);
            }



        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return results;
    }

    public void deleteShowing(String id) {
        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "DELETE FROM movieShowing WHERE showingID = " + id;
            myStatement.executeUpdate(sql);



        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

    public void insertShowing(MovieShowing showing, String hall) {
        List<Seat> seats = new ArrayList<Seat>();

        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM seat WHERE hallID = '" + hall + "'";
            ResultSet myResults = myStatement.executeQuery(sql);

            while(myResults.next()) {
                Seat temp = new Seat();
                temp.seatID = myResults.getInt("seatID");
                seats.add(temp);
            }
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "INSERT INTO movieShowing (movieID, hallID, showTime) VALUES('"+ showing.movieID +"', '" + hall + "', '"+ showing.showTimeID + "')";
            myStatement.executeUpdate(sql);

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        for(Seat seat: seats) {
            try {

                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
                Statement myStatement = myConn.createStatement();
                String sql = "INSERT INTO seatAtShowTime (seatID, showingID, status) VALUES('"+ seat.seatID +"', '" + showing.showTimeID + "', 'open')";
                myStatement.executeUpdate(sql);

            }

            catch(Exception  ex){
                ex.printStackTrace();
            }
        }


    }

    public List<MovieShowing> getShowingsMovie(String id) {
        List<MovieShowing> results = new ArrayList<MovieShowing>();
        MovieShowing temp = new MovieShowing();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM movieShowing " +
                    "INNER JOIN showTime ON showTime.showTimeID=movieShowing.showTime WHERE movieID = '" + id + "'";

            ResultSet myResult = myStatement.executeQuery(sql);

            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                temp = new MovieShowing();

                temp.id = myResult.getString("showingID");
                temp.showTimeID = myResult.getString("showTime");

                temp.showTime = myResult.getString("time");

                results.add(temp);
            }



        }

        catch(Exception  ex){
            ex.printStackTrace();
        }


        return results;
    }

    public static List<SeatAtShowing> getSeatsAtShowing(String id){
        List<SeatAtShowing> results = new ArrayList<SeatAtShowing>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM seatAtShowTime INNER JOIN seat ON seatAtShowTime.seatID = seat.seatID WHERE showingID ='" + id + "'" ;
            ResultSet myResult = myStatement.executeQuery(sql);
            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                SeatAtShowing temp = new SeatAtShowing();
                temp.seatShowingID = myResult.getInt("seatShowID");
                temp.seatID = myResult.getInt("seatID");
                temp.status = myResult.getString("status");
                temp.seatName = myResult.getString("name");
                results.add(temp);
            }
        }
        catch(Exception  ex){
            ex.printStackTrace();
        }
        return results;
    }

    //UPDATE seatAtShowing
    public void updateSeatsAtShowing(List<SeatAtShowing> selected){
        selected = new ArrayList<SeatAtShowing>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            for(int i = 0; i < selected.size(); i++) {
                String sql = "UPDATE seatAtShowing SET status ='" + selected.get(i).getStatus() + "' WHERE seatShowingID ='"
                        + selected.get(i).getSeatShowingID() + "'";
                ResultSet myResult = myStatement.executeQuery(sql);
            }
            // Get attributes of Movies we need for MovieList and add them to ArrayList
        }
        catch(Exception  ex){
            ex.printStackTrace();
        }
    }
}
