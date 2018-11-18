/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author me-aydin
 */
public class Jdbc {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    //String query = null;
    
    
    public Jdbc(String query){
        //this.query = query;
    }

    public Jdbc(){
    }
    
    public void connect(Connection con){
       connection = con;
    }
    
    private ArrayList rsToList() throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) { 
          String[] s = new String[cols];
          for (int i = 1; i <= cols; i++) {
            s[i-1] = rs.getString(i);
          } 
          aList.add(s);
        } // while    
        return aList;
    } //rsToList
 
    private String makeTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
          b.append("<tr>");
          row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
          b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable
    
    private String makeBookingsTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        
        b.append("<tr>");
        b.append("<th>Name</th>");
        b.append("<th>Address</th>");
        b.append("<th>Destination</th>");
        b.append("<th>Date</th>");
        b.append("<th>Time</th>");
        b.append("<th>Status</th>");
        b.append("<tr>");
        
        for (Object s : list) {
          b.append("<tr>");
          row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
          b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable
    
    private void select(String query){
        //Statement statement = null;
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    
    public String retrieveUserType(String username){
        String query = "SELECT USERTYPE FROM TEST.USERS Where UserName = '" + username + "'";
        String result = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    result = row1;
                }
            }
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
        return result;
    }
    
    public int retrieveNextID(){
        String query = "SELECT COUNT(ID) FROM TEST.USERS";
        int result = 0;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    result = Integer.valueOf(row1) + 1;
                }
            }
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
        return result;
    }
    
    public String retrieve(String query) throws SQLException {
        String results="";
        select(query);
        if (query.contains("demands")) {
            return makeBookingsTable(rsToList());
        }
        return makeTable(rsToList());//results;
    }
    
    public boolean exists(String user) {
        boolean bool = false;
        try  {
            select("select username from users where username='"+user+"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    public boolean insert(String[] str){
        PreparedStatement ps = null;
        boolean success = false;
        try {
            ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); 
            ps.setString(2, str[1].trim());
            ps.setString(3, str[2].trim());
            ps.setString(4, String.valueOf(retrieveNextID()));
            success = ps.executeUpdate() != 0;
        
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
         return success;
    }
    public void update(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set password=? where username=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim()); 
            ps.setString(2, str[0].trim());
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(String user){
       
      String query = "DELETE FROM Users " +
                   "WHERE username = '"+user.trim()+"'";
      
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
    }
    
    public boolean checkUser(String username, String password) {
        boolean bool = false;
        try  {
            select("select username from users where username = '" + username + "' and password = '" + password + "'");
            if(rs.next()) {
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    public void closeAll(){
        try {
            rs.close();
            statement.close(); 		
            //connection.close();                                         
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws SQLException {
        String str = "select * from users";
        String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('meaydin', 'meaydin')";
        String update = "UPDATE `Users` SET `password`='eaydin' WHERE `username`='meaydin' ";
        String db = "MyDB";
        
        Jdbc jdbc = new Jdbc(str);
        Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/database/", "test", "test");
        }
        catch(ClassNotFoundException | SQLException e){
            
        }
        jdbc.connect(conn);
        String [] users = {"eaydin","benim","benim"};
        System.out.println(jdbc.retrieve(str));
        if (!jdbc.exists(users[0]))
            jdbc.insert(users);            
        else {
                jdbc.update(users);
                System.out.println("user name exists, change to another");
        }
        jdbc.delete("aydinme");
        
        System.out.println(jdbc.retrieve(str));
        jdbc.closeAll();
    }            
}
