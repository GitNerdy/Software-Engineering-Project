package ebooking.cinema;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    String username;
    String password;
    String email;
    String fname;
    String lname;
    boolean loggedIn = false;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLoggedIn() {
        return loggedIn;
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

    public boolean login() {
                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "password");
                    Statement myStatement = myConn.createStatement();
                    System.out.println("Connected!");
                    ResultSet myResult = myStatement.executeQuery("SELECT *  FROM user WHERE emailAddress ='" + this.username + "' AND password='" + this.password + "'");

                    String admin = myResult.getString("userType");
                    if (myResult.next() == true) {
                        System.out.println("Sucess!");
                        this.loggedIn = true;
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
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "password");
            Statement myStatement = myConn.createStatement();
            System.out.println("Connected!");
            String sql = "INSERT INTO user (emailAddress, password, firstName, lastName) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "')";
            //myStatement.executeQuery("INSERT INTO user (emailAddress, password, firstName, lastName) VALUES ('" + this.username + "', '" + this.password + "', '" + this.fname + "', '" + this.lname + "')");
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
}
