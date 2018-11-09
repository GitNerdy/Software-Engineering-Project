package ebooking.cinema;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    String username;
    String password;
   // public User(){}
   // public User(String username, String password) { this.username = username; this.password = password;}
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "LegendOfLink30");
                    Statement myStatement = myConn.createStatement();
                    System.out.println("Connected!");
                    ResultSet myResult = myStatement.executeQuery("SELECT *  FROM user WHERE emailAddress ='" + this.username + "' AND password='" + this.password + "'");


                    if (myResult.next() == true) {
                        System.out.println("Sucess!");
                        return true;
                    }


                }
                catch(Exception  ex){
                    ex.printStackTrace();
                }

        return false;
    }
}

class RegUser extends User {
    String email;
    String fname;
    String lname;

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
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
}
