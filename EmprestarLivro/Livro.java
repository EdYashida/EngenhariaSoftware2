package src;


public class Livro {

	int prazo;
	boolean exemplarBiblioteca;
	Titulo titulo;

	public Livro(int codigo) {
		super();
		//instância um titulo e o associa ao livro
		titulo = new Titulo(codigo);
		//codigo aleatório para definir se o livro é exemplar unico
		if (codigo == 2 || codigo == 4 )
			exemplarBiblioteca =true;
		else
			exemplarBiblioteca =false;
	}

	public int verPrazo() {
		return titulo.getPrazo();
	}

	
	public boolean verificaLivro()
	{  return exemplarBiblioteca;
	   
	}
	
}
