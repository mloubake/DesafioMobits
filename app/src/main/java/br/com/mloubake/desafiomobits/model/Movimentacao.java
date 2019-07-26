package br.com.mloubake.desafiomobits.model;

import java.util.Date;

public class Movimentacao {

    String data;
    String horario;
    String tipoMovimentacao;
    float valor;

    public Movimentacao(String data, String horario, String tipoMovimentacao, float valor) {
        this.data = data;
        this.horario = horario;
        this.tipoMovimentacao = tipoMovimentacao;
        this.valor = valor;
    }

    public Movimentacao(float valor) {
        this.valor = valor;
    }

    public Movimentacao() {

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

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
}
