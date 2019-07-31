package br.com.mloubake.desafiomobits.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.mloubake.desafiomobits.model.Conta;
import br.com.mloubake.desafiomobits.model.Movimentacao;
import br.com.mloubake.desafiomobits.model.Usuario;

import static android.content.ContentValues.TAG;

public class BDFuncoes {
    //TODO fazer lista de tarefas do que falta

    private SQLiteDatabase db;
    private BancoDados bancoDados;

    ContentValues valores;

    public BDFuncoes(Context context) {
        bancoDados = new BancoDados(context);
    }

    //Popular Tabela Usuario (Executar somente 1 vez)
    public boolean checkarPopulacaoBD() {
        db = bancoDados.getReadableDatabase();

        String query = "Select * from usuario,conta";
        Cursor cursor = db.rawQuery(query, null);

        boolean checkTabela;
        if((cursor != null) && (cursor.getCount()>0)) {
            checkTabela = false;
            return checkTabela;
        } else {
            checkTabela = true;
            return checkTabela;
        }
    }

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

    public Conta recuperarSaldo(int contaNumero) {
        db = bancoDados.getReadableDatabase();

        String query = "SELECT saldo FROM conta WHERE conta = " + contaNumero;

        Cursor cursor = db.rawQuery(query, null);
        Conta conta = new Conta();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                conta.setSaldo(cursor.getFloat(cursor.getColumnIndex(bancoDados.KEY_CONTA_SALDO)));
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

    public Conta recuperarContaDestino(int contaNumero) {
        db = bancoDados.getReadableDatabase();

        String query = "SELECT conta FROM conta WHERE conta = " + contaNumero;

        Cursor cursor = db.rawQuery(query, null);
        Conta conta = new Conta();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                conta.setNumero(cursor.getInt(cursor.getColumnIndex(bancoDados.KEY_CONTA_NUMERO)));
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

//    public void incluirSaldo(int conta, float valor) {
//        db = bancoDados.getWritableDatabase();
//        valores = new ContentValues();
//        valores.put(bancoDados.KEY_CONTA_SALDO, valor);
//
//        db.update(bancoDados.TABELA_CONTA,
//                valores,
//                bancoDados.KEY_CONTA_NUMERO + "= ?",
//                new String[]{String.valueOf(conta)});
//        db.close();
//    }

    public void alterarSaldo(int conta, float valor) {
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
        alterarSaldo(contaOrigem, subSaldo);
//        incluirSaldo(contaDestino, addSaldo);
        alterarSaldo(contaDestino, addSaldo);
    }

    public void registrarMovimentacao(Movimentacao movimentacao) {
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

    public ArrayList<Movimentacao> getMovimentacao(int conta) {
        db = bancoDados.getReadableDatabase();

        String query = "SELECT * FROM movimentacao WHERE contaOrigem =" + conta /*+ " OR contaDestino = " + conta*/;

        Cursor cursor = db.rawQuery(query, null);
//        Cursor cursor = db.query(bancoDados.TABELA_MOVIMENTACAO,
//                new String[]{bancoDados.KEY_MOV_DATA,
//                bancoDados.KEY_MOV_HORARIO,
//                bancoDados.KEY_MOV_VALOR,
//                bancoDados.KEY_MOV_CONTA_ORIGEM,
//                bancoDados.KEY_MOV_CONTA_DESTINO,
//                bancoDados.KEY_MOV_TIPO},
//                null,
//                null,
//                null,
//                null,
//                null);

        ArrayList<Movimentacao> arrayListMov = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndex(bancoDados.KEY_MOV_DATA));
                String horario = cursor.getString(cursor.getColumnIndex(bancoDados.KEY_MOV_HORARIO));
                float valor = cursor.getFloat(cursor.getColumnIndex(bancoDados.KEY_MOV_VALOR));
                int contaOrigem = cursor.getInt(cursor.getColumnIndex(bancoDados.KEY_MOV_CONTA_ORIGEM));
                int contaDestino = cursor.getInt(cursor.getColumnIndex(bancoDados.KEY_MOV_CONTA_DESTINO));
                String tipoMov = cursor.getString(cursor.getColumnIndex(bancoDados.KEY_MOV_TIPO));

                arrayListMov.add(new Movimentacao(data, horario, valor, contaOrigem, contaDestino, tipoMov));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayListMov;
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

