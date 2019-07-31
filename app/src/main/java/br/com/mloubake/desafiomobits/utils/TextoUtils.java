package br.com.mloubake.desafiomobits.utils;

public class TextoUtils {

    public static String formatarDuasCasasDecimais(float valor) {
        String formatar = String.format("%.2f", valor);
        return formatar;
    }

}
