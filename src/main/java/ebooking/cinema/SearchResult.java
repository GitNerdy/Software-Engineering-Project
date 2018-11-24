package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    String movieTitle;
    String image;

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

    public List<SearchResult> searchMovies() {
        List<SearchResult> results = new ArrayList<SearchResult>();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM movie ";
            ResultSet myResult = myStatement.executeQuery(sql);
            System.out.println("Lion King");


            while (myResult.next()) {
                SearchResult temp = new SearchResult();
                temp.movieTitle = myResult.getString("movieTitle");
                temp.image = myResult.getString("image");
                System.out.println(temp.movieTitle);
                System.out.println(temp.image);
                results.add(temp);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return results;
    }
}
