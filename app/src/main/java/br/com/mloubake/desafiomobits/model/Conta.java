package br.com.mloubake.desafiomobits.model;

public class Conta {

    int contaId;
    int numeroConta;
    int tipoConta;
    float saldo;
    Movimentacao movimentacao;

    public Conta(int numeroConta, float saldos) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public Conta() {

    }

    public int getContaId() {
        return contaId;
    }
    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public int getNumeroConta() {
        return numeroConta;
    }
    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
