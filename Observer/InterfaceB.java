
package obs;

import java.util.Observable;
import java.util.Observer;

public class InterfaceB implements Observer {
private ConcreteObservable c = ConcreteObservable.getInstance();
@Override//implementa o método Update para tratar o dado alterado
public void update(Observable o, Object arg) {
if (c.getPeso() <= 5) {
System.out.println("Dentro do limite de peso");
}
else if(c.getPeso() > 5)
System.out.println("Fora do limite de peso");
}
}
