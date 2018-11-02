package ebooking.cinema;

import java.sql.*;

public class User {

    String username;
    String password;

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

                try{
                    Connection  myConn=  DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema2.0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
                    Statement  myStatment=  myConn.createStatement();
                    ResultSet  myResult=  myStatment.executeQuery("SELECT *  FROM user");
                    while(myResult.next()){
                        System.out.println(myResult.getString("userId")+" "+myResult.getString("emailAddress")+" "+myResult.getString("firstName")+" "+myResult.getString("lastName"));
                    } //while
                    System.out.println("Connected!");
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
