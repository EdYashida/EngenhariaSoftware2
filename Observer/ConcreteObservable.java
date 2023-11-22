
package obs;

import java.util.Observable;

public class ConcreteObservable extends Observable {
private double mudanca;
private double peso;
public static ConcreteObservable instance = null;
//Não permite o acesso de fora da própria classe
private ConcreteObservable() {
this.mudanca = 0;
}
//Garante que apenas uma instancia da classe seja criada
public static ConcreteObservable getInstance() {
if (instance == null) {
instance = new ConcreteObservable();
}
return instance;
}

public void setMudanca(double mudanca) {
this.mudanca = mudanca;
setChanged(); // avisa que houve alterações
notifyObservers(); // notifica os observadores
}

public double getMudanca(){
    return mudanca;
}

public void setPeso(double peso) {
this.peso = peso;
setChanged(); // avisa que houve alterações
notifyObservers(); // notifica os observadores
}

public double getPeso(){
    return peso;
}

}
