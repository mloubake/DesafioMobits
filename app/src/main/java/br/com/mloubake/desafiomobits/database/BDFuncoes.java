package br.com.mloubake.desafiomobits.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Movimentacao;
import br.com.mloubake.desafiomobits.model.Usuario;

import static android.content.ContentValues.TAG;

public class BDFuncoes {
    //TODO fazer lista de tarefas do que falta

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
        valores = new ContentValues();
        valores.put(bancoDados.KEY_CONTA_NUMERO, conta.getNumero());
        valores.put(bancoDados.KEY_CONTA_SALDO, conta.getSaldo());
        db.insert(bancoDados.TABELA_CONTA,
                null,
                valores);
        db.close();
    }

    public Usuario login(int conta) {
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

    public Conta getSaldo(int contaNumero) {
        db = bancoDados.getReadableDatabase();
        String query = "SELECT saldo FROM conta WHERE conta = " + contaNumero;

        Cursor cursor = db.rawQuery(query, null);
        Conta conta = new Conta();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                conta.setSaldo(cursor.getFloat(cursor.getColumnIndex(bancoDados.KEY_CONTA_SALDO)));
                Log.d(TAG, "getSaldo: " + conta.getNumero() + " / " + conta.getSaldo());
                if((conta.getNumero() == contaNumero)) {
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return conta;
    }

    public void conceder(int conta, float valor) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.KEY_CONTA_SALDO, valor);

        db.update(bancoDados.TABELA_CONTA,
                valores,
                bancoDados.KEY_CONTA_NUMERO + "= ?",
                new String[]{String.valueOf(conta)});
        db.close();
    }

    public void retirar(int conta, float valor) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.KEY_CONTA_SALDO, valor);

        db.update(bancoDados.TABELA_CONTA,
                valores,
                bancoDados.KEY_CONTA_NUMERO + "= ?",
                new String[] {String.valueOf(conta)});
        db.close();
    }

    public void transferencia(int contaOrigem, int contaDestino, float subSaldo, float addSaldo) {
        retirar(contaOrigem, subSaldo);
        conceder(contaDestino, addSaldo);
    }

    public void criarMovimentacao(Movimentacao movimentacao) {
        db = bancoDados.getWritableDatabase();

        valores = new ContentValues();
        valores.put(bancoDados.KEY_MOV_DATA, String.valueOf(movimentacao.getData()));
        valores.put(bancoDados.KEY_MOV_HORARIO, String.valueOf(movimentacao.getHorario()));
        valores.put(bancoDados.KEY_MOV_VALOR, movimentacao.getValor());
        valores.put(bancoDados.KEY_MOV_CONTA_ORIGEM, movimentacao.getContaOrigem());
        valores.put(bancoDados.KEY_MOV_CONTA_DESTINO, movimentacao.getContaDestino());
        valores.put(bancoDados.KEY_MOV_TIPO, movimentacao.getTipoMov());

        db.insert(bancoDados.TABELA_MOVIMENTACAO,
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
        valores.put(bancoDados.KEY_CONTA_NUMERO, conta.getNumero());
        valores.put(bancoDados.KEY_CONTA_SALDO, conta.getSaldo());
        //Executa a alteração
        db.insert(bancoDados.TABELA_CONTA,
                null,
                valores);
        //Fecha o bancoDados após operação
        db.close();
    }

}

