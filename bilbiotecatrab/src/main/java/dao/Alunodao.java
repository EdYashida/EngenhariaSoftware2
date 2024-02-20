
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;


public class Alunodao {
    
   
    private Acesso acesso;
    
    public Alunodao (){
        acesso = Acesso.getInstance();
    }
    
    public boolean inserir(Aluno aluno){
        String sql = "INSERT INTO aluno(RA, Nome, Debito) VALUES (?,?,?)";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getRA());
            stmt.setBoolean(3, false); //quando cadastra  novo aluno, ele nao tem debito
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarNome(Aluno aluno){
        String sql = "UPDATE aluno SET Nome=? WHERE RA=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getRA());
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean buscaAluno(String RA){ //verifica se aluno esta cadastrado a partir do RA informado
        String sql = "SELECT Nome FROM aluno WHERE RA=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, RA);
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean verificaDebito(String RA){ //verifica se aluno tem debito ativo
        String sql = "SELECT debito FROM aluno WHERE RA=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, RA);
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}
