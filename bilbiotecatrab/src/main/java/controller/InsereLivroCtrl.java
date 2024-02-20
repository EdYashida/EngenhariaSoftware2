 
package controller;
import dao.Livrodao;
import model.Livro;

public class InsereLivroCtrl {
    private Observer observer;
    
    public boolean cadastraLivro(String codigo, String nome, Integer prazo ,String isbn, boolean unico){
        Livrodao titulodao= new Livrodao();
        Livro livro = new Livro(codigo,nome,prazo,isbn,unico);
        boolean existe = titulodao.buscaCadastrado(livro.verCod());
        if (existe==true){
            if (observer != null) {
            observer.update();
        }
            return false;
        }
        else{
            titulodao.inserir(livro);
            if (observer != null) {
            observer.update();
        }
           return true; 
        } 
    }
    
    public void setObserver(Observer observer){
        this.observer=observer;
    }
    
}
