
package controller;

import dao.Alunodao;
import dao.Debitodao;
import dao.Emprestimodao;
import dao.Itemdao;
import dao.Livrodao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Aluno;
import model.Emprestimo;
import model.Item;
import model.Livro;

public class EmprestimoCtrl {
    
    String raaux = "novo"; //assume o valor do ultimo RA registrado pra saber se ainda é o mesmo empréstimo
    int prazoaux=0; //salva o prazo do ultimo item emprestimo para determinar qual a data prevista do empréstimo
    String codempaux = "codnovo"; //salva o codigo do ultimo emprestimo para saber se agora se está sendo trabalhado com outro
    Date dataDev;
    
    private Observer observer;
    
    public String emprestarLivro(String ra, String codEx,String codEmp){
        
        Date now = new Date(System.currentTimeMillis());
        Alunodao alunodao= new Alunodao();
        boolean existeregistro = alunodao.buscaAluno(ra);//verifica se aluno esta cadatrado
        
        if(existeregistro=true){ //se existe aluno, segue
            Debitodao debitodao = new Debitodao();
            Emprestimodao empdao = new Emprestimodao();
            Livrodao livdao = new Livrodao();
            
            boolean tememp = empdao.buscasEmpAl(ra); // aluno tem algum emprestimo?
            
            if(tememp = true){ // se tiver, confere se está atrasado
                boolean atrasado = empdao.emprestimoAtrasado(ra, now);
                
                if(atrasado = true){ // se estiver atrasado, cria debito relacionado ao aluno
                    debitodao.inserir(ra,now);
                }
            }

            boolean temdebito = debitodao.buscarDebAl(ra); //verifica se aluno tem debito
            
            if(temdebito = false){ //se nao tem debito, prossegue
                
                boolean emprep = empdao.buscasEmpCod(codEx);
                
                if(emprep = true && raaux != ra){ //error --> nao pode repetir codigo de emprestimo para aluno diferente
                                                        // deve ter outro cod pois eh outro emprestimo
                   return "Ja existe emprestimo com esse codigo";
                    
                }
                 //se for mesmo codigo e RA, entao esta inserindo mais um livro no emprestimo
                 
                 Emprestimo emprestimo = new Emprestimo(); //criando emprestimo

                 
                   boolean cadastrado = livdao.buscaCadastrado(codEx); // verifica se livro esta cadastrado
                   
                   if (cadastrado=true){ // cadastrado
                       boolean unico = livdao.exUnico(codEx);//exemplar eh da biblioteca?
                       
                       if(unico = false){ //nao eh exemplar bilbioteca, pode emprestar
                           
                           boolean disp = livdao.exDisponivel(codEx);//esta disponivel?
                           
                           if(disp = true){ //ta disponivel
                               
                               //recupera info do livro para criar objeto livro para entao criar item emprestimo
                               
                               String isb = livdao.pegaIsbn(codEx);
                               String nom = livdao.pegaNome(codEx);
                               int praz = livdao.pegaPrazo(codEx);
                               boolean un = livdao.pegaExBib(codEx);
                               
                               Livro liv = new Livro(isb,nom,praz,codEx,un);
                               Item itememp = new Item(liv);
                               
                               Itemdao itdao = new Itemdao();
                               itdao.inserir(liv, ra, codEx);
                               livdao.ocupar(liv);

                               empdao.inserir(codEmp, ra, now); //registra codigo emprestimo, ID do aluno e data do emprestimo
                               // data de devolução prevista corrigida a seguir
                               
                               if(raaux == ra && prazoaux<liv.verPrazo() && codempaux==codEmp){
                               //atvidado para mais um livro em um determinado emprstimo
                               //se o novo livro do mesmo emprestimo tiver um prazo maior, altera prazo de entrega considerando ele
                                
                                Date dataPrv = itememp.calculaDataDevolucao(now); //calcula data de devolucao considerando o item com o maior prazo registrado no emprestimo
                                empdao.atualizaEmprestimoPrazo(codEmp, dataPrv);//atualiza a data de devolucao do emprestimo
                                prazoaux = liv.verPrazo(); //salva maior prazo de item adicionado ao emprestimo
                               }
                               
                               else if(raaux != ra || codempaux != codEmp){
                                //ativado para o primeiro livro de um novo emprestimo
                                  Date dataPrv = itememp.calculaDataDevolucao(now); 
                                  empdao.atualizaEmprestimoPrazo(codEmp, dataPrv);
                                  prazoaux = liv.verPrazo();
                               }
                               
                               
                               //atualiza variaveis auxiliares para salvarem dados sobre o ultimo emprestimo realizado
                               raaux = ra;
                               codempaux = codEmp;
                               //dados do emprestimo
                               return "Empréstimo realizado! \n Código do livro emprestado: "+codEx+"\n Nome do livro: "+ liv.verNome()+"RA do aluno: "+ra+"Devolução até: "+dataDev ;
                               
                           }
                           else if(disp = false){ //ta emprestado
                               return "exemplar indisponivel";
                           }
                           
                       }
                       else if(unico = true){
                           return "Exemplar unico, nao pode ser emprestado";
                       }
                       
                   }
                   else if(cadastrado=false){ //nao cadastrado
                           return "livro nao cadastrado";
                           }
                   
                
                
                
            }
            else if(temdebito = true){ // se tiver debito encerra
            return "O aluno tem debito pendente";
        }
            
        }
        else if(existeregistro=false){// se não tiver cadastro encerra
            
            return "Aluno não cadastrado";
        }
        
        
        return "eae";
    }
    
        public void setObserver(Observer observer){
        this.observer=observer;
    }
    
}
