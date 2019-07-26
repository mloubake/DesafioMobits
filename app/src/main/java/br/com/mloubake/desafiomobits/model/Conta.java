package br.com.mloubake.desafiomobits.model;

public class Conta {

    int contaCorrente;
    float saldo;

    public Conta(int tipoConta, float saldo) {
        this.contaCorrente = tipoConta;
        this.saldo = saldo;
    }

    public Conta(float saldo) {
        this.saldo = saldo;
    }

    public Conta() {

    }

    public int getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(int contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
