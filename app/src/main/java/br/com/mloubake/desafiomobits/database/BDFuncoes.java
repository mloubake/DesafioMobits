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

public class BDFuncoes {
    //TODO fazer lista de tarefas do que falta

    private SQLiteDatabase db;
    private BancoDados bancoDados;

    ContentValues valores;

    public BDFuncoes(Context context) {
        bancoDados = new BancoDados(context);
    }

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
        valores.put(bancoDados.COL_USUARIO_CONTA, usuario.getConta());
        valores.put(bancoDados.COL_USUARIO_SENHA, usuario.getSenha());
        valores.put(bancoDados.COL_USUARIO_TIPO, usuario.getTipo());
        db.insert(bancoDados.TABELA_USUARIO,
                null,
                valores);
        db.close();
    }
    public void popularConta(Conta conta) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.COL_CONTA_NUMERO, conta.getNumero());
        valores.put(bancoDados.COL_CONTA_SALDO, conta.getSaldo());
        db.insert(bancoDados.TABELA_CONTA,
                null,
                valores);
        db.close();
    }

    public Usuario login(int conta) {
        db = bancoDados.getReadableDatabase();
        Cursor cursor = db.query(bancoDados.TABELA_USUARIO,
                new String[] {bancoDados.COL_USUARIO_CONTA,
                        bancoDados.COL_USUARIO_SENHA,
                        bancoDados.COL_USUARIO_TIPO},
                null,
                null,
                null,
                null,
                null);

        Usuario usuario = new Usuario();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                usuario.setConta(cursor.getInt(cursor.getColumnIndex(bancoDados.COL_USUARIO_CONTA)));
                usuario.setSenha(cursor.getInt(cursor.getColumnIndex(bancoDados.COL_USUARIO_SENHA)));
                usuario.setTipo(cursor.getString(cursor.getColumnIndex(bancoDados.COL_USUARIO_TIPO)));
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

    public Conta recuperarConta(int contaNumero) {
        db = bancoDados.getReadableDatabase();

        String query = "SELECT saldo,horaSaldoNegativo FROM conta WHERE conta = " + contaNumero;

        Cursor cursor = db.rawQuery(query, null);
        Conta conta = new Conta();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                conta.setSaldo(cursor.getFloat(cursor.getColumnIndex(bancoDados.COL_CONTA_SALDO)));
                conta.setDataSaldoNegativo(cursor.getLong(cursor.getColumnIndex(bancoDados.COL_CONTA_DATA_SALDO_NEGATIVO)));
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
                conta.setNumero(cursor.getInt(cursor.getColumnIndex(bancoDados.COL_CONTA_NUMERO)));
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

    public void alterarSaldo(int conta, float valor) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.COL_CONTA_SALDO, valor);

        db.update(bancoDados.TABELA_CONTA,
                valores,
                bancoDados.COL_CONTA_NUMERO + "= ?",
                new String[] {String.valueOf(conta)});
        db.close();
    }

    public void transferirSaldo(int contaOrigem, int contaDestino, float subSaldo, float addSaldo) {
        alterarSaldo(contaOrigem, subSaldo);
        alterarSaldo(contaDestino, addSaldo);
    }

    public void registrarMovimentacao(Movimentacao movimentacao) {
        db = bancoDados.getWritableDatabase();

        valores = new ContentValues();
        valores.put(bancoDados.COL_MOV_DATA, String.valueOf(movimentacao.getData()));
        valores.put(bancoDados.COL_MOV_HORARIO, String.valueOf(movimentacao.getHorario()));
        valores.put(bancoDados.COL_MOV_VALOR, movimentacao.getValor());
        valores.put(bancoDados.COL_MOV_CONTA_ORIGEM, movimentacao.getContaOrigem());
        valores.put(bancoDados.COL_MOV_CONTA_DESTINO, movimentacao.getContaDestino());
        valores.put(bancoDados.COL_MOV_TIPO, movimentacao.getTipoMov());

        db.insert(bancoDados.TABELA_MOVIMENTACAO,
                null,
                valores);
        db.close();
    }

    public ArrayList<Movimentacao> getMovimentacao(int conta) {
        db = bancoDados.getReadableDatabase();

        String query = "SELECT * FROM movimentacao WHERE contaOrigem =" + conta + " OR contaDestino = " + conta;

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Movimentacao> arrayListMov = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndex(bancoDados.COL_MOV_DATA));
                String horario = cursor.getString(cursor.getColumnIndex(bancoDados.COL_MOV_HORARIO));
                float valor = cursor.getFloat(cursor.getColumnIndex(bancoDados.COL_MOV_VALOR));
                int contaOrigem = cursor.getInt(cursor.getColumnIndex(bancoDados.COL_MOV_CONTA_ORIGEM));
                int contaDestino = cursor.getInt(cursor.getColumnIndex(bancoDados.COL_MOV_CONTA_DESTINO));
                String tipoMov = cursor.getString(cursor.getColumnIndex(bancoDados.COL_MOV_TIPO));

                arrayListMov.add(new Movimentacao(data, horario, valor, contaOrigem, contaDestino, tipoMov));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return arrayListMov;
    }

    public void setHoraSaldoNegativo(int conta, long horarioSaldoNeg) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.COL_CONTA_DATA_SALDO_NEGATIVO, horarioSaldoNeg);

        db.update(bancoDados.TABELA_CONTA,
                valores,
                bancoDados.COL_CONTA_NUMERO + "= ?",
                new String[] {String.valueOf(conta)});
        db.close();
    }

    public Conta getSaldoNegativo(int contaNumero, long horaSaldoNegativo) {
        db = bancoDados.getReadableDatabase();

        String query = "SELECT saldo FROM conta WHERE horaSaldoNegativo =" + horaSaldoNegativo;
        Cursor cursor = db.rawQuery(query, null);

        Conta conta = new Conta();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                conta.setSaldo(cursor.getFloat(cursor.getColumnIndex(bancoDados.COL_CONTA_SALDO)));
                Log.d("", "getSaldoNegativo: ");
                if(conta.getNumero() == contaNumero) {
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return conta;
    }

    public Conta getHoraSaldoNegativo(int contaNumero) {
        db = bancoDados.getReadableDatabase();

        String query = "SELECT horaSaldoNegativo FROM conta WHERE horaSaldoNegativo IS NOT NULL";
        Cursor cursor = db.rawQuery(query, null);

        Conta conta = new Conta();
        if(cursor != null && cursor.moveToFirst()) {
            do {
                conta.setDataSaldoNegativo(cursor.getLong(cursor.getColumnIndex(bancoDados.COL_CONTA_DATA_SALDO_NEGATIVO)));
                if(conta.getNumero() == contaNumero) {
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return conta;
    }

    public void alterarDataSaldoNegatico(int conta, long horarioSaldoNegativo) {
        db = bancoDados.getWritableDatabase();
        valores = new ContentValues();
        valores.put(bancoDados.COL_CONTA_DATA_SALDO_NEGATIVO, horarioSaldoNegativo);

        db.update(bancoDados.TABELA_CONTA,
                valores,
                bancoDados.COL_CONTA_NUMERO + "= ?",
                new String[] {String.valueOf(conta)});
        db.close();
    }


}


