 
package model;

import java.util.Date;


public class Devolucao {


    Date dataDevolucao;
    boolean atraso;
    double valorMulta;
    
    public Devolucao(Date datdev, boolean atraso, double valormult){
        this.atraso=atraso;
        this.dataDevolucao=datdev;
        this.valorMulta=valormult;
    }
    
        public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isAtraso() {
        return atraso;
    }

    public void setAtraso(boolean atraso) {
        this.atraso = atraso;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }
    
    
    public double calculaMulta(ItemDevolucao dev){
        int qntDia = dev.getDiasAtraso();
        valorMulta = qntDia * 5.0; //5 reais para cada dia de atraso
        return valorMulta;
    }
    
}
