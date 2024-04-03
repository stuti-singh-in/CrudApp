/* This line is importing all classes from the java.sql package, 
which contain the required classes and interfcases for JDBC - Java Database Conncectivity, 
allowing me or rather java interact with the databases.*/

import java.sql.*; 

/* This class declares a class named Main, I could use any other name, 
but for convenience and becaus eit is hosting the "main" method, 
which is the entry point of the program, I used the name "Main".*/

public class Main{ 

/*This line is  defining a constant variable "JDBC_DRIVER" and 
assigning it the value of the class name of the driver provided by the Microsoft for SQL Server.*/
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

/* here, I have defined another constant variable DB_URL and assigned
it the URL needed to connect to our SQL databse*/
	static final String DB_URL = "jdbc:sqlserver://DESKTOP-1PFUCEE\\STUTISERVER:59479;databaseName=EmployeeDB;encrypt=true;trustServerCertificate=true;";
	static final String USER = "sa";
    static final String PASS = "StutiSingh";
	

/*declared the main method, which serves as the entry point of the program. 
It's where the execution of the program begins. */    
public static void main(String[] args) {

    /* declared two variables conn and stmt of type Connection and Statement, 
    these variables will be used to establish a connection to the database and execute SQL statements. 
    The reason for using these two is they are the standard objects provided by 
    the JDBC API for managing database connections and executing SQL statements.*/

        Connection conn = null; /* The Connection object represents a connection to a database. 
    It is responsible for establishing and managing the connection to the database server.*/
 
        Statement stmt = null; // The Statement object provides methods to issue SQL commands to the database.

        try {
            Class.forName(JDBC_DRIVER); //dynamically loads the JDBC driver class specified by the JDBC_DRIVER variable using the Class.forName() method.

            //we're establishing a conncetion to the server db, using the URL, username and password specified above.
            System.out.println("Connecting to database..."); 
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

           /*The statement object is created using  the createStatement() method of the Connection object. 
          This method returns an instance of the Statement interface, which provides methods for executing SQL*/
         
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            // Insert data
            String sql = "INSERT INTO Employees (id, name, age, position) VALUES (101, 'John Doe', 30, 'Manager')";
            stmt.executeUpdate(sql);
            System.out.println("Data inserted successfully.");

            // Retrieve data
            sql = "SELECT * FROM Employees";
            ResultSet rs = stmt.executeQuery(sql); //ResultSet is an interface provided by the JDBC 
            // that represents the result set of a database query. It acts like an iterator and allows you to traverse the rows returned by a SQL query
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String position = rs.getString("position");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Position: " + position);
            }
            rs.close();

            // Update data
            sql = "UPDATE Employees SET age=31 WHERE id=101";
            int rowsUpdated = stmt.executeUpdate(sql);
            System.out.println("Rows updated: " + rowsUpdated);

            // Delete data
            sql = "DELETE FROM Employees WHERE id=101";
            int rowsDeleted = stmt.executeUpdate(sql);
            System.out.println("Rows deleted: " + rowsDeleted);



        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}