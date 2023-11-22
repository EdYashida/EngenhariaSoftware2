
package obs;

import java.util.Observable;
import java.util.Observer;

public class InterfaceA implements Observer {
private ConcreteObservable c = ConcreteObservable.getInstance();
@Override //implementa o método Update para tratar o dado alterado
public void update(Observable o, Object arg) {
System.out.println(" Mudou para:"+ c.getPeso()+"kg");
}



}
