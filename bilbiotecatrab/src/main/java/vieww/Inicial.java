
package vieww;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Inicial extends JFrame {
    
    JLabel tittle;
    
    public Inicial(){
        setTitle("Página Inicial");
        setVisible(true);
        setSize(800,500);//largura,altura
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        setLayout(null);
        
        //titulo centralizado
        tittle = new JLabel("Pagina Inicial");
        tittle.setBounds(this.getX()/2,10,400,100);
        tittle.setFont(new Font("Arial",Font.BOLD,40));
        
        add(tittle);
        
        //cadastra aluno
        JButton ButtonAluno = new JButton();
        ButtonAluno.setText("Registrar aluno");
        ButtonAluno.setBounds(30,50,200,70); //x,y,largura,altura
        ButtonAluno.setFont(new Font("Arial",Font.BOLD,15));
        ButtonAluno.setForeground(new Color(237,241,238));
        ButtonAluno.setBackground(new Color(9,9,9));
        
        add(ButtonAluno);
        
        ButtonAluno.addActionListener(this::al);
        
        
        //cadastra livro
        JButton ButtonLivro = new JButton();
        ButtonLivro.setText("Registrar livro");
        ButtonLivro.setBounds(30,150,200,70); //x,y,largura,altura
        ButtonLivro.setFont(new Font("Arial",Font.BOLD,15));
        ButtonLivro.setForeground(new Color(237,241,238));
        ButtonLivro.setBackground(new Color(9,9,9));
        
        add(ButtonLivro);
        
        ButtonAluno.addActionListener(this::liv);
        
        //realiza emprestimo
        JButton ButtonEmp = new JButton();
        ButtonEmp.setText("Emprestar livro");
        ButtonEmp.setBounds(100,50,200,70); //x,y,largura,altura
        ButtonEmp.setFont(new Font("Arial",Font.BOLD,15));
        ButtonEmp.setForeground(new Color(237,241,238));
        ButtonEmp.setBackground(new Color(9,9,9));
        
        add(ButtonEmp);
        
        ButtonAluno.addActionListener(this::emp);
        
        //realiza devolução
        JButton ButtonDev = new JButton();
        ButtonDev.setText("Devolver livro");
        ButtonAluno.setBounds(100,150,200,70); //x,y,largura,altura
        ButtonDev.setFont(new Font("Arial",Font.BOLD,15));
        ButtonDev.setForeground(new Color(237,241,238));
        ButtonDev.setBackground(new Color(9,9,9));
        
        add(ButtonDev);
        
        ButtonDev.addActionListener(this::dev);
        

    }
    
    //oque ocorre ao clicar no botão
    
    
    private void al(ActionEvent actionEvent ){ //muda pra tela de cadastro de aluno
        AlunoCad alunocad = new AlunoCad();
        alunocad.setVisible(true);
        dispose();
    }
    private void liv(ActionEvent actionEvent ){ //muda pra tela de cadastro de livro
        //JOptionPane.showMessageDialog(null, "cadastro livro", "aluno", JOptionPane.ERROR_MESSAGE);
        LivroCad livrocad = new LivroCad();
        livrocad.setVisible(true);
        dispose();
    }
    private void emp(ActionEvent actionEvent ){ //muda pra tela de cadastro de aluno
        EmprestimoTela emptela = new EmprestimoTela();
        emptela.setVisible(true);
        dispose();
    }
    private void dev(ActionEvent actionEvent ){ //muda pra tela de cadastro de aluno
        DevolucaoTela devtela = new DevolucaoTela();
        devtela.setVisible(true);
        dispose();
    }
    
    
}
