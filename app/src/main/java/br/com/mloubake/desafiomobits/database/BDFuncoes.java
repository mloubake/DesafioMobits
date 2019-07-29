package br.com.mloubake.desafiomobits.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Usuario;

import static android.content.ContentValues.TAG;

public class BDFuncoes {
    private SQLiteDatabase db;
    private BancoDados bancoDados;

    ContentValues valores;
    long resultado;

    public BDFuncoes(Context context) {
        bancoDados = new BancoDados(context);
    }

    //Popular Tabela Usuario (Executar somente 1 vez)
    public void popularUsuario(Usuario usuario) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.KEY_USUARIO_CONTA, usuario.getConta());
        valores.put(bancoDados.KEY_USUARIO_SENHA, usuario.getSenha());
        valores.put(bancoDados.KEY_USUARIO_TIPO, usuario.getTipo());

        db.insert(bancoDados.TABELA_USUARIO,
                null,
                valores);
        db.close();
    }
    //Popular Tabela Conta (Executar somente 1 vez)
    public void popularConta(Conta conta) {
        db = bancoDados.getWritableDatabase();
        valores.put(bancoDados.KEY_CONTA_CORRENTE, conta.getNumero());
        valores.put(bancoDados.KEY_CONTA_SALDO, conta.getSaldo());
        db.insert(bancoDados.TABELA_CONTA,
                null,
                valores);
        db.close();
    }

    public void testeSaldo(Conta conta) {
        //Pega a referência para escrever no bancoDados
        db = bancoDados.getWritableDatabase();
        //Instância ContentValues para adicionar chave Coluna/Valor
        valores = new ContentValues();
        //Inserece as infos de Coluna/Valor
        valores.put(bancoDados.KEY_CONTA_CORRENTE, conta.getNumero());
        valores.put(bancoDados.KEY_CONTA_SALDO, conta.getSaldo());
        //Executa a alteração
        db.insert(bancoDados.TABELA_CONTA,
                null,
                valores);
        //Fecha o bancoDados após operação
        db.close();
    }

    public Usuario pegarUsuarioConta(int conta) {
        db = bancoDados.getReadableDatabase();
        Cursor cursor = db.query(bancoDados.TABELA_USUARIO,
                new String[] {bancoDados.KEY_USUARIO_CONTA,
                        bancoDados.KEY_USUARIO_SENHA,
                        bancoDados.KEY_USUARIO_TIPO},
                null,
                null,
                null,
                null,
                null);

        Usuario usuario = new Usuario();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                usuario.setConta(cursor.getInt(cursor.getColumnIndex(bancoDados.KEY_USUARIO_CONTA)));
                usuario.setSenha(cursor.getInt(cursor.getColumnIndex(bancoDados.KEY_USUARIO_SENHA)));
                usuario.setTipo(cursor.getString(cursor.getColumnIndex(bancoDados.KEY_USUARIO_TIPO)));
                Log.d(TAG, "pegarUsuario: Conta/senha/tipo: " + usuario.getConta() + " / " + usuario.getSenha() + " / " + usuario.getTipo());
                if((usuario.getConta() == conta)) {
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return usuario;
    }

    //Saldo
    public Float recuperarSaldoUsuario(/*numero da conta*/) {
        //Pega a referência para a leitura do DB
        db = bancoDados.getReadableDatabase();

        //Executando a query
        Cursor cursor = db.query(bancoDados.TABELA_CONTA,
                new String[]{bancoDados.KEY_CONTA_SALDO},
                null /*where com o num conta*/,
                null,
                null,
                null,
                null,
                null);
        //Se achar resultados, move para o primeiro item
        if(cursor != null) {
            cursor.moveToFirst();
        }
        float saldo = cursor.getFloat(cursor.getColumnIndex(bancoDados.KEY_CONTA_SALDO));

        db.close();

        return saldo;
    }


    //Activity Extrato

    //Activity Saque

    //Activity Depósito
    public void depositarValor(Conta conta) {
        valores = new ContentValues();
        valores.put(BancoDados.KEY_CONTA_SALDO,conta.getSaldo());

//        db.update(bancoDados.TABELA_CONTA,
//                valores,
//                ,
//                new String[]{"" + conta.getSaldo()});
    }

//    public void testeSaldo(float saldo) {
//        db = bancoDados.getWritableDatabase();
//        valores = new ContentValues();
//        valores.put(bancoDados.KEY_CONTA_SALDO, saldo);
////        db.insert(bancoDados.TABELA_CONTA, null, valores);
//        db.update(bancoDados.TABELA_CONTA, valores, null, null);
//        db.close();
//    }

    //TODO fazer lista de tarefas do que falta



    public float testeUpdateSaldo(int conta, float valor) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.KEY_CONTA_SALDO, valor);

        float saldo = db.update(bancoDados.TABELA_CONTA,
                valores,
                bancoDados.KEY_CONTA_SALDO + "= ?",
                new String[]{String.valueOf(conta)});

        return saldo;
    }

    //Activity Transferência

    //Activity VisitaGerente

}

