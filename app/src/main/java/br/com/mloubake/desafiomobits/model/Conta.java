package br.com.mloubake.desafiomobits.model;

public class Conta {

    int tipoConta;
    float saldo;

    public Conta(int tipoConta, float saldo) {
        this.tipoConta = tipoConta;
        this.saldo = saldo;
    }

    public Conta(float saldo) {
        this.saldo = saldo;
    }

    public Conta() {

    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
