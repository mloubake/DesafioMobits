package br.com.mloubake.desafiomobits.model;

public class Conta {

    int numero;
    float saldo;
    long dataSaldoNegativo;

    public Conta() {
    }

    public Conta(int numero, float saldo) {
        this.numero = numero;
        this.saldo = saldo;
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

    public long getDataSaldoNegativo() {
        return dataSaldoNegativo;
    }
    public void setDataSaldoNegativo(long dataSaldoNegativo) {
        this.dataSaldoNegativo = dataSaldoNegativo;
    }
}
