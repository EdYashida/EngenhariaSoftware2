package model;

public class Titulo {
int prazo;
String nome;
String isbn;

public Titulo( String isbn, String nome,int prazo)
{
	this.prazo = prazo;
        this.nome = nome;
        this.isbn = isbn;
}
public int getPrazo() {
	return prazo;
}

public void setPrazo(int prazo) {
	this.prazo = prazo;
}
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
