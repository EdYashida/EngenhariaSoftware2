/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Livro {

	int prazo;
	boolean disponivel; 
	Titulo titulo;
        boolean unico;
        String codEx;

	public Livro( String isbn, String nomeliv,int prazo,String codigo, boolean unico) {
		super();
		//instância um titulo e o associa ao livro
		titulo = new Titulo(isbn,nomeliv,prazo);
		//codigo aleatório para definir se o livro é exemplar unico
                this.unico=unico;
                this.codEx=codigo;
        }

	public int verPrazo() {
		return titulo.getPrazo();
	}
        public String verNome(){
            return titulo.getNome();
        }
        public String verIsbn(){
            return titulo.getIsbn();
        }
	public boolean verificaLivro()
	{  
            return disponivel;   
	}
        public String verCod(){
            return codEx;
        }
        public boolean verunico(){
            return unico;
        }
	
}
