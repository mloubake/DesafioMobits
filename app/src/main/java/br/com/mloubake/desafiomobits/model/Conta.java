package br.com.mloubake.desafiomobits.model;

public class Conta {

    int numero;
    float saldo;
    Movimentacao movimentacao;

    public Conta(int numero, float saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public Conta(int numero, float saldo, Movimentacao movimentacao) {
        this.numero = numero;
        this.saldo = saldo;
        this.movimentacao = movimentacao;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }
    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }


}
