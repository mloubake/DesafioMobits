package br.com.mloubake.desafiomobits.utils;

import android.text.TextUtils;
import android.widget.EditText;

public class TextoUtils {

    public static String formatarDuasCasasDecimais(float valor) {
        String formatar = String.format("%.2f", valor);
        return formatar;
    }

    public static void tratarEditTextNulo (EditText editText, float valor) {
        if(!TextUtils.isEmpty(editText.getText())) {
            valor = Float.parseFloat(editText.getText().toString());
        }
    }


}
