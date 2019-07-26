package br.com.mloubake.desafiomobits.model;

public class Usuario {

    Conta contaCorrente;
    int contaSenha;
    int tipoCliente;

    public Usuario(Conta contaCorrente, int contaSenha, int tipoCliente) {
        this.contaCorrente = contaCorrente;
        this.contaSenha = contaSenha;
        this.tipoCliente = tipoCliente;
    }

    public Conta getContaCorrente(){
        return contaCorrente;
    }
    public void setContaCorrente(Conta contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public int getContaSenha() {
        return contaSenha;
    }
    public void setContaSenha(int contaSenha) {
        this.contaSenha = contaSenha;
    }

    public int getTipoCliente() {
        return tipoCliente;
    }
    public void setTipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

}
