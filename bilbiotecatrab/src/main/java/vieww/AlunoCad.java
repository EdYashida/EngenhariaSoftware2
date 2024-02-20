
package vieww;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import controller.InsereAlunoCtrl;
import controller.Observer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AlunoCad  extends JFrame implements Observer{
    
    JTextField RAal;
    JTextField nomeal;
    JLabel tittle;
    private InsereAlunoCtrl controladoral;
    boolean retorno;
    
    public AlunoCad(){
    
        setTitle("Cadastro Aluno");
        setVisible(true);
        setSize(800,500);//largura,altura
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        setLayout(null);
        //cadastra aluno
        JButton ButtonAluno = new JButton();
        ButtonAluno.setText("Cadastrar Aluno");
        ButtonAluno.setBounds(300,350,200,70); //x,y,largura,altura
        ButtonAluno.setFont(new Font("Arial",Font.BOLD,40));
        ButtonAluno.setForeground(new Color(237,241,238));
        ButtonAluno.setBackground(new Color(9,9,9));
        
        add(ButtonAluno);
        ButtonAluno.addActionListener(this::cadal);
        
        //retorna a tela inicial
        JButton ButtonVoltara = new JButton();
        ButtonVoltara.setText("Voltar");
        ButtonVoltara.setBounds(0,0,100,50); //x,y,largura,altura
        ButtonVoltara.setFont(new Font("Arial",Font.BOLD,20));
        ButtonVoltara.setForeground(new Color(237,241,238));
        ButtonVoltara.setBackground(new Color(9,9,9));
        
        add(ButtonVoltara);
        ButtonVoltara.addActionListener(this::retornoa);

        //caixa de texto
        
        RAal= new JTextField("Digite o RA");
        RAal.setBounds(100,100,400,50);
        RAal.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(RAal);
        
        nomeal= new JTextField("Digite o nome do aluno");
        nomeal.setBounds(100,200,400,50);
        nomeal.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(nomeal);
        
        //titulos caixa de texto
        
        JLabel jlabel1 = new JLabel ("RA");
        jlabel1.setBounds(30,100,90,50);
        jlabel1.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel1);
        
        JLabel jlabel2 = new JLabel ("Nome");
        jlabel2.setBounds(30,200,90,50);
        jlabel2.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel2);
        
        //titulo centralizado
        tittle = new JLabel("Cadastro de aluno");
        tittle.setBounds(this.getX()/2,50,400,100);
        tittle.setFont(new Font("Arial",Font.BOLD,40));
        
        add(tittle);
        
        controladoral = new InsereAlunoCtrl();
        controladoral.setObserver(this);
   
    }
    private void cadal(ActionEvent actionEvent ){ //aplicar Observer para comunicar controller
        //verificar se existe aluno com o mesmo RA presente no banco
        //realiza o cadastro
        retorno = controladoral.cadastrarAluno(RAal.getText(), nomeal.getText());
        update();
        
        
    }
        @Override
    public void update(){ //mostra mensagem de acordo com o resultado
        
        if(retorno==false){
            JOptionPane.showMessageDialog(null, "RA ja registrado ", "aluno", JOptionPane.ERROR_MESSAGE);
        }
        else if(retorno==true){
            JOptionPane.showMessageDialog(null, "Cadastro realizado", "aluno", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void retornoa (ActionEvent actionEvent ){
        Inicial inicial = new Inicial();
        inicial.setVisible(true);
        dispose();
    }
    

}
