
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ItemDevolucao;


public class Itemdevolucaodao {
    
    private Acesso acesso;
    
    public Itemdevolucaodao(){
        acesso = Acesso.getInstance();
    }
    public boolean inserir(ItemDevolucao itdev){
        String sql = "INSERT INTO itemDevolucao(Codlivro, dataDev) VALUES (?,?)";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, itdev.getIdLivro());
            stmt.setDate(2, (Date) itdev.getDataDev());
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}
