
package model;

import java.util.List;

public class Aluno {
String RA;
String nome;

public Aluno(String nome, String RA) {
	super();
	//precisa ir no banco e criar o aluno
	this.RA = RA;
        this.nome = nome;
	
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getRA() {
	return RA;
}

public void setRA(String RA) {
	this.RA = RA;
}

public boolean verficaAluno()
{   //Se o RA é null é retorna erro - método aleatório
	if(this.RA.equals("10"))
	 return false;
else
	return true;
}

public boolean verificaDebito()
{       //instancia um objeto débito
        Debito debito = new Debito( Integer.parseInt(this.RA));
        
        return debito.verificaDebito();
	/* Aqui vocÊ deve chamar o metodo verificaDebito da classe debito*/
}

//Metodo que delega a funcionalidade de emprestar para a classe emprestimo
public boolean emprestar(List<Livro> livros)
{   /* Aqui você deve intanciar um objeto emprestimo */
    Emprestimo emprestimo = new Emprestimo();
	/* Aqui vocÊ deve chamar o metodo emprestar da classe empresitmo*/
        return emprestimo.emprestar(livros);
	
}
}
