package br.com.mloubake.desafiomobits.model;

public class Movimentacao {

    String data;
    String horario;
    float valor;
    int contaOrigem;
    int contaDestino;
    String tipoMov;

    public Movimentacao(String data, String horario, float valor,
                        int contaOrigem, int contaDestino, String tipoMov) {
        this.data = data;
        this.horario = horario;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.tipoMov = tipoMov;
    }

    public Movimentacao(String data, String horario, float valor,
                        int contaOrigem, String tipoMov) {
        this.data = data;
        this.horario = horario;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.tipoMov = tipoMov;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoMov() {
        return tipoMov;
    }
    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public int getContaOrigem() {
        return contaOrigem;
    }
    public void setContaOrigem(int contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public int getContaDestino() {
        return contaDestino;
    }
    public void setContaDestino(int contaDestino) {
        this.contaDestino = contaDestino;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
}
