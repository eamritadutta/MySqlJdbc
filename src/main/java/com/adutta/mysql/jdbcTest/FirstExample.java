package com.adutta.mysql.jdbcTest;

import java.sql.*;

/**
 * Created by eamritadutta on 9/28/14.
 */
public class FirstExample {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/FIRST_EXAMPLE";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "*Ad*0404";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // load the driver class
            Class.forName(JDBC_DRIVER);

            // open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // execute a query
            System.out.println("Creating a statement...");
            stmt = conn.createStatement();

            String query = "Select id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(query);

            // extract data from result set
            while(rs.next()) {
                // retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                // display values
                System.out.print("ID:" + id);
                System.out.print(", Age:" + age);
                System.out.print(", First:" + first);
                System.out.println(", Last:" + last);
            }
            // clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
