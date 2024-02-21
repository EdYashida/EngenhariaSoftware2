
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Acesso {
    public Connection connection;
    private static Acesso uniqueInstance = new Acesso();
    
    public Acesso (){
        try{
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://localhost:5432/Biblioteca";
    String user = "postgres";
    String password = "Careca47";

    this.connection = DriverManager.getConnection(url, user, password);
  } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    public static Acesso getInstance() {
		return uniqueInstance;
	}
    
}
