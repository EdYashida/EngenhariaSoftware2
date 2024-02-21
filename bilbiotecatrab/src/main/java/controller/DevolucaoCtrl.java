
package controller;

import dao.Devolucaodao;
import dao.Emprestimodao;
import dao.Itemdao;
import dao.Itemdevolucaodao;
import dao.Livrodao;
import java.util.Date;
import model.Devolucao;
import model.ItemDevolucao;
import model.Livro;


public class DevolucaoCtrl {
    
        private Observer observer;
        Itemdao itemdao = new Itemdao();
        Livrodao livdao = new Livrodao();
        Devolucaodao devdao = new Devolucaodao();
        Emprestimodao emprdao = new Emprestimodao();
        String idempaux="ola";
        Double multaaux;  
        
        public String devolucao(String coDex, String idEmp){ //precisa de codigo do exemplar para alterar disponibilidade
                                                             //ID do emprestimo para identificar com qual emprestimo o item devolvido estava atrelado
                                                              
            Date now = new Date(System.currentTimeMillis());
            

            //consulta 
           Date dataDevolvido = emprdao.pegaPrazo(idEmp); //pega data limite pra veriricar se devolucao esta dentro 
            
           Devolucao dev= new Devolucao(now,false,0);
           
             //verifica se o livro está no empréstimo em questão
            boolean itemnoemp = itemdao.confereEmp(idEmp, coDex);

            if(itemnoemp==false){ //se der erro
                                  //nao foi encontrado o livro informado no emprestimo informado
                return "Item nao encontrado no empréstimo informado";
            }
            
            //criando livro para criar itemDevolucao
            
            String isb = livdao.pegaIsbn(coDex);
            String nom = livdao.pegaNome(coDex);
            int praz = livdao.pegaPrazo(coDex);
            boolean un = livdao.pegaExBib(coDex); 
                               
            Livro liv = new Livro(isb,nom,praz,coDex,un);
            ItemDevolucao itdv = new ItemDevolucao(liv,now);
            livdao.disponibilizar(liv);//disponibiliza exemplar novamente
            
            double multa = 0;
            
            
            //eh usado uma variavel de multa e de Idemp auxiliar, se for o mesmo Emp da ultima chamada do codigo,
            //atualiza o valor da multa da devolucao
            
           
           if(dataDevolvido.after(now)){ //se atrasou, calcula multa
               if (idempaux==idEmp){ // se for sobre o memso emprestimo, atualiza valor da multa
                   multa = dev.calculaMulta(itdv);
                   multa = multa+multaaux; //salva o valor da multa ate entao
                   multaaux=multa;
                   dev.setValorMulta(multa);
                   devdao.mudaMulta(idEmp, dev); //UPDATE no valor da multa
                   
               }
               else if(idempaux!=idEmp){
              multa = dev.calculaMulta(itdv);
              multaaux = multa; //salva o valor da multa ate entao
              dev.setAtraso(true); //atualiza objeto devolucao
              dev.setValorMulta(multa);
               }
           }
           
           Itemdevolucaodao itdevdao = new Itemdevolucaodao();
           itdevdao.inserir(itdv);
           
           if(idempaux!=idEmp){
           devdao.inserir(dev, idEmp); //insere devolucao apenas se for a primeira devolucao de um emprestimo
           }
           
           
           fechaEmprestimo(coDex,idEmp);
           idempaux=idEmp; //assume o codigo do emprestimo da ultima devolucao
            
           if(multa!=0){ //se houver multa
               return "O livro "+liv.verNome()+"foi de devolvido com atraso em "+now+"\n multa: R$"+multa;
           }
            return "O livro "+liv.verNome()+"foi de devolvido em "+now;
            
            
        }
        
        public boolean fechaEmprestimo(String coDex, String idEmp){ //veririca se todos os itens de uma empréstimo foram devolvidos
            itemdao.devolveItem(coDex, coDex); //seta item emp como devolvido
            boolean fechado = itemdao.existeNDevolvido(idEmp);//confere se encontra mais item nao devolvido associado ao emprestimo
            if(fechado=false){//se sim, retorna false e segue em frente
                return false;
            }
            else if(fechado=true){ //se nao, retornar true e seta emp como fechado
               emprdao.alteraFechado(idEmp);
                return true;
            }
            return true;
        }
        
    
        public void setObserver(Observer observer){
        this.observer=observer;
    }
}
