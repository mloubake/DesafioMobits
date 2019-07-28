package br.com.mloubake.desafiomobits.model;

public class Usuario {

    int usuarioId;
    int contaCorrente;
//    Conta numeroConta;
    int contaSenha;
    int tipoCliente;

    public Usuario(int usuarioId, int contaCorrente, int contaSenha, int tipoCliente) {
        this.usuarioId = usuarioId;
        this.contaCorrente = contaCorrente;
        this.contaSenha = contaSenha;
        this.tipoCliente = tipoCliente;
    }

    public Usuario() {
    }

    public int getUsuarioId(){
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getContaCorrente(){
        return contaCorrente;
    }
    public void setContaCorrente(int contaCorrente) {
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
