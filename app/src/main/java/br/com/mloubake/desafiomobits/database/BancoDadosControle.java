package br.com.mloubake.desafiomobits.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoDadosControle {
    private SQLiteDatabase db;
    private BancoDados banco;

    public BancoDadosControle(Context context) {
        banco = new BancoDados(context);
    }

    public String inserirDadoUsuario(int contaCorrente, int contaSenha, int tipoCliente) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoDados.KEY_CONTA_CORRENTE, contaCorrente);
        valores.put(BancoDados.KEY_CONTA_SENHA, contaSenha);

        valores.put(BancoDados.KEY_TIPO_CONTA, tipoCliente);

        resultado = db.insert(BancoDados.TABELA_USUARIO, null, valores);
        db.close();

        if(resultado == -1) {
            return "Erro ao inserir";
        }
        else
            return "Inserção com sucesso";
    }
}
