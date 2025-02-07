import java.sql.*;

public class DBUtils {
    private static String url = "jdbc:mysql://localhost:3306/Lab03";

    //? created this user with minimal control (SELECT, INSERT, UPDATE, FILE)
    private static String appUsername = "abood";
    private static String appPassword = "12345";

    public static Connection establishConnection(){
        Connection con = null;
        try{            
            con = DriverManager.getConnection(url, appUsername, appPassword);
            System.out.println("Connection Successful");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static void closeConnection(Connection con,Statement stmt){
        try{
            stmt.close();
            con.close();
            System.out.println("Connection is closed");        
        }catch(SQLException e){
            e.getMessage();
        }
    }
}

