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
    
    private String makeUsersTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        
        b.append("<tr>");
        b.append("<th>Username</th>");
        b.append("<th>User Type</th>");
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
    
    private String makeUserBookingsTable(ArrayList list) {
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
    
    private String makeBookingsTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        
        b.append("<tr>");
        b.append("<th>ID</th>");
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
    
    private String makeJourneysTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");        
        b.append("<tr>");
        b.append("<th>Destination</th>");
        b.append("<th>Distance</th>");
        b.append("<th>Date</th>");
        b.append("<th>Time</th>");
        b.append("<th>Registration</th>");
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
    }
    
    private String makeInvoiceTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        b.append("<tr>");
        b.append("<th>ID</th>");
        b.append("<th>Name</th>");
        b.append("<th>Driver Registration</th>");
        b.append("<th>Mileage</th>");
        b.append("<th>Date</th>");
        b.append("<th>Time</th>");
        b.append("<th>Price (£)</th>");
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
    
    private String makeDriverTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        b.append("<tr>");
        b.append("<th>Registration</th>");
        b.append("<th>Name</th>");
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
    
    private String makeDriverJourneysTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        b.append("<tr>");
        b.append("<th>JID</th>");
        b.append("<th>Registration</th>");
        b.append("<th>Destination</th>");
        b.append("<th>Distance</th>");
        b.append("<th>Date</th>");
        b.append("<th>Time</th>");
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
    
    private String makeOperationsTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        b.append("<tr>");
        b.append("<th>Customer Name</th>");
        b.append("<th>Destination</th>");
        b.append("<th>Price (£)</th>");
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
    
    public String[] selectInvoices(String query) throws SQLException{
      String[] result = new String[8];
      int index = 0;
      
      try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    result[index++] = row1;
                }
            }
        }
        catch(SQLException e) {
            System.out.println("way way"+e);
            //results = e.toString();
        }
        return result;
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
    
    public String[] retrieveReportingInformation(String date) {
        String turnover = "0";
        String customersServed = "0";
        String turnoverQuery = "SELECT SUM(PRICE) FROM INVOICES WHERE DATE = '" + date + "'";
        String customerThroughputQuery = "SELECT COUNT(DISTINCT CUSTOMERNAME) FROM INVOICES WHERE DATE = '" + date + "'";
        String result = null;
        
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(turnoverQuery);
            
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    result = row1;
                }
            }
            
            if (result != null) {
                turnover = String.valueOf(result);
            }
            
            rs = statement.executeQuery(customerThroughputQuery);
            
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    result = row1;
                }
            }
            
            customersServed = String.valueOf(result);
        }
        catch(SQLException e) {
            System.out.println("Error retrieving reporting information " + e);
        }
        
        String[] results = new String[] {turnover, customersServed}; 
        
        return results;
    }
    
    public int retrieveNextID(){
        String query = "SELECT MAX(ID) FROM TEST.USERS";
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
    
    public int retrieveNextBookingID(){
        String query = "SELECT COUNT(ID) FROM TEST.DEMANDS";
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
            System.out.println("way way" + e);
            //results = e.toString();
        }
        return result;
    }
    
    public int retrieveNextJourneyID(){
        String query = "SELECT COUNT(JID) FROM TEST.JOURNEY";
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
            System.out.println("way way" + e);
            //results = e.toString();
        }
        return result;
    }
    
    public int retrieveNextInvoiceID(){
        String query = "SELECT COUNT(ID) FROM TEST.INVOICES";
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
            System.out.println("way way" + e);
            //results = e.toString();
        }
        return result;
    }
    
    public String retrieve(String query) throws SQLException {
        select(query);
        
        if (query.contains("jrny.destination, jrny.distance, jrny.date, jrny.time, jrny.registration")) {
            return makeJourneysTable(rsToList());
        }
        else if (query.contains("select jid, journey.registration, destination, distance, date, time from journey")) {
            return makeDriverJourneysTable(rsToList());
        }
        else if (query.contains("SELECT CUSTOMERNAME, JRNY.DESTINATION, PRICE FROM INVOICES")) {
            return makeOperationsTable(rsToList());
        }
        else if (query.contains("id") && query.contains("demands")) {
            return makeBookingsTable(rsToList());
        } 
        else if (query.contains("users")) {
            return makeUsersTable(rsToList());
        }
        else if(query.contains("drivers")) {
            return makeDriverTable(rsToList());
        }
        else if(query.contains("INVOICES") || query.contains("invoices")){
            return makeInvoiceTable(rsToList());
        }
        else if (query.contains("name, address, destination, date, time, status")) {
            return makeUserBookingsTable(rsToList());
        }
        
        return makeTable(rsToList());
    }
    
    public String[] retrieveQueryWithStringArray(String query){
      return RunQuery(query);
    }
    
    public String[] RunQuery(String qry){
      ArrayList<String> result = new ArrayList<>();
        try  {
            select(qry);
            
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    result.add(row1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] results = result.toArray(new String[result.size()]);
        return results;
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
    
    public boolean existsInDemands(String demandID) {
        boolean bool = false;
        try  {
            select("select ID from DEMANDS where id="+demandID);
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public boolean existsInDriver(String driverReg) {
        boolean bool = false;
        try  {
            select("select registration from drivers where registration='"+driverReg+"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public String[] getTimeFromDemands(String id) {
        String[] result = new String[2];
        int index = 0;
        try  {
            select("select date, time from demands where id="+id);
            
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    result[index++] = row1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean doesTimeExistInJourney(String[] query) {
        boolean bool = false;
        try  {
            select("select time from journey where registration='"+query[0]+"' And date = '" + query[1] + "' AND time ='" + query[2] + "'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public boolean doesABookingExist(String date, String time) {
        boolean bool = false;
        try  {
            select("select date, time from demands where date='"+date+ "' " +  "AND" +
                    "time='" + time +"'");
            if(rs.next()) {
                System.out.println("TRUE");         
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public boolean insertUser(String[] str){
        PreparedStatement ps = null;
        boolean success = false;
        try {
            ps = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); 
            ps.setString(2, str[1].trim());
            
            // If a new customer account is being created then they won't be able to specify a userType
            // so we set the userType to customer
            if (str[2] == null) {
                str[2] = "customer";
            }
            
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
    
    public boolean insertInvoice(String[] str){
        PreparedStatement ps = null;
        boolean success = false;
        try {
            ps = connection.prepareStatement("INSERT INTO INVOICES VALUES (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(retrieveNextInvoiceID())); 
            ps.setString(2, str[0].trim());
            ps.setString(3, str[1].trim());
            ps.setString(4, str[2].trim());
            ps.setString(5, str[3].trim());
            ps.setString(6, str[4].trim());
            ps.setString(7, str[5].trim());
            ps.setString(8, str[6].trim());
            success = ps.executeUpdate() != 0;
        
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
         return success;
    }
    
    public boolean insertJourney(String[] str){
        PreparedStatement ps = null;
        boolean success = false;
        try {
          String[] demandDetails = GetBookingDetails(str[0]);
            ps = connection.prepareStatement("INSERT INTO journey VALUES (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(retrieveNextJourneyID()));
            ps.setString(2, str[0].trim()); //     Demand ID
            ps.setString(3, demandDetails[0]); //  Destination
            ps.setString(4, str[2]); //            Distance
            ps.setString(5, str[1]); //            Registration
            ps.setString(6, demandDetails[1]); //  Date
            ps.setString(7, demandDetails[2]); //  Time
            success = ps.executeUpdate() != 0;
            
            if(success){
              String[] updateDemands = {"Assigned", str[0]};
              updateDemands(updateDemands);
            }
        
            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
         return success;
    }
    
    public boolean insertBooking(String[] str){
        PreparedStatement ps = null;
        boolean success = false;
        try {
            ps = connection.prepareStatement("INSERT INTO demands VALUES (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(retrieveNextBookingID())); // Sets the ID.
            ps.setString(2, str[0].trim()); // Sets the Name.
            ps.setString(3, str[1].trim()); // Sets The Address.
            ps.setString(4, str[2].trim()); // Sets the Destination.
            ps.setString(5, str[3].trim()); // Sets the date.
            ps.setString(6, str[4].trim()); // Sets the time.
            ps.setString(7, "Outstanding"); // Sets the status.
            ps.setString(8, str[0].trim()); // Sets the Booked_By.
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
    
    public void updateDemands(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update DEMANDS Set STATUS=? where ID=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); 
            ps.setString(2, str[1].trim());
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTableWithQuery(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
        
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void updatePrices(String[] qry) {
      PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update PRICING Set price=?",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, qry[0].trim()); 
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
    
    public String[] GetBookingDetails(String id) {
        String[] results = new String[3];
        int index = 0;
        try  {
            select("select destination, date, time from demands where id = " + id);
            for (Object s : rsToList()) {
                String[] row = (String[]) s;
                for (String row1 : row) {
                    results[index++] = row1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
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
            jdbc.insertUser(users);            
        else {
                jdbc.update(users);
                System.out.println("user name exists, change to another");
        }
        jdbc.delete("aydinme");
        
        System.out.println(jdbc.retrieve(str));
        jdbc.closeAll();
    }            
}
