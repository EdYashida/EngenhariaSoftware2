/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import model.Emprestimo;

public class Emprestimodao {
    private Acesso acesso;
    
    public Emprestimodao (){
        acesso = Acesso.getInstance();
    }
    
    public boolean inserir(String codEmp, String ra, Date emp){ // aluno comeca a dever
        String sql = "INSERT INTO emprestimo(codEmp, RA, dataEmp, dataPrev, fechado) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codEmp); //aluno que fez emprestimo
            stmt.setString(2, ra); //aluno que fez emprestimo
            stmt.setDate(3, (java.sql.Date) emp);//data atual
            stmt.setDate(4, (java.sql.Date) emp);//data prevista sera corrigida apos  a insercao dos itens do emprestimo
            stmt.setBoolean(5, false); //emprestimo aberto
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
        public boolean buscasEmpAl(String ra){ // procura se aluno tem emprestimo
        String sql = "SELECT  codEmp FROM emprestimo WHERE RA=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, ra); //aluno que fez emprestimo
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
        
        public boolean buscasEmpCod(String codEmp){ // procura se existe emprestimo com codigo inserido
        String sql = "SELECT RA FROM emprestimo WHERE codEmp=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codEmp); //codigo do emprestimo
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
        
        public boolean emprestimoAtrasado(String ra, Date prev){ //o emprestimo está atrasado?
            String sql = "SELECT dataEmp FROM emprestimo WHERE RA=? AND dataDev = null AND dataPrev < ?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, ra); //aluno que fez emprestimo
            stmt.setDate(2, (java.sql.Date) prev); 
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        }
        
        public boolean atualizaEmprestimoPrazo(String codEmp, Date prev){ //seta o prazo do emprestimo
            String sql = "UPDATE emprestimo SET dataPrev = ?  WHERE codEmp=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setDate(1, (java.sql.Date) prev); //aluno que fez emprestimo
            stmt.setString(2, codEmp); 
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        }
        
        //obter data do emprestimo
        
        public Date pegaPrazo(String codemp) { 
    Date dataPrv=null; // inicializa nome como null
    
    String sql = "SELECT DataPrev FROM emprestimo WHERE codigo = ?"; 
    try {
        PreparedStatement stmt = acesso.connection.prepareStatement(sql);
        stmt.setString(1, codemp);
        ResultSet resultSet = stmt.executeQuery(); 
        
        if (resultSet.next()) { 
            dataPrv = resultSet.getDate("DataPrev"); 
        }
        
        resultSet.close(); 
        stmt.close(); 
        
        return dataPrv; 
    } catch (SQLException ex) {
        Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
        return null; 
    }
        }
        
        public boolean alteraFechado(String codemp){ //todos os livros do emprestimo foram devolvidos
            String sql = "UPDATE emprestimo SET fechado = true  WHERE codEmp=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codemp); 
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        }
        
}
        
