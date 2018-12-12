package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgeGroup {
    String id;
    String description;
    float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static List<AgeGroup> getAgeGroups() {
        List<AgeGroup> results = new ArrayList<AgeGroup>();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM ageGroup";
            ResultSet myResult = myStatement.executeQuery(sql);

            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                AgeGroup temp = new AgeGroup();
                temp.id = myResult.getString("ageGroupID");
                temp.description = myResult.getString("description");
                temp.price = myResult.getFloat("cost");

                results.add(temp);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

            return results;
    }

    public static float getCost(String id) {
        float cost = 0;

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM ageGroup WHERE ageGroupID = '" + id + "'";
            ResultSet myResult = myStatement.executeQuery(sql);

            // Get attributes of Movies we need for MovieList and add them to ArrayList
            while (myResult.next()) {
                cost = myResult.getFloat("cost");
                System.out.println("Cost: " + cost);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return cost;
    }
}
