package br.com.mloubake.desafiomobits.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movimentacao {

    LocalDate data;
    LocalTime horario;
    float valor;
    int contaOrigem;
    int contaDestino;
    String tipoMov;

    public Movimentacao(LocalDate data, LocalTime horario, float valor,
                        int contaOrigem, int contaDestino, String tipoMov) {
        this.data = data;
        this.horario = horario;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.tipoMov = tipoMov;
    }

    public Movimentacao() {
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }
    public void setHorario(LocalTime horario) {
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
