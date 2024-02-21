
package vieww;

import controller.DevolucaoCtrl;
import controller.Observer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DevolucaoTela extends JFrame implements Observer{
    
    JTextField CodEx;
    JTextField CodEmp;
    JLabel tittle;
    private DevolucaoCtrl controladordev;
    String retornodevolucao;
    
    public DevolucaoTela(){
        setTitle("Emprestimo");
        setVisible(true);
        setSize(800,500);//largura,altura
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        setLayout(null);
        
        //titulo centralizado
        tittle = new JLabel("Emprestimo de Livro");
        tittle.setBounds(this.getX()/2,50,400,100);
        tittle.setFont(new Font("Arial",Font.BOLD,40));
        
        add(tittle);
        
        //retorna a tela inicial
        JButton ButtonVoltara = new JButton();
        ButtonVoltara.setText("Voltar");
        ButtonVoltara.setBounds(0,0,100,50); //x,y,largura,altura
        ButtonVoltara.setFont(new Font("Arial",Font.BOLD,20));
        ButtonVoltara.setForeground(new Color(237,241,238));
        ButtonVoltara.setBackground(new Color(9,9,9));
        
        add(ButtonVoltara);
        ButtonVoltara.addActionListener(this::retornoa);
        
        //Obtem Identificacao do livro e de seu emprestimo
        
        CodEx= new JTextField("Digite o Codigo do Exemplar");
        CodEx.setBounds(100,150,400,50);
        CodEx.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(CodEx);
        
        JLabel jlabele = new JLabel ("Id do exemplar");
        jlabele.setBounds(30,150,90,50);
        jlabele.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabele);
        
        CodEmp= new JTextField("Digite o Codigo do Empréstimo");
        CodEmp.setBounds(100,150,400,50);
        CodEmp.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(CodEmp);
        
        JLabel jlabelem = new JLabel ("Id do empréstimo");
        jlabelem.setBounds(30,150,90,50);
        jlabelem.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabelem);
        
        //empresta livro
        JButton ButtonDev = new JButton();
        ButtonDev.setText("Devolver Livro");
        ButtonDev.setBounds(300,400,200,70); //x,y,largura,altura
        ButtonDev.setFont(new Font("Arial",Font.BOLD,40));
        ButtonDev.setForeground(new Color(237,241,238));
        ButtonDev.setBackground(new Color(9,9,9));
        
        add(ButtonDev);
        ButtonDev.addActionListener(this::devliv);
        
        controladordev = new DevolucaoCtrl();
        controladordev.setObserver(this); 
        
    }
    
         private void devliv(ActionEvent actionEvent ){ //aplicar Observer para comunicar controller
        //verificar se existe livro com o mesmo cod presente no banco
        
        retornodevolucao=controladordev.devolucao(CodEx.getText(), CodEmp.getText()); 
        update();
    }
    
        @Override
    public void update(){ //mostra mensagem de acordo com o resultado
  
            JOptionPane.showMessageDialog(null, retornodevolucao, "devolucao", JOptionPane.INFORMATION_MESSAGE); 
    }
            private void retornoa (ActionEvent actionEvent ){
        Inicial inicial = new Inicial();
        inicial.setVisible(true);
        dispose();
    }
}
