
package model;

import java.util.Date;


public class ItemDevolucao {
   
    Livro livro;
    Date dataDev;
    int diasAtraso;
    double multa;
    
    public ItemDevolucao(Livro livro, Date devolucao) {
		super();
		this.livro = livro;
                this.dataDev = devolucao;
		
	}
    
    public String getIdLivro(){
       return livro.verCod();
    }
    
    public int getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }


    public Date getDataDev() {
        return dataDev;
    }

    public void setDataDev(Date dataDev) {
        this.dataDev = dataDev;
    }

    public int calculaDiasAtraso(Date dataPrev){ //retorna dias de atraso entre data prevista e data de devolucao
        
        long atr = Math.abs(dataDev.getTime() - dataPrev.getTime());
        diasAtraso = (int) (atr / (1000 * 60 * 60 * 24));
        return diasAtraso;
    }
    
    
}
