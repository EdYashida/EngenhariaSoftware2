
package vieww;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import controller.InsereLivroCtrl;
import controller.Observer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class LivroCad extends JFrame implements Observer{
    
    JTextField codLiv;
    JTextField nomeLiv;
    JTextField prazo;
    JTextField isbn;
    JCheckBox unico;
    JLabel tittle;
    private InsereLivroCtrl controladorliv;
    Integer prazoliv;
    boolean retornoliv;
    
    
    public LivroCad(){
    setTitle("Cadastro livro");
        setVisible(true);
        setSize(800,500);//largura,altura
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        setLayout(null);
        //cadastra livro
        JButton ButtonLivro = new JButton();
        ButtonLivro.setText("Cadastrar Livro");
        ButtonLivro.setBounds(300,400,200,70); //x,y,largura,altura
        ButtonLivro.setFont(new Font("Arial",Font.BOLD,40));
        ButtonLivro.setForeground(new Color(237,241,238));
        ButtonLivro.setBackground(new Color(9,9,9));
        
        add(ButtonLivro);
        ButtonLivro.addActionListener(this::cadliv);
        
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
        
        codLiv= new JTextField("Digite o Codigo do Livro");
        codLiv.setBounds(100,100,400,50);
        codLiv.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(codLiv);
        
        nomeLiv= new JTextField("Digite o título");
        nomeLiv.setBounds(100,200,400,50);
        nomeLiv.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(nomeLiv);
        
        prazo= new JTextField("Prazo de quantos dias para devolução?");
        prazo.setBounds(100,300,100,50);
        prazo.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(prazo);
        
        isbn = new JTextField("Digite o ISBN");
        isbn.setBounds(100,400,400,50);
        isbn.setFont(new Font("Arial", Font.ITALIC,40));
        
        add(isbn);
        
        
        //checkbox 
        
        unico = new JCheckBox("Volume ùnico");
        unico.setBounds(100,500,50,50);

        add(unico);
        
        
        //titulos caixa de texto
        
        JLabel jlabel1 = new JLabel ("Codigo");
        jlabel1.setBounds(30,100,90,50);
        jlabel1.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel1);
        
        JLabel jlabel2 = new JLabel ("Nome");
        jlabel2.setBounds(30,200,90,50);
        jlabel2.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel2);
        
        JLabel jlabel3 = new JLabel ("Prazo(dias)");
        jlabel3.setBounds(30,300,90,50);
        jlabel3.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel3);
        
        JLabel jlabel4 = new JLabel ("ISBN");
        jlabel4.setBounds(30,400,90,50);
        jlabel4.setFont(new Font("Arial",Font.PLAIN,20));
        
        add(jlabel4);
        
        //titulo centralizado
        tittle = new JLabel("Cadastro de livro");
        tittle.setBounds(this.getX()/2,0,300,100);
        tittle.setFont(new Font("Arial",Font.BOLD,40));
        
        add(tittle);
        
        
        
        controladorliv = new InsereLivroCtrl();
        controladorliv.setObserver(this);
}
    private void cadliv(ActionEvent actionEvent ){ //aplicar Observer para comunicar controller
        //verificar se existe livro com o mesmo cod presente no banco
        prazoliv = Integer.parseInt(prazo.getText());
        retornoliv=controladorliv.cadastraLivro(codLiv.getText(), nomeLiv.getText(), prazoliv, isbn.getText(), unico.isSelected()); 
        update();
    }
    
    @Override
    public void update(){ //mostra mensagem de acordo com o resultado
        
        
        if(retornoliv==false){
            JOptionPane.showMessageDialog(null, "Codigo ja registrado", "livro", JOptionPane.ERROR_MESSAGE);
        }
        else if(retornoliv==true){
            JOptionPane.showMessageDialog(null, "Cadastro realizado", "livro", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    private void retornoa (ActionEvent actionEvent ){
        Inicial inicial = new Inicial();
        inicial.setVisible(true);
        dispose();
    } 
}
