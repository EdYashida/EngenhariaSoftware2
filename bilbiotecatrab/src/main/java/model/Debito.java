/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Debito {
	int codigoAluno;
	public Debito(int aluno){
		this.codigoAluno =aluno;
	}
	
	public boolean verificaDebito()
	{
	//codigo aleatorio para definir se o aluno tem débito
	//é necessário fazer a verificação de forma persistente
	if(this.codigoAluno == 4)
		 return false;
	 else
		return true;
	}

}