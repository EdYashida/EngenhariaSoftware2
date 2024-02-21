
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
import model.Livro;

public class Livrodao {
        private Acesso acesso;


        public Livrodao(){
            acesso = Acesso.getInstance();
    }
        
        public boolean inserir(Livro livro){
        String sql = "INSERT INTO titulo(codigo, nome, prazo, disponivel, isbn, unico) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, livro.verCod());
            stmt.setString(2, livro.verNome());
            stmt.setInt(3, livro.verPrazo());
            stmt.setBoolean(4, true); //quando cadastra  novo aluno, ele nao tem debito
            stmt.setString(5,livro.verIsbn());
            stmt.setBoolean(6, livro.verunico());
            stmt.execute();
            return true;   
        } catch (SQLException ex){
            Logger.getLogger(Livrodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean disponibilizar(Livro livro){ //disponibiliza livro quando devolvido
        String sql = "UPDATE livro SET disponivel=?  WHERE codigo=? AND disponivel=false";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setString(2, livro.verCod());
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean ocupar(Livro livro){ //ocupar quando for emprestado
        String sql = "UPDATE livro SET disponivel=?  WHERE codigo=? AND disponivel=true";
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setString(2, livro.verCod());
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /* public String buscaCodigo(Livro livro){ //retorna o codigo de somente 1 titulo que estja disponivel para realizar emprestimo
        String sql = "SELECT titulo codigo=? WHERE nome=? AND disponivel=true LIMIT 1"; 
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, livro.verCodigo());
            stmt.setString(2, livro.verNome());
            stmt.execute();
            return livro.verCodigo();
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return "nenhum exemplar disponivel";
        }
    }*/
     
     public boolean buscaCadastrado(String codex){ //retorna true se ja tiver cadastrado titulo com um codigo especifico
        String sql = "SELECT nome FROM livro WHERE codigo=? LIMIT 1"; 
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codex);
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public boolean exUnico(String codex){ //retorna true se livro for exemplar unico
        String sql = "SELECT nome FROM livro WHERE codigo=? AND unico = true"; 
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codex);
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public boolean exDisponivel(String codex){ //retorna true se exemplar estiver disponivel
        String sql = "SELECT nome FROM livro WHERE codigo=? AND disponivel = true"; 
        try{
            PreparedStatement stmt = acesso.connection.prepareStatement(sql);
            stmt.setString(1, codex);
            stmt.execute();
            return true;
        }
        catch (SQLException ex){
            Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
    //metodos para recuperacao de info de livro registrado
     
     
     public String pegaIsbn(String codex) { // retorna o ISBN do livro
    String isbn = null; // inicializa isbn como null
    
    String sql = "SELECT isbn FROM livro WHERE codigo = ?"; // corrigido o SQL para selecionar o ISBN da tabela livro
    try {
        PreparedStatement stmt = acesso.connection.prepareStatement(sql);
        stmt.setString(1, codex);
        ResultSet resultSet = stmt.executeQuery(); // Executa a consulta e obtém o conjunto de resultados
        
        if (resultSet.next()) { // Verifica se há algum resultado
            isbn = resultSet.getString("isbn"); // Obtém o ISBN da linha atual do conjunto de resultados
        }
        
        resultSet.close(); // Fecha o conjunto de resultados
        stmt.close(); // Fecha o statement
        
        return isbn; // Retorna o ISBN (pode ser null se nenhum livro com o código especificado for encontrado)
    } catch (SQLException ex) {
        Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
        return null; // Retorna null em caso de exceção SQL
    }
}
     
     public String pegaNome(String codex) { // retorna o Nome do livro
    String nome = null; // inicializa nome como null
    
    String sql = "SELECT nome FROM livro WHERE codigo = ?"; // corrigido o SQL para selecionar o ISBN da tabela livro
    try {
        PreparedStatement stmt = acesso.connection.prepareStatement(sql);
        stmt.setString(1, codex);
        ResultSet resultSet = stmt.executeQuery(); // Executa a consulta e obtém o conjunto de resultados
        
        if (resultSet.next()) { // Verifica se há algum resultado
            nome = resultSet.getString("nome"); // Obtém o ISBN da linha atual do conjunto de resultados
        }
        
        resultSet.close(); // Fecha o conjunto de resultados
        stmt.close(); // Fecha o statement
        
        return nome; // Retorna o ISBN (pode ser null se nenhum livro com o código especificado for encontrado)
    } catch (SQLException ex) {
        Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
        return null; // Retorna null em caso de exceção SQL
    }
}
     
     public int pegaPrazo(String codex) { // retorna o Nome do livro
    int prazo = 0; // inicializa nome como null
    
    String sql = "SELECT prazo FROM livro WHERE codigo = ?"; // corrigido o SQL para selecionar o ISBN da tabela livro
    try {
        PreparedStatement stmt = acesso.connection.prepareStatement(sql);
        stmt.setString(1, codex);
        ResultSet resultSet = stmt.executeQuery(); // Executa a consulta e obtém o conjunto de resultados
        
        if (resultSet.next()) { // Verifica se há algum resultado
            prazo = resultSet.getInt("prazo"); // Obtém o ISBN da linha atual do conjunto de resultados
        }
        
        resultSet.close(); // Fecha o conjunto de resultados
        stmt.close(); // Fecha o statement
        
        return prazo; // Retorna o ISBN (pode ser null se nenhum livro com o código especificado for encontrado)
    } catch (SQLException ex) {
        Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
        return 0; // Retorna null em caso de exceção SQL
    }
}
     
     
     public boolean pegaExBib(String codex) { // retorna o Nome do livro
    boolean exbib = false; // inicializa nome como null
    
    String sql = "SELECT prazo FROM livro WHERE codigo = ?"; // corrigido o SQL para selecionar o ISBN da tabela livro
    try {
        PreparedStatement stmt = acesso.connection.prepareStatement(sql);
        stmt.setString(1, codex);
        ResultSet resultSet = stmt.executeQuery(); // Executa a consulta e obtém o conjunto de resultados
        
        if (resultSet.next()) { // Verifica se há algum resultado
            exbib = resultSet.getBoolean("exbib"); // Obtém o ISBN da linha atual do conjunto de resultados
        }
        
        resultSet.close(); // Fecha o conjunto de resultados
        stmt.close(); // Fecha o statement
        
        return exbib; // Retorna o ISBN (pode ser null se nenhum livro com o código especificado for encontrado)
    } catch (SQLException ex) {
        Logger.getLogger(Alunodao.class.getName()).log(Level.SEVERE, null, ex);
        return true; // Retorna null em caso de exceção SQL
    }
}

     
     
     
}
