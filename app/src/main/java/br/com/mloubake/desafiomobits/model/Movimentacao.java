package br.com.mloubake.desafiomobits.model;

import java.util.Date;

public class Movimentacao {

    Date data;
    Date horario;
    String tipoMovimentacao;
    float valor;

    public Movimentacao(Date data, Date horario, String tipoMovimentacao, float valor) {
        this.data = data;
        this.horario = horario;
        this.tipoMovimentacao = tipoMovimentacao;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

    public Date getHorario() {
        return horario;
    }
    public void setHorario(Date horario) {
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
