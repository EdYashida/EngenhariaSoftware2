
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Livro;


public class Itemdao {
    
    private Acesso acesso;
    
    public Itemdao(){
        acesso = Acesso.getInstance();
    }
    
            public boolean inserir(Livro livro, String RA, String codEmp){
        String sql = "INSERT INTO itemEmprestimo(codEmp, RA, CodEx, devolvido) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, livro.verCod());
            stmt.setString(2, RA);
            stmt.setString(3, codEmp);
            stmt.setBoolean(4, false);

            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Livrodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
            public boolean confereEmp(String CodEm, String CodeX){
        String sql = "SELECT devolvido FROM itemEmprestimo WHERE codEmp = ? AND CodEx = ?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, CodEm);
            stmt.setString(2, CodeX);

            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Livrodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
            public boolean devolveItem(String CodEm, String CodeX){
        String sql = "UPDATE itemEmprestimo SET devolvido=true  WHERE codEmp = ? AND CodEx = ?";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, CodEm);
            stmt.setString(2, CodeX);

            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Livrodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
            
       public boolean existeNDevolvido(String codemp){
           String sql = "SELECT codEx FROM itemEmprestimo WHERE codEmp = ? AND devolvido = false";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codemp);

            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Livrodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       }     
           
    
}
