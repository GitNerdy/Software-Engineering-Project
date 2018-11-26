package ebooking.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {

    String id;
    String username;
    String password;
    String email;
    String fname;
    String lname;
    String status;
    boolean loggedIn = false;
    boolean isAdmin = false;

    // Address Info for Registration
    String street = null;
    String city = null;
    String state = null;
    String zip = null;

    // Paymen Info for Registration
    String type = null;
    String cardNumber = null;
    String securityCode = null;

    int promo = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    public String getPassword() {
        return password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean login() {
                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
                    Statement myStatement = myConn.createStatement();
                    System.out.println("Connected!");
                    ResultSet myResult = myStatement.executeQuery("SELECT *  FROM user WHERE emailAddress ='" + this.username + "' AND password='" + this.password + "'");

                    if (myResult.next() == true) {
                        String admin = myResult.getString("userTypeID");
                        System.out.println(admin);
                        System.out.println("Sucess!");
                        this.loggedIn = true;
                        if (admin.equals("2")) {
                            System.out.println("Is Admin");
                            this.isAdmin = true;
                        }
                        return true;
                    }


                }
                catch(Exception  ex){
                    ex.printStackTrace();
                }

        return false;
    }

    public void register() {
        try {
            int id = 0;

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            System.out.println("Connected!");
            String sql = "INSERT INTO user (emailAddress, password, firstName, lastName, userTypeID) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "', '" + 1 + "')";
            //myStatement.executeQuery("INSERT INTO user (emailAddress, password, firstName, lastName) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "')");
            myStatement.executeUpdate(sql);

            ResultSet myResult = myStatement.executeQuery("SELECT *  FROM user WHERE emailAddress ='" + this.username + "' AND password='" + this.password + "'");

            if (myResult.next() == true) {
                id = myResult.getInt("userID");
            }


            sql = "INSERT INTO address (occupant, street, city, state, zipCode) VALUES ('" + id + "', '" + this.street + "', '" + this.city + "', '" + this.state + "', '" + this.zip + "')";

            myStatement.executeUpdate(sql);

            this.loggedIn = true;
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }

    public void logout() {
        this.loggedIn = false;
    }

    public List<User> getUsers() {
        List<User> results = new ArrayList<User>();

        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "SELECT * FROM user ";
            ResultSet myResult = myStatement.executeQuery(sql);


            while (myResult.next()) {
                User temp = new User();
                temp.id = myResult.getString("userID");
                temp.fname = myResult.getString("firstName");
                temp.lname = myResult.getString("lastName");
                temp.status = myResult.getString("status");
                results.add(temp);
            }

        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return results;
    }

    public void updateStatus(String id, String status) {
        try {

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
            Statement myStatement = myConn.createStatement();
            String sql = "UPDATE user SET status = '" + status + "' WHERE userID = '" + id + "'";
            myStatement.executeUpdate(sql);



        }

        catch(Exception  ex){
            ex.printStackTrace();
        }
    }
}
