package ebooking.cinema;

import java.sql.*;

public class User {

    String email;
    String password;
    boolean loggedIn = false;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean login() {

        try{
            Connection  myConn=  DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","LegendOfLink30");
            Statement  myStatment=  myConn.createStatement();
            ResultSet  myResult=  myStatment.executeQuery("SELECT *  FROM user WHERE emailAddress = '" + this.email + "' AND  password = '" + this.password + "'");
            while(myResult.next()){
                System.out.println(myResult.getString("userId")+" "+myResult.getString("emailAddress")+" "+myResult.getString("firstName")+" "+myResult.getString("lastName"));

                RegUser regUser = new RegUser();
                regUser.email = this.email;
                regUser.fname = myResult.getString("firstName");
                regUser.lname = myResult.getString("lastName");
            }
            loggedIn = true;
            System.out.println("Connected!");
        }

        catch(Exception  ex){
            ex.printStackTrace();
        }

        return loggedIn;
    }
}

class RegUser extends User {
    String fname;
    String lname;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}

class Admin extends RegUser {

}
