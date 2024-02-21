
package controller;

import dao.Alunodao;
import model.Aluno;


public class InsereAlunoCtrl {
    
    private Observer observer;
    
    public boolean cadastrarAluno(String RA, String nome){
      Alunodao alunodao = new Alunodao();
      Aluno aluno= new Aluno(nome, RA);
      boolean existente;
      //verifica RA
      //verifica se ja tem algum aluno com o mesmo RA no banco
     existente = alunodao.buscaAluno(aluno.getRA());
     //se sim - return false;
     if (existente = true){
         if (observer != null) {
            observer.update();
        }
         return false;
         //ja existe aluno com esse RA
     }
     //se não, executa a seguir...
     else{
      alunodao.inserir(aluno);
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
