package br.com.mloubake.desafiomobits.model;

public class Usuario {

    int conta;
//    Conta conta;
    int senha;
    String tipo;

    public Usuario(int conta, int senha, String tipo) {
        this.conta = conta;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    public int getConta(){
        return conta;
    }
    public void setConta(int conta) {
        this.conta = conta;
    }

    public int getSenha() {
        return senha;
    }
    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
