package br.com.mloubake.desafiomobits.utils;

import java.util.Date;

public class JurosUtils {
    private static final float TAXA_COMPOSTA = 0.001f;

    public static float calcularJurosNegativo(float saldo, long horarioInicial) {
        Date horario = new Date();
        long horarioFinal = horario.getTime();
        long horarioDif = ((horarioFinal/1000/60) - (horarioInicial/1000/60));
        float montante = (float) (saldo * (Math.pow((1 + TAXA_COMPOSTA), horarioDif)));

        //TODO Colocar a formula do calc do juros composto como comentário
        return montante;
    }
}
