/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads.dsw3.prova1.marcosjunior.models;

/**
 *
 * @author marco
 */
public class Celula {

    private int linha;
    private String coluna;
    private String cor;
    private boolean ocupada;

    public Celula(int linha, String coluna, String cor) {
        this.linha = linha;
        this.coluna = coluna;
        this.cor = cor;
        this.ocupada = false;
    }

    /**
     * @return the linha
     */
    public int getLinha() {
        return linha;
    }

    /**
     * @param linha the linha to set
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }

    //Retorna true se linha puder ser incrementada, ou false, se não.
    public boolean podeIncrementarLinha(Tabuleiro tab) {
        return (!tab.celulaEstaOcupada(this.linha + 1, this.coluna) && (this.linha < 20));

    }

    public void incrementarLinha() {
        this.linha++;
    }

    /**
     * @return the coluna
     */
    public String getColuna() {
        return coluna;
    }

    /**
     * @param coluna the coluna to set
     */
    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    public boolean podeIncrementarColuna(Tabuleiro tab) {
        //retorna true se não chegou à coluna "J"
        int i = this.getIndexColuna();
        if (i < 9) {
            return !tab.celulaEstaOcupada(this.linha, Tabuleiro.getColunas()[++i]);
        } else {
            return false;
        }
    }

    //Atualiza a coluna atual para a próxima coluna
    public void incrementarColuna() {
        String[] cols = Tabuleiro.getColunas();
        int i = this.getIndexColuna();
        this.coluna = cols[++i];
    }

    //retorna true se a coluna não for "A" (índice 0) e  se a coluna à esquerda estiver livre
    public boolean podeDecrementarColuna(Tabuleiro tab) {
        int i = this.getIndexColuna();
        if (i > 0) {
            return !tab.celulaEstaOcupada(this.linha, Tabuleiro.getColunas()[--i]);
        } else {
            return false;
        }
    }

    //Atualiza a coluna atual para a coluna anterior
    public void decrementarColuna() {
        String[] cols = Tabuleiro.getColunas();
        int i = this.getIndexColuna();
        this.coluna = cols[--i];
    }

    public int getIndexColuna() {
        String[] cols = Tabuleiro.getColunas();
        int i = 0;
        for (String col : cols) {
            if (col.equals(this.coluna)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    /**
     * @return the ocupada
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * @param ocupada the ocupada to set
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

}
