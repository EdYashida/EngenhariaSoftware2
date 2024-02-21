
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Devolucao;

public class Devolucaodao {
    private Acesso acesso;
    
    public Devolucaodao(){
     acesso = Acesso.getInstance();  
    }
    
    public boolean inserir(Devolucao devolucao, String codEmp){
        String sql = "INSERT INTO devolucao(CodEmprestimo, dataDev, Atraso, Multa) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codEmp);
            stmt.setDate(2, (Date) devolucao.getDataDevolucao());
            stmt.setBoolean(3, devolucao.isAtraso()); //quando cadastra  novo aluno, ele nao tem debito
            stmt.setDouble(4, devolucao.getValorMulta());
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean mudaMulta(String codEmp, Devolucao devolucao){
        String sql = "UPDATE devolucao SET multa=?  WHERE codEmp=?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(2, codEmp);
            stmt.setDouble(1, devolucao.getValorMulta());
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
