
package vieww;

import controller.EmprestimoCtrl;
import controller.Observer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class EmprestimoTela extends JFrame implements Observer{
    
    JTextField RAe;
    JTextField CodEmp;
    JTextField CodEX;
    JLabel tittle;
    private EmprestimoCtrl controladoremp;
    String retornoemprestimo;
    
    public EmprestimoTela(){
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
        
        //Coleta de Info
        
        CodEmp= new JTextField("Digite o Codigo do Emprestimo");
        CodEmp.setBounds(100,100,400,50);
        CodEmp.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(CodEmp);
        
        RAe= new JTextField("Digite o RA do aluno");
        RAe.setBounds(100,200,400,50);
        RAe.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(RAe);
        
        CodEX= new JTextField("Digite o Codigo do Exemplar");
        CodEX.setBounds(100,300,400,50);
        CodEX.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(CodEX);
        
        //titulos caixa de texto
        
        JLabel jlabel1 = new JLabel ("Codigo do Emprestimo");
        jlabel1.setBounds(30,100,90,50);
        jlabel1.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel1);
        
        JLabel jlabel2 = new JLabel ("RA do aluno");
        jlabel2.setBounds(30,200,90,50);
        jlabel2.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel2);
        
        JLabel jlabel3 = new JLabel ("Id do exemplar");
        jlabel3.setBounds(30,300,90,50);
        jlabel3.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel3);
        
        //empresta livro
        JButton ButtonEmp = new JButton();
        ButtonEmp.setText("Emprestar Livro");
        ButtonEmp.setBounds(300,400,200,70); //x,y,largura,altura
        ButtonEmp.setFont(new Font("Arial",Font.BOLD,40));
        ButtonEmp.setForeground(new Color(237,241,238));
        ButtonEmp.setBackground(new Color(9,9,9));
        
        add(ButtonEmp);
        ButtonEmp.addActionListener(this::empliv);
        
        controladoremp = new EmprestimoCtrl();
        controladoremp.setObserver(this);
        
        }
    
    
            private void empliv(ActionEvent actionEvent ){ //aplicar Observer para comunicar controller
        //verificar se existe livro com o mesmo cod presente no banco
        
        retornoemprestimo=controladoremp.emprestarLivro(RAe.getText(),CodEX.getText(),CodEmp.getText()); 
        update();
    }
    
    @Override
    public void update(){ //mostra mensagem de acordo com o resultado
            JOptionPane.showMessageDialog(null, retornoemprestimo, "emprestimo", JOptionPane.INFORMATION_MESSAGE);
 
    }
    
        private void retornoa (ActionEvent actionEvent ){
        Inicial inicial = new Inicial();
        inicial.setVisible(true);
        dispose();
    }
}
