package br.com.mloubake.desafiomobits.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String pegarData() {
        Date date = new Date();
        SimpleDateFormat dataFormatacao = new SimpleDateFormat("dd/MM/yyyy");
        String data = dataFormatacao.format(date);

        return data;
    }

    public static String pegarHorario() {
        Date date = new Date();
        SimpleDateFormat horarioFormatacao = new SimpleDateFormat("hh:mm:ss");
        String horario = horarioFormatacao.format(date);

        return horario;
    }
}
