package br.com.mloubake.desafiomobits.model;

import java.util.Date;

public class Movimentacao {

    int movimentacaoId;
    String data;
    String horario;
    String tipoMovimentacao;
    int contaOrigem;
    int contaDestino;
    float valor;

    public Movimentacao(int movimentacaoId, String data, String horario, String tipoMovimentacao, int contaOrigem, int contaDestino, float valor) {
        this.movimentacaoId = movimentacaoId;
        this.data = data;
        this.horario = horario;
        this.tipoMovimentacao = tipoMovimentacao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public Movimentacao(String data, String horario, String tipoMovimentacao, int contaOrigem, int contaDestino, float valor) {
        this.data = data;
        this.horario = horario;
        this.tipoMovimentacao = tipoMovimentacao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public Movimentacao() {

    }
    public int getMovimentacaoId() {
        return movimentacaoId;
    }
    public void setMovimentacaoId(int movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }

    public String  getData() {
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

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }
    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
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
