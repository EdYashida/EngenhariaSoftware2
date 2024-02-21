
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Debito;

public class Debitodao {
    
    private Acesso acesso;

    
    public Debitodao (){
        acesso = Acesso.getInstance();
    }
    
    public boolean inserir(String ra, Date now){ // aluno comeca a dever
        String sql = "INSERT INTO debito(RA, Deve, Data) VALUES (?,?,?)";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, ra);
            stmt.setBoolean(2, true);
            stmt.setDate(3, (java.sql.Date) now);
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean buscarDebAl(String ra){ // verifica se tem debito no nome do aluno
        String sql = "SELECT deve FROM debito WHERE ra=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, ra);
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
        public boolean ocuparDebAl(String ra){ //aluno deve empréstimo
        String sql = "UPDATE debito SET deve=? WHERE ra=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, ra);
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
        public boolean liberarDebAl(String ra){ // aluno nao deve mais
        String sql = "UPDATE debito SET deve=? WHERE ra=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setString(2, ra);
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
}
