
package obs;

import java.util.Observer;

public class Main {

  
    public static void main(String[] args) {
        //Instância os objetos observable e observer
ConcreteObservable c = ConcreteObservable.getInstance();
Observer a = new InterfaceA();
Observer b = new InterfaceB();
//Adiciona as Interfaces A e B para serem notificadas
c.addObserver(a);
c.addObserver(b);
//faz alterações no Observable
c.setPeso(4);
c.setPeso(7);
    }
    
}
