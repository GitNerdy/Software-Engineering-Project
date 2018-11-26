package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    String id;
    String movieTitle;
    String image;
    String cast;
    String director;
    String synopsis;
    String producer;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SearchResult> searchMovies(String title) {
        List<SearchResult> results = new ArrayList<SearchResult>();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM movie WHERE movieTitle LIKE '%" + title + "%'";
            ResultSet myResult = myStatement.executeQuery(sql);

            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                SearchResult temp = new SearchResult();
                temp.id = myResult.getString("movieID");
                temp.movieTitle = myResult.getString("movieTitle");
                temp.image = myResult.getString("image");
                results.add(temp);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return results;
    }

    // Makes list of all movies in system with various attributes
    public List<SearchResult> getMovieList() {
        List<SearchResult> results = new ArrayList<SearchResult>();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM movie";
            ResultSet myResult = myStatement.executeQuery(sql);


            while (myResult.next()) {
                SearchResult temp = new SearchResult();
                temp.movieTitle = myResult.getString("movieTitle");
                temp.image = myResult.getString("image");
                temp.cast = myResult.getString("cast");
                temp.director = myResult.getString("director");
                temp.synopsis = myResult.getString("synopsis");
                temp.producer = myResult.getString("producer");
                results.add(temp);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return results;
    }
}
