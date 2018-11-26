package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Movie {

    String id;
    String title;
    String cast;
    String director;
    String producer;
    String synopsis;
    String image;
    String showTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Movie getMovie(String id) {
        Movie result = new Movie();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM movie WHERE movieID = '" + id + "'";
            ResultSet myResult = myStatement.executeQuery(sql);


            while (myResult.next()) {
                result.id = myResult.getString("movieID");
                result.title = myResult.getString("movieTitle");
                result.cast = myResult.getString("cast");
                result.director = myResult.getString("director");
                result.producer = myResult.getString("producer");
                result.synopsis = myResult.getString("synopsis");
                result.image = myResult.getString("image");
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return result;
    }
}
